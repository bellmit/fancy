package cn.telling.tools.excel;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
/**   
 * @Title: ExcelCreateForXLSX.java 
 * @Description: TODO(描述该文件做什么) 
 * @author chxb  
 * @date 2015-10-17 下午10:08:33 
 * @version V1.0   
 */
public class ExcelCreateForXLSX {
	
    private   SXSSFWorkbook  workbook = null;

    private   Sheet sheet = null;

    private   Row row = null;
	/**
	 * 设置工作表的格式
	 * 
	 * @param sheetName
	 */
	public ExcelCreateForXLSX() {
		workbook = new SXSSFWorkbook(1000);//new XSSFWorkbook();
	    Font font2 =workbook.createFont();
	    font2.setFontName("仿宋_GB2312");
	    font2.setFontName("黑体");
	    font2.setBoldweight(Font.BOLDWEIGHT_BOLD);//粗体显示
	    font2.setFontHeightInPoints((short)24);
		sheet =workbook.createSheet();
	}
	

	public SXSSFWorkbook getWorkbook() {
		return workbook;
	}


	public void setWorkbook(SXSSFWorkbook workbook) {
		this.workbook = workbook;
	}


	public Sheet getSheet() {
		return sheet;
	}


	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}


	public Row getRow() {
		return row;
	}


	public void setRow(Row row) {
		this.row = row;
	}


	/**
	 * 增加一行
	 *  @param  index 行号
	 */
	public  void  createRow( int  index){
	   row =  sheet.createRow(index);
	}
    /*
     * 下载Excel
     */
    public  void downloadExcel (SXSSFWorkbook workbook, HttpServletResponse response, String filename) throws IOException { 
        OutputStream out = response.getOutputStream(); 
        response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(filename, "UTF-8")); 
        response.setContentType("application/msexcel;charset=UTF-8"); 
        workbook.write(out);
        out.flush();
        out.close(); 
        workbook.dispose();
    }
    /**
     * 
     * @Description: 下载报表
     * @param		参数说明
     * @return		返回值
     * @author      崔大鹏
     * @date 2013-6-28 上午9:39:38 
     * @version V1.0
     */
    public  void downloadReportExcel (SXSSFWorkbook workbook, HttpServletResponse response, String filename) throws IOException { 
        OutputStream out = response.getOutputStream(); 
        response.setHeader("Content-disposition", "attachment;filename="+ filename); 
        response.setContentType("application/msexcel;charset=UTF-8"); 
        workbook.write(out);
        out.flush();
        out.close(); 
    }
    /**
	* 设置单元格
	* @param  index列号
	* @param  value 单元格填充值
    */
	public  void  setCell( int  index,  String  value,CellStyle style)  {
      Cell cell  =  row.createCell(( short ) index);
      /***可以设置文本类型 **/
      cell.setCellStyle(style);
      cell.setCellType(Cell.CELL_TYPE_STRING);
      /***可以设置文本类型 **/
      /*cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//设置边框
      cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
      cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
      cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
      cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
      cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); //设置居中
*/      cell.setCellValue(value);
    }
	
	
	public CellStyle getCellStyle(){
	   CellStyle cellStyle2 = workbook.createCellStyle();
	   DataFormat format = workbook.createDataFormat();
	   cellStyle2.setDataFormat(format.getFormat("@"));
	   return cellStyle2;
	}
	
	
	/**
	* 设置单元格
	* @param  index列号
	* @param  value 单元格填充值
    */
	public  void  setCells( int  index,  String  value)  {
      Cell cell  =  row.createCell(( short ) index);
      /***可以设置文本类型 **/
      CellStyle cellStyle2 = workbook.createCellStyle();
      DataFormat format = workbook.createDataFormat();
      cellStyle2.setDataFormat(format.getFormat("@"));
      cell.setCellStyle(cellStyle2);
      cell.setCellType(Cell.CELL_TYPE_STRING);
      /***可以设置文本类型 **/
      //cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//设置边框
      cellStyle2.setBorderBottom(CellStyle.BORDER_THIN);//下边框
      cellStyle2.setBorderLeft(CellStyle.BORDER_THIN);//左边框
      cellStyle2.setBorderTop(CellStyle.BORDER_THIN);//上边框
      cellStyle2.setBorderRight(CellStyle.BORDER_THIN);//右边框
      cellStyle2.setAlignment(CellStyle.ALIGN_CENTER); //设置居中
      cell.setCellValue(value);
    }
	
	 /**
		* 设置单元格
		* @param  index列号
		* @param  value 单元格填充值
	    */
	public  void  setCell2( int  index,  String  value)  {
	      Cell cell  =  row.createCell(( short ) index);
	      cell.setCellValue(value);
	      /***可以设置货币格式 **/
	      CellStyle cellStyle2 = workbook.createCellStyle();
	      DataFormat format = workbook.createDataFormat();
	      cellStyle2.setDataFormat(format.getFormat("¥#,##0"));
	      cell.setCellStyle(cellStyle2);
	      cell.setCellType(Cell.CELL_TYPE_STRING);
	      cellStyle2.setBorderBottom(CellStyle.BORDER_THIN);//下边框
	      cellStyle2.setBorderLeft(CellStyle.BORDER_THIN);//左边框
	      cellStyle2.setBorderTop(CellStyle.BORDER_THIN);//上边框
	      cellStyle2.setBorderRight(CellStyle.BORDER_THIN);//右边框
	      cellStyle2.setAlignment(CellStyle.ALIGN_CENTER); //设置居中
	    }
    /**
	* 重新获取单元格设置居中样式
    */
	public  void  setCellStyle(int row,int col){
      /***可以设置文本类型 **/
	  Row onerow = sheet.getRow(( short ) row);
	  Cell cell  =  onerow.getCell(( short ) col);
      CellStyle cellStyle = workbook.createCellStyle();
      cellStyle.setAlignment(CellStyle.ALIGN_CENTER); //左右设置居中
//      cellStyle.setAlignment( HSSFCellStyle.VERTICAL_CENTER);//上下居中
     
      cell.setCellStyle(cellStyle);
    }
    /**
	* 合并单元格
	* addMergedRegion(new Region(0,(short)0,0,(short)2));
    * first row (0-based)
    * last row  (0-based)
    * first column (0-based)
    * last column  (0-based)
    */
	public  void  addMergedRegion(CellRangeAddress region)  {
		sheet.addMergedRegion(region);
    }
    /**
    * 设置单元格
   	*  @param  index 列号
   	*  @param  value 单元格填充值
    */
	public  void  setCellTitle( int  index,  String  value)  {
      Cell cell  =   row.createCell(( short ) index);
      /***可以设置文本类型 **/
      CellStyle cellStyle2 = workbook.createCellStyle();
      DataFormat format = workbook.createDataFormat();
      cellStyle2.setDataFormat(format.getFormat("@"));
      cell.setCellStyle(cellStyle2);
      cell.setCellType(Cell.CELL_TYPE_STRING);
      /***可以设置文本类型 **/
      //cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//设置边框
      cellStyle2.setBorderBottom(CellStyle.BORDER_THIN);//下边框
      cellStyle2.setBorderLeft(CellStyle.BORDER_THIN);//左边框
      cellStyle2.setBorderTop(CellStyle.BORDER_THIN);//上边框
      cellStyle2.setBorderRight(CellStyle.BORDER_THIN);//右边框
      cellStyle2.setAlignment(CellStyle.ALIGN_CENTER); //设置居中 黑体
      cell.setCellValue(value);
  }
}
