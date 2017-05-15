package cn.telling.menu.vo;


/**
 * PusReRoleMenu entity. @author MyEclipse Persistence Tools
 */
public class PusReRoleMenu implements java.io.Serializable {

	// Fields
	private Integer id;
	private Integer roleid;
	private Integer menuid;
	private Integer state;
	
	
//	@ManyToMany
//	@Fetch(FetchMode.JOIN)
//	@NotFound(action=NotFoundAction.IGNORE)
//	@JoinColumn(name = "roleid", insertable=false, updatable=false,referencedColumnName = "roleid") 
//	private PusReUserRole pusReUserRole;
//	
//
//
//	public PusReUserRole getPusReUserRole() {
//		return pusReUserRole;
//	}
//
//	public void setPusReUserRole(PusReUserRole pusReUserRole) {
//		this.pusReUserRole = pusReUserRole;
//	}

	/** default constructor */
	public PusReRoleMenu() {
	}

	/** full constructor */
	public PusReRoleMenu(Integer roleid, Integer menuid, Integer state) {
		this.roleid = roleid;
		this.menuid = menuid;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getMenuid() {
		return this.menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}