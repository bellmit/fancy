package cn.telling.common.uitl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import cn.telling.constant.MessageCode;
import cn.telling.utils.MessageUtil;
import cn.telling.utils.StringHelperTools;

/**
 * 微信推送消息接口工具类
 * 
 * @author dongfengda
 *
 */
public class WechatSendMsgUtil {
	protected static final Logger logger = Logger
			.getLogger(WechatSendMsgUtil.class);

	public static String sendWSMGet(String paramData, String baseUrl) {
		try {
			// 发送GET请求
			String getUrl = baseUrl + "?" + paramData;
			URL url = new URL(getUrl);
			System.out.println(getUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.connect();

			// 获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				logger.error(">>>>>>>>>>>>>>>>>>>>>>>>微信消息推送失败！");
				return "";
			}
			// 获取响应内容体
			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			return result;
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return "";
	}
	
	/**
	 * @Description:微信推送
	 * @param String
	 *            paramData, String baseUrl
	 * @return 返回值
	 * @author dongfengda
	 * @date 2015/05
	 * @version V1.0
	 */
	private static void sendWSM(String paramData, String baseUrl) {
		Thread th = new Thread(new SendThread(StringHelperTools.nvl(paramData),
				StringHelperTools.nvl(baseUrl)));
		th.start();
	}
	
	private static class SendThread implements Runnable {
		private final String paramData;
		private final String baseUrl;

		public SendThread(String paramData, String baseUrl) {
			this.paramData = paramData;
			this.baseUrl = baseUrl;
		}

		@Override
		public void run() {
			try {
				sendWSMGet(paramData, baseUrl);
			} catch (Exception e) {
				System.out.println("微信消息推送>>>>>>>失败！");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 微信推送短信 退款通知
	 * 用户useid
	 * 退款原因
	 * 退款金额
	 */
	public static String wsm4returnmoney(String userid,String reason,String refund) {
		String postData;
		String ret = "";
		try {
			postData = "userid=" + userid
					+ "&reason=" + java.net.URLEncoder.encode(reason, "utf-8")
					+ "&refund=" + java.net.URLEncoder.encode(refund, "utf-8");
			sendWSM(postData,MessageUtil.getMsg(MessageCode.WSM_URL)+"/TellingWechat/wechatsendmsg/returnmoney.html");
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>退款通知微信推送返回：" + ret);
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>退款通知微信推送返回失败！");
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 微信推送短信 订单支付成功
	 * 用户useid
	 * 支付金额
	 * 订单产品
	 */
	public static String wsm4ordersuccess(String userid,String orderMoneySum,String orderProductName) {
		String postData;
		String ret = "";
		try {
			postData = "userid=" + userid
					+ "&orderMoneySum=" + java.net.URLEncoder.encode(orderMoneySum, "utf-8")
					+ "&orderProductName=" + java.net.URLEncoder.encode(orderProductName, "utf-8");
			sendWSM(postData,MessageUtil.getMsg(MessageCode.WSM_URL)+"/TellingWechat/wechatsendmsg/ordersuccess.html");
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>退订单支付成功微信推送返回：" + ret);
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>订单支付成功微信推送返回失败！");
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 微信推送短信 登录成功提醒
	 * 用户useid
	 * 登录账号
	 * 登录时间
	 * 登陆设备 1pc2手机
	 */
	public static String wsm4loginsuccess(String userid,String loginname,String logintime,String logindevice) {
		String postData;
		String ret = "";
		try {
			postData = "userid=" + userid
					+ "&loginname=" + java.net.URLEncoder.encode(loginname, "utf-8")
					+ "&logintime=" + java.net.URLEncoder.encode(logintime, "utf-8")
					+ "&logindevice=" + java.net.URLEncoder.encode(logindevice, "utf-8");
			sendWSM(postData,MessageUtil.getMsg(MessageCode.WSM_URL)+"/TellingWechat/wechatsendmsg/loginsuccess.html");
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>退款通知微信推送返回：" + ret);
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>退款通知微信推送返回失败！");
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 微信推送短信 订单发货提醒
	 * 用户useid
	 * 订单编号
	 * 物流公司
	 * 物流单号
	 */
	public static String wsm4delivery(String userid,String ordercode,String expressname,String expressno) {
		String postData;
		String ret = "";
		try {
			postData = "userid=" + userid
					+ "&ordercode=" + java.net.URLEncoder.encode(ordercode, "utf-8")
					+ "&expressname=" + java.net.URLEncoder.encode(expressname, "utf-8")
					+ "&expressno=" + java.net.URLEncoder.encode(expressno, "utf-8");
			sendWSM(postData,MessageUtil.getMsg(MessageCode.WSM_URL)+"/TellingWechat/wechatsendmsg/delivery.html");
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>订单发货提醒微信推送返回：" + ret);
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>订单发货提醒微信推送返回失败！");
			e.printStackTrace();
		}
		return ret;
	}
}
