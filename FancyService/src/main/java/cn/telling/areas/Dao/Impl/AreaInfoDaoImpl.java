package cn.telling.areas.Dao.Impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.telling.areas.Dao.IAreaInfoDao;
import cn.telling.areas.vo.AreaConditionVo;
import cn.telling.areas.vo.AreaInfoVo;
import cn.telling.common.Enums.Status;
import cn.telling.common.Pager.PageVo;
import cn.telling.common.Pager.Pager;
import cn.telling.utils.AutoInjection;
import cn.telling.utils.Common;
import cn.telling.utils.StringHelperTools;

@Repository
public class AreaInfoDaoImpl implements IAreaInfoDao {

	private static Logger logger = Logger.getLogger(AreaInfoDaoImpl.class);

	@Resource(name="film-template")
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 保存
	 */
	@Override
	public int Save(AreaInfoVo area) {
		int effRows = 0;
		String sql = "";
		if (area.getAreaId() == null || area.getAreaId().equals("")) {
			sql = "select count(*) from AreaInfo where Areaname=? or areaCode=?";
			Object[] parms = new Object[] { area.getAreaName(), area.getAreaCode() };
			parms = StringHelperTools.nullToString(parms);
			boolean exists = getJdbcTemplate().queryForInt(sql, parms) > 0;
			if (!exists) {
				// add
				sql = "insert into AreaInfo(AreaId,Areaname,parentId,areaCode,status,createTime) values(" + "seq_area_key.nextval,?,?,?,?,sysdate)";
				parms = new Object[] { area.getAreaName(), area.getParentId(), area.getAreaCode(), area.getEnumStatus().value() };
				parms = StringHelperTools.nullToString(parms);
				effRows = getJdbcTemplate().update(sql, parms);
			} else {
				effRows = -1;
			}
		} else {
			// edit
			sql = "select count(*) from AreaInfo where (Areaname=? or areaCode=?) and areaId<>?";
			Object[] parms = new Object[] { area.getAreaName(), area.getAreaCode(), area.getAreaId() };
			parms = StringHelperTools.nullToString(parms);
			boolean exists = getJdbcTemplate().queryForInt(sql, parms) > 0;

			if (!exists) {
				sql = "update areaInfo set areaName=?,areaCode=?,status=? where areaId=?";
				parms = new Object[] { area.getAreaName(), area.getAreaCode(), area.getStatus(), area.getAreaId() };
				parms = StringHelperTools.nullToString(parms);
				// String aa = Common.logSQL(sql.toString(), parms);
				effRows = getJdbcTemplate().update(sql, parms);
			} else {
				effRows = -1;
			}

		}
		return effRows;
	}

	@Override
	public List<AreaInfoVo> GetAll() {
		return null;
	}

	@Override
	public List<AreaInfoVo> GetByPage(AreaConditionVo condition, PageVo pageVo) {
		String sql = "select * from AreaInfo where 1=1 ";
		sql += Condition2Sql(condition);
		sql += " order by AREAID";
		sql = Pager.getPageDatas(pageVo, sql);
		Object[] params = null;
		return jdbcTemplate.query(sql, params, new RowMapper<AreaInfoVo>() {

			public AreaInfoVo mapRow(ResultSet rs, int i) throws SQLException {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				vo.setEnumStatus(Integer.parseInt(vo.getStatus()));
				return vo;
			}
		});
	}

