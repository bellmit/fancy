package cn.telling.common;

import javax.servlet.http.HttpServletRequest;

/**
 * 网站URL全局变量
 */
public class UrlCommon {
	/**网通站域名统一声明**/
	public static String domain_url = "www.tianlian.com";
	/**网通站域名统一声明+http://**/
	public static String domain_url_http = "http://www.tianlian.com";
	//网站图片服务地址
	public static String image_url = "http://image.tianlian.com" ; 
	/**
	 * 
	 * @Description: 动态请求路径
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      李 欢
	 * @date 2013-6-7 下午7:31:51 
	 * @version V1.0
	 */
	public static String ContextPath(HttpServletRequest request){
		return request.getContextPath();
	}
	/**
	 * 
	 * @Description:静态文件请求路径
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      李 欢
	 * @date 2013-6-7 下午7:32:06 
	 * @version V1.0
	 */
	
	public static String getStaticFilePath(HttpServletRequest request){
		return request.getContextPath();
	}
}
