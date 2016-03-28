/**
 * 
 * Project Name:TestExample
 * File Name:BusCar.java
 * Package Name:dynamicProxy
 * Date:2015-7-16
 *
 */

package dynamicProxy;

/**
 * ClassName:BusCar <br/>
 * 
 * @author caosheng
 */
public class BusCar implements Car {

	@Override
	public void run() {
		System.out.println("公交车在跑");
	}

}
