package cn.fancy.test;

import java.util.HashMap;
import java.util.Map;



public class StringAndStringBufferTest {

  public static void main(String[] args) {
    // System.out.println(1 << 15);
    String[] he = {"12", "3"};
    System.out.println(he[0]);
    StringBuffer sBuffer = new StringBuffer("我是测试,");
    System.out.println(sBuffer.length());
    System.out.println(sBuffer.substring(0, sBuffer.length()));
    System.out.println("-----" + System.getProperty("line.separator"));
    System.out.println(String.valueOf((char) 1));
    System.out.println("2012-12-12 12:12:12");
    
    switch (1) {
      case 1:
        System.out.println("====1");
      case 2:
        System.out.println("====2");
      default:
        System.out.println("====default");
        break;
    }
    Map<String,String> payMaps=new HashMap<String,String>();
    payMaps.put("1", "天联好机汇web");
    payMaps.put("2", "天联好机汇APP");
    payMaps.put("3", "天联好机汇WAP");
    System.out.println(payMaps.get("4"));
  }

}
