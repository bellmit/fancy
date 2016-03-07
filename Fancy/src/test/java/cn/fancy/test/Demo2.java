package cn.fancy.test;

public class Demo2 {
	public String a = "1";

	public static void main(String[] args) {
		Demo2 demo2 = new Demo2();
		System.out.println(demo2.a);
		demo2.d();
		System.out.println(demo2.a);
		demo2.c();
	}

	public void c() {
		System.out.println(a);
	}

	public void d() {
		a = "2";
	}
}
