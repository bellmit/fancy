/**
 * 
 * Project Name:myweb-web
 * File Name:Td.java
 * Package Name:cn.fancy.test
 * Date:2015-7-23
 *
 */

package cn.fancy.test;

import java.math.BigDecimal;
import java.text.ParseException;

import cn.telling.utils.StringHelperTools;

/**
 * ClassName:Td <br/>
 * 
 * @author caosheng
 */
public class Td {
	public static void main(String[] args) throws ParseException
	{
//		String str = "2015-06-10 11:50:47";
//		SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
//
//		System.out.println(DateTimeTool.dateToString(DateTimeTool.parseDate(str), "yyyy/MM/dd hh:mm:ss"));
//
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Date d = df.parse(str);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//		System.out.println(formatter.format(d).trim());
//
//		
//		  SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日");
//		  SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd");
//		   System.out.println(format2.format(format.parse(str)));
		BigDecimal b=new BigDecimal(0);
		String o="";
		try {
			b=new BigDecimal(StringHelperTools.nvl(o));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
