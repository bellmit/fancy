/**
 * 
 */
package cn.telling.common.action;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**   
 * @Title: DictionaryType.java 
 * @Package com.Common.action 
 * @Description: TODO(描述该文件做什么) 
 * @author 冯俊杰
 * @date 2013-4-10 下午3:53:55 
 * @version V1.0   
 */
@Controller("Dictionary")
public class DictionaryType {
	
	@RequestMapping(value = "/list}", method = { RequestMethod.GET,RequestMethod.POST })
	public List<Map<String,String>> GetDictionaryList(String dicType)
	{
//		List<Map<String,String>> mapList=null;
		return null;
	}
}
