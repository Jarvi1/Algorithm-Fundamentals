import java.util.*;
import java.util.regex.*;  

public class Helper
{	
	//method goes through and converts string into regex so pattern.matches will work
	public static boolean isPattern(String pattern, String strWord)
	{
		String newPattern = ""; //will be adjusted pattern for regex
		int pl = pattern.length();
		for(int i = 0; i < pl; i++) //goes through every letter of string
		{
			char ch = pattern.charAt(i);
			if(ch == '*')
			{
				newPattern = newPattern + "[a-z]*";
			}
			else if(ch == '?')
			{
				newPattern = newPattern + "[a-z]";
			}
			else
			{
				newPattern = newPattern + ch;
			}
		}
		return Pattern.matches(newPattern, strWord); //uses mathces with newPattern
	}

	//method to test whether or not a string is the same as another
	public static boolean isNeighbor(String s1, String s2)
	{
		int s1Length = s1.length(); //more efficent than calling again
		int diffs = 0;
		if(s1Length == s2.length())
		{
			for(int i = 0; i < s1Length; ++i)
			{
				if(s1.charAt(i) != s2.charAt(i))
				{
					diffs++; //totals how many letter differnet
				}
				if(diffs > 1)
				{
					return false;
				}
			}
			//returns true since word is same length, and is same or one letter different
			return true;
		}
		return false;
	}

	// Generic method to perform gnome sort
	public static <E extends Comparable<E>>
	void gnomeSort(List<E> list)
	{
		int first = 0;
		int last = list.size()-1;

		int pos = first + 1;
		while (true)
		{
			if (pos > last)
	 		{
				return;					// done
	   	}
	   	else if (pos == first)
	  		{
	   		pos = first + 1;		// move right
			}
	  		else if (list.get(pos).compareTo(list.get(pos-1)) >= 0)
	    	{
				pos++; 					// move right

			}
			else // list.get(pos).compareTo(list.get(pos-1)) < 0
			{
				swap(list, pos, pos-1);
				pos--;					// swap and move left
			}
	   }
	}

	// Swap elments at positions i and j
	private static<E extends Comparable<E>>
	void swap(List<E> list, int i, int j)
	{
		E temp = list.get(i);
		list.set(i, list.get(j));
	 	list.set(j, temp);
	}

	// Generic method to perform bubble sort
	public static<E extends Comparable<E>> void bubbleSort(List<E> list)
	{
		int left = 0;
		int right = list.size()-1;
		for(int i = right; i >= left + 1; i--)
		{
			for(int j = left; j <= i-1; j++)
			{
				if(list.get(j).compareTo(list.get(j+1)) > 0)
				{
					swap(list, j, j+1);
				}
			}
		}
	}

	// Generic method to perform selection sort
	public static<E extends Comparable<E>> void selectionSort(List<E> list)
	{
		int left = 0;
		int right = list.size()-1;
		for(int i = right; i >= left + 1; i--)
		{
			int madIndex = getMaxIndex(list);
			swap(list, i, madIndex);
		}
	}
	
