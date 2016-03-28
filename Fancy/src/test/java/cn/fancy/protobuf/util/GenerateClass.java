package cn.fancy.protobuf.util;

import cn.telling.utils.LogUtils;

/**
 * @Title: Snippet.java
 * @Package cn.fancy.protobuf.util
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2016-3-10 下午2:04:55
 * @version V1.0
 */

public class GenerateClass {
    /**
     * 调用protoc.exe生成java数据访问类
     * */
    public static void main(String[] args) throws Exception {
        String protoFile = "person.proto";// 如果要更换生成的数据访问类，只需在此进行更改
        // String protoFile = "person.proto";
        String strCmd = "protoc.exe --java_out=./src/test/java ./proto/" + protoFile;
        Runtime.getRuntime().exec("cmd /c " + strCmd).waitFor();// 通过执行cmd命令调用protoc.exe程序
        LogUtils.Log(GenerateClass.class).debug("啊哈谁的发生的发");
    }
}
