package cn.telling.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 功能、用途、现存BUG: 帮助实现一些通用的字符串处理
 *
 * @author
 * @version 1.0.0
 * @see 需要参见的其它类
 * @since 1.0.0
 */
public class StringHelperTools extends StringUtils
{

	/**
	 * null值转换
	 * @param args
	 * @return 返回转换后数组
	 */
	public static Object[] nullToString(Object args[])
	{
		for (int i = 0; i < args.length; i++) {
			if (args[i] == null || "null".equals(args[i])) {
				args[i] = "";
			}
		}
		return args;
	}
	/**
	 * 判断字符串是否在定义的字符串数组中存在，不区分大小写。目标字符串会trim后比较
	 * 
	 * @param stringArray 定义的字符串数组
	 * @param source 目标字符串
	 * @return 是否包含
	 */
	public static boolean containsIgnoreCase(String[] stringArray, String source) {
		if (stringArray == null || stringArray.length == 0 || source == null) {
			return false;
		}
		for (int i = 0; i < stringArray.length; i++) {
			if (stringArray[i].equalsIgnoreCase(source.trim())) {
				return true;
			}
		}
		return false;
	}
	
	

	/**
	 * null值转换
	 * @param args
	 * @return 返回转换后数组
	 */
	public static Object[] STringnullToString(Object args[])
	{
		for (int i = 0; i < args.length; i++) {
			if (args[i] == null) {
				args[i] = "";
			}
		}
		return args;
	}

	/**
	 * NULL字符串转换，参数对象为null时，返回空字符串
	 * 
	 * @param o 转换原对象
	 * @return 字符串
	 */
	public static String nvl(Object o)
	{
		if (o == null) {
			return "";
		}
		return o.toString().trim();
	}

