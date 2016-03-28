package cn.telling.areas.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class AreaInfoManageVo implements Serializable{
	private static final long serialVersionUID = -3815623018058168674L;
	private BigDecimal areaId;//区域id
	private String areaName;//区域名称
	private BigDecimal parentId;//区域父id
	private String areaCode;//区域编码
	private String status;//状态
	private String createTime;//创建时间
	
	private String NC_PK_AREACL;
	private String NC_PK_CORP;
	private String NC_DEF1;
	private String NC_PK_FATHERAREA;
	
	private BigDecimal areaLevel;//区域等级
	
	private BigDecimal DR;
	private String ISQUXIAN;//是否为区县
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
	public String getNC_PK_AREACL() {
		return NC_PK_AREACL;
	}
	public void setNC_PK_AREACL(String nC_PK_AREACL) {
		NC_PK_AREACL = nC_PK_AREACL;
	}
	public String getNC_PK_CORP() {
		return NC_PK_CORP;
	}
	public void setNC_PK_CORP(String nC_PK_CORP) {
		NC_PK_CORP = nC_PK_CORP;
	}
	public String getNC_DEF1() {
		return NC_DEF1;
	}
	public void setNC_DEF1(String nC_DEF1) {
		NC_DEF1 = nC_DEF1;
	}
	public String getNC_PK_FATHERAREA() {
		return NC_PK_FATHERAREA;
	}
	public void setNC_PK_FATHERAREA(String nC_PK_FATHERAREA) {
		NC_PK_FATHERAREA = nC_PK_FATHERAREA;
	}
	public BigDecimal getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(BigDecimal areaLevel) {
		this.areaLevel = areaLevel;
	}
	public BigDecimal getDR() {
		return DR;
	}
	public void setDR(BigDecimal dR) {
		DR = dR;
	}
	public String getISQUXIAN() {
		return ISQUXIAN;
	}
	public void setISQUXIAN(String iSQUXIAN) {
		ISQUXIAN = iSQUXIAN;
	}
	
//	private String AREACLCODE;
//	private String AREACLNAME;
//	private String NC_PK_AREACL;
//	private String PK_CORP;
//	private String DEF1;
//	private String PK_FATHERAREA;
//	private Status enumStatus;
	
}
