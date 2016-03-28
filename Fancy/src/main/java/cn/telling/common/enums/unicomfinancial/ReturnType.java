package cn.telling.common.enums.unicomfinancial;



/**   
 * @Title: ReturnType.java 
 * @Package com.Common.Enums.unicomfinancial 
 * @Description: 联通财务返还类型 
 * @author jiangchengkai   
 * @date 2013-5-10 上午11:49:56 
 * @version V1.0   
 */
public enum ReturnType {
	SpeAwards(1,"特殊奖励"),Superimposed(2,"省市叠加"),Commission(3,"佣金查询"),ConstractAwards(4,"合约奖励"),MachineReturn(5,"机款返还"),DisCommission(6,"代垫佣金");
	
	private int _value;
	private String _text;
	private ReturnType(int value,String text)
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
	public static ReturnType valueOf(int v)
	{
		//Enum.valueOf(UsersStatus.class, name)
		switch (v) {
		case 1:
			return SpeAwards;
		case 2:
			return Superimposed;
		case 3:
			return Commission;
		case 4:
			return ConstractAwards;
		case 5:
			return MachineReturn;
		default:
			return MachineReturn;
		}
	}
}

