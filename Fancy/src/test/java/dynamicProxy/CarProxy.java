/**
 * 
 * Project Name:TestExample
 * File Name:CarProxy.java
 * Package Name:dynamicProxy
 * Date:2015-7-16
 *
 */

package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ClassName:代理类 <br/>
 * 
 * @author caosheng
 */

public class CarProxy implements InvocationHandler {
    /**
     * 代理类持有一个委托类的对象引用（实际传入需要执行的接口实现类）
     * */
	public Object target;

	/**
	 *生成动态代理对象
	  */
	public Object bind(Object object) {
		this.target = object;
		ClassLoader classLoader=target.getClass().getClassLoader();
		Class<?>[] interfa1ce=target.getClass().getInterfaces();
		for (int i = 0; i < interfa1ce.length; i++) {
          System.out.println(interfa1ce[i]);
        }
		System.out.println("target.getClass().getClassLoader():"+classLoader+"----interface");
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	/**
	 * 
	 * 通过反射动态执行方法
	 * 代理类  new BusCar
	 * method 动态获取到的接口方法run   target.getClass().getInterfaces()
	 * args 方法参数
	 * **/
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result;
		System.out.println("开始");
		result = method.invoke(target, args);
		System.out.println("结束");
		return result;
	}
}
