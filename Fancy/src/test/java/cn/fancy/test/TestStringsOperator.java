package cn.fancy.test;

import java.util.Arrays;

public class TestStringsOperator {
	String a = "包河区,长丰县";

	public static void main(String[] args) {
		String a = "包河区,长丰县";
		String[] str = a.split(",");
		System.out.println(Arrays.toString(str));
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i <str.length; i++) {
			if (i == str.length-1) {
				buffer.append(str[i]);
			} else {
				buffer.append(str[i] + ",");
			}
		}	
		System.out.println(buffer.toString());
	}
}
