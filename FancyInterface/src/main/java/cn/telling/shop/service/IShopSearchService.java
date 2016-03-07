package cn.telling.shop.service;

import java.math.BigDecimal;
import java.util.List;

import cn.telling.common.vo.PageVo;
import cn.telling.shop.vo.ShopInfoVo;

/**
 * 
 * @ClassName: IShopSearchService
 * TODO
 * @author xingle
 * @date 2015-8-24 上午9:50:04
 */
public interface IShopSearchService {
	
	/**
	 * 店铺搜索
	 * @param searchShop
	 * 	搜索店铺关键词
	 * @param page
	 * 	(pageNow,messageForPage 两个参数必须)
	 * @param userid
	 * 	(可选) 登录用户id
	 * @param areaIdLs
	 * 	(可选) 筛选店铺所在地区域id列表
	 * @param areaid
	 * 	(可选) 登录用户的区域id
	 * @param shopType
	 *  (可选) 店铺类型 {@code 1} 金冠店，{@code 2} 皇冠店，{@code 3} 钻级店，{@code 4} 心级店
	 * @param goodRate
	 * 	(可选) 好评率 (输入0~100区间值，例如，100 代表100%好评，99代表99%以上好评)
	 * @param isBuy
	 *  (可选) 是否收藏和购买过 {@code true} 收藏或者购买过(如果userid为空，该项无效)
	 *  @param rand
	 *  (可选) 排序类型 (默认按照销量排序){@code 1} 销量，{@code 2} 信用
	 *  @param isup
	 *  (可选) 排序顺序(默认按照升序) {@code 1} 升序，{@code 2} 降序
	 * @return
	 * @author xingle
	 * @data 2015-8-24 上午9:55:46
	 */
	public List<ShopInfoVo> getShopSearchLs(String searchShop, PageVo page,BigDecimal userid,
			List<String> areaIdLs, String areaid,String shopType, BigDecimal goodRate,
			boolean isBuy,String rand,String isup);
	
	/**
	 * 店铺搜索（只返回店铺，店铺产品不返回）
	 * @param searchShop
	 * 	搜索店铺关键词
	 * @param page
	 * 	(pageNow,messageForPage 两个参数必须)
	 * @param userid
	 *  (可选) 登录用户id
	 * @param areaIdLs
	 *  (可选) 筛选店铺所在地区域id列表
	 * @param areaid
	 * 	(可选) 登录用户的区域id
	 * @param shopType
	 *  (可选) 店铺类型 {@code 1} 金冠店，{@code 2} 皇冠店，{@code 3} 钻级店，{@code 4} 心级店
	 * @param goodRate
	 * 	(可选) 好评率 (输入0~100区间值，例如，100 代表100%好评，99代表99%以上好评)
	 * @param isBuy
	 *  (可选) 是否收藏和购买过 {@code true} 收藏或者购买过(如果userid为空，该项无效)
	 * @param rand
	 *  (可选) 排序类型 (默认按照销量排序){@code 1} 销量，{@code 2} 信用
	 * @param isup
	 * 	(可选) 排序顺序(默认按照升序) {@code 1} 升序，{@code 2} 降序
	 * @return
	 * @author xingle
	 * @data 2015-9-6 下午4:48:00
	 */
	public List<ShopInfoVo> getShopSearchExcludeProLs(String searchShop, PageVo page,BigDecimal userid,
			List<String> areaIdLs, String areaid,String shopType, BigDecimal goodRate,
			boolean isBuy,String rand,String isup);
	
	/**
	 * 获取单个店铺信息
	 * @param shopid
	 * @return
	 * @author xingle
	 * @data 2015-9-7 下午6:12:58
	 */
	public List<ShopInfoVo> getShopInfo(String shopid);

}
