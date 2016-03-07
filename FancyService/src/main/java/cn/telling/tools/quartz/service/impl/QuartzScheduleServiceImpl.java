package cn.telling.tools.quartz.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.telling.tools.quartz.dao.IQuartzScheduleDao;
import cn.telling.tools.quartz.service.IQuartzScheduleService;
import cn.telling.tools.quartz.vo.QuartzVo;


@Service("quartzService")
public class QuartzScheduleServiceImpl implements IQuartzScheduleService{
	
	/*
	 public void execute(JobExecutionContext context) throws JobExecutionException {   
		simpleJobTest();   
	 }
	*/
	@Autowired
	@Qualifier("QuartzScheduleDao")
	private IQuartzScheduleDao quartzScheduleDao;
	
	public IQuartzScheduleDao getQuartzScheduleDao() {
		return quartzScheduleDao;
	}
	public void setQuartzScheduleDao(IQuartzScheduleDao quartzScheduleDao) {
		this.quartzScheduleDao = quartzScheduleDao;
	}
	
	//	@Scheduled(cron="0/10 * * * * ? ")
	@Override
	public int addSchedule(QuartzVo vo) {
		return quartzScheduleDao.addSchedule(vo);
	}
	@Override
	public int editSchedule(QuartzVo vo) {
		return quartzScheduleDao.editSchedule(vo);
	}
	@Override
	public int deleteSchedule(QuartzVo vo) {
		return quartzScheduleDao.deleteSchedule(vo);
	}
	@Override
	public List<QuartzVo> getAllJob() {
		List<QuartzVo> quartzList = quartzScheduleDao.getAllJob();
		return quartzList;
	}
	@Override
	public Boolean checkName(String checkName) {
		return quartzScheduleDao.checkName(checkName);
	}
	
}
