/**
 * Copyright (c) 2015年, SIMPO Technology. All Rights Reserved. SIMPO Technology. CONFIDENTIAL
 */
package cn.fancy.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;

/**
 * 
 * @author caosheng
 * @version 2016年8月12日
 */
public class TestList {

  public static void main(String[] args)
  {
    Map m=new HashedMap();
    
    Set s=new HashSet<>();
    s.add("我是set");
    s.add("我是dier");
    Collection a = new ArrayList();
    a.add("123");
    a.add("234");
    a.add(s);
    System.out.println(a.size());
    for (Object object : a) {
      System.out.println(object);
    }
  }
}
