/**
 * Copyright (c) 2015年, SIMPO Technology. All Rights Reserved. SIMPO Technology. CONFIDENTIAL
 */
package cn.fancy.test;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 对标签进行转义和反转义
 * @author caosheng
 * @version 2016年6月27日
 */
public class StringEscapeTest {

  /** * @param args */
  public static void main(String[] args)
  {
    String s = "<pre class=\"brush: java;\">";
    System.out.println(StringEscapeUtils.escapeHtml3(s));
    String u = "<pre class='brush: java;'>";
    System.out.println(StringEscapeUtils.unescapeHtml3(u));
  }
}
