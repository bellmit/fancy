/**
 * 
 * Project Name:TellingMallWechat
 * File Name:MemberRegister.java
 * Package Name:cn.telling.member
 * Date:2015-6-23
 *
 */

package cn.telling.action.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.telling.product.service.IProductService;

import com.danga.MemCached.MemCachedClient;

@Controller
@RequestMapping("/test")
public class MemberAction {

	// 缓存对象
	// 缓存对象
	@Resource
	MemCachedClient memcachedClient;

	@Resource
	IProductService demoService;

	@RequestMapping("/login")
	public String a() {
		demoService.getClass();
		System.out.println(demoService.getProductMainLs().toArray());
		System.out.println(System.getProperty("fancytest.root"));

		return null;
	}

	public static void main(String[] args) {
		Long l = 0L;
		System.out.println(l);
		String s = "B23423E";
		System.out.println(s.substring(s.indexOf("B") + 1, s.lastIndexOf("E")));
		System.out.println(s.lastIndexOf("B"));
		System.out.println(s.charAt(s.indexOf("B")));
		System.out.println(s.substring(0));
		System.out.println(System.getProperty("fancytest.root"));

	}
}
