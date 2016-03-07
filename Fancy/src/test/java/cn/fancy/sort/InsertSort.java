package cn.fancy.sort;

import java.util.Arrays;

/**
 * @Title: InsertSort.java
 * @Package cn.fancy.test
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015年9月5日 下午9:46:34
 * @version V1.0
 */
public class InsertSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 800, 9, 3, 6, 12, 54, 35, 411, 3, 245, 1, 0, 4 };
		InsertSort.InsertSort(arr);
	}
	/**重点  每次都是后一位和前一位比较，小于就交换，每一次都记住后一位的值，如果比较发现是最小，插入到最前面**/
	public static int[] InsertSort(int[] arr) {
		int i, j;
		int insertNote;// 要插入的数据
		int[] array = arr;

		// 从数组的第二个元素开始循环将数组中的元素插入
		for (i = 1; i < array.length; i++) {
			// 设置数组中的第2个元素为第一次循环要播讲的数据
			insertNote = array[i];
			j = i - 1;
			while (j >= 0 && insertNote < array[j]) {// 第二个小于第一个
				// 如果要播讲的元素小于第j个元素,就将第j个元素向后移动
				array[j + 1] = array[j];// 9=800//3=800
				j--;
			}
			// 直到要插入的元素不小于第j个元素,将insertNote插入到数组中
			array[j + 1] = insertNote;// 9=800
			System.out.println(Arrays.toString(array));
		}
		// 打印排序后的数组
		System.out.println(Arrays.toString(array));
		return array;

	}
}
