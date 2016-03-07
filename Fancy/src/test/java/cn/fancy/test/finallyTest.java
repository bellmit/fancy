package cn.fancy.test;

import java.io.FileInputStream;

/**
 * @Title: Test1.java
 * @Package cn.fancy.test
 * @Description: 最后执行 ，最返回的方法还是finally
 * @author 操圣
 * @date 2015-10-29 下午5:16:44
 * @version V1.0
 */
public class finallyTest {

	public static void main(String[] args) {
		// System.out.println(test());
//		System.out.println(amethod());
		 Cake c1 = new Cake(1);  
	        Cake c2 = new Cake(2);  
	        Cake c3 = new Cake(3);  
	          
	        c2 = c3 = null;  
	        System.gc(); //Invoke the Java garbage collector  
	}

	public static String test() {
		try {
			System.out.println("try block");
			return test1();
		} finally {
			System.out.println("finally block");
			return "finally";
		}
	}

	public static String test1() {
		System.out.println("return statement");
		return "after return";
	}

	public static int amethod() {
		try {
			FileInputStream dis = new FileInputStream("Hello.txt"); // 1，抛出异常
		} catch (Exception ex) {
			System.out.println("No such file found"); // 2.catch捕获异常，并执行
			return -1; // 4,return 返回
		} finally {
			System.out.println("Doing finally"); // 3.finally一定会执行，在return之前。（准确说，应该是return
			return -2; // ;语句）

		}
	}


}

class Cake extends Object {

	private int id;

	public Cake(int id) {
		this.id = id;
		System.out.println("Cake Object " + id + "is created");
	}

	protected void finalize() throws java.lang.Throwable {
		super.finalize();
		System.out.println("Cake Object " + id + "is disposed");
	}
}
