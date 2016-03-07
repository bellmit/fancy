package cn.telling.common;

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
	/****操圣删除日志记录***/
	/*	private static Logger logger = Logger.getLogger(PurviewIntercept.class);*/
	
	private UrlPathHelper urlPathHelper = new UrlPathHelper();

	/** 不需要登录验证的url **/
//	private static String[] NEED_NOT_CHECK_ACCESS_URLS = {
//			"/member/register.html","/member/register.html","/member/registerajax.html","/member/no_open.html",
//			"/member/getCity.html","/member/PhoneSendMsg.html","/member/login.html","/member/userLogin.html",
//			"/member/checkUserNameIsExists.html","/member/nologin.html","/member/validatePhone.html","/member/checkPhoneIsExists.html","/member/contactservice.html",
//			
//			};
	
	/***操圣删除--开始-***/
	/*	private static String[] NEED_CHECK_ACCESS_URLS = {
			"/member/center.html"
		};
	*/	

	/** 不需要openid的url **/
	/*	private static String[] NEED_NOT_OPENID_CHECK_ACCESS_URLS = {
		"/member/wxLogin.html",
		"/wechatsendmsg/","/wechatpush/"
		};*/
	
	/***操圣删除---结束***/
	
	
	/***操圣删除----删除微信签名工具类***/
	//	@Resource
	//	WechatConfig webChatConfig;

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
		/***操圣修改**/
		if (!StringHelperTools.isEmpty(queryString)&&!"/member/login.html".equals(url)
				/***操圣修改---删除微信登录判断开始***/
//				&&!"/member/wxLogin.html".equals(url)
				/***操圣修改---删除微信登录判断结束***/
				) {
			redirectURL = redirectURL + "?" + queryString;
		}
		

		// 取当前用户
		// 特别注意：session.setAttribute("userId", obj)，存什么类型，取的时候就用什么类型来取
//		BigDecimal curUserId = (BigDecimal) request.getSession().getAttribute("userId");
		
		/**操圣删除---从cookie里面取开始***/
//		String openId = StringHelperTools.nvl(request.getSession().getAttribute("openId"));
//		if("".equals(openId)){
//			Cookie cookies[] = request.getCookies();
//			if(cookies!=null){
//				for (int i = 0; i < cookies.length; i++) {
//					if (cookies[i].getName().equals("openId")) {
//						openId = StringHelperTools.nvl(cookies[i].getValue());
//						request.getSession().setAttribute("openId", openId);
//						break;
//					}
//				}
//			}
//		}
		/**操圣删除---从cookie里面取 结束***/
		//openId = "oiFCvs_DdBMOJIKQPK8JvU1oRyEM";
		//request.getSession().setAttribute("openId", openId);
		// openid不存在 跳转取openid
		
		/**操圣删除---如果openid为空并且访问的不是允许访问的网址就获取openid***/
	/*	if(!containsExt(NEED_NOT_OPENID_CHECK_ACCESS_URLS, url) && "".equals(openId)){
			String wxLogin = "http://" + webChatConfig.getDomain() + "/TellingMallWechat/member/wxLogin.html?redirectURL="+redirectURL;
			String wxStr;
			try {
				wxStr = URLEncoder.encode(wxLogin, "utf-8");
				String appId = webChatConfig.getAppId();
				String wxurl =
						"https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=" + wxStr
								+ "&response_type=code&scope=snsapi_base#wechat_redirect";
				response.sendRedirect(wxurl);
				return false;
			} catch (UnsupportedEncodingException e) {
				logger.error("不支持的编码类型：" + e.getMessage());
			} catch (IOException e) {
				logger.error("访问微信接口网站出现异常：" + e.getMessage());
			}
		}*/
		/**操圣删除---结束***/
		
		// 访问地址在需要登录列表中并且session中userId不存在，进行跳转处理
		
/*		if (!containsExt(NEED_NOT_CHECK_ACCESS_URLS, url) && ("".equals(curUserId) || null == curUserId)
				*//***操圣删除---openid判断开始***//*
//				&& !"".equals(openId)
				*//***操圣删除---openid判断结束***//*
				) {
				// 跳转到登录页面
				response.sendRedirect(request.getContextPath()
						+ "/member/login.html?redirectURL="+URLEncoder.encode(redirectURL, "UTF-8"));
				return false;
		}*/
	
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
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
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
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 你自己的业务逻辑

		// //下面这句话不要动，就这样放着。你在处理你的业务逻辑之后，spring会将你的请求和响应继续往容器传或者往客户端进行传递
		super.afterCompletion(request, response, handler, ex);
	}
}
