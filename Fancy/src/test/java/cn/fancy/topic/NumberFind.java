package cn.fancy.topic;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: NumberFind.java
 * @Package cn.fancy.topic
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2016-2-18 下午2:26:51
 * @version V1.0
 */

/**** 判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除， 则表明此数不是素数，反之是素数。 ***/
public class NumberFind {

    public static void main(String[] args) {
//        int count = 0;
//        for (int i = 1; i < 50; i += 2) {
//            boolean b = false;
//            for (int j = 2; j <= Math.sqrt(i); j++) {
//                if (i % j == 0) {
//                    b = false;
//                    break;
//                } else {
//                    b = true;
//                }
//            }
//            if (b == true) {
//                count++;
//                System.out.println(i);
//            }
//        }
//        System.out.println("素数个数是: " + count);
//        System.out.println(isPrime(2));

        PrimeExample2();    
       
    }
    
    /**另一种是用一个数分别去除2到sqrt(这个数)，如果能被整除，则表明此数不是素数，反之是素数。 **/
    public static void PrimeExample2()
    {
        int count=0,i,j,leap=1;  
          
        for (i=101; i<=200; i++)   
        {  
            for (j=2; j<=Math.sqrt(i+1); j++)   
            {  
      
                if (i%j==0)   
                {  
                    leap=0;  
                    break;  
                }  
            }  
              
            if (leap==1)   
            {  
                count++;  
                System.out.println(i);
                if (count % 5 == 0)   
               System.out.println();
            }  
              
            leap=1;  
        }      
        System.out.println(count);
    }
    
    /**
     * 101-200之间的素数解法1
     * 
     */
    public void PrimeExample1()
    {
        /**
         * 
         * 
         * 程序分析:首先明白什么是素数，只能被1和本身整除的数，用循环遍历101-200之间的数，然后用101~200间的书整出2到该数前面一个数，
         * 比如是113，我们113整除2~112之间的数，只要这里的数整出都不等于0(不能整除),则可以判断这个数是素数；
         * **/
        int i,j;  
        int count=0;  
          
        for (i=101; i<=200; i++)   
        {  
            for (j=2; j<i; j++)   
            {  
//              如果j能被i整出在跳出循环  
               
                if (i%j==0)   
                    break;  
            }  
//            判断循环是否提前跳出，如果j<i说明在2~j之间,i有可整出的数  
            if (j>=i)   
            {  
                count++;  
               System.out.println(i);
//                换行，用count计数，每五个数换行  
                if (count % 5 == 0)   
               System.out.println();
      
            }  
      
        } 
        System.out.println(count);
    }
    /***
     * 
     * 
     * */
    public static boolean isPrime(long n) {
        if (n <= 3) {
            return n > 1;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            
            return false;
        }
     
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }


}
