package cn.telling.areas.Dao.Impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.telling.areas.Dao.IAreaInfoManageDao;
import cn.telling.areas.vo.AreaInfoManageVo;
import cn.telling.areas.vo.AreaInfoVo;
import cn.telling.utils.AutoInjection;
import cn.telling.utils.BaseUtil;
import cn.telling.utils.StringHelperTools;
import cn.telling.utils.TellingConstance;

@Repository
public class AreaInfoManageDaoImpl implements IAreaInfoManageDao {
	private static Logger logger = Logger.getLogger(AreaInfoManageDaoImpl.class);
	@Resource(name="film-template")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<AreaInfoManageVo> queryAllAreaInfo() {
		String strsql = "select areaid ,areaname,parentid from areainfo where status='1' start with parentid=? connect by prior areaid=parentid";
		Object args [] = new Object[]{TellingConstance.AREAROOT};
		logger.debug(BaseUtil.logSQL(strsql,args));
		return jdbcTemplate.query(strsql,args,new RowMapper<AreaInfoManageVo>() {
			public AreaInfoManageVo mapRow(ResultSet rs, int rowNum)throws SQLException {
				AreaInfoManageVo vo = new AreaInfoManageVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
	}

	@Override
	public boolean insertAreaInfo(AreaInfoManageVo vo) {
		String sql = "INSERT INTO AREAINFO(AREAID,AREANAME,PARENTID,AREACODE,STATUS,CREATETIME,NC_PK_AREACL,NC_PK_CORP,NC_DEF1,NC_PK_FATHERAREA,AREALEVEL,DR,ISQUXIAN)" +
				" VALUES(SEQ_AREA_KEY.NEXTVAL,?,?,?,'1',SYSDATE,?,?,?,?,?,'0',?)";
		Object[] args = new Object[]{
				StringHelperTools.nvl(vo.getAreaName()),
				vo.getParentId(),
				StringHelperTools.nvl(vo.getAreaCode()),
				StringHelperTools.nvl(vo.getNC_PK_AREACL()),
				StringHelperTools.nvl(vo.getNC_PK_CORP()),
				StringHelperTools.nvl(vo.getNC_DEF1()),
				StringHelperTools.nvl(vo.getNC_PK_FATHERAREA()),
				vo.getAreaLevel(),
				StringHelperTools.nvl(vo.getISQUXIAN())
		};
		logger.debug(BaseUtil.logSQL(sql, args));
		return jdbcTemplate.update(sql, args) == 1 ? true : false;
	}

	@Override
	public AreaInfoManageVo queryAreaInfoByAreaId(BigDecimal areaId) {
		String sql = "select * from areainfo ai where ai.areaid = ? and ai.status='1'";
		Object[] args = new Object[]{areaId};
		logger.debug(BaseUtil.logSQL(sql,args));
		List<AreaInfoManageVo> list = jdbcTemplate.query(sql,args,new RowMapper<AreaInfoManageVo>() {
			public AreaInfoManageVo mapRow(ResultSet rs, int rowNum)throws SQLException {
				AreaInfoManageVo vo = new AreaInfoManageVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
		if(list.size()!=0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean updateAreaInfo(AreaInfoManageVo vo) {
		String sql = "UPDATE AREAINFO AI SET AI.AREANAME=?, AI.PARENTID=?, AI.AREACODE=?, AI.NC_PK_AREACL=?, AI.NC_PK_CORP=?, AI.NC_DEF1=?, AI.NC_PK_FATHERAREA=?" +
				" WHERE AI.AREAID=?";
		Object[] args = new Object[]{
				StringHelperTools.nvl(vo.getAreaName()),
				StringHelperTools.nvl(vo.getParentId()),
				StringHelperTools.nvl(vo.getAreaCode()),
				StringHelperTools.nvl(vo.getNC_PK_AREACL()),
				StringHelperTools.nvl(vo.getNC_PK_CORP()),
				StringHelperTools.nvl(vo.getNC_DEF1()),
				StringHelperTools.nvl(vo.getNC_PK_FATHERAREA()),
				vo.getAreaId()
		};
		logger.debug(BaseUtil.logSQL(sql, args));
		return jdbcTemplate.update(sql, args) == 1 ? true : false;
	}

	@Override
	public boolean getAreaInfoChild(String areaId) {
		String sql = "select * from areainfo ai where ai.parentid = ? and ai.status='1'";
		Object[] args = new Object[]{ areaId };
		logger.debug(BaseUtil.logSQL(sql, args));
		List<AreaInfoManageVo> list = jdbcTemplate.query(sql, args, new RowMapper<AreaInfoManageVo>() {
			public AreaInfoManageVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				AreaInfoManageVo vo = new AreaInfoManageVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
		return list.size() != 0 ? true : false;
	}

	@Override
	public boolean deleteAreaInfo(BigDecimal areaid) {
		String sql = "DELETE FROM AREAINFO AI WHERE AI.AREAID = ?";
		Object[] args = new Object[]{ areaid };
		logger.debug(BaseUtil.logSQL(sql, args));
		return jdbcTemplate.update(sql, args) == 1 ? true : false;
	}

	@Override
	public List<AreaInfoVo> getAreaInfoByLevel(BigDecimal level) {
		String sql = "SELECT * FROM AREAINFO WHERE AREALEVEL=?";
		Object[] args = new Object[]{ level };
		return jdbcTemplate.query(sql, args, new RowMapper<AreaInfoVo>() {
			public AreaInfoVo mapRow(ResultSet rs, int i) {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
	}

	@Override
	public List<AreaInfoVo> getAreaInfoByAreaId(String provinceId) {
		String sql = "SELECT * FROM AREAINFO AI WHERE AI.PARENTID=?";
		Object[] args = new Object[]{ provinceId };
		return jdbcTemplate.query(sql, args, new RowMapper<AreaInfoVo>() {
			public AreaInfoVo mapRow(ResultSet rs, int i) {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
	}

	@Override
	public String getParentByAreaId(String areaId) {
		String sql = "SELECT * FROM AREAINFO AI WHERE AI.AREAID=?";
		Object[] args = new Object[]{ areaId };
		List<AreaInfoVo> list =  jdbcTemplate.query(sql, args, new RowMapper<AreaInfoVo>() {
			public AreaInfoVo mapRow(ResultSet rs, int i) {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
		return list.get(0).getParentId().toString();
	}
	
}
