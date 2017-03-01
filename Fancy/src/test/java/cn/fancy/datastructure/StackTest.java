package cn.fancy.datastructure;

/**   
 * @Title: StackTest.java 
 * @Package cn.fancy.datastructure 
 * @Description: 
 * stack  先进后出(LIFO)--------Java 对应的类 Stack
队列 先进先出(FIFO）--------java对应的类Queue 
 * @author 操圣
 * @date 2017年2月9日 上午10:31:49 
 * @version V1.0   
 */
public class StackTest {
	 public static void main(String[] args) {  
	        MyStack<String> stack = new MyStack<String>();  
	        for(String s : "the prefect code".split(" ")){//LIFO  
	            stack.push(s);  
	        }  
	        while(!stack.empty()){  
	            System.out.print(stack.peek()+" ");  
	            stack.pop();  
	        }  
	          
	        System.out.println();  
	        for(char s : "写了个一句话倒起来说的程序".toCharArray()){//用例：正话反说  
	            stack.push(String.valueOf(s));  
	        }  
	        while(!stack.empty()){  
	            System.out.print(stack.peek());  
	            stack.pop();  
	        }  
	    }  
}

