package cn.telling.freemarker;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 数据字典根据key显示value
 * 
 * @author 菠萝大象
 */
public class DictDisplayDirective implements TemplateDirectiveModel {

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		SimpleScalar paramValue = (SimpleScalar) params.get("value");
		SimpleScalar type = (SimpleScalar) params.get("type");
		int value = 0;
		if (paramValue != null)
			value = Integer.parseInt(paramValue.getAsString());
		Writer out = env.getOut();
		String result = null;
		if(value != 0){
			result = DictContext.getInstance().getDict(type.getAsString(), value);
		}
		if (StringUtils.isEmpty(result))
			result = "";
		out.write(result);
	}
}