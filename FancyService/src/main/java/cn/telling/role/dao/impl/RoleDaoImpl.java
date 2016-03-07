/**
 * 
 * Project Name:myweb-service
 * File Name:RoleDaoImpl.java
 * Package Name:com.fancy.role.dao.impl
 * Date:2015-7-24
 *
 */

package cn.telling.role.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.telling.role.dao.IRoleDao;
import cn.telling.role.vo.Role;
import cn.telling.utils.MySQLAutoInjection;

/**
 * ClassName:RoleDaoImpl <br/>
 * 
 * @author caosheng
 */
@Repository
public class RoleDaoImpl implements IRoleDao {
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Role> getRoles()
	{
		String sql = "SELECT role.id, role.`name`, role.type, role.`code`, role.pid, role.remark, role.state, role.createtime FROM role";
		return jdbcTemplate.query(sql, new RowMapper<Role>() {

			@Override
			public Role mapRow(ResultSet rs, int rowNum)
			{
				Role r = new Role();
				MySQLAutoInjection.Rs2Vo(rs, r, null);
				return r;
			}

		});
	}

	public List<Role> queryRoleByUserId(BigDecimal roleId)
	{
		String sql = "SELECT  r.`code`,r.createtime,r.id,r.`name`,r.pid,r.remark,r.state,r.type from role r,re_user_role  re where  re.roleid=r.id and re.userid=?";
		return jdbcTemplate.query(sql, new Object[]
		{ roleId }, new RowMapper<Role>() {

			@Override
			public Role mapRow(ResultSet rs, int rowNum)
			{
				Role r = new Role();
				MySQLAutoInjection.Rs2Vo(rs, r, null);
				return r;
			}

		});
	}

}
