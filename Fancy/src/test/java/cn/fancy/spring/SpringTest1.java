package cn.fancy.spring;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    
    @Test
    public void test() {
        ProductVo pvPr = new ProductVo();
        pvPr.setId("12");
        System.out.println("shopService==================================="+shopService.getShops());
        System.out.println(pvPr.hashCode());
        ApplicationContext a=SpringContextHolder.getApplicationContext();
        ShopServiceImpl s=(ShopServiceImpl) a.getBean("ShopServiceImpl");
        System.out.println("shopServiceImpl============================="+s.hashCode());
        System.out.println("===============action=============="+a.getBean("ShopServiceImpl"));
        System.out.println("===============SpringContextHolder=============="+a.getBean("springContextHolder"));
//        List<ProductVo> pv = shopService.getPLs(pvPr);
//        for (ProductVo productVo : pv) {
//            System.out.println(productVo.getProductId() + "  " + productVo.getProductName() + " "
//                            + productVo.getSaletype());
//        }
//        System.out.println(pv.size());
//
//        BeanDefinitionBuilder builder =
//                        BeanDefinitionBuilder.genericBeanDefinition(IShopService.class);
//        builder.addPropertyValue("serviceInterface", "serviceinter");
//        builder.addPropertyValue("serviceContext", "serviceContext");
//        // overloadEnabled
//        builder.addPropertyValue("overloadEnabled", true);
//        System.out.println(builder.getBeanDefinition());
      

    }



}
