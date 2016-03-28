package cn.telling.user.vo;

import java.io.Serializable;
import java.util.List;

/**   
 * @Title: ReturnUserVo.java 
 * @Package cn.telling.user.vo 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2016年1月1日 下午4:51:06 
 * @version V1.0   
 */
public class ReturnUserVo implements Serializable{
	private static final long serialVersionUID = -7522049764961540012L;
	private List<?> userLs;
	private int totalCount;
	
	public List<?> getUserLs() {
		return userLs;
	}
	
	public void setUserLs(List<?> userLs) {
		this.userLs = userLs;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}