	/**
	 * NULL字符串转换，参数对象为null时，返回空字符串
	 * 
	 * @param o 转换原对象
	 * @return 字符串
	 */
	public static boolean isEmpty(Object[] p)
	{
		for (int i = 0; i < p.length; i++) {
			Object params = p[i];
			if (params != null) {
				if (params instanceof String && !params.equals("")) {
					return true;
				}
				if (params instanceof Integer && !params.equals(0)) {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * NULL字符串转换，参数对象为null时，返回空值为0的BigDecimal对象
	 * 
	 * @param o 转换原对象
	 * @return 字符串
	 */
	public static BigDecimal nvlToBigDecimal(Object o)
	{
		if (o == null) {
			return new BigDecimal(0);
		} else {
			return (BigDecimal) o;
		}
	}

	/**
	 * NULL字符串转换，参数对象为null时，返回空字符串
	 * 将" " 替换为 "&nbsp;" 用于页面显示多个空格
	 * @param o 转换原对象
	 * @return 字符串
	 */
	public static String nvlShow(Object o)
	{
		if (o == null) {
			return "";
		}
		return o.toString().trim().replaceAll(" ", "&nbsp;");
	}

	/**
	 * NULL字符串转换，参数对象为null时，返回默认值
	 * 
	 * @param o 转换原对象
	 * @param res 默认值
	 * @return 字符串
	 */
	public static String nvl(Object o, String res)
	{
		if (o == null) {
			return res;
		}
		return o.toString().trim();
	}

	/**
	 * NULL或空字符串转换，参数对象为null或空时，返回默认值
	 * 
	 * @param o 转换原对象
	 * @param res 默认值
	 * @return 字符串
	 */
	public static String nvlHtml(Object o, String res)
	{
		if (o == null || o.toString().trim().equals("")) {
			return res;
		}
		return o.toString().trim();
	}

	/**
	 * 字符串替换。如果不需要用正则表达式请用此方法； 否则用String.replaceAll(String regex, String replacement)
	 * 
	 * @param text 需要被处理的字符串
	 * @param from 需要被替换掉的字符串
	 * @param to 被替换成的字符串
	 * @return 被替换后的字符串
	 * @see String#replaceAll(String, String)
	 */
	public static String replace(String text, String from, String to)
	{
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
	 * 替换回车为br
	 * 
	 * @param text 原文本
	 * @return 替换后文本
	 */
	public static String replaceLineFeedCode(String text)
	{
		return replace(text, "\n", "<br>\n");
	}

	/**
	 * 替换\t为4个html空格
	 * 
	 * @param text 原文本
	 * @return 替换后文本
	 */
	public static String replaceTab24Space(String text)
	{
		return replace(text, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
	}

	/**
	 * 对html标签或特殊字符串编码
	 * 
	 * @param html html代码
	 * @return String 替换后代码
	 */
	public static String replaceQuotes(String html)
	{
		html = replace(html, "\"", "&quot;");
		return html;
	}

	/**
	 * 对html标签或特殊字符串编码
	 * 
	 * @param html html代码
	 * @return String 替换后代码
	 */
	public static String replaceAllQuotes(String html)
	{
		html = replace(html, "\"", "&quot;");
		html = replace(html, "\'", "&apos;");
		return html;
	}

	/**
	 * 对html中js中引用的字符（比如alert中）转换 单引号
	 * 
	 * @param html html代码
	 * @return String 替换后代码
	 */
	public static String replaceJsApos(String html)
	{
		html = replace(html, "'", "\\'");
		return html;
	}

	/**
	 * 对html中js中引用的字符（比如alert中）转换 单引号
	 * 
	 * @param html html代码
	 * @return String 替换后代码
	 */
	public static String replaceJsQuote(String html)
	{
		html = replace(html, "\"", "\\\"");
		return html;
	}

	/**
	 * 检查是否为数字。可以包含小数点，但是小数点个数不能多于一个； 可以包含负号，但是不能只有负号而没有其他数字； 不允许包含逗号
	 * 
	 * @param s 被检查的字符串
	 * @return true 表示是数字, false 表示不是数字
	 */
	public static boolean isNumber(String s)
	{
		boolean pointfirsttime = true;
		int i = 0;
		if (s == null || s.length() < 1) {
			return false;
		}
		boolean negative = false;
		if (s.charAt(0) == '-') {
			i++;
			negative = true;
		}
		while (i < s.length()) {
			if (!Character.isDigit(s.charAt(i))) {
				if ('.' == s.charAt(i) && pointfirsttime) {
					pointfirsttime = false;
				} else {
					return false;
				}
			}
			i++;
		}
		if (negative && (i == 1)) {
			return false;
		}
		return true;
	}

	/**
	 * 检查是否为整数。可以为负整数，但是不能只有负号而没有其他数字
	 * 
	 * @param s 被检查的字符串
	 * @return true 表示是整数, false 表示不是整数
	 */
	public static boolean isInteger(String s)
	{
		int i = 0;
		if (s == null || s.trim().length() < 1) {
			return false;
		}
		boolean negative = false;
		if (s.charAt(0) == '-') {
			i++;
			negative = true;
		}
		while (i < s.length()) {
			if (!Character.isDigit(s.charAt(i))) {
				return false;
			}
			i++;
		}
		if (negative && (i == 1)) {
			return false;
		}
		return true;
	}

	/**
	 * 检查是否为合法的Email
	 * 
	 * @param mail 字符串
	 * @return true 合法，false 非法
	 */
	public static boolean checkMailAddress(String mail)
	{
		if (mail == null) {
			return false;
		}
		String mailstr = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern pn = Pattern.compile(mailstr);
		boolean b = pn.matcher(mail).matches();
		System.out.println(b);
		return b;
	}

	/**
	 * 判断字符串是否为null或者trim后长度小于1
	 * 
	 * @param arg 要被判断的字符串
	 * @return true 为null或者trim后长度小于1
	 */
	public static boolean isEmpty(String arg)
	{
		if (arg == null || arg.trim().length() < 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据从REQUEST中取出的语种，得到数据库中定义的MESSAGE的语种。
	 * 
	 * @param langHeader 原语种代码
	 * @return 语种代码
	 */
	public static String getLangIDFromString(String langHeader)
	{
		String langId = "";
		final String CN = "CN";
		final String EN = "EN";
		if (langHeader == null) {
			langId = CN;
		} else if (langId.startsWith("zh")) {
			langId = CN;
		} else {
			langId = EN;
		}
		return CN;
	}

	/**
	 * 查找字符串
	 * 
	 * @param strSource 原始字符串
	 * @param strFrom 开始字符串
	 * @param strTo 结束字符串
	 * @return 字符串
	 */
	public static String find(String strSource, String strFrom, String strTo)
	{
		String strDest = "";
		int intFromIndex = strSource.indexOf(strFrom) + strFrom.length();
		int intToIndex = strSource.indexOf(strTo);
		if (intFromIndex < intToIndex) {
			strDest = strSource.substring(intFromIndex, intToIndex);
		}
		return strDest;
	}

	/**
	 * 格式化金额字符串
	 * 
	 * @param strPrice 原始金额字符串
	 * @return 金额字符串。空时返回html空格，其他返回“＄金额”
	 */
	public static String addMoney(String strPrice)
	{
		strPrice = nvl(strPrice).trim();
		if (strPrice.equals("")) {
			return "&nbsp;";
		} else {
			return "＄" + strPrice;
		}
	}

	/**
	 * 对html标签或特殊字符串编码
	 * 
	 * @param html html代码
	 * @return String 替换后代码
	 */
	public static String HtmlEncode(String html)
	{
		if (isEmpty(html)) {
			return html;
		}
		html = replace(html, "&", "&amp;");
		html = replace(html, "<", "&lt;");
		html = replace(html, ">", "&gt;");
		html = replace(html, "\n", "<br>");
		html = replace(html, "\"", "&quot;");
		return html;
	}

	/**
	 * 从交款信息表的支付备注中得到电子支付时，所输入的金额（出境，国内，包机 专用） <br>
	 * 
	 * @param str 金额串
	 * @return 金额。参数对象为null或""时，返回0.00
	 */
	public static String getMoney(String str)
	{
		if (str == null || str.trim().equals("")) {
			return "0.00";
		}
		int start = -1, end = str.length();
		for (int i = 0; i < str.length(); i++) {
			if (start == -1 && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				start = i;
			}
			if (start != -1 && !((str.charAt(i) >= '0' && str.charAt(i) <= '9') || str.charAt(i) == '.')) {
				end = i;
				break;
			}
		}
		return str.substring(start, end);
	}

	/**
	 * 空字符串转化为NULL
	 * 
	 * @param str 字符串
	 * @return 字符串
	 */
	public static final String str2Null(String str)
	{
		if (str == null || str != null && "".equals(str.trim()))
			return null;
		else
			return str.trim();
	}

	/**
	 * 字符串拆分
	 * 
	 * @param sInputStr 字符串
	 * @param cDelimiter 拆分字符
	 * @return 字符串数组
	 */
	public static String[] split(String sInputStr, char cDelimiter)
	{
		int iStrLen = sInputStr.length();
		int iTokCount = 0;
		if (0 == iStrLen)
			return null;
		for (int p = 0; p < iStrLen; p++)
			if (sInputStr.charAt(p) == cDelimiter)
				iTokCount++;
		String Tokens[] = new String[iTokCount + 1];
		int iToken = 0;
		int iLast = 0;
		for (int iNext = 0; iNext < iStrLen; iNext++) {
			if (sInputStr.charAt(iNext) == cDelimiter) {
				if (iLast == iNext)
					Tokens[iToken] = "";
				else
					Tokens[iToken] = sInputStr.substring(iLast, iNext);
				iLast = iNext + 1;
				iToken++;
			} // fi (sInputStr[iNext]==cDelimiter)
		} // next
		if (iLast >= iStrLen)
			Tokens[iToken] = "";
		else
			Tokens[iToken] = sInputStr.substring(iLast, iStrLen);
		return Tokens;
	} // split

	/**
	 * 增加日期
	 * 
	 * @param strDate 日期串"yyyy-MM-dd"
	 * @param add 天数
	 * @return 日期串
	 */
	public static String getAddDate(String strDate, int add)
	{
		// 返回日期
		String strReturn = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(strDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, add);
			strReturn = sdf.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strReturn;
	}

	/**
	 * 判断日期是否在今天之前
	 * 
	 * @param strDate 日期串"yyyy-MM-dd"
	 * @return 在今天之前，返回true，否则返回false
	 */
	public static boolean judgeBefore(String strDate)
	{
		// 返回值
		boolean blnReturn = true;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(strDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(new Date());
			blnReturn = c.before(c2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blnReturn;
	}

	/**
	 * 数值转换
	 * 
	 * @param o 数字对象
	 * @return NULL或空格，返回“0”
	 */
	public static String nvlnum(Object o)
	{
		if (o == null) {
			return "0";
		}
		if ("".equals(o.toString().trim())) {
			return "0";
		}
		return o.toString();
	}

	/**
	 * 对字符串作MD5加密处理
	 * 
	 * @param inStr 需要被处理的字符串
	 * @return 被处理后的字符串，被转换为16进制表示的字符串
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5(String inStr) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(inStr.getBytes());
		byte[] r = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < r.length; i++) {
			byte b = r[i];
			sb.append(Character.forDigit((b >> 4 & 0x0F), 16));
			sb.append(Character.forDigit((b & 0x0F), 16));
		}
		return sb.toString();
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param fileSrc 文件路径
	 * @return 判断结果
	 */
	public static boolean fileExist(String fileSrc)
	{
		File file = new File(fileSrc);
		return (file.exists());
	}

	//	/**
	//	 * 将字符串（包括汉字）分割成固定长度的字符串数组
	//	 * 
	//	 * @param strParamTarget 字符串
	//	 * @param nParamLen 固定长度
	//	 * @return 字符串数组
	//	 */
	//	public static String[] splitLength(String strParamTarget, int nParamLen) {
	//		Vector vctWork = new Vector();
	//		int nCharLen;
	//
	//		int nLen = 0;
	//
	//		int i;
	//		StringBuffer sbWork = new StringBuffer("");
	//		char cWork;
	//
	//		if (strParamTarget == null) {
	//
	//		} else {
	//
	//			for (i = 0; i < strParamTarget.length(); i++) {
	//
	//				cWork = strParamTarget.charAt(i);
	//
	//				if (String.valueOf(cWork).getBytes().length > 1) {
	//					nCharLen = 2;
	//				} else {
	//					nCharLen = 1;
	//				}
	//
	//				if ((nLen + nCharLen) > nParamLen) {
	//
	//					vctWork.addElement(sbWork.toString());
	//
	//					sbWork = new StringBuffer("");
	//					nLen = 0;
	//				}
	//
	//				nLen += nCharLen;
	//
	//				sbWork.append(cWork);
	//			}
	//
	//			vctWork.addElement(sbWork.toString());
	//		}
	//
	//		return (String[]) vctWork.toArray(new String[0]);
	//	}
	/**
	 * 国内机票儿童价和婴儿价格取得
	 * 
	 * @param yPrice 标准舱位价格
	 * @param flg 1:儿童；0：婴儿
	 * @return 价格
	 */
	public static String getSpcPrc(String yPrice, int flg)
	{
		double dYprice = Double.parseDouble(yPrice);
		if (flg == 1) {
			dYprice *= 0.5;
		} else {
			dYprice *= 0.1;
		}
		java.math.BigDecimal b = new java.math.BigDecimal(dYprice);
		java.math.BigDecimal ten = new java.math.BigDecimal("10");
		return String.valueOf(b.divide(ten, 0, java.math.BigDecimal.ROUND_HALF_UP).floatValue() * 10);
	}

	/**
	 * 国内机票儿童价和婴儿价格取得
	 * 
	 * @param adultPrice 成人价格
	 * @param yPrice 	 标准舱位价格
	 * @param bunkId 	 舱位ID
	 * @param flg 		 1:儿童；2：婴儿
	 * @return 			 价格
	 */
	public static String getGnPersonPrice(String adultPrice, String yPrice, String bunkId, String flg)
	{
		double dAdultPrice = Double.parseDouble(adultPrice);
		double dYprice = Double.parseDouble(yPrice);
		bunkId = nvl(bunkId).toUpperCase();
		if ("F".equals(bunkId) || "C".equals(bunkId) || dAdultPrice >= dYprice) {
			dYprice = dAdultPrice;
		}
		if (flg.equals("1")) {
			dYprice *= 0.5;
		} else {
			dYprice *= 0.1;
		}
		java.math.BigDecimal b = new java.math.BigDecimal(dYprice);
		java.math.BigDecimal ten = new java.math.BigDecimal("10");
		return String.valueOf(b.divide(ten, 0, java.math.BigDecimal.ROUND_HALF_UP).floatValue() * 10);
	}

	/**
	 * 对字符串进行base64编码，主要用于网页汉字拼url
	 * 
	 * @param s 待编码字符串
	 * @return 编码字符串
	 */
	public static String encodeBase64(String s)
	{
		s = nvl(s);
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
	public static String decodeBase64(String s)
	{
		String res = "";
		s = nvl(s);
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
	 * 
	 * @param o 待编码对象
	 * @return 编码字符串
	 */
	public static String encodeBase64(Object o)
	{
		return encodeBase64((String) o);
	}

	/**
	 * 对字符串进行base64解码
	 * 
	 * @param o 待解码对象
	 * @return 解码字符串
	 */
	public static String decodeBase64(Object o)
	{
		return decodeBase64((String) o);
	}

	/**
	 * 对GBK字符串进行转码成UTF-8
	 * 
	 * @param str 待解码字符串
	 * @return 字符串
	 * @throws Exception 
	 */
	public static String strGBKtoUtf8(String str) throws Exception
	{
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
	 * 格式化int类型为String类型
	 * 
	 * @param iFormat 数值
	 * @return String 字符串。一位的int在个位前加0;否则返回int的String值
	 */
	public static String intFormatTOStr(int iFormat)
	{
		String strFormat = String.valueOf(iFormat);
		if (strFormat.length() < 2) {
			strFormat = "0" + strFormat;
		}
		return strFormat;
	}

	/**
	 * 将传入的航空城市英文名字取第一个字母并大写
	 * 
	 * @param strEname 城市英文名字
	 * @return String 第一个字母
	 */
	public static String AirCitysEnameOne(String strEname)
	{
		String strEnameOne = nvl(strEname).trim();
		if (strEnameOne.length() > 0) {
			strEnameOne = strEnameOne.substring(0, 1).toUpperCase();
		}
		return strEnameOne;
	}

	/**
	 * 将传入的价格加人民币符号（￥）和千分撇（,）
	 * 
	 * @param strPrice 价格字符串
	 * @return 处理后字符串
	 */
	public static String addRMBQFP(String strPrice)
	{
		strPrice = nvl(strPrice).trim();
		// 负数标志
		String flag = "no";
		if (!"".equals(strPrice)) {
			if ("-".equals(strPrice.substring(0, 1))) {
				strPrice = strPrice.substring(1);
				flag = "yes";
			}
			int pointIndex = strPrice.indexOf(".");
			String strPriceZ = "";
			String strPriceX = "";
			if (pointIndex > 0) {
				strPriceZ = strPrice.substring(0, pointIndex);
				strPriceX = strPrice.substring(pointIndex);
			} else {
				strPriceZ = strPrice;
				strPriceX = "";
			}
			if (strPriceZ.length() >= 4) {
				int qfpNum = (strPriceZ.length() - (strPriceZ.length() % 3)) / 3;
				String tempPrice = "";
				for (int i = 0; i < qfpNum; i++) {
					tempPrice =
							"," + strPriceZ.substring(strPriceZ.length() - (i + 1) * 3, strPriceZ.length() - i * 3)
									+ tempPrice;
				}
				if (strPriceZ.length() % 3 == 0) {
					tempPrice = tempPrice.trim().substring(1);
				} else {
					tempPrice = strPriceZ.substring(0, strPriceZ.length() % 3) + tempPrice;
				}
				if ("yes".equals(flag)) {
					strPrice = "¥-" + tempPrice + strPriceX;
				} else {
					strPrice = "¥" + tempPrice + strPriceX;
				}
			} else {
				if (!"".equals(strPrice) && !".".equals(strPrice)) {
					if ("yes".equals(flag)) {
						strPrice = "¥-" + strPrice;
					} else {
						strPrice = "¥" + strPrice;
					}
				}
			}
		}
		return strPrice;
	}

	/**
	 * 将传入的美元加美元符号（$）和千分撇（,）
	 * 
	 * @param strPrice 价格字符串
	 * @return 处理后字符串
	 */
	public static String addMYQFP(String strPrice)
	{
		strPrice = nvl(strPrice).trim();
		// 负数标志
		String flag = "no";
		if (!"".equals(strPrice)) {
			if ("-".equals(strPrice.substring(0, 1))) {
				strPrice = strPrice.substring(1);
				flag = "yes";
			}
			int pointIndex = strPrice.indexOf(".");
			String strPriceZ = "";
			String strPriceX = "";
			if (pointIndex > 0) {
				strPriceZ = strPrice.substring(0, pointIndex);
				strPriceX = strPrice.substring(pointIndex);
			} else {
				strPriceZ = strPrice;
				strPriceX = "";
			}
			if (strPriceZ.length() >= 4) {
				int qfpNum = (strPriceZ.length() - (strPriceZ.length() % 3)) / 3;
				String tempPrice = "";
				for (int i = 0; i < qfpNum; i++) {
					tempPrice =
							"," + strPriceZ.substring(strPriceZ.length() - (i + 1) * 3, strPriceZ.length() - i * 3)
									+ tempPrice;
				}
				if (strPriceZ.length() % 3 == 0) {
					tempPrice = tempPrice.trim().substring(1);
				} else {
					tempPrice = strPriceZ.substring(0, strPriceZ.length() % 3) + tempPrice;
				}
				if ("yes".equals(flag)) {
					strPrice = "$-" + tempPrice + strPriceX;
				} else {
					strPrice = "$" + tempPrice + strPriceX;
				}
			} else {
				if (!"".equals(strPrice) && !".".equals(strPrice)) {
					if ("yes".equals(flag)) {
						strPrice = "$-" + strPrice;
					} else {
						strPrice = "$" + strPrice;
					}
				}
			}
		}
		return strPrice;
	}

	/**
	 * 对字符串按照长度换行
	 * 
	 * @param s 需要换行的字符传
	 * @param len 多长时，需要换行
	 * @return HTML字符串
	 */
	public static String autoChangeRow(String s, int len)
	{
		String sReurlt = "";
		for (int i = 0; i < s.length(); i = i + len) {
			if (i == 0) {
				sReurlt = s.substring(0, s.length() > len ? len : s.length());
			} else {
				sReurlt = sReurlt + "</br>";
				sReurlt = sReurlt + s.substring(i, s.length() > i + len ? i + len : s.length());
			}
		}
		return sReurlt;
	}

	/**
	 * 对字符串按照长度换行(当文字中有CSS样式时，适用)
	 * 
	 * @param s 需要换行的字符传
	 * @param len 多长时，需要换行
	 * @return HTML字符串
	 */
	public static String autoChangeRowWithCSS(String s, int len)
	{
		String[] oldS = s.split(",");
		String[] newS = s.split(",");
		String strNewS = "";
		String strOldS = "";
		for (int i = 0; i < newS.length; i++) {
			if (newS[i].charAt(0) == '<') {
				newS[i] = newS[i].substring(newS[i].indexOf(">") + 1, newS[i].indexOf("<", 2));
			}
			strNewS += "," + newS[i];
		}
		strNewS = strNewS.substring(1);
		strNewS = autoChangeRow(strNewS, len);
		newS = strNewS.split(",");
		for (int i = 0; i < oldS.length; i++) {
			if (oldS[i].charAt(0) == '<') {
				oldS[i] =
						oldS[i].substring(0, oldS[i].indexOf(">") + 1) + newS[i]
								+ oldS[i].substring(oldS[i].indexOf("<", 2), oldS[i].length());
			} else {
				oldS[i] = newS[i];
			}
			strOldS += "," + oldS[i];
		}
		strOldS = strOldS.substring(1);
		return strOldS;
	}

	/**
	 * 计算人民币转化为美元的值，向上取整
	 * @param strRmbValue 人民币值
	 * @param strRate	汇率
	 * @return String
	 */
	public static String convertRmbToUsdRoundUp(String strRmbValue, String strRate)
	{
		strRmbValue = nvlnum(strRmbValue);
		strRate = nvlnum(strRate);
		if ("0".equals(strRate)) {
			strRate = "1";
		}
		return nvlnum((new BigDecimal(strRmbValue).divide(new BigDecimal(strRate), 0, BigDecimal.ROUND_CEILING)));
	}

	/**
	 * 计算其它币种转人民币，保留2位小数截取
	 * @param strOtherCur 	其它币种值
	 * @param strToRMBRate	对人民币汇率
	 * @return String
	 */
	public static BigDecimal convertToRmbRoundDown(BigDecimal strOtherCur, BigDecimal strToRMBRate)
	{
		if ("0".equals(nvlnum(strToRMBRate))) {
			strToRMBRate = new BigDecimal(1);
		}
		return strOtherCur.multiply(strToRMBRate).setScale(2, BigDecimal.ROUND_DOWN);
	}

	/**
	 * 获取字符串中非GB2312字符
	 * 
	 * @param str 需要处理的字符串
	 * @return 字符串
	 */
	public static String getNotGB2312(String str)
	{
		str = nvl(str);
		char[] chars = str.toCharArray();
		String GB2312 = "";
		for (int i = 0; i < chars.length; i++) {
			try {
				byte[] bytes = ("" + chars[i]).getBytes("GB2312");
				if (bytes.length == 2) {
					int[] ints = new int[2];
					ints[0] = bytes[0] & 0xff;
					ints[1] = bytes[1] & 0xff;
					if (!(ints[0] >= 0xb0 && ints[0] <= 0xf7 && ints[1] >= 0xa1 && ints[1] <= 0xfe)) {
						GB2312 += chars[i];
					}
				} else {
					GB2312 += chars[i];
				}
			} catch (Exception e) {
				GB2312 += chars[i];
				System.out.println("ERR=====" + str);
				e.printStackTrace();
			}
		}
		return GB2312;
	}

	/**
	 * 取字符串的后几位，位数小于字符串，返回本身
	 * @param string
	 * @param num 获取的位数
	 * @return
	 */
	public static String subString(String string, int num)
	{
		String returnValue = "";
		if (string != null) {
			int length = string.length();
			if (num > length) {
				returnValue = string;
			} else {
				returnValue = string.substring(length - num);
			}
		}
		return returnValue;
	}

	/**
	 * 替代oldStr中head之前，end之后的字符串
	 * @param oldStr
	 * @param str
	 * @param head 
	 * @param end
	 * @return String
	 * 
	 * @author 
	 */
	public static String strReplace(String oldStr, String str, int head, int end)
	{
		StringBuffer result = new StringBuffer();
		if (oldStr == null || oldStr == "") {
			return result.toString();
		} else if (head > end || end > oldStr.length() || head < 0) {
			return oldStr;
		}
		for (int i = 0; i < head; i++) {
			result.append(str);
		}
		result.append(oldStr.substring(head, end));
		for (int i = end; i < oldStr.length(); i++) {
			result.append(str);
		}
		return result.toString();
	}

	/**
	 *
	 * 把字符串转换成合适的SQL查询语言,适用于  like  
	 * @param str 转换的字符串 - 可以为null
	 * @return String 返回转换后新的SQL文字符串，如果字符串为null就返回null
	 * 将用户传入的检索条件中的特殊字符进行转义
	 * <br/>
	 * 将 ' 转换 \'
	 * <br/>
	 * 将 " 转换 \"
	 * <br/>
	 * 将 % 转换 \%
	 * <br/>
	 * 用法 " like '"+name+"'" 改写为   " like '"+CmnUtFunc.escapeSql(name)+"'"
	 */
	public static String escapeSql(String str)
	{
		if (str == null || str.equals("")) {
			return str;
		} else {
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				switch (c) {
				//将 ' 转换为 ''
				case '\'':
					buf.append("''");
					break;
				//将 " 转换为 \"
				case '\"':
					buf.append("\"");
					break;
				//将 % 转换为 \%    
				case '%':
					buf.append("\\%");
					break;
				//将 $ 转换为 \$    
				case '$':
					buf.append("\\$");
					break;
				//将 \ 转换为 \\    
				case '\\':
					buf.append("\\\\");
					break;
				default:
					buf.append(c);
					break;
				}
			}
			return buf.toString();
		}
	}

	/**
	 * 把doc格式的字符串转换为html格式,但是对table保持原样
	 * @param sourceStr
	 * @return targetStr
	 */
	public static String getTargetStr(String sourceStr)
	{
		StringBuffer buf = new StringBuffer();
		int tabSIndex = sourceStr.indexOf("<table>");
		int tabEIndex = sourceStr.indexOf("</table>");
		if (tabSIndex == -1 || tabEIndex == -1) {
			return sourceStr;
		}
		String headStr = sourceStr.substring(0, tabSIndex);
		headStr = headStr.replaceAll("\r\n", "<br>");
		String middleStr = sourceStr.substring(tabSIndex + 1, tabEIndex);
		String endStr = sourceStr.substring(tabEIndex + 1, sourceStr.length() - 1);
		buf.append(headStr);
		buf.append(middleStr);
		if (endStr.indexOf("<table>") != -1) {
			buf.append(getTargetStr(endStr));
		}
		return buf.toString();
	}

	/**
	 * 截取字符串，输入字符串长度大于要截取的长度，则显示“…”
	 * @param input
	 * @param lettersNum 英文个数 ，一个中文占两个英文
	 * @return
	 */
	public static String subString2(String input, int lettersNum)
	{
		if (input == null || input.trim() == "") {
			return "";
		}
		String tmpStr = input.trim();
		if (tmpStr.length() * 2 <= lettersNum) {
			return tmpStr;
		}
		int num = 0;
		String temp = "";
		for (int i = 0; i < tmpStr.length() && num < lettersNum; i++) {
			if (tmpStr.substring(i, i + 1).getBytes().length > 1) {
				num += 2;
				temp = tmpStr.substring(0, i + 1);
			} else {
				num += 1;
				temp = tmpStr.substring(0, i + 1);
			}
		}
		if (temp.length() == tmpStr.length()) {
			return temp;
		} else {
			while (num > lettersNum - 2) {
				int i = temp.length();
				if (temp.substring(i - 1, i).getBytes().length > 1) {
					num = num - 2;
				} else {
					num = num - 1;
				}
				temp = temp.substring(0, i - 1);
			}
			temp += "…";
		}
		return temp;
	}

	/**
	 * 国内游出境游用
	 * @return
	 */
	public static String replaceImg(String src)
	{
		if (src == null) {
			return "";
		}
		//System.out.println(src);
		src = src.replaceAll("<div class=\"detail_004_002_left1\"><img.+?</div>", "");
		src = src.replaceAll("<div class=\"detail_004_002_left1\"></div>", "");
		src = src.replaceAll("<div class='detail_004_002_left1'><img.+?</div>", "");
		src = src.replaceAll("<div class='detail_004_002_left1'></div>", "");
		src = src.replaceAll("line-height: 24px;margin-left: 201px;", "line-height: 24px;margin-left: 15px;");
		//		src=src.replaceAll("<img.+?/>","");
		return src;
	}

	/**
	 * 国内游出境游用
	 * @return
	 */
	public static String replaceImgIphone(String src)
	{
		if (src == null) {
			return "";
		}
		//System.out.println(src);
		src = src.replaceAll("<div class=\"detail_004_002_left1\"><img.+?</div>", "");
		src = src.replaceAll("<div class=\"detail_004_002_left1\"></div>", "");
		src = src.replaceAll("<div class='detail_004_002_left1'><img.+?</div>", "");
		src = src.replaceAll("<div class='detail_004_002_left1'></div>", "");
		src = src.replaceAll("line-height: 24px;margin-left: 201px;", "line-height: 24px;margin-left: 15px;");
		src = src.replaceAll("<img.+?/>", "");
		return src;
	}

	/**
	 * 按单位给字符串加换行
	 * @param sourceStr
	 * @param cutUnit 字符串的单位
	 * @return
	 */
	public static String addBr(String sourceStr, int cutUnit)
	{
		if (sourceStr == null || cutUnit <= 0) {
			return "";
		} else {
			int len = sourceStr.length();
			if (len <= cutUnit) {
				return sourceStr;
			} else {
				String targetStr = "";
				int strCount = len / cutUnit;
				int raminder = len % cutUnit;
				for (int i = 0; i < strCount; i++) {
					String frontSubStr = sourceStr.substring(cutUnit * i, cutUnit * (i + 1));
					if ((i == (strCount - 1)) && (raminder == 0)) {
						targetStr += frontSubStr;
					} else {
						targetStr += frontSubStr + "<br>";
					}
				}
				if (raminder != 0) {
					targetStr += sourceStr.substring(cutUnit * strCount, len);
				}
				return targetStr;
			}
		}
	}

	/**
	 * 按字节截取字符串
	 * @param sourceStr
	 * @param byteLen
	 * @return
	 */
	public static String cutStringByByte(String sourceStr, int byteLen)
	{
		if (sourceStr == null)
			return "";
		String targetStr = sourceStr;
		byte[] sourceByte = sourceStr.getBytes();
		if (sourceByte.length > byteLen) {
			targetStr = new String(sourceByte, 0, byteLen);
		}
		return targetStr;
	}

	/***
	 * 判断字符串是否是数字
	 * @param str
	 * @return boolean
	 * */
	public static boolean isNum(String str)
	{
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	/**
	 * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2015-12-18 下午7:01:01 
	 * @version V1.0  
	*/
	
	
}
