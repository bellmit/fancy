package cn.telling.areas.Service.Impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.telling.areas.Dao.IAreaInfoManageDao;
import cn.telling.areas.service.IAreaInfoManageService;
import cn.telling.areas.vo.AreaInfoManageVo;
import cn.telling.areas.vo.AreaInfoVo;
import cn.telling.utils.StringHelperTools;

@Service
public class AreaInfoManageServiceImpl implements IAreaInfoManageService {
	@Resource
	private IAreaInfoManageDao areaInfoManageDao;

	@Override
	public List<AreaInfoManageVo> queryAllAreaInfo() {
		return areaInfoManageDao.queryAllAreaInfo();
	}

	@Override
	public String getAllTree(List<AreaInfoManageVo> allList, Object object) {
		StringBuffer tree = new StringBuffer("[");
		if (allList != null && allList.size() > 0) {
			for (int i = 0; i < allList.size(); i++) {
				AreaInfoManageVo vo = allList.get(i);
				tree.append("{id:");
				tree.append("'" + vo.getAreaId() + "'");
				tree.append(",");
				tree.append("pId:");
				tree.append("'" + vo.getParentId() + "'");
				tree.append(",");
				tree.append("name:");
				tree.append("'" + vo.getAreaName() + "'");
				// 如果一级的话展开
				if(StringHelperTools.nvl(vo.getAreaId()).equals("2")){
					tree.append(",");
					tree.append("checked:true");
					tree.append(",");
					tree.append("open:true");
				}
				if (i == allList.size() - 1) {
					tree.append("}]");
				} else {
					tree.append("},");
				}
			}
		}
		return tree.toString();
	}

	@Override
	@Transactional(value = "txManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public boolean insertAreaInfo(AreaInfoManageVo vo) {
		// 获取父areaid
		BigDecimal areaid = vo.getParentId();
		// 判断这个父id是不是中国
		if(StringHelperTools.nvl(areaid).equals("2")){
			vo.setAreaLevel(new BigDecimal(2));
		}else{
			AreaInfoManageVo v = areaInfoManageDao.queryAreaInfoByAreaId(areaid);
			if(StringHelperTools.nvl(v.getAreaLevel()).equals("3")){
				if(vo.getAreaName().endsWith("区")){
					vo.setISQUXIAN("1");
				}else{
					vo.setISQUXIAN("2");
				}
			}
			vo.setAreaLevel(new BigDecimal(Integer.parseInt(v.getAreaLevel()+"")+1));
		}
		return this.areaInfoManageDao.insertAreaInfo(vo);
	}

	@Override
	public AreaInfoManageVo queryAreaInfoByAreaId(BigDecimal areaId) {
		// TODO Auto-generated method stub
		return areaInfoManageDao.queryAreaInfoByAreaId(areaId);
	}

	@Override
	@Transactional(value = "txManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public boolean updateAreaInfo(AreaInfoManageVo vo) {
		// TODO Auto-generated method stub
		return areaInfoManageDao.updateAreaInfo(vo);
	}

	@Override
	public boolean getAreaInfoChild(String areaId) {
		// TODO Auto-generated method stub
		return areaInfoManageDao.getAreaInfoChild(areaId);
	}

	@Override
	@Transactional(value = "txManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public boolean deleteAreaInfo(BigDecimal areaid) {
		// TODO Auto-generated method stub
		return areaInfoManageDao.deleteAreaInfo(areaid);
	}

	@Override
	public List<AreaInfoVo> getAreaInfoByLevel(BigDecimal level) {
		// TODO Auto-generated method stub
		return areaInfoManageDao.getAreaInfoByLevel(level);
	}

	@Override
	public List<AreaInfoVo> getAreaInfoByAreaId(String provinceId) {
		// TODO Auto-generated method stub
		return areaInfoManageDao.getAreaInfoByAreaId(provinceId);
	}

	@Override
	public String getParentByAreaId(String areaId) {
		// TODO Auto-generated method stub
		return areaInfoManageDao.getParentByAreaId(areaId);
	}

}
