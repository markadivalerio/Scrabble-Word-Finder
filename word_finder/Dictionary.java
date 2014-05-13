package word_finder;

import java.awt.*;
import java.io.*;
import java.lang.*;
import java.net.*;
import java.nio.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
import static java.lang.System.*;


public class Dictionary
{
	// public HashMap<File, Boolean, ArrayList<String>> dictionaries = new HashMap<File, Boolean, ArrayList<String>>();
	
	protected static ArrayList<File>	dictFiles	= new ArrayList<File>();
	protected static ArrayList<String>	words		= new ArrayList<String>();
	
	
	
	public Dictionary()
	{
		dictFiles = findAllDictionaryFiles();
		words = new ArrayList<String>();
	}
	
	
	public ArrayList<File> findAllDictionaryFiles()
	{
		return searchAllFiles("dictionary", new String[] {".txt", ".dat"});
	}
	
	
	public ArrayList<File> searchAllFiles(String nameContains, String[] fileTypes)
	{
		ArrayList<File> allFiles = new ArrayList<File>();
		try
		{
			File currentDir = new File(System.getProperty("user.dir"));
			File parentDir = currentDir.getParentFile();
			if(parentDir == null)
			{
				return allFiles;// returns an empty arraylist
			}
			else
			{
				LinkedList<File> directories = new LinkedList<File>();
				directories.add(parentDir);
				while(directories.isEmpty() == false)
				{
					for(File f: directories.poll().listFiles())
					{
						if(f.isDirectory() == true)
						{
							directories.add(f);
						}
						else if(f.isFile() == true)
						{
							if(isFileAMatch(f, nameContains, fileTypes) == true)
							{
								allFiles.add(f);
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			exit(1);
		}
		return allFiles;
	}
	
	
	public boolean isFileAMatch(File file, String nameContains, String[] fileTypes)
	// NOTE: file name matching ignores cases & considers uppercase/lowercase characters the same
	{
		boolean result = false;
		boolean hasNameConstraint = true;
		boolean hasTypeConstraint = true;
		
		if((file == null) || (file.isFile() == false))
		{
			result = false;// no file to match or it is a directory
		}
		else
		{
			if((nameContains == null) || (nameContains.equals("") == true))
			{
				hasNameConstraint = false;
			}
			if((fileTypes == null) || (fileTypes.length == 0))
			{
				hasTypeConstraint = false;
			}
			
			if(hasTypeConstraint == true)
			{
				for(String type: fileTypes)
				{
					if(file.getName().endsWith(type) == true)
					{
						if(hasNameConstraint == true)
						{
							result = (file.getName().toLowerCase().contains(nameContains.toLowerCase()));
							break;
						}
						else
						{
							result = true;
							break;
						}
					}
				}
			}
			else if(hasTypeConstraint == false)
			{
				if(hasNameConstraint == true)
				{
					result = (file.getName().toLowerCase().contains(nameContains.toLowerCase()));
				}
				else
				{
					result = true;// file matching has no constraints...returns true for all files
				}
			}
		}
		return result;
	}
	
	
	public ArrayList<String> loadWordsFromDict(File dictionary)
	{
		ArrayList<String> w = new ArrayList<String>();
		
		
		return w;
	}
	
	
	public static void main(String[] args) throws Exception
	{
		File oxfordFile = new File(getProperty("user.dir") + "/Source Files/Oxford English Dictionary.txt");
		final LinkedList<String> lines = new LinkedList<String>();
		final Scanner reader = new Scanner(oxfordFile, "UTF-8");
		while(reader.hasNextLine())
		{
			final String line = reader.nextLine();
			if((line.isEmpty() == true) || (line.equalsIgnoreCase("\n") == true))
			{
				continue;
			}
			final StringBuilder builder = new StringBuilder();
			builder.append(line);
			lines.add(builder.toString());
		}
		reader.close();
		final BufferedWriter writer = new BufferedWriter(new FileWriter(oxfordFile, false));
		for(final String line: lines)
		{
			writer.write(line);
			writer.newLine();
		}
		writer.flush();
		writer.close();
		
//		file.close();
//		fos.close();
//			File file = new File(getProperty("user.dir") + "/Source Files/Oxford English Dictionary.txt");
//			File parent = file.getParentFile();
//			
//			out.println(parent.isDirectory());
//			out.println("absolute: " + file.getAbsolutePath());
//			out.println("conical: " + file.getCanonicalPath());
//			out.println();
//			// System.get
//			// URL path = ClassLoader.getSystemResource("Oxford English Dictionary.txt");
//			// out.println(path.toString());
		
		
		
//		Scanner scanner = new Scanner(oxfordFile);
//		String previousLine = null;
//		while(scanner.hasNextLine() == true)
//		{
//			String line = scanner.nextLine();
//			if(previousLine != null)
//			{	
//				
//				
//				
//			}
//			previousLine = line;
//		}
	}
}
