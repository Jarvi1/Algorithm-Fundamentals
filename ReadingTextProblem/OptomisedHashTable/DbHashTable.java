/*
 * Class Name:    DbHashTable
 *
 * Author:        Your Name
 * Creation Date: Wednesday, May 13 2020, 17:51 
 * Last Modified: Thursday, May 28 2020, 17:32
 * 
 * Class Description:
 *
 */
import java.util.ArrayList;
import java.util.LinkedList;

//has a main hash table that points to many buckets of hashtables ie double hash table
public class DbHashTable<V extends Hashable<V>>
{
	private int size = 251;
	private int count = 0;
	private int longestStr = 1;
	private final double loadFactorBound = 0.5;
	
	private ArrayList<Bucket> buckets; 

	public DbHashTable() 
	{
      buckets = new ArrayList<Bucket>(size);
      for(int i = 0; i < size; i++)
      {
         buckets.add(new Bucket());
      }
	}
   
   public void insert(V val)
   {
	 if(val.getKey().length() > longestStr) { longestStr = val.getKey().length(); }
     int loc = hashKey(val.getKey()); //where hashkey of val (value) equals the index loc (location)
     Bucket b = buckets.get(loc);
     
     b.insert(val);
     count++;
   }
   
   public V search(String key)
   {
	  int loc = hashKey(key); //where hashkey of val (value) equals the index loc (location)
      Bucket b = buckets.get(loc);
	  
	  return (V) b.search(key);
   }

   public ArrayList<V> toList()
   {
      ArrayList<V> list = new ArrayList<V>();
      for(int i = 0; i < size; i++)
      {
         Bucket b = buckets.get(i); int bSize = b.getSize(); ArrayList<V> vals = b.getList();
         for(int j = 0; j < bSize; j++)
         {
            if(vals.get(j) != null)
            {
               list.add(vals.get(j));
            }
         }
      }
      return list;
   }
   
   private void rehash()
   {
      //may need to introduce gets rather then defining variables
	   ArrayList<Bucket> oldBuckets = buckets;
	   int newSize = Helper.getPrime(size * 2);
	   buckets = new ArrayList<Bucket>(newSize);
	   count = 0;

      for(int i = 0; i < size; i++)
      {
         Bucket oldBucket = oldBuckets.get(i);
         int oldBucketSize = oldBucket.getSize();
         if(oldBucket.getCount() > 0)
         {
            for(int j = 0; j < oldBucketSize; j++)
            {
               V val = (V) oldBucket.getList().get(j);
               if(val != null)
               {
                  insert(val);
               }
            }
         }
      }
      size = newSize;
   }

   //uses prime numbers to generate different unique keys 
   public int hashKey(String key)
   {
      int h = 11;
      int strLength = key.length();

      for(int i = 0; i < strLength; i++)
      {
         h = h * 31 + key.charAt(i);
      }

      h = Math.abs(h) % size;
      return h;
   }

   public double getLoadFactor()
   {
      double total = 0.0;
      for(Bucket b: buckets) //may be lag for loop going through every bucket
      {
         total = total + b.getLoadFactor();
      }
      return total / size;
   }
   
   public int getLongestStr() { return longestStr; }
}

