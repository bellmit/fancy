package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**   
 * @Title: XlsUtil.java 
 * @Package util 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2015-12-2 上午10:17:43 
 * @version V1.0   
 */
public class XlsUtil {
	public static void read(String filePath) throws IOException {
	    String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
	    InputStream stream = new FileInputStream(filePath);
	    Workbook wb = null;
	    if (fileType.equals("xls")) {
	      wb = new HSSFWorkbook(stream);
	    } else if (fileType.equals("xlsx")) {
	      wb = new XSSFWorkbook(stream);
	    } else {
	      System.out.println("您输入的excel格式不正确");
	    }
	    Sheet sheet1 = wb.getSheetAt(0);
	    for (Row row : sheet1) {
	      for (Cell cell : row) {
	        System.out.print(cell.getStringCellValue() + "  ");
	      }
	      System.out.println();
	    }
	  }
	/***
	 * @deprecated 上下左右都有下划线
	 * @author Andy
	 * @param wb
	 * @return
	 */
	private static CellStyle createBorderedStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return style;
	}
	  public static boolean write(String outPath) throws Exception {
	    String fileType = outPath.substring(outPath.lastIndexOf(".") + 1, outPath.length());
	    System.out.println(fileType);
	    // 创建工作文档对象
	    Workbook wb = null;
	    if (fileType.equals("xls")) {
	      wb = new HSSFWorkbook();
	    } else if (fileType.equals("xlsx")) {
	      wb = new XSSFWorkbook();
	    } else {
	      System.out.println("您的文档格式不正确！");
	      return false;
	    }
	    CellStyle style=null;
	    Font titleFont = wb.createFont();
	    titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    titleFont.setFontHeightInPoints((short) 11);
		titleFont.setFontName("华文行楷");
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor((short)35);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(titleFont);
		style.setWrapText(true);
	
		
	    
	    // 创建sheet对象
	    Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
	    // 循环写入行数据
	    for (int i = 0; i < 1; i++) {
	      Row row = (Row) sheet1.createRow(i);
	      // 循环写入列数据
	      for (int j = 0; j < 8; j++) {
	        Cell cell = row.createCell(j);
	       cell.setCellStyle(style);
	        cell.setCellValue("起始日期" + j);
	      }
	    }
	    // 创建文件流
	    OutputStream stream = new FileOutputStream(outPath);
	    // 写入数据
	    wb.write(stream);
	    // 关闭文件流
	    stream.close();
	    return true;
	  }


	  public static void main(String[] args) {
	    try {
	      XlsUtil.write("D:" + File.separator + "out.xlsx");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    try {
	      XlsUtil.read("D:" + File.separator + "out.xlsx");
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
	}

