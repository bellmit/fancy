package cn.telling.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @ClassName: ProductSaleVo
 * TODO
 * @author xingle
 * @date 2015-8-27 下午1:05:48
 */
public class ProductSaleVo implements Serializable{

	/**
	* @Fields serialVersionUID : TODO(描述变量表示)
	*/
	private static final long serialVersionUID = -6954252922401322710L;
	
	private BigDecimal product_id;
	private BigDecimal supply_area_id;
	private BigDecimal salenum;
	public BigDecimal getProduct_id() {
		return product_id;
	}
	public void setProduct_id(BigDecimal product_id) {
		this.product_id = product_id;
	}
	public BigDecimal getSupply_area_id() {
		return supply_area_id;
	}
	public void setSupply_area_id(BigDecimal supply_area_id) {
		this.supply_area_id = supply_area_id;
	}
	public BigDecimal getSalenum() {
		return salenum;
	}
	public void setSalenum(BigDecimal salenum) {
		this.salenum = salenum;
	}


}
