/*
 * @(#) ZipUtil.java 2015年3月23日
 *
 * Copyright (c) 2014, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;


/**
 * java 压缩/解压zip文件工具类
 * 
 * @author zhengjiudong
 * @version 1.0
 * @since 2015年3月23日
 */
public class ZipUtil {
	// 记录日志
	protected static final Logger logger = Logger.getLogger(ZipUtil.class);
	/**
	 * zip压缩
	 * 
	 * @param sourceFilePath
	 * @param zipFilePath
	 */
	public static Map<String,Object> zip(String[] sourceFilePaths, String zipFilePath){
		
		Map<String,Object> zipResult = new HashMap<String,Object>();
		
		File sourceFile = null;
		for(String sourceFilePath : sourceFilePaths){
			
			sourceFile = new File(sourceFilePath);
			if(!sourceFile.exists()){
				
				zipResult.put("success",false);
				zipResult.put("msg","源文件不存在");
				return zipResult;
			}
		}
		
		ZipOutputStream zos = null;
		File zipFile = new File(zipFilePath);
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			fos = new FileOutputStream(zipFile);
			bos = new BufferedOutputStream(fos);
			zos = new ZipOutputStream(bos);
			zos.setMethod(ZipOutputStream.DEFLATED);
			for(String sourceFilePath : sourceFilePaths){
				
				compress(new File(sourceFilePath),zos,"");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally{
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				} finally{
					fos = null;
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				} finally{
					bos = null;
				}
			}
			if (zos != null) {
				try {
					zos.flush();
					zos.close();
				} catch (IOException e) {
				logger.error(e.getMessage(), e);
				} finally{
					zos = null;
				}
			}
		}
		
		return zipResult;
	}
	
	
	private static void compress(File sourceFile, ZipOutputStream zos, String basePath) throws Exception{
		
		
		String nextEntryName = basePath=="" ? sourceFile.getName():basePath+"/"+sourceFile.getName();
		
		if(sourceFile.isDirectory()){
			
			File[] files = sourceFile.listFiles();
			if(files.length==0){
				
				zos.putNextEntry(new ZipEntry(nextEntryName + "/"));
				zos.closeEntry();
			}else{
				
				for(File file : files){
					
					compress(file,zos,nextEntryName);
				}
			}
		}else{
			
			zos.putNextEntry(new ZipEntry(nextEntryName));
			InputStream is = null;
			try {
				is = new BufferedInputStream(new FileInputStream(sourceFile));
				byte[] b = new byte[1024];
				int count = is.read(b);
				while(count!=-1){
					
					zos.write(b,0,count);
					count = is.read(b);
				}
			}
			finally {
				if (is != null) {
					is.close();
				}
			}
			zos.closeEntry();
		}
	}

}
