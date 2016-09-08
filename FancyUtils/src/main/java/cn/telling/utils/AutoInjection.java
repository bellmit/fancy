package cn.telling.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.support.lob.OracleLobHandler;

/**
 * @Title: AutoInjection.java
 * @Package cn.com.cits.tools.common
 * @Description: 用于自动注入数值。支持ResultSet、HttpServletRequest
 * @author 李欢
 * @date 2011-11-28 下午2:04:49
 * @version V1.0
 */
@SuppressWarnings("deprecation")
public class AutoInjection {
	/**
	 * 
	 * @Description: <li>用于将结果集中的数据注入到Vo中，建议：时间类型使用String，数字类型一律使用
	 *               BigDecimal（含：int、double、float等）</li> <li>
	 *               注意：必须保证数据库的字段与Vo中的字段
	 *               【名称】一致（字段大小写不限，Vo属性写法请遵守javabean规范。对象属性数量不限）</li> <li>
	 *               目前支持：大字段、字符串、Number类型转换；如果没有大字段请在第三个参数传入 ：null</li> <li>
	 *               数据库的日期类型q将以字符串形式保存到vo，所以在查询的时候请在sql中使用to_char()函数</li> <li>
	 *               varchar2、char 将以字符串类型保存到vo</li> <li>
	 *               Number将以BigDecimal类型保存到vo</li> <li>其余类型一律以字符串保存到vo</li>
	 * @param ResultSet
	 *            结果集；Object 对象Vo
	 * @return 无
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 李欢
	 * @date 2011-11-28 下午2:07:11
	 */
	public static boolean Rs2Vo(ResultSet rs, Object obj,
			@SuppressWarnings("deprecation") OracleLobHandler oracleLobHandler) {

		try {
			// 取得所有vo的属性和方法
			Map<String, String> objInfoMap = getObjectInfo(obj);
			// 取得结果集中的字段和数值
			Map<String, String> rsInfoMap = getResulstInfo(rs);
			//进入注入
			toInjection(obj, rs, oracleLobHandler, objInfoMap, rsInfoMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 
	 * @Description: 自动注值工具类
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 李欢
	 * @date 2011-11-28 下午5:20:44
	 * @version V1.0
	 */
	private static void toInjection(Object obj, ResultSet rs,
			OracleLobHandler oracleLobHandler, Map<String, String> objInfoMap,
			Map<String, String> rsInfoMap) throws SQLException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		// 遍历结果集属性map
		Set<Map.Entry<String, String>> set = rsInfoMap.entrySet();
		Iterator<Map.Entry<String, String>> it = set.iterator();
		// 匹配后跳到的断点标签
		lable:
		// 遍历结果集
		while (it.hasNext()) {
			Map.Entry<String, String> entryObject = (Map.Entry<String, String>) it
					.next();
			// 结果集字段名称
			String objName = entryObject.getKey();
			// 结果集类型
			String objType = entryObject.getValue();
			// 遍历对象属性map
			Set<Map.Entry<String, String>> setDB = objInfoMap.entrySet();
			Iterator<Map.Entry<String, String>> itDB = setDB.iterator();
			while (itDB.hasNext()) {
				Map.Entry<String, String> entryRS = (Map.Entry<String, String>) itDB.next();
				// 对象属性名称
				String rsName = entryRS.getKey();
				// 对象属性类型
				String rsType = entryRS.getValue();
				// 如果匹配(对象属性名称和字段名称)
				if (objName.equalsIgnoreCase(rsName)) {// 如果字段类型为CHAR或者VARCHAR2
					// 进行注入
					reflectInjection(rs, obj, rsName, rsType, objName, objType,oracleLobHandler);
					// 删除匹配上的对象属性
					itDB.remove();
					// 删除已匹配上的字段
					it.remove();
					// 跳到lable,回到结果集继续循环
					continue lable;
				}
			}
		}

	}

	/**
	 * 
	 * @Description: 进行对象的反射拼装
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 李欢
	 * @date 2011-11-28 下午5:18:45
	 * @version V1.0
	 */
	private static void reflectInjection(ResultSet rs, Object obj,
			String objName, String objType, String rsName, String rsType,
			OracleLobHandler oracleLobHandler) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, SQLException {

		Class<?> clas = obj.getClass();
		StringBuffer sb = new StringBuffer();
		sb.append("set");
		sb.append(objName.substring(0, 1).toUpperCase());
		sb.append(objName.substring(1, objName.length()));
		Method setMethod = null;

		if ("VARCHAR2".equalsIgnoreCase(rsType)|| "CHAR".equalsIgnoreCase(rsType)) {
			if ("String".equalsIgnoreCase(objType)) {// 如果对象属性类型为字符串
				// 进行反射注入
				setMethod = clas.getDeclaredMethod(sb.toString(), String.class);
				setMethod.invoke(obj, rs.getString(rsName));
			}
		} else if ("NUMBER".equalsIgnoreCase(rsType)||"INTEGER".equalsIgnoreCase(rsType)) {
			if ("BigDecimal".equalsIgnoreCase(objType)) {
				setMethod = clas.getDeclaredMethod(sb.toString(),BigDecimal.class);
				setMethod.invoke(obj,rs.getBigDecimal(rsName));
			}

		} else if ("CLOB".equalsIgnoreCase(rsType) && oracleLobHandler != null) {
			if ("String".equalsIgnoreCase(objType)) {
				setMethod = clas.getDeclaredMethod(sb.toString(), String.class);
				setMethod.invoke(obj,oracleLobHandler.getClobAsString(rs, rsName));
			}
		} else {
			if ("String".equalsIgnoreCase(objType)) {
				// 进行反射注入
				setMethod = clas.getDeclaredMethod(sb.toString(), String.class);
				setMethod.invoke(obj, rs.getString(rsName));
			}
		}

	}

	/**
	 * 
	 * @Description: 利用反射原理得到该对象的全部属性以及属性类型
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 李欢
	 * @date 2011-11-28 下午3:44:43
	 * @version V1.0
	 */

	private static Map<String, String> getObjectInfo(Object obj) {
		// 得到类型
		Class<?> cla = obj.getClass();
		// 得到全部属性
		Field[] fa = cla.getDeclaredFields();
		// 返回Map
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int j = 0; j < fa.length; j++) {
			Field field = fa[j];
			Class<?> fieldClass = field.getType();
			// 属性类型名称
			String className = fieldClass.getSimpleName();
			// 属性名称
			String fieldName = field.getName();
			// 放入map
			map.put(fieldName, className);
		}
		return map;
	}

	/**
	 * 
	 * @Description: 根据结果集获得字段的名称和类型
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 李欢
	 * @date 2011-11-28 下午3:50:12
	 * @version V1.0
	 */
	private static Map<String, String> getResulstInfo(ResultSet rs)
			throws SQLException {
		// 根据结果集获得元数据
		ResultSetMetaData rmd = rs.getMetaData();
		// 返回Map
		Map<String, String> map = new LinkedHashMap<String, String>();

		for (int i = 1; i <= rmd.getColumnCount(); i++) {
			// 数据库字段名称
			String columnName = rmd.getColumnName(i);
			// 数据库字段类型
			String columnType = rmd.getColumnTypeName(i);
			// 放入map
			map.put(columnName, columnType);
		}
		return map;
	}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@172.100.1.15:1521:b2btest1", "citsonline",
				"cits3011");

		Statement stm = conn.createStatement();
		String sql = "select * from route_baseinfo ";
		ResultSet rs = stm.executeQuery(sql);

		ResultSetMetaData rmd = rs.getMetaData();
		for (int i = 1; i <= rmd.getColumnCount(); i++) {
			System.out.println(rmd.getColumnName(i) + " == "
					+ rmd.getColumnTypeName(i) + " == " + rmd.getPrecision(i)
					+ " ==1 " + rmd.getScale(i));
		}
		int i = 0;
		if (!rs.next()) {
			return;
		}

		while (rs.next()) {
			System.out.println(rs.getString("SUPPLY_ID"));
			i++;
			if (i == 20) {
				break;
			}
		}
	}

	// getObjectInfo(new Test());

}

