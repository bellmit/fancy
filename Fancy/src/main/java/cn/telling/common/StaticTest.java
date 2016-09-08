package cn.telling.common;

/**   
 * @Title: StaticTest.java 
 * @Package cn.fancy.test 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2015-12-10 上午9:01:14 
 * @version V1.0   
 */
  class StaticTest {
	public  final void test1()
	{
		System.out.println("========================================================================");
	}
	
	/*
	 * @(#) StaticTest.java.java 2015-12-10
	 *
	 * Copyright (c) 2015, Simpo Technology. All Rights Reserved.
	 * Simpo Technology. CONFIDENTIAL
	 */
	public StaticTest() {
		System.out.println("我出事花了");
	}
	public static void main(String[] args) {
		String path = PropertiesLoader.class.getResource("/").getPath();
		System.out.println(path);
	}
}

