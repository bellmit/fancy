package cn.fancy.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title: Spring_001.java
 * @Package cn.fancy.test
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015-12-10 下午8:34:49
 * @version V1.0
 */
public class Spring_001 {

	private AbstractApplicationContext applicationContext;

	public AbstractApplicationContext getAbstractApplicationContext() {
		return this.applicationContext;
	}

	String[] PATH = { "classpath:globalApplication.xml" };

	/*
	 * @(#) Spring_001.java.java 2015-12-10
	 * 
	 * Copyright (c) 2015, Simpo Technology. All Rights Reserved. Simpo
	 * Technology. CONFIDENTIAL
	 */
	public Spring_001() {
		this.applicationContext = new ClassPathXmlApplicationContext(PATH);
	}

	public static void main(String[] args) {

		new Spring_001();
	}
}
