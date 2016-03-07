package cn.telling.areas.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import cn.telling.common.Enums.Status;


public class AreaInfoVo implements Serializable{
	
	private static final long serialVersionUID = 5298037900885111339L;
	private BigDecimal areaId;
	private String areaName;
	private BigDecimal parentId;
	private String areaCode;
	private String status;
	private String createTime;
	private BigDecimal areaLevel;
	private String AREACLCODE;
	private String AREACLNAME;
	private String NC_PK_AREACL;
	private String PK_CORP;
	private String DEF1;
	private String PK_FATHERAREA;
	private Status enumStatus;
	private String areaStatus;
	
	
	
	

	public String getNC_PK_AREACL() {
		return NC_PK_AREACL;
	}
	public void setNC_PK_AREACL(String nC_PK_AREACL) {
		NC_PK_AREACL = nC_PK_AREACL;
	}
	public Status getEnumStatus() {
		return enumStatus;
	}
	public void setEnumStatus(int enumStatus) {
		this.enumStatus = Status.valueOf(enumStatus);
	}
	public BigDecimal getAreaId() {
		return areaId;
	}
	public void setAreaId(BigDecimal areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public BigDecimal getParentId() {
		return parentId;
	}
	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(BigDecimal areaLevel) {
		this.areaLevel = areaLevel;
	}
	
	public String getAREACLCODE() {
		return AREACLCODE;
	}
	public void setAREACLCODE(String aREACLCODE) {
		AREACLCODE = aREACLCODE;
	}
	public String getAREACLNAME() {
		return AREACLNAME;
	}
	public void setAREACLNAME(String aREACLNAME) {
		AREACLNAME = aREACLNAME;
	}
	public String getPK_CORP() {
		return PK_CORP;
	}
	public void setPK_CORP(String pK_CORP) {
		PK_CORP = pK_CORP;
	}
	public String getDEF1() {
		return DEF1;
	}
	public void setDEF1(String dEF1) {
		DEF1 = dEF1;
	}
	public String getPK_FATHERAREA() {
		return PK_FATHERAREA;
	}
	public void setPK_FATHERAREA(String pK_FATHERAREA) {
		PK_FATHERAREA = pK_FATHERAREA;
	}
	public void setEnumStatus(Status enumStatus) {
		this.enumStatus = enumStatus;
	}
	public String getAreaStatus() {
		return areaStatus;
	}
	public void setAreaStatus(String areaStatus) {
		this.areaStatus = areaStatus;
	}
}
