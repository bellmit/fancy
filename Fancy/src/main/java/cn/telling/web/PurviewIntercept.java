package cn.telling.web;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import cn.telling.utils.StringHelperTools;


/**
 * @Title: PurviewIntercept.java
 * @Package cn.telling.weshop.acion
 * @Description: 组织权限拦截器
 * @author wuzheng
 * @date 2015-4-22 上午9:57:47
 * @version V1.0
 */
public class PurviewIntercept extends HandlerInterceptorAdapter {

	private final UrlPathHelper urlPathHelper = new UrlPathHelper();

	// 不需要登录验证的url
	private static String[] NOT_NEED_CHECK_ACCESS_URLS = { "/login.html", "/validate.html" };

	/**
	 * 
	 * @Description: 在进入全部Action之前进行拦截处理（即处理请求之前）。
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author wuzheng
	 * @date 2015-4-22 上午9:57:47
	 * @version V1.0
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 获取访问url
		String url = urlPathHelper.getLookupPathForRequest(request);
		String queryString = urlPathHelper.getOriginatingQueryString(request);
		String redirectURL = url;
		if (!StringHelperTools.isEmpty(queryString)) {
			redirectURL = redirectURL + "?" + queryString;
		}
		// 取当前用户
		// 特别注意：session.setAttribute("userId", obj)，存什么类型，取的时候就用什么类型来取
		Integer curUserId = (Integer) request.getSession().getAttribute("userId");
		// 访问地址在需要登录列表中并且session中userId不存在，进行跳转处理
		if (!contains(NOT_NEED_CHECK_ACCESS_URLS, url) && (null == curUserId || "".equals(curUserId))) {
			// 跳转到登录页面
			response.sendRedirect(request.getContextPath() + "/login.html?redirectURL="
					+ URLEncoder.encode(redirectURL, "UTF-8"));
			return false;
		}
		// 下面这句话不要动，就这样放着。你在处理你的业务逻辑之后，spring会将你的请求和响应继续往容器传或者往客户端进行传递
		return super.preHandle(request, response, handler);
	}

	/**
	 * 判断某个字符串是否存在于数组中
	 * 
	 * @param stringArray
	 *            原数组
	 * @param source
	 *            查找的字符串
	 * @return 是否找到
	 */
	public static boolean contains(String[] stringArray, String source) {
		// 转换为list
		List<String> tempList = Arrays.asList(stringArray);
		// 利用list的包含方法,进行判断
		if (tempList.contains(source)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 模糊判断某个字符串是否存在于数组中
	 * 
	 * @param stringArray
	 *            原数组
	 * @param source
	 *            查找的字符串
	 * @return 是否找到
	 */
	public static boolean containsExt(String[] stringArray, String source) {
		if (stringArray != null && stringArray.length > 0) {
			for (int i = 0; i < stringArray.length; i++) {
				if (source.contains(stringArray[i])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @Description: 在进入全部Action之后进行拦截处理（即在业务处理器处理请求执行完成后,生成视图之前执行的动作 ）。
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 李 欢
	 * @date 2013-4-8 上午10:03:04
	 * @version V1.0
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 你自己的业务逻辑
		// /下面这句话不要动，就这样放着。你在处理你的业务逻辑之后，spring会将你的请求和响应继续往容器传或者往客户端进行传递
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 
	 * @Description: 在现实视图之后进行处理（在DispatcherServlet完全处理完请求后被调用）
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 李 欢
	 * @date 2013-4-8 上午10:03:32
	 * @version V1.0
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 你自己的业务逻辑
		// //下面这句话不要动，就这样放着。你在处理你的业务逻辑之后，spring会将你的请求和响应继续往容器传或者往客户端进行传递
		super.afterCompletion(request, response, handler, ex);
	}
}
