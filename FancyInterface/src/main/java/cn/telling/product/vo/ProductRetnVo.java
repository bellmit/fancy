package cn.telling.product.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: ProductRetnVo
 * 
 * @author xingle
 * @date 2015-8-13 上午11:23:57
 */
public class ProductRetnVo implements Serializable{

	/**
	* @Fields serialVersionUID : TODO(描述变量表示)
	*/
	private static final long serialVersionUID = -8027352531246264527L;
	
	/**
	* @Fields proLs : 分页返回产品列表
	*/
	private List<ProductDetailVo> proLs;
	/**
	* @Fields total : 未分页总数
	*/
	private int total;
	/**
	* @Fields sortLs : 产品分类统计列表
	*/
	private List<ProductSortVo> sortLs;
	
	
	public List<ProductDetailVo> getProLs() {
		return proLs;
	}
	public void setProLs(List<ProductDetailVo> proLs) {
		this.proLs = proLs;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<ProductSortVo> getSortLs() {
		return sortLs;
	}
	public void setSortLs(List<ProductSortVo> sortLs) {
		this.sortLs = sortLs;
	}
	
	
	
}
