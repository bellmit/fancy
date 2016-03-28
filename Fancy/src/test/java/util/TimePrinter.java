/**
 * 
 * Project Name:TestExample
 * File Name:TimePrinter.java
 * Package Name:util
 * Date:2015-5-12下午4:02:13
 *
*/
package util;

/**
 * ClassName:TimePrinter <br/>
 * @author   caosheng
 */
import java.util.Date;

public class TimePrinter implements Runnable
{

	int pauseTime;

	String name;

	public TimePrinter(int x, String n)
	{
		pauseTime = x;
		name = n;
	}

	/**
	 * @param args
	 *            当使用 runnable 接口时，您不能直接创建所需类的对象并运行它；必须从 Thread 类的一个实例内部运行它
	 * 
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		// TimePrinter tp1 = new TimePrinter(1000, "Fast Guy"); 继承Thread
		// tp1.start();
		// TimePrinter tp2 = new TimePrinter(3000, "Slow Guy");
		// tp2.start();
		Thread t1 = new Thread(new TimePrinter(1000, "Fast Guy"));
		t1.start();
		Thread t2 = new Thread(new TimePrinter(3000, "Slow Guy"));
		t2.start();
	}



	@Override
	public void run()
	{
		while (true) {
			try {
				System.out.println(name + ":" + new Date(System.currentTimeMillis()));
				Thread.sleep(pauseTime);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
