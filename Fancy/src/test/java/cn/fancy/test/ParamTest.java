/**
 * Copyright (c) 2015年, SIMPO Technology. All Rights Reserved. SIMPO Technology. CONFIDENTIAL
 */
package cn.fancy.test;


/**
 * 形参和形参Test
 * 
 * 形式参数就是函数定义时设定的参数。例如函数头 int min(int x,int y,int z) 中 x,y,z 就是形参。实际参数是调用函数时所使用的实际的参数。 例如调用方法时传递main(x,y,z) x=1,y=2,z=3
 * 
  C:真正被传递的是实参
  D:形参可以是对象,是对象的时候传递引用. 
 * @author caosheng
 * @version 2016年6月27日
 */
public class ParamTest {

  public static void changeInt( int a)
  {
    a = 100;
  }

  public static void main(String[] args)
  {
    int a = 10;//注意 他只是一个局部变量
    changeInt(a);//int基本类型    ;形参获取实参一个副本传递给方法  
    System.out.println(a);
  }
  
  
  /*public static void main(String[] args)
  {
    Name n = new Name("zhenxing", "sun");//声明了一个Name n的对象，地址值在堆中   n---- new Name("zhenxing", "sun")  n引用了堆中的对象
    changeName(n);  //形参获取实参地址（实参实际上就是存储的地址，也就是说它的值就是地址）的一个副本并传递给方法
    System.out.println(n);
    
  }

  public static void changeName(Name n)
  {
    n = new Name("yuanyuan", "sun");//n变量指向了新的Name实例,但是main方法中的n变量指向的对象并没有改变
  }
  */
}


class Name {

  public String firstName;
  public String lastName;

  public Name(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String toString()
  {
    return firstName + "-" + lastName;
  }
}
