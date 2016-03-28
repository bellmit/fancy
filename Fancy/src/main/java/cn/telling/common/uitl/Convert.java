package cn.telling.common.uitl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghb01
 *
 */
public class Convert {
	
	/**
	 * 字符串转换为日期
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static Date String2Date(String dateStr,String formatStr)
	{
		DateFormat dd=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
		
	}
	
	public static int String2Int(String s)
	{
		int r = 0;
		try{
			r = Integer.parseInt(s);
		}catch(Exception e){
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 把数字用中文输出
	 * @param num
	 * @return num的中文输出形式
	 */
	public static String numToCnChar(int num) {
		if(0==num) 
			return "零";
		String originNum = num +"";
		StringBuffer res = new StringBuffer();
		
		Map<Character, String> map_v = new HashMap<Character, String>(); 
		map_v.put('1', "一");
		map_v.put('2', "二");
		map_v.put('3', "三");
		map_v.put('4', "四");
		map_v.put('5', "五");
		map_v.put('6', "六");
		map_v.put('7', "七");
		map_v.put('8', "八");
		map_v.put('9', "九");
		map_v.put('0', "零");
		
		Map<Integer, String> map_c = new HashMap<Integer, String>(); 
		map_c.put(0, "");
		map_c.put(1, "十");
		map_c.put(2, "百");
		map_c.put(3, "千");
		map_c.put(4, "万");
		map_c.put(5, "十");
		map_c.put(6, "百");
		map_c.put(7, "千");
		map_c.put(8, "亿");
		map_c.put(9, "十");
//		map_c.put(10, "百");
//		map_c.put(9, "千");
//		map_c.put(9, "万");
		
		int length = originNum.length();
		for(int i = 0;i<length;i++) {
			if((0 == i) && '0' == originNum.charAt(length-i-1)) 
				continue;
				
			if(i > 0 && '0' == originNum.charAt(length-i-1) && '0' == originNum.charAt(length-i)) {
				continue;
			}
			
			res.insert(0,map_v.get(originNum.charAt(length-i-1)));
			
			if(i != 4 && '0' == originNum.charAt(length-i-1))
				continue;
			
			res.insert(1,map_c.get(i));
		}
		
		return res.toString();
		
	}
	
	public static void main(String[] args) {
		System.out.println(Convert.numToCnChar(123213243));
	}
}
