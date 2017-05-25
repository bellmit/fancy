package cn.telling.service.impl;

import org.springframework.stereotype.Service;

import cn.telling.base.ServiceMybatis;
import cn.telling.service.LogService;

/**   
 * @Title: LogServiceImpl.java 
 * @Package cn.telling.service.impl 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2017年5月20日 下午1:06:42 
 * @version V1.0   
 */
@Service("LogService")
public class LogServiceImpl extends ServiceMybatis implements LogService{

	@Override
	public String list() {
		System.out.println("=======================");
		return null;
	}

	

}



