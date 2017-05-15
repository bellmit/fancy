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
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.mozilla.javascript.ObjArray;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.telling.common.AutoInjectionRowMapper;
import cn.telling.common.CommonBaseDao;
import cn.telling.common.Pager.PageVo;
import cn.telling.menu.vo.PusReRoleMenu;
import cn.telling.role.dao.IRoleDao;
import cn.telling.role.vo.Role;
import cn.telling.user.vo.ReturnUserVo;
import cn.telling.user.vo.User;
import cn.telling.utils.MySQLAutoInjection;
import cn.telling.utils.StringHelperTools;

/**
 * ClassName:RoleDaoImpl <br/>
 * 
 * @author caosheng
 */
@Repository
public class RoleDaoImpl extends CommonBaseDao implements IRoleDao {
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

	public List<Role> queryRoleByUserId(String roleId)
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
	@Override
	public ReturnUserVo queryUserPagesByname(String name, PageVo page){
		String sql="SELECT role.id, role.`name`, role.`code`, role.pid, "
				+ "role.remark, role.state, role.createtime, role.type FROM role"+
				" where 1=1";
		Object params[]=null;
		if(!"".equals(StringHelperTools.nvl(name)))
		{
			sql+=" and account=?";
			params=new Object[]{name};;
		}
		sql+=" order by createtime desc";
		List<Role> userLs = queryByPage(sql, page,params ,new AutoInjectionRowMapper<Role>(Role.class));
		ReturnUserVo ruv=new ReturnUserVo();
		ruv.setTotalCount(page.getTotalCount());
		ruv.setUserLs(userLs);
		return ruv;
	}

	/* (non-Javadoc)
	 * @see cn.telling.role.dao.IRoleDao#getById(java.lang.Integer)
	 */
	@Override
	public Role getById(Integer id) {
		String sql="SELECT r1.id, r1.`name`, r1.type, r1.`code`, r1.pid, r1.remark,"
				+ " r1.state, r1.createtime, role.id FROM role AS r1"
				+ " LEFT JOIN role ON r1.pid = role.id WHERE 1=1";
		Object params[]=null;
		if(!"".equals(StringHelperTools.nvl(id)))
		{
			sql+=" and r1.id = ?";
			params=new Object[]{id};
		}
		sql+=" order by createtime desc";
		return queryForObject(sql,params, new RowMapper<Role>() {

			@Override
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Role u = new Role();
				MySQLAutoInjection.Rs2Vo(rs, u, null);
				return u;
			}
		});
	}

	/* (non-Javadoc)
	 * @see cn.telling.role.dao.IRoleDao#saveRole(cn.telling.menu.vo.PusReRoleMenu)
	 */
	@Override
	public int saveRole(List<Object[]> batch) {
		//  Auto-generated method stub
		return jdbcTemplate.batchUpdate("insert into re_role_menu(roleid,menuid,state) values(?,?,?)",batch).length>0?0:1;
	}
}
