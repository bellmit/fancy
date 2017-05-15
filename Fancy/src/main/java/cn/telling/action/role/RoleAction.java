/**
 * 
 * Project Name:myweb-web
 * File Name:RoleAction.java
 * Package Name:com.fancy.action.role
 * Date:2015-7-24
 *
 */

package cn.telling.action.role;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import cn.telling.common.Pager.PageVo;
import cn.telling.common.constants.Constants;
import cn.telling.common.uitl.StringUtil;
import cn.telling.common.uitl.TimestampTypeAdapter;
import cn.telling.menu.service.IMenuService;
import cn.telling.menu.vo.Menu;
import cn.telling.menu.vo.PusReRoleMenu;
import cn.telling.menu.vo.TreeNode;
import cn.telling.role.service.IRoleService;
import cn.telling.role.vo.EditPurviewBean;
import cn.telling.role.vo.Role;
import cn.telling.utils.Paging;
import cn.telling.web.MethodLog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



/**
 * ClassName:RoleAction <br/>
 * 
 * @author caosheng
 */
@Controller
public class RoleAction {
	@Resource
	private CacheManager shiroCacheManager;
	@Resource
	public IRoleService roleService;
	@Resource
	IMenuService menuService;
	@RequestMapping("/roleList")
	public String roleList(ModelMap map, HttpServletRequest request, Paging pa)
	{
		return "sys/listRole";
	}
	@RequestMapping("/viewAllRoleJson")
	@MethodLog(remark = "角色列表查看")
	public ModelAndView getViewAllRoleJson(
			@RequestParam(required = false, defaultValue = "") String name,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();
		PageVo pages = new PageVo();
		pages.setCenterPage(page);
		pages.setMessageForPage(rows);

		mapjson.put("total", roleService.queryUserPagesByname(name, pages)
				.getTotalCount());
		mapjson.put("rows", roleService.queryUserPagesByname(name, pages)
				.getUserLs());
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(mapjson);
		// String json = gsonBuilder.(mapjson);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(json);
		out.flush();
		return null;
	}
	
	@RequestMapping(value = "/assignPermissions", method = RequestMethod.GET)
	@MethodLog(remark = "查看权限分配情况")
	public ModelAndView assignPermissionsAction(@RequestParam("id") Integer id)
			throws Exception {
		ModelAndView mav = new ModelAndView("sys/editPurview");
		Role pr = roleService.getById(id);
		// List<PusMenu> listpm = pusMenuDao.findMenuByRoleId(id);

		EditPurviewBean ep = new EditPurviewBean();
		ep.setRoleid(id);
		ep.setRolename(pr.getName());

		mav.addObject("editPermissionsForm", ep);
		return mav;
	}

	@RequestMapping("/viewPermissionsMenuJson")
	@MethodLog(remark = "权限菜单显示")
	public ModelAndView getPermissionsMenuJson(@RequestParam("id") Integer id,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");

		List<Menu> listpm = menuService.findMenuByRoleId(id); // 被选过的

		List<Menu> listrelationpm = menuService.getPusMenuList();
		// 拉出数据库的数据，放入list2中

		List<TreeNode> list = new ArrayList<TreeNode>();
		Map map = new HashMap<String, TreeNode>();
		// 拉出数据库的数据，放入list2中
		Map pmap = new HashMap<String, TreeNode>();
		// ArrayList<PusMenu> list2 = (ArrayList<TDict>)this.find(hql);
		// if (listpm.size() <= 0) {
		// listpm = listrelationpm;
		// } else {
		//
		// }
		// 将listpm中的数据，转换成TreeNode类型，放入Map中备用
		for (Menu pm : listrelationpm) {
			TreeNode node = new TreeNode();
			for (Menu pmchecked : listpm) {
				if (pm.getId().equals(pmchecked.getId())) {
					node.setChecked(true);
				}
			}

			node.setId(pm.getId());
			node.setText(pm.getName());

			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", pm.getPageUrl());

			node.setAttribute(attributes);
			if (pm.getPm() != null) {
				node.setParentId(pm.getPm().getId());
			}

			map.put(pm.getId(), node);

		}

		// 遍历listpm的数据，把每个节点加入他的父节点的孩子List

		for (Menu pm : listrelationpm) {

			if (pm.getPm() != null) {

				if (pm.getPm().getId() == null) {
					list.add((TreeNode) map.get(pm.getId()));
				} else {

					BigDecimal pidInteger =pm.getPm().getId();
					TreeNode pnode = (TreeNode) map.get(pidInteger);
					TreeNode cnode = (TreeNode) map.get(pm.getId());
					pnode.addChild(cnode);
				}
			} else {
				list.add((TreeNode) map.get(pm.getId()));
			}
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(list);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(json);
		out.flush();
		return null;
	}

	@RequestMapping(value = "/savePermission", method = RequestMethod.POST)
	@MethodLog(remark = "角色菜单权限分配保存")
	public String savePermissionAction(@RequestParam("roleid") Integer roleid,
			@RequestParam("state") Integer state,
			@RequestParam("menuid") String menuid, SessionStatus status,
			HttpServletResponse response) {
		String menuidarry[] = menuid.split(",");
		// Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
		// mc.setCreatetime(now);
		// 保存前先删除以前该角色的所有角色菜单权限关系
		// for (int i = 0; i < menuidarry.length; i++) {
		// pusReRoleMenuDao.batchDelete(roleid,StringUtil.IntegerIsNull(menuidarry[i]));
		// }
		menuService.batchDelete(roleid);


		int saverec = 0;
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < menuidarry.length; i++) {
			Object[] values=new Object[]{roleid,StringUtil.IntegerIsNull(menuidarry[i]),state};
			// int
			// count=pusReRoleMenuDao.haveprrmcount(StringUtil.IntegerIsNull(menuidarry[i]),roleid);
			// if(count>0){
			// pusReRoleMenuDao.batchDelete(StringUtil.IntegerIsNull(menuidarry[i]),roleid);
			// }
			batch.add(values);
		}
		saverec = roleService.saveRole(batch);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.write("保存成功,您可以继续保存或者关闭当前页面");
		// 刷新权限缓存
		shiroCacheManager.getCache(Constants.authorizationCacheName).clear();
		// if (saverec > 0) {
		// out.write("保存成功,您可以继续保存或者关闭当前页面");
		// } else {
		// out.write("保存失败");
		// }

		out.flush();
		return null;
	}

}
