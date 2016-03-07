package cn.telling.tools.upload;

import java.io.Serializable;

/**   
 * @Title: CompressImageVo.java 
 * @Package com.test 
 * @Description: TODO(描述该文件做什么) 
 * @author 李 欢   
 * @date 2013-4-27 上午9:38:26 
 * @version V1.0   
 */

public class CompressImageVo implements Serializable {
	/**
	 * 序列
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 输入图文件路径名（含文件名）
	 */
	private String inputFileName; 
	/**
	 *  输出图文件名（含文件名）
	 */
	private String outputFileName; 
	/**
	 * 默认输出图片宽
	 */
	private int outputWidth = 100; 
	/**
	 *  默认输出图片高
	 */
	private int outputHeight = 100; 
	/**
	 * 是否等比缩放标记(默认为等比缩放)
	 */
	private boolean proportion = true; 
	

	public String getInputFileName() {
		return inputFileName;
	}
	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}
	public String getOutputFileName() {
		return outputFileName;
	}
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
	public int getOutputWidth() {
		return outputWidth;
	}
	public void setOutputWidth(int outputWidth) {
		this.outputWidth = outputWidth;
	}
	public int getOutputHeight() {
		return outputHeight;
	}
	public void setOutputHeight(int outputHeight) {
		this.outputHeight = outputHeight;
	}
	public boolean isProportion() {
		return proportion;
	}
	public void setProportion(boolean proportion) {
		this.proportion = proportion;
	}
	
	
}
