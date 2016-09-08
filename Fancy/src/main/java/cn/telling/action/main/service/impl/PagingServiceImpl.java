package cn.telling.action.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.telling.action.main.dao.impl.PagingDaoImpl;
import cn.telling.action.main.service.PagingService;
import cn.telling.common.Pager.PageVo;
import cn.telling.user.vo.User;
import cn.telling.utils.Paging;


/**   
* @Title: PagingService.java 
* @Package com.fancy.paging.service.impl 
* @Description: (描述该文件做什么) 
* @author 操圣
* @date 2015-3-30 上午11:47:00 
* @version V1.0   
*/
@Service
public class PagingServiceImpl implements PagingService
{

	@Resource
	private PagingDaoImpl pagingDaoImpl;

	/* (non-Javadoc)
	 * @see com.fancy.paging.service.PagingService#getPageCount()
	 */
	@Override
	public int getPageCount()
	{
		return pagingDaoImpl.getPageCount();
	}

	@Override
	public List<User> getPageData(Paging page)
	{
		return pagingDaoImpl.getPageData(page);
	}

    /* (non-Javadoc)
     * @see cn.telling.user.service.PagingService#getPageData(cn.telling.common.Pager.PageVo)
     */
    @Override
    public List<User> getPageData(PageVo pageVo) {
        return pagingDaoImpl.getPageData(pageVo);
    }
    public Integer getUserCount(PageVo pageVo){
        return pagingDaoImpl.getUserCount(pageVo);
    }
}
