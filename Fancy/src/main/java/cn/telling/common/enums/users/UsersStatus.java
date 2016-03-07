package cn.telling.common.enums.users;


/**
 * 用户状态枚举
 * @author zhanghb01
 * 
 */
public enum UsersStatus {
	Available(1,"可用"),Unavailable(0,"不可用"),Deleted(4,"已删除"),Freeze(5,"已冻结");
	
	private int _value;
	private String _text;
	private UsersStatus(int value,String text)
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
	
	/**
	 * Int转换为枚举
	 * @param v
	 * @return
	 */
	public static UsersStatus valueOf(int v)
	{
		//Enum.valueOf(UsersStatus.class, name)
		switch (v) {
		case 1:
			return Available;
		case 0:
			return Unavailable;
		case 4:
			return Deleted;
		case 5:
			return Freeze;
		default:
			return Unavailable;
		}
	}
}
