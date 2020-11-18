/*
 * Class Name:    Task2
 *
 * Author:        Your Name
 * Creation Date: Monday, March 30 2020, 18:14 
 * Last Modified: Saturday, April 04 2020, 17:02
 * 
 * Class Description:
 *
 */
import java.io.*;
import java.util.*;

public class Task2
{
	private List<Word> words;
	private int wordsSize; //easier to call in methods

	public Task2()
	{
		words = new ArrayList<Word>();
	}
   
	public void readFile(Scanner s) throws Exception
	{
      while(s.hasNext())
      {
         String strLine = s.nextLine();
         String[] strWords = strLine.split("\\s+"); //makes individual strings
         //String[] strWords = strLine.replaceAll("[^a-zA-Z]","").toLowerCase().split("\\s+");

         for(String str : strWords)
         {
            str = str.replaceAll("[^a-zA-Z]","").toLowerCase().trim(); //does create empty strings

            if(str.length() == 0) {} //stops empty strings
            else
            {
               int indexWord = getIndexWord(str);
               if(0 < indexWord) //for each string cehcks unique
               {
                  words.get(indexWord).addFreq();
               }
               else //ads unqiue string to list
               {
                  Word word = new Word(str);
                  words.add(word);
               }
            }
         }
      }
   }

	//basic search for a word
   public int getIndexWord(String str)
   {
      wordsSize = words.size();
      for(int i = 0; i < wordsSize; ++i)
      {
         if(words.get(i).getWord().equalsIgnoreCase(str))
         {
            return i;
         }
      }
      return -1;
   }
   
   //more efficient to do all in one hit, shows pattern, and saves to file
   public void display_printPatternWords(String pattern, PrintWriter pw) throws IOException
   {
	   boolean isFound = false;   
	   System.out.println(pattern); //shows to screen
	           pw.println(pattern); //writes to file 
	   for(Word w : words)
       {	
			if(Helper.isPattern(pattern, w.getWord())) //pattern matches word
			{
				isFound = true;
				System.out.println(w); //shows to screen
	                    pw.println(w); //writes to file 
			}
       }
	   if(!isFound)
	   {
		   System.out.println("No words in the lexicon match the pattern");
		           pw.println("No words in the lexicon match the pattern");
	   }
   }
   
   //used in testing of program
   public void writeFile(PrintWriter pw) throws IOException 
   {
      for(Word w: words)
      {
         w.writeFileTask1(pw);
      }
   }

	//basic get for task 2 list
   public List<Word> getWords()
   {
      return words;
   }
   
   //old code bellow no longer relevant
   /*
   public void addPatternWords(String pattern)
   {
      patternWords = new ArrayList();
      for(Word w : words)
      {
         if(Helper.isPattern(pattern, w.getWord()))
         {
            patternWords.add(w);
         }
      }
   }
   */

	/*
   public void printPatternWords()
   {
      if(0 == patternWords.size())
      {
         System.out.println("No words in the lexicon match the pattern");
      }
      else
      {
         for(Word w : patternWords)
         {
            System.out.println(w);
         }
      }
   }
   */
   
   /*
   public void printPatternWords(String pattern)
   {
		for(Word w : words)
		{
			if(Helper.isPattern(pattern, w.getWord()))
			{
				System.out.println(w);
			}
		}
   }
   */
	
	/*
   public void writeFile(PrintWriter pw, String pattern) throws IOException 
   {
      pw.println(pattern);
      if(0 == patternWords.size())
      {
         System.out.println("No words in the lexicon match the pattern");
      }
      else
      {
         for(Word w : patternWords)
         {
            w.writeFileTask2(pw);
         }
      }
   }
   */
}

