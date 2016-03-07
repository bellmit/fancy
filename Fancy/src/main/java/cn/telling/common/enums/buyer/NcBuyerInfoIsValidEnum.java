package cn.telling.common.enums.buyer;

public enum NcBuyerInfoIsValidEnum {
	
	yes(1,"有效"),no(0,"无效");
	private int _value;
	private String _text;
	private NcBuyerInfoIsValidEnum(int value,String text)
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

}
