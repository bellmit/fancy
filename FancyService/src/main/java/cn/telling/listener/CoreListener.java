package cn.telling.listener;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;


/**
 * @Title: CoreListener.java
 * @Package cn.telling.web
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2016-2-25 上午10:28:57
 * @version V1.0
 */
public class CoreListener extends ContextLoaderListener {
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.context.ContextLoader#initWebApplicationContext(javax.servlet.
     * ServletContext)
     */
    @Override
    public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n    欢迎使用 Fancy开发平台FancyServer接口  - Powered By http://jeesite.com\r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
        return super.initWebApplicationContext(servletContext);
    }
    
    
}
