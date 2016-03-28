package cn.telling.shop.service;

import java.math.BigDecimal;
import java.util.List;

import cn.telling.shop.vo.Article;
import cn.telling.shop.vo.ProductSub;
import cn.telling.shop.vo.ProductVo;

/**
 * @Title: IShopServiceImpl.java
 * @Package com.fancy.shop.service
 * @Description: TODO(描述该文件做什么)
 * @author wangshu
 * @date 2015年7月27日 下午8:10:04
 * @version V1.0
 */
public interface IShopService {
	public List<Article> getShops();

	public List<ProductSub> getTongxun();

	public List<ProductSub> getProvince();

	public List<ProductVo> getProduct();

	/***
	 * 获取非好机汇的
	 * 
	 * @param pId
	 * @return
	 */
	public List<ProductSub> getProSub(BigDecimal pId);

	/****
	 * 获取好机汇
	 * 
	 * @param pId
	 * @return
	 */
	public List<ProductSub> getHJHProSub(BigDecimal pId);

	public List<ProductSub> getProSub();

	public List<ProductSub> getHJHProSub();
	
	public List<ProductVo>getPLs();
}
