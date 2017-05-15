package cn.fancy.test;

/**   
 * @Title: TwoArray.java 
 * @Package cn.fancy.test 
 * @Description: 二维数组
 * @author 操圣
 * @date 2017年4月5日 下午3:57:06 
 * @version V1.0   
 */
public class TwoArray {
	public static void main(String[] args) {
		int[][] a=new int[][]{{2,3,62,5,72,1,0},{34,5,24,78,345,6},{34,0,3,2,6,92}};
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
}

