package cn.telling.common.enums.users;

/** 
 * 性别枚举
 * @author zhanghb01
 *
 */
public enum Gender {
	Man(1,"男"),Woman(0,"女");
	
	private int _value;
	private String _text;
	private Gender(int value,String text)
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
	 * Int 转换为枚举
	 * @param v
	 * @return
	 */
	public static Gender valueOf(int v)
	{
		switch (v) {
		case 0:
			return Woman;
		case 1:
			return Man;
		default:
			return Man;
		}
	}
}
