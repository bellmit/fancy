package cn.telling.action.quartz;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.telling.tools.quartz.service.IQuartzScheduleService;
import cn.telling.tools.quartz.util.CronExpressionEx;
import cn.telling.tools.quartz.util.DateFormatUtil;
import cn.telling.tools.quartz.util.QuartzManager;
import cn.telling.tools.quartz.vo.QuartzVo;



@Controller
@RequestMapping("/quartz")
public class QuartzScheduleAction{
	private static Logger logger = Logger.getLogger(QuartzScheduleAction.class);
	
	@Resource
	private IQuartzScheduleService quartzScheduleService;
	
	
	public IQuartzScheduleService getQuartzScheduleService() {
		return quartzScheduleService;
	}

	public void setQuartzScheduleService(
			IQuartzScheduleService quartzScheduleService) {
		this.quartzScheduleService = quartzScheduleService;
	}

	@RequestMapping(value="/index",method={RequestMethod.GET,RequestMethod.POST})
	public String index(ModelMap map,HttpServletRequest request) { 
		logger.info("设置定时调度信息");
		return "quartz/index";
	} 
	
	@RequestMapping(value="/schedule",method={RequestMethod.GET,RequestMethod.POST})
	public String input(ModelMap map,HttpServletRequest request) { 
		logger.info("========提交定时调度信息成功=======");
		String jobName =  (String)request.getParameter("jobName");
		String callPath = "http://"+(String)request.getParameter("callPath");
		String cron = (String) request.getParameter("cron");
		logger.info("===============jobName:"+jobName+"===============");
		logger.info("===============callPath:"+callPath+"===============");
		logger.info("===============cron:"+cron+"===============");

		String result = "";
		QuartzVo vo = new QuartzVo();
		vo.setJobTitle(jobName);
		vo.setJcallpath(callPath);
		vo.setJobcron(cron);
		quartzScheduleService.addSchedule(vo);
		result = "redirect:/quartz/index.html";
		return result;
	} 

	@RequestMapping(value="/checkName",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ModelMap  checkName(ModelMap map,HttpServletRequest request,HttpServletResponse response) 
					throws Exception, SchedulerException, ParseException {
		String checkName = (String)request.getParameter("checkName");
		boolean check = false;
		
		check =quartzScheduleService.checkName(checkName);
		if(check==true){
			map.put("result","{result:1}");
		}else{
			map.put("result","{result:0}");
		}
		return map;
	}
	
	
	@RequestMapping(value="/delJob",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ModelMap  delJob(ModelMap map,HttpServletRequest request,HttpServletResponse response) 
					throws Exception, SchedulerException, ParseException {
		String id = (String)request.getParameter("id");
		boolean flag = false;
		QuartzVo vo = new QuartzVo();
		vo.setId(new BigDecimal(id));
		int num = 0;
		
		num =quartzScheduleService.deleteSchedule(vo);
		if(num>0){
			flag = true;
		}
		map.put("flag", flag);
		return map;
	}
	
	
	@RequestMapping(value="/getAllJob",method={RequestMethod.GET,RequestMethod.POST})
	public String getAllJob(ModelMap map,HttpServletRequest request,HttpServletResponse response) 
					throws Exception, SchedulerException, ParseException {
		List<QuartzVo> list = quartzScheduleService.getAllJob();
		map.put("list", list);
		return "quartz/quartzList";
	}
	
	@RequestMapping(value="/example",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ModelMap jobExample(ModelMap map,HttpServletRequest request,HttpServletResponse response){
		logger.info("print time:"+new java.util.Date());
		String cronStr = (String)request.getParameter("cron");
		
		String result = "";
		try{
			CronExpressionEx exp = new CronExpressionEx(cronStr);
	        StringBuffer cronResult = new StringBuffer();
	        java.util.Date dd = new java.util.Date();
	        for (int i = 1; i <= 8; i++) {
	          dd = exp.getNextValidTimeAfter(dd);
	          cronResult.append(i + ": " + DateFormatUtil.format("yyyy-MM-dd HH:mm:ss", dd) + "<br/>");
	          dd = new java.util.Date(dd.getTime() + 1000);
	        }
	        result= cronResult.toString();
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		map.put("result", result);
		return map;			
	}
	
	@RequestMapping(value="/test",method={RequestMethod.GET,RequestMethod.POST})
	public String jobTest(ModelMap map,HttpServletRequest request,HttpServletResponse response){
		logger.info("天王盖地虎，小鸡炖蘑菇!"+new java.util.Date());
		
		return "common/pubAddSuccess";			
	}
	
	@RequestMapping(value="/callAll",method={RequestMethod.GET,RequestMethod.POST})
	public void callJobs(ModelMap map,HttpServletRequest request,HttpServletResponse response){
		logger.info("天王盖地虎，小鸡炖蘑菇!"+new java.util.Date());
		
		List<QuartzVo> list = quartzScheduleService.getAllJob();
		logger.info("list size:"+list.size());
		for(int i = 0 ;i<list.size();i++){
			QuartzVo vo =(QuartzVo)list.get(i);
			//UrlJob job = new UrlJob(vo);
			try {
				QuartzManager.addJob(vo.getJobTitle(), vo, vo.getJobcron());
			} catch (SchedulerException e) {
				logger.error(e.getCause());
				e.printStackTrace();
			} catch (ParseException e) {
				logger.error(e.getCause());
				e.printStackTrace();
			}
		}
	}
} 


