/*
 * Class Name:    WordMatch
 *
 * Author:        Your Name
 * Creation Date: Wednesday, March 25 2020, 15:38 
 * Last Modified: Sunday, May 24 2020, 10:53
 * 
 * Class Description:
 *
 */
import java.io.*;

import java.util.Scanner;
public class WordMatch
{  
   //private Scanner kb; //kb to take input from keyboard
   private Scanner s1; //file to take input from text file
   private Scanner s2; //file to take input from text file
   private PrintWriter pw1; //so file can be saved or overwritten
   private PrintWriter pw2; //so file can be saved or overwritten

   private static String fInName1 = ""; 
   private static String fInName2 = ""; 
   private static String fOutName1 = "";
   private static String fOutName2 = "";
   
   private Task1 t1;

   public static void main(String[] args) throws Exception
   {
	   try
	   {
			fInName1 = args[0];
	       fOutName1 = args[1];
			fInName2 = args[2];
		   fOutName2 = args[3];
		   
		   WordMatch wm = new WordMatch(); 
       	 		     wm.load();
				     wm.run();
	   }
	   catch(Exception e)
	   {
		   System.out.println("Error   - something went wrong with the program\n" +
							  "          please put valid textfiles and filenames in the command line\n" +
							  "Example - time java WordMatch in1.txt out1.txt in2.txt out2.txt\n");
	   }
   }
   
   public WordMatch() throws IOException 
   {
       //kb = new Scanner(System.in);     
       s1 = new Scanner(new File(fInName1));
	   s2 = new Scanner(new File(fInName2));
       pw1 = new PrintWriter(new File(fOutName1));
       pw2 = new PrintWriter(new File(fOutName2));
   }

   public void load() throws IOException 
   {
       t1 = new Task1();
	   t1.readTextFiles(s1);
	   s1.close();
	   t1.readPatternTextFiles(s2);
	   s2.close();
   }

   public void run() throws IOException 
   {
     t1.writeFileLexicon(pw1);
	 pw1.close();
	 t1.writeFilePattern(pw2);
	 pw2.close();
   }
}