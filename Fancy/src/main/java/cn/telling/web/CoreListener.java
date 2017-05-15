package cn.telling.web;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import cn.telling.config.Global;


/**
 * @Title: CoreListener.java
 * @Package cn.telling.web
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2016-2-25 上午10:28:57
 * @version V1.0
 */
public class CoreListener extends ContextLoaderListener {
    @Override
    public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
    	System.setProperty("org.terracotta.quartz.skipUpdateCheck","true");
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n    欢迎使用 "+Global.getConfig("productName")+"  - Powered By https://github.com/zhgo116\r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
        return super.initWebApplicationContext(servletContext);
    }
    
    
}
