package word_finder;

//Mark DiValerio
//10-6-2010
//Scrabble Word Finder

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import javax.swing.Timer;

//class TileBlock
//{
//	public char		letter				= ' ';
//	public int		count				= 0;
//	public int		value				= 0;
//	
//	public boolean	on_double_letter	= false;
//	public boolean	on_triple_letter	= false;
//	public boolean	on_double_word		= false;
//	public boolean	on_triple_word		= false;
//	
////	public TileBlock()
////	{
////		letter = '?';
////		count = 2;
////		value = 0;
////	}
//}


public class ScrabbleWordFinder
{
	public static final String	dict_file		= "Source Files/dictionary.txt";
	public static final String	tile_info_file	= "scrabble_tile_info.txt";
	
	public ArrayList<String>	dictionary		= new ArrayList<String>();
	
	
//	public ArrayList<TileBlock>	tiles			= new ArrayList<TileBlock>();
	
	
	public ScrabbleWordFinder()
	{
		load_files();
		intro();
	}
	
	
	public void load_files()
	{
		try
		{
			File file = new File(dict_file);
			Scanner dict_scan = new Scanner(file);
			dictionary = new ArrayList<String>();
			while(dict_scan.hasNextLine() == true)
			{
				dictionary.add(dict_scan.nextLine());
			}
		}
		catch(IOException e)
		{
			out.println("Dictionary file '" + dict_file + "' not found");
			e.printStackTrace();
			exit(1);
		}
//		try
//		{
//			File file = new File(tile_info_file);
//			Scanner tile_scan = new Scanner(file);
//			tiles = new ArrayList<TileBlock>();
//			while(tile_scan.hasNextLine() == true)
//			{
//				String line = tile_scan.nextLine();
//				if(line.charAt(0) != '/')
//				{
//					String[] temp = line.split(" ");
//					TileBlock tile = new TileBlock();
//					tile.letter = temp[0].charAt(0);
//					tile.count = Integer.parseInt(temp[1]);
//					tile.value = Integer.parseInt(temp[2]);
//					tiles.add(tile);
//				}
//			}
//		}
//		catch(IOException e)
//		{
//			out.println("Dictionary file '" + dict_file + "' not found");
//			e.printStackTrace();
//			exit(1);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
	}
	
	
	public void intro()
	{
		
		out.println("Hello there, and welcome to the Scrabble Word Finder!");
		out.println("Type in the letter-tiles you have and the Scrabble Word Finder will show you words you can use!");
		out.println("\nNOTE: For blank tiles, use QUESTION MARKS '?' (2 max)");
		out.println("      Use CAPITAL letters to lock in tile's location");
		out.println("      2 letters minimum\n\n");
		
		Scanner keyboard = new Scanner(System.in);
		out.print("Enter in your letters:");
		String temp = keyboard.nextLine();
		boolean isGoodInput = true;
		
//		if(temp.length() > 9)// check # of letters
//		// 7 standard letters plus an optional beginning letter and an optional ending letter
//		{
//			out.println("\nERROR-too many letters! (7 + 2 optional letters=9 letters max).\n");
//			isGoodInput = false;
//		}
		if(temp.length() < 2)
		{
			out.println("ERROR-not enough letters! must have at least 2 letters.\n");
			isGoodInput = false;
		}
		int counter = 0;
		for(int x = 0; x < temp.length(); x++)
		{
			char c = temp.charAt(x);
			if(!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '?'))
			// if input has numbers or symbols then show error
			{
				out.println("\nERROR-can only use letters or '?'. no numbers or symbols.\n");
				isGoodInput = false;
				break;
			}
			if(c == '?')
			{
				counter++;
			}
			if(counter > 2)
			{
				out.println("\nERROR-too many blank letters (only 2 in the game).\n");
				isGoodInput = false;
				break;
			}
		}
		
