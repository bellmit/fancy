/**
 * 
 */
package cn.telling.common.Enums;
/**   
 * @Title: AreaLevel.java 
 * @Package com.Common.Enums 
 * @Description: TODO(描述该文件做什么) 
 * @author 冯俊杰
 * @date 2013-5-9 上午11:20:01 
 * @version V1.0   
 */
public enum AreaLevel {
	country(1,"国家"),province(2,"省份"),city(3,"市"),county(4,"区县"),invalid(5,"无效");
	
	private int _value;
	private String _text;
	private AreaLevel(int value,String text)
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
	public static AreaLevel valueOf(int v)
	{
		//Enum.valueOf(UsersStatus.class, name)
		switch (v) {
		case 1:
			return country;
		case 0:
			return province;
		case 4:
			return county;
		default:
			return invalid;
		}
	}
}
