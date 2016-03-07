package cn.fancy.test;

import cn.telling.user.vo.Users;

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
		Users u = new Users();
		u.setAccount("我是");
		;
		test(u);
		System.out.println(u.getAge());
	}

	public static void test(Users us) {
		us.setAge("122");
	}
}
