package cn.fancy.spring.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fancy.spring.dao.IShopDao;
import cn.fancy.spring.service.IShopService;
import cn.fancy.spring.vo.Article;
import cn.fancy.spring.vo.ProductSub;
import cn.fancy.spring.vo.ProductVo;
import cn.fancy.spring.vo.Users;

/**   
 * @Title: ShopServiceImpl.java 
 * @Package cn.telling.shop.service.impl 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2016-1-18 下午3:22:14 
 * @version V1.0   
 */
@Service("ShopServiceImpl")
public class ShopServiceImpl implements IShopService {

  @Resource
  private IShopDao shopDao;
  /* (non-Javadoc)
   * @see cn.telling.shop.service.IShopService#getShops()
   */
  @Override
  public List<Users> getShops() {

    return shopDao.getShops();
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
  public List<ProductVo> getPLs(ProductVo pvPr) {
    System.out.println(pvPr.hashCode());
    return shopDao.getPLs();
  }

}

