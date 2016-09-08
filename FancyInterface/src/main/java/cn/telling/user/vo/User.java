package cn.telling.user.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @Title: Users.java
 * @Package com.fancy.paging.entity
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015-3-30 上午11:54:02
 * @version V1.0
 */
public class User implements Serializable {


	private static final long serialVersionUID = 8291538730234781395L;

    private BigDecimal id;
	
	private String username;

	private String password;

	private String realname;
	private String lastTime;
	private String email;
	private String sex;
	private String phone;
	private String address;
	private String description;
	private String age;
	private Date createtime;
	private String account;
	
	private String rolename;
	
	
	public String getRolename() {
		return rolename;
	}


	
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}


	public String getAccount() {
		return account;
	}

	
	public void setAccount(String account) {
		this.account = account;
	}

	public User() {
	}

	public User(User u) {
		this.id = u.getId();
		this.username = u.getUsername();
		this.password = u.getPassword();
		this.realname = u.getRealname();
		this.lastTime = u.getLastTime();
		this.email = u.getEmail();
		this.sex = u.getSex();
		this.phone = u.getPhone();
		this.address = u.getAddress();
		this.description = u.getDescription();
		this.age = u.getAge();
		this.createtime = u.getCreatetime();
		this.account=u.getAccount();
		this.rolename=u.getRolename();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

}
