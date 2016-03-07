package cn.fancy.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.telling.shop.service.IShopService;
import cn.telling.user.service.IUserService;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest
{

	@Resource
	private IUserService userService;

	@Autowired
	private IShopService shopService;

	@Test
	public void test()
	{
		//		Users us = userService.getUserInfo(7);
		//		System.out.println(us);
		//		Users uss = userService.getUserInfo(7);
		//		System.out.println(uss);
		//		System.out.println(System.getProperty("webapp.root"));
//		List<ShopInfo> siList = shopService.getShops();
//		System.out.println(siList.size());
	}

}
