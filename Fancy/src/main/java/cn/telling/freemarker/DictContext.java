package cn.telling.freemarker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 模拟数据字典
 * @author 菠萝大象
 */
public class DictContext {

	private static Map<String, List<String[]>> map = new HashMap<String, List<String[]>>();
	
	private DictContext(){
		List<String[]> dictList = new ArrayList<String[]>();
		String[] array = new String[] {"1", "男"};
		dictList.add(array);
		array = new String[] {"2", "女"};
		dictList.add(array);
		map.put("xb01", dictList); // 性别
		dictList = new ArrayList<String[]>();
		array = new String[] {"1", "专科"};
		dictList.add(array);
		array = new String[] {"2", "本科"};
		dictList.add(array);
		array = new String[] {"3", "硕士"};
		dictList.add(array);
		array = new String[] {"4", "博士"};
		dictList.add(array);
		array = new String[] {"5", "博士后"};
		dictList.add(array);
		map.put("xl01", dictList); // 学历
	}
	
	private static class SingletonHolder {
		static DictContext instance = new DictContext();
	}
	
	public static DictContext getInstance() {
        return SingletonHolder.instance;
    }
	
	/**
	 * 根据key获得数据字典集
	 * <br/>每个数据集
	 * @param key 数据字典标识 egg xb01, xl01
	 * @return 数据字典集合List
	 */
	public List<String[]> getDict(String key) {
		if(!map.containsKey(key))
			return null;
		return map.get(key);
	}
	
	/**
	 * 根据key和value获得对应的文本
	 * @param key 数据字典标识 egg xb01, xl01
	 * @param value 数据字典值 egg key:xb01 value:1
	 * @return 数据字典对应的文本值
	 */
	public String getDict(String key, int value) {
		List<String[]> list = map.get(key);
		if (list == null)
			return null;
		for (String[] s : list) {
			if (StringUtils.isBlank(s[0]))
				continue;
			if (Integer.parseInt(s[0]) == value)
				return s[1];
		}
		return null;
	}
}