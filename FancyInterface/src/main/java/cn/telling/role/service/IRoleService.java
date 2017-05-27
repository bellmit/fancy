/**
 * 
 * Project Name:myweb-service
 * File Name:IRoleService.java
 * Package Name:com.fancy.role.service
 * Date:2015-7-24
 *
 */

package cn.telling.role.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import cn.telling.common.pager.PageVo;
import cn.telling.menu.vo.PusReRoleMenu;
import cn.telling.role.vo.Role;
import cn.telling.user.vo.ReturnUserVo;

/**
 * ClassName:IRoleService <br/>
 * 
 * @author caosheng
 */
public interface IRoleService {
	/****
	 * 获取所有角色名称
	 * 
	 * @param
	 * @return
	 * @author caosheng
	 * @version V1.0
	 */
	public List<Role> getRoles();

	public Set<String> getPermissionsAsString(String userId) throws Exception;

	public Set<String> getRolesAsString(String userId) throws Exception;

	/**
	 * @Description:  查询角色列表
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2017年3月17日 下午1:47:29 
	 * @version V1.0  
	*/
	
	public ReturnUserVo queryUserPagesByname(String name, PageVo pages);

	/**
	 * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2017年3月17日 下午2:22:33 
	 * @version V1.0  
	*/
	
	public Role getById(Integer id);

	/**
	 * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2017年3月17日 下午3:17:46 
	 * @version V1.0  
	*/
	
	public int saveRole(List<Object[]> batch);
}
