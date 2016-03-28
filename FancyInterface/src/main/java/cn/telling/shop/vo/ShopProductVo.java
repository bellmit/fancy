package cn.telling.shop.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @ClassName: ShopProductVo
 * TODO
 * @author xingle
 * @date 2015-8-21 下午5:28:04
 */
public class ShopProductVo implements Serializable{

	/**
	* @Fields serialVersionUID : TODO(描述变量表示)
	*/
	private static final long serialVersionUID = -8442887672005420455L;
	/**
	* @Fields shopid : 店铺id
	*/
	private String shopid;
	
	/**
	* @Fields productId : 产品id
	*/
	private BigDecimal productId;
	/**
	* @Fields productName : 产品名称
	*/
	private String productName;
	/**
	* @Fields priceretailonline : 产品价格
	*/
	private BigDecimal priceretailonline;	
	/**
	* @Fields sa_id : supply_area0表id
	*/
	private BigDecimal sa_id;
	/**
	* @Fields feature : 产品feature;
	*/
	private String feature;
	/**
	* @Fields overplusnumber : 库存
	*/
	private BigDecimal overplusnumber;
	/**
	* @Fields picturepath1 : 产品图片路径
	*/
	private String picturepath1;
	/**
	* @Fields saleNum : 销量（提供单独店铺查询用）
	*/
	private BigDecimal saleNum;
	
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
	public BigDecimal getPriceretailonline() {
		return priceretailonline;
	}
	public void setPriceretailonline(BigDecimal priceretailonline) {
		this.priceretailonline = priceretailonline;
	}
	public BigDecimal getSa_id() {
		return sa_id;
	}
	public void setSa_id(BigDecimal sa_id) {
		this.sa_id = sa_id;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public BigDecimal getOverplusnumber() {
		return overplusnumber;
	}
	public void setOverplusnumber(BigDecimal overplusnumber) {
		this.overplusnumber = overplusnumber;
	}
	public String getPicturepath1() {
		return picturepath1;
	}
	public void setPicturepath1(String picturepath1) {
		this.picturepath1 = picturepath1;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public BigDecimal getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(BigDecimal saleNum) {
		this.saleNum = saleNum;
	}
	
	
	
}
