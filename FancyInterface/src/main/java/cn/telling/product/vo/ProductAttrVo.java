package cn.telling.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @ClassName: productAttrVo
 * TODO
 * @author xingle
 * @date 2015-7-31 下午5:41:10
 */
public class ProductAttrVo implements Serializable{
	
	/**
	* @Fields serialVersionUID : TODO(描述变量表示)
	*/
	private static final long serialVersionUID = -77022423768647817L;
	private BigDecimal productid;	
	private BigDecimal specificationid;
    private String specificationname;
    private String parametervalue;
	public BigDecimal getProductid() {
		return productid;
	}
	public void setProductid(BigDecimal productid) {
		this.productid = productid;
	}
	public BigDecimal getSpecificationid() {
		return specificationid;
	}
	public void setSpecificationid(BigDecimal specificationid) {
		this.specificationid = specificationid;
	}
	public String getSpecificationname() {
		return specificationname;
	}
	public void setSpecificationname(String specificationname) {
		this.specificationname = specificationname;
	}
	public String getParametervalue() {
		return parametervalue;
	}
	public void setParametervalue(String parametervalue) {
		this.parametervalue = parametervalue;
	}
    
    

}
