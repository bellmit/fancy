package cn.telling.freemarker;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.TemplateException;

/**
 * 自定义一个ShiroTagFreeMarkerConfigurer继承Spring本身提供的FreeMarkerConfigurer,目的是在FreeMarker的Configuration中添加shiro的配置
 * 
 * @author q
 * 
 */
public class TagFreeMarkerConfigurer extends FreeMarkerConfigurer {
	@Override
	public void afterPropertiesSet() throws IOException, TemplateException
	{
		super.afterPropertiesSet();
		this.getConfiguration().setSharedVariable("display", new DictDisplayDirective());
		this.getConfiguration().setSharedVariable("select", new DictSelectDirective());
		this.getConfiguration().setSharedVariable("redirect", new RedirectTag());

	}
}
