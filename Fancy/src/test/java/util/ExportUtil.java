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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 导出文件工具类
 */
public class ExportUtil
{
	/**
	 * 创建TXT或者CSV文件
	 * 
	 * @param list 查询出的结果
	 * @param head 表头
	 * @param proerty 需要导出的列（与head对应）
	 * @param fileName 文件名
	 * @return
	 * @throws Exception
	 */
	/*public static File createTxtFile(List list, List<String> head, List<String> proerty, String fileName)
			throws Exception
	{
		File file = new File(fileName);
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
		StringBuilder sb = new StringBuilder();
		for (String str : head) {
			sb.append("\t" + str + ",");
		}
		sb.append("\r\n");
		Long row = 0L;
		for (Object obj : list) {
			Class className = obj.getClass();
			// 反射所有字段
			@SuppressWarnings("unused")
			Field[] fields = className.getDeclaredFields();
			for (String str : proerty) {
				// 若该字段是需要导出的字段则写入Excel
				Object o = ReflectUtils.getProertyValue(obj, str);
				String value = o == null ? "" : o.toString();
				// 设置cell的值
				sb.append("\t" + value + ",");
			}
			sb.append("\r\n");
			if (row % 1000 == 0) {
				out.write(sb.toString().getBytes(LX100Constant.CHAR_SET));
				out.flush();
				sb = new StringBuilder();
			}
		}
		out.write(sb.toString().getBytes(LX100Constant.CHAR_SET));
		out.flush();
		out.close();
		return file;
	}

	*//**
		* 创建excel文件
		* 
		* @param list
		* @param head
		* @param proerty
		* @param fileName
		* @return
		* @throws Exception
		*/
	/*
	public static File createExcel(List list, List<String> head, List<String> proerty, String fileName)
		throws Exception
	{
	// 创建输出文件
	File file = new File(fileName);
	int line = 0;
	int row = 0;
	int sheetNum = 1;
	// 表头格式
	WritableCellFormat wcfF = new jxl.write.WritableCellFormat();
	wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	@SuppressWarnings("unused")
	Colour olour;
	wcfF.setBackground(Colour.LIGHT_ORANGE);
	WritableWorkbook wwb = Workbook.createWorkbook(file);
	WritableSheet ws = wwb.createSheet("第" + sheetNum + "页", sheetNum);
	// 设置冻结首行
	ws.getSettings().setVerticalFreeze(1);
	ws.getSettings().setFitWidth(100);
	// 数据格式
	WritableCellFormat dateDcfF = new jxl.write.WritableCellFormat();
	dateDcfF.setWrap(true);
	dateDcfF.setAlignment(jxl.format.Alignment.CENTRE);
	dateDcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	// 控制列宽
	ws.setColumnView(0, 10);
	ws.setColumnView(1, 18);
	ws.setColumnView(2, 18);
	ws.setColumnView(3, 18);
	ws.setColumnView(4, 18);
	ws.setColumnView(5, 25);
	ws.setColumnView(6, 18);
	ws.setColumnView(7, 18);
	ws.setColumnView(8, 18);
	ws.setColumnView(9, 18);
	ws.setColumnView(10, 18);
	ws.setColumnView(11, 18);
	ws.setColumnView(12, 18);
	ws.setColumnView(13, 18);
	ws.setColumnView(14, 18);
	ws.setColumnView(15, 18);
	sheetNum++;
	// 写入表头
	for (String str : head) {
		Label label = new Label(line, 0, str, wcfF);
		ws.addCell(label);
		line++;
	}
	row++;
	// 写入数据
	for (Object obj : list) {
		line = 0;
		Class className = obj.getClass();
		// 反射所有字段
		Field[] fields = className.getDeclaredFields();
		for (String str : proerty) {
			for (Field field : fields) {
				// 若该字段是需要导出的字段则写入Excel
				if (str.equals(field.getName())) {
					// 修改相应filed的权限
					boolean accessFlag = field.isAccessible();
					field.setAccessible(true);
					// 读取对象中相应的属性的值
					String value = field.get(obj).toString();
					if (str.equals("commendTime")) {
						value = field.get(obj).toString().substring(0, 19);
					}
					// 设置cell的值
					Label label = new Label(line, row, value, dateDcfF);
					ws.addCell(label);
					// 恢复相应field的权限
					field.setAccessible(accessFlag);
					line++;
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
			ws.setColumnView(1, 18);
			ws.setColumnView(2, 18);
			ws.setColumnView(3, 18);
			ws.setColumnView(4, 18);
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
	// 写入数据并关闭文件
	wwb.write();
	wwb.close();
	return file;
	}*/
}
