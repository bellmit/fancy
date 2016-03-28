package cn.telling.tools.quartz.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.telling.tools.quartz.dao.IQuartzScheduleDao;
import cn.telling.tools.quartz.vo.QuartzVo;
import cn.telling.utils.AutoInjection;


@Repository("QuartzScheduleDao")
public class QuartzScheduleDaoImpl implements IQuartzScheduleDao {
	private static Logger logger = Logger.getLogger(QuartzScheduleDaoImpl.class);  
	
	@Autowired
	@Qualifier("film-template")
	private JdbcTemplate jdbcOracleB2BTemplate;

	public JdbcTemplate GetjdbcOracleB2BTemplate() {
		return jdbcOracleB2BTemplate;
	}
	public void SetJdbcOracleB2BTemplat(JdbcTemplate jdbcOracleB2BTemplate) {
		this.jdbcOracleB2BTemplate = jdbcOracleB2BTemplate;
	}
	
	
	@Override
	public int addSchedule(QuartzVo vo) {
		String sql = "insert into quartzList(id,jobtitle,jcallpath, jobcron ) values(seq_quartzList_key.NEXTVAL,'"+vo.getJobTitle()+"','"+vo.getJcallpath()+"','"+vo.getJobcron()+"')";
		int num = 0;
		if(null!=vo ){
			 jdbcOracleB2BTemplate.execute(sql);
			 num = 1 ; 	
		}
		
		return num;
	}

	@Override
	public int editSchedule(QuartzVo vo) {
		String sql = "update  quartzList set jobtitle='"+vo.getJobTitle()+"',and set =jcallpath'"+vo.getJcallpath()+"',set jobcron='"+vo.getJobcron()+"'where id="+vo.getId()+")";
		int num = 0;
		if(null!=vo ){
			 GetjdbcOracleB2BTemplate().execute(sql);
			 num = 1 ;   	
		}
		return num;
	}

	@Override
	public int deleteSchedule(QuartzVo vo) {
		String sql = "delete from  quartzList where id="+vo.getId()+"";
		int num = 0;
		if(null!=vo ){
			 GetjdbcOracleB2BTemplate().execute(sql);
			 num = 1 ;   	
		}
		return num;
	}
	@Override
	public List<QuartzVo> getAllJob() {
		String sql = "select id,jobtitle,jcallpath, jobcron from  quartzList ";
		return GetjdbcOracleB2BTemplate().query(sql, new RowMapper<QuartzVo>() {
			public QuartzVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				QuartzVo vo = new QuartzVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		});
	}
	@Override
	public Boolean checkName(String jobName) {
		String sql = " select count(*) from quartzlist ql where jobtitle='"+jobName+"' ";
		logger.debug("检查任务是否存在:"+sql);
		int a = jdbcOracleB2BTemplate.queryForInt(sql);
		if(a>0){
			return true;
		}else{
			return false;
		}
	}
}
