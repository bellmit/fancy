package cn.telling.tools.excel;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
/**   
 * @Title: ExcelCreate.java 
 * @Description: TODO(描述该文件做什么) 
 * @author xuesongtan   
 * @date 2013-8-7 下午2:08:33 
 * @version V1.0   
 */
@SuppressWarnings("deprecation")
public class ExcelCreate {
    private   HSSFWorkbook workbook = null;

    private   HSSFSheet sheet = null;

    private   HSSFRow row = null;
    
    private static HSSFCellStyle cellStyle2 = null;
    		
	/**
	 * 设置工作表的格式
	 * 
	 * @param sheetName
	 */
	public ExcelCreate() {
		workbook = new HSSFWorkbook();
	    HSSFFont font2 =workbook.createFont();
	    font2.setFontName("仿宋_GB2312");
	    font2.setFontName("黑体");
	    font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
	    font2.setFontHeightInPoints((short)24);
		sheet =workbook.createSheet();
		cellStyle2 = workbook.createCellStyle();
	}
	public HSSFWorkbook getWorkbook() {
		return workbook;
	}


	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}


	public HSSFSheet getSheet() {
		return sheet;
	}


	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}


	public HSSFRow getRow() {
		return row;
	}


	public void setRow(HSSFRow row) {
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
    public  void downloadExcel (HSSFWorkbook workbook, HttpServletResponse response, String filename) throws IOException { 
        OutputStream out = response.getOutputStream(); 
        response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(filename, "UTF-8")); 
        response.setContentType("application/msexcel;charset=UTF-8"); 
        workbook.write(out);
        out.flush();
        out.close(); 
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
    public  void downloadReportExcel (HSSFWorkbook workbook, HttpServletResponse response, String filename) throws IOException { 
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
    public  void  setCell( int  index,  String  value)  {
      HSSFCell cell  =  row.createCell(( short ) index);
      /***可以设置文本类型 **/
     // HSSFCellStyle cellStyle2 = workbook.createCellStyle();
      HSSFDataFormat format = workbook.createDataFormat();
      cellStyle2.setDataFormat(format.getFormat("@"));
      cell.setCellStyle(cellStyle2);
      cell.setCellType(HSSFCell.CELL_TYPE_STRING);
      /***可以设置文本类型 **/
      /*cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//设置边框
      cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
      cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
      cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
      cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
      cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); //设置居中
*/      cell.setCellValue(value);
    }
	
	
	/**
	* 设置单元格
	* @param  index列号
	* @param  value 单元格填充值
    */
    public  void  setCells( int  index,  String  value)  {
      HSSFCell cell  =  row.createCell(( short ) index);
      /***可以设置文本类型 **/
      HSSFCellStyle cellStyle2 = workbook.createCellStyle();
      HSSFDataFormat format = workbook.createDataFormat();
      cellStyle2.setDataFormat(format.getFormat("@"));
      cell.setCellStyle(cellStyle2);
      cell.setCellType(HSSFCell.CELL_TYPE_STRING);
      /***可以设置文本类型 **/
      //cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//设置边框
      cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
      cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
      cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
      cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
      cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); //设置居中
      cell.setCellValue(value);
    }
	
	 /**
		* 设置单元格
		* @param  index列号
		* @param  value 单元格填充值
	    */
	public  void  setCell2( int  index,  String  value)  {
	      HSSFCell cell  =  row.createCell(( short ) index);
	      cell.setCellValue(value);
	      /***可以设置货币格式 **/
	      HSSFCellStyle cellStyle2 = workbook.createCellStyle();
	      HSSFDataFormat format = workbook.createDataFormat();
	      cellStyle2.setDataFormat(format.getFormat("¥#,##0"));
	      cell.setCellStyle(cellStyle2);
	      cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	      cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
	      cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
	      cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
	      cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
	      cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); //设置居中
	    }
    /**
	* 重新获取单元格设置居中样式
    */
	public  void  setCellStyle(int row,int col){
      /***可以设置文本类型 **/
	  HSSFRow onerow = sheet.getRow(( short ) row);
	  HSSFCell cell  =  onerow.getCell(( short ) col);
      HSSFCellStyle cellStyle = workbook.createCellStyle();
      cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //左右设置居中
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
	public  void  addMergedRegion(Region region)  {
		sheet.addMergedRegion(region);
    }
    /**
    * 设置单元格
   	*  @param  index 列号
   	*  @param  value 单元格填充值
    */
	public  void  setCellTitle( int  index,  String  value)  {
      HSSFCell cell  =   row.createCell(( short ) index);
      /***可以设置文本类型 **/
      HSSFCellStyle cellStyle2 = workbook.createCellStyle();
      HSSFDataFormat format = workbook.createDataFormat();
      cellStyle2.setDataFormat(format.getFormat("@"));
      cell.setCellStyle(cellStyle2);
      cell.setCellType(HSSFCell.CELL_TYPE_STRING);
      /***可以设置文本类型 **/
      //cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//设置边框
      cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
      cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
      cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
      cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
      cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); //设置居中 黑体
      cell.setCellValue(value);
  }
}
