package cn.telling.common.Enums;


/**   
 * @Title: Status.java 
 * @Package com.Common.Enums 
 * @Description: TODO(描述该文件做什么) 
 * @author 张海斌
 * @date 2013-4-15 上午11:10:20 
 * @version V1.0   
 */
public enum Status {
	valid(1,"有效的"),invalid(0,"无效的"),deleted(4,"已删除");
	
	private int _value;
	private String _text;
	private Status(int value,String text)
	{
		_value = value;
		_text = text;
	}
	
	public int value()
	{
		return _value;
	}
	
	public String text()
	{
		return _text;
	}
	public int getValue()
	{
		return _value;
	}
	
	public String getText()
	{
		return _text;
	}
	
	/**
	 * Int转换为枚举
	 * @param v
	 * @return
	 */
	public static Status valueOf(int v)
	{
		//Enum.valueOf(UsersStatus.class, name)
		switch (v) {
		case 1:
			return valid;
		case 0:
			return invalid;
		case 4:
			return deleted;
		default:
			return invalid;
		}
	}
}

