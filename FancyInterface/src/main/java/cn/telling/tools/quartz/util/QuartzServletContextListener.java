package cn.telling.tools.quartz.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;


public class QuartzServletContextListener implements ServletContextListener {   
//    private static Log logger = LogFactory.getLog(QuartzServletContextListener.class);   
  
    public static final String QUARTZ_FACTORY_KEY =  "org.quartz.impl.StdSchedulerFactory.KEY";   
  
    private ServletContext ctx = null;   
  
    private StdSchedulerFactory factory = null;   
  
    /**  
     * Called when the container is shutting down.  
     */  
    @SuppressWarnings("static-access")
    public void contextDestroyed(ServletContextEvent sce) {   
         try {   
              factory.getDefaultScheduler().shutdown();   
         } catch (SchedulerException ex) {   
              //logger.error("Error stopping Quartz", ex);   
         }   
  
    }   
  
    /**  
     * Called when the container is first started.  
     */  
    public void contextInitialized(ServletContextEvent sce) {   
  
         ctx = sce.getServletContext();   
  
         try {   
  
              factory = new StdSchedulerFactory();
  
              // Start the scheduler now   
  
              factory.getScheduler().start();
  
//              logger.info("Storing QuartzScheduler Factory at"+ QUARTZ_FACTORY_KEY);   
  
              ctx.setAttribute(QUARTZ_FACTORY_KEY, factory);   
  
        } catch (Exception ex) {
//              logger.error("Quartz failed to initialize", ex);   
         }   
    }   
}  
