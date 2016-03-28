package cn.telling.shop.vo;

/**
 * @Title: ShopInfo.java
 * @Package com.emp.infomationapp.vo
 * @Description: 店铺信息表
 * @author caosheng
 * @date 2015-3-18 下午5:00:47
 * @version V1.0
 */
public class ShopInfo {

	/***
	 * 店铺编号
	 */
	private String id;

	/***
	 * 店铺用户id
	 */
	private String selleruserid;

	/***
	 * 店铺商标表
	 */
	private String shopLogo;

	/***
	 * 店铺banner
	 */
	private String shopBanner;

	/***
	 * 店铺广告
	 */
	private String shopPic;

	/***
	 * 店铺简介
	 */
	private String shopIntroduction;

	/***
	 * 店铺名称
	 */
	private String shopName;

	/***
	 * 物流承诺
	 */
	private String promise;

	/***
	 * 删除标示
	 */
	private char delFlag;

	/***
	 * 添加日期
	 */
	private int addDate;

	/***
	 * 添加人
	 */
	private String addUser;

	/***
	 * 修改日期
	 */
	private String updateDate;

	/***
	 * 修改人
	 */
	private String updateUser;

	/***
	 * 店铺地址二级域名关键字
	 */
	private String shopSld;

	/***
	 * 域名是否有效：1有效，0无效
	 */
	private char sldIsvalid;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getSelleruserid()
	{
		return selleruserid;
	}

	public void setSelleruserid(String selleruserid)
	{
		this.selleruserid = selleruserid;
	}

	public String getShopLogo()
	{
		return shopLogo;
	}

	public void setShopLogo(String shopLogo)
	{
		this.shopLogo = shopLogo;
	}

	public String getShopBanner()
	{
		return shopBanner;
	}

	public void setShopBanner(String shopBanner)
	{
		this.shopBanner = shopBanner;
	}

	public String getShopPic()
	{
		return shopPic;
	}

	public void setShopPic(String shopPic)
	{
		this.shopPic = shopPic;
	}

	public String getShopIntroduction()
	{
		return shopIntroduction;
	}

	public void setShopIntroduction(String shopIntroduction)
	{
		this.shopIntroduction = shopIntroduction;
	}

	public String getShopName()
	{
		return shopName;
	}

	public void setShopName(String shopName)
	{
		this.shopName = shopName;
	}

	public String getPromise()
	{
		return promise;
	}

	public void setPromise(String promise)
	{
		this.promise = promise;
	}

	public char getDelFlag()
	{
		return delFlag;
	}

	public void setDelFlag(char delFlag)
	{
		this.delFlag = delFlag;
	}

	public int getAddDate()
	{
		return addDate;
	}

	public void setAddDate(int addDate)
	{
		this.addDate = addDate;
	}

	public String getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(String updateDate)
	{
		this.updateDate = updateDate;
	}

	public String getUpdateUser()
	{
		return updateUser;
	}

	public void setUpdateUser(String updateUser)
	{
		this.updateUser = updateUser;
	}

	public String getShopSld()
	{
		return shopSld;
	}

	public void setShopSld(String shopSld)
	{
		this.shopSld = shopSld;
	}

	public char getSldIsvalid()
	{
		return sldIsvalid;
	}

	public void setSldIsvalid(char sldIsvalid)
	{
		this.sldIsvalid = sldIsvalid;
	}

	/*
	 * @(#)空构造函数 2015-3-18
	 * 
	 * Copyright (c) 2015, Simpo Technology. All Rights Reserved. Simpo Technology. CONFIDENTIAL
	 */
	public ShopInfo()
	{

	}

	public String getAddUser()
	{
		return addUser;
	}

	public void setAddUser(String addUser)
	{
		this.addUser = addUser;
	}

	@Override
	public String toString()
	{
		return "ShopInfo [id=" + id + ", selleruserid=" + selleruserid + ", shopLogo=" + shopLogo + ", shopBanner="
				+ shopBanner + ", shopPic=" + shopPic + ", shopIntroduction=" + shopIntroduction + ", shopName="
				+ shopName + ", promise=" + promise + ", delFlag=" + delFlag + ", addDate=" + addDate + ", addUser="
				+ addUser + ", updateDate=" + updateDate + ", updateUser=" + updateUser + ", shopSld=" + shopSld
				+ ", sldIsvalid=" + sldIsvalid + "]";
	}

}
