package cn.fancy.test;

import java.util.ArrayList;
import java.util.Collection;

/**   
 * @Title: CollectionTest.java 
 * @Package cn.fancy.test 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2015-12-17 下午6:54:39 
 * @version V1.0   
 */
public class CollectionTest {
	public static void main(String[] args) {
		Collection c=new ArrayList<>();
		c.add("a");
		c.add("b");
		c.add("c");
		c.add("d");
		
		for (Object object : c) {
			System.out.println(object);
		}
		int a=2;
		for (int i = 0; i < 10&&i<=2; i++) {
			System.out.println("=========");
		}
	}
}

