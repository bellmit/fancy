/**
 * 
 * Project Name:TestExample
 * File Name:sequare.java
 * Package Name:util
 * Date:2015-5-14下午4:29:30
 *
*/
package util;

import java.util.Random;

/**
 * ClassName:sequare <br/>
 * @author   caosheng
 */
public class Sequare
{

	private int[][] matrix = new int[3][3];

	private Random random = new Random();

	public int[][] loadMatrix()
	{
		return matrix;
	}

	/**
	 * @param	
	 * @author caosheng
	 * @date 2015-5-14
	 */
	public Sequare(int n)
	{
		matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = random.nextInt(100);
			}
		}
	}

	public Sequare(int n, int m)
	{
		matrix = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = random.nextInt(100);
			}
		}
	}

	public Sequare()
	{
		matrix = new int[3][3];
	}

	/**
	 * @param	
	 * @return	
	 * @author caosheng
	 * @date 2015-5-14
	 */
	public void print()
	{
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args)
	{
		Sequare sq = new Sequare(4);
		sq.print();
		Sequare s = sq.transpose();
		System.out.println("==================");
		s.print();
	}// 求一个矩阵的转置矩阵

	public Sequare transpose()
	{
		int n = matrix.length;
		int m = matrix[0].length;
		Sequare sq = new Sequare(n, m);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sq.loadMatrix()[i][j] = matrix[j][i];
			}
		}
		return sq;
	}
}
