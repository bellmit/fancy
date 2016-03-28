package cn.telling.common;

import com.danga.MemCached.MemCachedClient;

/**
 * @Title: CacheManager.java
 * @Package cn.telling.common
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015-12-1 上午9:48:19
 * @version V1.0
 */
public class CacheManager {

	private MemCachedClient memClient;

	public MemCachedClient getMemClient() {
		return memClient;
	}

	public void setMemClient(MemCachedClient memClient) {
		this.memClient = memClient;
	}

}
