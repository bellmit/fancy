/**
 * 
 * Project Name:myweb-service
 * File Name:IRoleDao.java
 * Package Name:com.fancy.role.dao
 * Date:2015-7-24
 *
 */

package cn.telling.role.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.telling.role.vo.Role;

/**
 * ClassName:IRoleDao <br/>
 * 
 * @author caosheng
 */
public interface IRoleDao {
	public List<Role> getRoles();

	public List<Role> queryRoleByUserId(BigDecimal roleId);
}
