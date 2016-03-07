package cn.fancy.topic;




/**
 * @Title: Fibonacci.java
 * @Package cn.fancy.topic
 * @Description: 斐波那契数列
 * @author 操圣
 * @date 2016-2-15 上午10:37:29
 * @version V1.0
 */

/*** 
 *  
             实际月份  1    2    3    4    5    6    7     8

             幼仔对数  1    0    1    1    2    3    5     8
             成兔对数  0    1    1    2    3    5    8    13
             总体对数  1    1    2    3    5    8   13   21
     
             幼仔对数=前月成兔对数
             成兔对数=前月成兔对数+前月幼仔对数
             总体对数=本月成兔对数+本月幼仔对数
     
             可以看出幼仔对数、成兔对数、总体对数都构成了一个数列。这个数列有关十分明显的特点，那是：前面相邻两项之和，构成了后一项。
 ****/
public class Fibonacci {
    public static void main(String[] args) {
       
        System.out.println("第1个:    1");
        System.out.println("第2个:    1");
        int f1 = 1;
        int f2 = 1;
        int f=1;
        for (int i = 3; i <= 24; i++) {
            f = f2;//把上次的结果赋给temp
            f2 = f1 + f2; 
            f1 = f;//替换前面的a值
            System.out.println("第" + i + "个: " + f2);
        }
        
        
        /***第二种方法*/
        int a=1;
        int b=1;
        int c=1;
        for (int i = 3; i <= 24; i++) {
           c=a+b;
           b=a;
           a=c;
            System.out.println("第" + i + "个: " + c);
        }
        System.out.println("=-================");
        test();
    }
    public static  void test()
    {
        int a = 1;
        int b = 0;
        int c;
        while (a <= 55) {
        c= a + b;
        a = b;
        b = c;
        System.out.println(c);
         
        }
    }
}
