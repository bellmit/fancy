package cn.telling.freemarker;

import java.io.IOException;

import cn.telling.web.CmsWebUtils;
import freemarker.template.TemplateException;

public class RedirectTag extends AbstractSupperTag {

	@Override
	public void renden() throws TemplateException, IOException
	{
		String url = getParam("url");
	
		CmsWebUtils.getResponse().sendRedirect(url);
	}

}
