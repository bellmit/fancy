/**
 * 
 * Project Name:myweb-service
 * File Name:ILogDao.java
 * Package Name:cn.fancy.log.dao
 * Date:2015-7-15
 *
*/

package cn.telling.log.dao;

import cn.telling.log.vo.Syslog;

/**
 * ClassName:ILogDao <br/>
 * @author   caosheng
 */
public interface ISysLogDao {
	public Boolean saveLog(Syslog log);
}

