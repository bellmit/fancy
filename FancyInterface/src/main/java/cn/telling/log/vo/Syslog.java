/**
 * 
 * Project Name:myweb-service
 * File Name:Syslog.java
 * Package Name:cn.fancy.log
 * Date:2015-7-15
 *
 */

package cn.telling.log.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:Syslog <br/>
 * 
 * @author caosheng
 */
public class Syslog implements Serializable {
	/**
	 * serialVersionUID:TODO
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 1104368181584141296L;
	private Integer id;
	private String ipAddress;
	private String loginName;
	private String methodName;
	private String methodRemark;
	private String operatingContent;
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodRemark() {
		return methodRemark;
	}

	public void setMethodRemark(String methodRemark) {
		this.methodRemark = methodRemark;
	}

	public String getOperatingContent() {
		return operatingContent;
	}

	public void setOperatingContent(String operatingContent) {
		this.operatingContent = operatingContent;
	}

}
