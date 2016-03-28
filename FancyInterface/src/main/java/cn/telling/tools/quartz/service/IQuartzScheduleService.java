package cn.telling.tools.quartz.service;

import java.util.List;

import cn.telling.tools.quartz.vo.QuartzVo;



public interface IQuartzScheduleService{
	
	
	public int addSchedule(QuartzVo vo);
	public int editSchedule(QuartzVo vo);
	public int deleteSchedule(QuartzVo vo);
	public List<QuartzVo> getAllJob();
	
	public Boolean checkName(String checkName);
}
