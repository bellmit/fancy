package cn.telling.web;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import cn.telling.utils.EncryptUtils;

/**   
 * @Title: EncryptPropertyPlaceholderConfigurer.java 
 * @Package cn.telling.web 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2017年5月24日 下午4:42:35 
 * @version V1.0   
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer  
{  
    private String[] encryptPropNames = {"jdbc.username", "jdbc.password"};  
      
    @Override  
    protected String convertProperty(String propertyName, String propertyValue)  
    {  
          
        //如果在加密属性名单中发现该属性  
        if (isEncryptProp(propertyName))  
        {  
            String decryptValue = EncryptUtils.aesDecrypt(propertyValue,EncryptUtils.defaultKey);  
            System.out.println("解密后的字符串是:"+decryptValue);  
            return decryptValue;  
        }else {  
            return propertyValue;  
        }  
          
    }  
      
    private boolean isEncryptProp(String propertyName)  
    {  
        for (String encryptName : encryptPropNames)  
        {  
            if (encryptName.equals(propertyName))  
            {  
                return true;  
            }  
        }  
        return false;  
    }  
}  

