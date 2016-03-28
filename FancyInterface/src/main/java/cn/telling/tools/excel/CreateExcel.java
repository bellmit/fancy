package cn.telling.tools.excel;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;

@SuppressWarnings("deprecation")
public class CreateExcel {

	
	public HSSFWorkbook wb = null;
	public HSSFSheet sheet = null;
	public HSSFDataFormat format = null;
	public HSSFRow hdRow = null;
//	int listlength = 0;

	/**
	 * 设置工作表的格式
	 * 
	 * @param sheetName
	 */
	public CreateExcel() {
		wb = new HSSFWorkbook();
	}

	public HSSFSheet createSheet(String sheetName) {
		
		sheet = wb.createSheet(sheetName);// 页签名称
		format = wb.createDataFormat();
		hdRow = sheet.createRow(0);// 创建一行
		sheet.setDefaultRowHeightInPoints(120);
		sheet.setDefaultColumnWidth(12);
		return sheet;
	}

	/* 设置各列单元格宽度 */

    public void setDefaultCellHighWidthInRange(short[] eachCellWidth, int high) {
		// 假定第一行和第一行所需的单元个已经建立好了，也就是说，在这之前已经调用了DesignXlsHeaderFooter.setXlsHeader
		sheet.setDefaultRowHeightInPoints(high);// 设置默认高
		/* 设置各列单元格宽度 */
		for (int i = 0; i < eachCellWidth.length; i++) {
			// System.out.print(""+i+"\t");
			sheet.setColumnWidth((short) i, (short) ((eachCellWidth[i]) * 256));
		}
	}
	
	public HSSFWorkbook getWorkbook() {
		return wb;
	}

	/**
	 * 表头数据
	 * 
	 * @throws Exception
	 */
	public void addHeader(List<?> rowvalues, boolean isFilter) throws Exception {
		// listlength = rowvalues.size();
		// 设置字体
		HSSFFont workFont = wb.createFont();
		workFont.setFontName("微软雅黑");
		//workFont.setFontHeightInPoints((short) 12);
		workFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		HSSFRow hdRow=sheet.createRow(headrow);
		// 表头样式及背景色
		HSSFCellStyle hdStyle = wb.createCellStyle();
		
		hdStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		hdStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		hdStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		hdStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		
//		hdStyle.setBottomBorderColor(HSSFColor.BLACK.index);
//		hdStyle.setLeftBorderColor(HSSFColor.BLACK.index);
//		hdStyle.setRightBorderColor(HSSFColor.BLACK.index);
//		hdStyle.setTopBorderColor(HSSFColor.BLACK.index);

		
		hdStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
	      hdStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
	      hdStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
	      hdStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		

		hdStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);

		hdStyle.setFont(workFont);
		String[] title = new String[rowvalues.size()];
		for (int i = 0; i < rowvalues.size(); i++) {
			title[i] = (String) rowvalues.get(i);
		}
		if (isFilter == true) {
			for (int i = 0; i < title.length; i++) {
				HSSFCell cell1 = hdRow.createCell(i);
				HSSFRichTextString value = new HSSFRichTextString(title[i]);
				cell1.setCellValue(value);
				cell1.setCellStyle(hdStyle);// 设置样式
			}
		} else {
			for (int i = 0; i < title.length; i++) {
				HSSFCell cell2 = hdRow.createCell(i);
				HSSFRichTextString value2 = new HSSFRichTextString(title[i]);
				cell2.setCellValue(value2);
				//cell2.setCellStyle(hdStyle);// 设置样式
			}
		}
	}

	public void addRow(List<String> rowvalues, int row) {		
		HSSFRow dtRow = sheet.createRow(row);
		DataFormat format = wb.createDataFormat();
		HSSFCellStyle dtStyle = wb.createCellStyle();
		dtStyle.setDataFormat(format.getFormat("text"));
		dtStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		dtStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		dtStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		dtStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		dtStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		dtStyle.setRightBorderColor(HSSFColor.BLACK.index);
		dtStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		dtStyle.setTopBorderColor(HSSFColor.BLACK.index);
		dtStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	
		for (int j = 0; j < rowvalues.size(); j++) {
			Object cell_data = rowvalues.get(j);
			HSSFCell cell = dtRow.createCell(j);
			if(null!=cell_data){
				 if (cell_data instanceof BigDecimal) {
					 dtStyle.setAlignment(CellStyle.ALIGN_CENTER);
					 cell.setCellStyle(dtStyle);
					 cell.setCellValue(Double.parseDouble(cell_data.toString()) );
				   }else{
					   cell.setCellStyle(dtStyle);
					   cell.setCellValue((String) cell_data);
				   }
			}
			
		}
	}


	/**
	 * 具体文件生成的路径
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void exportExcel(String file) throws Exception {
		FileOutputStream fileOut = new FileOutputStream(file);
		wb.write(fileOut);
		fileOut.close();
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
	 * 具体文件生成的文件
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void exportExcel(File file) throws Exception {
		FileOutputStream fileOut = new FileOutputStream(file);
		wb.write(fileOut);
		fileOut.close();
	}

	/**
	 * 具体文件生成的文件
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void exportExcel(OutputStream outputstream) throws Exception {
		BufferedOutputStream buffout = new BufferedOutputStream(outputstream);
		wb.write(buffout);
		buffout.flush();
		buffout.close();
	}

	public static void main(String[] args) throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("任务序号任务序号");
		list.add("负责人");
		list.add("日期");
		list.add("事件");
		list.add("标志");
		list.add("地名");
		CreateExcel s = new CreateExcel();
		for (int i = 1; i <= 5; i++) {
			s.createSheet(i + "工作任务");
			//s.addHeader(list, true,0);
			setExcel(s,null);
		}
		JFileChooser jfc = new JFileChooser();
		jfc.showSaveDialog(null);
		File file = jfc.getSelectedFile();
		file = new File(file + ".xls");
		s.exportExcel(file);
	}

	private static void setExcel(CreateExcel s ,List<?> dataList) throws Exception {
		List<String> lists = null;
		for (int i = 0; i < 5; i++) {
			lists = new ArrayList<String>();
			lists.add("A000" + i);
			if (i == 0) {
				lists.add("关羽");
				lists.add("2014-04-03");
				lists.add("走单骑");
				lists.add("男");
				lists.add("河东解良");
			} else if (i == 1) {
				lists.add("张飞");
				lists.add("2014-04-03");
				lists.add("大喝长坂坡");
				lists.add("男");
				lists.add("江南");
			} else if (i == 2) {
				lists.add("赵云");
				lists.add("2014-04-03");
				lists.add("单骑救阿斗");
				lists.add("男");
				lists.add("常山");
			} else if (i == 3) {
				lists.add("马超");
				lists.add("2014-04-03");
				lists.add("力压群雄");
				lists.add("男");
				lists.add("西凉");
			} else if (i == 4) {
				lists.add("黄忠");
				lists.add("2014-04-03");
				lists.add("老当益壮");
				lists.add("男");
				lists.add("许都");
			}
			s.addRow(lists, i + 1);
		}
	}


}
