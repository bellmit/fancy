package cn.telling.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**   
 * @Title: AbstractBaseRedisDao.java 
 * @Package cn.telling.redis.dao 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2016-1-22 下午4:48:07 
 * @version V1.0   
 */
public abstract class AbstractBaseRedisDao<K, V> {  
    
    @Autowired  
    protected RedisTemplate<K, V> redisTemplate;  
  
    /** 
     * 设置redisTemplate 
     * @param redisTemplate the redisTemplate to set 
     */  
    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
      
    /** 
     * 获取 RedisSerializer 
     * <br>------------------------------<br> 
     */  
    protected RedisSerializer<String> getRedisSerializer() {  
        return redisTemplate.getStringSerializer();  
    }  
}  