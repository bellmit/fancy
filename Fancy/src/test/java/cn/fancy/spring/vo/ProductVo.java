package cn.fancy.spring.vo;

import java.io.Serializable;


/**
 * 
 * @ClassName: ProductVo
 * TODO
 * @author xingle
 * @date 2015-7-28 上午10:03:44
 */
public class ProductVo implements Serializable{
	
	private static final long serialVersionUID = -2202699500502185065L;
  private String id;
	private String productId;
	private String productName;
	private String productPrice;
	private String saletype;
	
	
	public String getSaletype() {
    return saletype;
  }
  public void setSaletype(String saletype) {
    this.saletype = saletype;
  }
  public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	
	

}
