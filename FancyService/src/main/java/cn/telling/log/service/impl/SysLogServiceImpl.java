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

import cn.telling.log.dao.ISysLogDao;
import cn.telling.log.service.ISysLogService;
import cn.telling.log.vo.Syslog;


/**
 * ClassName:LogServiceImpl <br/>
 * 
 * @author caosheng
 */
@Service
public class SysLogServiceImpl implements ISysLogService {
	@Autowired
	private ISysLogDao logDao;

	@Override
	public Boolean saveLog(Syslog log) {

		return logDao.saveLog(log);
	}

}
