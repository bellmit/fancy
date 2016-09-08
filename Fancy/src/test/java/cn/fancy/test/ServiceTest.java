package cn.fancy.test;

import cn.telling.user.vo.User;

/**
 * @Title: ServiceTest.java
 * @Package cn.fancy.test
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015年12月27日 上午10:48:49
 * @version V1.0
 */
public class ServiceTest {

	public static void main(String[] args) {
		User u = new User();
		u.setAccount("我是");
		;
		test(u);
		System.out.println(u.getAge());
	}

	public static void test(User us) {
		us.setAge("122");
	}
}
