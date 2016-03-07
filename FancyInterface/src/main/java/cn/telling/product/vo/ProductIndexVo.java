package cn.telling.product.vo;

import java.util.List;

/**
 * 
 * @ClassName: ProductIndexVo
 * @author xingle
 * @date 2015-7-30 下午7:30:25
 */
public class ProductIndexVo {
	
	private ProductMainVo promVo;
	private List<ProductSubVo> prodLs;
	public ProductMainVo getPromVo() {
		return promVo;
	}
	public void setPromVo(ProductMainVo promVo) {
		this.promVo = promVo;
	}
	public List<ProductSubVo> getProdLs() {
		return prodLs;
	}
	public void setProdLs(List<ProductSubVo> prodLs) {
		this.prodLs = prodLs;
	}
	
	

}
