/**
 * Project Desc:
 * Project Name:TestExample
 * File Name:ExportUtil.java
 * Package Name:util
 * Date:2015-5-6上午11:38:07
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
*/
package util;

/**
 * ClassName:ExportUtil <br/>
 * Date:     2015-5-6 上午11:38:07 <br/>
 * @author   caosheng
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;

/**
 * 导出文件工具类
 */
public class ExportUtil2
{

	/**
	 * 创建excel文件
	 * @param list 数据集合
	 * @param head 表头
	 * @param proerty 需要导出的属性
	 * @param fileName 导出的文件路径
	 */
	public static void downLoadExcel(List<?> datas, String[] heads, String[] props, String fileName)
	{
		Logger logger = Logger.getLogger(ExportUtil2.class);
		// 创建输出文件
		File file = new File(fileName);
		int sheetNum = 1;
		WritableWorkbook wwb = null;
		InputStream ins = null;
		OutputStream outs = null;
		BufferedInputStream bins = null;
		BufferedOutputStream bouts = null;
		try {
			// 表头格式
			WritableCellFormat wcfF = new WritableCellFormat();
			wcfF.setAlignment(Alignment.CENTRE);
			wcfF.setVerticalAlignment(VerticalAlignment.CENTRE);
			wwb = Workbook.createWorkbook(file);
			WritableSheet ws = wwb.createSheet("第" + sheetNum + "页", sheetNum);
			// 设置冻结首行
			ws.getSettings().setVerticalFreeze(1);
			ws.getSettings().setFitWidth(100);
			// 数据格式
			WritableFont wf =
					new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat dateDcfF = new WritableCellFormat(wf);
			dateDcfF.setWrap(true);
			dateDcfF.setAlignment(jxl.format.Alignment.CENTRE);
			dateDcfF.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 控制列宽
			for (int i = 0; i < datas.size(); i++) {
				ws.setColumnView(i, 18);
			}
			sheetNum++;
			int line = 0;//几列
			int row = 0;//几行
			boolean flag = false;
			// 写入表头
			for (String str : heads) {
				Label label = new Label(line, 0, str, wcfF);
				ws.addCell(label);
				line++;
			}
			if (props[0].equalsIgnoreCase("gId")) {
				flag = true;
			}
			row++;
			// 写入数据
			for (Object obj : datas) {
				line = 0;
				// 反射所有字段
				Field[] fields = obj.getClass().getDeclaredFields();
				for (String str : props) {
					for (Field field : fields) {
						// 修改相应filed的权限
						field.setAccessible(true);
						Object val = field.get(obj);//获取属性值
						// 若该字段是需要导出的字段则写入Excel
						if (str.equals(field.getName())) {
							if (props[0].equalsIgnoreCase("gId")) {
								line += 1;
								setValue(ws, dateDcfF, line, row, val);
							} else {
								setValue(ws, dateDcfF, line, row, val);
								line++;
							}
						}
						// 恢复相应field的权限
						field.setAccessible(false);
					}
				}
				System.out.println();
				row++;
				// 行数超过10000行是数据放入下一个sheet
				/*	if (row % 10000 == 0) {
					// 设置标题格式
					line = 0;
					row = 0;
					ws = wwb.createSheet("第" + sheetNum + "页", sheetNum);
					// 设置冻结首行
					ws.getSettings().setVerticalFreeze(1);
					// 控制列宽
					ws.setColumnView(0, 10);
					ws.setColumnView(1, 28);
					ws.setColumnView(2, 28);
					ws.setColumnView(3, 28);
					ws.setColumnView(4, 28);
					sheetNum++;
					// 再次写入表头
					for (String str : heads) {
						Label label = new Label(line, 0, str, wcfF);
						ws.addCell(label);
						line++;
					}
					row++;
				}*/
			}
			if (flag) {
				for (int i = 1; i <= datas.size(); i++) {
					jxl.write.Number nVal = new Number(0, i, i, dateDcfF);
					ws.addCell(nVal);
				}
			}
			wwb.write();
			wwb.close();
			//			ins = new FileInputStream(file);//构造一个读取文件的IO流对象
			//			bins = new BufferedInputStream(ins);//放到缓冲流里面
			//			outs = response.getOutputStream();//获取文件输出IO流
			//			bouts = new BufferedOutputStream(outs);
			//			response.setContentType("application/x-download");//设置response内容的类型
			//			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));//设置头部信息
			//			int bytesRead = 0;
			//			byte[] buffer = new byte[8192];
			//			//开始向网络传输文件流
			//			while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
			//				bouts.write(buffer, 0, bytesRead);
			//			}
			//			bouts.flush();//这里一定要调用flush()方法
		} catch (IOException | IllegalArgumentException | IllegalAccessException | WriteException e) {
			logger.error("导出文件报错:" + e.getMessage());
		} finally {
			try {
				if (ins != null) {
					ins.close();
				}
				if (bins != null) {
					bins.close();
				}
				if (outs != null) {
					outs.close();
				}
				if (bouts != null) {
					bouts.close();
				}
			} catch (IOException e) {
				logger.error("导出文件IO报错:" + e.getMessage());
			}
		}
	}

