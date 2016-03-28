package cn.telling.product.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.telling.common.vo.PageVo;
import cn.telling.product.vo.ControlVo;
import cn.telling.product.vo.ProductAttrVo;
import cn.telling.product.vo.ProductDetailVo;
import cn.telling.product.vo.ProductInfoVo;
import cn.telling.product.vo.ProductMainVo;
import cn.telling.product.vo.ProductSortVo;
import cn.telling.product.vo.ProductSubVo;
import cn.telling.shop.vo.ShopInfoVo;

/**
 * 
 * @ClassName: IProductDao
 * TODO
 * @author xingle
 * @date 2015-7-31 下午1:23:52
 */
public interface IProductDao {

	/**
	 * 获取产品主表基本信息
	 * @return
	 * @author xingle
	 * @data 2015-7-31 下午5:57:03
	 */
	List<ProductMainVo> getProductMainLs();

	/**
	 * 获取产品的属性信息
	 * @param productId
	 * @return
	 * @author xingle
	 * @data 2015-7-31 下午6:08:54
	 */
	List<ProductAttrVo> getProductAttrLs(BigDecimal productId);

	/**
	 * 获取好机汇产品子表信息
	 * @param productId
	 * @return
	 * @author xingle
	 * @data 2015-7-31 下午6:14:19
	 */
	List<ProductSubVo> getProductSubByHjhLs(BigDecimal productId);

	/**
	 * 获取非好机汇产品子表信息
	 * @param productId
	 * @return
	 * @author xingle
	 * @data 2015-7-31 下午6:18:46
	 */
	List<ProductSubVo> getProductSubLs(BigDecimal productId);

	/**
	 * 获取所有主产品信息列表
	 * @return
	 * @author xingle
	 * @data 2015-8-6 上午10:55:12
	 */
	List<ProductInfoVo> getProductMainLs1();

	/**
	 * TODO
	 * @param pls
	 * @param areaLs
	 * @param userid
	 * @return
	 * @author xingle
	 * @data 2015-8-11 上午11:30:16
	 */
	List<ProductDetailVo> getFilterProductLs(List<String> pls,
			List<String> areaLs, BigDecimal userid, PageVo page, String rand,
			String isup, ControlVo isControl);

	/**
	 * 获取区域id的上卷所有区域列表
	 * @param areaid
	 * @return
	 * @author xingle
	 * @data 2015-8-11 上午9:14:50
	 */
	List<String> getAreaLs(BigDecimal areaid);

	/**
	 * TODO
	 * @param total
	 * @return
	 * @author xingle
	 * @data 2015-8-12 上午9:31:44
	 */
	List<ProductInfoVo> getProductMainSegLs(int total, int current);

	/**
	 * TODO
	 * @param pls
	 * @param areaLs
	 * @param userid
	 * @return
	 * @author xingle
	 * @data 2015-8-18 下午4:55:13
	 */
	List<ProductSortVo> getProductSort(List<String> pls, List<String> areaLs,
			BigDecimal userid,ControlVo isControl);

	/**
	 * 获取店铺新建索引信息
	 * @return
	 * @author xingle
	 * @data 2015-8-24 上午11:33:19
	 */
	List<ShopInfoVo> getShopIndexLs();

	/**
	 * 获取省id
	 * @param areaid
	 * @return
	 * @author xingle
	 * @data 2015-8-25 下午2:18:03
	 */
	BigDecimal getProvinceIdByAreaid(String areaid);

	/**
	 * 更新产品销量表
	 * @return
	 * @author xingle
	 * @data 2015-8-27 下午1:59:14
	 */
	void updateProductSaleNum();

	/**
	 * 更新供应商产品销量表
	 * @return
	 * @author xingle
	 * @data 2015-8-27 下午1:59:32
	 */
	void updatesSupplyProductSaleNum();


}
