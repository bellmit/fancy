package cn.fancy.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @Title: HashMapTest.java
 * @Package cn.fancy.test
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015-11-16 上午10:19:03
 * @version V1.0
 */
class HashMapTest {

	/** hashcode判断对象地址是否相同 **/
	public static void main1(String[] args) {
		// 并允许使用 null 值和 null 键 线程非安全 此类不保证映射的顺序，特别是它不保证该顺序恒久不变。
		HashMap<String, String> hm = new HashMap<String, String>(1);
		hm.put(null, "df");
		hm.put(null, "df");
		hm.put(null, "df");
		hm.put(null, "df");
		hm.put("a", "df");
		hm.put(null, "df");
		hm.put(null, "df");
		hm.put(null, "df");
		hm.put(null, "aa");
		System.out.println(hm.get(null));
		System.out.println(hm.get("a"));

		// 不并允许使用 null 值和 null 键 线程安全
		Hashtable<Object, Object> ht = new Hashtable<>();
		ht.put("a", "12");
		System.out.println(ht.get("a"));

		/*** 不允许重复，会替换重复值 底层是hashmap实现 */
		Set<Object> s = new HashSet<>();
		s.add("测试");
		s.add("测试");
		Iterator<Object> aIterable = s.iterator();
		while (aIterable.hasNext()) {
			System.out.println(aIterable.next());

		}
	}

	public static void main(String[] args) {
		HashMap<Element, Figureout> h2 = new HashMap<Element, Figureout>();
		for (int i = 0; i < 10; i++) {
			h2.put(new Element(i), new Figureout());
			System.out.println("h2:");
			System.out.println("Get the result for Element:");
		}
		Element test = new Element(3);
		if (h2.containsKey(test)) {
			System.out.println((Figureout) h2.get(test));
		} else {
			System.out.println("Not found");
		}
		/** ?? ***/

		int a[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (7 > j + i * 3) {
					System.out.println(a[j + i * 3]);
				}
			}
		}
		
		int h = 1;
		int d = h ^ 3;
		System.out.println("============after" + d);

		int t = 121;
		int b = 121;
		System.out.println("a 和b 与的结果是：" + (t & b));

		
		/**********************测试***************************/
		Map<String,Integer> map = new HashMap<>();
        map.put("s1", 1);
        map.put("s2", 2);
        map.put("s3", 3);
        map.put("s4", 4);
        map.put("s5", 5);
        map.put(null, 9);
        map.put("s6", 6);
        map.put("s7", 7);
        map.put("s8", 8);
        for(Map.Entry<String,Integer> entry:map.entrySet())
        {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
	}

}

class Element {

	int number;

	public Element(int n) {
		number = n;
	}

	public int hashCode() {
		return number;
	}

	public boolean equals(Object o) {
		return (o instanceof Element) && (number == ((Element) o).number);
	}
}

class Figureout {

	Random r = new Random();

	boolean possible = r.nextDouble() > 0.5;

	public String toString() {
		if (possible) {
			return "OK!";
		} else {
			return "Impossible!";
		}
	}
}
