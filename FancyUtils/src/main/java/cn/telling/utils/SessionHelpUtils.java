package cn.telling.utils;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Description：获得当前session
 * @author: 高兵(gaobing03@chinatelling.com)
 * @Package: com.tools
 * @date: 上午10:16:39
 */
public class SessionHelpUtils {
	
	
	
	/**
	 * @Description：获得当前登录人userid
	 * @author: 高兵(gaobing03@chinatelling.com)
	 * @Package: com.tools
	 * @return: BigDecimal
	 * @date: 上午10:16:53
	 * @return
	 */
	public static BigDecimal getUserId(){
		BigDecimal userId=(BigDecimal)getSession().getAttribute("userId");
		return userId;
	}
	
	protected static final Logger logger = Logger.getLogger(SessionHelpUtils.class);
    /**
     * @return
     * @Description：获得当前session
     * @return: HttpSession
     */
    public static HttpSession getSession() {
        HttpSession session = null;
        try
        {
             session = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest().getSession();// 获得当前session
        } catch(Exception e) {
            logger.error(e.getMessage());
        }
        return session;
    }

    /**
     * @return
     * @Description：获得当前登录人userid
     * @return: BigDecimal
     */
    public static String getCurrentUserId() {
        HttpSession session = getSession();
        if (session == null) {
            return "sys";
        }
        String userId = (String) getSession().getAttribute("LOGIN_USER_ID");
        return userId;
    }
    


}
