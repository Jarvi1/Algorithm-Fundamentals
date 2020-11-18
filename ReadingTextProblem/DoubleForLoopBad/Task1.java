/*
 * Class Name:    Task1
 *
 * Author:        Your Name
 * Creation Date: Monday, March 30 2020, 18:14 
 * Last Modified: Saturday, April 04 2020, 18:01
 * 
 * Class Description:
 *
 */
import java.io.*;
import java.util.*;

public class Task1
{
    private List<Word> words;
    private int wordsSize; //easier then calling again
	
	public Task1()
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

	//basic search for word
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

	//goes through two copies of list, doesn't compare to self
   public void addNeighbors() //logic all g now 
   {
      wordsSize = words.size();
      for(int i = 0; i < wordsSize; ++i)
      {
         for(int j = 0; j < wordsSize; ++j)
         {
            if(i != j) //here stops from looking at self
            {
               String wi = words.get(i).getWord(); //more efficent to get strings
               String wj = words.get(j).getWord();
               if(Helper.isNeighbor(wj, wi)) //uses helper
               {
                  words.get(j).addNeighbor(wi);
               }
            }
         }
      }
   }

	//prints out for everyword using task1 printwriter
   public void writeFile(PrintWriter pw) throws IOException
   {
      for(Word w : words)
      {
         w.writeFileTask1(pw);
      }
   }

	//basic get words
   public List<Word> getWords()
   {
      return words;
   }
   
   //old code not in use
   //neighors atm arent corresponding ie a[i] i[] ?? ... old method
   /*
   public void addNeighbor(String str)
   {
      wordsSize = words.size();
      for(int i = 0; i < wordsSize; ++i)
      {
         Word w = words.get(i);
         if(Helper.isNeighbor(w.getWord(), str))
         {
            w.addNeighbor(str);
         }
      }
   }
   */
}

