package cn.fancy.datastructure;

import java.util.LinkedList;

/**   
 * @Title: MyStack.java 
 * @Package cn.fancy.datastructure 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2017年2月9日 上午10:30:26 
 * @version V1.0   
 */
public class MyStack<T> {
	   private LinkedList<T> storage = new LinkedList<T>();  
	   
	    public synchronized void push(T e) {//需要加上同步  
	        storage.addFirst(e);  
	    }  
	  
	    public T peek() {  
	        return storage.getFirst();  
	    }  
	  
	    public void pop() {  
	        storage.removeFirst();  
	    }  
	  
	    public boolean empty() {  
	        return storage.isEmpty();  
	    }  
	  
	    @Override  
	    public String toString() {  
	        return storage.toString();  
	    }  
}

