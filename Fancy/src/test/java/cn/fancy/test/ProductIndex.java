package cn.fancy.test;

import java.io.Serializable;
import java.util.List;

import cn.telling.shop.vo.ProductSub;
import cn.telling.shop.vo.ProductVo;

public class ProductIndex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -962482644949621794L;

	private ProductVo pv;
	private List<ProductSub> psLs;

	public ProductVo getPv() {
		return pv;
	}

	public void setPv(ProductVo pv) {
		this.pv = pv;
	}

	public List<ProductSub> getPsLs() {
		return psLs;
	}

	public void setPsLs(List<ProductSub> psLs) {
		this.psLs = psLs;
	}

}
