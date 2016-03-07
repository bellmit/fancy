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

import cn.telling.role.vo.Role;

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

	public Set<String> getPermissionsAsString(BigDecimal userId) throws Exception;

	public Set<String> getRolesAsString(BigDecimal userId) throws Exception;
}
