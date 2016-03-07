package cn.fancy.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class TestEncoding {
	public static void main(String[] args) {
		try {
	
			String a=URLEncoder.encode("我是java","UTF-8");
			System.out.println(a);
			System.out.println(	URLDecoder.decode(a,"utf-8"));
			System.out.println(URLEncoder.encode("我是java","gbk"));
			System.out.println(URLEncoder.encode("我是java","iso-8859-1"));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
