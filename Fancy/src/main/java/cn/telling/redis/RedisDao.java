package cn.telling.redis;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @Title: Snippet.java
 * @Package cn.telling.redis
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2016-1-22 下午4:31:39
 * @version V1.0
 */

public class RedisDao {
    @Autowired
    private ShardedJedisPool shardedJedisPool;

    public void set(String key, String value) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        jedis.set(key, value);
    }

    public String get(String key) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        return jedis.get(key);
    }
}
