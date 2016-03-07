/**
 * 
 * Project Name:myweb-service
 * File Name:Role.java
 * Package Name:cn.fancy.user.vo
 * Date:2015-7-14
 *
 */

package cn.telling.role.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;


/**
 * ClassName:Role <br/>
 * 
 * @author caosheng
 */
public class Role implements Serializable {

	/**
	 * serialVersionUID:TODO
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = -5705174063514505034L;

	private BigDecimal id;
	private String name;
	private String type;
	private String code;
	private BigDecimal pid;
	private String remark;
	private String state;
	private Date createTime;

	public BigDecimal getId()
	{
		return id;
	}

	public void setId(BigDecimal id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public BigDecimal getPid()
	{
		return pid;
	}

	public void setPid(BigDecimal pid)
	{
		this.pid = pid;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	@Override
	public String toString()
	{
		return "Role [id=" + id + ", name=" + name + ", type=" + type + ", code=" + code + ", pid=" + pid + ", remark="
				+ remark + ", state=" + state + ", createTime=" + createTime + "]";
	}

}
