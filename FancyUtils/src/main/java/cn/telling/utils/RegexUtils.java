package cn.telling.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**   
 * @Title: RegexUtils.java 
 * @Package cn.telling.utils 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2015-12-16 下午1:05:17 
 * @version V1.0   
 */
public class RegexUtils {

	public static boolean isMatch(String regex, String content) {
		if (Assert.isEmpty(regex) || Assert.isEmpty(content))
			return false;
		return Pattern.matches(regex, content);
	}

	public static String[][] match(String regex, String content) {
		if (Assert.isEmpty(regex) || Assert.isEmpty(content))
			return null;
		Matcher matcher = Pattern.compile(regex).matcher(content);
		List<String[]> list = new ArrayList<String[]>();
		while (matcher.find()) {
			String matched[] = new String[matcher.groupCount() + 1];
			for (int i = 0; i <= matcher.groupCount(); i++)
				matched[i] = matcher.group(i);
			list.add(matched);
		}
		String[][] matched = new String[list.size()][];
		list.toArray(matched);
		return matched;
	}

}