/**
 * Copyright (c) 2015年, SIMPO Technology. All Rights Reserved. SIMPO Technology. CONFIDENTIAL
 */
package thread;


/**
 * 
 * @author caosheng
 * @version 2016年8月15日
 */
public class RunableTest implements Runnable {

  @Override
  public void run()
  {
    while (true) {
      System.out.println(Thread.currentThread().getName() + "吃饭");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args)
  {
    RunableTest rt = new RunableTest();
    Thread t1 = new Thread(rt);
    t1.start();
    Thread t2 = new Thread(rt);
    t2.start();
  }
}


class ThreadTets extends Thread {

  @Override
  public void run()
  {
    System.out.println(Thread.currentThread().getName() + "做事");
  }

  public static void main(String[] args)
  {
    ThreadTets tt = new ThreadTets();
    tt.start();
  }
}
