package cn.fancy.designpattern.factory;

/**
 * @Title: Implementation1.java
 * @Package cn.test
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015-11-4 下午7:17:55
 * @version V1.0
 */
class Implementation1 implements Service {

	private Implementation1() {
	}

	public void method1() {
		System.out.println("Implementation1 method1");
	}

	public void method2() {
		System.out.println("Implementation1 method2");
	}

	public static ServiceFactory factory = new ServiceFactory() {

		public Service getService() {
			return new Implementation1();
		}
	};
}