package cn.fancy.neety;

import java.io.File;

/**
 * @Title: PropertiesTest.java
 * @Package cn.fancy.neety
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2016-2-3 下午6:10:22
 * @version V1.0
 */
public class PropertiesTest {
    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("service_reg_server_ip", "service_reg_server");
    public static void main(String[] args) {
        System.out.println(HOST);
        System.out.println(File.separator);
    }
}
