package cn.fancy.test;


public class CompareNumber {

	public static void main(String[] args) {
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
			if (b2<a[i]&& a[i] < b1){
			
				b2 = a[i];
				
			}
		}

		System.out.println("第二大的数是：" + b2);
	}

}
