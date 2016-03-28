package cn.fancy.sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title: Te.java
 * @Package cn.fancy.sort
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015年9月21日 下午8:41:34
 * @version V1.0
 */
public class TestDateMinus {

	public static long fromDateStringToLong(String inVal) { // 此方法计算时间毫秒
		Date date = null; // 定义时间类型
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			date = inputFormat.parse(inVal); // 将字符型转换成日期型
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date.getTime(); // 返回毫秒数
	}

	public static void main(String[] args) throws ParseException {

//		long startT = TestDateMinus.fromDateStringToLong("2005-03-03 14:51:23"); // 定义上机时间
//		long endT = TestDateMinus.fromDateStringToLong("2005-03-03 13:50:23"); // 定义下机时间
//		long ss = (startT - endT) / 1000; // 共计秒数
//		int MM = (int) ss / 60; // 共计分钟数
//		int hh = (int) MM / 60; // 共计小时数
//		int dd = (int) hh / 24; // 共计天数
//
//		System.out.println("共" + dd + "天,时间是：" + hh + " 小时 " + MM + " 分钟" 
//		+ ss + " 秒 共计：" + ss * 1000 + " 毫秒");


		   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		   java.util.Date now = df.parse("2004-03-31 0:0:0");
//		   java.util.Date date=df.parse("2004-03-28 0:0:0");
		   java.util.Date now = df.parse("2015-10-3 9:00:00");
		   java.util.Date date=df.parse("2015-10-2 8:01:00");
		  long curr= now.getTime();
		   long dd=date.getTime();
		   System.out.println(curr+"=="+dd);
		 long l=curr-dd;//两个时间差总毫秒
//	   	long day=l/(24*60*60*1000);
//		   long hour=(l/(60*60*1000)-day*24);
//		   long min=((l/(60*1000))-day*24*60-hour*60);
//		   long s=(l/1000-day*24*60*60-hour*60*60-min*60);
//		   System.out.println(hour+"小时"+min+"分"+s+"秒");
//		
	
		   long hour=(l/(60*60*1000));//得到总共小时
		   long min=((l/(60*1000))-hour*60);//得到分钟减去小时就是分钟
		   long s=(l/1000-hour*60*60-min*60);//得到秒减去小时减去分钟就是秒
		   System.out.println(hour+"小时"+min+"分"+s+"秒");
	}

}
