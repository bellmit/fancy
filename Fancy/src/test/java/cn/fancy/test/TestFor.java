package cn.fancy.test;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class TestFor {
	public static void main(String[] args) {
		List l = new ArrayList();
		l.add("java");
		l.add("c#");
		l.add("codf");
		for (int i = 0, len = l.size(); i < len; i++) {
		  System.out.println("=========测试");
		}
	  System.out.println(l.size());
	   Object[] elementData=new Object[10];
	   elementData[0]=l;
	   System.out.println(elementData.length);
	   System.out.println(elementData[0]);
	   List datas=Lists.newArrayList();
	  System.out.println(datas.size());
	  System.out.println("aSpring.java".matches(".*\\.java$"));
	  System.out.println("123"+1);
	  
	}
}
