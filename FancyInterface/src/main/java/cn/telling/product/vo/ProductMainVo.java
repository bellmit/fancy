package cn.telling.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 产品主表Vo
 * @ClassName: ProductMainVo
 * 
 * @author xingle
 * @date 2015-7-30 下午6:13:24
 */
public class ProductMainVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3155180712339663224L;
	/**
	* @Fields productId : 产品id
	*/
	private BigDecimal productId ;        		
	/**
	* @Fields productName : 产品名称
	*/
	private String productName;  		
	/**
	* @Fields picturepath1 : 产品图片
	*/
	private String picturepath1;   		
	/**
	* @Fields brand : 产品品牌
	*/
	private BigDecimal brand;			
	/**
	* @Fields productpattern : 产品型号
	*/
	private String productpattern;			
	/**
	* @Fields memory : 内存
	*/
	private String memory; 		
	/**
	* @Fields color : 产品颜色
	*/
	private String color;     		
	/**
	* @Fields category : 产品类别（手机，平板等）
	*/
	private String category; 		
	/**
	* @Fields network : 网络制式
	*/
	private String network;		
	/**
	* @Fields screen : 屏幕大小
	*/
	private String screen;		
	/**
	* @Fields system : 系统
	*/
	private String system;		
	/**
	* @Fields saletype : 销售类型
	*/
	private String saletype;				
	
	public BigDecimal getProductId() {
		return productId;
	}
	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPicturepath1() {
		return picturepath1;
	}
	public void setPicturepath1(String picturepath1) {
		this.picturepath1 = picturepath1;
	}
	public BigDecimal getBrand() {
		return brand;
	}
	public void setBrand(BigDecimal brand) {
		this.brand = brand;
	}
	public String getProductpattern() {
		return productpattern;
	}
	public void setProductpattern(String productpattern) {
		this.productpattern = productpattern;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getSaletype() {
		return saletype;
	}
	public void setSaletype(String saletype) {
		this.saletype = saletype;
	}
	

}
