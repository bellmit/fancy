package cn.telling.mapper;

import java.util.List;

import cn.telling.model.User;

import com.github.abel533.mapper.Mapper;

/**   
 * @Title: UserMapper.java 
 * @Package cn.telling.mapper 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2017年5月19日 下午1:13:50 
 * @version V1.0   
 */

public interface UserMapper extends Mapper<User>{

	List<User> findList();

}

