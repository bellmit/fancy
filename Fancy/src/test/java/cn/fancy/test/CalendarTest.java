/*
 * Copyright (c) 2015年, SIMPO Technology. All Rights Reserved. SIMPO Technology. CONFIDENTIAL
 */
package cn.fancy.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @description
 * @author caosheng
 * @version 2016年4月22日
 */
public class CalendarTest {

  public static void main(String[] args)
  {
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    System.out.println(sdf.format(c.getTime()));
    System.out.println(c.get(Calendar.HOUR_OF_DAY));
  }
}
