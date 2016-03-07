package cn.telling.tools.scheduler ;
/**
* @Title: taskManager.java 
* @Package com.tools.scheduler 
* @Description: 多线程任务调度管理:内存执行,循环监视,实时分配线程执行任务
* @author 曾启华  
* @date 2013-12-30
**/
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/scheduler")
public class taskManager
{
  public static ArrayList<String[]> tasklist;//调度列表,任务名|起始时间|间隔数量|间隔单位|程序URL地址
  public static boolean started;//运行状态
  public static boolean torun=true;//调度总开关
  private static String tormvid="id:";
  private static ArrayList<String> errurl;
  private String fpath;
  private String taskfile;
  private static Logger logger = Logger.getLogger(taskManager.class);
	
  public taskManager ()
  {
	}
  
  @RequestMapping(value="/index",method={RequestMethod.GET,RequestMethod.POST})
	public String index(ModelMap map,HttpServletRequest request) { 
		fpath=request.getSession().getServletContext().getRealPath("WEB-INF/webPage/scheduler");
		taskfile=fpath+"/taskfile.txt";
		
		if (errurl==null) errurl=new ArrayList<String>();
		if (tasklist==null||tasklist.size()==0) 
		{
			tasklist=new ArrayList<String[]>();
			String taskln="";
			try{
  			BufferedReader ins=new BufferedReader(new FileReader(taskfile));
  			Date d=new java.util.Date();    
				SimpleDateFormat s=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String ntime = s.format(d);
					
  			while ((taskln = ins.readLine()) != null)
				{
					String[] task=taskln.split("\\|");
					if (ntime.compareTo(task[1])>0)
						task[1]=addtime(ntime,1,"小时").substring(0,13)+task[1].substring(13);
					tasklist.add(task);
				}
				ins.close();ins=null;
			}catch (Exception re){}
		}
		
		startwork();
		map.put("list", tasklist);
		if (torun)
		{
			 map.put("rstat","<font color=blue>调度已启动,监视执行中...</font>");
			 map.put("docommd","<input type=button class='inpstyle05' onclick='sendcommd(1,0)' value='停止调度'>");
		}
		else
		{
			map.put("rstat","<font color=red>调度已停止...</font>");
			map.put("docommd","<input type=button class='inpstyle05' onclick='sendcommd(1,1)' value='启动调度'>");
		}
		return "scheduler/index";
	}
	
	
	@RequestMapping(value="/addtask",method={RequestMethod.GET,RequestMethod.POST})
	public String addtask(ModelMap map,HttpServletRequest request) 
	{
		String[] task={request.getParameter("jobName"),request.getParameter("bgtime"),request.getParameter("sptime"),request.getParameter("stimetype"),"http://"+request.getParameter("callPath")};
		String addstatus="";
		try
		{
		if (!existloop(task[4]))
		{
			tasklist.add(task);
			flushtask(tasklist);
			torun=true;
		}
		else
			addstatus="<span style='color:red;'>已存在此程序的循环调度..</span>";
		request.getSession().setAttribute("addstatus",addstatus);
		return ("redirect:/scheduler/index.html");
		}
		catch(Exception e){return ("redirect:/Login.html");}
	}
	@RequestMapping(value="/taskcommd",method={RequestMethod.GET,RequestMethod.POST})
	public String taskcommd(ModelMap map,HttpServletRequest request) 
	{
		String opid=request.getParameter("opid");
		if (opid==null) opid="";
		String itemid=request.getParameter("commdid");
		if (itemid==null) itemid="";
			
		if (opid.equals("1"))
		{
			if (itemid.equals("0")) torun=false;
			if (itemid.equals("1"))
			{
				torun=true;
				Date d=new java.util.Date();    
				SimpleDateFormat s=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String ntime = s.format(d);
				for (int i=0;i<tasklist.size();i++)
				{
					String[] task=(String[])tasklist.get(i);
					if (ntime.compareTo(task[1])>0)
						task[1]=addtime(ntime,1,"小时").substring(0,13)+task[1].substring(13);
					tasklist.set(i,task);
				}
			}
		}
		else if (opid.equals("2"))
		{
			boolean bf=torun;torun=false;
			writeinlog((String[])tasklist.get(Integer.parseInt(itemid)));
			tasklist.remove(Integer.parseInt(itemid));
			flushtask(tasklist);
			torun=bf;
		}
		return ("redirect:/scheduler/index.html");
	}
	
