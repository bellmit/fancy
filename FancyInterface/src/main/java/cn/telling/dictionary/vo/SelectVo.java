package cn.telling.dictionary.vo;

import java.io.Serializable;

/**
 * 下拉框
 * @author Super
 *
 */
public class SelectVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
