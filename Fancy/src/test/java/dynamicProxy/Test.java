/**
 * 
 * Project Name:TestExample
 * File Name:Test.java
 * Package Name:dynamicProxy
 * Date:2015-7-16
 *
 */

package dynamicProxy;

/**
 * ClassName:Test <br/>
 * 
 * @author caosheng
 */
public class Test {
	public static void main(String[] args) {
		CarProxy cp = new CarProxy();
		Car c = (Car) cp.bind(new BusCar());
		c.run();
	}
}
