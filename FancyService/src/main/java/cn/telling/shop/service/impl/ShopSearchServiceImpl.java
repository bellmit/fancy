package cn.telling.shop.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.telling.common.vo.PageVo;
import cn.telling.index.Search;
import cn.telling.product.dao.IProductDao;
import cn.telling.product.vo.ProductMainVo;
import cn.telling.shop.service.IShopSearchService;
import cn.telling.shop.vo.ShopInfoVo;

/**
 * 
 * @ClassName: ShopSearchServiceImpl
 * TODO
 * @author xingle
 * @date 2015-8-24 上午9:51:32
 */
@Service
public class ShopSearchServiceImpl implements IShopSearchService{
	
	@Resource
	Search search;
	
	@Resource
	private IProductDao productDao;
	/**
	 * 
	 * @Description: TODO
	 * @param searchShop
	 * @param page
	 * @param userid
	 * @param areaIdLs
	 * @param areaid
	 * @param shopType
	 * @param goodRate
	 * @param isBuy
	 * @param rand
	 * @param isup
	 * @return
	 * @author xingle
	 * @data 2015-8-25 下午4:51:12
	 */
	@Override
	public List<ShopInfoVo> getShopSearchLs(String searchShop, PageVo page,
			BigDecimal userid, List<String> areaIdLs, String areaid,
			String shopType, BigDecimal goodRate, boolean isBuy, String rand,
			String isup) {
		List<ShopInfoVo> ls = search.searchShop(searchShop, page, areaIdLs, areaid,
				shopType, goodRate, isBuy, userid, rand, isup);

		return ls;
	}

	@Override
	public List<ShopInfoVo> getShopSearchExcludeProLs(String searchShop,
			PageVo page, BigDecimal userid, List<String> areaIdLs,
			String areaid, String shopType, BigDecimal goodRate, boolean isBuy,
			String rand, String isup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShopInfoVo> getShopInfo(String shopid) {
		List<ProductMainVo> pmLs=productDao.getProductMainLs();
		System.out.println(pmLs.toArray());
		return null;
	}

}
