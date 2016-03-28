/**
 * Project Desc:
 * Project Name:TestExample
 * File Name:De.java
 * Package Name:util
 * Date:2015-5-8下午12:55:50
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
*/
package util;

/**
 * ClassName:De <br/>
 * Date:     2015-5-8 下午12:55:50 <br/>
 * @author   caosheng
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelOperationTest1
{

	public static void clean(String inFile, String outFile)
	{
		//首先需要声明输入输出流与Workbook，Workbook对应Excel文件
		InputStream is = null;
		Workbook inWb = null;
		OutputStream os = null;
		WritableWorkbook outWb = null;
		try {
			//实例化输入输出流与Workbook
			is = new FileInputStream(inFile);
			//Workbook无法通过new关键字进行实例化，必须调用Workbook.getWorkbook(File)静态方法
			inWb = Workbook.getWorkbook(is);
			os = new FileOutputStream(outFile);
			outWb = Workbook.createWorkbook(os);
			//下面这一句是选择原文件的工作表，Sheet对应的是Excel中的Sheet
			Sheet inSheet = inWb.getSheet("Sheet1");
			//WritableSheet为要创建的文件中的工作表
			WritableSheet outSheet = outWb.createSheet("Sheet1", 0);
			//设置循环标志
			boolean flag = true;
			//循环条件
			int i = 0;
			while (flag) {
				//Cell对应一个单元格，getCell方法第一个参数对应列，第二个参数对应行。
				//注意：行和列的开始坐标均为0，与JAVA中的数组一样
				Cell tmp = inSheet.getCell(0, i);
				//取得每行第一列单元格中的数据。不管单元格中的数据在EXCEL中为何种类型，读取之后都将成为String类型
				String c = tmp.getContents().trim();
				//为处理方面，EXCEL文件中最后一行的第一个单元格设置了一个break标志，读到这一行时将退出循环
				if (c.equals("break")) {
					flag = false;
					break;
				}
				//我处理的EXCEL表格为8列
				for (int j = 0; j < 8; j++) {
					//取得源单元格
					Cell inCell = inSheet.getCell(j, i);
					//获取数据
					String content = inCell.getContents().trim();
					//创建新单元格，并用数据填充
					Label labelCF = new Label(j, i, content);
					//将新单元格加入到新工作表中
					outSheet.addCell(labelCF);
				}
				i++;
			}
			//写入文件
			outWb.write();
			//关闭流
			outWb.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//测试
	public static void main(String[] args)
	{
		try {
			//  打开文件    
			WritableWorkbook book = Workbook.createWorkbook(new File(" test.xls "));
			//  生成名为“第一页”的工作表，参数0表示这是第一页    
			WritableSheet sheet = book.createSheet(" 第一页 ", 0);
			//  在Label对象的构造子中指名单元格位置是第一列第一行(0,0)   
			//  以及单元格内容为test    
			Label label = new Label(0, 0, " test ");
			//  将定义好的单元格添加到工作表中    
			sheet.addCell(label);
			Label label1 = new Label(0, 1, " test ");
			//  将定义好的单元格添加到工作表中    
			sheet.addCell(label1);
			//  写入数据并关闭文件    
			book.write();
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