	/**
	 * @param	
	 * @return	
	 * @author caosheng
	 * @date 2015-5-8
	 */
	private static void setValue(WritableSheet ws, WritableCellFormat dateDcfF, int line, int row, Object val)
			throws WriteException, RowsExceededException
	{
		//设置cell的值
		if (val instanceof BigDecimal) {
			BigDecimal num = new BigDecimal(val.toString());
			jxl.write.Number nVal = new Number(line, row, num.doubleValue(), dateDcfF);
			ws.addCell(nVal);
		} else if (val instanceof String) {
			Label label = new Label(line, row, val.toString(), dateDcfF);
			ws.addCell(label);
		} else if (val instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date = sdf.format(val);
			Label label = new Label(line, row, date, dateDcfF);
			ws.addCell(label);
		} else {
			if (val != null) {
				Label label = new Label(line, row, val.toString(), dateDcfF);
				ws.addCell(label);
			}
		}
		System.out.println(val);
	}

	public static void main(String[] args)
	{
		ExportUtil2 eu2 = new ExportUtil2();
		eu2.invokeDown();
	}

	/**
	 * @param	
	 * @return	
	 * @author caosheng
	 * @date 2015-5-12
	 */
	private void invokeDown()
	{
		List<Novel> novels = new ArrayList<Novel>();
		//		Novel novel1 = new Novel("风云第一刀", "古龙", "2012-12-12");
		//		Novel novel2 = new Novel("书剑恩仇录", "金庸", "2012-12-12");
		//		Novel novel3 = new Novel("陆小凤传奇", "刘德华", "2012-12-12");
		//		Novel novel4 = new Novel("鹿鼎记", "韦小宝", "2012-12");
		Novel novel1 = new Novel("书剑恩仇录", "", new BigDecimal(2));
		Novel novel2 = new Novel("书剑恩仇录", "刘德华", new BigDecimal(0));
		Novel novel3 = new Novel("陆小凤传奇", "刘德华", new BigDecimal(422.12));
		Novel novel4 = new Novel("鹿鼎记", "韦小宝", new BigDecimal(5));
		novels.add(novel1);
		novels.add(novel2);
		novels.add(novel3);
		novels.add(novel4);
		String[] heads = { "编号", "书名", "价格" };
		String[] props = { "gId", "name", "price" };
		downLoadExcel(novels, heads, props, "测试报表.csv");
	}

	protected class Novel
	{

		private String name;

		private String author;

		private BigDecimal price;

		private Date date;

		/**
		 * Creates a new instance of Novel.
		 *
		 */
		public Novel()
		{
		}

		public Novel getInstant()
		{
			Novel novel = this;
			{
				return novel;
			}
		}

		/**
		 * Creates a new instance of Novel.
		 *
		 * @param string
		 * @param string2
		 * @param date
		 */
		public Novel(String name, String author, BigDecimal price)
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

		public Date getDate()
		{
			return date;
		}

		public void setDate(Date date)
		{
			this.date = date;
		}
	}
}