	/**
	*	监视器:
	* 1. 任务列表中,若任务时间到,则新开线程执行任务
	* 2. 对于单次调度,调度后标记移除
	* 3. 对于循环调度,更新监视点(下次执行时间)
	* 4. 遍历后若有移除任务,则更新历史文件与任务列表文件
	* 5. 每次遍历休息1秒.完成所有任务(任务列表为空)时,监视主线程结束,状态为停止
	**/ 
  private class monitor extends Thread
  {
      monitor ()
      {
      }
      public void run() 
      {
      	started=true;
      	logger.info("*** 监视器已启动,开始遍历监视任务列表 ***");
  			while (!tasklist.isEmpty()&&torun)
  			{
  				Date d=new java.util.Date();    
					SimpleDateFormat s=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String dateStr = s.format(d);
						
  				for (int i=0;i<tasklist.size();i++)
  				{
  					String[] task=(String[])tasklist.get(i);
  					if (dateStr.equals(task[1]))
  				  {
  				  	execTask dotask=new execTask(task,i);
							dotask.start();
							if (!task[2].equals("0"))
  				  	{
  				  		task[1]=addtime(task[1],Integer.parseInt(task[2]),task[3]);
  				  		tasklist.set(i,task);
  				  	}
  				  }
    			}//end of for
    			
    			if (!tormvid.equals("id:"))
    			{
    				for (int m=0;m<tasklist.size();m++)
  					{
  						if (tormvid.indexOf(":"+String.valueOf(m)+":")>0)
  						{
  							writeinlog((String[])tasklist.get(m));
  							tasklist.remove(m);
  							flushtask(tasklist);
  						}
  					}
  					tormvid="id:";
    			}
    			try{
    				sleep(1000);
    			}catch(Exception se){}
  			}//end of while
  			logger.info("*** 完成所有任务,或被手动关闭调度.监视器已停止.当有新任务加入时,将再次自动启动.... ***");
  			started=false;
  		}// end of run
  }
  private class execTask extends Thread
  {
  	private String[] task;
  	private int tid;
  	execTask(String[] tk,int inid)
  	{
  		task=tk;tid=inid;
  	}
  	public void run()
  	{
  		try
  		{
  			if (!openURL(task[4]))
  			{
  				Date d=new java.util.Date();    
					SimpleDateFormat s=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String dateStr = s.format(d);
  				try
					{
  					FileWriter logfile=new FileWriter(fpath+"/logs/error.log",true);
  					logfile.write(dateStr+" -> "+task[0]+"|"+task[1]+"|"+task[2]+"|"+task[3]+"|"+task[4]+"\r\n");
  					logfile.close();
  				}catch(Exception e) {e.printStackTrace();}
  				
  				String ntime=addtime(dateStr,10,"秒");
  				if (task[2].equals("0"))
  				{
  					if (errurl.contains(task[4]))
  					{
  						if (ntime.compareTo(gettimebyurl(task[4],false))<0) 
  						{
  							task[1]=ntime;
  							tasklist.set(tid,task);
  						}
  						else
  						{
  							tormvid=tormvid+String.valueOf(tid)+":";System.out.println("添加移除ID: "+String.valueOf(tid));
  							errurl.remove(task[4]);
  						}
  					}
  					else 
  					{
  						task[1]=ntime;
  						tasklist.set(tid,task);
  					}
					}
					else
					{
						if (!errurl.contains(task[4]))
						{
							tasklist.add(new String[]{task[0],ntime,"0",task[3],task[4]});
							errurl.add(task[4]);
						}
					}
  			}
  			else
  			{
  					if (task[2].equals("0"))
  					{
  						tormvid=tormvid+String.valueOf(tid)+":";
  						if (errurl.contains(task[4])) errurl.remove(task[4]);
  					}
  			}
  			
  		}catch(Exception e){System.out.println(e);}
  	}
  }
  public boolean existloop(String instr)
  {
  	boolean rs=false;
  	for (int i=0;i<tasklist.size();i++)
  	{
  		String[] task=(String[])tasklist.get(i);
  		if (task[4].equals(instr)&&(!task[2].equals("0")))
  		{ rs=true;break;}
  	}
  	return rs;
  }
 
