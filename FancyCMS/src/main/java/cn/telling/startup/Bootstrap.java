package cn.telling.startup;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.google.common.util.concurrent.AbstractIdleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebListener
public class Bootstrap extends AbstractIdleService  implements ServletContextListener{

    private ClassPathXmlApplicationContext context;
    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) {
    	Bootstrap bootstrap = new Bootstrap();
        bootstrap.startAsync();
        try {
            Object lock = new Object();
            synchronized (lock) {
                while (true) {
                    lock.wait();//为了使子线程处于等待状态,主线程就会暂停
                }
            }
        } catch (InterruptedException ex) {
        	LOGGER.error("ignore interruption",ex);
        }
    }

    /**
     * Start the service.
     */
    @Override
    protected void startUp() throws Exception {
        LOGGER.info("===================fancy-cms START ....==========================");
        context = new ClassPathXmlApplicationContext(new String[]{"config/spring/cms-context.xml"});
        context.start();
        context.registerShutdownHook();
        LOGGER.info("pig-cms service started successfully");
       

    }

    /**
     * Stop the service.
     */
    @Override
    protected void shutDown() throws Exception {
        context.stop();
        LOGGER.info("service stopped successfully");
    }
    /**
     * 
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.info("pig-cms service started ");
     try {
            startUp();
     } catch (Exception ex) {
            ex.printStackTrace();
     LOGGER.error("ignore interruption ");
     }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            shutDown();
     } catch (Exception e) {
            e.printStackTrace();
     }
    }
}
