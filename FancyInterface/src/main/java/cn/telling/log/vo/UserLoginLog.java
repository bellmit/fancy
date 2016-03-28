package cn.telling.log.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**   
 * @Title: UserLoginLog.java 
 * @Package cn.telling.log.vo 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2015-12-15 上午10:53:39 
 * @version V1.0   
 */
public class UserLoginLog implements Serializable{

	private static final long serialVersionUID = -3614247265383947005L;
	
	private Integer id;
	private String useraccount;
	private Integer type;
	private String ip;
	private Timestamp createtime;
	
	
	public Timestamp getCreatetime() {
		return createtime;
	}

	
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUseraccount() {
		return useraccount;
	}
	
	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}

