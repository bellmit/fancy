package cn.telling.freemarker;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import freemarker.core.Environment;
import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * select下拉框FreeMarker自定义指令
 * 
 * @author 菠萝大象
 */
public class DictSelectDirective implements TemplateDirectiveModel {

	@Override
	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		SimpleScalar id = (SimpleScalar) params.get("id"); // select id
		SimpleScalar name = (SimpleScalar) params.get("name"); // select name
		SimpleScalar type = (SimpleScalar) params.get("type"); // 数据字典类型
		TemplateModel value = (TemplateModel)params.get("value"); // 数据字典值
		SimpleScalar style = (SimpleScalar) params.get("class"); // select class样式
		TemplateBooleanModel showSelect = (TemplateBooleanModel) params.get("showSelect"); // 是否显示请选择option
		SimpleScalar option = (SimpleScalar) params.get("option"); //默认的第一个显示文本，如果为空，显示"请选择"，否则按此值显示
		Writer out = env.getOut();
		if (type != null) {
			StringBuilder builder = new StringBuilder();
			builder.append("<select id='");
			if (id != null && StringUtils.isNotBlank(id.getAsString())) {
				builder.append(id);
			} else if (name != null && StringUtils.isNotBlank(name.getAsString())) {
				builder.append(name);
			}
			builder.append("'");
			if (name != null && StringUtils.isNotBlank(name.getAsString())) {
				builder.append(" name='").append(name).append("'");
			}
			if (style != null && StringUtils.isNotBlank(style.getAsString())) {
				builder.append(" class='").append(style).append("'");
			}
			builder.append(">");
			if (showSelect == null || showSelect.getAsBoolean() != false) {
				builder.append("<option value='0'>");
				// 如果默认显示值不为空，即需要显示特定的文本，则加入option值
				if(option != null && StringUtils.isNotBlank(option.getAsString()))
					builder.append(option);
				else
					builder.append("请选择");
				builder.append("</option>");
			}
			
			List<String[]> dictList = DictContext.getInstance().getDict(type.getAsString());
			if (dictList != null) {
				int values = 0;
				if(value != null){
					if(value instanceof SimpleNumber){
						values = ((SimpleNumber)value).getAsNumber().intValue();
					}else if(value instanceof SimpleScalar){
						values = Integer.valueOf(((SimpleScalar)value).getAsString());
					}
				}
				for (String[] s : dictList) {
					builder.append("<option value='").append(s[0]).append("'");
					if (values == Integer.parseInt(s[0]))
						builder.append(" selected");
					builder.append(">").append(s[1]).append("</option>");
				}
			}
			builder.append("</select>");
			out.write(builder.toString());
		}
	}
}
