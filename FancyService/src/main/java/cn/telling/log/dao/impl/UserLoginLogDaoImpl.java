/**
 * 
 * Project Name:myweb-service
 * File Name:LogDaoImpl.java
 * Package Name:cn.fancy.log.dao.impl
 * Date:2015-7-15
 *
 */

package cn.telling.log.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.telling.common.AutoInjectionRowMapper;
import cn.telling.common.CommonBaseDao;
import cn.telling.common.pager.PageVo;
import cn.telling.log.dao.IUserLoginLogDao;
import cn.telling.log.vo.Syslog;
import cn.telling.log.vo.UserLoginLog;
import cn.telling.user.vo.ReturnUserVo;
import cn.telling.utils.BaseUtil;
import cn.telling.utils.LogUtils;


/**
 * ClassName:LogDaoImpl <br/>
 * 用户登录操作日志
 * @author caosheng
 */
@Repository
public class UserLoginLogDaoImpl extends CommonBaseDao implements IUserLoginLogDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public Boolean saveLog(UserLoginLog log) {
		String sql = "insert into user_loginlog ( useraccount, type, ip, createtime ) values ( ?, ?, ?,sysdate()) ;";
		Object params[]=new Object[] { log.getUseraccount(), log.getType(), log.getIp()};
		int flag = jdbcTemplate.update(sql, params);
		LogUtils.info("新增登录日志:"+BaseUtil.logSQL(sql, params));
		return flag > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see cn.telling.log.dao.IUserLoginLogDao#querySysLogPagesByAccount(java.lang.String, cn.telling.common.pager.PageVo)
	 */
	@Override
	public ReturnUserVo querySysLogPagesByAccount(String account, PageVo pages) {
		String sql = "SELECT l.id, l.ipAddress, l.loginName, l.methodName, l.methodRemark, l.createTime, l.operatingContent FROM syslog AS l ORDER BY l.createTime ASC";
		List<Syslog> userLs = queryByPage(sql, pages ,new Object[]{}, new AutoInjectionRowMapper<Syslog>(Syslog.class));
		ReturnUserVo ruv=new ReturnUserVo();
		ruv.setTotalCount(pages.getTotalCount());
		ruv.setUserLs(userLs);
		return ruv;
	}

}