  public String gettimebyurl(String instr,boolean tp)
  {
  	String tm="";
  	for (int i=0;i<tasklist.size();i++)
  	{
  		String[] task=(String[])tasklist.get(i);
  		boolean tasktp=tp?task[2].equals("0"):!task[2].equals("0");
  		if (task[4].equals(instr)&&tasktp)
  		{ tm=task[1];break;}
  	}
  	return tm;
  }
  public boolean openURL( String inurl) throws java.net.MalformedURLException,java.io.IOException
	{
		URL url =new URL(inurl);
  	HttpURLConnection httpconn = (HttpURLConnection)url.openConnection();
  	try{
  		httpconn.connect();
  	}catch(Exception e){
  		System.out.println("******* 连接失败,程序地址 : "+inurl);return false;}
  	
  	if(httpconn.getResponseCode()!=HttpURLConnection.HTTP_OK)
  	{
  		System.out.println("******* 调度失败!!,程序地址 : "+inurl);
			return false;
		}else
		{
			System.out.println("******* 已完成调用程序 : "+inurl);
			return true;
		}
	}
  private void writeinlog(String[] item)
  {
  	Date d=new java.util.Date();    
		SimpleDateFormat s=new java.text.SimpleDateFormat("yyyy-MM-dd");
		String dateStr = s.format(d);
		try
		{
  		FileWriter logfile=new FileWriter(fpath+"/logs/"+dateStr+".log",true);
  		logfile.write(item[0]+"|"+item[1]+"|"+item[2]+"|"+item[3]+"|"+item[4]+"\r\n");
  		logfile.close();
  	}catch(Exception e){e.printStackTrace();}
  }
  private void flushtask(ArrayList<String[]> tsklst)
  {
  	StringBuffer strb=new StringBuffer();
  	for (int i=0;i<tsklst.size();i++)
  	{
  		String[] task=(String[])tsklst.get(i);
  		strb.append(task[0]).append("|").append(task[1]).append("|").append(task[2]).append("|").append(task[3]).append("|").append(task[4]).append("\r\n");
  	}
  	try{
  	FileOutputStream fout = new FileOutputStream( taskfile );
    fout.write( strb.toString().getBytes() );
    fout.close();
  	}catch(IOException ioe){}
  }
  private void startwork()
  {
  	if (!started)
  	{
  		monitor mt=new monitor();
			mt.start();
  	}
  }
  public int getTaskcount()
  {
  	return tasklist.size();
  }
  public boolean isworking()
  {
  	return started;
  }
  public String addtime(String idate,int toadd,String unit)
{
	idate = idate.replace(":","-");
	idate = idate.replace(" ","-");
	String[] dts = idate.split("-");
	
	Calendar calendar = new GregorianCalendar(Integer.parseInt(dts[0]),Integer.parseInt(dts[1]),Integer.parseInt(dts[2]),Integer.parseInt(dts[3]),Integer.parseInt(dts[4]),Integer.parseInt(dts[5]));
  calendar.add(Calendar.MONTH , -1);
  if (unit.equals("秒"))
  	calendar.add(Calendar.SECOND , toadd);
  else if (unit.equals("分钟"))
  	calendar.add(Calendar.MINUTE , toadd);
  else if (unit.equals("小时"))
  	calendar.add(Calendar.HOUR_OF_DAY , toadd);
  else if (unit.equals("天"))
    calendar.add(Calendar.DAY_OF_MONTH , toadd);
  else if (unit.equals("周"))
    calendar.add(Calendar.DAY_OF_MONTH , toadd*7);
  else if (unit.equals("月"))
    calendar.add(Calendar.MONTH , toadd);
  
  java.util.Date d=calendar.getTime();    
	java.text.SimpleDateFormat s=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	return s.format(d);
}

} 