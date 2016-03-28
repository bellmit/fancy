package cn.telling.common.enums.logoManage;


public enum LogoTypeEnum {
	Zero(0,"---"),DefaultLogo(1,"默认"),SupplyLogo(2,"卖家前台"),ManageLogo(3,"管理后台"),BuyerLogo(4,"买家前台");
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
	private LogoTypeEnum(int value,String text)
	{
		_value = value;
		_text = text;
	}
	/**
	 * Int 转换为枚举
	 * @param v
	 * @return
	 */
	public static LogoTypeEnum valueOf(int v)
	{
		switch (v) {
		case 1:
			return DefaultLogo;
		case 2:
			return SupplyLogo;
		case 3:
			return ManageLogo;
		case 4:
			return BuyerLogo;
		default:
			return Zero;
		}
	}

}
