package cn.fancy.sort;

public class Logarithm_Test {
	private static int[] sort = new int[] { 1, 0, 10, 20, 3, 5,6,7,8,9,0 ,3,3,2,3,4,1};

	/****
	 * 对数测试
	 * @param args
	 */
	public static void main(String[] args) {
		print(sort);
	}

	private static void print(int[] data) {
		int pre = -2;
		for (int i = 0; i < data.length; i++) {
			int s = (int) getLog(i + 1);
			if (pre < s) {
				pre = s;
				System.out.println();
			}
			System.out.print(data[i] + "|");
		}
		System.out.println("=========================");
		System.out.println("1===" + Math.log(1+1) / Math.log(2));
		System.out.println("2===" + Math.log(2+1) / Math.log(2));
		System.out.println("3===" + Math.log(3+1) / Math.log(2));
		System.out.println("4===" + Math.log(4+1) / Math.log(2));
		System.out.println("5===" + Math.log(5+1) / Math.log(2));
		System.out.println("6===" + Math.log(6+1) / Math.log(2));
		System.out.println("7===" + Math.log(7+1) / Math.log(2));
		System.out.println("8===" + Math.log(8+1) / Math.log(2));
		System.out.println("9===" + Math.log(9+1) / Math.log(2));
		System.out.println("10===" + Math.log(10+1) / Math.log(2));
		System.out.println("11===" + Math.log(11+1) / Math.log(2));
		System.out.println("12===" + Math.log(12+1) / Math.log(2));
		System.out.println("13===" + Math.log(13+1) / Math.log(2));
		System.out.println("14===" + Math.log(14+1) / Math.log(2));
		System.out.println("15===" + Math.log(15+1) / Math.log(2));
		System.out.println("16===" + Math.log(16+1) / Math.log(2));
		System.out.println("17===" + Math.log(17+1) / Math.log(2));
		System.out.println("18===" + Math.log(18+1) / Math.log(2));
		System.out.println("19===" + Math.log(19+1) / Math.log(2));
		System.out.println("20===" + Math.log(20+1) / Math.log(2));
	}

	/**
	 * 以2为底的对数
	 * 
	 * @paramparam
	 * @return
	 */
	private static double getLog(double param) {
		double c = Math.log(param) / Math.log(2);
		return c;
	}
}
