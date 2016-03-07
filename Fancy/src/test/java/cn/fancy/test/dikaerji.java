/**
 * 
 * Project Name:myweb-web
 * File Name:dikaerji.java
 * Package Name:cn.fancy.test
 * Date:2015-7-24
 *
 */

package cn.fancy.test;

/**
 * ClassName:dikaerji <br/>
 * 
 * @author caosheng
 */
public class dikaerji {
	static String[][] arrs = { { "a", "b", "c" }, { "1", "2", "3", "2" },
			{ "A", "B", "23", "ccd" }, { "中", "国", "3", "32", "121" } };

	static String[][] arrs1 = { { "a", "b", "c" }, { "1", "2", "3", "2" },
			{ "A", "B", "23", "ccd" }, { "中", "国", "3", "32", "121" } };

	public static void main(String[] args) {
		 p(0, "");

		// String str = "";
		// int len = 0;
		// String s[] = arrs1[len];
		// for (int i = 0; i < arrs1.length-1; i++) {
		//
		// if (len< s.length-1) {
		// str = str + " " + s[i];
		// len++;
		// continue;
		// } else {
		// System.out.println(str + " " + s[i]);
		// }
		// }
	}

	static void p(int c, String str) {
		String[] d = arrs[c];
		/** 每列的字符递增 **/
		int  len = arrs.length - 1;
		for (int i = 0;i < d.length; i++) {
			if (c < len)/*** 列的length小于数组的length-1 **/
			{
				p(c + 1, str + " " + d[i]);// 行每次加1,遍历下个字符，累加每次的字符
			} else {
				System.out.println(str + " " + d[i]);// 当遍历到最后一条---3==4-1，打印一行
			}
		}
	}

}

class testRecursion {
	static int i = 1;

	// 递归方法
	static void method() {
		System.out.print(1);

		while (i > 0) {
			System.out.print(2);

			i = -1;
			method(); // 调用本方法

			System.out.print(3);
		}

		System.out.print(4);
	}

	// 输出的结果是： 1 2 1 4 3 4
	public static void main(String[] args) {
		method();
	}
}
