package cn.telling.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @ClassName: ProductSortVo
 * TODO
 * @author xingle
 * @date 2015-8-18 下午4:53:08
 */
public class ProductSortVo implements Serializable{
	/**
	* @Fields serialVersionUID : TODO(描述变量表示)
	*/
	private static final long serialVersionUID = -2463256542828604550L;
	/**
	* @Fields sortId : 类目id
	*/
	private BigDecimal sortId;	
	/**
	* @Fields sortName : 全部分类类名
	*/
	private String sortName; 
	/**
	* @Fields num : 各个分类的产品数量
	*/
	private BigDecimal num;
	public BigDecimal getSortId() {
		return sortId;
	}
	public void setSortId(BigDecimal sortId) {
		this.sortId = sortId;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public BigDecimal getNum() {
		return num;
	}
	public void setNum(BigDecimal num) {
		this.num = num;
	}
	
	

}
