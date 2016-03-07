/**
 * 
 * Project Name:TestExample
 * File Name:Test.java
 * Package Name:cn.te
 * Date:2015-7-16
 *
 */

package staticProxy;

/**
 * ClassName:Test <br/>
 * 
 * @author caosheng
 */
public class Test {
	public static void main(String[] args) {
		BusCar bc = new BusCar();
		TuolajiCar c = new TuolajiCar(bc);
		c.run();
	}
}
