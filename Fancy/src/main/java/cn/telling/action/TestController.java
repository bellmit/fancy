/*
 * Copyright (c) 2015年, SIMPO Technology. All Rights Reserved. SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author caosheng
 * @version 2016年5月25日
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

  @RequestMapping("index")
  public String index()
  {
    logger.debug("hahahahsdfa");
    return "/a";
  }

}
