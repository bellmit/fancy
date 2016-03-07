package cn.telling.tools.quartz.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.telling.tools.quartz.service.IQuartzScheduleService;
import cn.telling.tools.quartz.vo.QuartzVo;

/**   
 * @Title: CallQuartz.java 
 * @Package com.tools.quartz.util 
 * @Description: TODO(描述该文件做什么) 
 * @author 李 欢   
 * @date 2013-6-6 下午3:45:34 
 * @version V1.0   
 */

public class CallQuartz {
	private static Logger logger = Logger.getLogger(CallQuartz.class);
	@Autowired 
	@Qualifier("quartzService")
	private IQuartzScheduleService quartzScheduleService;


	public IQuartzScheduleService getQuartzScheduleService() {
		return quartzScheduleService;
	}

	public void setQuartzScheduleService(
			IQuartzScheduleService quartzScheduleService) {
		this.quartzScheduleService = quartzScheduleService;
	}
	
	/**
	 * 
	 * @Description: TODO 描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      李 欢
	 * @date 2013-6-6 下午3:47:21 
	 * @version V1.0
	 */
	public void callAllQuartz(){
		List<QuartzVo> list =this.getNewJobs();
		logger.info("定时任务数量： "+list.size());
		for(int i = 0 ;i<list.size();i++){
			QuartzVo vo =(QuartzVo)list.get(i);
		//	UrlJob job = new UrlJob(vo);
			try {
				QuartzManager.addJob(vo.getJobTitle(), vo, vo.getJobcron());
				
			} catch (SchedulerException e) {
				logger.error(e.getCause());
				e.printStackTrace();
			} catch (ParseException e) {
				logger.error(e.getCause());
				e.printStackTrace();
			}finally{
				//放入缓存，共后续使用
			//	this.getMemClient().add("TY_QUARTZ_JOB_"+vo.getJobTitle(),vo);
				QuartzVo.quartzMap.put("TY_QUARTZ_JOB_"+vo.getJobTitle(),vo);
			}
		}
	}
	/**
	 * 
	 * @Description: 用于返回新的定时任务
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      李 欢
	 * @date 2013-6-6 下午7:44:23 
	 * @version V1.0
	 */
	private List<QuartzVo>  getNewJobs(){
		List<QuartzVo> returnList=new ArrayList<QuartzVo>();
		//从Memcached获得全部的定时任务
	//	List<QuartzVo> list=(List<QuartzVo>)this.getMemClient().get("TY_QUARTZ_JOB_ALL_JOBS");
		List<QuartzVo> list=(List<QuartzVo>)QuartzVo.newQuartzList;
		if(list==null || list.isEmpty()){//如果缓存中list为空，则进行新增操作
			returnList = quartzScheduleService.getAllJob();
			if(returnList!=null && returnList.size()>0){
			//	this.getMemClient().add("TY_QUARTZ_JOB_ALL_JOBS", returnList);
				QuartzVo.newQuartzList=returnList;
			}
		}else{//如果不为空，则进行更新操作，将新的任务拿出来，放到调度执行计划中
			List<QuartzVo> tempList = quartzScheduleService.getAllJob();
			List<QuartzVo> tobeRemoveList=new ArrayList<QuartzVo>();
			for(int i=0;i<tempList.size();i++){
				QuartzVo dbJobs=(QuartzVo)tempList.get(i);
				for(int j=0;j<list.size();j++){
					QuartzVo memJobs=(QuartzVo)list.get(j);
					//数据库中任务名称
					String dbJobsName=dbJobs.getJobTitle();
					//缓存中任务名称
					String memJobsName=memJobs.getJobTitle();
					if(dbJobsName.equals(memJobsName)){
						tobeRemoveList.add(dbJobs);
					}
				}
			}
			
			boolean isNew=tempList.removeAll(tobeRemoveList);
			if(isNew){
			 returnList=tempList;
			//如果有新的任务，则进行缓存更新
			if(returnList!=null && returnList.size()>0){
			//	this.getMemClient().replace("TY_QUARTZ_JOB_ALL_JOBS", tempList);
				QuartzVo.newQuartzList.addAll(tempList);
				}
			}
		}
		return returnList;
	}
}
