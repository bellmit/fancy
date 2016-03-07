package cn.fancy.sort;

/**
 * @Title: ArrayUtils.java
 * @Package cn.fancy.sort
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015年9月6日 下午9:25:17
 * @version V1.0
 */
public class ArrayUtils {

	public static void printArray(int[] array) {
		System.out.print("{");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i < array.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("}");
	}

	public static void exchangeElements(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
}
