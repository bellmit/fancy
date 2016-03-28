package cn.telling.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 
 * @ClassName: ProductDetailVo
 * TODO
 * @author xingle
 * @date 2015-8-18 下午5:01:20
 */
public class ProductDetailVo implements Serializable{
	
	/**
	* @Fields serialVersionUID : TODO(描述变量表示)
	*/
	private static final long serialVersionUID = -6612345805020368281L;

	/**
	* @Fields productid : 产品id
	*/
	private BigDecimal productid;
	/**
	* @Fields productname : 产品名称
	*/
	private String productname;
	/**
	* @Fields hjhtype : 是否好机汇：1 是，0 否
	*/
	private String hjhtype;
	/**
	* @Fields color : 颜色
	*/
	private String color;
	/**
	* @Fields picturepath1 : 产品图片地址
	*/
	private String picturepath1;
	/**
	* @Fields brandid : 品牌id
	*/
	private BigDecimal brandid;
	/**
	* @Fields brandname : 品牌名称
	*/
	private String brandname;
	/**
	* @Fields modelid : 型号id
	*/
	private String modelid;
	/**
	* @Fields productpattern : 型号
	*/
	private String productpattern;
	/**
	* @Fields saletype : 销售类型
	*/
	private String saletype;
	/**
	* @Fields saId : supply_area0 表id
	*/
	private BigDecimal saId;
	/**
	* @Fields spId : shop_product 表id
	*/
	private BigDecimal spId;
	/**
	* @Fields areaid : 销售区域id
	*/
	private BigDecimal areaid;
	/**
	* @Fields customid : 指定客户userid
	*/
	private BigDecimal customid;
	/**
	* @Fields supplyid : 供应商id
	*/
	private BigDecimal supplyid;
	/**
	* @Fields supplyname : 供应商名称
	*/
	private String supplyname;
	/**
	* @Fields shopname : 店铺名称
	*/
	private String shopname;
	/**
	* @Fields priceretailonline : 价格
	*/
	private BigDecimal priceretailonline;
	/**
	* @Fields overplusnumber : 库存
	*/
	private BigDecimal overplusnumber;
	/**
	* @Fields feature : TODO(描述变量表示)
	*/
	private String feature;
	/**
	* @Fields salenum : 销量
	*/
	private BigDecimal salenum;
	/**
	* @Fields hits : 关注
	*/
	private BigDecimal hits;
	/**
	* @Fields onshelftime : 上架时间
	*/
	private String onshelftime;
	/**
	* @Fields sortid : 分类id
	*/
	private BigDecimal sortid;
	/**
	* @Fields sortname : 分类名称
	*/
	private String sortname;
	
	public BigDecimal getProductid() {
		return productid;
	}
	public void setProductid(BigDecimal productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getHjhtype() {
		return hjhtype;
	}
	public void setHjhtype(String hjhtype) {
		this.hjhtype = hjhtype;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPicturepath1() {
		return picturepath1;
	}
	public void setPicturepath1(String picturepath1) {
		this.picturepath1 = picturepath1;
	}
	public BigDecimal getBrandid() {
		return brandid;
	}
	public void setBrandid(BigDecimal brandid) {
		this.brandid = brandid;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public String getProductpattern() {
		return productpattern;
	}
	public void setProductpattern(String productpattern) {
		this.productpattern = productpattern;
	}
	public String getSaletype() {
		return saletype;
	}
	public void setSaletype(String saletype) {
		this.saletype = saletype;
	}
	public BigDecimal getSaId() {
		return saId;
	}
	public void setSaId(BigDecimal saId) {
		this.saId = saId;
	}
	public BigDecimal getSpId() {
		return spId;
	}
	public void setSpId(BigDecimal spId) {
		this.spId = spId;
	}
	public BigDecimal getAreaid() {
		return areaid;
	}
	public void setAreaid(BigDecimal areaid) {
		this.areaid = areaid;
	}
	public BigDecimal getCustomid() {
		return customid;
	}
	public void setCustomid(BigDecimal customid) {
		this.customid = customid;
	}
	public BigDecimal getSupplyid() {
		return supplyid;
	}
	public void setSupplyid(BigDecimal supplyid) {
		this.supplyid = supplyid;
	}
	public String getSupplyname() {
		return supplyname;
	}
	public void setSupplyname(String supplyname) {
		this.supplyname = supplyname;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public BigDecimal getPriceretailonline() {
		return priceretailonline;
	}
	public void setPriceretailonline(BigDecimal priceretailonline) {
		this.priceretailonline = priceretailonline;
	}
	public BigDecimal getOverplusnumber() {
		return overplusnumber;
	}
	public void setOverplusnumber(BigDecimal overplusnumber) {
		this.overplusnumber = overplusnumber;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public BigDecimal getSalenum() {
		return salenum;
	}
	public void setSalenum(BigDecimal salenum) {
		this.salenum = salenum;
	}
	public BigDecimal getHits() {
		return hits;
	}
	public void setHits(BigDecimal hits) {
		this.hits = hits;
	}
	public String getOnshelftime() {
		return onshelftime;
	}
	public void setOnshelftime(String onshelftime) {
		this.onshelftime = onshelftime;
	}
	public BigDecimal getSortid() {
		return sortid;
	}
	public void setSortid(BigDecimal sortid) {
		this.sortid = sortid;
	}
	public String getSortname() {
		return sortname;
	}
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}
	
	

}
