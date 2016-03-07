package cn.telling.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.support.lob.DefaultLobHandler;

import cn.telling.utils.StringHelperTools;


/**
 * @Title: AutoInjection.java
 * @Package com.maylink.util
 * @Description: 用于自动注入数值。支持ResultSet、HttpServletRequest
 * @author guohui
 * @date 2011-11-28 下午2:04:49
 * @version V1.0
 */
public class AutoInjection {
	// 记录日志
	protected static final Logger logger = Logger.getLogger(AutoInjection.class);

	@Autowired
	@Qualifier("lobHandler")
	private static DefaultLobHandler lobHandler;

	/**
	 * 
	 * @Description: <li>用于将结果集中的数据注入到Vo中，建议：时间类型使用String，数字类型一律使用 BigDecimal（含：int、double、float等）</li> <li>
	 *               注意：必须保证数据库的字段与Vo中的字段 【名称】一致（字段大小写不限，Vo属性写法请遵守javabean规范。对象属性数量不限）</li> <li>
	 *               目前支持：大字段、字符串、Number类型转换；如果没有大字段请在第三个参数传入 ：null</li> <li>
	 *               数据库的日期类型q将以字符串形式保存到vo，所以在查询的时候请在sql中使用to_char()函数</li> <li>
	 *               varchar2、char 将以字符串类型保存到vo</li> <li>
	 *               Number将以BigDecimal类型保存到vo</li> <li>其余类型一律以字符串保存到vo</li>
	 * @param ResultSet 结果集；Object 对象Vo
	 * @return 无
	 * @exception 异常描述
	 * @date 2011-11-28 下午2:07:11
	 */
	public static boolean Rs2Vo(ResultSet rs, Object obj) {

		try {
			// 取得所有vo的属性和方法
			Map<String, String> objInfoMap = getObjectInfo(obj);
			// 取得结果集中的字段和数值
			Map<String, String> rsInfoMap = getResulstInfo(rs);
			// 进入注入
			toInjection(obj, rs, objInfoMap, rsInfoMap);
		}
		catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		catch (SecurityException e) {
			logger.error(e.getMessage(), e);
		}
		catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), e);
		}
		catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e);
		}
		catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		}
		catch (InvocationTargetException e) {
			logger.error(e.getMessage(), e);
		}
		return true;
	}

	/**
	 * 
	 * @Description: 自动注值工具类
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @author guohui
	 * @date 2011-11-28 下午5:20:44
	 * @version V1.0
	 */
	@SuppressWarnings("unchecked")
	private static void toInjection(Object obj, ResultSet rs, Map<String, String> objInfoMap,
			Map<String, String> rsInfoMap) throws SQLException, SecurityException,
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		// 遍历结果集属性map
		Set<Map.Entry<String, String>> setRS = rsInfoMap.entrySet();
		Iterator<Map.Entry<String, String>> itRS = setRS.iterator();
		// 匹配后跳到的断点标签
		lable:
		// 遍历结果集
		while (itRS.hasNext()) {
			Map.Entry<String, String> entryRS = (Map.Entry<String, String>)itRS.next();
			// 结果集字段名称
			String rsName = entryRS.getKey();
			// 结果集类型
			String rsType = entryRS.getValue();
			// 遍历对象属性map
			Set<Map.Entry<String, String>> setObj = objInfoMap.entrySet();
			Iterator<Map.Entry<String, String>> itObj = setObj.iterator();
			while (itObj.hasNext()) {
				Map.Entry<String, String> entryObj = (Map.Entry<String, String>)itObj.next();
				// 对象属性名称
				String objName = entryObj.getKey();
				// 判断时候有下层
				String[] names = rsName.split("\\.");
				// 对象属性类型
				String objType = entryObj.getValue();
				// 如果匹配(对象属性名称和字段名称)
				if (rsName.equalsIgnoreCase(objName)) {// 如果字段类型为CHAR或者VARCHAR2
					// 进行注入
					reflectInjection(rs, obj, objName, objType, rsName, rsType);
					// 删除匹配上的对象属性
					itObj.remove();
					// 删除已匹配上的字段
					itRS.remove();
					// 跳到lable,回到结果集继续循环
					continue lable;
				}
				else if (names.length == 3 && objName.equals(names[1]) && "Map".equals(objType)) {
					StringBuffer sb = new StringBuffer();
					sb.append("get");
					sb.append(objName.substring(0, 1).toUpperCase());
					sb.append(objName.substring(1, objName.length()));
					Method getMethod = obj.getClass().getDeclaredMethod(sb.toString());
					Map<String, Object> map = (Map<String, Object>)getMethod.invoke(obj);
					Object innerObj = null;
					if (!map.containsKey(names[0])) {
						try {
							Field mapField = obj.getClass().getDeclaredField(objName);
							Type mapType = mapField.getGenericType();
							if (mapType instanceof ParameterizedType) {
								// 执行强制类型转换
								ParameterizedType parameterizedType = (ParameterizedType)mapType;
								// 获取泛型类型的泛型参数
								Type[] types = parameterizedType.getActualTypeArguments();
								innerObj = ((Class<?>)types[1]).getConstructor().newInstance();
							}
						}
						catch (InstantiationException e) {
							logger.error(e.getMessage(), e);
						}
						catch (NoSuchFieldException e) {
							logger.error(e.getMessage(), e);
						}
						map.put(names[0], innerObj);
					}
					innerObj = map.get(names[0]);
					if (innerObj != null) {
						String innerName = names[2];
						String innerType = getObjectInfo(innerObj).get(innerName);
						// 进行注入
						reflectInjection(rs, innerObj, innerName, innerType, rsName, rsType);
						// 删除已匹配上的字段
						itRS.remove();
						// 跳到lable,回到结果集继续循环
						continue lable;
					}
				}
			}
		}

	}

	private static final String[] STRING_TYPE = { "VARCHAR2", "VARCHAR", "CHAR" };
	private static final String[] NUMBER_TYPE = { "NUMBER", "INTEGER", "INT", "DECIMAL", "BIGINT" };
	private static final String[] LOB_TYPE = { "BLOB", "CLOB", "TEXT" };
	private static final String[] DATE_TYPE = { "DATE", "TIMESTAMP", "DATETIME" };

	/**
	 * 
	 * @Description: 进行对象的反射拼装
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @author guohui
	 * @date 2011-11-28 下午5:18:45
	 * @version V1.0
	 */
	private static void reflectInjection(ResultSet rs, Object obj, String objName, String objType,
			String rsName, String rsType) throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			SQLException {

		StringBuffer sb = new StringBuffer();
		sb.append("set");
		sb.append(objName.substring(0, 1).toUpperCase());
		sb.append(objName.substring(1, objName.length()));
		Method setMethod = null;

		Class<?> clas = obj.getClass();
		if (StringHelperTools.containsIgnoreCase(STRING_TYPE, rsType)) {
			if ("String".equalsIgnoreCase(objType)) {// 如果对象属性类型为字符串
				// 进行反射注入
				setMethod = clas.getMethod(sb.toString(), String.class);
				setMethod.invoke(obj, rs.getString(rsName));
			}
		}
		else if (StringHelperTools.containsIgnoreCase(NUMBER_TYPE, rsType)) {
			if ("BigDecimal".equalsIgnoreCase(objType)) {
				setMethod = clas.getMethod(sb.toString(), BigDecimal.class);
				setMethod.invoke(obj, rs.getBigDecimal(rsName));
			}
			if ("Integer".equalsIgnoreCase(objType)) {
				setMethod = clas.getMethod(sb.toString(), Integer.class);
				setMethod.invoke(obj, rs.getInt(rsName));
			}
		}
		else if (StringHelperTools.containsIgnoreCase(LOB_TYPE, rsType) && lobHandler != null) {
			if ("String".equalsIgnoreCase(objType)) {
				setMethod = clas.getMethod(sb.toString(), String.class);
				setMethod.invoke(obj, lobHandler.getClobAsString(rs, rsName));
			}
		}
		else if (StringHelperTools.containsIgnoreCase(DATE_TYPE, rsType)) {
			if ("Date".equalsIgnoreCase(objType)) {
				setMethod = clas.getMethod(sb.toString(), Date.class);
				setMethod.invoke(obj, (Date) rs.getTimestamp(rsName));
			}
		}
		else {
			if ("String".equalsIgnoreCase(objType)) {
				// 进行反射注入
				setMethod = clas.getMethod(sb.toString(), String.class);
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
	 * @author guohui
	 * @date 2011-11-28 下午3:44:43
	 * @version V1.0
	 */
	private static Map<String, String> getObjectInfo(Object obj) {
		// 得到类型
		Class<?> cla = obj.getClass();
		return getObjectInfo(cla);
//		// 得到全部属性
//		Field[] fa = cla.getDeclaredFields();
//		// 返回Map
//		Map<String, String> map = new LinkedHashMap<String, String>();
//		for (int j = 0; j < fa.length; j++) {
//			Field field = fa[j];
//			Class<?> fieldClass = field.getType();
//			// 属性类型名称
//			String className = fieldClass.getSimpleName();
//			// 属性名称
//			String fieldName = field.getName();
//			// 放入map
//			map.put(fieldName, className);
//		}
//		for (int j = 0; j < fapu.length; j++) {
//			Field fieldpu = fapu[j];
//			Class<?> fieldClass = fieldpu.getType();
//			// 属性类型名称
//			String className = fieldClass.getSimpleName();
//			// 属性名称
//			String fieldName = fieldpu.getName();
//			// 放入map
//			map.put(fieldName, className);
//		}
	}
	
	private static Map<String, String> getObjectInfo(Class<?> clazz) {
		if (clazz == Object.class) {
			return new LinkedHashMap<String, String>();
		}
		else {
			Map<String, String> map = getObjectInfo(clazz.getSuperclass());

			Field[] flds = clazz.getDeclaredFields();
			for (Field fld : flds) {
				Class<?> fieldClass = fld.getType();
				map.put(fld.getName(), fieldClass.getSimpleName());
			}
			return map;
		}
	}

	/**
	 * 
	 * @Description: 根据结果集获得字段的名称和类型
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @author guohui
	 * @date 2011-11-28 下午3:50:12
	 * @version V1.0
	 */
	private static Map<String, String> getResulstInfo(ResultSet rs) throws SQLException {
		// 根据结果集获得元数据
		ResultSetMetaData rmd = rs.getMetaData();
		// 返回Map
		Map<String, String> map = new LinkedHashMap<String, String>();

		for (int i = 1; i <= rmd.getColumnCount(); i++) {
			// 数据库字段名称
			// String columnName = rmd.getColumnName(i);
			String columnName = rmd.getColumnLabel(i);
			// 数据库字段类型
			String columnType = rmd.getColumnTypeName(i);
			// 放入map
			map.put(columnName, columnType);
		}
		return map;
	}

}
