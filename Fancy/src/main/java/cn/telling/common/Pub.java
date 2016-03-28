package cn.telling.common;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import sun.misc.BASE64Encoder;
import cn.telling.common.Vo.DealResultLogInterfaceVO;
import cn.telling.common.Vo.IBaseVO;
import cn.telling.utils.UUIDUtil;

public class Pub {

	private static Logger logger = Logger.getLogger(Pub.class);

	/**
	 * md5加密
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
    public static String encodeByMD5(String str) throws Exception {
		// 确定计算方法
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		// 加密后的字符串
		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newstr;
	}

	/***
	 * 小写
	 * 
	 * @param str
	 * @return
	 */
	public static String getStringtoLower(String str) {
		return str.toLowerCase();
	}

	/***
	 * 大写
	 * 
	 * @param str
	 * @return
	 */
	public static String getStringtoUpper(String str) {
		return str.toUpperCase();
	}

	/***
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String firstLetterToUpper(String str) {
		char[] array = str.toCharArray();
		array[0] -= 32;
		return String.valueOf(array);
	}

	/***
	 * 首字母小写
	 * 
	 * @param str
	 * @return
	 */
	public static String firstLetterToLower(String str) {
		char[] array = str.toCharArray();
		array[0] += 32;
		return String.valueOf(array);
	}

