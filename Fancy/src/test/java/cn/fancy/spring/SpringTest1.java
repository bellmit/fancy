package cn.fancy.spring;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.danga.MemCached.MemCachedClient;

import cn.fancy.spring.service.IShopService;
import cn.fancy.spring.service.impl.ShopServiceImpl;
import cn.fancy.spring.vo.ProductVo;
import cn.telling.common.uitl.SpringContextHolder;


/***
 * 
 * @description 
 *  one @resouce IShopService 注入的并不是iShopService,而是IShopService的实现类shopServiceImpl
 * 
 * */
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest1 {
    
    @Resource(name="ShopServiceImpl")
    private IShopService shopService;

    @Autowired
    private MemCachedClient memcachedClient;
    
    @Test
    public void test() {
      System.out.println("================"+memcachedClient);
      memcachedClient.add("ceshiyi", "java");
      memcachedClient.add("ced", "java");
      System.out.println("=================="+memcachedClient.get("ceshiyi")+"==="+memcachedClient.get("ced"));
        ProductVo pvPr = new ProductVo();
        pvPr.setId("12");
        System.out.println("shopService==================================="+shopService.getShops());
        System.out.println(pvPr.hashCode());
        ApplicationContext a=SpringContextHolder.getApplicationContext();
        ShopServiceImpl s=(ShopServiceImpl) a.getBean("ShopServiceImpl");
        System.out.println("shopServiceImpl============================="+s.hashCode());
        System.out.println("===============action=============="+a.getBean("ShopServiceImpl"));
        System.out.println("===============SpringContextHolder=============="+a.getBean("springContextHolder"));
        List<ProductVo> pv = shopService.getPLs(pvPr);
        for (ProductVo productVo : pv) {
            System.out.println(productVo.getProductId() + "  " + productVo.getProductName() + " "
                            + productVo.getSaletype());
        }
        System.out.println(pv.size());

        BeanDefinitionBuilder builder =
                        BeanDefinitionBuilder.genericBeanDefinition(IShopService.class);
        builder.addPropertyValue("serviceInterface", "serviceinter");
        builder.addPropertyValue("serviceContext", "serviceContext");
        // overloadEnabled
        builder.addPropertyValue("overloadEnabled", true);
        System.out.println(builder.getBeanDefinition());
      
        System.out.println("----------------------------------------------------------------------");
    }



}
