package cn.telling.common.Vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class LoginingVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 当前登陆用户id
	 */
	private BigDecimal login_id;
	/**
	 * 用户类型
	 */
	private String categoryId;
	/**
	 * 用户区域列表
	 */
	private List<BigDecimal> areaLs;
	/**
	 * 用户角色  
	 * yuanquan 
	 */
	private String productPricePoliceRole;
	
	
	public BigDecimal getLogin_id() {
		return login_id;
	}
	public void setLogin_id(BigDecimal login_id) {
		this.login_id = login_id;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public List<BigDecimal> getAreaLs() {
		return areaLs;
	}
	public void setAreaLs(List<BigDecimal> areaLs) {
		this.areaLs = areaLs;
	}
	public String getProductPricePoliceRole() {
		return productPricePoliceRole;
	}
	public void setProductPricePoliceRole(String productPricePoliceRole) {
		this.productPricePoliceRole = productPricePoliceRole;
	}
	
}
