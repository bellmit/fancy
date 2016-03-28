package cn.telling.areas.Dao;

import java.math.BigDecimal;
import java.util.List;

import cn.telling.areas.vo.AreaInfoManageVo;
import cn.telling.areas.vo.AreaInfoVo;

public interface IAreaInfoManageDao {

	public List<AreaInfoManageVo> queryAllAreaInfo();
	
	public boolean insertAreaInfo(AreaInfoManageVo vo);

	public AreaInfoManageVo queryAreaInfoByAreaId(BigDecimal areaId);
	
	public boolean updateAreaInfo(AreaInfoManageVo vo);

	public boolean getAreaInfoChild(String areaId);

	public boolean deleteAreaInfo(BigDecimal areaid);

	public List<AreaInfoVo> getAreaInfoByLevel(BigDecimal level);

	public List<AreaInfoVo> getAreaInfoByAreaId(String provinceId);

	public String getParentByAreaId(String areaId);

}
