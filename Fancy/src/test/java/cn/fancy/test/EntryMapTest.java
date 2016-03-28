package cn.fancy.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class EntryMapTest {
	public static void main(String[] args) {
		Map<String, String> map=new HashMap<String,String>();
		map.put("name", "123");
		map.put("id","21");
		
		Iterator<Entry<String, String>> datas=map.entrySet().iterator();
		while (datas.hasNext()) {
			Entry<String, String> key=datas.next();
			String ke=key.getKey();
			String va=key.getValue();
			System.out.println(ke+"======"+va);
		}
	}
}
