package cn.telling.hessian;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;

/**   
 * @Title: HessianTest.java 
 * @Package cn.telling.hessian 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2016-1-22 下午4:25:53 
 * @version V1.0   
 */
public class HessianTest {
    public static void main(String[] args) {
        /*ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-test.xml");
        IregisterService registerService = (IregisterService) ctx.getBean("registerService");*/
        Dog dog = new Dog();
        dog.setId(5);
        dog.setName("shit");
        // registerService.register(dog);
        //

        HessianProxyFactory factory = new HessianProxyFactory();
        IregisterService helloService;
        try {
            helloService =(IregisterService) factory .create(IregisterService.class, "http://127.0.0.1:8080/HessionService/remoting/registerService-hessian");
            helloService.register(dog);
            System.out.println(dog);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    
}

interface IregisterService{

    /**
     * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
     * @param		参数说明
     * @return		返回值
     * @exception   异常描述
     * @see		          需要参见的其它内容。（可选）
     * @author      操圣
     * @date 2016-1-22 下午4:26:54 
     * @version V1.0  
    */
    
    void register(Dog dog);
    
}
class Dog{

    /**
     * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
     * @param		参数说明
     * @return		返回值
     * @exception   异常描述
     * @see		          需要参见的其它内容。（可选）
     * @author      操圣
     * @date 2016-1-22 下午4:26:22 
     * @version V1.0  
    */
    
    public void setId(int i) {
        //  Auto-generated method stub
        
    }

    /**
     * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
     * @param		参数说明
     * @return		返回值
     * @exception   异常描述
     * @see		          需要参见的其它内容。（可选）
     * @author      操圣
     * @date 2016-1-22 下午4:26:24 
     * @version V1.0  
    */
    
    public void setName(String string) {
        //  Auto-generated method stub
        
    }
    
}

