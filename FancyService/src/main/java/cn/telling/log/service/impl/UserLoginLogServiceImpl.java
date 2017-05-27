/**
 * 
 * Project Name:myweb-service
 * File Name:LogServiceImpl.java
 * Package Name:cn.fancy.log.service.impl
 * Date:2015-7-15
 *
 */

package cn.telling.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.telling.common.pager.PageVo;
import cn.telling.log.dao.IUserLoginLogDao;
import cn.telling.log.service.IUserLoginLogService;
import cn.telling.log.vo.UserLoginLog;
import cn.telling.user.vo.ReturnUserVo;


/**
 * ClassName:LogServiceImpl <br/>
 * 
 * @author caosheng
 */
@Service
public class UserLoginLogServiceImpl implements IUserLoginLogService {
	@Autowired
	private IUserLoginLogDao logDao;

	@Override
	public Boolean saveLog(UserLoginLog log) {
		return logDao.saveLog(log);
	}

	/* (non-Javadoc)
	 * @see cn.telling.log.service.IUserLoginLogService#querySysLogPagesByAccount(java.lang.String, cn.telling.common.pager.PageVo)
	 */
	@Override
	public ReturnUserVo querySysLogPagesByAccount(String account, PageVo pages) {
		//  Auto-generated method stub
		return logDao.querySysLogPagesByAccount(account, pages);
	}

}
