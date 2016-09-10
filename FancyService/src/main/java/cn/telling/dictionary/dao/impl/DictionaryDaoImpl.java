package cn.telling.dictionary.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.telling.common.Pager.PageVo;
import cn.telling.common.Pager.Pager;
import cn.telling.dictionary.dao.DictionaryDao;
import cn.telling.dictionary.vo.DictionaryConditionVo;
import cn.telling.dictionary.vo.DictionaryTypeVo;
import cn.telling.dictionary.vo.DictionaryVo;
import cn.telling.dictionary.vo.SelectVo;
import cn.telling.utils.AutoInjection;
import cn.telling.utils.BaseUtil;
@Repository("DictionaryDao")
public class DictionaryDaoImpl implements DictionaryDao{
	private static Logger logger = Logger.getLogger(DictionaryDaoImpl.class);
	@Autowired
	@Qualifier("film-template")
	private JdbcTemplate jdbcTemplate;
	@SuppressWarnings("deprecation")
  @Override
	public int getDictionaryTypeTotalCountForPage(String type) {
		String sql="select count(1) from dictionarytype dt where del_flag='1' ";
		if(type!=null&&type.length()>0){
			sql+=" and  dt.dictionarytypetext like '%"+type+"%'";
		}
		sql+=" order by dt.dictionarytypevalue asc";
		return jdbcTemplate.queryForInt(sql);
	}
	@Override
	public List<DictionaryTypeVo> getDictionaryTypeByPage(PageVo pageVo,String type){
		String sql="select dt.dictionarytypevalue , dt.dictionarytypetext from dictionarytype dt where del_flag='1' ";
		if(type!=null&&type.length()>0){
			sql+=" and  dt.dictionarytypetext like '%"+type+"%'";
		}
		sql+=" order by dt.dictionarytypevalue asc";
		String exe=Pager.getPageDatas(pageVo, sql);
		return jdbcTemplate.query(exe, new RowMapper<DictionaryTypeVo>(){

			@Override
			public DictionaryTypeVo mapRow(ResultSet rs, int arg1)
					throws SQLException {
				DictionaryTypeVo dt=new DictionaryTypeVo();
				AutoInjection.Rs2Vo(rs, dt, null);
				return dt;
			}
			
		});
	}
	@Override
	public List<DictionaryTypeVo> getDictionaryType(String id){
		String sql="select dt.dictionarytypevalue , dt.dictionarytypetext from dictionarytype dt where del_flag='1' ";
		if(id!=null&&!"".equals(id)){
			sql+=" and dt.dictionarytypevalue='"+id+"'";
		}
		sql+=" order by dt.dictionarytypevalue asc";
		return jdbcTemplate.query(sql, new RowMapper<DictionaryTypeVo>(){

			@Override
			public DictionaryTypeVo mapRow(ResultSet rs, int arg1)
					throws SQLException {
				DictionaryTypeVo dt=new DictionaryTypeVo();
				AutoInjection.Rs2Vo(rs, dt, null);
				return dt;
			}
			
		});
	}
	@SuppressWarnings("deprecation")
  public int getDictionaryTypeTotalCount(){
		String sql="select count(1) from dictionarytype";
		return jdbcTemplate.queryForInt(sql);
	}
	@Override
	public int addDictionaryType(DictionaryTypeVo vo,BigDecimal userid) {
		String sql="insert into dictionarytype (dictionarytypevalue,dictionarytypetext,del_flag,addtime,adduser) values (?, ?, '1', sysdate,?)";
		return jdbcTemplate.update(sql, vo.getDictionaryTypeValue(),vo.getDictionaryTypeText(),userid);
	}
	@Override
	public int deleteDictionaryType(String typeValue,BigDecimal userid) {
		String sql="update dictionarytype set del_flag='0',modifyuser=?,modifytime=sysdate where dictionarytypevalue=?";
		return jdbcTemplate.update(sql,userid,typeValue);
	}
	@Override
	public int updateDictionaryType(DictionaryTypeVo vo,BigDecimal userid) {
		String sql="update dictionarytype set dictionarytypetext=?,modifyuser=?,modifytime=sysdate where dictionarytypevalue=?";
		return jdbcTemplate.update(sql,vo.getDictionaryTypeText(),userid,vo.getDictionaryTypeValue());
	}
	@Override
	public List<DictionaryVo> getDictionaryByPage(PageVo pageVo,
			DictionaryConditionVo vo) {
		String sql="select * from dictionary d where delflag='1' ";
		List<String> para=new ArrayList<String>();
		if(vo.getDictionaryType()!=null&&!"".equals(vo.getDictionaryType())){
			sql+=" and d.dictionarytypevalue like ?";
			para.add("%"+vo.getDictionaryType()+"%");
		}
		if(vo.getDictionaryValue()!=null&&!"".equals(vo.getDictionaryValue())){
			sql+=" and d.dictionaryvalue like ?";
			para.add("%"+vo.getDictionaryValue()+"%");
		}
		sql+=" order by d.dictionarykey asc";
		if(pageVo!=null){
			sql = Pager.getPageDatas(pageVo, sql);
		}
		return jdbcTemplate.query(sql,para.toArray(),new RowMapper<DictionaryVo>(){

			@Override
			public DictionaryVo mapRow(ResultSet rs, int arg1)
					throws SQLException {
				DictionaryVo obj=new DictionaryVo();
				AutoInjection.Rs2Vo(rs, obj, null);
				return obj;
			}
			
		});
	}
	@Override
	public List<SelectVo> getDictionaryByType(
			String type) {
		String sql="select dictionaryvalue as key,describtion  as value from dictionary d where delflag='1' and dictionarytypevalue='"+type+"'";
		
		return jdbcTemplate.query(sql,new RowMapper<SelectVo>(){
			
			@Override
			public SelectVo mapRow(ResultSet rs, int arg1)
			throws SQLException {
				SelectVo obj=new SelectVo();
				AutoInjection.Rs2Vo(rs, obj, null);
				return obj;
			}
			
		});
	}
	@SuppressWarnings("deprecation")
  @Override
	public int getDicitonaryTotalCountForPage(DictionaryConditionVo vo) {
		String sql="select count(*) from dictionary d where delflag='1' ";
		List<String> para=new ArrayList<String>();
		if(vo.getDictionaryType()!=null&&!"".equals(vo.getDictionaryType())){
			sql+=" and d.dictionarytypevalue like ?";
			para.add("%"+vo.getDictionaryType()+"%");
		}
		if(vo.getDictionaryValue()!=null&&!"".equals(vo.getDictionaryValue())){
			sql+=" and d.dictionaryvalue like ?";
			para.add("%"+vo.getDictionaryValue()+"%");
		}
		return jdbcTemplate.queryForInt(sql,para.toArray());
	}
	@Override
	public int insertDictionary(DictionaryVo vo) {
		String sql="insert into dictionary (dictionaryid,dictionarytypevalue,dictionarykey,dictionaryvalue,describtion,addtime,adduser,delflag,dictionarytypetext) values (? ,? ,? ,?, ? ,sysdate,? ,'1',?)";
		
		
		Object[] params = {
				vo.getDictionaryId(),
				vo.getDictionaryTypeValue(),
				vo.getDictionaryKey(),
				vo.getDictionaryValue(),
				vo.getDescribtion(),vo.getAdd_user(),
				vo.getDictionaryTypeText()
				};
		logger.debug("sssssss:"+BaseUtil.logSQL(sql.toString(),params));
		return jdbcTemplate.update(sql, params);
	}
	@Override
	public int deleteDictionary(String id) {
		String sql="update dictionary set delflag='0' where dictionaryid=?";
		return jdbcTemplate.update(sql,id);
	}
	@SuppressWarnings("deprecation")
  public int getDictionaryTotalCount(){
		String sql="select  SEQ_DICTIONARY_PK.nextval  from dual";
		return jdbcTemplate.queryForInt(sql);
	}
	@Override
	public DictionaryVo getDictionaryById(String id){
		String sql="select * from dictionary d where d.dictionaryid=? and delflag='1'";
		List<DictionaryVo> list=jdbcTemplate.query(sql,new Object[]{id}, new RowMapper<DictionaryVo>(){

			@Override
			public DictionaryVo mapRow(ResultSet rs, int arg1)
					throws SQLException {
				DictionaryVo vo=new DictionaryVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
			
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public boolean queryDictionaryType(String total) {
		String sql="SELECT * FROM DICTIONARYTYPE D WHERE D.DICTIONARYTYPEVALUE=? AND D.DEL_FLAG='1'";
		Object[] args = new Object[]{total};
		List<DictionaryTypeVo> list = this.jdbcTemplate.query(sql, args, new RowMapper<DictionaryTypeVo>(){
			@Override
			public DictionaryTypeVo mapRow(ResultSet rs, int i)
					throws SQLException {
				DictionaryTypeVo vo = new DictionaryTypeVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
		if(list.size()!=0){
			return true;
		}
		return false;
	}
	@Override
	public boolean queryDictionaryIsAble(String dictionaryTypeKey,
			String dictionaryValue) {
		String sql="SELECT * FROM DICTIONARY D WHERE D.DICTIONARYTYPEVALUE = ? AND D.DICTIONARYVALUE = ?";
		Object[] args = new Object[]{dictionaryTypeKey, dictionaryValue};
		List<DictionaryVo> list = this.jdbcTemplate.query(sql, args, new RowMapper<DictionaryVo>(){
			@Override
			public DictionaryVo mapRow(ResultSet rs, int i)
					throws SQLException {
				DictionaryVo vo = new DictionaryVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
		if(list.size()!=0){
			return true;
		}
		return false;
	}
}
