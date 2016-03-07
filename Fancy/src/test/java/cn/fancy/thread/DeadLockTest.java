package cn.fancy.thread;

/**
 * @Title: DeadLockTest.java
 * @Package cn.fancy.thread
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2016年1月3日 下午3:29:23
 * @version V1.0
 */
class Test implements Runnable {

	private Object locka = new Object();

	private Object lockb = new Object();

	private boolean flag;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	Test(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		if (flag) {
			synchronized (locka) {
				System.out.println("if locka");
				synchronized (lockb) {
					System.out.println("if lockb");
				}
			}
		} else {
			synchronized (lockb) {
				System.out.println("else lockb");
				synchronized (locka) {
					System.out.println("else locka");
				}
			}
		}
	}
}

public class DeadLockTest {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Test(true));
		Thread t2 = new Thread(new Test(false));
		t1.start();
		t2.start();
	}
}
