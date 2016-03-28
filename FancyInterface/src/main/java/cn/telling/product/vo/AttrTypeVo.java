package cn.telling.product.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: AttrTypeVo
 * TODO
 * @author xingle
 * @date 2015-8-11 上午9:41:02
 */
public class AttrTypeVo implements Serializable{
	
	/**
	* @Fields serialVersionUID : TODO(描述变量表示)
	*/
	private static final long serialVersionUID = -5929302608874169824L;
	/**
	* @Fields type : {@code 1}品牌，{@code 2} 型号，{@code 3} 颜色
	*/
	private  String type;	
	/**
	* @Fields value : 具体对应type的值，例如 华为，iphone 4s，红色等
	*/
	private  String value;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
