/**
 * 
 * Project Name:myweb-service
 * File Name:ILogDao.java
 * Package Name:cn.fancy.log.dao
 * Date:2015-7-15
 *
*/

package cn.telling.log.dao;

import cn.telling.common.Pager.PageVo;
import cn.telling.log.vo.Syslog;
import cn.telling.log.vo.UserLoginLog;
import cn.telling.user.vo.ReturnUserVo;

/**
 * ClassName:ILogDao <br/>
 * @author   caosheng
 */
public interface IUserLoginLogDao {
	public Boolean saveLog(UserLoginLog log);

	/**
	 * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2017年3月17日 下午4:59:09 
	 * @version V1.0  
	*/
	
	public ReturnUserVo querySysLogPagesByAccount(String account, PageVo pages);
}

