package cn.telling.common.uitl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import cn.telling.utils.StringHelperTools;
/**
 * 编码处理工具类
 * @author XUEST
 *
 */

public class EncodingUtil {
	/**
	 * 解决参数中有中文乱码的问题，可以将参数在Action中进行重新编码
	 * @param parm
	 * @return
	 */
	public static String Encode2UTF(String parm) {
		if (parm == null)
			return "";
		try {
			parm = new String(parm.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return parm;
	}

	/**
	 * 
	 * @Description: TODO 描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @date 2011-12-6 上午11:11:29
	 * @version V1.0
	 */
	public static String Encode4Str(String parm) {
		if (parm == null)
			return "";
		byte[] currArray = parm.getBytes();
		try {
			parm = new String(currArray, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return parm;
	}

	/**
	 * 
	 * @Description: 将一个带有汉字的字符串转化为网页上可以用的url 即：在jsp上进行转码。
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 李欢
	 * @date 2011-11-30 下午3:55:05
	 * @version V1.0
	 */
	public static String getStrURL(String strURL) {
		String retURL = "";
		try {
			retURL = URLEncoder.encode(strURL, "utf-8");
		} catch (Exception e) {
		}
		return retURL;
	}

	/**
	 * 
	 * @Description: 将前台转码后的URL进行解码。
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 李欢
	 * @date 2011-12-5 下午8:09:11
	 * @version V1.0
	 */
	public static String getDeCodeURL(String strURL) {
		String retURL = "";
		try {
			retURL = URLDecoder.decode(strURL, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retURL;
	}
	/**
	 * 对字符串进行base64编码，主要用于网页汉字拼url
	 * @param s 待编码字符串
	 * @return 编码字符串
	 */
    public static String encodeBase64(String s) {
		s = StringHelperTools.nvl(s);
		s = new BASE64Encoder().encode(s.getBytes());
		try {
			s = URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return s;
	}

	/**
	 * 对字符串进行base64解码
	 * 
	 * @param s 待解码字符串
	 * @return 解码字符串
	 */
	public static String decodeBase64(String s) {
		String res = "";
		s = StringHelperTools.nvl(s);
		BASE64Decoder base64 = new BASE64Decoder();
		try {
			res = new String(base64.decodeBuffer(s));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return res;
	}

	/**
	 * 对字符串进行base64编码，主要用于网页汉字拼url
	 * @param o 待编码对象
	 * @return 编码字符串
	 */
	public static String encodeBase64(Object o){
		return encodeBase64((String)o); 
	}

	/**
	 * 对字符串进行base64解码
	 * 
	 * @param o 待解码对象
	 * @return 解码字符串
	 */
	public static String decodeBase64(Object o){
		return decodeBase64((String)o);
	} 

	/**
	 * 对GBK字符串进行转码成UTF-8
	 * @param str 待解码字符串
	 * @return 字符串
	 * @throws Exception 
	 */
	public static String strGBKtoUtf8(String str) throws Exception {

		String toStr = null;

		if (str != null) {
			try {
				toStr = new String(str.getBytes("gbk"), "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		return toStr;
	}
	/**
	 * 把ISO-8859-1码转换成UTF-8
	 * 
	 * @param sISO
	 * @param sDBEncoding
	 * @return 字符串
	 */
	public static String ISOConvertUTF(String sISO, String sDBEncoding) {

		String sUTF;
		try {
			if (sISO == null || sISO.equals("")) {
				return "";
			} else {
				sISO = sISO.trim();
				sUTF = new String(sISO.getBytes(sDBEncoding), "GBK");
				return sUTF;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	/**
	 * 把UTF-8码转换成ISO-8859-1
	 * 
	 * @param sUTF
	 * @param sDBEncoding
	 * @return 字符串
	 */
	public static String UTFConvertISO(String sUTF, String sDBEncoding) {

		String sISO;
		try {
			if (sUTF == null || sUTF.equals("")) {
				return "";
			} else {
				sUTF = sUTF.trim();
				sISO = new String(sUTF.getBytes("GBK"), sDBEncoding);
				return sISO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	/**
	 * 取得数据库的编码方式。
	 * 
	 * 每次大规模转码之前调用一次。
	 * 
	 * @return 字符串
	 */
	public static String getDBEncoding() {
		String sDBEncoding = "ISO-8859-1";
		return sDBEncoding;
	}
	
	/**
	 * 把字符转换为Unicode(&#x????)编码
	 * 
	 * @param 转换前字符
	 * @return 转换后字符
	 */
	public static String toUnicodeX(String str) {
		char[] arChar = str.toCharArray();
		int iValue = 0;
		String uStr = "";
		for (int i = 0; i < arChar.length; i++) {
			iValue = (int) str.charAt(i);
			if (iValue <= 256) {
				uStr += "&#x00" + Integer.toHexString(iValue) + ";";
			}
			else {
				uStr += "&#x" + Integer.toHexString(iValue) + ";";
			}
		}
		return uStr;
	}

	/**
	 * 把字符转换为Unicode(\\u????)编码
	 * 
	 * @param 转换前字符
	 * @return 转换后字符
	 */
	public static String toUnicodeU(String str) {
		char[] arChar = str.toCharArray();
		int iValue = 0;
		String uStr = "";
		for (int i = 0; i < arChar.length; i++) {
			iValue = (int) str.charAt(i);
			if (iValue <= 256) {
				uStr += "\\u00" + Integer.toHexString(iValue);
			}
			else {
				uStr += "\\u" + Integer.toHexString(iValue);
			}
		}
		return uStr;
	}
}
