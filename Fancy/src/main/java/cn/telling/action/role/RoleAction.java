/**
 * 
 * Project Name:myweb-web
 * File Name:RoleAction.java
 * Package Name:com.fancy.action.role
 * Date:2015-7-24
 *
 */

package cn.telling.action.role;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.telling.role.service.IRoleService;
import cn.telling.role.vo.Role;
import cn.telling.utils.Paging;



/**
 * ClassName:RoleAction <br/>
 * 
 * @author caosheng
 */
@Controller
public class RoleAction {
	@Resource
	public IRoleService roleService;

	@RequestMapping("/roleList")
	public String roleList(ModelMap map, HttpServletRequest request, Paging pa)
	{
		List<Role> roleList = roleService.getRoles();
		map.put("rList", roleList);
		return "/role_list";
	}
}
