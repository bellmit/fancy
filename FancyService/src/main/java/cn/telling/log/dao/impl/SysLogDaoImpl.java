/**
 * 
 * Project Name:myweb-service
 * File Name:LogDaoImpl.java
 * Package Name:cn.fancy.log.dao.impl
 * Date:2015-7-15
 *
 */

package cn.telling.log.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.telling.log.dao.ISysLogDao;
import cn.telling.log.vo.Syslog;


/**
 * ClassName:LogDaoImpl <br/>
 * 
 * @author caosheng
 */
@Repository
public class SysLogDaoImpl implements ISysLogDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public Boolean saveLog(Syslog log) {
		String sql = "insert into syslog(ipAddress,loginName,methodName,methodRemark,operatingContent) values(?,?,?,?,?)";
		int flag = jdbcTemplate.update(sql, new Object[] { log.getIpAddress(), log.getLoginName(), log.getMethodName(),
				log.getMethodRemark(), log.getOperatingContent() });
		return flag > 0 ? true : false;
	}

}