	@Override
	public int GetTotalCount(AreaConditionVo condition) {
		String sql = "select count(*) from AreaInfo where 1=1 ";
		sql += Condition2Sql(condition);
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public AreaInfoVo GetById(BigDecimal areaId) {
		String sql = "select * from AreaInfo where AreaId=?";
		List<AreaInfoVo> li = jdbcTemplate.query(sql, new Object[] { areaId }, new RowMapper<AreaInfoVo>() {

			public AreaInfoVo mapRow(ResultSet rs, int i) throws SQLException {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
		if (li != null && li.size() > 0) {
			return li.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void DeleteById(BigDecimal areaId) {
		String sql = "update areaInfo set Status=? where areaId=?";
		Object[] parms = new Object[] { Status.deleted.value(), areaId };
		parms = StringHelperTools.nullToString(parms);
		getJdbcTemplate().update(sql, parms);
	}

	/**
	 * 将条件转换为sql语句
	 * 
	 * @param condition
	 * @return
	 */
	private String Condition2Sql(AreaConditionVo condition) {
		String sql = "";
		if (condition.getParentId() != null && !condition.getParentId().equals("")) {
			sql += " and parentId='" + condition.getParentId() + "'";
		}
		if (condition.getParentId().equals("")) {
			sql += " and parentId =0";
		}
		return sql;
	}

	/**
	 * 将条件转换为sql语句 - 模糊查询
	 * 
	 * @param condition
	 * @return
	 */
	private String Condition2SqlFuzzy(AreaConditionVo condition) {
		String sql = "";
		if (condition.getAreaName() != null && !condition.getAreaName().equals("")) {
			sql += " and areaName like '%" + condition.getAreaName() + "%'";
		}
		if (condition.getAreaCode() != null && !condition.getAreaCode().equals("")) {
			sql += " and areaCode like '%" + condition.getAreaCode() + "%'";
		}
		sql += " and status=" + Status.valid.value();
		return sql;
	}

	@Override
	public List<AreaInfoVo> GetByConditionFuzzy(AreaConditionVo condition) {
		String sql = "select * from AreaInfo where 1=1 ";
		sql += Condition2SqlFuzzy(condition);
		Object[] parms = null;
		return jdbcTemplate.query(sql, parms, new RowMapper<AreaInfoVo>() {

			public AreaInfoVo mapRow(ResultSet rs, int i) throws SQLException {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
	}

	@Override
	public int RelationOrg2Area(String orgId, String areaId) {
		String sql = "select count(*) from relation_orgarea where orgId=? and areaId=?";
		int r = jdbcTemplate.queryForInt(sql, new Object[] { orgId, areaId });
		if (r <= 0) {
			sql = "insert into relation_orgarea(id,orgId,areaId) " + "values(SEQ_RELATION_ORGAREA_PK.nextval,?,?)";
			return jdbcTemplate.update(sql, new Object[] { orgId, areaId });
		}
		return -1;
	}

	/**
	 * 根據level（目前定死{leve=1:全国；level=2:省份；level=3:�?level=4:区县}）查询areaVO
	 */
	@Override
	public List<AreaInfoVo> GetByLevel(BigDecimal areaLevel) {
		String sql = "select * from AreaInfo where arealevel=?";
		return getJdbcTemplate().query(sql, new Object[] { areaLevel }, new RowMapper<AreaInfoVo>() {

			public AreaInfoVo mapRow(ResultSet rs, int i) {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
	}

	/**
	 * 根据用户管辖区域查询区域信息
	 * 
	 * @param userId
	 * @param type
	 *            sheng 省；其他： 市
	 * @return
	 */
	@Override
	public List<AreaInfoVo> GetByUserId(String userId, String type) {
		StringBuffer sql = new StringBuffer();
		if ("sheng".equals(type)) {
			sql.append(" SELECT B.* FROM AREAINFO B WHERE EXISTS (SELECT 1 FROM (SELECT B.PARENTID ");
			sql.append(" FROM RELATION_STAFFMANAGEAREA A, AREAINFO B,USERSSTAFFRELATION C ");
			sql.append(" WHERE A.STAFFID=C.STAFFID AND A.AREAID=B.AREAID AND C.USERID=?");
			sql.append(" AND B.AREALEVEL = 3 ) T WHERE T.PARENTID=B.AREAID)");
			return getJdbcTemplate().query(sql.toString(), new Object[] { userId }, new RowMapper<AreaInfoVo>() {

				public AreaInfoVo mapRow(ResultSet rs, int i) {
					AreaInfoVo vo = new AreaInfoVo();
					AutoInjection.Rs2Vo(rs, vo, null);
					return vo;
				}
			});
		} else {
			sql.append(" SELECT * FROM RELATION_STAFFMANAGEAREA A, AREAINFO B,USERSSTAFFRELATION C ");
			sql.append(" WHERE A.STAFFID=C.STAFFID AND A.AREAID=B.AREAID AND C.USERID=? AND B.AREALEVEL = 3 AND B.PARENTID=? ");
			return getJdbcTemplate().query(sql.toString(), new Object[] { userId, type }, new RowMapper<AreaInfoVo>() {

				public AreaInfoVo mapRow(ResultSet rs, int i) {
					AreaInfoVo vo = new AreaInfoVo();
					AutoInjection.Rs2Vo(rs, vo, null);
					return vo;
				}
			});
		}
	}

	/**
	 * 根据用户id，获得用户下的区域
	 * 
	 * @param userId
	 * @param type
	 * @param parentID
	 * @return
	 */

	@Override
	public List<AreaInfoVo> GetAreaListByUserId(String userId, String type, String parentID) {
		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT * FROM RELATION_STAFFMANAGEAREA A, AREAINFO B,USERSSTAFFRELATION C ");
		sql.append(" WHERE A.STAFFID=C.STAFFID AND A.AREAID=B.AREAID AND C.USERID=? AND B.AREALEVEL = ? AND B.PARENTID=? ");
		return getJdbcTemplate().query(sql.toString(), new Object[] { userId, type, parentID }, new RowMapper<AreaInfoVo>() {

			public AreaInfoVo mapRow(ResultSet rs, int i) {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});

	}

	/**
	 * 根據parentId查询所有属于该父ID的子项，用于选择当前区域ID显示其下�?
	 */
	@Override
	public List<AreaInfoVo> GetByParentId(BigDecimal parentId) {
		String sql = "select * from AreaInfo where parentId=? and areaid not in ('3586','3587','3588')";
		return getJdbcTemplate().query(sql, new Object[] { parentId }, new RowMapper<AreaInfoVo>() {

			public AreaInfoVo mapRow(ResultSet rs, int i) {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
	}

	/**
	 * @Description: 根据UserId获取管辖区域
	 * @param 参数说明
	 * @return 返回�
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author guohui
	 * @date 2013-5-15 上午11:59:38
	 * @version V1.0
	 */
	public List<AreaInfoVo> GetManageAreaByUserId(BigDecimal userId) {

		String sql = "select A.AREAID,A.AREANAME,A.PARENTID,A.AREACODE,A.STATUS,A.CREATETIME " + " from " + " USERSSTAFFRELATION B " + " inner join RELATION_STAFFMANAGEAREA C on B.STAFFID = C.STAFFID " + " inner join AreaInfo A on C.AREAID = A.AREAID " + " where B.USERID=? ";
		return getJdbcTemplate().query(sql, new Object[] { userId }, new RowMapper<AreaInfoVo>() {

			public AreaInfoVo mapRow(ResultSet rs, int i) {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
	}

	@Override
	public BigDecimal Get3AreaById(BigDecimal areaId) {
		List<BigDecimal> id = new ArrayList<BigDecimal>();
		String sql = "select areaid from areainfo where areainfo.arealevel=3 start with areaid = ? connect by prior parentid = areaid ";
		id = getJdbcTemplate().query(sql, new Object[] { areaId }, new RowMapper<BigDecimal>() {

			public BigDecimal mapRow(ResultSet rs, int i) throws SQLException {
				return rs.getBigDecimal("areaid");
			}
		});
		if (id != null)
			return id.get(0);
		else
			return null;
	}

	@Override
	public BigDecimal GetAreaByIdAndLevel(BigDecimal areaId, int level) {
		List<BigDecimal> id = new ArrayList<BigDecimal>();
		String sql = "select areaid from areainfo where areainfo.arealevel=? start with areaid = ? connect by prior parentid = areaid ";
		logger.debug(Common.logSQL(sql, StringHelperTools.nullToString(new Object[] { level, areaId })));
		id = getJdbcTemplate().query(sql, new Object[] { level, areaId }, new RowMapper<BigDecimal>() {

			public BigDecimal mapRow(ResultSet rs, int i) throws SQLException {
				return rs.getBigDecimal("areaid");
			}
		});
		if (id != null && id.size() > 0)
			return id.get(0);
		else
			return null;
	}

	/**
	 * @Description: 查询指定区域第一级及本身
	 * @param 参数说明
	 * @return 返回值
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 杨串林
	 * @date 2013-8-2 下午4:27:11
	 * @version V1.0
	 */
	public List<AreaInfoVo> queryOneLevelByParentId(BigDecimal areaId) {
		String sql = "select areaid,parentid,areaname from areainfo  where level<3 start with areaid=? connect by prior areaid=parentid";
		return getJdbcTemplate().query(sql, new Object[] { areaId }, new RowMapper<AreaInfoVo>() {

			public AreaInfoVo mapRow(ResultSet rs, int i) {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
	}

	/**
	 * @Description: 根据UserId和areaId获取管辖区域
	 * @param 参数说明
	 * @return 返回�
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author gaobing
	 * @date 2013-11-11 上午9:29:38
	 * @version V1.0
	 */
	public List<AreaInfoVo> queryManageAreaByUserIdAndAreaid(String userId, String areaId) {

		// String sql =
		// "SELECT A.AREAID,A.AREANAME,A.PARENTID,A.AREACODE,A.STATUS,A.CREATETIME,A.AREALEVEL "
		// +
		// " FROM " +
		// " USERSSTAFFRELATION B " +
		// " INNER JOIN RELATION_STAFFMANAGEAREA C ON B.STAFFID = C.STAFFID " +
		// " INNER JOIN AREAINFO A ON C.AREAID = A.AREAID " +
		// " WHERE B.USERID=? ";
		StringBuffer sb = new StringBuffer();
		String[] parms = userId.split("@");
		if ("true".equals(parms[1])) {// true 代表此用户是管理员 管理默认查询所有的菜单
			sb.append("SELECT DISTINCT T.AREAID,T.AREALEVEL,T.AREANAME,T.PARENTID,T.AREACODE,T.STATUS,T.CREATETIME ");
			sb.append(" FROM AREAINFO T  WHERE T.PARENTID=?");
			logger.debug(Common.logSQL(sb.toString(), StringHelperTools.nullToString(new Object[] { areaId })));
			return getJdbcTemplate().query(sb.toString(), new Object[] { areaId }, new RowMapper<AreaInfoVo>() {

				public AreaInfoVo mapRow(ResultSet rs, int i) {
					AreaInfoVo vo = new AreaInfoVo();
					AutoInjection.Rs2Vo(rs, vo, null);
					return vo;
				}
			});
		} else {// 非管理员，查询用户对应的员工管辖区域信息
			sb.append("SELECT DISTINCT T.AREAID,T.AREALEVEL,T.AREANAME,T.PARENTID,T.AREACODE,T.STATUS,T.CREATETIME ");
			sb.append(" FROM AREAINFO T  WHERE T.PARENTID=? START WITH T.AREAID IN (SELECT  A.AREAID");
			sb.append(" FROM USERSSTAFFRELATION B INNER JOIN RELATION_STAFFMANAGEAREA C ON B.STAFFID = C.STAFFID ");
			sb.append(" INNER JOIN AREAINFO A ON C.AREAID = A.AREAID  WHERE B.USERID=?)  ");
			sb.append(" CONNECT BY PRIOR T.PARENTID=T.AREAID ");
			logger.debug(Common.logSQL(sb.toString(), StringHelperTools.nullToString(new Object[] { areaId, parms[0] })));
			return getJdbcTemplate().query(sb.toString(), new Object[] { areaId, parms[0] }, new RowMapper<AreaInfoVo>() {

				public AreaInfoVo mapRow(ResultSet rs, int i) {
					AreaInfoVo vo = new AreaInfoVo();
					AutoInjection.Rs2Vo(rs, vo, null);
					return vo;
				}
			});

		}
	}

	/**
	 * @Description: 根据UserId获取省级管辖区域，以及所拥有的地市区县等管辖区域
	 * @param userId参数说明
	 * @return 返回�
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author gaobing
	 * @date 2013-11-11 上午9:29:38
	 * @version V1.0
	 */
	public List<AreaInfoVo> queryManageProvinceAreaByUserId(String userId) {
		String[] parms = userId.split("@");
		StringBuffer sb = new StringBuffer();

		if ("true".equals(parms[1])) {// true 代表此用户是管理员 管理默认查询所有的菜单
			sb.append("SELECT  T.AREAID,T.AREALEVEL,T.AREANAME,T.PARENTID,T.AREACODE,T.STATUS,T.CREATETIME  FROM AREAINFO T  WHERE T.AREALEVEL=2 ");
			sb.append(" UNION ALL ");
			sb.append("SELECT MAX( T.AREAID),T.AREALEVEL, MAX(T.AREANAME), MAX(T.PARENTID), MAX(T.AREACODE), MAX(T.STATUS),MAX(T.CREATETIME) FROM AREAINFO T  WHERE T.AREALEVEL NOT IN(1,2)  GROUP BY T.AREALEVEL ");

			return getJdbcTemplate().query(sb.toString(), new RowMapper<AreaInfoVo>() {

				public AreaInfoVo mapRow(ResultSet rs, int i) {
					AreaInfoVo vo = new AreaInfoVo();
					AutoInjection.Rs2Vo(rs, vo, null);
					return vo;
				}
			});

		} else {// 非管理员，查询用户对应的员工管辖区域信息
			sb.append("SELECT DISTINCT T.AREAID,T.AREALEVEL,T.AREANAME,T.PARENTID,T.AREACODE,T.STATUS,T.CREATETIME ");
			sb.append(" FROM AREAINFO T  WHERE T.AREALEVEL=2 START WITH T.AREAID IN (SELECT  A.AREAID");
			sb.append(" FROM USERSSTAFFRELATION B INNER JOIN RELATION_STAFFMANAGEAREA C ON B.STAFFID = C.STAFFID ");
			sb.append(" INNER JOIN AREAINFO A ON C.AREAID = A.AREAID  WHERE B.USERID=?)  ");
			sb.append(" CONNECT BY PRIOR T.PARENTID=T.AREAID ");
			sb.append(" UNION ALL ");
			sb.append(" SELECT MAX(DISTINCT T.AREAID),T.AREALEVEL, MAX(T.AREANAME), ");
			sb.append(" MAX(T.PARENTID), MAX(T.AREACODE), MAX(T.STATUS),MAX(T.CREATETIME) ");
			sb.append(" FROM AREAINFO T  WHERE T.AREALEVEL NOT IN(1,2) START WITH T.AREAID IN (SELECT  A.AREAID");
			sb.append(" FROM USERSSTAFFRELATION B INNER JOIN RELATION_STAFFMANAGEAREA C ON B.STAFFID = C.STAFFID ");
			sb.append(" INNER JOIN AREAINFO A ON C.AREAID = A.AREAID  WHERE B.USERID=? AND A.AREALEVEL NOT IN(1,2))  ");
			sb.append(" CONNECT BY PRIOR T.PARENTID=T.AREAID  GROUP BY T.AREALEVEL  ");
			logger.debug(Common.logSQL(sb.toString(), StringHelperTools.nullToString(new Object[] { parms[0], parms[0] })));

			return getJdbcTemplate().query(sb.toString(), new Object[] { parms[0], parms[0] }, new RowMapper<AreaInfoVo>() {

				public AreaInfoVo mapRow(ResultSet rs, int i) {
					AreaInfoVo vo = new AreaInfoVo();
					AutoInjection.Rs2Vo(rs, vo, null);
					return vo;
				}
			});
		}

	}

	/**
	 * @Description: 根据UserId获取管辖区域的父级区域
	 * @param userId参数说明
	 * @return 返回�
	 * @exception 异常描述
	 * @see 需要参见的其它内容。（可选）
	 * @author 王震
	 * @date 2013-11-11 上午9:29:38
	 * @version V1.0
	 */
	@Override
	public AreaInfoVo GetParentAreaByUserId(BigDecimal userId) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT rsf.areaid,ar.areaname");
		sb.append(" FROM USERS U");
		sb.append(" INNER JOIN USERSSTAFFRELATION US");
		sb.append(" ON U.USERID = US.USERID");
		sb.append(" INNER JOIN userstaffinfo uf");
		sb.append(" on us.staffid = uf.staffid");
		sb.append(" INNER JOIN relation_staffmanagearea rsf");
		sb.append(" on uf.staffid = rsf.staffid");
		sb.append(" INNER JOIN  areainfo ar on rsf.areaid= ar.areaid");
		sb.append(" AND ar.arealevel in('1','2','3')");
		sb.append(" AND ar.parentid in('0','2','100000')");
		sb.append(" WHERE U.USERID = ?");
		sb.append(" ORDER BY ar.areaid");
		Object[] params = new Object[] { userId };
		logger.debug("根据用户获得用户所在区域: " + Common.logSQL(sb.toString(), params));
		List<AreaInfoVo> list = getJdbcTemplate().query(sb.toString(), new Object[] { userId }, new RowMapper<AreaInfoVo>() {

			public AreaInfoVo mapRow(ResultSet rs, int i) {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});

		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * @Description: 根据userId 查询产品政策区域
	 * @param userId
	 *            用户Id
	 * @return
	 * @author liugeng
	 * @date 2013-12-20
	 */
	@Override
	public List<AreaInfoVo> queryProductPricePoliceScope(String userId, String areaLevel) {
		String sql = "select A.AREALEVEL,A.AREAID,A.AREANAME,A.PARENTID,A.AREACODE,A.STATUS,A.CREATETIME " + " from " + " USERSSTAFFRELATION B " + " inner join RELATION_STAFFMANAGEAREA C on B.STAFFID = C.STAFFID " + " inner join AreaInfo A on C.AREAID = A.AREAID " + " where B.USERID=? and A.AREALEVEL= ? ";
		return getJdbcTemplate().query(sql, new Object[] { userId, areaLevel }, new RowMapper<AreaInfoVo>() {

			public AreaInfoVo mapRow(ResultSet rs, int i) {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
	}

	/**
	 * @Description: 查询产品政策区域 省份
	 * @return
	 * @author liugeng
	 * @date 2013-12-20
	 */
	@Override
	public List<AreaInfoVo> queryProductPricePoliceByAreaLevle(String areaLevel) {
		String sql = "select A.AREALEVEL,A.AREAID,A.AREANAME,A.PARENTID,A.AREACODE,A.STATUS,A.CREATETIME " + " from AreaInfo A " + " where A.AREALEVEL= ? ";
		return getJdbcTemplate().query(sql, new Object[] { areaLevel }, new RowMapper<AreaInfoVo>() {

			public AreaInfoVo mapRow(ResultSet rs, int i) {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
	}

	/**
	 * @Description: 根据AreaId和等级 查询产品政策区域
	 * @param areaId
	 * @param areaLevel
	 * @return
	 * @author liugeng
	 * @date 2013-12-20
	 */
	@Override
	public List<AreaInfoVo> queryProductPricePoliceByIdAndLevle(BigDecimal areaId, String areaLevel) {
		String sql = "select A.AREALEVEL,A.AREAID,A.AREANAME,A.PARENTID,A.AREACODE,A.STATUS,A.CREATETIME " + " from AreaInfo A " + " where A.AREALEVEL= ? " + " start with A.AREAID = ? connect by prior A.PARENTID =A.AREAID ";
		return getJdbcTemplate().query(sql, new Object[] { areaLevel, areaId }, new RowMapper<AreaInfoVo>() {

			public AreaInfoVo mapRow(ResultSet rs, int i) {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
	}

	@Override
	public AreaInfoVo GetAreaBysupplyUserId(BigDecimal userId) {
		String sql = " select af.areaname, af.areaid, af.areaLevel " + " from supply su " + " inner join supply_area sa on sa.supply_id = su.id and su.user_id = ? and sa.DEL_FLAG='1' " + " left  join areainfo af on af.areaid = sa.area_id ";

		Object[] params = new Object[] { userId };
		logger.debug("根据供应商id获得用户所在区域: " + Common.logSQL(sql, params));
		List<AreaInfoVo> list = getJdbcTemplate().query(sql, new Object[] { userId }, new RowMapper<AreaInfoVo>() {

			public AreaInfoVo mapRow(ResultSet rs, int i) throws SQLException {
				AreaInfoVo vo = new AreaInfoVo();
				vo.setAreaName(rs.getString("areaname"));
				vo.setAreaId(rs.getBigDecimal("areaid"));
				vo.setAreaLevel(rs.getBigDecimal("areaLevel"));
				return vo;
			}
		});

		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * 获取所有省和直辖市区域信息
	 * 
	 * @Description:
	 * @see com.Areas.Dao.IAreaInfoDao#getProvinceInfo()
	 * @return
	 * @author xingle
	 * @date 2014-4-10 上午10:58:25
	 */
	public List<AreaInfoVo> getProvinceInfo() {
		String sql = " select ai.areaid,ai.areaname,ai.parentid,ai.areacode,ai.status,ai.createtime,ai.arealevel " + "from areainfo ai where (ai.arealevel = 2 and ai.areaid <> 100000) or (ai.arealevel = 3 and ai.parentid = 100000) " + "order by ai.areaid ";
		List<AreaInfoVo> ls = jdbcTemplate.query(sql, new RowMapper<AreaInfoVo>() {

			@Override
			public AreaInfoVo mapRow(ResultSet rs, int i) throws SQLException {
				AreaInfoVo vo = new AreaInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}

		});
		return ls;
	}
}
