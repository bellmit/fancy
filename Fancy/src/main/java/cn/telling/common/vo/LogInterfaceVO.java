package cn.telling.common.vo;



public class LogInterfaceVO implements IBaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pk_interface;
	private String visittime;
	private String xmlcontent;
	private String post;
	private String type;
	private String datetag;
	private String direction;
	private String pairflag;
	private String isResult;
//	private String mac;
	
	
//
//	public String getMac() {
//		return mac;
//	}
//
//	public void setMac(String mac) {
//		this.mac = mac;
//	}

	public String getPk_interface() {
		return pk_interface;
	}

	public void setPk_interface(String pk_interface) {
		this.pk_interface = pk_interface;
	}

	// public Date getVisittime() {
	// return visittime;
	// }
	//
	// public void setVisittime(Date visittime) {
	// this.visittime = visittime;
	// }

	public String getXmlcontent() {
		return xmlcontent;
	}

	public String getVisittime() {
		return visittime;
	}

	public void setVisittime(String visittime) {
		this.visittime = visittime;
	}

	public void setXmlcontent(String xmlcontent) {
		this.xmlcontent = xmlcontent;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDatetag() {
		return datetag;
	}

	public void setDatetag(String datetag) {
		this.datetag = datetag;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	

	public String getPairflag() {
		return pairflag;
	}

	public void setPairflag(String pairflag) {
		this.pairflag = pairflag;
	}

	
	public String getIsResult() {
		return isResult;
	}

	public void setIsResult(String isResult) {
		this.isResult = isResult;
	}

	@Override
	public String getTableName() {
		return "lg_interface";
	}

	@Override
	public String getPrimaryKey() {
		return "pk_interface";
	}

	@Override
	public String getRootTag() {
		return "interfaces";
	}

	public LogInterfaceVO(String pk_interface,String visittime, String post, String type,String datetag, String direction, String pairflag, String isResult, String xmlcontent) {
		this.pk_interface = pk_interface;
		this.visittime = visittime;
		this.post = post;
		this.type = type;
		this.datetag = datetag;
		this.direction = direction;
		this.pairflag = pairflag;
		this.xmlcontent = xmlcontent;
		this.isResult = isResult;
//		this.mac=mac;
	}

	public LogInterfaceVO() {

	}

	public String toString() {
		return " 访问时间 :" + this.visittime + " IP：" + this.post + " 类型："+ this.type + " 方向：" + this.direction;
	}
}
