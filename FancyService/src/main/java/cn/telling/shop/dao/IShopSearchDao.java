package cn.telling.shop.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.telling.common.vo.PageVo;
import cn.telling.shop.vo.ShopInfoVo;
import cn.telling.shop.vo.ShopProductVo;

/**
 * 
 * @ClassName: IShopSearchDao
 * TODO
 * @author xingle
 * @date 2015-8-26 下午4:06:58
 */
public interface IShopSearchDao {


	/**
	 * 获取店铺信息
	 * @param shopls
	 * @param page
	 * @param isBuy
	 * @param userid
	 * @param areaid
	 * @param rand
	 * @param isup
	 * @param areaIdLs
	 * @return
	 * @author xingle
	 * @data 2015-8-26 下午6:36:23
	 */
	List<ShopInfoVo> getShopInfoLs(List<String> shopls, PageVo page,
			boolean isBuy, BigDecimal userid, String areaid, String rand,
			String isup,List<String> areaIdLs);

	/**
	 * 获取店铺产品
	 * @param shopLs
	 * @return
	 * @author xingle
	 * @data 2015-8-26 下午4:15:48
	 */
	List<ShopProductVo> getShopProductLs(List<ShopInfoVo> shopLs);

}
