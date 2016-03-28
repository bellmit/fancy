package cn.telling.utils;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

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
	 * @Description：获得当前session
	 * @author: 高兵(gaobing03@chinatelling.com)
	 * @Package: com.tools
	 * @return: HttpSession
	 * @date: 上午10:16:39
	 * @return
	 */
	public static HttpSession getSession(){
		HttpSession session=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();//获得当前session
		return session;
	}
	
	/**
	 * @Description：获得当前登录人userid
	 * @author: 高兵(gaobing03@chinatelling.com)
	 * @Package: com.tools
	 * @return: BigDecimal
	 * @date: 上午10:16:53
	 * @return
	 */
	public static BigDecimal getCurrentUserId(){
		BigDecimal userId=(BigDecimal)getSession().getAttribute("userId");
		return userId;
	}
}
