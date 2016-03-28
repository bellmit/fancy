/**
 * 
 * Project Name:myweb-web
 * File Name:ProductVo.java
 * Package Name:cn.fancy.test
 * Date:2015-7-31
 *
 */

package cn.telling.shop.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ClassName:ProductVo <br/>
 * 
 * @author caosheng
 */
public class ProductVo implements Serializable {
	/**
	 * serialVersionUID:TODO
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = -600922009112651404L;
	private BigDecimal productId;
	private String productName;
	private String picturePath;
	private String brandName;
	private String productPattern;
	private String memory;
	private String color;
	private String category;
	private String network;
	private String screen;
	private String sysname;
	private String saleType;
	public BigDecimal getProductId()
	{
		return productId;
	}
	public void setProductId(BigDecimal productId)
	{
		this.productId = productId;
	}
	public String getProductName()
	{
		return productName;
	}
	public void setProductName(String productName)
	{
		this.productName = productName;
	}
	public String getPicturePath()
	{
		return picturePath;
	}
	public void setPicturePath(String picturePath)
	{
		this.picturePath = picturePath;
	}
	public String getBrandName()
	{
		return brandName;
	}
	public void setBrandName(String brandName)
	{
		this.brandName = brandName;
	}
	public String getProductPattern()
	{
		return productPattern;
	}
	public void setProductPattern(String productPattern)
	{
		this.productPattern = productPattern;
	}
	public String getMemory()
	{
		return memory;
	}
	public void setMemory(String memory)
	{
		this.memory = memory;
	}
	public String getColor()
	{
		return color;
	}
	public void setColor(String color)
	{
		this.color = color;
	}
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}
	public String getNetwork()
	{
		return network;
	}
	public void setNetwork(String network)
	{
		this.network = network;
	}
	public String getScreen()
	{
		return screen;
	}
	public void setScreen(String screen)
	{
		this.screen = screen;
	}
	public String getSysname()
	{
		return sysname;
	}
	public void setSysname(String sysname)
	{
		this.sysname = sysname;
	}
	public String getSaleType()
	{
		return saleType;
	}
	public void setSaleType(String saleType)
	{
		this.saleType = saleType;
	}
	@Override
	public String toString() {
		return "ProductVo [productId=" + productId + ", productName="
				+ productName + ", picturePath=" + picturePath + ", brandName="
				+ brandName + ", productPattern=" + productPattern
				+ ", memory=" + memory + ", color=" + color + ", category="
				+ category + ", network=" + network + ", screen=" + screen
				+ ", sysname=" + sysname + ", saleType=" + saleType + "]";
	}

	

}
