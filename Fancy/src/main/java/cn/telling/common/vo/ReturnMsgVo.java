package cn.telling.common.vo;

public class ReturnMsgVo implements IBaseVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3242168731427321762L;

	private String code;
	
	private String result;
	
	private String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRootTag() {
		// TODO Auto-generated method stub
		return null;
	}
}
