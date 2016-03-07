/**
 * 
 * Project Name:myweb-web
 * File Name:DateTimeTest.java
 * Package Name:cn.fancy.test
 * Date:2015-7-21
 *
 */

package cn.fancy.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.telling.utils.DateTimeTool;



/**
 * ClassName:DateTimeTest <br/>
 * 
 * @author caosheng
 */
public class DateTimeTest {
	public static void main(String[] args) throws ParseException
	{
		String str = "2015-06-10 11:50:47";
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd");
		System.out.println(format1.format(format.parse(str)));
		// 感觉这个好一点
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		Calendar ca = Calendar.getInstance();
		String operDate = df.format(ca.getTime());
		System.out.println(operDate);
		
		System.out.println(DateTimeTool.dateToString(DateTimeTool.parseDate("2015-06-10 11:50:47", "yyyy-MM-dd HH:mm"),"yyyy-MM-dd HH:mm"));
	}
}
