/**
 * 
 * Project Name:TestExample
 * File Name:Node.java
 * Package Name:util
 * Date:2015-5-14上午8:39:12
 *
*/
package util;

/**
 * ClassName:Node <br/>
 * @author   caosheng
 */
//节点类
public class Node
{

	protected Node next; //指针域

	public static Node first; // 定义一个头结点

	protected int data;//数据域

	public Node(int data)
	{
		this.data = data;
	}

	//显示此节点
	public void display()
	{
		System.out.print(data + " ");
	}

	//单链表
	static class LinkList
	{

		private int pos = 0;// 节点的位置

		public LinkList()
		{
			first = null;
		}

		// 插入一个头节点
		public void addFirstNode(int data)
		{
			Node node = new Node(data);
			node.next = first;
			first = node;
		}

		// 删除一个头结点,并返回头结点
		public Node deleteFirstNode()
		{
			Node tempNode = first;
			first = tempNode.next;
			return tempNode;
		}

		// 在任意位置插入节点 在index的后面插入
		public void add(int index, int data)
		{
			Node node = new Node(data);
			Node current = first;
			Node previous = first;
			while (pos != index) {
				previous = current;
				current = current.next;
				pos++;
			}
			node.next = current;
			previous.next = node;
			pos = 0;
		}

		// 删除任意位置的节点
		public Node deleteByPos(int index)
		{
			Node current = first;
			Node previous = first;
			while (pos != index) {
				pos++;
				previous = current;
				current = current.next;
			}
			if (current == first) {
				first = first.next;
			} else {
				pos = 0;
				previous.next = current.next;
			}
			return current;
		}

		// 根据节点的data删除节点(仅仅删除第一个)
		public Node deleteByData(int data)
		{
			Node current = first;
			Node previous = first; //记住上一个节点
			while (current.data != data) {
				if (current.next == null) {
					return null;
				}
				previous = current;
				current = current.next;
			}
			if (current == first) {
				first = first.next;
			} else {
				previous.next = current.next;
			}
			return current;
		}

		// 显示出所有的节点信息
		public void displayAllNodes()
		{
			Node current = first;
			while (current != null) {
				current.display();
				current = current.next;
			}
			System.out.println();
		}

		// 根据位置查找节点信息
		public Node findByPos(int index)
		{
			Node current = first;
			while (pos != index) {
				current = current.next;
				pos++;
			}
			return current;
		}

		// 根据数据查找节点信息
		public Node findByData(int data)
		{
			Node current = first;
			while (current.data != data) {
				if (current.next == null)
					return null;
				current = current.next;
			}
			return current;
		}
	}

	public static void main(String[] args)
	{
		LinkList linkList = new LinkList();
		linkList.addFirstNode(20);
		linkList.addFirstNode(21);
		linkList.addFirstNode(19);
		//19,21,20
		linkList.add(1, 22); //19,22,21,20
		linkList.add(2, 23); //19,22,23,21,20
		linkList.add(3, 99); //19,22,23,99,21,20
		
		//      Node node = linkList.deleteFirstNode();
		//      System.out.println("node : " + node.data);
		//      linkList.displayAllNodes();
		//      node = linkList.deleteByPos(2);
		//      System.out.println("node : " + node.data);
		//      linkList.displayAllNodes();
		//      linkList.deleteFirstNode();
		Node node = linkList.deleteByData(19);
		//      Node node = linkList.deleteByPos(0);
		System.out.println("node : " + node.data);
		linkList.displayAllNodes();
		Node node1 = linkList.findByPos(0);
		System.out.println("node1: " + node1.data);
		Node node2 = linkList.findByData(22);
		System.out.println("node2: " + node2.data);
	}
}
