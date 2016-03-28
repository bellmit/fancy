package cn.fancy.spring.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.fancy.spring.vo.Article;
import cn.fancy.spring.vo.ProductSub;
import cn.fancy.spring.vo.ProductVo;
import cn.fancy.spring.vo.Users;

/**   
 * @Title: IShopDao.java 
 * @Package cn.telling.shop.dao 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2016-1-18 下午3:22:42 
 * @version V1.0   
 */
public interface IShopDao {
  public List<Users> getShops();

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

