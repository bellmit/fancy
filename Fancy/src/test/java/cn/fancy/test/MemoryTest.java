package cn.fancy.test;

/**
 * @Title: MemoryTest.java
 * @Package cn.fancy.test
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015-10-28 上午9:07:14
 * @version V1.0
 */
public class MemoryTest {

	public static void main(String[] args) {
		Runtime currRuntime = Runtime.getRuntime();
		int nFreeMemory = (int) (currRuntime.freeMemory() / 1024 / 1024);
		int nTotalMemory = (int) (currRuntime.totalMemory() / 1024 / 1024);
		String a= nFreeMemory + "M/" + nTotalMemory + "M(free/total)";
		System.out.println(a);
	}
}
