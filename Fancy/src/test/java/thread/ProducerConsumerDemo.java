package thread;


/**
 * @Title: ProducerConsumerDemo.java
 * @Package thread
 * 需求：
 * 多个生产者 多个消费者
 *
 * @Description: 演示生成者消费者的例子(会存在一个商品生产1个之后未消费的问题)
 * 是因为被其他生产线程所覆盖，导致未判断上一个线程是否已经生产
 * 
 * 解决办法把if判断白哦几改为while就可以
 * 
 * 但是还存在一个问题  while导致所有线程等待  ，程序挂在那不动  解决办法改notify 为notifyall
 * @author 操圣
 * @date 2016年8月30日 下午10:44:48
 * @version V1.0
 */

class Resouce5 {

	private String name;

	private int count = 1;

	private boolean flag = false;

	public synchronized void set(String name) {
		if (flag)
			try {
				this.wait();
			} catch (Exception e) {
			}
		this.name = name + "---" + count++;
		System.out.println(Thread.currentThread().getName() + "...生产者" + this.name);
		flag = true;
		this.notify();
	}

	public synchronized void out() {
		if (!flag)
			try {
				this.wait();
			} catch (Exception e) {
			}
		System.out.println(Thread.currentThread().getName() + "---------消费者" + this.name);
		flag = false;
		this.notify();
	}
}

class Producer implements Runnable {

	Resouce5 r;

	@Override
	public void run() {
		if (true) {
			r.set("+商品+");
		}
	}

	public Producer(Resouce5 r) {
		this.r = r;
	}

}

public class ProducerConsumerDemo {

	public static void main(String[] args) {
		Resouce5 r = new Resouce5();
		Producer P = new Producer(r);
		Consumer consumer = new Consumer(r);
		Thread t1 = new Thread(consumer);
		t1.start();
		Thread t2 = new Thread(P);
		t2.start();
		Thread t3 = new Thread(consumer);
		t3.start();
		Thread t4 = new Thread(P);
		t4.start();
	}
}

class Consumer implements Runnable {

	Resouce5 r;

	public Consumer(Resouce5 r) {
		this.r = r;
	}

	@Override
	public void run() {
		while (true) {
			r.out();
		}
	}

}
