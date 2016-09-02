package thread;

/**
 * @Title: StopThreadDemo.java
 * @Package thread
 * @Description:
 * 
 *               加上线程还不安全,考虑亮点： 1.是不是两个以上线程， 2.用的同一个锁，都加上同步synchronized
 * 
 * 
 *               interrupter :当没有指定的方式让冻结的线程恢复到运行状态，这时需要对冻结状态进行清除
 *               强制让线程恢复到运行状态中来,这样就可以操作标记让线程结束 Thread类提供该方法interrupt
 * 
 * 
 * 
 *               stop方法已过时
 * 
 *               如何停止线程? 只有一种,run方法结束 开启多线程运行，运行代码通常是循环结构
 * 
 *               只要控制住循环，就可以让run方法结束，也就是线程结束
 *               
 *               特殊情况:
 *               当线程处于了冻结状态
 *               就不会读取到标记，那么线程就不会结束
 *               当没有指定的方式让冻结的线程恢复到运行状态的是：这时需要对冻结状态经行清除
 *               强制让线程恢复到运行状态中来，这样就可以操作标记让线程结束
 *               Thread类提供了interrupt方法
 *               
 * @author 操圣
 * @date 2016年9月1日 下午11:09:07
 * @version V1.0
 */
class StopThread implements Runnable {

	boolean flag = true;

	@Override
	public synchronized void run() {
		while (flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + "=========exception");
				flag=false;
			}
			System.out.println(Thread.currentThread().getName() + "=========run");
		}
	}

}

public class StopThreadDemo {

	public static void main(String[] args) {
		StopThread t = new StopThread();
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		t1.start();
		t2.start();
//		t1.setDaemon(true);
//		t2.setDaemon(true);
		int count = 0;
		while (true) {
			if (count++ == 60) {
				// t.flag=false;
				t1.interrupt();
				t2.interrupt();
				break;
			}
			System.out.println(Thread.currentThread().getName() + "" + count);
		}
	}

}
