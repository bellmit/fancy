package cn.telling.common.uitl;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import cn.telling.utils.UrlCommon;


/**
 * @Title: BaseUtil.java
 * @Package cn.com.cits.common.base.util
 * @Description: TODO(描述该文件做什么)
 * @author 李欢
 * @date 2011-11-21 下午4:36:48
 * @version V1.0
 */
public class BaseUtil {

	/**
	 * 日志打印JDBCTemplate的SQL
	 * 
	 * @param sql
	 * @param args
	 * @return 返回实际执行sql
	 */
	public static String logSQL(String sql, Object args[]) {
		if (args == null)
			return sql;
		for (int i = 0; i < args.length; i++) {
			if (args[i] != null) {
				String param = args[i].toString().replace("$", "\\$");
				sql = sql.replaceFirst("\\?",
						param.length() == 0 ? param
								: "\\'" + param + "'");
			} else {
				sql = sql.replaceFirst("\\?", "null");
			}
		}
		return sql;
	}

	/**
	 * 
	 * @param pwd_len
	 *            生成的密码的总长度
	 * @return 密码的字符串
	 */
	public static String genRandomNum(int pwd_len) {
		// 10个数字
		final int maxNum = 9;
		int i; // 生成的随机数
		int count = 0; // 生成的随机数的长度
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}

	/**
	 * 对html标签或特殊字符串编码
	 * 
	 * @param html
	 *            html代码
	 * @return String 替换后代码
	 */
	public static String HtmlEncode(String html) {

		if (isEmpty(html)) {
			return html;
		}

		html = replace(html, "&", "&amp;");
		// html = replace(html, "<", "&lt;");
		// html = replace(html, ">", "&gt;");
		html = replace(html, "\n", "<br>");
		// html = replace(html, "\"", "&quot;");

		return html;
	}

	/**
	 * 判断字符串是否为null或者trim后长度小于1
	 * 
	 * @param arg
	 *            要被判断的字符串
	 * @return true 为null或者trim后长度小于1
	 */
	public static boolean isEmpty(String arg) {
		if (arg == null || arg.trim().length() < 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 字符串替换。如果不需要用正则表达式请用此方法； 否则用String.replaceAll(String regex, String
	 * replacement)
	 * 
	 * @param text
	 *            需要被处理的字符串
	 * @param from
	 *            需要被替换掉的字符串
	 * @param to
	 *            被替换成的字符串
	 * @return 被替换后的字符串
	 * @see String#replaceAll(String, String)
	 */
	public static String replace(String text, String from, String to) {
		if (text == null || from == null || to == null) {
			return null;
		}
		StringBuffer newText = new StringBuffer(text.length() * 2);
		int pos1 = 0;
		int pos2 = text.indexOf(from);
		while (pos2 >= 0) {
			newText.append(text.substring(pos1, pos2) + to);
			pos1 = pos2 + from.length();
			pos2 = text.indexOf(from, pos1);
		}
		newText.append(text.substring(pos1, text.length()));
		return newText.toString();
	}


	/**
	 * 
	 * @Description: 制定上传路径，并返回。
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 李 欢
	 * @date 2013-6-5 下午5:26:53
	 * @version V1.0
	 */
	public static String getStaticFilePathForUpload(HttpServletRequest request,
			String yourPath) {
		// 图片上传返回的路径
		String uploadPath = "";
		// 获得主机名
		String serverName = request.getServerName();
		// 如果是本机，则按原来那样放置，在本地工程内即可
		if ("localhost".equals(serverName) || "127.0.0.1".equals(serverName)) {
			StringBuffer temp = new StringBuffer(request.getSession()
					.getServletContext().getRealPath("/")).append(
					"/uploads/resourses/").append(yourPath);
			System.out.println(request.getSession().getServletContext().getRealPath("/"));
			uploadPath = temp.toString();

		} else {// 否则默认就是Linux系统，默认存放根路径为 /mnt/b2b
			StringBuffer temp = new StringBuffer("/mnt/b2b/").append(yourPath);
			uploadPath = temp.toString();
		}
		return uploadPath;
	}

	/**
	 * 
	 * @Description: 得到上传后的图片地址。
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 李 欢
	 * @date 2013-6-5 下午5:28:05
	 * @version V1.0
	 */
	public static String showStaticFilesPath(HttpServletRequest request) {
		// 返回图片显示路径
		String showImages = "";
		// 获得主机名
		String serverName = request.getServerName();
		// 如果是本机，则按原来那样放置，在本地工程内即可
		if ("localhost".equals(serverName) || "127.0.0.1".equals(serverName)) {
			showImages = request.getContextPath()+"/uploads/resourses/";
		}else if("192.168.102.207".equals(serverName) || "192.168.102.207:8082".equals(serverName) ||"192.168.102.207:8085".equals(serverName) || "220.181.188.246".equals(serverName)|| "220.181.188.246:8082".equals(serverName)|| "220.181.188.246:8085".equals(serverName)){
			showImages = "http://192.168.102.207:8888/";//因测试环境上传图片只能上传到服务器对应地址，不能上传到203。现在在207配置下图片显示路径 
		}else if ("192.168.102.213".equals(serverName)) {// 说明是预发环境服务器
			showImages = "http://192.168.102.213:8888/";
		}else {// 默认是正式系统
			showImages = UrlCommon.image_url+"/";
		}
		return showImages;
	}
	public static String showWeichatFilesPath(HttpServletRequest request) {
		// 返回图片显示路径
		String showImages = "";
		// 获得主机名
		String serverName = request.getServerName();
		// 如果是本机，则按原来那样放置，在本地工程内即可
		if ("localhost".equals(serverName) || "127.0.0.1".equals(serverName)) {
			showImages = "http://"+serverName+":"+request.getLocalPort()+"/uploads/resourses/";
		} else if ("192.168.102.213".equals(serverName) ||"192.168.102.212".equals(serverName) || "192.168.102.204".equals(serverName) || "192.168.102.207".equals(serverName) || "220.181.188.246".equals(serverName)) {// 说明是测试服务器
			showImages = "http://"+serverName+":"+request.getLocalPort()+"/sfiles/";
		} else {// 默认是正式系统
			showImages = UrlCommon.image_url+"/";
		}
		return showImages;
	}
	

}
