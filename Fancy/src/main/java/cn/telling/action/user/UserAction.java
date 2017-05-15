/**
 * Project Desc:
 * Project Name:SpringMVC
 * File Name:UserLoginAction.java
 * Package Name:com.fancy.paging.action
 * Date:2015-4-27下午6:10:02
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
 */
package cn.telling.action.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.telling.common.Pager.PageVo;
import cn.telling.common.uitl.TimestampTypeAdapter;
import cn.telling.user.service.IUserService;
import cn.telling.user.vo.ReturnUserVo;
import cn.telling.user.vo.User;
import cn.telling.web.MethodLog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jmx.snmp.Timestamp;

/**
 * ClassName:UserLoginAction <br/>
 * Date: 2015-4-27 下午6:10:02 <br/>
 * 用户相关
 * @author 操圣
 * @version 1.0
 * @see
 */
@Controller
@RequestMapping("/user")
public class UserAction {

	@Resource
	IUserService userService;

	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewAllUserJson() {
		return "/sys/listUser";
	}

	@MethodLog(remark = "查询用户列表")
	@RequestMapping(value = "/viewAllUserJson", method = { RequestMethod.GET, RequestMethod.POST })
	public void list(@RequestParam(required = false, defaultValue = "") String account, PageVo pv, 
			HttpServletResponse response,@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows) {
		response.setContentType("text/html;charset=utf-8");
		pv.setMessageForPage(5);
		// 安全过滤
		if (account.equalsIgnoreCase("' or 1 like '%1%")) {
			account = "";
		}
		account = account.replace(" ", "").trim();
		pv.setPageNow(page);
		pv.setMessageForPage(10);
		ReturnUserVo ruv = userService.queryUsers(account, pv);
		Map<String, Object> mapjson = new HashMap<String, Object>();
		mapjson.put("total", ruv.getTotalCount());
		mapjson.put("rows", ruv.getUserLs());
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class, new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();
		String json = gson.toJson(mapjson);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(json);
		out.flush();
	}
	/**
	 * 添加用户
	 * **/
	@RequestMapping("/addUser")
	public ModelAndView addUserAction() {
		ModelAndView mav = new ModelAndView("sys/addUser");
		User u = new User();
		mav.getModelMap().put("psu", u);
		return mav;
	}
	@RequestMapping(value = "eidtUserPwd", method = RequestMethod.GET)
	@MethodLog(remark = "更改后台用户密码跳转")
	public ModelAndView eidtUserPwdAction(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView("sys/editUser");
		User mc = userService.getUserInfo(id);
		mav.addObject("editUserForm", mc);
		return mav;
	}
}
