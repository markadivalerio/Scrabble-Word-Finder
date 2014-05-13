package word_finder;

import java.util.*;

public class Word
{
	// allowed in scrabble
	public static final String					TYPE_NOUN			= "n";
	public static final String					TYPE_VERB			= "v";
	public static final String					TYPE_ADVERB			= "adv";
	public static final String					TYPE_ADJECTIVE		= "adj";
	public static final String					TYPE_INTERJECTION	= "int";
	public static final String					TYPE_CONJUGATION	= "conj";
	public static final String					TYPE_PREPOSITION	= "prep";
	public static final String					TYPE_CONTRACTION	= "contr";
	public static final String					TYPE_PRONOUN		= "pron";
	
	// not allowed in scrabble
	public static final String					TYPE_PREFIX			= "prefix";
	public static final String					TYPE_SUFFIX			= "suffix";
	public static final String					TYPE_ABBREVIATION	= "abbr";
	public static final String					TYPE_SYMBOL			= "symb";
	
	private static final ArrayList<String>		TYPES				= new ArrayList<String>(Arrays.asList(TYPE_NOUN,
																		TYPE_VERB, TYPE_ADVERB, TYPE_ADJECTIVE,
																		TYPE_INTERJECTION, TYPE_CONJUGATION,
																		TYPE_PREPOSITION, TYPE_CONTRACTION,
																		TYPE_PRONOUN, TYPE_PREFIX, TYPE_SUFFIX,
																		TYPE_ABBREVIATION, TYPE_SYMBOL));
	
	// more descriptions of the word - doesn't matter for now
	public static final String					PLURAL				= "pl";
	public static final String					COLLOQUIALISM		= "colloq";
	public static final String					POSSESSIVE			= "poss";
	public static final String					ARCHAIC				= "archaic";
	public static final String					ATTRIBUTE			= "attrib";
	public static final String					DEROGATORY			= "derog";
	public static final String					SLANG				= "slang";
	public static final String					MUSICAL				= "mus";
	public static final String					NAUTICAL			= "naut";
	
	
	
	// special
	public static final String					VARIATION_OF		= "var. Of";
	public static final String					EQUALS				= "=";
	public static final String					LINK				= "*";
	public static final String					AND					= "&";
	public static final String					USAGE				= "usage";
	/*
	 * NOTE: Oxford .txt file also uses "usu." for usually and "derog." for deragatory
	 */
	
	public String								word;
	public ArrayList<String>					variations			= new ArrayList<String>();
	
	private HashMap<String, ArrayList<String>>	definitions			= new HashMap<String, ArrayList<String>>();
	
	
	// key's up every definition to the word (and to it's type)
	
	public Word()
	{	
		
	}
	
	
	public void parseWordFromDictionary(String lines)
	{	
		
	}
}
