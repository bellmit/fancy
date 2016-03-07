/**
 * 
 * Project Name:myweb-web
 * File Name:ProductSub.java
 * Package Name:cn.fancy.test
 * Date:2015-7-31
 *
 */

package cn.telling.shop.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ClassName:ProductSub <br/>
 * 
 * @author caosheng
 */
public class ProductSub implements Serializable {

	/**
	 * serialVersionUID:TODO
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 7979460742177929673L;

	/***
	 * shop_product productid
	 */
	private BigDecimal spProductId;

	/***
	 * shop_product表id
	 */
	private BigDecimal spId;
	private BigDecimal saId;
	private BigDecimal supplyId;
	private String supplyName;
	private String shopName;
	private BigDecimal areaId;
	private BigDecimal customerId;
	private BigDecimal priceretailonline;
	private BigDecimal overplusNumber;
	private BigDecimal hits;
	private String onshelftime;
	private BigDecimal saleqty;

	public BigDecimal getSpProductId()
	{
		return spProductId;
	}



	public void setSpProductId(BigDecimal spProductId)
	{
		this.spProductId = spProductId;
	}

	public BigDecimal getSpId()
	{
		return spId;
	}

	public void setSpId(BigDecimal spId)
	{
		this.spId = spId;
	}

	public BigDecimal getSaId()
	{
		return saId;
	}

	public void setSaId(BigDecimal saId)
	{
		this.saId = saId;
	}

	public BigDecimal getSupplyId()
	{
		return supplyId;
	}

	public void setSupplyId(BigDecimal supplyId)
	{
		this.supplyId = supplyId;
	}

	public String getSupplyName()
	{
		return supplyName;
	}

	public void setSupplyName(String supplyName)
	{
		this.supplyName = supplyName;
	}

	public String getShopName()
	{
		return shopName;
	}

	public void setShopName(String shopName)
	{
		this.shopName = shopName;
	}

	public BigDecimal getAreaId()
	{
		return areaId;
	}

	public void setAreaId(BigDecimal areaId)
	{
		this.areaId = areaId;
	}

	public BigDecimal getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(BigDecimal customerId)
	{
		this.customerId = customerId;
	}

	public BigDecimal getPriceretailonline()
	{
		return priceretailonline;
	}

	public void setPriceretailonline(BigDecimal priceretailonline)
	{
		this.priceretailonline = priceretailonline;
	}

	public BigDecimal getOverplusNumber()
	{
		return overplusNumber;
	}

	public void setOverplusNumber(BigDecimal overplusNumber)
	{
		this.overplusNumber = overplusNumber;
	}

	public BigDecimal getHits()
	{
		return hits;
	}

	public void setHits(BigDecimal hits)
	{
		this.hits = hits;
	}

	public String getOnshelftime()
	{
		return onshelftime;
	}

	public void setOnshelftime(String onshelftime)
	{
		this.onshelftime = onshelftime;
	}

	public BigDecimal getSaleqty()
	{
		return saleqty;
	}

	public void setSaleqty(BigDecimal saleqty)
	{
		this.saleqty = saleqty;
	}

	@Override
	public String toString()
	{
		return "ProductSub [spProductId=" + spProductId + ", spId=" + spId + ", saId=" + saId + ", supplyId="
				+ supplyId + ", supplyName=" + supplyName + ", shopName=" + shopName + ", areaId=" + areaId
				+ ", customerId=" + customerId + ", priceretailonline=" + priceretailonline + ", overplusNumber="
				+ overplusNumber + ", hits=" + hits + ", onshelftime=" + onshelftime + ", saleqty=" + saleqty + "]";
	}

}
