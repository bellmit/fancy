/**
 * 
 */
package cn.telling.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @Title: MessageUtil.java
 * @Package com.Common.Uitl
 * @Description: 消息处理共通工具类
 * @author guohui
 * @date 2013-5-23 下午3:48:49
 * @version V1.0
 */
public class MessageUtil {

	// LOGGER
	private static Logger logger = Logger.getLogger(MessageUtil.class);



	// /////////////////////////////////////////////
	// properties
	// /////////////////////////////////////////////
	private static final String PROPERTIES_FILE_NAME = "messages.properties";

	private static final Map<String, String> MESSAGE_MAP;
	static {
		MESSAGE_MAP = new HashMap<String, String>();

		try {
			String path = MessageUtil.class.getResource("/").getPath();
//			System.out.println(path);
//			int index = path.indexOf(":");
//			path = path.substring(index >= 0 ? 1 : 0, path.indexOf("classes"));
//			System.out.println(path);
			InputStream is = new BufferedInputStream(new FileInputStream(path+/* PROPERTIES_FILE_PATH + System.getProperty("file.separator") +*/ PROPERTIES_FILE_NAME));
			Properties properties = new Properties();
			properties.load(is);
			Enumeration<Object> enum1 = properties.keys();

			while (enum1.hasMoreElements()) {
				String key = enum1.nextElement().toString();
				String msg = properties.getProperty(key);
				MESSAGE_MAP.put(key, msg);
			}
		} catch (Exception e) {
			logger.error("ExceptionMsgUtil.static error : " + e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description: 根据MessageCode判断是否存在消息。
	 * @param 消息代码
	 * @return 消息内容
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author guohui
	 * @date 2013-5-23 下午3:48:49
	 * @version V1.0
	 */
	public static boolean isFind(String key) {
		if (key == null || key.trim().length() == 0) {
			return false;
		}

		return MESSAGE_MAP.keySet().contains(key.trim());
	}

	/**
	 * 
	 * @Description: 根据MessageCode返回消息,带参数。
	 * @param 消息代码
	 *            消息参数
	 * @return 消息内容
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author guohui
	 * @date 2013-5-23 下午3:48:49
	 * @version V1.0
	 */
	public static String getMsg(String key, Object... param) {
		if (!isFind(key)) {
			return null;
		}
		String strMsgPattern = MESSAGE_MAP.get(key.trim());
		if (param == null || param.length == 0) {
			return strMsgPattern;
		}

		return MessageFormat.format(strMsgPattern, param);
	}
}
