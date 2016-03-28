/**
 * Project Desc:
 * Project Name:TestExample
 * File Name:Demo1.java
 * Package Name:util
 * Date:2015-5-6上午11:41:44
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
*/
package util;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:Demo1 <br/>
 * Date:     2015-5-6 上午11:41:44 <br/>
 * @author   caosheng
 */
public class Demo1
{

	/**
	* 把数据按一定的格式写到csv文件中
	* @param novels     数据集合
	* @param fileName  csv文件完整路径
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws IOException 
	*/
	public static void writeData2CSV(List<?> novels, String fileName)
	{
		Map<String, String> map = null;
		FileWriter fw = null;
		//		try {
		try {
			fw = new FileWriter(fileName);
			//","间隔,写完一行需要回车换行"rn"
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < novels.size(); i++) {
				Object vo = novels.get(i);
				Class<?> clz = vo.getClass();
				Method[] methods = clz.getDeclaredMethods();
				for (Method m : methods) {
					String mName = m.getName();
					if (mName.contains("get")) {
						Object rsObject = m.invoke(vo, new Object[] {});
						String content = String.valueOf(rsObject);
						fw.write(content + ",");
					}
				}
				fw.write("\n");
				fw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//					
	//				Set<Map.Entry<String, String>> mapObject = map.entrySet();
	//				Iterator<Map.Entry<String, String>> it = mapObject.iterator();
	//				while (it.hasNext()) {
	//					Entry<String, String> entry = it.next();
	//					Class<?> clas = vo.getClass();
	//					StringBuffer sb = new StringBuffer();
	//					sb.append("set");
	//					String oName = entry.getKey();
	//					sb.append(oName.substring(0, 1).toUpperCase());//objName是对象的名称
	//					sb.append(oName.substring(1, oName.length()));
	//					Method setMethod = null;
	//					// 进行反射注入
	//					setMethod = clas.getDeclaredMethod(sb.toString(), String.class);
	//					
	//					setMethod.invoke(vo,"");
	//								}
	//注意列之间用","间隔,写完一行需要回车换行"rn"
	/*content =
			(i + 1) + "," + novel.getName() + "," + novel.getAuthor() + ","
					+ sdf.format(novel.getPublishDate()) + "\n";*/
	//				fw.write(content);
	//			}
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			throw new RuntimeException(e);
	//		} finally {
	//			try {
	//				if (fw != null) {
	//					fw.close();
	//				}
	//			} catch (IOException e) {
	//				e.printStackTrace();
	//			}
	//		}
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
	private static Map<String, String> getObjectInfo(Object obj)
	{
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

	public static <T> void main(String[] args) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IOException
	{
		//		List<Person> persons = new ArrayList<Person>();
		//		for (int i = 1; i < 6; i++) {
		//			Person person = new Person();
		//			person.setId(String.valueOf(i));
		//			person.setName(String.valueOf((char) (i + 64)));
		//			person.setAge(i + 20);
		//			person.setSex("man");
		//			persons.add(person);
		//		}
		//		Demo1.execute(persons);
		//		//writeData2CSV(getNovels(), "d://a.csv");
//		List<Novel> novels = new ArrayList<Novel>();
//		Novel novel1 = new Novel("风云第一刀", "古龙", new BigDecimal(2), new Date());
//		Novel novel2 = new Novel("书剑恩仇录", "金庸", new BigDecimal(3), new Date());
//		Novel novel3 = new Novel("陆小凤传奇", "刘德华", new BigDecimal(4), new Date());
//		Novel novel4 = new Novel("鹿鼎记", "韦小宝", new BigDecimal(5), new Date());
//		novels.add(novel1);
//		novels.add(novel2);
//		novels.add(novel3);
//		novels.add(novel4);
//		writeData2CSV(novels, "d://a.csv");
//		getData(novels, novels);
		//		getData(novels, new Novel());
	}

	/**
	* 构造一些数据
	* 实际上可能是从数据库中把大量的数据查出来
	*/
	private static List<Object> getData(List<?> novels, Object obj)
	{
		if (novels.get(0) == null) {
			return null;
		}
		Map<String, String> map = getObjectInfo(obj);
		obj = novels.get(0);
		for (int i = 0; i < novels.size(); i++) {
			//			novels.get(0).;//////
			//需要把novels里面的属性取出来
			// 得到类型
			Class<?> cla = obj.getClass();
			System.out.println(novels.get(0).toString());
			// 得到全部属性
			Field[] fa = cla.getDeclaredFields();
			// 返回Map
			for (int j = 0; j < fa.length; j++) {
				Field field = fa[j];
				Class<?> fieldClass = field.getType();
				// 属性类型名称
				String className = fieldClass.getSimpleName();
				// 属性名称
				String fieldName = field.getName();
				// 放入map
				System.out.println(fieldName);
			}
		}
		//				// 得到类型
		//				Class<?> cla = obj.getClass();
		//				// 得到全部属性
		//				Field[] fa = cla.getDeclaredFields();
		//				// 返回Map
		//				Map<String, String> map = new LinkedHashMap<String, String>();
		//				for (int j = 0; j < fa.length; j++) {
		//					Field field = fa[j];
		//					Class<?> fieldClass = field.getType();
		//					// 属性类型名称
		//					String className = fieldClass.getSimpleName();
		//					// 属性名称
		//					String fieldName = field.getName();
		//					// 放入map
		//					map.put(fieldName, className);
		//				}
		//				return map;
		return null;
	}
}
