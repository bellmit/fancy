package cn.telling.areas.Service.Impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.telling.areas.Dao.IAreaInfoDao;
import cn.telling.areas.service.IAreaInfoService;
import cn.telling.areas.vo.AreaConditionVo;
import cn.telling.areas.vo.AreaInfoVo;
import cn.telling.common.pager.PageVo;

@Service
public class AreaInfoServiceImpl implements IAreaInfoService {

	@Resource
	private IAreaInfoDao areaDao;
	
	@Override
	@Transactional(value="txManager",isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int Save(AreaInfoVo area) {
		return areaDao.Save(area);
	}

	@Override
	public List<AreaInfoVo> GetByPage(AreaConditionVo condition, PageVo pageVo) {
		return areaDao.GetByPage(condition, pageVo);
	}

	@Override
	public int GetTotalCount(AreaConditionVo condition) {
		return areaDao.GetTotalCount(condition);
	}

	@Override
	public AreaInfoVo GetById(BigDecimal areaId) {
		return areaDao.GetById(areaId);
	}

	@Override
	public void DeleteById(BigDecimal areaId) {
		areaDao.DeleteById(areaId);
	}

	@Override
	public List<AreaInfoVo> GetByConditionFuzzy(AreaConditionVo condition) {
		return areaDao.GetByConditionFuzzy(condition);
	}

	@Override
	public int RelationOrg2Area(String orgId, String areaId) {
		return areaDao.RelationOrg2Area(orgId,areaId);
	}

	@Override
	public List<AreaInfoVo> GetByParentId(BigDecimal parentId) {
		return areaDao.GetByParentId(parentId);
	}

	@Override
	public List<AreaInfoVo> GetByLevel(BigDecimal areaLevel) {
		return areaDao.GetByLevel(areaLevel);
	}
	
	@Override
	public List<AreaInfoVo> GetByUserId(String areaLevel,String type) {
		return areaDao.GetByUserId(areaLevel,type);
	}
       @Override
	public List<AreaInfoVo> GetAreaParents(List<AreaInfoVo> pList,BigDecimal areaId)
	{
	    //???????????????????????????ID????????????????????????????????????ID???????????????VO
		AreaInfoVo areaInfoVo=this.GetById(areaId);
		if(areaInfoVo!=null)
		{
			pList.add(areaInfoVo);
			AreaInfoVo areaInfoVoTem=this.GetById(areaInfoVo.getParentId());
			if(areaInfoVoTem!=null)
			{
				if(!areaInfoVoTem.getParentId().equals(BigDecimal.valueOf(0)))
				{
					pList.add(areaInfoVoTem);
					GetAreaParents(pList,areaInfoVoTem.getParentId());
				}
			}
		}
		return pList;
	}

	/**
	 * @Description: ??????UserId??????????????????
	 * @param		????????????
	 * @return		?????????
	 * @exception   ????????????
	 * @see		          ??????????????????????????????????????????
	 * @author      guohui
	 * @date 2013-5-15 ??????11:59:38 
	 * @version V1.0
	 */
	@Override
	public List<AreaInfoVo> GetManageAreaByUserId(BigDecimal userId) {
		return areaDao.GetManageAreaByUserId(userId);
	}
	@Override
	public BigDecimal Get3AreaById(BigDecimal areaId){
		return areaDao.Get3AreaById(areaId);
	}
	@Override
	public BigDecimal GetAreaByIdAndLevel(BigDecimal areaId,int level){
		return areaDao.GetAreaByIdAndLevel(areaId,level);
	}
	/**
	 * @Description: ????????????????????????????????????
	 * @param		????????????
	 * @return		?????????
	 * @exception   ????????????
	 * @see		          ??????????????????????????????????????????
	 * @author      ?????????
	 * @date 2013-8-2 ??????4:27:11 
	 * @version V1.0
	 */
	@Override
	public List<AreaInfoVo> queryOneLevelByParentId(BigDecimal areaId) {
		return areaDao.queryOneLevelByParentId(areaId);
	}
	
	
	@Override
	public List<AreaInfoVo> queryManageAreaByUserIdAndAreaid(String staffId,String areaId){
		return areaDao.queryManageAreaByUserIdAndAreaid(staffId,areaId);
	}
	
	@Override
	public List<AreaInfoVo> queryManageProvinceAreaByUserId(String userId){
		return areaDao.queryManageProvinceAreaByUserId(userId);
	}
	/**
	 * @Description: ??????UserId???????????????????????????
	 * @param		userId????????????
	 * @return		?????????
	 * @exception   ????????????
	 * @see		          ??????????????????????????????????????????
	 * @author      ??????
	 * @date 2013-11-11 ??????9:29:38 
	 * @version V1.0
	 */
	@Override
	public AreaInfoVo GetParentAreaByUserId(BigDecimal userId) {
		// TODO Auto-generated method stub
		return areaDao.GetParentAreaByUserId(userId);
	}
	/**
	 * @Description: ??????userId ????????????????????????
	 * @param userId  ??????Id
	 * @return
	 * @author liugeng
	 * @date 2013-12-20
	 */
	@Override
	public List<AreaInfoVo> queryProductPricePoliceScope(String userId,String areaLevel) {
		return areaDao.queryProductPricePoliceScope(userId,areaLevel);
	}
	
	/**
	 * @Description: ????????????????????????   ??????
	 * @return
	 * @author liugeng
	 * @date 2013-12-20
	 */
	@Override
	public List<AreaInfoVo> queryProductPricePoliceByAreaLevle(String areaLevel) {
		return areaDao.queryProductPricePoliceByAreaLevle(areaLevel);
	}
	
	/**
	 * @Description: ??????AreaId?????????  ???????????????????????? 
	 * @param areaId
	 * @param areaLevel
	 * @return
	 * @author liugeng
	 * @date 2013-12-20
	 */
	@Override
	public List<AreaInfoVo> queryProductPricePoliceByIdAndLevle(BigDecimal areaId, String areaLevel) {
		return areaDao.queryProductPricePoliceByIdAndLevle(areaId, areaLevel);
	}

	@Override
	public AreaInfoVo GetAreaBysupplyUserId(BigDecimal userId) {
		// TODO Auto-generated method stub
		return areaDao.GetAreaBysupplyUserId(userId);
	}
	
	/** 
	* ???????????????????????????????????????
	* @Description:  
	* @see cn.telling.areas.service.Areas.Service.IAreaInfoService#getProvinceInfo()  
	* @return    
	* @author xingle
	* @date 2014-4-10 ??????10:57:28
	*/
	public List<AreaInfoVo> getProvinceInfo()
	{
		return areaDao.getProvinceInfo();
	}

	@Override
	public List<AreaInfoVo> GetAreaListByUserId(String userId, String type,
			String parentID) {
		return areaDao.GetAreaListByUserId(userId, type, parentID);
	}
}
