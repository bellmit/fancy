package cn.telling.common.enums.buyer;

public enum BuyerRelationSysFlagEnum {
	nc(1,"nc标示");
	private int _value;
	private String _text;
	private BuyerRelationSysFlagEnum(int value,String text)
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
