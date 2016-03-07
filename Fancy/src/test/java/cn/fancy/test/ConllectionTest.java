/**
 * 
 * Project Name:myweb-web
 * File Name:ConllectionTest.java
 * Package Name:cn.fancy.test
 * Date:2015-8-6
 *
*/

package cn.fancy.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * ClassName:ConllectionTest <br/>
 * @author   caosheng
 */
public class ConllectionTest {
	public static void main(String[] args)
	{
		List<Integer> l=new ArrayList<Integer>();
		l.add(1);
		l.add(4);
		l.add(3);
		Collections.sort(l,new Comparator<Integer>() {

		

			@Override
			public int compare(Integer i, Integer c)
			{
				System.out.println("==============");
				return i.compareTo(c);
			}
		});
		for (Object object : l)
		{
			System.out.println(object);
		}
	}
}

