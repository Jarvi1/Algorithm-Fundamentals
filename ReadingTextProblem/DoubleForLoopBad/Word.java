/*
 * Class Name:    Word
 *
 * Author:        Your Name
 * Creation Date: Monday, March 30 2020, 17:46 
 * Last Modified: Saturday, April 04 2020, 16:45
 * 
 * Class Description:
 *
 */
import java.io.*;
import java.util.*;

public class Word implements Comparable<Word> //need to compare for helper
{
	private String strWord;
	private int freq;
	private ArrayList<String> neighbors;
	
	public Word(String str) //constructor, note defaults
	{
		this.strWord = str;
		freq = 1;
		neighbors = new ArrayList<String>();
	}
	
	//basic gets and sets and adds bellow
	public String getWord(){return strWord;}
	
	public int getFreq(){return freq;}
	
	public void addFreq(){freq++;}

	public ArrayList<String> getNeighbors(){return neighbors;}
   
	public void addNeighbor(String str){neighbors.add(str);}

   //prints to file in given format, last neighbor doesnt print with commar 
   public void writeFileTask1(PrintWriter pw) throws IOException
   {
      pw.print(strWord + " " + freq + " ["); 
      int size = neighbors.size();
      for(int i = 0; i < size; ++i)
      {
         if(i == (size - 1))
         {
            pw.print(neighbors.get(i));
         }
         else
         {
            pw.print(neighbors.get(i) + ", ");
         }
      }
      pw.print("]\n");
   }

   //prints file for task2, easier to have to serperate methods, no [] 
   public void writeFileTask2(PrintWriter pw) throws IOException
   {
      pw.println(strWord + ' ' + freq);
   }

   public String toString()
   {
      return strWord + ' ' + freq;
   }

	//allows for helper to compare word objects, in this case the strings
   public int compareTo(Word w)
   {
      return this.getWord().compareTo(w.getWord());
   }

}

