/*
 * @(#) UploadAction.java 2014年7月20日
 *
 * Copyright (c) 2014, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.action.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Rotation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.telling.bean.Constants;
import cn.telling.utils.StringHelperTools;

import com.alibaba.fastjson.JSONArray;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;


@Controller
@RequestMapping("upload")
public class FileUploadAction {

	// 记录日志
	protected final static Logger logger = Logger.getLogger(FileUploadAction.class);

	private static final String STATIC_FILE_PATH_IMAGE = "images/";
	private static final String STATIC_FILE_PATH_VIDEO = "video/";
	private static final String STATIC_FILE_PATH_FILE = "file/";

	/**
	 * 上传视文件
	 * 
	 * @param request
	 * @param response
	 * @param imageFile
	 * @return
	 */
	@RequestMapping(value = "/uploadFile", method = { RequestMethod.POST }, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String uploadFile(HttpServletRequest request,
			HttpServletResponse response, @RequestParam MultipartFile imageFile) {
		return uploadCommon(request, response, imageFile, STATIC_FILE_PATH_FILE);

	}

	/**
	 * 上传视频
	 * 
	 * @param request
	 * @param response
	 * @param imageFile
	 * @return
	 */
	@RequestMapping(value = "/uploadVideo", method = { RequestMethod.POST }, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String uploadVideo(HttpServletRequest request,
			HttpServletResponse response, @RequestParam MultipartFile videoFile) {
		return uploadCommon(request, response, videoFile, STATIC_FILE_PATH_VIDEO);

	}

	/***
	 * 上传图片
	 * 
	 * @param request
	 * @param response
	 * @param imageFile
	 * @return
	 */
	@RequestMapping(value = "/uploadImage", method = { RequestMethod.POST }, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String uploadImage(HttpServletRequest request,
			HttpServletResponse response, @RequestParam MultipartFile imageFile) {
		if(imageFile.getContentType().indexOf("image")>=0){
			if(imageFile.getSize()>5*1024*1024){
				return "9";
			}
		}
		return uploadCommon(request, response, imageFile, STATIC_FILE_PATH_IMAGE);
	}

	private String uploadCommon(HttpServletRequest request, HttpServletResponse response,
			MultipartFile imageFile, String type) {
		String originalFilename = imageFile.getOriginalFilename();
		if (!StringHelperTools.isEmpty(originalFilename) && originalFilename.lastIndexOf(".") > 0) {
			String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
			String realFilename = UUID.randomUUID().toString() + ext;
			String realPath = getStaticFilePathForUpload(request, type);
			try {
				saveFile(imageFile.getInputStream(), realPath, realFilename);
			}
			catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			return "/uploads/" + type + realFilename;
		}
		// FIXME 判断文件类型，
		return "";
	}

	/**
	 * 处理kindeditor文件上传
	 * 
	 * @param request
	 * @param response
	 * @param imgFile
	 * @return JSON
	 * 
	 */
	@RequestMapping(value = "/uploadImg", method = { RequestMethod.POST })
	public @ResponseBody void uploadImg(HttpServletRequest request, HttpServletResponse response,
			@RequestParam MultipartFile imgFile) {

		// 返回JSON格式
		try {
			String originalFilename = imgFile.getOriginalFilename();
			if (!StringHelperTools.isEmpty(originalFilename)
					&& originalFilename.lastIndexOf(".") > 0) {
				String ext = originalFilename.substring(originalFilename.lastIndexOf(".") - 1);
				String realFilename = UUID.randomUUID().toString() + ext;
				String realPath = getStaticFilePathForUpload(request, STATIC_FILE_PATH_IMAGE);
				try {
					saveFile(imgFile.getInputStream(), realPath, realFilename);
				}
				catch (IOException e) {
					logger.error(e.getMessage());
					logger.error("filesaveError", e);
				}
				// 其他环境
				String contextPath = request.getContextPath();
				// 正式环境地址
				// String contextPath = "";
				String filePath = contextPath + "/uploads/" + STATIC_FILE_PATH_IMAGE + realFilename;
				String json = "{\"error\":0,\"url\":\"" + filePath + "\"}";
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(json);
				response.getWriter().close();  // modify by ranphy 内存问题
			}
			else {
				String json = "{\"error\":1,\"message\":\"上传失败\"}";
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(json);
				response.getWriter().close();  // modify by ranphy 内存问题
			}

		}
		catch (IOException e) {
			logger.error(e.getMessage());
			logger.error("fileUploadError", e);
		}
	}

	/**
	 * 保存上传的文件在project目录下
	 * 
	 * @param stream
	 * @param path
	 * @param filename
	 * @throws IOException
	 */
	private void saveFile(InputStream stream, String path, String filename) throws IOException {
		FileOutputStream fs = null;
		try {
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();
			File file = new File(path + filename);
			if (!file.exists())
				file.createNewFile();
			byte[] buffer = new byte[1024 * 1024];
			int byteread = 0;
			fs = new FileOutputStream(path + filename);
			while ((byteread = stream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
		} catch (Exception ex) {
			throw ex;
		}
		finally {
			// modify by ranphy 内存问题
			if (fs != null) {
				fs.close();
			}
			if (stream != null) {
				stream.close();
			}
		}
	}

	/**
	 * 获得保存上传文件的物理路径
	 * 
	 * @param request
	 * @param yourPath
	 * @return
	 */
	public static String getStaticFilePathForUpload(HttpServletRequest request, String yourPath) {
		// 图片上传返回的路径
		String uploadPath = "";
		// 获得主机名
		String serverName = request.getServerName();
		// 如果是本机，则按原来那样放置，在本地工程内即可
		if ("localhost".equals(serverName)) {
			StringBuffer temp = new StringBuffer(request.getSession().getServletContext()
					.getRealPath("/")).append("/uploads/").append(yourPath);
			uploadPath = temp.toString();
		}
		else {
			StringBuffer temp = new StringBuffer(request.getSession().getServletContext()
					.getRealPath("/")).append("/uploads/").append(yourPath);
			uploadPath = temp.toString();
//			String serverPath = Constants.UPLOAD_FILE_PATH;
//			if (StringHelperTools.isEmpty(Constants.UPLOAD_FILE_PATH)) {
//				serverPath = request.getSession().getServletContext().getRealPath("/");
//			}
//			StringBuffer temp = new StringBuffer(serverPath).append("/uploads/").append(yourPath);
//			uploadPath = temp.toString();
		}
		return uploadPath;
	}

	/**
	 * 获得保存上传文件的物理路径
	 * 
	 * @param request
	 * @param yourPath
	 * @return
	 */
	public static String getFilePath(HttpServletRequest request, String yourPath) {
		// 图片上传返回的路径
		String uploadPath = "";
		// 获得主机名
		String serverName = request.getServerName();
		// 如果是本机，则按原来那样放置，在本地工程内即可
		if ("localhost".equals(serverName)) {
			StringBuffer temp = new StringBuffer(request.getSession().getServletContext()
					.getRealPath("/")).append(yourPath);
			uploadPath = temp.toString();
		}
		else {
			String serverPath = Constants.UPLOAD_FILE_PATH;
			if (StringHelperTools.isEmpty(Constants.UPLOAD_FILE_PATH)) {
				serverPath = request.getSession().getServletContext().getRealPath("/");
			}
			StringBuffer temp = new StringBuffer(serverPath).append(yourPath);

			uploadPath = temp.toString();
		}
		return uploadPath;
	}

	/**
	 * 上传文件
	 * 
	 * @param response
	 * @param request
	 * @param file
	 * @throws IOException
	 */
	@RequestMapping("/newUploadFile")
	public void uploadFile(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
 
		// 接受的文件流
		byte[] bytes = file.getBytes();
		String serverPath = Constants.UPLOAD_FILE_PATH;
		if (StringHelperTools.isEmpty(Constants.UPLOAD_FILE_PATH)) {
			serverPath = request.getSession().getServletContext().getRealPath("/");
		}
		// 存储路径
		String uploadDir = serverPath + "/uploads";
		File dirPath = new File(uploadDir);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		String sep = System.getProperty("file.separator");
		// 全路径
		File uploadedFile = new File(uploadDir + sep + file.getOriginalFilename());
		// 复制文件
		FileCopyUtils.copy(bytes, uploadedFile);
		String msg = "true";
		response.getWriter().write(msg);
		response.getWriter().close(); // modify by ranphy 内存问题
	}
	
	/***
	 * 上传图片 微信投票前台
	 * 
	 * @param request
	 * @param response
	 * @param imageFile
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/uploadImageWx", method = { RequestMethod.POST }, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String uploadImageWx(HttpServletRequest request,
			HttpServletResponse response, @RequestParam MultipartFile imageFile) throws Exception {
		if(imageFile.getContentType().indexOf("image")>=0){
			if(imageFile.getSize()>5*1024*1024){
				return "{'result':9,'data':[]}";
			}
		}
		String originalFilename = imageFile.getOriginalFilename();
		if (!StringHelperTools.isEmpty(originalFilename) && originalFilename.lastIndexOf(".") > 0) {
			String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
			String realFilename = UUID.randomUUID().toString() + ext;
			String minirealFilename = UUID.randomUUID().toString() + ext;
			String picfl = request.getParameter("picfl");
			String realPath = getStaticFilePathForUpload(request, STATIC_FILE_PATH_IMAGE+picfl+"/");
			try {
				saveFile(imageFile.getInputStream(), realPath, realFilename);
				saveMinPhoto(realPath + realFilename,realPath + minirealFilename, 600);
			}
			catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			List<String> backImgUrl = new ArrayList<String>();
			
			backImgUrl.add("/uploads/" + STATIC_FILE_PATH_IMAGE + picfl + "/" + realFilename);
			backImgUrl.add("/uploads/" + STATIC_FILE_PATH_IMAGE + picfl + "/" + minirealFilename);
			return "{'result':0,'data':" + JSONArray.toJSON(backImgUrl).toString() + "}";
		}
		// FIXME 判断文件类型，
		return "{'result':1,'data':[]}";
	}
	
	/**
	 * 等比例压缩算法： 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图
	 * 
	 * @param srcURL 原图地址
	 * @param deskURL 缩略图地址
	 * @param comBase 压缩基数
	 * @param scale 压缩限制(宽/高)比例 一般用1： 当scale>=1,缩略图height=comBase,width按原图宽高比例;若scale<1,缩略图width=comBase,height按原图宽高比例
	 * @throws Exception
	 * @author shenbin
	 * @createTime 2014-12-16
	 * @lastModifyTime 2014-12-16
	 */
	public static void saveMinPhoto(String srcURL, String deskURL, double comBase)
			throws Exception {

		File srcFile = new java.io.File(srcURL);
		BufferedImage src = ImageIO.read(srcFile);
		
		Rotation r = null;
		try {
			r = getRotation(srcURL);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (r != null) {
			src = Scalr.rotate(src, r);
		}
		
		int srcHeight = src.getHeight(null);
		int srcWidth = src.getWidth(null);
		double scale = 1;
		
		int deskHeight = 0;// 缩略图高
		int deskWidth = 0;// 缩略图宽
		double srcScale = (double)srcHeight / srcWidth;
		/** 缩略图宽高算法 */
		if ((double)srcHeight > comBase || (double)srcWidth > comBase) {
			if (srcScale >= scale || 1 / srcScale > scale) {
				if (srcScale >= scale) {
					deskHeight = (int)comBase;
					deskWidth = srcWidth * deskHeight / srcHeight;
				} else {
					deskWidth = (int)comBase;
					deskHeight = srcHeight * deskWidth / srcWidth;
				}
			} else {
				if ((double)srcHeight > comBase) {
					deskHeight = (int)comBase;
					deskWidth = srcWidth * deskHeight / srcHeight;
				} else {
					deskWidth = (int)comBase;
					deskHeight = srcHeight * deskWidth / srcWidth;
				}
			}
		} else {
			deskHeight = srcHeight;
			deskWidth = srcWidth;
		}
		BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
		tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); // 绘制缩小后的图
		FileOutputStream deskImage = new FileOutputStream(deskURL); // 输出到文件流
		ImageIO.write(tag, "jpg", deskImage); 
		deskImage.close();
		
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
//		encoder.encode(tag); // 近JPEG编码
//		deskImage.close();
	}
	
	private static Rotation getRotation(String path) throws ImageProcessingException, IOException, MetadataException {
		Metadata metadata = ImageMetadataReader.readMetadata(new File(path));
		Collection<ExifIFD0Directory> exifIFD0 = metadata.getDirectoriesOfType(ExifIFD0Directory.class);
		int orientation = 1;
		for (ExifIFD0Directory e : exifIFD0) {
			orientation = e.getInt(ExifIFD0Directory.TAG_ORIENTATION);
		}

		switch (orientation) {
		case 1: // [Exif IFD0] Orientation - Top, left side (Horizontal /
				// normal)
			return null;
		case 6: // [Exif IFD0] Orientation - Right side, top (Rotate 90 CW)
			return Rotation.CW_90;
		case 3: // [Exif IFD0] Orientation - Bottom, right side (Rotate 180)
			return Rotation.CW_180;
		case 8: // [Exif IFD0] Orientation - Left side, bottom (Rotate 270 CW)
			return Rotation.CW_270;
		default:
			return null;
		}
	}
	
}
