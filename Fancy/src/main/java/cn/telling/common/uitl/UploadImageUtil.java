package cn.telling.common.uitl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created with Intellij IDEA 13.
 * User:黄学斌(sunny)
 * Date:2014/7/26,13:46
 * 上传图片工具类
 */
public class UploadImageUtil {
	
	public static String UPLOAD_PATH="uploadImg/";
	public static String MAX_SIZE="1024000";
	public static String IMAGETYPES="jpg,png,bmp,jpeg,gif";
	public static String MAX_WIDTH="";
	public static String MAX_HEIGHT="";
	
	
    /**
     * 文件操作 获取文件扩展名
     * @param filename
     * 注意：filename 文件名称包含扩展名
     * return，后缀 如：txt jpg
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }
    public static String getExtensionNameNew(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        System.out.println(filename);
        return filename;
    }
    /**
     * 上传文件方法
     * @param file 文件流
     * @param savePath 保存服务器路径
     *        注意：路径必须是以"/mnt/b2b/"开头的
     * @param filename 文件原名称
     * @return 返回数据库应该保存的 url
     * @throws Exception
     */
    public static String uploadPic(MultipartFile file,String savePath,String filename)throws Exception{
        String newfilename=getNewFilename(filename);
        copyFile(file,savePath,newfilename);
        String urlpath=savePath.substring(8,savePath.length());
        return urlpath+newfilename;
    }
    /**
     * 上传文件方法
     * @param request
     * @param savePath 保存服务器路径
     *        注意：路径必须是以"/mnt/b2b/"开头的
     * @return 返回数据库应该保存的 url
     * @throws Exception
     */
    public static String uploadPic(HttpServletRequest request,String savePath)throws Exception{
        String newfilename="";
        try {
            String dispoString = request.getHeader("Content-Disposition");
            int iFindStart = dispoString.indexOf("filename=\"") + 10;
            int iFindEnd = dispoString.indexOf("\"", iFindStart);
            String sFileName = dispoString.substring(iFindStart, iFindEnd);
            int i = request.getContentLength();
            byte buffer[] = new byte[i];
            int j = 0;
            while (j < i) { //获取表单的上传文件
                int k = request.getInputStream().read(buffer, j, i - j);
                j += k;
            }
            if (buffer.length >= 0) { //文件是否为空
                newfilename=getNewFilename(sFileName);
                String uploadFile = savePath+newfilename;
                File filelist = new File(savePath);
                if  (!filelist .exists()  && !filelist .isDirectory()) filelist.mkdir();
                File file = new File(uploadFile);
                OutputStream out = new BufferedOutputStream(new FileOutputStream(file, true));
                out.write(buffer);
                out.close();
            }
        } catch (Exception ex) {
        }
        String urlpath=savePath.substring(8,savePath.length());
        return urlpath+newfilename;
    }

    //    public static void main(String[] args) {
//        String savePath="/mnt/b2b/index/zx/";
//        String filename="aaaa.";
//        String newfilename=getNewFilename(filename);
//        String urlpath=savePath.substring(8,savePath.length());
//        System.out.println(urlpath+newfilename); ;
//    }
    /**
     * 获取新的文件名称用于储存到linux目录和数据库
     * @param filename
     * @return
     */
    public static String getNewFilename(String filename){
        if (filename==null||filename==""){
            return null;
        }
        Calendar cal   =   Calendar.getInstance();
        Date time   =   cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss_ssss");
        String systemTime=sdf.format(time);
        String newFileName = systemTime+"."+getExtensionName(filename);
        return newFileName;
    }

    public static String getNewFilenameNew(String filename){
        if (filename==null||filename==""){
            return null;
        }
    	Random random= new Random();
        int rand= random.nextInt(10);
        Calendar cal   =   Calendar.getInstance();
        Date time   =   cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss_ssss");
        String systemTime=sdf.format(time);
        String newFileName = systemTime+rand+"."+getExtensionNameNew(filename);
        return newFileName;
    }
    
    public static String getNewFilenameNew1(String filename){
        if (filename==null||filename==""){
            return null;
        }
//    	Random random= new Random();
       // int rand= random.nextInt(10);
    	UUID uuid = UUID.randomUUID();
        Calendar cal   =   Calendar.getInstance();
        Date time   =   cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss_ssss");
        String systemTime=sdf.format(time);
        String newFileName = systemTime+uuid.toString()+"."+getExtensionNameNew(filename);
        return newFileName;
    }
    /**
     * 上传copy文件方法
     * @param savePath 在linux上要保存完整路径
     * @param newFilename 新的文件名称， 采用系统时间做文件名防止中文报错的问题
     * @throws Exception
     */
    public static void copyFile(MultipartFile file,String savePath,String newFilename ) throws Exception {
        try {
            File targetFile = new File(savePath, newFilename);
            if (!targetFile.exists()) {
                //判断文件夹是否存在，不存在就创建
                targetFile.mkdirs();
            }

                file.transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

    }


}
