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
import java.io.FileInputStream;
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
public class ExportUtil1
{

	/**
	 * 创建excel文件
	 * @param list 数据集合
	 * @param head 表头
	 * @param proerty 需要导出的属性
	 * @param fileName 导出的文件路径
	 */
	public static File createExcel(List<?> list, String[] head, String[] proerty, String fileName)
	{
		Logger logger = Logger.getLogger(ExportUtil.class);
		// 创建输出文件
		File file = new File(fileName);
		int sheetNum = 1;
		String value = "";
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
			ws.setColumnView(0, 20);
			ws.setColumnView(1, 28);
			ws.setColumnView(2, 28);
			ws.setColumnView(3, 28);
			ws.setColumnView(4, 28);
			ws.setColumnView(5, 25);
			ws.setColumnView(6, 28);
			ws.setColumnView(7, 28);
			ws.setColumnView(8, 28);
			ws.setColumnView(9, 28);
			ws.setColumnView(10, 28);
			ws.setColumnView(11, 28);
			ws.setColumnView(12, 28);
			ws.setColumnView(13, 28);
			ws.setColumnView(14, 28);
			ws.setColumnView(15, 28);
			sheetNum++;
			int line = 0;//几列
			int row = 0;//几行
			boolean flag = false;
			// 写入表头
			for (String str : head) {
				Label label = new Label(line, 0, str, wcfF);
				ws.addCell(label);
				line++;
			}
			if (proerty[0].equals("gId")) {
				flag = true;
			}
			row++;
			// 写入数据
			for (Object obj : list) {
				line = 0;
				Class<?> className = obj.getClass();
				// 反射所有字段
				Field[] fields = className.getDeclaredFields();
				for (String str : proerty) {
					for (Field field : fields) {
						// 修改相应filed的权限
						field.setAccessible(true);
						Object val = field.get(obj);
						// 若该字段是需要导出的字段则写入Excel
						if (str.equals(field.getName())) {
							if (proerty[0].equals("gId")) {
								line += 1;
								setValue(value, ws, dateDcfF, line, row, val);
							} else {
								setValue(value, ws, dateDcfF, line, row, val);
								// 恢复相应field的权限
								field.setAccessible(false);
								line++;
							}
						}
					}
				}
				row++;
				// 行数超过10000行是数据放入下一个sheet
				if (row % 10000 == 0) {
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
					for (String str : head) {
						Label label = new Label(line, 0, str, wcfF);
						ws.addCell(label);
						line++;
					}
					row++;
				}
			}
			if (flag) {
				for (int i = 1; i <= list.size(); i++) {
					jxl.write.Number nVal = new Number(0, i, i, dateDcfF);
					ws.addCell(nVal);
				}
			}
			wwb.write();
			wwb.close();
			ins = new FileInputStream(file);//构造一个读取文件的IO流对象
			bins = new BufferedInputStream(ins);//放到缓冲流里面
			//			outs = response.getOutputStream();//获取文件输出IO流
			//			bouts = new BufferedOutputStream(outs);
			//			response.setContentType("application/x-download");//设置response内容的类型
			//			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));//设置头部信息
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			//开始向网络传输文件流
			//			while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
			//				bouts.write(buffer, 0, bytesRead);
			//			}
			//bouts.flush();//这里一定要调用flush()方法
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
		return file;
	}

	/**
	 * @param	
	 * @return	
	 * @author caosheng
	 * @date 2015-5-8
	 */
	private static void setValue(String value, WritableSheet ws, WritableCellFormat dateDcfF, int line, int row,
			Object val) throws WriteException, RowsExceededException
	{
		// 读取对象中相应的属性的值
		if (val instanceof BigDecimal) {
			BigDecimal num = new BigDecimal(val.toString());
			jxl.write.Number nVal = new Number(line, row, num.longValue(), dateDcfF);
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
			//设置cell的值
			Label label = new Label(line, row, value, dateDcfF);
			ws.addCell(label);
		}
	}

	public static void main(String[] args)
	{
//		List<Novel> novels = new ArrayList<Novel>();
//		//		Novel novel1 = new Novel("风云第一刀", "古龙", "2012-12-12");
//		//		Novel novel2 = new Novel("书剑恩仇录", "金庸", "2012-12-12");
//		//		Novel novel3 = new Novel("陆小凤传奇", "刘德华", "2012-12-12");
//		//		Novel novel4 = new Novel("鹿鼎记", "韦小宝", "2012-12");
//		Novel novel1 = new Novel("", "", new BigDecimal(2));
//		Novel novel2 = new Novel("书剑恩仇录", "", new BigDecimal(0));
//		Novel novel3 = new Novel("陆小凤传奇", "刘德华", new BigDecimal(4));
//		Novel novel4 = new Novel("鹿鼎记", "韦小宝", new BigDecimal(5));
//		novels.add(novel1);
//		novels.add(novel2);
//		novels.add(novel3);
//		novels.add(novel4);
//		List<String> title = new ArrayList<String>();
//		String tName = "书名";
//		String tAuthor = "作者";
//		String tId = "编号";
//		String tDate = "日期";
//		title.add(tName);
//		title.add(tAuthor);
//		title.add(tId);
//		title.add(tDate);
//		List<String> pList = new ArrayList<String>();
//		String name = "name";
//		String author = "author";
//		String id = "id";
//		String date = "date";
//		pList.add(author);
//		pList.add(name);
//		pList.add(id);
//		pList.add(date);
//		String[] str = { "author", "name", "id" };
//		String[] titles = {  "作者", "书名", "编号"};
//		createExcel(novels, titles, str, "d.csv");
	}
}
