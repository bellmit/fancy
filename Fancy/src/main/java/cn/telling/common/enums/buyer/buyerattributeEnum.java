/**
 * 
 */
package cn.telling.common.enums.buyer;
/**   
 * @Title: buyerattributeEnum.java 
 * @Package com.Common.Enums.buyer 
 * @Description: TODO(描述该文件做什么) 
 * @author 冯俊杰
 * @date 2013-4-12 下午2:54:51 
 * @version V1.0   
 */
/**
 * @author Administrator
 *
 */
public enum buyerattributeEnum {

	yidong(1,"移动"),liantong(2,"联通"),dianxin(3,"电信"),电信(4,"开放平台");
	private int _value;
	private String _text;
	private buyerattributeEnum(int value,String text)
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
