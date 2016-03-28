/**
 * 
 */
package cn.telling.common.exception;

import cn.telling.utils.PropertiesLoader;

/**   
 * @Title: BusinessException.java 
 * @Package com.Common.exception 
 * @Description: 系统业务异常
 * @author guohui
 * @date 2013-5-23 下午3:48:49 
 * @version V1.0   
 */
public class BusinessException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3928201008842485899L;
	/** 消息ID */
	private String key;
	/**
	 * 取得消息ID。
	 * 
	 * @return 消息ＩＤ
	 */
	public String getKey() {
		return this.key;
	}
	/**
	 * 取得完整消息内容。ID+完整消息内容
	 * 
	 * @return 完整消息内容
	 */
	public String getFullMessage() {
		return this.key + ":" + this.getMessage();
	}

	/** 异常类型 */
	private int type;
	/**
	 * 取得异常类型。
	 * 
	 * @return 异常类型
	 */
	public int getType() {
		return this.type;
	}
	
	/** 异常类型:后台 */
	public static final int TYPE_BACK = 0;
	/** 异常类型:买家 */
	public static final int TYPE_BUYER = 1;
	/** 异常类型:卖家 */
	public static final int TYPE_SELLER = 2;
	
	/**
	 * 业务异常构造函数
	 * 
	 * @param exType 异常类型
	 * @param key 消息ID
	 * @param param 异常参数
	 */
	public BusinessException(int exType, String key, Object... param) {
		super(PropertiesLoader.getMsg(key, param));
		this.type = exType;
		this.key = key;
	}
}
