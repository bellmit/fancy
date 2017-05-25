package cn.telling.modules.sys.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.telling.service.IUserService;

/**   
 * @Title: UserController.java 
 * @Package cn.telling.modules.sys.controller 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2017年5月20日 上午9:18:02 
 * @version V1.0   
 */
@Controller

public class UserController {


	@Resource(name="UserServiceImpl")
	IUserService userService;
	
	@RequestMapping("index")
	public String index(){
		userService.findList();
		return "aa";
	}
}

