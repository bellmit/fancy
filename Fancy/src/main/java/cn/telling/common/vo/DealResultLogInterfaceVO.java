package cn.telling.common.vo;



public class DealResultLogInterfaceVO implements IBaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pk_interface;
	private String sender;
	private String process;
	private String result;
	private String msg;

	public String getPk_interface() {
		return pk_interface;
	}

	public void setPk_interface(String pk_interface) {
		this.pk_interface = pk_interface;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String getTableName() {
		return "lg_interface_process";
	}

	@Override
	public String getPrimaryKey() {
		return "pk_interface";
	}

	@Override
	public String getRootTag() {
		return "interfaces";
	}

	public DealResultLogInterfaceVO(String pk_interface,String sender, String process, String result,String msg) {
		this.pk_interface = pk_interface;
		this.sender = sender;
		this.process = process;
		this.result = result;
		this.msg = msg;
	}

	public DealResultLogInterfaceVO() {

	}
}
