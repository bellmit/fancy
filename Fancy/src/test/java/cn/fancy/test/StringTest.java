package cn.fancy.test;

import cn.telling.utils.StringHelperTools;

/**
 * @Title: StringTest.java
 * @Package cn.fancy.test
 * @Description: 字符串回文判断
 * @author 操圣
 * @date 2015-11-2 上午9:08:40
 * @version V1.0
 */
public class StringTest {

	static int fun(int low, int high, char str[], int length) {
		if (length == 0 || length == 1)
			return 1;
		if (str[low] != str[high])
			return 0;
		return fun(low + 1, high - 1, str, length - 2);
	}

	public static void main(String[] args) {
		String str = "aaabbaaa";
		// 返回1代表是， 0代表不是
		System.out.println(fun(0, str.length() - 1, str.toCharArray(), str.length()));
		System.out.println(StringHelperTools.nvl(null));
		System.out.println("123".charAt(1));
	}
}
