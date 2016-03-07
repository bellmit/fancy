package cn.fancy.designpattern.factory;

/**
 * @Title: Factories.java
 * @Package cn.test
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015-11-4 下午7:18:30
 * @version V1.0
 */
public class Factories {

	public static void serviceConsumer(ServiceFactory fact) {
		Service s = fact.getService();
		s.method1();
		s.method2();
	}

	public static void main(String[] args) {
		serviceConsumer(Implementation1.factory);
		// Implementations are completely interchangeable:
		serviceConsumer(Implementation2.factory);
	}
}
