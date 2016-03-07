package cn.telling.common.enums.users;
/**   
 * @Title: LoginSucceedPath.java 
 * @Package com.Common.Enums.Users 
 * @Description: TODO(描述该文件做什么) 
 * @author 张海斌
 * @date 2013-4-10 下午4:02:54 
 * @version V1.0   
 */
public enum LoginSucceedPath {
	AdministratorPath(1,"/Welcome"),PlatformAdminPath(2,"/Welcome"),
	SupplyPath(3,"redirect:/customer/logindirect.html"),BuyerPath(4,"redirect:/customer/logindirect.html"),Staff(5,"/Welcome"),gridmanager(6,"/Welcome"),commonBuyerPath(7,"redirect:/customer/logindirect.html"),OtherPath(-1,"/");
	
	private int _value;
	private String _text;
	private LoginSucceedPath(int value,String text)
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
	
	public static LoginSucceedPath valueOf(int v)
	{
		switch (v) {
		case 1:
			return AdministratorPath;
		case 2:
			return PlatformAdminPath;
		case 3:
			return SupplyPath;
		case 4:
			return BuyerPath;
		case 5:
			return Staff;
		case 6:
			return gridmanager;
		case 7:
			return commonBuyerPath;
		default:
			return OtherPath;
		}
	}
}

