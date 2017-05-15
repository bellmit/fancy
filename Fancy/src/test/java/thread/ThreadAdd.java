package thread;

/**   
 * @Title: ThreadAdd.java 
 * @Package thread 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2017年3月15日 下午10:59:20 
 * @version V1.0   
 */
public class ThreadAdd {
    private int j;  
    public static void main(String args[]) {  
    	ThreadAdd tt = new ThreadAdd();  
   
        for (int i = 0; i < 2; i++) {  
            new Thread( tt.new Inc()).start();  
            new Thread(tt.new Dec()).start();  
        }  }  
    private synchronized void inc() {  
        j++;  
        System.out.println(Thread.currentThread().getName() + "-inc:" + j);  
    }  
    private synchronized void dec() {  
        j--;  
        System.out.println(Thread.currentThread().getName() + "-dec:" + j);  
    }  
    class Inc implements Runnable {  
        public void run() {  
            for (int i = 0; i < 100; i++) {  
                inc();  
            }  }  }  
    class Dec implements Runnable {  
        public void run() {  
            for (int i = 0; i < 100; i++) {  
                dec();  
            }}  }}

