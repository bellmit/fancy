package cn.telling.shop.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.telling.shop.dao.IShopDao;
import cn.telling.shop.service.IShopService;
import cn.telling.shop.vo.Article;
import cn.telling.shop.vo.ProductSub;
import cn.telling.shop.vo.ProductVo;

/**   
 * @Title: ShopServiceImpl.java 
 * @Package cn.telling.shop.service.impl 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2016-1-18 下午3:22:14 
 * @version V1.0   
 */
@Service
public class ShopServiceImpl implements IShopService {

  @Resource
  private IShopDao shopDao;
  /* (non-Javadoc)
   * @see cn.telling.shop.service.IShopService#getShops()
   */
  @Override
  public List<Article> getShops() {
    //  Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see cn.telling.shop.service.IShopService#getTongxun()
   */
  @Override
  public List<ProductSub> getTongxun() {
    //  Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see cn.telling.shop.service.IShopService#getProvince()
   */
  @Override
  public List<ProductSub> getProvince() {
    //  Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see cn.telling.shop.service.IShopService#getProduct()
   */
  @Override
  public List<ProductVo> getProduct() {
    //  Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see cn.telling.shop.service.IShopService#getProSub(java.math.BigDecimal)
   */
  @Override
  public List<ProductSub> getProSub(BigDecimal pId) {
    //  Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see cn.telling.shop.service.IShopService#getHJHProSub(java.math.BigDecimal)
   */
  @Override
  public List<ProductSub> getHJHProSub(BigDecimal pId) {
    //  Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see cn.telling.shop.service.IShopService#getProSub()
   */
  @Override
  public List<ProductSub> getProSub() {
    //  Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see cn.telling.shop.service.IShopService#getHJHProSub()
   */
  @Override
  public List<ProductSub> getHJHProSub() {
    //  Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see cn.telling.shop.service.IShopService#getPLs()
   */
  @Override
  public List<ProductVo> getPLs() {
    return shopDao.getPLs();
  }

}

