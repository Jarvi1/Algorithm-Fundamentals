/*
 * Class Name:    LexiconTester
 *
 * Author:        Your Name
 * Creation Date: Wednesday, March 25 2020, 15:38 
 * Last Modified: Wednesday, April 08 2020, 19:06
 * 
 * Class Description:
 *
 */
import java.io.*;
import java.util.*;
public class LexiconTester
{
	//private Scanner kb; //kb to take input from keyboard
	private Scanner f1; //file to take input from text file
	private Scanner f2; //file to take input from text file
	private PrintWriter pw; //so file can be saved or overwritten

   private String fInName1 = "sample1.txt"; 
   private String fInName2 = "sample2.txt"; 
   private String fOutName = "sample3.txt";
   private Task1 t1;

   public static void main(String[] args) throws Exception
   {
      LexiconTester lt = new LexiconTester(); 
	  lt.load();
      lt.run();
   }
   
   public LexiconTester() throws Exception 
   {
      //kb = new Scanner(System.in);     
      f1 = new Scanner(new File(fInName1));
      f2 = new Scanner(new File(fInName2));
      pw = new PrintWriter(new File(fOutName));
   }

   public void load() throws Exception 
   {
      t1 = new Task1();
      t1.readFile(f1);
      f1.close();
      t1.readFile(f2);
      f2.close();
   }

   public void run() throws Exception 
   { 
      //sorts word list
	   Helper.earlyStopBubbleSort(t1.getWords());
	  
	   //addneighbors to words
	   t1.addNeighbors();
	  
      t1.writeFile(pw);
      pw.close();
	  System.out.println("Output saved to " + fOutName + '\n');
   }
}

