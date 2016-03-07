/**
 * 
 * Project Name:TestExample
 * File Name:LinkedList.java
 * Package Name:util
 * Date:2015-5-14上午10:35:00
 *
*/
package util;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * ClassName:LinkedList <br/>
 * @author   caosheng
 */
public class LinkedList
{

	private LinkedList next;

	private String data;

	private LinkedList first;

	public LinkedList()
	{
		this.first = null;
	}

	public LinkedList(String data)
	{
		this.data = data;
	}

	public void addFirstNode(String data)
	{
		LinkedList ll = new LinkedList(data);
		ll.next = first;
		first = ll;
	}

	public void show()
	{
		System.out.println(data);
	}

	public void Print()
	{
		LinkedList ll = first;
		while (ll != null) {
			ll.show();
			ll = ll.next;
		}
		System.out.println();
	}

	public static void main(String[] args)
	{
	        /**自定义LinkList测试**/
			LinkedList ll = new LinkedList();
			ll.addFirstNode("java");
			ll.addFirstNode("php");
			ll.Print();
			/****队列测试*/
			Queue<String> queue = new java.util.LinkedList<String>();
			queue.offer("Hello");
			queue.offer("World!");
			queue.offer("你好！");
			System.out.println(queue.size());
			String str;
			while ((str = queue.poll()) != null) {
				System.out.print(str + "\n");
			}
			System.out.println();
			System.out.println(queue.size());
		Queue linked = new java.util.LinkedList();
		linked.add("java");
		linked.add("php");
		System.out.println(linked.size());
		
		List lk=new java.util.LinkedList<>();
		lk.add("我是");
		lk.add("你是");
		lk.add(2, "哈哈");
		lk.add(1, "新增");
		
		System.out.println(lk.toString()+"====="+lk.get(0)+"===="+lk.get(3));
		String a="a";
		String b="b";
		System.out.println(a+"b"=="a"+b);
		System.out.println("a"+b.hashCode());
		
	}
}
