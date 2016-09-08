package cn.telling.common.uitl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import cn.telling.config.Global;
import cn.telling.constant.MessageCode;
/**
 * 短信接口工具类
 * @author dongfengda
 *
 */
public class SendMsgUtil {
	protected static final Logger logger = Logger.getLogger(SendMsgUtil.class);

	public static String sendSMSPost(String postData, String postUrl) {
		try {
			// 发送POST请求
			URL url = new URL(postUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setUseCaches(false);
			conn.setDoOutput(true);

			conn.setRequestProperty("Content-Length", "" + postData.length());
			OutputStreamWriter out = new OutputStreamWriter(
					conn.getOutputStream(), "UTF-8");
			out.write(postData);
			out.flush();
			out.close();

			// 获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				logger.error(">>>>>>>>>>>>>>>>>>>>>>>>短信平台连接失败！");
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

	public static String sendSMSGet(String paramData, String baseUrl) {
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
				logger.error(">>>>>>>>>>>>>>>>>>>>>>>>短信平台连接失败！");
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

	public static String sendSMSHL(String num, String con) {

		String postData;
		String ret = "";
		try {
			postData = "username=" +Global.getMsg(MessageCode.SMS_USER)
					+ "&password=" + Global.getMsg(MessageCode.SMS_PASS)
					+ "&phone=" + num + "&message="
					+ java.net.URLEncoder.encode(con, "gbk") + "&epid="
					+ Global.getMsg(MessageCode.SMS_EPID)
					+ "&linkid=&subcode=";
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>发送目标号码：" + num);
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>发送短信内容：" + con);
			ret = sendSMSGet(postData, Global.getMsg(MessageCode.SMS_URL));
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>短信平台返回：" + ret);
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>发送短信失败！");
			e.printStackTrace();
		}
		return ret;
	}
	
	public static String sendSMSGroup(String num, String con,String subcode) {
		
		String postData;
		String ret = "";
		String username = Global.getMsg(MessageCode.SMS_USER_GROUP);
		String password = Global.getMsg(MessageCode.SMS_PASS_GROUP);
		
		try {
			if(!"".equals(subcode)){
				username = subcode;
				password = Global.getMsg(MessageCode.SMS_PASS_SON);
			}
			postData = "username=" + username
					+ "&password=" + password
					+ "&phone=" + num + "&message="
					+ java.net.URLEncoder.encode(con, "gbk") + "&epid="
					+ Global.getMsg(MessageCode.SMS_EPID_GROUP)
					+ "&linkid=&subcode=";
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>发送目标号码：" + num);
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>发送短信内容：" + con);
			ret = sendSMSGet(postData, Global.getMsg(MessageCode.SMS_URL));
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>短信平台返回：" + ret);
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>发送短信失败！");
			e.printStackTrace();
		}
		return ret;
	}

	public static String sendSMSHLTellingMall(String phoneNum, String smsContent) {
		String postData;
		String ret = "";
		try {
			postData = "username=" + Global.getMsg(MessageCode.SMS_USER_TELLING_MALL)
					+ "&password=" + Global.getMsg(MessageCode.SMS_PASS_TELLING_MALL)
					+ "&phone=" + phoneNum + "&message="
					+ java.net.URLEncoder.encode(smsContent, "gbk") + "&epid="
					+ Global.getMsg(MessageCode.SMS_EPID_TELLING_MALL)
					+ "&linkid=&subcode=";
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>发送目标号码：" + phoneNum);
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>发送短信内容：" + smsContent);
			ret = sendSMSGet(postData, Global.getMsg(MessageCode.SMS_URL));
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>短信平台返回：" + ret);
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>发送短信失败！");
			e.printStackTrace();
		}
		return ret;
	}
}
