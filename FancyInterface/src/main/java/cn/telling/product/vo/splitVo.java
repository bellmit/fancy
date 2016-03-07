package cn.telling.product.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: splitVo
 * TODO
 * @author xingle
 * @date 2015-7-29 下午6:26:08
 */
public class splitVo implements Serializable{
	
	/**
	* @Fields serialVersionUID : TODO(描述变量表示)
	*/
	private static final long serialVersionUID = -2729781533191680038L;
	private  String keyword;
	private  String field;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	
	

}
