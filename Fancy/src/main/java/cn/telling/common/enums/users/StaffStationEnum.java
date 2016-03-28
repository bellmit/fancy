package cn.telling.common.enums.users;


/**   
 * @Title: StaffStationEnum.java 
 * @Package com.Common.Enums.staff 
 * @Description: TODO(描述该文件做什么) 
 * @author taoye  
 * @date 2013-6-4 上午11:37:32 
 * @version V1.0   
 */
public enum StaffStationEnum {
	Business(1,"商务"),Customer (2,"客户代表");
	
	private int _value;
	private String _text;
	private StaffStationEnum(int value,String text)
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
	public static StaffStationEnum valueOf(int v)
	{
		//Enum.valueOf(UsersStatus.class, name)
		switch (v) {
		case 1:
			return Business;
		case 2:
			return Customer;
		default:
			return Business;
		}
	}
}
