package cn.telling.tools.quartz.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.telling.tools.quartz.vo.QuartzVo;

/**   
 * @Title: UrlJob.java 
 * @Package com.tools.jobscheduler.job 
 * @Description: TODO(描述该文件做什么) 
 * @author 崔大鹏   
 * @date 2013-4-18 下午5:26:14 
 * @version V1.0   
 */
public class UrlJob implements Job {
	
	public QuartzVo vo;
	public UrlJob(){}
	public UrlJob(QuartzVo v){
		vo = v;
	}

	public String getReturnData(String urlString) throws UnsupportedEncodingException {  
        String res = "";   
        try {   
            URL url = new URL(urlString);  
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();  
            conn.setDoOutput(true);  
            conn.setRequestMethod("POST");  
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));  
            String line;  
            while ((line = in.readLine()) != null) {  
                res += line;  
            }  
            in.close();  
        } catch (Exception e) {  
          //  logger.error("error in wapaction,and e is " + e.getMessage());  
        }  
//      System.out.println(res);   
        return res;  
    }  
	

	/**
	 * 真正执行地方方法
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      李 欢
	 * @date 2013-6-6 下午4:01:51 
	 * @version V1.0
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		String httpUrl="http://"+vo.getJcallpath();
		HttpURLConnection connection=null;
		
		InputStream  in; 
        try { 
        	
	        URL url = new java.net.URL(httpUrl); 
	        connection = (HttpURLConnection)url.openConnection(); 
	        
	        connection.setRequestProperty("User-Agent","Mozilla/4.0"); 
	        System.out.println("执行的URL= "+httpUrl);
	        connection.connect(); 					        
	        in = connection.getInputStream(); 
	    	in.close();
        } catch (MalformedURLException e) { 
        	e.printStackTrace(); 
        }catch(IOException e){
        	e.printStackTrace();
        }finally{ 
        	connection.disconnect();
        }  
	}
}

