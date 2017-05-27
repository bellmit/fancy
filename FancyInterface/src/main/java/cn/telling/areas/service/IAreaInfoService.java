package cn.telling.areas.service;

import java.math.BigDecimal;
import java.util.List;

import cn.telling.areas.vo.AreaConditionVo;
import cn.telling.areas.vo.AreaInfoVo;
import cn.telling.common.pager.PageVo;

public interface IAreaInfoService {
	public int Save(AreaInfoVo area);
	public List<AreaInfoVo> GetByPage(AreaConditionVo condition,PageVo pageVo);
	public int GetTotalCount(AreaConditionVo condition);
	public AreaInfoVo GetById(BigDecimal areaId);
	public void DeleteById(BigDecimal areaId);
	public List<AreaInfoVo> GetByConditionFuzzy(AreaConditionVo condition);
	public int RelationOrg2Area(String orgId, String areaId);
	/**
	 * 根据ParentId获取记录
	 * @Description: TODO 描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态�
	 * @param		参数说明
	 * @return		返回�
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      张海�
	 * @date 2013-4-15 上午11:59:38 
	 * @version V1.0
	 */
	public List<AreaInfoVo> GetByParentId(BigDecimal parentId);
    public List<AreaInfoVo> GetByLevel(BigDecimal areaLevel) ;
    public List<AreaInfoVo> GetByUserId(String userid,String type) ;
	public List<AreaInfoVo> GetAreaParents(List<AreaInfoVo> pList,BigDecimal areaId);

	/**
	 * @Description: 根据UserId获取管辖区域
	 * @param		参数说明
	 * @return		返回�
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      guohui
	 * @date 2013-5-15 上午11:59:38 
	 * @version V1.0
	 */
	public List<AreaInfoVo> GetManageAreaByUserId(BigDecimal userId);
	
	/**
	 * 
	 * @Description: 根据地区id取得上级市级地区id。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      wangyunlong
	 * @date 2013-6-17 下午3:03:33 
	 * @version V1.0
	 */
	public BigDecimal Get3AreaById(BigDecimal areaId);
	/**
	 * @Description:根据地区id和层级标示取得对应区域id。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @date 2013-6-17 下午3:03:33 
	 * @version V1.0
	 */
	public BigDecimal GetAreaByIdAndLevel(BigDecimal areaId,int level);
	/**
	 * @Description: 查询指定区域第一级及本身
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      杨串林
	 * @date 2013-8-2 下午4:27:11 
	 * @version V1.0
	 */
	public List<AreaInfoVo> queryOneLevelByParentId(BigDecimal areaId) ;
	
	public List<AreaInfoVo> queryManageAreaByUserIdAndAreaid(String staffId,String areaId) ;
	public List<AreaInfoVo> queryManageProvinceAreaByUserId(String userId);
	
	/**
	 * @Description: 根据UserId获取父级区域
	 * @param		参数说明
	 * @return		返回
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      王震
	 * @date 2013-12-19 上午11:59:38 
	 * @version V1.0
	 */
	public AreaInfoVo GetParentAreaByUserId(BigDecimal userId);
	/**
	 * @Description: 根据userId 查询产品政策区域范围
	 * @param userId  用户Id
	 * @param areaLevle 区域等级
	 * @return
	 * @author liugeng
	 * @date 2013-12-20
	 */
	public List<AreaInfoVo> queryProductPricePoliceScope(String userId,String areaLevel);
	/**
	 * @Description: 查询产品政策区域   省份
	 * @return
	 * @author liugeng
	 * @date 2013-12-20
	 */
	public List<AreaInfoVo> queryProductPricePoliceByAreaLevle(String areaLevel);
	
	/**
	 * @Description: 根据AreaId和等级  查询产品政策区域 
	 * @param areaId
	 * @param areaLevel
	 * @return
	 * @author liugeng
	 * @date 2013-12-20
	 */
	public List<AreaInfoVo> queryProductPricePoliceByIdAndLevle(BigDecimal areaId,String areaLevel);
	
	/**
	 * @Description: 根据UserId获取区域(供应商id)
	 * @param		参数说明
	 * @return		返回
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      王震
	 * @date 2013-12-19 上午11:59:38 
	 * @version V1.0
	 */
	public AreaInfoVo GetAreaBysupplyUserId(BigDecimal userId);
	
	/** 
	* 获取所有省和直辖市区域信息
	* @Title: getProvinceInfo 
	* @Description: TODO  
	* List<AreaInfoVo> 
	* @author xingle
	* @date 2014-4-10 上午10:56:11
	*/
	public List<AreaInfoVo> getProvinceInfo();
	
	/**
	 * 根据用户id，父级id，区域类型 获得用户下的区域
	 * @param userId
	 * @param type
	 * @param parentID
	 * @return
	 */
	public List<AreaInfoVo> GetAreaListByUserId(String userId, String type,
			String parentID);
}
