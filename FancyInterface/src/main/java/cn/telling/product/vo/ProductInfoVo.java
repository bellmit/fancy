package cn.telling.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @ClassName: ProductInfoVo
 * TODO
 * @author xingle
 * @date 2015-8-6 下午1:32:31
 */
public class ProductInfoVo implements Serializable{
	
	
	/**
	* @Fields serialVersionUID : TODO(描述变量表示)
	*/
	private static final long serialVersionUID = -2493620679955224646L;
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
	private String brand;			
	/**
	* @Fields productpattern : 产品型号
	*/
	private String productpattern;			 		
	/**
	* @Fields color : 产品颜色
	*/
	private String color;     		
	/**
	* @Fields category : 产品类别（手机，平板等）
	*/
	private String category; 		
		
	/**
	* @Fields saletype : 销售类型
	*/
	private String saletype;	
	
	/**
	* @Fields productAttrs : 产品属性
	*/
	private String productAttribute;
	
	/**
	* @Fields proAttrLs : 产品属性列表
	*/
	private List<ProductAttrVo> proAttrLs;
	
	/**
	* @Fields minprice : TODO(描述变量表示)
	*/
	private BigDecimal minprice;
	
	/**
	* @Fields maxprice : TODO(描述变量表示)
	*/
	private BigDecimal maxprice;

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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProductpattern() {
		return productpattern;
	}

	public void setProductpattern(String productpattern) {
		this.productpattern = productpattern;
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

	public String getSaletype() {
		return saletype;
	}

	public void setSaletype(String saletype) {
		this.saletype = saletype;
	}

	public String getProductAttribute() {
		return productAttribute;
	}

	public void setProductAttribute(String productAttribute) {
		this.productAttribute = productAttribute;
	}

	public List<ProductAttrVo> getProAttrLs() {
		return proAttrLs;
	}

	public void setProAttrLs(List<ProductAttrVo> proAttrLs) {
		this.proAttrLs = proAttrLs;
	}

	public BigDecimal getMinprice() {
		return minprice;
	}

	public void setMinprice(BigDecimal minprice) {
		this.minprice = minprice;
	}

	public BigDecimal getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(BigDecimal maxprice) {
		this.maxprice = maxprice;
	}
	
	

}
