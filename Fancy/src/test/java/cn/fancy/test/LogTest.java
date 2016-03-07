package cn.fancy.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Title: LogTest.java
 * @Package cn.fancy.test
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2016-2-3 下午4:51:46
 * @version V1.0
 */
public class LogTest {
    final Logger logger = LoggerFactory.getLogger(LogTest.class);
    Integer t;
    Integer oldT;

    public void setTemperature(Integer temperature) {
       
        oldT = t;
        t = temperature;
        Object[] objs = {new java.util.Date(), oldT, t};
        logger.info("Today is {}, Temperature set to {}. Old temperature was {}.", objs);
        if (temperature.intValue() > 50) {
            logger.warn("Temperature({}) has risen above 50 degrees.", t);
        }
        logger.debug("哈哈{}","我是        ");
    }

    public static void main(String[] args) {
        LogTest wombat = new LogTest();
        wombat.setTemperature(10);
        wombat.setTemperature(60);
       
    }
}
