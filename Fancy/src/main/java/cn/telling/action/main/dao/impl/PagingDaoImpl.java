package cn.telling.action.main.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.telling.action.main.dao.PagingDao;
import cn.telling.common.AutoInjectionRowMapper;
import cn.telling.common.CommonBaseDao;
import cn.telling.common.Pager.PageVo;
import cn.telling.common.Pager.Pager;
import cn.telling.user.vo.Users;
import cn.telling.utils.AutoInjection;
import cn.telling.utils.LogUtils;
import cn.telling.utils.MySQLAutoInjection;
import cn.telling.utils.Paging;


/**
 * @Title: PagingDaoImpl.java
 * @Package com.fancy.paging.dao
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015-3-30 上午11:48:08
 * @version V1.0
 */
@Repository
public class PagingDaoImpl extends CommonBaseDao implements PagingDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource(name="film-template")
    private JdbcTemplate oracleJdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fancy.paging.dao.PagingDao#getPageCount()
	 */
	@Override
	public int getPageCount() {
		String sql = "select count(id) from Users";
		LogUtils.debug("输出sql:" + sql);
		return jdbcTemplate.queryForInt(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fancy.paging.dao.PagingDao#getPageData()
	 */
	@Override
	public List<Users> getPageData(Paging page) {
		String sql = "select id,username,password from users limit ?,?";
		LogUtils.debug("输出sql:" + sql);
		Object[] params = { page.getStartIndex(), page.getEndIndex() };
		return jdbcTemplate.query(sql, params, new RowMapper<Users>() {

			@Override
			public Users mapRow(ResultSet rs, int arg1) throws SQLException {
				Users user = new Users();
				MySQLAutoInjection.Rs2Vo(rs, user, null);
				return user;
			}
		});
	}

    /**
     * @Description:  查询用户列表
     * @param		参数说明
     * @return		返回值
     * @exception   异常描述
     * @see		          需要参见的其它内容。（可选）
     * @author      操圣
     * @date 2016-2-2 上午10:55:21 
     * @version V1.0  
    */
    
    public Integer getUserCount(PageVo pageVo) {
        String sql = getUsers();
        LogUtils.debug("输出sql:" + sql);
        sql = Pager.getTotalPageSql(pageVo, sql);
        return oracleJdbcTemplate.queryForInt(sql, new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet rs, int arg1) throws SQLException {
                Users user = new Users();
                AutoInjection.Rs2Vo(rs, user, null);
                return user;
            }
        });
    }

    private String getUsers() {
        String sql = "select userid,username from users";
        return sql;
    }

    /* (non-Javadoc)
     * @see cn.telling.action.main.dao.PagingDao#getPageData(cn.telling.common.Pager.PageVo)
     */
    @Override
    public List<Users> getPageData(PageVo pageVo) {
        String sql = getUsers();
       LogUtils.debug("输出sql:" + sql);
        return queryByPage(sql, pageVo, new AutoInjectionRowMapper<Users>(Users.class));
    }
}
