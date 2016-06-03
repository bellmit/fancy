package cn.fancy.test;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.util.StringUtils;


/**
 * @Title: FileTest.java
 * @Package cn.fancy.test
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2016-1-8 上午10:58:53
 * @version V1.0
 */
public class FileTest {

  public static void main(String[] args) throws IOException
  {
    // 创建一个空文件
    /*
     * File file=new File("Test1.txt"); 
     * file.createNewFile(); 
     * System.out.println( file.exists());
     */

    BigDecimal b = new BigDecimal(12.1212);
    System.out.println(b.subtract(new BigDecimal(10)));
    Double c = new Double(-233.12);
    System.out.println(c - (-233.12));
   String a="abcdef,123";
    StringBuilder sb=new StringBuilder("a").append("d").append("a");//推荐
    
    System.out.println(StringUtils.delimitedListToStringArray(a, ","));
    
  }
}
