package cn.telling.service;


import java.util.List;

import cn.telling.base.BaseService;
import cn.telling.model.User;
/**   
 * @Title: IUserService.java 
 * @Package cn.telling.service 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2017年5月19日 上午10:26:42 
 * @version V1.0   
 */
public interface IUserService extends BaseService<User>{
	
	/**
	 * 获取用户列表
	 */
	public List<User> findList();
		
}

