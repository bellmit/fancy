package cn.telling.common.vo;



import java.io.Serializable;

public interface IBaseVO extends Serializable,Cloneable{
	
	public  String getTableName();
//	public abstract String [] getFieldNames();
//	public abstract String [] getFieldTypeNames();
	public  String getPrimaryKey();
//	public abstract String getClassTag();
	public  String getRootTag();
	
//	public abstract ValueObject getValueObject();

	
}
