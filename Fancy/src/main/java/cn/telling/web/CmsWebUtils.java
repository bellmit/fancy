package cn.telling.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.telling.utils.Assert;

import com.alibaba.fastjson.JSONObject;

public class CmsWebUtils {
	public static final String SC_C_SUF_DY = "suffix_dy";
	public static final String SC_C_SUF_TP = "suffix_dy";
	public static final String SC_C_NOW_USER = "SC_USER";
	public static final String SC_C_VALIDATE_CODE = "SC_VALIDATE_CODE";

	private static ThreadLocal<HttpServletRequest> threadRequest = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> threadResponse = new ThreadLocal<HttpServletResponse>();

	public static void setRequest(HttpServletRequest request)
	{
		threadRequest.set(request);
	}

	public static void setResponse(HttpServletResponse response)
	{
		threadResponse.set(response);
	}

	public static HttpServletRequest getRequest()
	{
		return threadRequest.get();
	}

	public static HttpServletResponse getResponse()
	{
		return threadResponse.get();
	}

	public static HttpSession getSession()
	{
		return getRequest().getSession();
	}

	public static JSONObject request2Json(String... args)
	{
		HttpServletRequest request = CmsWebUtils.getRequest();
		if (Assert.isNull(request))
			return null;
		JSONObject json = new JSONObject();
		if (args == null || args.length == 0)
		{
			Enumeration<?> enumeration = request.getParameterNames();
			while (enumeration.hasMoreElements())
			{
				String key = (String) enumeration.nextElement();
				String[] values = request.getParameterValues(key);
				json.put(key, values.length > 1 ? values : values[0]);
			}
		} else
		{
			for (String key : args)
			{
				String[] values = request.getParameterValues(key);
				if (null == values || values.length == 0)
					continue;
				json.put(key, values.length > 1 ? values : values[0]);
			}
		}
		return json;
	}

	public static JSONObject getNowShop()
	{
		return null;
		// return getBean(ShopService.class).getNowShop();
	}

	public static JSONObject setNowUser(JSONObject json)
	{
		JSONObject oldJson = (JSONObject) getSession().getAttribute(SC_C_NOW_USER);
		getSession().setAttribute(SC_C_NOW_USER, json);
		return oldJson;
	}

	public static JSONObject getNowUser()
	{
		return (JSONObject) getSession().getAttribute(SC_C_NOW_USER);
	}

	public static String decodingGetMethod(String str)
	{
		if (Assert.notEmpty(str))
			try
			{
				return new String(str.getBytes("iso8859-1"), "utf-8");
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		return str;
	}
}
