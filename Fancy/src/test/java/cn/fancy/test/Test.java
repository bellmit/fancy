package cn.fancy.test;

public class Test {
	static String flag = "1";

	public static void main(String[] args) throws InterruptedException {
		/* *在给定的一个整数数组中（ 长度是20），要求查找第二大的数字是多少 */
		int[] a = { 12, 32, 65, 98, 41, 52, 63, 74, 85, 96, 36, 25, 14, 12, 45, 78, 64, 97, 28, 17 };
		// 先找到最大的
		int b1 = a[0];

		// 先找最大值
		for (int i = 0; i < a.length; i++) {
			if (b1 < a[i]) {
				b1 = a[i];
			}
		}
		int b2 = 0;
		for (int i = 0; i < a.length; i++) {
			if (b2 < a[i] && b2<b1)
				b2 = a[i];
		}
		// 打印结果：
		System.out.println("第二大的数是：" + b2);
	}
		


//		addIndex();
//		while (true) {
//			Thread.sleep(2000);
//			if (flag.equals("1")) {
//				System.out.println("处理1");
//				flag = "2";
//			} else if (flag.equals("2")) {
//				System.out.println("处理2");
//				flag = "1";
//			}
//		}
	

	private static void addIndex() {
		String a[] = { "1", "2", "3" };
		String b[] = { "1", "1", "1", "2", "2" };
		String c = "";
		String k = "";
		for (int i = 0; i < a.length; i++) {
			String a1 = a[i];

			for (int j = 0; j < b.length; j++) {
				String b1 = b[j];
				if (a1.equals(b1)) {
					c = b1;
					k = "1";
					System.out.println("加子表");
				}

				if ((!c.equals("") && !a1.equals(b1)) || j == b.length - 1 && k.equals("1") && a.length - 1 == i) {
					System.out.println("===============加主表=");
					c = "";
					k = "";
				}

			}

		}
	}

}
