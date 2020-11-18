import java.util.*;
import java.util.regex.*;  

public class Helper
{	
	//Assginment 2 methods bellow
	
	//Primes
	public static int getPrime(int n)
    {
		while (!isPrime(n)) { n++; }
		return n;
	}
	
	public static boolean isPrime(int num)
	{	
		for(int n = 2; n*n <= num; n++)
		{
			if(num % n == 0) { return false; }
		}
		return true;
	}
	
	//generates potential neighbors based off str
	public static ArrayList<String> getPotenchNeighbors(String string)
	{
		char[] str = string.toCharArray();
		int strLength = str.length;
		
		char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		int alphabetLength = alphabet.length;
		
		ArrayList<String> potenchNeighbors = new ArrayList<String>();
		for(int i = 0; i < strLength; i++)
		{
			char chStr = str[i];
			for(int j = 0; j < alphabetLength; j++)
			{
				char chStrAlpha = alphabet[j];
				if(chStr != chStrAlpha)
				{
					str[i] = chStrAlpha;
					potenchNeighbors.add(new String(str));
					str[i] = chStr;
				}
			}
		}
		return potenchNeighbors;
	}
	
	public static ArrayList<Word> getPattens(String pattern, List<Word> words)
	{
		ArrayList<Word> patterns = new ArrayList<Word>();
		for(Word w: words)
		{
			if(isPattern(pattern, w.getWord()))
			{
				patterns.add(w);
			}
		}
		return patterns;
	}
	
	public static ArrayList<String> getPotenchPatterns(String pattern, int longestStr)
	{
		char[] str = pattern.toCharArray();
		int strLength = str.length;
		
		char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		int alphabetLength = alphabet.length;
		
		ArrayList<String> potenchPatterns = new ArrayList<String>();
		for(int i = 0; i < strLength; i++)
		{
			char chStr = str[i];
			if(chStr == '?')
			{
				for(int j = 0; j < alphabetLength; j++)
				{ 
					str[i] = alphabet[j];
					potenchPatterns.add(new String(str));
				}
			}
			else if(chStr == '*')
			{
				//
			}
			else
			{
			}
		}
		
		return null;
	}

   //merge sort
   public static<E extends Comparable<E>> void mergeSort(List<E> list, int first, int last)
   {
      if(first == last)
      {
         return;
      }

      int mid = (first + last)/2;
      mergeSort(list, first, mid);
      mergeSort(list, (mid+1), last);

      merge(list, first, mid, last);
   }
	
   //more merge sort
   public static<E extends Comparable<E>> void merge(List<E> list, int left, int mid, int right)
   {
      List<E> temp = new ArrayList<E>(right - left + 1);

      int indexLeft = left;
      int indexRight = mid + 1;
      int indexTemp = 0;
      
      //merge elements until one half sorted
      while(indexLeft <= mid && indexRight <= right)
      {
         if(list.get(indexLeft).compareTo(list.get(indexRight)) < 0) //need to check compare to logic
         {
            temp.add(indexTemp, list.get(indexLeft));
            ++indexLeft;
            ++indexTemp;
         }
         else
         {
            temp.add(indexTemp, list.get(indexRight));
            ++indexRight;
            ++indexTemp;
         }
      }

      while(indexLeft <= mid)
      {
         temp.add(indexTemp, list.get(indexLeft));
         ++indexLeft;
         ++indexTemp;
      }
      while(indexRight <= right)
      {
         temp.add(indexTemp, list.get(indexRight));
         ++indexRight;
         ++indexTemp;
      }

      for(int i = left; i <= right; i++)
      {
         list.set(i, temp.get(i - left));
      }
   }







































   //Assignment 1 methods bellow

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
		else
		{
			return false;
		}
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
}
