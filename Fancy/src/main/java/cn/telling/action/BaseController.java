/*
 * Copyright (c) 2015年, SIMPO Technology. All Rights Reserved. SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.action;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import cn.telling.common.uitl.DateUtils;


/**
 * 父类控制器
 * 
 * @author caosheng
 * @version 2016年5月25日
 */
public abstract class BaseController {

  /**
   * 日志对象
   */
  protected Logger logger = LoggerFactory.getLogger(getClass());

  public HttpServletRequest req;
  public HttpServletResponse resp;

  /**
   * 管理基础路径
   */
  @Value("${adminPath}")
  protected String adminPath;
  
  
  @ModelAttribute
  public void getRequestResponse(HttpServletRequest request, HttpServletResponse response)
  {
    System.out.println(adminPath);
    this.req = request;
    this.resp = response;
  }
  
  
  /**
   * 初始化数据绑定
   * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
   * 2. 将字段中Date类型转换为String类型
   */
  @InitBinder
  protected void initBinder(WebDataBinder binder) {
      // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
      binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
          @Override
          public void setAsText(String text) {
              setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
          }
          @Override
          public String getAsText() {
              Object value = getValue();
              return value != null ? value.toString() : "";
          }
      });
      // Date 类型转换
      binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
          @Override
          public void setAsText(String text) {
              setValue(DateUtils.parseDate(text));
          }
//        @Override
//        public String getAsText() {
//            Object value = getValue();
//            return value != null ? DateUtils.formatDateTime((Date)value) : "";
//        }
      });
  }
}
