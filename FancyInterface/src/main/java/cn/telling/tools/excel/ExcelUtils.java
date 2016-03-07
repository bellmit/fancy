package cn.telling.tools.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils {

	/**
	 * cell styles used for formatting calendar sheets
	 */
	public static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

		CellStyle style;
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setFontName("微软雅黑");
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		titleFont.setFontName("微软雅黑");
		
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor((short)35);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(titleFont);
		style.setWrapText(true);
		styles.put("title", style);

		Font monthFont = wb.createFont();
		monthFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(monthFont);
		style.setWrapText(true);
		styles.put("header", style);
		
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor((short)22);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(monthFont);
		style.setWrapText(true);
		styles.put("reportheader", style);



		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
		styles.put("cell", style);
		
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styles.put("number", style);
		
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styles.put("formula", style);
		
		style = createBorderedStyle(wb); 
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor((short) 31);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND); 
		styles.put("corp", style);

		Font headerFont = wb.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(headerFont);
		styles.put("total", style);//总计
		
		//divide
		headerFont = wb.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setDataFormat(wb.createDataFormat().getFormat("0.0%"));
		style.setFont(headerFont);
		styles.put("formula_divide", style);
		
		headerFont = wb.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setDataFormat(wb.createDataFormat().getFormat("0.0"));
		style.setFont(headerFont);
		styles.put("formula_decimals", style);
		
		 headerFont = wb.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(headerFont);
		styles.put("uptotal", style);//合计
		
		 headerFont = wb.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(headerFont);
		styles.put("subtotal", style);//小计

		return styles;
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
	
	/***
	 *  添加数据行
	 * @param rowvalues
	 * @param row
	 * @param sheet
	 * @param wb
	 * @param styles
	 */
	public static void addRow(List<?> rowvalues, int row,HSSFSheet sheet,HSSFWorkbook wb,Map<String, CellStyle> styles) {		
		HSSFRow dtRow = sheet.createRow(row);
		sheet.getLastRowNum();
		for (int j = 0; j < rowvalues.size(); j++) {
			Object cell_data = rowvalues.get(j);
			HSSFCell cell = dtRow.createCell(j);
			if(null!=cell_data){
				 if (cell_data instanceof BigDecimal) {
					 cell.setCellStyle(styles.get("number"));
					 cell.setCellValue(Double.parseDouble(cell_data.toString()) );
				 }else if(cell_data instanceof Integer){
					 cell.setCellStyle(styles.get("number"));
					 cell.setCellValue(Double.parseDouble(cell_data.toString()) );
				 }else{
					   cell.setCellStyle(styles.get("cell"));
					   cell.setCellValue((String) cell_data);
				   }
			}else{
				cell.setCellStyle(styles.get("cell"));
			}
		}
	}
	
	public void exportExcel(File file,HSSFWorkbook wb) throws Exception {
		FileOutputStream fileOut = new FileOutputStream(file);
		wb.write(fileOut);
		fileOut.close();
	}
	
	 /*
     * 下载Excel
     */
    public static  void downloadExcel (HSSFWorkbook workbook, HttpServletResponse response, String filename) throws IOException { 
        OutputStream out = response.getOutputStream(); 
        response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(filename, "UTF-8")); 
        response.setContentType("application/msexcel;charset=UTF-8"); 
        workbook.write(out);
        out.flush();
        out.close(); 
    }

	    /***
	 * 设置头信息
	 * @param rowvalues
	 * @param beginRow
	 * @param sheet
	 * @param styles
	 */
	public static void setHeader(List<?> rowvalues,int beginRow,HSSFSheet sheet,Map<String, CellStyle> styles){
		HSSFRow  hdRow = sheet.createRow(beginRow);
		sheet.setDefaultRowHeightInPoints(120);
		sheet.setDefaultColumnWidth(12);
		for (int i = 0; i < rowvalues.size(); i++) {
//			sheet.setColumnWidth(i, 10 * 256);//销售时间列宽度的修改
			HSSFCell cell1 = hdRow.createCell(i);
			cell1.setCellValue(rowvalues.get(i).toString());
			cell1.setCellStyle(styles.get("header"));// 设置样式
		}
	}
	
	   /***
		 * 设置头信息
		 * @param rowvalues
		 * @param beginRow
		 * @param sheet
		 * @param styles
		 */
		public static void setReportHeader(List<?> rowvalues,int beginRow,HSSFSheet sheet,Map<String, CellStyle> styles){
			HSSFRow  hdRow = sheet.createRow(beginRow);
			sheet.setDefaultRowHeightInPoints(120);
			sheet.setDefaultColumnWidth(12);
			hdRow.setHeight((short)400);
			for (int i = 0; i < rowvalues.size(); i++) {
				HSSFCell cell1 = hdRow.createCell(i);
				cell1.setCellValue(rowvalues.get(i).toString());
				cell1.setCellStyle(styles.get("reportheader"));// 设置样式
			}
		}
}