	/***
	 * 得到字段对的类型
	 * 
	 * @param vo
	 * @return
	 */
	public static Map<String, String> getVOFieldType(Object vo) {
		Map<String, String> fieldMap = new LinkedHashMap<String, String>();
		List<String> retEmpty = new ArrayList<String>();
		Class<?> clazz = vo.getClass();
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			String methordName = methods[i].getName();
			if (methods[i].getReturnType().getName().equalsIgnoreCase("void")) {
				retEmpty.add((String) methordName.subSequence(3,
						methordName.length()));
			}
		}
		Field[] fa = clazz.getDeclaredFields();
		for (int j = 0; j < fa.length; j++) {
			Class<?> fieldClass = fa[j].getType();
			// 属性类型名称
			String className = fieldClass.getSimpleName();
			// 属性名称
			String fieldName = Pub.firstLetterToUpper(fa[j].getName());
			if (retEmpty.contains(fieldName)) {
				fieldMap.put(fieldName, className);
			}
		}
		return fieldMap;
	}

	/*** 当前时间 **/
	public static String getCurrTime() throws Exception {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(cal.getTime());
		return str;
	}

	/**
	 * 利用反射注入值
	 * 
	 * @param clazz
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	// public static BaseVO setBaseValue(Class<?> clazz,ResultSet rs) throws
	// Exception{
	public static IBaseVO setBaseValue(IBaseVO vo, ResultSet rs)
			throws Exception {
		Class<?> clazz = vo.getClass();
		// IBaseVO vo = (IBaseVO)clazz.newInstance();
		Map<String, String> map = Pub.getVOFieldType(vo);
		int i = 0;
		for (String key : map.keySet()) {
			String fieldType = map.get(key);// 字段类型
			if ("INT".equalsIgnoreCase(fieldType)
					|| fieldType.equalsIgnoreCase("BigDecimal")) {
				Method m = clazz.getMethod("set" + key,
						new Class[] { BigDecimal.class });
				m.invoke(vo, new Object[] { rs.getBigDecimal(i + 1) });
			} else {
				Method m = clazz.getMethod("set" + key,
						new Class[] { String.class });
				m.invoke(vo, new Object[] { rs.getString(i + 1) });
			}
			i++;
		}
		return vo;
	}

	/***
	 * 获取要查询的字段
	 * 
	 * @param sql
	 * @param vo
	 * @return
	 */
	public static String getAllFields(Object vo, boolean flag) {
		String sql = "";
		Map<String, String> fieldMap = Pub.getVOFieldType(vo);
		Iterator<?> it = fieldMap.keySet().iterator();
		int a = 0;
		while (it.hasNext()) {
			String key = it.next().toString();
			sql += flag ? (key + " = ? ") : (key);
			if (a < fieldMap.size() - 1) {
				sql += ",";
			}
			a++;
		}
		return sql;
	}

	/**
	 * @param vo
	 * @param flag
	 * @return
	 */
	public static String getAllFieldValues(Object vo, boolean flag) {
		String sql = "";
		Map<String, String> fieldMap = Pub.getVOFieldType(vo);
		Iterator<?> it = fieldMap.keySet().iterator();
		int a = 0;
		while (it.hasNext()) {
			String key = it.next().toString();
			sql += flag ? ("  ? ") : (key);
			if (a < fieldMap.size() - 1) {
				sql += ",";
			}
			a++;
		}
		return sql;
	}

	/**
	 * vo更新操作
	 * 
	 * @param vo
	 * @param stmt
	 * @throws Exception
	 */
	public static void updateBaseValue(IBaseVO vo, PreparedStatement stmt)
			throws Exception {
		Map<String, String> fieldMap = Pub.getVOFieldType(vo);
		int i = 0;
		for (String key : fieldMap.keySet()) {
			Method m = vo.getClass().getMethod("get" + key, new Class[0]);
			Object obj = m.invoke(vo, new Object[0]);
			if (fieldMap.get(key).equalsIgnoreCase("INT")
					|| fieldMap.get(key).equalsIgnoreCase("BigDecimal")) {
				stmt.setBigDecimal(i + 1, new BigDecimal(obj.toString()));
			} else {
				stmt.setString(i + 1, obj == null ? null : obj.toString());
			}
			i++;
		}
	}

	/***
	 * 
	 * @param vo
	 * @param stmt
	 * @param keys
	 * @param a
	 * @throws Exception
	 */
	public static void updateBaseValues(IBaseVO vo, PreparedStatement stmt,
			String[] keys, int a) throws Exception {
		Map<String, String> fieldMap = Pub.getVOFieldType(vo);
		int i = 0;
		for (String key : fieldMap.keySet()) {
			logger.debug("_________________字段_____________" + key);
			Method m = vo.getClass().getMethod("get" + key, new Class[0]);
			Object obj = m.invoke(vo, new Object[0]);
			String primarykey = UUIDUtil.getUUID();
			if (vo.getPrimaryKey() != null
					&& vo.getPrimaryKey().equalsIgnoreCase(key)) {
				if (obj == null) {// ||Integer.parseInt(obj.toString())==0
					keys[a] = primarykey;// getID();
					logger.debug("_________________得到主键_____________" + keys[a]);
					stmt.setString(i + 1, keys[a]);
				} else {
					// stmt.setInt(i+1, Integer.parseInt(obj.toString()));
					stmt.setString(i + 1, (String)obj);
				}
				Method m1 = vo.getClass().getMethod("set" + key,
						new Class[] { String.class });
				m1.invoke(vo, new Object[] { primarykey });
				logger.debug("_________________得到主键：" + primarykey);
			} else {
				if (fieldMap.get(key).equalsIgnoreCase("BigDecimal")
						|| fieldMap.get(key).equalsIgnoreCase("INT")) {
					stmt.setBigDecimal(i + 1, new BigDecimal(obj.toString()));
				} else {
					stmt.setString(i + 1, (String) obj);
				}
			}
			i++;
		}
	}

	public static void main(String[] args) {
		String str = "我是中国人";
		System.out.println("中文首字母：" + chinaInitial(str, true).substring(0, 3));
		int a=20;
		System.out.println(a-(str.length()));
		System.out.println("store".toUpperCase());
	}

	/**
	 * 返回首字母
	 * 
	 * @param strChinese
	 * @param bUpCase
	 * @return
	 */
	public static String chinaInitial (String strChinese, boolean bUpCase) {
		try {
			StringBuffer buffer = new StringBuffer();
			byte b[] = strChinese.getBytes("GBK");// 把中文转化成byte数组
			for (int i = 0; i < b.length; i++) {
				if ((b[i] & 255) > 128) {
					int char1 = b[i++] & 255;
					char1 <<= 8;// 左移运算符用“<<”表示，是将运算符左边的对象，向左移动运算符右边指定的位数，并且在低位补零。其实，向左移n位，就相当于乘上2的n次方
					int chart = char1 + (b[i] & 255);
					buffer.append(getPYIndexChar((char) chart, bUpCase));
					continue;
				}
				char c = (char) b[i];
				if (!Character.isJavaIdentifierPart(c))// 确定指定字符是否可以是 Java
														// 标识符中首字符以外的部分。
					c = 'A';
				buffer.append(c);
			}
			return buffer.toString();
		} catch (Exception e) {
			System.out.println((new StringBuilder())
					.append("\u53D6\u4E2D\u6587\u62FC\u97F3\u6709\u9519")
					.append(e.getMessage()).toString());
		}
		return null;
	}

	/**
	 * 得到首字母
	 * 
	 * @param strChinese
	 * @param bUpCase
	 * @return
	 */
	private static char getPYIndexChar(char strChinese, boolean bUpCase) {
		int charGBK = strChinese;
		char result;
		if (charGBK >= 45217 && charGBK <= 45252)
			result = 'A';
		else if (charGBK >= 45253 && charGBK <= 45760)
			result = 'B';
		else if (charGBK >= 45761 && charGBK <= 46317)
			result = 'C';
		else if (charGBK >= 46318 && charGBK <= 46825)
			result = 'D';
		else if (charGBK >= 46826 && charGBK <= 47009)
			result = 'E';
		else if (charGBK >= 47010 && charGBK <= 47296)
			result = 'F';
		else if (charGBK >= 47297 && charGBK <= 47613)
			result = 'G';
		else if (charGBK >= 47614 && charGBK <= 48118)
			result = 'H';
		else if (charGBK >= 48119 && charGBK <= 49061)
			result = 'J';
		else if (charGBK >= 49062 && charGBK <= 49323)
			result = 'K';
		else if (charGBK >= 49324 && charGBK <= 49895)
			result = 'L';
		else if (charGBK >= 49896 && charGBK <= 50370)
			result = 'M';
		else if (charGBK >= 50371 && charGBK <= 50613)
			result = 'N';
		else if (charGBK >= 50614 && charGBK <= 50621)
			result = 'O';
		else if (charGBK >= 50622 && charGBK <= 50905)
			result = 'P';
		else if (charGBK >= 50906 && charGBK <= 51386)
			result = 'Q';
		else if (charGBK >= 51387 && charGBK <= 51445)
			result = 'R';
		else if (charGBK >= 51446 && charGBK <= 52217)
			result = 'S';
		else if (charGBK >= 52218 && charGBK <= 52697)
			result = 'T';
		else if (charGBK >= 52698 && charGBK <= 52979)
			result = 'W';
		else if (charGBK >= 52980 && charGBK <= 53688)
			result = 'X';
		else if (charGBK >= 53689 && charGBK <= 54480)
			result = 'Y';
		else if (charGBK >= 54481 && charGBK <= 55289)
			result = 'Z';
		else
			result = (char) (65 + (new Random()).nextInt(25));
		if (!bUpCase)
			result = Character.toLowerCase(result);
		return result;
	}
	
	/**
	 * 解析欧维特返回报文字符串
	 * @param returnxml
	 * @return
	 * @throws Exception
	 */
	public static boolean parseReturnXml(String returnxml) throws Exception{
		boolean result=false;
		if("".equals(returnxml)){
			return false;
		}
		String validateResult="";
		StringReader sr = new StringReader(returnxml); 
		InputSource is = new InputSource(sr); 
		is.setEncoding("UTF-8");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder=factory.newDocumentBuilder(); 
		Document doc = builder.parse(is);
		NodeList list=doc.getElementsByTagName("result");
		if(list.getLength()==0){
			validateResult=validateResult+"欧维特返回报文格式错误！";
		}else if(list.getLength()>1){
			validateResult=validateResult+"欧维特返回报文格式错误！";
		}else{
			if("0".equals(StringUtils.trimToEmpty(list.item(0).getTextContent()))){
				return true;
			}else{
				return false;
			}
		}
		return result;
	}
	
	
	/***
	 * 必输项赋值为空时为“”
	 * @param custom
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void mustFieldsConvers(Object vo,String[] fields) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		for(String field:fields){
			field=Pub.firstLetterToUpper(field);
			Method m = vo.getClass().getMethod("get"+field, new Class[0]);
			 Object obj = m.invoke(vo, new Object[0]);
			 if(obj==null){
				m = vo.getClass().getMethod("set"+field, new Class[]{String.class});
				m.invoke(vo, new Object[]{""});
			 }
		}
	}

	/**
	 * 解析欧维特返回报文 存入DB
	 * @param returnxml
	 * @return
	 * @throws Exception
	 */
	public static DealResultLogInterfaceVO processReturnXml(
			String returnxml, String intoUUID, String msg) throws Exception {
		DealResultLogInterfaceVO reLog = null;
		StringReader sr = new StringReader(returnxml); 
		InputSource is = new InputSource(sr); 
		is.setEncoding("UTF-8");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder=factory.newDocumentBuilder(); 
		Document doc = builder.parse(is);
		NodeList resultlist=doc.getElementsByTagName("result");
		NodeList msglist=doc.getElementsByTagName("msg");
		if("0".equals(StringUtils.trimToEmpty(resultlist.item(0).getTextContent()))){
			reLog=new DealResultLogInterfaceVO(intoUUID,"B2B","WMS","成功",msg);
		}else{
			reLog=new DealResultLogInterfaceVO(intoUUID,"B2B","WMS","处理异常",msglist.item(0).getTextContent());
		}
		return reLog;
	}
			
	
}
