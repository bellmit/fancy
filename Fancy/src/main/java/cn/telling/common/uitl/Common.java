package cn.telling.common.uitl;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.io.File;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;

import cn.telling.utils.StringHelperTools;
/**
 * 共通工具类
 * @author XUEST
 *
 */
public class Common {
	/**
	 * 
	 * @Description: TODO 按照指定规则判断，返回需要关联的分表号
	 * @param		ii 做判断分表编号   tableSum分表数量
	 * @return		返回指定分表号
	 * @author      薛松坛
	 * @date 2013-3-26 下午5:16:12 
	 * @version V1.0
	 */
	 public synchronized static String getTableNum(BigDecimal ii,int tableSum,String tableName){
		 int idnum = ii.intValue();
		 int tableNum = idnum%tableSum;
		 tableName = tableName+tableNum;
		 return tableName;
	 }
	/**
	 * 日志打印JDBCTemplate的SQL
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
	 * 检查是否为数字。可以包含小数点，但是小数点个数不能多于一个； 可以包含负号，但是不能只有负号而没有其他数字； 不允许包含逗号
	 * 
	 * @param s 被检查的字符串
	 * @return true 表示是数字, false 表示不是数字
	 */
	public static boolean isNumber(String s) {
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
	public static boolean isInteger(String s) {
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
	public static boolean checkMailAddress(String mail) {
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
	 * 格式化金额字符串
	 * 
	 * @param strPrice 原始金额字符串
	 * @return 金额字符串。空时返回html空格，其他返回“＄金额”
	 */
	public static String addMoney(String strPrice) {
		strPrice = StringHelperTools.nvl(strPrice).trim();
		if (strPrice.equals("")) {
			return "&nbsp;";
		} else {
			return "＄" + strPrice;
		}
	}



	/**
	 * 从交款信息表的支付备注中得到电子支付时，所输入的金额（出境，国内，包机 专用） <br>
	 * 
	 * @param str 金额串
	 * @return 金额。参数对象为null或""时，返回0.00
	 */
	public static String getMoney(String str) {

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
	 * 字符串拆分
	 * 
	 * @param sInputStr 字符串
	 * @param cDelimiter 拆分字符
	 * @return 字符串数组
	 */
	public static String[] split(String sInputStr, char cDelimiter) {
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
	 * 数值转换
	 * 
	 * @param o 数字对象
	 * @return NULL或空格，返回“0”
	 */
	public static String nvlnum(Object o) {
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
	public static String md5(String inStr) throws NoSuchAlgorithmException {
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
	public static boolean fileExist(String fileSrc) {
		File file = new File(fileSrc);
		return (file.exists());
	}




	
	

	/**
	 * 格式化int类型为String类型
	 * 
	 * @param iFormat 数值
	 * @return String 字符串。一位的int在个位前加0;否则返回int的String值
	 */
	public static String intFormatTOStr(int iFormat) {
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
	public static String AirCitysEnameOne(String strEname) {
		String strEnameOne = StringHelperTools.nvl(strEname).trim();
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
	public static String addRMBQFP(String strPrice) {
		strPrice = StringHelperTools.nvl(strPrice).trim();
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
					tempPrice = "," + strPriceZ.substring(strPriceZ.length() - (i + 1) * 3, strPriceZ.length() - i * 3) + tempPrice;
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
	public static String addMYQFP(String strPrice) {
		strPrice = StringHelperTools.nvl(strPrice).trim();
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
					tempPrice = "," + strPriceZ.substring(strPriceZ.length() - (i + 1) * 3, strPriceZ.length() - i * 3) + tempPrice;
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
	 * 得到字符串的实际长度
	 * 
	 * @param s 字符串
	 * @return 实际长度
	 */
	@SuppressWarnings("deprecation")
	public static int getStringWidth(String s) {
		System.setProperty("java.awt.headless", "true");
		Font font = new Font("Dialog", Font.PLAIN, 12);
		FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
		int iWidth = SwingUtilities.computeStringWidth(fontMetrics, s);
		return iWidth;
	}



	/**
	 * 按字体得到字符串的实际长度
	 * 
	 * @param s 字符串
	 * @param font 字体
	 * @return 实际长度
	 */
	@SuppressWarnings("deprecation")
	public static int getStringWidth(String s, Font font) {
		System.setProperty("java.awt.headless", "true");
		FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
		int iWidth = SwingUtilities.computeStringWidth(fontMetrics, s);
		return iWidth;
	}

	/**
	 * 按字体得到截取字符串并补足
	 * @param bottomText 字符串
	 * @param fixedWidth 要取得的字符串的长度
	 * @param addStr 补足的字字符串
	 * @param fontName 字体名称
	 * @param size　字体大小　
	 * @return
	 */
	public static String getFixedWidthString(String bottomText, int fixedWidth,String addStr,String fontName,int size) {

		if("".equals(StringHelperTools.nvl(bottomText))){
			return "";
		}
		
		int bottomTextWidth = getStringWidth(bottomText,fontName,size);//字符串宽度 

		if (bottomTextWidth > fixedWidth) { //check如果是
			while (bottomTextWidth > fixedWidth-2) {//每次length()减1
				bottomText = bottomText.substring(0, bottomText.length() - 1);
				bottomTextWidth = getStringWidth(bottomText);
			}
			bottomText += addStr;//将舍弃的部分变成"..."代替
		}
		return bottomText;

	}

	/**
	 * 按字体得到字符串的实际长度
	 * 
	 * @param s 字符串
	 * @param fontName 字体名称
	 * @param size 字体大小
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getStringWidth(String s,String fontName,int size) {
		System.setProperty("java.awt.headless", "true");
		Font font = new Font(fontName, Font.PLAIN, size);
		FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
		int iWidth = SwingUtilities.computeStringWidth(fontMetrics, s);
		return iWidth;
	}


	/**
	 * 对字符串按照长度换行
	 * 
	 * @param s 需要换行的字符传
	 * @param len 多长时，需要换行
	 * @return HTML字符串
	 */
	public static String autoChangeRow(String s, int len) {
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
	public static String autoChangeRowWithCSS(String s, int len) {
		String[] oldS = s.split(",");
		String[] newS = s.split(",");
		String strNewS = "";
		String strOldS = "";
		for(int i = 0 ; i < newS.length ; i++){
			if(newS[i].charAt(0) == '<'){
				newS[i] = newS[i].substring(newS[i].indexOf(">")+1,newS[i].indexOf("<",2));
			}
			strNewS += "," + newS[i];
		}
		strNewS = strNewS.substring(1);
		strNewS = autoChangeRow(strNewS,len);
		newS = strNewS.split(",");
		for(int i = 0 ; i < oldS.length ; i++){
			if(oldS[i].charAt(0) == '<'){
				oldS[i] = oldS[i].substring(0,oldS[i].indexOf(">")+1) + newS[i] + oldS[i].substring(oldS[i].indexOf("<",2),oldS[i].length());
			}else{
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
	public static String convertRmbToUsdRoundUp(String strRmbValue,String strRate){
		strRmbValue = nvlnum(strRmbValue);
		strRate = nvlnum(strRate);
		if("0".equals(strRate)){
			strRate = "1";
		}
		return nvlnum((new BigDecimal(strRmbValue).divide(new BigDecimal(strRate),0,BigDecimal.ROUND_CEILING)));
	}
	
	/**
	 * 计算其它币种转人民币，保留2位小数截取
	 * @param strOtherCur 	其它币种值
	 * @param strToRMBRate	对人民币汇率
	 * @return String
	 */
	public static BigDecimal convertToRmbRoundDown(BigDecimal strOtherCur,BigDecimal strToRMBRate){

		if("0".equals(nvlnum(strToRMBRate))){
			strToRMBRate = new BigDecimal(1);
		}
		return strOtherCur.multiply(strToRMBRate).setScale(2,BigDecimal.ROUND_DOWN);
	}

	/**
	 * 获取字符串中非GB2312字符
	 * 
	 * @param str 需要处理的字符串
	 * @return 字符串
	 */
	public static String getNotGB2312(String str) {
		str = StringHelperTools.nvl(str);
		char[] chars = str.toCharArray();
		String GB2312 = "";
		for (int i = 0; i < chars.length; i++) {
			try{
				byte[] bytes = ("" + chars[i]).getBytes("GB2312");
				if (bytes.length == 2) {
					int[] ints = new int[2];
					ints[0] = bytes[0] & 0xff;
					ints[1] = bytes[1] & 0xff;
					if (!(ints[0] >= 0xb0 && ints[0] <= 0xf7 && ints[1] >= 0xa1 && ints[1] <= 0xfe)) {
						GB2312 += chars[i];
					}
				}else{
					GB2312 += chars[i];
				}
			}catch(Exception e){
				GB2312 += chars[i];
				System.out.println("ERR====="+str);
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
    public static String subString(String string ,int num){
    	String returnValue = "";
    	if(string!=null){
        	int length = string.length();
        	
        	if(num>length){
        		returnValue = string;
        	}else{
        		returnValue = string.substring(length-num);
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
	 * @author zhonglifeng 2008-09-27
	 */
	public static String strReplace(String oldStr ,String str,int head,int end){
		StringBuffer result = new StringBuffer();
		if(oldStr==null||oldStr==""){
			return result.toString();
		}else if(head>end||end>oldStr.length()||head<0){
			return oldStr;
		}
		
		for(int i=0;i<head;i++){
			result.append(str);
		}
		
		result.append(oldStr.substring(head,end));
		
		for(int i=end;i<oldStr.length();i++){
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
	public static String escapeSql(String str) {
		if(str==null||str.equals("")){
			return str;
		}else{
			StringBuffer buf = new StringBuffer();
			for(int i=0;i<str.length();i++){
				char c = str.charAt(i);
					switch(c){   
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
	
	public static String escapeSql4Search(String str) {
		if(str==null||str.equals("")){
			return StringHelperTools.nvl(str);
		}else{
			StringBuffer buf = new StringBuffer();
			for(int i=0;i<str.length();i++){
				char c = str.charAt(i);
					switch(c){   
					//将 ' 转换为 ''
					case '\'':
						buf.append(" ");
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
	public static  String getTargetStr(String sourceStr){
		StringBuffer buf = new StringBuffer();
		int tabSIndex = sourceStr.indexOf("<table>");
      	int tabEIndex = sourceStr.indexOf("</table>");
      	if(tabSIndex == -1 || tabEIndex == -1){
      		return sourceStr;
      	}
      	String headStr = sourceStr.substring(0,tabSIndex);
      	headStr = headStr.replaceAll("\r\n","<br>");
      	String middleStr = sourceStr.substring(tabSIndex+1,tabEIndex);
      	String endStr = sourceStr.substring(tabEIndex+1,sourceStr.length()-1);
      	buf.append(headStr);
      	buf.append(middleStr);
      	if(endStr.indexOf("<table>") != -1){
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
	public static String subString2(String input,int lettersNum){
    	
    	if(input == null || input.trim() == ""){
    		return "";
    	}
    	String tmpStr = input.trim();
    	
    	if(tmpStr.length() * 2<= lettersNum){
    		return tmpStr;
    	}
    	
    	int num = 0;
    	String temp = "";
    	for(int i=0; i<tmpStr.length() && num < lettersNum; i++){
    		if(tmpStr.substring(i, i+1).getBytes().length > 1){
    			num += 2;
    			temp = tmpStr.substring(0, i+1);
    		}else{
    			num += 1;
    			temp = tmpStr.substring(0, i+1);
    		}
    		
    	}
    	
    	if(temp.length() == tmpStr.length()){
    		return temp;
    	}else{
    		while(num > lettersNum - 2){
    			int i = temp.length();
    			
    			if(temp.substring(i-1, i).getBytes().length > 1){
    				num = num -2;
    			}else{
    				num = num - 1;
    			}
    			temp = temp.substring(0, i-1);
    		}
    		temp += "…";
    	}
    	
    	return temp;
    
	}
	
	/**
	 * 国内游出境游用
	 * @return
	 */
	public static String replaceImg(String src){
		if (src == null) {
			return "";
		}
		//System.out.println(src);
		src=src.replaceAll("<div class=\"detail_004_002_left1\"><img.+?</div>","");
		src=src.replaceAll("<div class=\"detail_004_002_left1\"></div>","");
		src=src.replaceAll("<div class='detail_004_002_left1'><img.+?</div>","");
		src=src.replaceAll("<div class='detail_004_002_left1'></div>","");
		src=src.replaceAll("line-height: 24px;margin-left: 201px;","line-height: 24px;margin-left: 15px;");
//		src=src.replaceAll("<img.+?/>","");
		return src;
	}
	
	/**
	 * 国内游出境游用
	 * @return
	 */
	public static String replaceImgIphone(String src){
		if (src == null) {
			return "";
		}
		//System.out.println(src);
		src=src.replaceAll("<div class=\"detail_004_002_left1\"><img.+?</div>","");
		src=src.replaceAll("<div class=\"detail_004_002_left1\"></div>","");
		src=src.replaceAll("<div class='detail_004_002_left1'><img.+?</div>","");
		src=src.replaceAll("<div class='detail_004_002_left1'></div>","");
		src=src.replaceAll("line-height: 24px;margin-left: 201px;","line-height: 24px;margin-left: 15px;");
		src=src.replaceAll("<img.+?/>","");
		return src;
	}
	/**
	 * 按单位给字符串加换行
	 * @param sourceStr
	 * @param cutUnit 字符串的单位
	 * @return
	 */
	public static String addBr(String sourceStr,int cutUnit){
		if(sourceStr == null || cutUnit <= 0){
			return "";
		}else{
			int len = sourceStr.length();
			if(len <= cutUnit){
				return sourceStr;
			}else{
				String targetStr = "";
				int strCount = len/cutUnit;
				int raminder = len%cutUnit;
				for(int i=0;i<strCount;i++){
					String frontSubStr = sourceStr.substring(cutUnit*i, cutUnit*(i+1));
					if((i == (strCount -1)) && (raminder == 0)){
						targetStr += frontSubStr;
					}else{
						targetStr += frontSubStr + "<br>";
					}
				}
				if(raminder != 0){
					targetStr += sourceStr.substring(cutUnit*strCount, len);
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
	public static String cutStringByByte(String sourceStr,int byteLen){
		if(sourceStr == null ) return "";
		String targetStr = sourceStr;
		byte[] sourceByte = sourceStr.getBytes();
    	if(sourceByte.length > byteLen){
    		targetStr = new String(sourceByte,0,byteLen);
    	}  
		return targetStr;
	}
	/**
	 * 去除文本中的html标记
	 * @param htmlStr
	 * @return
	 * */
	public static String delHTMLTag(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
        
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 
        
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
        
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 
       
        return htmlStr.trim(); //返回文本字符串 
    } 
	/**
	 * 把英文全角半角转化为汉语全角半角
	 * @param strName
	 * @return
	 * */
	public static String strReplace(String strName){ 
		if(strName!=null){
			int dyhCount = 0;
			for (int i = 0; i < strName.length(); i++) {
				char str = strName.charAt(i);
				if(String.valueOf(str).equals("'")){
					dyhCount++;
				}
			}
			int syhCount = 0;
			for (int i = 0; i < strName.length(); i++) {
				char str = strName.charAt(i);
				if(String.valueOf(str).equals("\"")){
					syhCount++;
				}
			}
			for (int i = 0; i <dyhCount; i++) {
				if(i%2==0){
					strName = strName.replaceFirst("'", "‘"); 
				}else{
					strName = strName.replaceFirst("'", "’");
				}
			}
			for (int i = 0; i <syhCount; i++) {
				if(i%2==0){
					strName = strName.replaceFirst("\"", "“"); 
				}else{
					strName = strName.replaceFirst("\"", "”");
				}
			}
		}
		return StringHelperTools.nvl(strName);
	}
		/**
		 * 去特殊字符
		 * @param key
		 * @return
		 */
		public static String tripString(String key){
			String resultKey = key;
			String[] spec ={"#","!","%",">","<","~","`","^","&","_","+","\\","/","?",".",";",":","\"","'"};
			for (int i = 0; i < spec.length; i++) {
		        if (resultKey.indexOf(spec[i]) >= 0) { //此处进行验证判断是否存在指定的字符
		        	
		        	resultKey = resultKey.replace(spec[i], ""); 
		        }
		   }
			return resultKey;
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
		 * 将 单引号 ' 替换为 /'
		 * @param str
		 * @return
		 */
		public static String DanYinHao(String str) {
			return str.replaceAll("/'", "////'");
		}
		/**
		 * 去除大字段前的特殊字符
		 */
		public static String fixString(String input) {
			if(input!=null)
			{
				return input.replaceAll("'", "‘").replaceAll("\"", "“");
			}
			else
			{
				return null;
			}
			
		}

}
