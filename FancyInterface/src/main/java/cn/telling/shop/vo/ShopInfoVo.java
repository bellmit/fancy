package cn.telling.shop.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @ClassName: ShopInfoVo
 * TODO
 * @author xingle
 * @date 2015-8-21 下午4:54:54
 */
public class ShopInfoVo implements Serializable{

	/**
	* @Fields serialVersionUID : TODO(描述变量表示)
	*/
	private static final long serialVersionUID = -4539123817408752461L;

	/**
	* @Fields shopid : 店铺id
	*/
	private String shopid;
	/**
	* @Fields shopname : 店铺名称
	*/
	private String shopname;
	/**
	* @Fields supplyid : 供应商id
	*/
	private BigDecimal supplyid;
	/**
	* @Fields selleruserid : 供应商userid
	*/
	private BigDecimal selleruserid;
	/**
	* @Fields supplyAreaId : 供应商省区域id
	*/
	private BigDecimal supplyAreaId;
	/**
	* @Fields supplyAreaName : 供应商省区域名称
	*/
	private String supplyAreaName;
	/**
	* @Fields supplyType : 供应商类型
	*/
	private String supplyType;
	/**
	* @Fields shoplogo : 店铺logo
	*/
	private String shoplogo;
	/**
	* @Fields shopIntro : 店铺简介
	*/
	private String shopIntro;
	/**
	* @Fields goodrate : 好评率（建索引用）
	*/
	private BigDecimal goodrate;
	/**
	* @Fields shoplever : 店铺等级（建索引用）
	*/
	private BigDecimal shoplever;
	/**
	* @Fields salenum : 销量
	*/
	private BigDecimal salenum;
	/**
	* @Fields shopProLs : 店铺产品列表
	*/
	private List<ShopProductVo> shopProLs;
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public BigDecimal getSupplyid() {
		return supplyid;
	}
	public void setSupplyid(BigDecimal supplyid) {
		this.supplyid = supplyid;
	}
	public BigDecimal getSelleruserid() {
		return selleruserid;
	}
	public void setSelleruserid(BigDecimal selleruserid) {
		this.selleruserid = selleruserid;
	}
	public BigDecimal getSupplyAreaId() {
		return supplyAreaId;
	}
	public void setSupplyAreaId(BigDecimal supplyAreaId) {
		this.supplyAreaId = supplyAreaId;
	}
	public String getSupplyAreaName() {
		return supplyAreaName;
	}
	public void setSupplyAreaName(String supplyAreaName) {
		this.supplyAreaName = supplyAreaName;
	}
	public String getSupplyType() {
		return supplyType;
	}
	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}
	public String getShoplogo() {
		return shoplogo;
	}
	public void setShoplogo(String shoplogo) {
		this.shoplogo = shoplogo;
	}
	public String getShopIntro() {
		return shopIntro;
	}
	public void setShopIntro(String shopIntro) {
		this.shopIntro = shopIntro;
	}
	public BigDecimal getGoodrate() {
		return goodrate;
	}
	public void setGoodrate(BigDecimal goodrate) {
		this.goodrate = goodrate;
	}
	public BigDecimal getShoplever() {
		return shoplever;
	}
	public void setShoplever(BigDecimal shoplever) {
		this.shoplever = shoplever;
	}
	public BigDecimal getSalenum() {
		return salenum;
	}
	public void setSalenum(BigDecimal salenum) {
		this.salenum = salenum;
	}
	public List<ShopProductVo> getShopProLs() {
		return shopProLs;
	}
	public void setShopProLs(List<ShopProductVo> shopProLs) {
		this.shopProLs = shopProLs;
	}
	
	
}
