package cn.telling.tools.quartz.util;
/**   
 * @Title: QuartzManager.java 
 * @Package com.tools.quartz.util 
 * @Description: TODO(描述该文件做什么) 
 * @author 崔大鹏   
 * @date 2013-4-19 下午4:16:33 
 * @version V1.0   
 */
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.telling.tools.quartz.vo.QuartzVo;

public class QuartzManager {
		private static Logger logger = Logger.getLogger(QuartzManager.class);
		// 缓存对象
		@Autowired
		@Qualifier("memcachedClient")
	   private static SchedulerFactory sf = new StdSchedulerFactory();
	   private static String JOB_GROUP_NAME = "tellingGroup";
	   private static String TRIGGER_GROUP_NAME = "tellimgTrigger";
	   

	/** *//**
	    *  添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	    * @param jobName 任务名
	    * @param job     任务
	    * @param time    时间设置，参考quartz说明文档
	    * @throws SchedulerException
	    * @throws ParseException
	    */
	   public static void addJob(String jobName,QuartzVo job,String time)  throws SchedulerException, ParseException{
		   logger.debug("***********************天音定时任务【"+jobName+"】 开始加载！*****************************");
	       Scheduler sched = sf.getScheduler();
	     //任务名，任务组，任务执行类
	       JobDetail jobDetail = new JobDetail(jobName, jobName, job.getClass());
	       //触发器  触发器名,触发器组
	       CronTrigger  trigger =  new CronTrigger(jobName, jobName);
	     //触发器时间设定
	       trigger.setCronExpression(time);
	       sched.scheduleJob(jobDetail,trigger);
	       //启动
	       if(!sched.isShutdown()){
	          sched.start();
	       }
	          logger.debug("***********************天音定时任务【"+jobName+"】 加载完成！*****************************");
	          //加入缓存中
	          
	   }
	   /** *//**
	    * 添加一个定时任务
	    * @param jobName 任务名
	    * @param jobGroupName 任务组名
	    * @param triggerName  触发器名
	    * @param triggerGroupName 触发器组名
	    * @param job     任务
	    * @param time    时间设置，参考quartz说明文档
	    * @throws SchedulerException
	    * @throws ParseException
	    */
	   public static void addJob(String jobName,String jobGroupName,
	                             String triggerName,String triggerGroupName,
	                             Job job,String time) 
	                               throws SchedulerException, ParseException{
	       Scheduler sched = sf.getScheduler();
	       JobDetail jobDetail = new JobDetail(jobName, jobGroupName, job.getClass());//任务名，任务组，任务执行类
	       //触发器
	       CronTrigger  trigger =  new CronTrigger(triggerName, triggerGroupName);//触发器名,触发器组
	       trigger.setCronExpression(time);//触发器时间设定
	       sched.scheduleJob(jobDetail,trigger);
	       if(!sched.isShutdown())
	          sched.start();
	   }
	   /** *//**
	    * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	    * @param jobName
	    * @param time
	    * @throws SchedulerException
	    * @throws ParseException
	    */
	   public static void modifyJobTime(String jobName,String time) 
	                                  throws SchedulerException, ParseException{
	       Scheduler sched = sf.getScheduler();
	       Trigger trigger =  sched.getTrigger(jobName,TRIGGER_GROUP_NAME);
	       if(trigger != null){
	           CronTrigger  ct = (CronTrigger)trigger;
	           ct.setCronExpression(time);
	           sched.resumeTrigger(jobName,TRIGGER_GROUP_NAME);
	       }
	   }
	   /** *//**
	    * 修改一个任务的触发时间
	    * @param triggerName
	    * @param triggerGroupName
	    * @param time
	    * @throws SchedulerException
	    * @throws ParseException
	    */
	   public static void modifyJobTime(String triggerName,String triggerGroupName,
	                                    String time) 
	                                  throws SchedulerException, ParseException{
	       Scheduler sched = sf.getScheduler();
	       Trigger trigger =  sched.getTrigger(triggerName,triggerGroupName);
	       if(trigger != null){
	           CronTrigger  ct = (CronTrigger)trigger;
	           //修改时间
	           ct.setCronExpression(time);
	           //重启触发器
	           sched.resumeTrigger(triggerName,triggerGroupName);
	       }
	   }
	   /** *//**
	    * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	    * @param jobName
	    * @throws SchedulerException
	    */
	   public static void removeJob(String jobName) 
	                               throws SchedulerException{
	       Scheduler sched = sf.getScheduler();
	       sched.pauseTrigger(jobName,TRIGGER_GROUP_NAME);//停止触发器
	       sched.unscheduleJob(jobName,TRIGGER_GROUP_NAME);//移除触发器
	       sched.deleteJob(jobName,JOB_GROUP_NAME);//删除任务
	   }
	   /** *//**
	    * 移除一个任务
	    * @param jobName
	    * @param jobGroupName
	    * @param triggerName
	    * @param triggerGroupName
	    * @throws SchedulerException
	    */
	   public static void removeJob(String jobName,String jobGroupName,
	                                String triggerName,String triggerGroupName) 
	                               throws SchedulerException{
	       Scheduler sched = sf.getScheduler();
	       sched.pauseTrigger(triggerName,triggerGroupName);//停止触发器
	       sched.unscheduleJob(triggerName,triggerGroupName);//移除触发器
	       sched.deleteJob(jobName,jobGroupName);//删除任务
	   }
	}
