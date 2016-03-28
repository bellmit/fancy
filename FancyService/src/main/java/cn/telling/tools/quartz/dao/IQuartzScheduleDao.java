package cn.telling.tools.quartz.dao;

import java.util.List;

import cn.telling.tools.quartz.vo.QuartzVo;

public interface IQuartzScheduleDao {
	public int addSchedule(QuartzVo vo);
	public int editSchedule(QuartzVo vo);
	public int deleteSchedule(QuartzVo vo);
	public List<QuartzVo> getAllJob();
	
	public Boolean checkName(String jobName);
}
