package cn.telling.product.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.telling.common.vo.PageVo;
import cn.telling.index.Search;
import cn.telling.product.dao.IProductDao;
import cn.telling.product.service.IProductService;
import cn.telling.product.vo.AttrTypeVo;
import cn.telling.product.vo.ControlVo;
import cn.telling.product.vo.ProductAttrVo;
import cn.telling.product.vo.ProductMainVo;
import cn.telling.product.vo.ProductRetnVo;
import cn.telling.shop.vo.ShopInfoVo;

/**
 * 
 * @ClassName: ProductServiceImpl
 * 
 * @author xingle
 * @date 2015-7-28 上午10:02:29
 */
@Service
public class ProductServiceImpl implements IProductService {
	
	@Resource
	Search search;
	
	@Resource
	private IProductDao productDao;
	/**
	 * 
	 * @Description:
	 * @param
	 * @return
	 * @author xingle
	 * @data 2015-7-28 上午10:08:55
	 */
	@Override
	public ProductRetnVo getProductLs(String searchProduct, PageVo page,
			BigDecimal minPrice, BigDecimal maxPrice, String sortName,
			String areaid, List<ProductAttrVo> specificationidLs,
			BigDecimal userid, List<AttrTypeVo> attrLs, String rand,
			String isup, ControlVo isControl) {

		ProductRetnVo ls = search.searchProduct1(searchProduct, page, areaid,
				specificationidLs, userid, attrLs, minPrice, maxPrice,
				sortName, rand, isup, isControl);
		return ls;
	}

	

	/* (non-Javadoc)
	 * @see cn.telling.product.service.IProductService#getShopInfo(java.lang.String)
	 */
	@Override
	public List<ShopInfoVo> getShopInfo(String shopid) {
	
		return null;
	}



	/* (non-Javadoc)
	 * @see cn.telling.product.service.IProductService#getProductMainLs()
	 */
	@Override
	public List<ProductMainVo> getProductMainLs() {
		// TODO Auto-generated method stub
		return productDao.getProductMainLs();
	}

}
