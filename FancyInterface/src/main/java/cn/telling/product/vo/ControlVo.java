package cn.telling.product.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: ControlVo
 * TODO
 * @author xingle
 * @date 2015-8-18 下午5:58:01
 */
public class ControlVo implements Serializable{
	/**
	* @Fields serialVersionUID : TODO(描述变量表示)
	*/
	private static final long serialVersionUID = -30056666696882939L;
	/**
	* @Fields iscontrol : 受控
	*/
	private String iscontrol;
	/**
	* @Fields productstandard : 产品制式
	*/
	private String productstandard;	
	/**
	* @Fields carrieroperator : 运营商
	*/
	private String carrieroperator;
	/**
	* @Fields supplier : 供应商
	*/
	private String supplier;
	public String getIscontrol() {
		return iscontrol;
	}
	public void setIscontrol(String iscontrol) {
		this.iscontrol = iscontrol;
	}
	public String getProductstandard() {
		return productstandard;
	}
	public void setProductstandard(String productstandard) {
		this.productstandard = productstandard;
	}
	public String getCarrieroperator() {
		return carrieroperator;
	}
	public void setCarrieroperator(String carrieroperator) {
		this.carrieroperator = carrieroperator;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	

}
