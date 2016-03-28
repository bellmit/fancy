package cn.telling.common.enums.logoManage;


public enum OperatorTypeEnum {
	Zero(0,"---"),LinkType(1,"联通"),MoveType(2,"移动"),TelecomType(3,"电信"),CommonLogo(4,"通用");
	private int _value;
	private String _text;
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
	private OperatorTypeEnum(int value,String text)
	{
		_value = value;
		_text = text;
	}
	/**
	 * Int 转换为枚举
	 * @param v
	 * @return
	 */
	public static OperatorTypeEnum valueOf(int v)
	{
		switch (v) {
		case 1:
			return LinkType;
		case 2:
			return MoveType;
		case 3:
			return TelecomType;
		case 4:
			return CommonLogo;
		default:
			return Zero;
		}
	}

}
