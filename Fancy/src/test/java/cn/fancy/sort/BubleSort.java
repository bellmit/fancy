package cn.fancy.sort;

import java.util.Arrays;

/**
 * @Title: Demo3.java
 * @Package cn.fancy.test
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015年9月5日 下午9:06:05
 * @version V1.0
 */
public class BubleSort {

	public static void main(String[] args) {
		int a[] = { 2, 1, 6, 3, 7, 0 };
		for (int i = 1; i <= a.length; i++) {
			for (int j = 0; j < a.length - i; j++) {
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(a));
	}
}