		if(isGoodInput == false)
		{
			intro();
		}
		else
		{
			char[] letters = new char[temp.length()];
			for(int x = 0; x < temp.length(); x++)
			{
				letters[x] = temp.charAt(x);
			}
			runThroughList(letters);
		}
	}
	
	
	public void runThroughList(char[] letters)
	{
		ArrayList<String> words = new ArrayList<String>();// arraylist of words that match w/ the player's letters
		char[] lockedLetters = new char[letters.length];
		char[] jumbledLetters = new char[letters.length];
		int numOfLockedLetters = 0;
		for(int x = 0; x < letters.length; x++)
		{
			char c = letters[x];
			
			if(c >= 'A' && c <= 'Z')// if letter is uppercase
			{
				lockedLetters[x] = toLowerCase(c);
				jumbledLetters[x] = '_';// _ is nothing-just used to keep track of the letters' order
				numOfLockedLetters++;
			}
			else
			{
				lockedLetters[x] = '_';
				jumbledLetters[x] = c;
			}
		}
		if(numOfLockedLetters == letters.length)// if all capital letters->make them all lowercase
		// this is a user mistake because if user types in all caps, then no letters can be moved around
		// user needs at least one lowercase letter to make the others locked-therefore user must've meant
		// to check all the letters for all the words in any order, whether they are capitalized or not.
		{
			for(int x = 0; x < letters.length; x++)
			{
				letters[x] = toLowerCase(letters[x]);
				jumbledLetters[x] = toLowerCase(lockedLetters[x]);
				lockedLetters[x] = '_';
			}
			numOfLockedLetters = 0;
		}
		out.println("locked letters:" + numOfLockedLetters);
		for(int x = 0; x < letters.length; x++)
		{
			out.print(lockedLetters[x] + " ");
		}
		out.println("\njumbled letters:");
		for(int x = 0; x < letters.length; x++)
		{
			out.print(jumbledLetters[x] + " ");
		}
		out.print("\n");
		if(numOfLockedLetters == 0)// if there are no locked letters (all capital or all lowercase letters)
		// check dictionary for any words matching letters in any order
		{
			out.println("allJumbled!");
			for(int x = 0; x < dictionary.size(); x++)
			{
				if(dictionary.get(x).length() <= letters.length)// if the length of the word is smaller or equal to
				// the # of letters the player has, check if the word matches
				{
					if(analyzeAllJumbled(jumbledLetters, dictionary.get(x)))
					{
						words.add(dictionary.get(x));
					}
				}
			}
			toString(words);
		}
		
		if(numOfLockedLetters > 0)// if there are some (but not all) uppercase letters, then their locations
		// are locked, check dictionary for words with those locked letters and some order of the jumbled
		// letters
		{
			for(int x = 0; x < dictionary.size(); x++)
			{
				String w = dictionary.get(x);
				if(w.length() <= letters.length)// if the length of the word is smaller or equal to
				// the # of letters the player has, check if the word matches
				{
					if(analyze(lockedLetters, jumbledLetters, w))
					{
						words.add(w);
					}
				}
			}
			toString(words);
		}
		
		
	}
	
	
	public boolean analyzeAllJumbled(char[] letters, String word)
	// analyzes a word and given letters (all lowercase-all jumbled) to see if they match
	{
		char[] tempWord = new char[word.length()];
		int blanks = 0;
		for(int x = 0; x < letters.length; x++)
		{
			if(letters[x] == '?')
			{
				blanks++;
			}
		}
		for(int x = 0; x < word.length(); x++)
		{
			tempWord[x] = word.charAt(x);
		}
		for(int x = 0; x < tempWord.length; x++)
		{
			int numOfCharL = containsNumOfChar(letters, tempWord[x]);
			// checks the user's tiles to see how many letters of a specific char in the word the player has
			int numOfCharW = containsNumOfChar(tempWord, tempWord[x]);
			// checks the word for repeated letters (ex: "potato" has 2 o's and 2 t's)
			char w = tempWord[x];
			if(blanks == 0)
			{
				if(numOfCharL < numOfCharW)// user doesnt have the letters/blanks needed to use this word
				{
					return false;
				}
			}
			if(blanks > 0)
			{
				if((numOfCharL + blanks) < numOfCharW)
				{
					return false;
				}
				if((numOfCharL < numOfCharW) && (numOfCharL + blanks) >= numOfCharW)
				{
					tempWord[x] = '?';// changes the word by replacing a needed letter that the user doesnt have
					// with a '?' in order to match the blanks & re-evaluates the new altered word
					blanks--;
					x = -1;
				}
			}
		}
		return true;// if it has finished analyzing the whole word without fail-then the word is playable
	}
	
	
	public boolean analyze(char[] locked, char[] jumbled, String w)
	// analyzes a word & given letters in 2 parts (upper case=locked, lower case=jumbled) to see if they match
	{
		char[] word = new char[w.length()];
		int blanks = 0;
		for(int x = 0; x < w.length(); x++)
		{
			word[x] = w.charAt(x);
			if(jumbled[x] == '?')
			{
				blanks++;
			}
		}
		for(int x = 0; x < w.length(); x++)
		{
			if(locked[x] != '_')
			{
				if(locked[x] != word[x])
				{
					return false;
				}
			}
			if(locked[x] == '_')
			{
				// Something needs to be put here for jumbled letters!!!
				int numOfCharL = containsNumOfChar(jumbled, word[x]);
				int numOfCharW = containsNumOfChar(word, word[x]);
				
				if(blanks == 0)
				{
					if(numOfCharL < numOfCharW)// user doesnt have the letters/blanks needed to use this word
					{
						return false;
					}
				}
				if(blanks > 0)
				{
					if((numOfCharL + blanks) < numOfCharW)
					{
						return false;
					}
					if((numOfCharL < numOfCharW) && (numOfCharL + blanks) >= numOfCharW)
					{
						word[x] = '?';
						blanks--;
						x = -1;
					}
				}
			}
		}
		return true;
	}
	
	
	public int containsNumOfChar(char[] word, char letter)// checks a word (in char[] format)
	// for a specific letter, and counts how many times that letter appears in the word
	{
		int numOfChars = 0;
		for(int x = 0; x < word.length; x++)
		{
			if(word[x] == letter)
			{
				numOfChars++;
			}
		}
		return numOfChars;
	}
	
	
	public int get_word_value(String word)
	{
		for(int i = 0; i < word.length(); i++)
		{	
			
		}
		
		return 0;
	}
	
	
	public void toString(ArrayList<String> words)
	{
		out.println("\n\nWords that are playable:\n");
		for(int length = 2; length < 10; length++)
		{
			for(int x = 0; x < words.size(); x++)
			{
				if(words.get(x).length() == length)
				{
					out.println("" + words.get(x));
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException
	{
		ScrabbleWordFinder start = new ScrabbleWordFinder();
	}
}
