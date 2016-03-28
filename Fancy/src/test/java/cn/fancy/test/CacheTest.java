/**
 * 
 * Project Name:myweb-web
 * File Name:T.java
 * Package Name:cn.fancy.test
 * Date:2015-6-29
 *
 */

package cn.fancy.test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * ClassName:T <br/>
 * 
 * @author caosheng
 */
public class CacheTest {
	public static void main(String[] args) {
		// 指定ehcache.xml的位置
		String fileName = "D:\\workspace\\myweb\\myweb-web\\src\\main\\resources\\ehcache.xml";
		CacheManager manager = new CacheManager(fileName);
		// 取出所有的cacheName
		String names[] = manager.getCacheNames();
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
		// 根据cacheName生成一个Cache对象
		// 第一种方式：
		Cache cache = manager.getCache(names[0]);
		// 第二种方式，ehcache里必须有defaultCache存在,"test"可以换成任何值
		// Cache cache = new Cache("test", 1, true, false, 5, 2);
		// manager.addCache(cache);

		// 向Cache对象里添加Element元素，Element元素有key，value键值对组成
		cache.put(new Element("key1", "values1"));
		cache.put(new Element("m1", "我是后加的"));
		Element element = cache.get("m1");

		System.out.println(element.getValue());
		Object obj = element.getObjectValue();
		System.out.println((String) obj);
		manager.shutdown();

	}
}
