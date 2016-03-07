package cn.telling.product.vo;

import java.math.BigDecimal;

/**
 * 产品主表Vo
 * @ClassName: ProductSubVo
 * @author xingle
 * @date 2015-7-30 下午6:13:41
 */
public class ProductSubVo {
	
	/**
	* @Fields spProductId : shop_product表productid
	*/
	private BigDecimal spProductId;		
	/**
	* @Fields spId : shop_product 表id
	*/
	private BigDecimal spId;		
	/**
	* @Fields saId : supply_area0 表id
	*/
	private BigDecimal saId;		
	/**
	* @Fields supplyId : 供应商id
	*/
	private BigDecimal supplyId;		
	/**
	* @Fields supplyName : 供应商名称
	*/
	private String supplyName;		
	/**
	* @Fields shopName : 店铺名称
	*/
	private String shopName; 	
	/**
	* @Fields areaId : 区域id
	*/
	private BigDecimal areaId; 		
	/**
	* @Fields customerId : 指定客户id
	*/
	private BigDecimal customerId;		
	/**
	* @Fields priceretailonline : 价格
	*/
	private BigDecimal priceretailonline;		
	/**
	* @Fields overplusNumber : 库存
	*/
	private BigDecimal overplusNumber;    			
	/**
	* @Fields hits : 关注度
	*/
	private BigDecimal hits;		
	/**
	* @Fields onshelftime : 上架时间
	*/
	private String onshelftime;		
	/**
	* @Fields saleNum : 销量
	*/
	private BigDecimal saleNum;

	
	public BigDecimal getSpProductId() {
		return spProductId;
	}
	public void setSpProductId(BigDecimal spProductId) {
		this.spProductId = spProductId;
	}
	public BigDecimal getSpId() {
		return spId;
	}
	public void setSpId(BigDecimal spId) {
		this.spId = spId;
	}
	public BigDecimal getSaId() {
		return saId;
	}
	public void setSaId(BigDecimal saId) {
		this.saId = saId;
	}
	public BigDecimal getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(BigDecimal supplyId) {
		this.supplyId = supplyId;
	}
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public BigDecimal getAreaId() {
		return areaId;
	}
	public void setAreaId(BigDecimal areaId) {
		this.areaId = areaId;
	}
	public BigDecimal getCustomerId() {
		return customerId;
	}
	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getPriceretailonline() {
		return priceretailonline;
	}
	public void setPriceretailonline(BigDecimal priceretailonline) {
		this.priceretailonline = priceretailonline;
	}
	public BigDecimal getOverplusNumber() {
		return overplusNumber;
	}
	public void setOverplusNumber(BigDecimal overplusNumber) {
		this.overplusNumber = overplusNumber;
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
	public BigDecimal getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(BigDecimal saleNum) {
		this.saleNum = saleNum;
	}


}
