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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * ClassName:Demo1 <br/>
 * Date:     2015-5-6 上午11:41:44 <br/>
 * @author   caosheng
 */
public class ExportTool
{

	static Logger logger = Logger.getLogger(ExportTool.class);

	/**
	 * 导出Excel文件,注意heads和props参数必须一一对应，否则导出的数据位置可能会错乱
	 * @param datas List集合数据(必须为泛型)
	 * @param heads excel每列标题名称(当需要家商家编号，传给我编号名称，必须放在数组第一条))
	 * @param props 需要导出的Vo属性(当需要家商家编号,传给我gId(大小写不区分),必须放在数组第一条)
	 * @param fileName 导出的文件名称
	 * @param response HttpResponse
	 */
	public static void downLoadExcel(List<?> datas, String[] heads, String[] props, String fileName)
	{
		OutputStreamWriter osw = null;
		Object[] params = new Object[] {};
		try {
			osw = new OutputStreamWriter(new FileOutputStream(fileName), "GBK");
			for (String string : heads) {
				osw.write(string + ",");
			}
			osw.write("\n");
			String value = null;
			for (int i = 0; i < datas.size(); i++) {
				for (int j = 0; j < props.length; j++) {
					String pName = getName(props[j]);//获取属性名称进行拼接
					Method method = datas.get(i).getClass().getMethod(pName);
					String mName = method.getName();//获取方法名称如getName
					if (mName.contains(pName)) {
						Object rsObject = method.invoke(datas.get(i), params);
						if (rsObject != null) {
							if (String.valueOf(rsObject).contains(",")) {
								value = String.valueOf(rsObject).replaceAll("\r|\n|\t|,", " ");
							} else {
								value = String.valueOf(rsObject).replaceAll("\r|\n|\t", "");
							}
							osw.write(value + ",");
							System.out.print(value);
						} else {
							osw.write("" + ",");
						}
					}
				}
				System.out.print("  ");
				osw.write("\n");
			}
			osw.flush();
		} catch (IOException | IllegalArgumentException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			logger.error("导出文件报错:" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (osw != null) {
					osw.close();
				}
			} catch (IOException e) {
				logger.error("导出文件IO报错:" + e.getMessage());
				e.printStackTrace();
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

	public static void main(String[] args)
	{
		List<Novel> novels = new ArrayList<Novel>();
		Novel novel1 = new Novel("风云第一刀\r\t", "刘德华", new BigDecimal(0), new Date());
		Novel novel2 = new Novel("书剑恩仇录", "金,庸", new BigDecimal(0), new Date());
		Novel novel3 = new Novel("陆小凤传奇", "刘德,华", new BigDecimal(0), new Date());
		Novel novel4 = new Novel("陆小凤,传奇", "韦小宝", new BigDecimal(0), new Date());
		novels.add(novel1);
		novels.add(novel2);
		novels.add(novel3);
		novels.add(novel4);
		List<Novel> datas = new ArrayList<Novel>();
		for (int i = 0; i < novels.size(); i++) {
			Novel novel = novels.get(i);
			novel.setGenerId(i + 1);
			datas.add(novel);
		}
		novels = null;
		downLoadExcel(datas, new String[] { "作者名称", "gId", "书名", "日期" }, new String[] { "author", "generId", "name",
				"date" }, "novel.csv");
	}

	static class Novel
	{

		private String name;

		private String author;

		private Date date;

		private BigDecimal price;

		private int generId;

		public int getGenerId()
		{
			return generId;
		}

		public void setGenerId(int generId)
		{
			this.generId = generId;
		}

		/**
		 * Creates a new instance of Novel.
		 *
		 */
		public Novel()
		{
		}

		public Date getDate()
		{
			return date;
		}

		public void setDate(Date date)
		{
			this.date = date;
		}

		/**
		 * Creates a new instance of Novel.
		 * @param date 
		 *
		 * @param string
		 * @param string2
		 * @param date
		 */
		public Novel(String name, String author, BigDecimal price, Date date)
		{
			this.name = name;
			this.author = author;
			this.price = price;
		}

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public String getAuthor()
		{
			return author;
		}

		public void setAuthor(String author)
		{
			this.author = author;
		}

		public BigDecimal getPrice()
		{
			return price;
		}

		public void setPrice(BigDecimal price)
		{
			this.price = price;
		}
	}
}
