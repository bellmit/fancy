package cn.fancy.datastructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**   
 * @Title: Stack.java 
 * @Package cn.fancy.datastructure 
 * @Description:
 *	队列中的元素是:[a, b, c, d, e, 1, 2, 3, 4, 5]
队列中的元素是:[b, c, d, e, 1, 2, 3, 4, 5]
查看队列中首个元素，并不移除:b
队列中的元素是:[b, c, d, e, 1, 2, 3, 4, 5]
-------栈--------
栈中的元素是:[a, b, c, d, e, 1, 2, 3, 4, 5]
栈中的元素是:[a, b, c, d, e, 1, 2, 3, 4]
查看栈中首个元素，并不移除:4
栈中的元素是:[a, b, c, d, e, 1, 2, 3, 4]
 * @author 操圣
 * @date 2017年2月9日 下午8:41:35 
 * @version V1.0   
 */
public class StackExample {
	/**
     * 测试队列
     * <pre>
     * 队列特点，先进先出，后进后出，火车过山洞例子
     * </pre>
     */
    static void testQueue(){
        Queue<String> queue=new LinkedList<String>();
        //添加几个元素
        queue.offer("b");
        queue.offer("a");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        System.out.println("队列中的元素是:"+queue);
        //弹出元素
        queue.poll();
        System.out.println("队列中的元素是:"+queue);
        //查看队列中首个元素，并不移除
        String peek=queue.peek();
        System.out.println("查看队列中首个元素，并不移除:"+peek);
        System.out.println("队列中的元素是:"+queue);
    }
    
    
    /**
     * 测试栈
     * <pre>
     * 先进后出，后进先出，水桶倒水
     * </pre>
     */
    static void testStack(){
        Stack<String> stack=new Stack<String>();
        //添加几个元素
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        stack.add("1");
        stack.add("2");
        stack.add("3");
        stack.add("4");
        stack.add("5");
        System.out.println("栈中的元素是:"+stack);
        stack.pop();//删除栈顶值
        System.out.println("栈中的元素是:"+stack);
        //查看栈中首个元素，并不移除
        String peek=stack.peek();
        System.out.println("查看栈中首个元素，并不移除:"+peek);
        System.out.println("栈中的元素是:"+stack);
    }
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        //testQueue();
        System.out.println("-------栈--------");
        //testStack();
        
        Stack s=new Stack();
        s.push("1");
        s.push("2");
        System.out.println(s);
    }

}

