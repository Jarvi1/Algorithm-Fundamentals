/*
 * Class Name:    Bucket
 *
 * Author:        Your Name
 * Creation Date: Monday, May 18 2020, 19:47 
 * Last Modified: Thursday, May 28 2020, 17:33
 * 
 * Class Description:
 *
 */
import java.util.ArrayList;
import java.util.LinkedList;

//Bucket is antoher hashtable hence double hashtable
public class Bucket<V extends Hashable<V>>
{
    private int size = 31;
    private int count;
	private int stepSize = 5;
    private final double loadFactorBound = 0.5;

    private ArrayList<V> list;

    public Bucket()
    {
	  list = new ArrayList<V>(size);
      fill(list, size); //populates list with empty's
    }

    public void fill(ArrayList<V> list, int length)
    {
       for(int i = 0; i < length; i++) { list.add(null); }
    }

    public void insert(V val) //previoos location
    {
       if(getLoadFactor() > loadFactorBound)
       {
          rehash();
       }
       int locBucket = hashKeyBucket(val.getKey());  
       while(true)
       {
		  int collision = 1;
          if(list.get(locBucket) == null) 
          {
             list.set(locBucket, val);
             count++;
             break;
          }
          else
          {
			 //goes next spot
             locBucket = (locBucket + (collision * stepSize)) % size; //double hashing !!!
			 collision++;
          }
       }
    }
	
	public V search(String key)
	{
	   
	   int locBucket = hashKeyBucket(key); 
	      
       while(true)
       {
		  int collision = 1;
          if(list.get(locBucket) == null)
          {
             return null;
          }
          else if(list.get(locBucket).getKey().compareTo(key) == 0)
          {
             return (V) list.get(locBucket);
          }
          else
          {
			 //goes next spot
             locBucket = (locBucket + (collision * stepSize)) % size; //double hashing !!!
			 collision++;
          }
       }
	}

    public void rehash()
    {
       ArrayList<V> oldBucket = list;
       int oldSize = size;
       size = Helper.getPrime(oldSize * 2); //new size

       list = new ArrayList<V>(size); //needs to be filled with empties!!
       fill(list, size);
       count = 0;
       
       for(int i = 0; i < oldSize; i++)
       {
          if(oldBucket.get(i) != null) //this sorter logic, ditch dummies, just use null 
          {
             this.insert(oldBucket.get(i));
          }
       }
    }
	
	public int hashKeyBucket(String key)
	{
		int h = 7;
		int strLength = key.length();

		for(int i = 0; i < strLength; i++)
		{
			h = h * 29 + key.charAt(i);
		}
		h = Math.abs(h) % size;
		return h;
	}

    public int hashKeyBucket(int key) 
    {
       LinkedList<Integer> stack = new LinkedList<Integer>();

       while (key > 0) {
          stack.push( key % 10 );
          key = key / 10;
       }

       int h = 5;
       while (!stack.isEmpty()) {
          h = h * 29 + stack.pop();
       }
       h = Math.abs(h) % size; 
       return h;
    }

    public double getLoadFactor()
    {
       return count / (double)size;
    }




    public V get(int i) { return list.get(i); }

    public int getSize() { return size; }

    public void setSize(int size) { this.size = size; }

    public int getCount() { return count; }

    public void setCount(int count) { this.count = count; }

    public ArrayList<V> getList() { return list; }

    public void setBucket(ArrayList<V> list) { this.list = list; }
}
