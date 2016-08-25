/**
 * Copyright (c) 2015年, SIMPO Technology. All Rights Reserved. SIMPO Technology. CONFIDENTIAL
 */
package thread;


/**
 * 死锁程序演示
 * @author caosheng
 * @version 2016年8月25日
 */
public class DeadLockTest implements Runnable {

   boolean flag = true;
  
  /**
   * 
   * @param 
   * @return 
   */
  public DeadLockTest(boolean flag) {
    this.flag=flag;
  }
  public void run()
  {
    if (flag) {
      while (true) {
        synchronized (MyLock.locka) {
          System.out.println("locka");
          synchronized (MyLock.lockb) {
            System.out.println("++lockb");
          }
        }
      }
    } else {
      while (true) {
        synchronized (MyLock.lockb) {
          System.out.println("lockb");
          synchronized (MyLock.locka) {
            System.out.println("--locka");
          }
        }
      }
    }
  }

  public static void main(String[] args)
  {
    DeadLockTest d = new DeadLockTest(true);
    DeadLockTest d1 = new DeadLockTest(false);
    Thread t = new Thread(d);
    t.start();
    Thread t1 = new Thread(d1);
    t1.start();
  }
}


class MyLock {

  static Object locka = new Object();
  static Object lockb = new Object();
}
