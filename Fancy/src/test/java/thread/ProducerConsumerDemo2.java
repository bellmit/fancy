package thread;


/**
 * @Title: ProducerConsumerDemo.java
 * @Package thread
 * @Description: 演示生成者消费者的例子(会存在一个商品生产1个之后未消费的问题)
 * 是因为被其他生产线程所覆盖，导致未判断上一个线程是否已经生产
 * 
 * 解决办法把if判断白哦几改为while就可以
 * @author 操圣
 * @date 2016年8月30日 下午10:44:48
 * @version V1.0
 */

class Resouce6 {

	private String name;

	private int count = 1;

	private boolean flag = false;

	public synchronized void set(String name) {
		while (flag)
			try {
				this.wait();
			} catch (Exception e) {
			}
		this.name = name + "---" + count++;
		System.out.println(Thread.currentThread().getName() + "...生产者" + this.name);
		flag = true;
		this.notifyAll();
	}

	public synchronized void out() {
		while (!flag)
			try {
				this.wait();
			} catch (Exception e) {
			}
		System.out.println(Thread.currentThread().getName() + "---------消费者" + this.name);
		flag = false;
		this.notifyAll();
	}
}

class Producer1 implements Runnable {

	Resouce6 r;

	@Override
	public void run() {
		while (true) {
			r.set("+商品+");
		}
	}

	public Producer1(Resouce6 r) {
		this.r = r;
	}

}

public class ProducerConsumerDemo2 {

	public static void main(String[] args) {
		Resouce6 r = new Resouce6();
		Producer1 P = new Producer1(r);
		Consumer2 consumer = new Consumer2(r);
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

class Consumer2 implements Runnable {

	Resouce6 r;

	public Consumer2(Resouce6 r) {
		this.r = r;
	}

	@Override
	public void run() {
		while (true) {
			r.out();
		}
	}

}
