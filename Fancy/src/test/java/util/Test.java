/**
 * Project Desc:
 * Project Name:TestExample
 * File Name:CompareNumber.java
 * Package Name:util
 * Date:2015-5-11上午9:57:02
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
*/
package util;

/**
 * ClassName:CompareNumber <br/>
 * Date:     2015-5-11 上午9:57:02 <br/>
 * @author   caosheng
 */
public class Test
{

	static long startTime;

	static long endTime;
	
	public static void main(String[] args)
	{
		startTime = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 100; j++) {
				for (int k = 0; k < 10; k++) {
					testFunction(i, j, k);
				}
			}
		}
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}

	/**
	 * @param	
	 * @return	
	 * @author caosheng
	 * @date 2015-5-11
	 */
	private static void testFunction(int i, int j, int k)
	{
		System.out.println("i====" + i + "j====" + j + "k:" + k);
	}
}
