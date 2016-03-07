/**
 * 
 */
package cn.telling.common.Vo;

import java.io.Serializable;

/**
 * @author 朱守德
 *
 */
public class CommonMenuVo implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -7977313234368336340L;
	private String menuCateId;
     private String menuCateName;
	 private String menuId;
	 private String menuName;	
	 private String linkUrl;
	public String getMenuCateId() {
		return menuCateId;
	}
	public void setMenuCateId(String menuCateId) {
		this.menuCateId = menuCateId;
	}
	public String getMenuCateName() {
		return menuCateName;
	}
	public void setMenuCateName(String menuCateName) {
		this.menuCateName = menuCateName;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}  
}
