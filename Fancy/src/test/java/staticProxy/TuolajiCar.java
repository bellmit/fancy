/**
 * 
 * Project Name:TestExample
 * File Name:TuolajiCar.java
 * Package Name:cn.te
 * Date:2015-7-16
 *
 */

package staticProxy;

/**
 * ClassName:TuolajiCar <br/>
 * 
 * @author caosheng
 */
public class TuolajiCar implements Car {

	BusCar bc;

	public TuolajiCar(BusCar bc) {
		this.bc = bc;
	}

	@Override
	public void run() {
		bc.run();
	}

}
