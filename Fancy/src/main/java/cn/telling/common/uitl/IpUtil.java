/**   
* @Title: IpUtil.java 
* @Package com.Common.Uitl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author DAIGUANGJIU    
* @date 2014-3-13 下午12:40:47 
* @version V1.0   
*/ 

package cn.telling.common.uitl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: IpUtil
 * @Description: 通过反向代理后获取客户端IP
 * @author fengjunjie
 * @date 2014-3-13 下午12:40:47
 *
 */
public class IpUtil {

	  public static String getIP4(HttpServletRequest request){
	        String ip = request.getHeader("X-Real-IP");
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	        }
            if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){  
                //根据网卡取本机配置的IP  
                InetAddress inet=null;  
                try {  
                    inet = InetAddress.getLocalHost();  
                } catch (UnknownHostException e) {  
                    e.printStackTrace();  
                }  
                ip= inet.getHostAddress();  
            }
	        return ip;
	 
	    }
}
