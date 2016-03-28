package cn.fancy.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Title: FileTest.java
 * @Package cn.fancy.test
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2016-1-8 上午10:58:53
 * @version V1.0
 */
public class FileTest {
  public static void main(String[] args) throws IOException {
    File file=new File("Test.txt");
    file.createNewFile();
    System.out.println( file.exists());
  }
}
