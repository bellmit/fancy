/**
 * 
 * Project Name:myweb-service
 * File Name:MenuServiceImpl.java
 * Package Name:com.fancy.menu.dao.impl
 * Date:2015-7-24
 *
 */

package cn.telling.menu.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.telling.menu.dao.IMenuDao;
import cn.telling.menu.vo.Menu;
import cn.telling.utils.MySQLAutoInjection;


/**
 * ClassName:MenuServiceImpl <br/>
 * 
 * @author caosheng
 */
@Repository
public class MenuDaoImpl implements IMenuDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Menu> queryMenuByRoleId(BigDecimal mId)
	{

		String sql = "SELECT m.createtime,m.description,m.id,m.menupid,m.`name`,m.pageurl,m.sortfiled,m.sortfiled,m.type,m.state,m.menupid FROM menu m,re_role_menu  re WHERE re.roleId=? AND re.menuid=m.id";
		return jdbcTemplate.query(sql, new Object[]
		{ mId }, new RowMapper<Menu>() {

			@Override
			public Menu mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Menu m = new Menu();
				MySQLAutoInjection.Rs2Vo(rs, m, null);
				return m;
			}
		});
	}

	/* (non-Javadoc)
	 * @see cn.telling.menu.dao.IMenuDao#getById(java.lang.BigDecimal)
	 */
	@Override
	public Menu getById(BigDecimal menupId) {
		String sql = "select * from menu m where m.id=?";
		List<Menu> size= jdbcTemplate.query(sql, new Object[]
		{ menupId }, new RowMapper<Menu>()	 {

			@Override
			public Menu mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Menu m = new Menu();
				MySQLAutoInjection.Rs2Vo(rs, m, null);
				return m;
			}
		});
		return size.size()>0?size.get(0):null;
	}
	
	
	   /* (non-Javadoc)
     * @see cn.telling.menu.dao.IMenuDao#getById(java.lang.BigDecimal)
     */
    @Override
    public Menu getPmById(BigDecimal id) {
        String sql = "select * from menu m where m.menupid=?";
        List<Menu> size= jdbcTemplate.query(sql, new Object[]
        { id }, new RowMapper<Menu>()   {

            @Override
            public Menu mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                Menu m = new Menu();
                MySQLAutoInjection.Rs2Vo(rs, m, null);
                return m;
            }
        });
        return size.size()>0?size.get(0):null;
    }
    
    

	   @Override
	   public List<Menu> findMenus(String mname){
	        String sql = "select * from menu m where m.name like '%"+mname+"%'";
	        List<Menu> size= jdbcTemplate.query(sql, new RowMapper<Menu>()   {

	            @Override
	            public Menu mapRow(ResultSet rs, int rowNum) throws SQLException
	            {
	                Menu m = new Menu();
	                MySQLAutoInjection.Rs2Vo(rs, m, null);
	                return m;
	            }
	        });
	        return size;
	    }
	   
	public List<Menu> findMenuByUserId(BigDecimal userId)
	{
		String sql="select m.id,m.name,m.menupid,m.description,m.pageurl,m.type,m.state,m.createtime,m.sortfiled " +
				"from menu m, re_role_menu rolemenu, re_user_role ur" +
				" where m.id=rolemenu.menuid and rolemenu.roleid=ur.roleid " +
				"and ur.userid=? and m.type=1 order by sortfiled";

		return jdbcTemplate.query(sql,new Object[]
				{ userId },new RowMapper<Menu>(){

			@Override
			public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
				Menu m = new Menu();
				MySQLAutoInjection.Rs2Vo(rs, m, null);
				return m;
			}});
	}
}
