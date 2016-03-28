/**
 * Project Desc:
 * Project Name:TestExample
 * File Name:Person.java
 * Package Name:util
 * Date:2015-5-6上午11:40:49
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
 */
package util;

/**
 * ClassName:Person <br/>
 * Date: 2015-5-6 上午11:40:49 <br/>
 * 
 * @author caosheng
 */
public class Person {

	private String id;

	private String name;

	private int age;

	private String sex;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
