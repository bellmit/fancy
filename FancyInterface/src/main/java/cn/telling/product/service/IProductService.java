package cn.telling.product.service;

import java.math.BigDecimal;
import java.util.List;

import cn.telling.common.vo.PageVo;
import cn.telling.product.vo.AttrTypeVo;
import cn.telling.product.vo.ControlVo;
import cn.telling.product.vo.ProductAttrVo;
import cn.telling.product.vo.ProductMainVo;
import cn.telling.product.vo.ProductRetnVo;
import cn.telling.shop.vo.ShopInfoVo;
/**
 * 
 * @ClassName: IProductService
 * @author xingle
 * @date 2015-7-28 上午10:01:17
 */
public interface IProductService {

	/**
	 * TODO
	 * @param searchProduct
	 * @param page
	 * 	(pageNow,messageForPage 两个参数必须)
	 * @param minPrice
	 * 	(可选) ， 最低价格,可为null
	 * @param maxPrice
	 * 	(可选) ， 最高价格,可为null
	 * @param sortName
	 * 	(可选) ， 分类类别名称，如手机等
	 * @param areaid
	 * 	(可选) 当前用户的区域id （过滤区域的产品用；空的话只查通讯市场的产品）
	 * @param specificationidLs
	 * 	(可选) 精确查找id列表，如果有值则为精确查找，会忽略传入搜索文本的值，其中specificationid，parametervalue 必须（内存  网络制式  操作系统 屏幕尺寸 等等 ）
	 * @param userid
	 * 	(可选) 当前用户userid（过滤指定客户的产品用；空的话只查通讯市场的产品）
	 * @param attrLs
	 * 	(可选) 精确查找 品牌 型号 颜色  
	 * @param rand
	 * 	(可选) 排序类型 (默认按照销量排序){@code 1} 销量，{@code 2} 价格，{@code 3} 上架时间，{@code 4} 点击量
	 * @param isup
	 * 	(可选) 排序顺序(默认按照升序) {@code 1} 升序，{@code 2} 降序
	 * @param isControl
	 * 	(可选) 受控条件 (如果受控，需传受控相应条件)
	 * @return
	 * @author xingle
	 * @data 2015-8-18 下午6:03:20
	 */
	public ProductRetnVo getProductLs(String searchProduct, PageVo page,
			BigDecimal minPrice, BigDecimal maxPrice, String sortName,
			String areaid, List<ProductAttrVo> specificationidLs,
			BigDecimal userid, List<AttrTypeVo> attrLs, String rand,
			String isup,ControlVo isControl);
	
	public List<ShopInfoVo> getShopInfo(String shopid);
	List<ProductMainVo> getProductMainLs();

}
