package cn.telling.shop.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: ShopRetnVo
 * TODO
 * @author xingle
 * @date 2015-8-24 上午9:43:27
 */
public class ShopRetnVo implements Serializable{

	/**
	* @Fields serialVersionUID : TODO(描述变量表示)
	*/
	private static final long serialVersionUID = -4539123817408752461L;
	
	/**
	* @Fields shop : 店铺基本信息
	*/
	private List<ShopInfoVo> shop;

	/**
	* @Fields total : TODO(描述变量表示)
	*/
	private int total;

	public List<ShopInfoVo> getShop() {
		return shop;
	}

	public void setShop(List<ShopInfoVo> shop) {
		this.shop = shop;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	

}
