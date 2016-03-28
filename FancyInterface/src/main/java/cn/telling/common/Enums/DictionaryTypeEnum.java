/**
 * 
 */
package cn.telling.common.Enums;
/**   
 * @Title: DictionaryType.java 
 * @Package com.Common.Enums 
 * @Description: 该枚举主要是使用字典表的时候用到,
 * 调用的时候首先调用 DictionaryTypeEnum.BUYERVERIFYSTATUS得到类型名称,不要使用GetMethod("BUYERVERIFYSTATUS")这种方式传递参数
 * @author 冯俊杰
 * @date 2013-4-10 下午3:50:31 
 * @version V1.0   
 */
public enum DictionaryTypeEnum {
	
	virtualBuyerType(1,"虚拟买家类型");
	
	private int _value;
	private String _text;
	private DictionaryTypeEnum(int value,String text)
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
	public static DictionaryTypeEnum valueOf(int v)
	{
		//Enum.valueOf(UsersStatus.class, name)
		switch (v) {
		case 1:
			return virtualBuyerType;
		default:
			return virtualBuyerType;
		}
	}

}
