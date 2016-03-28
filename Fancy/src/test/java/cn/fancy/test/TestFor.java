package cn.fancy.test;

import java.util.ArrayList;
import java.util.List;

public class TestFor {
	public static void main(String[] args) {
		List l = new ArrayList();
		l.add("java");
		l.add("c#");
		l.add("codf");
		for (int i = 0, len = l.size(); i < len; i++) {
		  System.out.println("=========测试");
		}
		
	}
}
