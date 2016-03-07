/**
 * 
 * Project Name:TestExample
 * File Name:TestFor.java
 * Package Name:util
 * Date:2015-5-27上午9:56:34
 *
*/
package util;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:TestFor <br/>
 * @author   caosheng
 */
public class TestFor
{

	/**
	 * @param	
	 * @return	
	 * @author caosheng
	 * @date 2015-5-27
	 */
	public static void main(String[] args)
	{
		List<Object> li = new ArrayList<Object>();
		System.out.println(li.size());
		for (int i = 0; i < li.size(); i++) {
			System.out.println(li);
		}
		Runtime runtime = Runtime.getRuntime();
		long freeMemoery = runtime.freeMemory();
		long totalMemory = runtime.totalMemory();
		long usedMemory = totalMemory - freeMemoery;
		long maxMemory = runtime.maxMemory();
		long useableMemory = maxMemory - totalMemory + freeMemoery;
		System.out.println(freeMemoery/1024/1024);
		System.out.println(maxMemory/1024/1024);
	}
}
