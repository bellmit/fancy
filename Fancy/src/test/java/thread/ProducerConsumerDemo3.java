package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: ProducerConsumerDemo.java
 * @Package thread
 * @Description: 演示生成者消费者的例子(会存在一个商品生产1个之后未消费的问题) 是因为被其他生产线程所覆盖，导致未判断上一个线程是否已经生产
 * 
 *               解决办法把if判断白哦几改为while就可以
 * @author 操圣
 * @date 2016年8月30日 下午10:44:48
 * @version V1.0
 */

class Resouce7 {

  private String name;

  private int count = 1;

  private boolean flag = false;
  Lock lock = new ReentrantLock();
  Condition condition = lock.newCondition();
  public void set(String name)
  {
    
    while (flag)
      try { condition.await(); } catch (InterruptedException e) { e.printStackTrace();lock.unlock();}
    lock.lock();
    this.name = name + "---" + count++;
    System.out.println(Thread.currentThread().getName() + "...生产者" + this.name);
    flag = true;
    condition.notifyAll();
    lock.unlock();
  }

  public  void out()
  {
    while (!flag)
      try { condition.await(); } catch (InterruptedException e) { e.printStackTrace();lock.unlock();}
    System.out.println(Thread.currentThread().getName() + "---------消费者" + this.name);
    flag = false;
    condition.notifyAll();
    lock.unlock();
  }
}


class Producer2 implements Runnable {

  Resouce7 r;

  @Override
  public void run()
  {
    while (true) {
      r.set("+商品+");
    }
  }

  public Producer2(Resouce7 r) {
    this.r = r;
  }

}


public class ProducerConsumerDemo3 {

  public static void main(String[] args)
  {
    Resouce7 r = new Resouce7();
    Producer2 P = new Producer2(r);
    Consumer3 consumer = new Consumer3(r);
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


class Consumer3 implements Runnable {

  Resouce7 r;

  public Consumer3(Resouce7 r) {
    this.r = r;
  }

  @Override
  public void run()
  {
    while (true) {
      r.out();
    }
  }

}
