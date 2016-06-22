/**
* Copyright (c) 2015年, SIMPO Technology. All Rights Reserved.
* SIMPO Technology. CONFIDENTIAL
*/
package cn.fancy.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *测试自定义注解的使用
 * @author caosheng
 * @version 2016年6月22日
 */
public class TestAnno {
 
  public static void main(String[] args)
  {
    Person per=new Person();
    try{
        for (Method m : per.getClass().getMethods()){
            if (m.getAnnotation(JsonIgnore.class) == null && m.getAnnotation(JsonBackReference.class) == null && m.getName().startsWith("get")){
                if (m.isAnnotationPresent(FieldName.class)) {
                    Annotation p = m.getAnnotation(FieldName.class);
                    FieldName fieldName=(FieldName) p;
                    
                    System.out.println(fieldName+"===="+fieldName.annotationType()+"==="+fieldName.value());
                }else{
                }
               
            }
        }
    }catch (Exception e) {
        e.printStackTrace();
    }

  }
}

class Person{
 
  private String id;
  private String name;
  
  @FieldName(value="1")
  public String getId()
  {
    return id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
}