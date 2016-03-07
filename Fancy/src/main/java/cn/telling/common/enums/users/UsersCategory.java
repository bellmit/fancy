package cn.telling.common.enums.users;

/**
 * 用户类别枚举
 * @author zhanghb01
 *
 */
public enum UsersCategory {
	Administrator(1,"超级管理员"),PlatformAdmin(2,"管理员"),Supply(3,"供应商"),Buyer(4,"买家"),Staff(5,"员工"),gridmanager(6,"联通用户"),commonBuyer(7,"普通买家"),tempSeller(8,"临时卖家");
	
	
	private int _value;
	private String _text;
	private UsersCategory(int value,String text)
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
	public static UsersCategory valueOf(int v)
	{
		switch (v) {
		case 1:
			return Administrator;
		case 2:
			return PlatformAdmin;
		case 3:
			return Supply;
		case 4:
			return Buyer;
		case 5:
			return Staff;
		case 6:
			return gridmanager;
		case 7:
			return commonBuyer;
		case 8:
			return tempSeller;
		default:
			return Buyer;
		}
	}
}
