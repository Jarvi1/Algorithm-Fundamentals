/*
 * Class Name:    Task1
 *
 * Author:        Your Name
 * Creation Date: Monday, March 30 2020, 18:14 
 * Last Modified: Wednesday, May 27 2020, 18:30
 * 
 * Class Description:
 *
 */
import java.io.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class Task1
{
	private Scanner sLocal;	            //for scanning multiple files
	private DbHashTable<Word> dhtWords; //double hash table for fast insertions
	
	private ArrayList<Word> words;      //needs to be different structure
	private ArrayList<Word> patterns;   //all matching words to pattern
	private String pattern = "";        //defined pattern from text file
	
	public Task1()
	{		
		dhtWords = new DbHashTable<Word>();
		   words = new ArrayList<Word>();    
		patterns = new ArrayList<Word>();
	}
	
   //reads many text files passed to next method
	public void readTextFiles(Scanner s) throws FileNotFoundException
	{
		while(s.hasNext())
		{
			String fInName = s.nextLine();
			sLocal = new Scanner(new File(fInName));
            readTextFile(sLocal);
			sLocal.close();
		}
	}
   
   public void readTextFile(Scanner s)
   {
      while(s.hasNext())
      {
         String strLine = s.nextLine();
         String[] strWords = strLine.split("\\s+"); //makes individual strings

         for(String str : strWords)
         {
            if(!hasDigit(str))
            {
               str = str.replaceAll("[^a-zA-Z]","").toLowerCase(); 
               if(str.length() != 0)
               {
                  Word w = dhtWords.search(str);
                  if(w == null) //for each string cehcks unique
                  {
                     Word word = new Word(str);
                     dhtWords.insert(word);
                  }
                  else //not unqiue, adds to freq
                  {
                     w.addFreq();
                  }
               }
            }
         }
      }
   }

   public final boolean hasDigit(String str) 
   {
      boolean hasDigit = false;
      if (str != null && !str.isEmpty()) 
      {
         for(char ch : str.toCharArray()) 
         {
            if(hasDigit = Character.isDigit(ch)) 
            {
               break;
            }
         }
      }
      return hasDigit;
   }

   public void readPatternTextFiles(Scanner s)
   {
      if(s.hasNext())
      {
         pattern = s.nextLine();
      }
   }

   public void writeFileLexicon(PrintWriter pw) throws IOException
   {
      words = dhtWords.toList(); //can be improved
      int size = words.size();
      if(size > 1) { Helper.mergeSort(words, 0, size - 1); } //checks to see if list is large enough for merge sort
      addNeighbors(pw);
   }

   //for every word, gets valid neighbors, sorts neighbors, prints to lexicion
   public void addNeighbors(PrintWriter pw) throws IOException
   {
      for(Word w: words)
      {
         //gets valid neighbors
         ArrayList<String> potenchNeighbors = Helper.getPotenchNeighbors(w.getWord());
         for(String key: potenchNeighbors)
         {
            Word neighbor = dhtWords.search(key);
            if(neighbor != null) { w.addNeighbor(key); } 
         }

         //sorts neighbors
         ArrayList<String> neighbors = w.getNeighbors();
         int size = neighbors.size();
         if(size > 1) { Helper.mergeSort(neighbors, 0, size - 1); }

         //prints to lexicion
         w.writeFileLexicon(pw);
      }
   }

   public void writeFilePattern(PrintWriter pw) throws IOException
   {
      patterns = Helper.getPattens(pattern, words); //gets list of all word matching pattern
      if(patterns.isEmpty())
      {
         pw.println("No words in the lexicon match the pattern");
      }
      else
      {
         for(Word w: patterns)
         {
            w.writeFilePattern(pw);
         }
      }
   }
}