	// Generic method to find max index
	public static<E extends Comparable<E>> int getMaxIndex(List<E> list)
	{
		int low = 0;
		int high = list.size()-1;
		int maxIndex = low;
		for(int i = low + 1; i <= high; i++)
		{
			if(list.get(i).compareTo(list.get(maxIndex)) > 0)
			{
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
   //Generic sort method for earlyStopBubbleSort,
	public static<E extends Comparable<E>> void earlyStopBubbleSort(List<E> list)
	{
		int left = 0;
		int right = list.size()-1;
		boolean isOrdered = true;
		for(int i = right; i >= left + 1; i--) //bubbles z words to the end
		{
			isOrdered = true;
			for(int j = left; j <= i-1; j++)
			{
				if(list.get(j).compareTo(list.get(j+1)) > 0) //uses compare to defined in words
				{
					swap(list, j, j+1); 
					isOrdered = false;
				}
			}
			if(isOrdered) {break;} //will stop early after one swap
		}
	}
	
   //Generic method for insertionSort
	public static<E extends Comparable<E>> void insertionSort(List<E> list)
	{
	  int length = list.size() -1;
      for(int i = 0; i < length; ++i)
      {
         insertElement(list, i);
      }
	}

   //Generic method for insertInOrderForAElement
   public static<E extends Comparable<E>> void insertElement(List<E> list, int next)
   {
      E v = list.get(next);
      int i = next;
      while(true)
      {
         if(i == 0)
         {
            list.set(0, v);
            return;
         }
         else if(list.get(i-1).compareTo(v) <= 0)
         {
           list.set(i, v);
		   return;
         }
         else
         {
            list.set(i, list.get(i - 1));
            i--;
         }
      }
   }
   
   //BELLOW IS METHODS FOR SHELL SORT MIGHT NOT BE RELEVANT TO ASSIGNMENT
   /*
   // Return Shell’s gap sequence of for collection of size n
   public static int[ ] getShellIntervals(int n)
   {
      //length of gap sequence = log2(n) = ln(n) / ln(2)
      int count = (int)Math.floor(Math.log(n)/Math.log(2));
      int[ ] gaps = new int[count];
      int h = n/2;
      for(int i = 0; i < gaps.length; i++)
      {
         gaps[i] = h;
         h = h/2;
      }
      return gaps;
   }

   // Return Knuth gap sequence of for collection of size n
   public static int[ ] getKnuthIntervals(int n)
   {
      {
         // determine start value of h and count number of elements
         // in the sequence
         int h = 1;
         int count = 1;
         while( 3 * h + 1 < n)
         {
            h = 3 * h + 1;
            count++;
         }
         // h is now the start value of the sequence
         // and count is the sequence's length
         int[] gaps = new int[count];
         for(int i = 0; i < gaps.length; i++)
         {
            gaps[i] = h;
            h = (h-1)/3;
         }
         return gaps;
      }
   }

   //Call insertElementPeople for a gapped subarray, starts at first next is
   //index of the eleent to be inserted
   private static<E extends Comparable<E>>  void insertElementPeople(List<Person> people, int h, int first, int next)
   {
      Person value = people.get(next);
      int i = next;
      while(true)
      {
         if(i == first)
         {
            people.set(first, value);
            break;
         }
         else if(people.get(i-h).compareTo(value) <= 0)
         {
            people.set(i, value);
            break;
         }
         else
         {
            people.set(i, people.get(i-h));
            i = i-h;
         }
      }
   }

   // Call insertElementPeople for each element to be inserted
   private static void sortGappedSubarrayPeople(List<Person> people, int h, int first)
   {
      int length = people.size() -1;
      for (int i = first + h; i < length; i = i + h )
      {
         insertElementPeople(people, h, first, i);
         // i is index of element to insert
         // i would be first + h, first + 2h,etc.
      }
   }

   //calls a hsort for people
   public static void hSortPeople(List<Person> people, int h)
   {
      for (int i = 0; i < h; i++ )
      {
         sortGappedSubarrayPeople(people, h, i);
         // subarray starts at i
         //  and i goes from 0 to h-1
      }
   }

   //calls a shell sort for people, gaps alreayd determined
   public static void shellSortPeople(List<Person> people, int[] gaps)
   {
      for (int i = 0; i < gaps.length; i++)
      {
         int h = gaps[ i ];
         hSortPeople(people, h);
      }
   }

   //calls a shell sort with shell gap for people
   public static void shellShellSortPeople(List<Person> people)
   {
      int[] gaps = getShellIntervals(people.size()-1);
      shellSortPeople(people, gaps);
   }

   //calls a shell sort with knuth gap for people
   public static void knuthShellSortPeople(List<Person> people)
   {
      int[] gaps = getKnuthIntervals(people.size()-1);
      shellSortPeople(people, gaps);
   }
   
   */
}

