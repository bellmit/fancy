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
	public Object target;

	public Object bind(Object object) {
		this.target = object;
		System.out.println(target.getClass().getClassLoader());
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result;
		System.out.println("开始");
		result = method.invoke(target, args);
		System.out.println("结束");
		return result;
	}
}
