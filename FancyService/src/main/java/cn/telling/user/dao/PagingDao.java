package cn.telling.user.dao;

import java.util.List;

import cn.telling.common.Pager.PageVo;
import cn.telling.user.vo.Users;
import cn.telling.utils.Paging;


/**
 * @Title: PagingDao.java
 * @Package dao
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015-3-30 上午11:39:16
 * @version V1.0
 */
public interface PagingDao {

	/****
	 * 
	 * @Description: 获取分页总数
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 操圣
	 * @date 2015-3-30 下午1:22:23
	 * @version V1.0
	 */
	public int getPageCount();

	/****
	 * 
	 * @Description: 获取分页数据
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 操圣
	 * @date 2015-3-30 下午1:22:32
	 * @version V1.0
	 */
	public List<Users> getPageData(Paging page) throws Exception;
	 public List<Users> getPageData(PageVo pageVo);
}
