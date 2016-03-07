package cn.telling.areas.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class AreaConditionVo  implements Serializable{
	private static final long serialVersionUID = 7173044240814050047L;
	private BigDecimal parentId;
	private String areaName;
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	private String areaCode;

	public BigDecimal getParentId() {
		return parentId;
	}

	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}
	
}
