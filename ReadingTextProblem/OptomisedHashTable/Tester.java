/*
 * Class Name:    Tester
 *
 * Author:        Your Name
 * Creation Date: Thursday, May 14 2020, 11:24 
 * Last Modified: Thursday, May 28 2020, 17:41
 * 
 * Class Description:
 *
 */

import java.util.*;

public class Tester
{
	public static void main(String[] args) throws Exception
	{
		ArrayList<String> patterns = Helper.getPotenchPatterns("?a?", 0);
		System.out.println(patterns);
	}
}

