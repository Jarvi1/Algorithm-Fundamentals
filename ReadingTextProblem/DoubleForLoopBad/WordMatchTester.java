/*
 * Class Name:    WordMatchTester
 *
 * Author:        Your Name
 * Creation Date: Wednesday, March 25 2020, 15:38 
 * Last Modified: Wednesday, April 08 2020, 19:18
 * 
 * Class Description:
 *
 */
import java.io.*;
import java.util.*;
public class WordMatchTester
{
	//private Scanner kb; //kb to take input from keyboard
	private Scanner f1; //file to take input from text file
	private Scanner f2; //file to take input from text file
	private PrintWriter pw; //so file can be saved or overwritten

   private String fInName1 = "sample1.txt"; 
   private String fInName2 = "sample2.txt"; 
   private String fOutName = "sample4.txt";
   private static String pattern = "";
   private Task2 t2;

   public static void main(String[] args)
   {
	   try //simple check if no args given
	   {
		   pattern = args[0];
		   WordMatchTester wmt = new WordMatchTester(); 
		   wmt.load();
		   wmt.run();
	   }
	   catch(Exception e)
	   {
		   System.out.println("Error   - Something went wrong with the program\n" +
							  "          maybe put a pattern in the command line\n" +
							  "Example - java WordMatchTester '?'a'*'\n");
	   }
   }
   
   public WordMatchTester() throws Exception 
   {
      //kb = new Scanner(System.in);     
      f1 = new Scanner(new File(fInName1));
      f2 = new Scanner(new File(fInName2));
      pw = new PrintWriter(new File(fOutName));
   }

   public void load() throws Exception 
   {
      t2 = new Task2();
      t2.readFile(f1);
      f1.close();
      t2.readFile(f2);
      f2.close();
   }

   public void run() throws Exception 
   {
      //sorts word list, efficient at sorting list
	  Helper.earlyStopBubbleSort(t2.getWords()); //runs 
	  
	  //pre test with many values to see any demostration
	  //test();
	  
      //finds pattern, displays pattern, printwrites pattern to file
      t2.display_printPatternWords(pattern, pw); 
      pw.close();
	  System.out.println("Output saved to " + fOutName + '\n');
   }
   
   public void test() throws Exception //runs many inputs
   {
	  t2.display_printPatternWords("i*", pw); 		//01
	  t2.display_printPatternWords("i?", pw); 		//02
	  t2.display_printPatternWords("?i*", pw);		//03
	  t2.display_printPatternWords("*i*", pw); 		//04
	  t2.display_printPatternWords("*i?", pw); 		//05
	  t2.display_printPatternWords("?i?", pw); 		//06
	  t2.display_printPatternWords("?a?", pw); 		//07
	  t2.display_printPatternWords("?a*s?", pw);	//08
	  t2.display_printPatternWords("*oo*", pw);  	//09
	  t2.display_printPatternWords("", pw);      	//10
   }
}

