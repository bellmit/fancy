/**
 * 
 * Project Name:myweb-service
 * File Name:ILogService.java
 * Package Name:cn.fancy.log.service
 * Date:2015-7-15
 *
 */

package cn.telling.log.service;

import cn.telling.log.vo.Syslog;

/**
 * ClassName:ILogService <br/>
 * 
 * @author caosheng
 */
public interface ISysLogService {
	public Boolean saveLog(Syslog log);
}
