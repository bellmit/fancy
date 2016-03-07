package cn.telling.tools.quartz.vo;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**   
 * @Title: QuartzVo.java 
 * @Package com.tools.quartz.vo 
 * @Description: 定时调度数据对象
 * @author 崔大鹏   
 * @date 2013-4-9 上午9:40:11 
 * @version V1.0   
 */
public class QuartzVo  implements Job,Serializable{
	/**
	 * 序列 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(QuartzVo.class);
	//每次新增的任务集合
	public static List<QuartzVo> newQuartzList=new ArrayList<QuartzVo>();
	//存放已经有效的集合
	public static Map<String,QuartzVo> quartzMap=new HashMap<String,QuartzVo>();
	
	/**
	 * id
	 */
	public BigDecimal id ;
	/**
	 * 任务名称
	 */
	public String jobTitle;
	/**
	 * 调度路径
	 */
	public String  jcallpath;
	/**
	 * 触发表达式
	 */
	public String jobcron;




	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJcallpath() {
		return jcallpath;
	}

	public void setJcallpath(String jcallpath) {
		this.jcallpath = jcallpath;
	}

	public String getJobcron() {
		return jobcron;
	}

	public void setJobcron(String jobcron) {
		this.jobcron = jobcron;
	}
/**
 * 
 * @Description: 需要调度的方法
 * @param		参数说明
 * @return		返回值
 * @exception   异常描述
 * @see		          需要参见的其它内容。（可选）
 * @author      李 欢
 * @date 2013-6-6 下午4:51:19 
 * @version V1.0
 */
	@SuppressWarnings("unused")
    @Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		String jobName=context.getJobDetail().getName();
		logger.debug("定时任务【"+jobName+"】 将要执行 start！！");
		QuartzVo quartzVo=(QuartzVo)QuartzVo.quartzMap.get("TY_QUARTZ_JOB_"+jobName);
		
		String httpUrl=quartzVo.getJcallpath();
		HttpURLConnection connection=null;
		InputStream  in=null; 
        try { 
	        URL url = new java.net.URL(httpUrl); 
	        connection = (HttpURLConnection)url.openConnection(); 
	        connection.setRequestProperty("User-Agent","Mozilla/4.0"); 
	        logger.debug("执行的URL为： "+httpUrl);
	        connection.connect(); 					        
	        in = connection.getInputStream(); 
	    	//in.close();
        } catch (MalformedURLException e) { 
        	e.printStackTrace(); 
        }catch(IOException e){
        	e.printStackTrace();
        }finally{ 
        	connection.disconnect();
        }  
		
	}

}

