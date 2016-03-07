/**
 * Project Desc:
 * Project Name:TestExample
 * File Name:Demo2.java
 * Package Name:util
 * Date:2015-5-6下午6:41:24
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
*/
package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:Demo2 <br/>
 * Date:     2015-5-6 下午6:41:24 <br/>
 * @author   caosheng
 */
public class Demo2
{

	public static void main(String[] args)
	{
		List<Person> person = new ArrayList<Person>();
		Person p = new Person();
		p.setAge("1");
		p.setId("12");
		p.setName("java");
		person.add(p);
		queryList(person);
	}

	public static void queryList(List<?> obj)
	{
		for (int i = 0; i < obj.size(); i++) {
			Object ob = obj.get(i);
			Method[] methods = Person.class.getDeclaredMethods();
			Field[] fields = Person.class.getDeclaredFields();
			for (Method method : methods) {
				try {
					if (method.getName().contains("get")) {
						System.out.println(method.invoke(ob, new Object[] {}));
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			for (Field field : fields) {
				boolean accessFlag = field.isAccessible();
				field.setAccessible(true);
				try {
					System.out.println(field.get(ob).toString());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				field.setAccessible(accessFlag);
			}
		}
	}

	static class Person
	{

		private String id;

		private String name;

		private String age;

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public String getId()
		{
			return id;
		}

		public void setId(String id)
		{
			this.id = id;
		}

		public String getAge()
		{
			return age;
		}

		public void setAge(String age)
		{
			this.age = age;
		}
	}
}
