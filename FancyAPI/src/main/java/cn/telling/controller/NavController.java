package cn.telling.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.telling.formbean.BaseResultVo;

/**
 * 
 * @author zsCat 2016-10-13 14:37:49
 * @Email: 951449465@qq.com
 * @version 4.0v 我的官网
 */
@Controller
@Api(value = "导航表增删改查", description = "导航表增删改查")
@RestController
@RequestMapping(value = "v2/api1", produces = {
		MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class NavController extends BaseApiController {

	public static void main(String[] args) {

	    com.fasterxml.jackson.core.JsonFactory factory;
	}
	/**
	 * 添加或更新区域
	 * 
	 * @param params
	 * @return
	 */
/*	@ResponseBody
	@RequestMapping(value = "/addNav", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "添加导航菜单", httpMethod = "POST", response = Nav.class, notes = "添加导航菜单")
	public Integer save(@ModelAttribute Nav Nav) {
		Nav.setAddtime(new Date());
		return NavService.savenav(Nav);
	}

	*//**
	 * 删除字典
	 * 
	 * @param id
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = "/delNavId/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "根据导航ID删除导航栏")
	public Integer del(@ModelAttribute Nav Nav) {
		return NavService.deleteNav(Nav);
	}

	@ApiOperation(value = "查询所有导航信息", notes = "查询所有导航信息", produces = "application/json; charset=utf-8", response = BaseResultVo.class)
	@RequestMapping(value = "/getNavList", method = RequestMethod.GET)
	@ResponseBody
	public String list() {
		List<Nav> students = NavService.select(null);
		if (students.size() > 0) {
			return buildSuccessResultInfo(students);
		}
		return buildFailedResultInfo(0, "no data");
	}*/

}
