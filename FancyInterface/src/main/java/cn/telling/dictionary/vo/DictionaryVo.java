package cn.telling.dictionary.vo;

import java.math.BigDecimal;

public class DictionaryVo {
	private BigDecimal dictionaryId;
	private String dictionaryTypeValue;
	private String dictionaryTypeText;
	private String dictionaryKey;
	private String dictionaryValue;
	private String describtion;
	private String add_user;
	public String getAdd_user() {
		return add_user;
	}
	public void setAdd_user(String add_user) {
		this.add_user = add_user;
	}
	public BigDecimal getDictionaryId() {
		return dictionaryId;
	}
	public void setDictionaryId(BigDecimal dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	public String getDictionaryTypeValue() {
		return dictionaryTypeValue;
	}
	public void setDictionaryTypeValue(String dictionaryTypeValue) {
		this.dictionaryTypeValue = dictionaryTypeValue;
	}
	public String getDictionaryTypeText() {
		return dictionaryTypeText;
	}
	public void setDictionaryTypeText(String dictionaryTypeText) {
		this.dictionaryTypeText = dictionaryTypeText;
	}
	public String getDictionaryKey() {
		return dictionaryKey;
	}
	public void setDictionaryKey(String dictionaryKey) {
		this.dictionaryKey = dictionaryKey;
	}
	public String getDictionaryValue() {
		return dictionaryValue;
	}
	public void setDictionaryValue(String dictionaryValue) {
		this.dictionaryValue = dictionaryValue;
	}
	public String getDescribtion() {
		return describtion;
	}
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	
}
