/**
 * 
 */
package cn.telling.common.Vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**   
 * @Title: DictionaryVo.java 
 * @Package com.Common.Vo 
 * @Description: TODO(描述该文件做什么) 
 * @author 冯俊杰
 * @date 2013-4-10 下午4:01:42 
 * @version V1.0   
 */
/**
 * @author Administrator
 *
 */
public class DictionaryVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BigDecimal dictionaryid;
	private String dictionarytypevalue;
	private String dictionarytypetext;
	private String dictionarykey;
	private String dictionaryvalue;
	private String dictionarydesc;
	private String addtime;
	private String adduser;
	private String sortnumber;
	private String dictionarystatus;
	public String getDictionarystatus() {
		return dictionarystatus;
	}
	public void setDictionarystatus(String dictionarystatus) {
		this.dictionarystatus = dictionarystatus;
	}
	private String delflag;
	
	public BigDecimal getDictionaryid() {
		return dictionaryid;
	}
	public void setDictionaryid(BigDecimal dictionaryid) {
		this.dictionaryid = dictionaryid;
	}
	public String getDictionarytypevalue() {
		return dictionarytypevalue;
	}
	public void setDictionarytypevalue(String dictionarytypevalue) {
		this.dictionarytypevalue = dictionarytypevalue;
	}
	public String getDictionarytypetext() {
		return dictionarytypetext;
	}
	public void setDictionarytypetext(String dictionarytypetext) {
		this.dictionarytypetext = dictionarytypetext;
	}
	public String getDictionarykey() {
		return dictionarykey;
	}
	public void setDictionarykey(String dictionarykey) {
		this.dictionarykey = dictionarykey;
	}
	public String getDictionaryvalue() {
		return dictionaryvalue;
	}
	public void setDictionaryvalue(String dictionaryvalue) {
		this.dictionaryvalue = dictionaryvalue;
	}
	public String getDictionarydesc() {
		return dictionarydesc;
	}
	public void setDictionarydesc(String dictionarydesc) {
		this.dictionarydesc = dictionarydesc;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getAdduser() {
		return adduser;
	}
	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}
	public String getSortnumber() {
		return sortnumber;
	}
	public void setSortnumber(String sortnumber) {
		this.sortnumber = sortnumber;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

}
