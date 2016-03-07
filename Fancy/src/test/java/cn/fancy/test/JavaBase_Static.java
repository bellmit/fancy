package cn.fancy.test;
import org.junit.Test;;
/**   
 * @Title: JavaBase_Static.java 
 * @Package cn.fancy.test 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2016-1-25 下午7:20:40 
 * @version V1.0   
 */

public class JavaBase_Static {
    int c=0;
   static int j=0;
    @Test
    public void t()
    {
    
        for (int i = 0; i < 2; i++) {
            c++;
            j++;
            System.out.println(c+"======"+j);
        }
    }
}

