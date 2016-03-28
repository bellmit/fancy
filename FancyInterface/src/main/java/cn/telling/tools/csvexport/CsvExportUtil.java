/**
 * Project Desc:
 * Project Name:TestExample
 * File Name:ExportUtil.java
 * Package Name:util
 * Date:2015-5-6上午11:38:07
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
*/
package cn.telling.tools.csvexport;

/**
 * ClassName:ExportUtil <br/>
 * Date:     2015-5-6 上午11:38:07 <br/>
 * @author   caosheng
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 导出Csv文件工具类
 */
public class CsvExportUtil
{

	static Logger logger = Logger.getLogger(CsvExportUtil.class);

	/**
	 * 导出Excel文件,注意heads和props参数必须一一对应，否则导出的数据位置可能会错乱
	 * @param datas List集合数据(必须为泛型)
	 * @param heads excel每列标题名称(如需要家商家编号,在vo中加入编号字段,，传给我编号名称))
	 * @param props 需要导出的Vo属性(如需要家商家编号,在vo中加入编号字段,然后标题列名中加入编号名称)
	 * @param fileName 导出的文件名称
	 * @param response HttpResponse
	 */
	public static void downLoadExcel(List<?> datas, String[] heads, String[] props, String fileName,
			HttpServletResponse response)
	{
		File file = new File(fileName);
		OutputStreamWriter osw = null;
		BufferedOutputStream bouts = null;
		Object[] params = new Object[] {};
		FileInputStream ins = null;
		try {
			osw = new OutputStreamWriter(new FileOutputStream(fileName), "GBK");
			for (String string : heads) {
				osw.write(string + ",");
			}
			osw.write("\n");
			for (Object obj : datas) {
				for (String prop : props) {
					String pName = getName(prop);//获取属性名称进行拼接
					Method method = obj.getClass().getMethod(pName);
					String getMethodName = method.getName();//获取方法名称如getName
					if (getMethodName.contains(pName)) {
						Object val = method.invoke(obj, params);
						if (val != null) {
							String value = String.valueOf(val).replaceAll("\r|\n|\t", "");
							value = String.valueOf(value).replaceAll(",", " ");
							osw.write(value + ",");
						} else {
							osw.write("" + ",");
						}
					}
				}
				osw.write("\n");
			}
			osw.flush();
			ins = new FileInputStream(file);//构造一个读取文件的IO流对象
			bouts = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[ins.available()];
			ins.read(buffer);
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("application/octet-stream");
			bouts.write(buffer);
			bouts.flush();
		} catch (IOException | IllegalArgumentException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			logger.error("导出文件报错:" + e.getMessage());
		} finally {
			try {
				if (osw != null) {
					osw.close();
				}
				if (bouts != null) {
					bouts.close();
				}
				if (ins != null) {
					ins.close();
				}
			} catch (IOException e) {
				logger.error("关闭文件流报错:" + e.getMessage());
			}
		}
	}

	/***
	 * 进行字符串拼接 如:getName
	 * @param	
	 * @return	
	 * @author caosheng
	 * @date 2015-5-13
	 */
	public static String getName(String name)
	{
		return "get" + name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
	}
}
