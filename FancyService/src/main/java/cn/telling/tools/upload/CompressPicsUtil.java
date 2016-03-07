package cn.telling.tools.upload;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;



/**   
 * @author 李 欢   
 * @date 2013-4-27 上午9:36:23 <br />
 * @see
 * 缩略图类（通用） 本java类能将jpg、bmp、png、gif图片文件，进行等比或非等比的大小转换。 具体使用方法
 * compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true)) <br />
 * * 1、图片格式<br />
 * JAVA的API很好，com.sun.image.codec.jpeg.JPEGCodec和com.sun.image.codec.jpeg.
 * JPEGImageEncoder 这两个类基本上自动解决了类型转换的问题，
 * 可以正常实现bmp转jpg、png转jpg、gif转jpg，但是暂时还没有解决gif转gif的功能。<br />
 * 2、画面质量的问题<br />
 * BufferedImage tag = new BufferedImage((int)newWidth, (int) newHeight,
 * BufferedImage.TYPE_INT_RGB); Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高
 * 生成的图片质量比较好 但速度慢 tag.getGraphics().drawImage(img.getScaledInstance(newWidth,
 * newHeight, Image.SCALE_SMOOTH), 0, 0, null);<br />
 * 3、压缩速度<br />
 * 测试36MB的bmp图片（8192*6144）压缩成（160*120）的jpg的5KB图片，只需要2-3秒的时间。批量处理100张（1027*768）
 * 的bmp图像，转换成（120*80）的jpg图片总共只需要17秒。<br />
 * 4、根据用户喜好选择压缩模式<br />
 * 按比例或者按规定尺寸<br />
 * compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))<br />
 * 
 */
public class CompressPicsUtil {
	/**
	 * 
	 * @Description: 获得图片大小 传入参数 String path ：图片路径
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @author      李 欢
	 * @date 2013-4-27 上午10:02:42 
	 * @version V1.0
	 */
	public long getPicSize(String path) {
		File file = new File(path);
		return file.length();
	}
	/**
	 * 
	 * @Description: 图片处理
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      李 欢
	 * @date 2013-4-27 上午9:43:15 
	 * @version V1.0
	 */
	
		public String compressPic(CompressImageVo compressVo) {
			FileOutputStream out=null;
			File file=null;
			try {
				// 获得源文件
				file = new File(compressVo.getInputFileName());
				if (!file.exists()) {
					return " 文件都没得，搞么子搞~";
				}
				Image img = ImageIO.read(file);
				// 判断图片格式是否正确
				if (img.getWidth(null) == -1) {
					System.out.println(" 格式不对啦~" + "<BR>");
					return " 格式都不对，搞么子搞~ ";
				} else {
					int newWidth;
					int newHeight;
					// 判断是否是等比缩放
					if (compressVo.isProportion() == true) {
						// 为等比缩放计算输出的图片宽度及高度
						double rate1 = ((double) img.getWidth(null))/ (double) compressVo.getOutputWidth() + 0.1;
						double rate2 = ((double) img.getHeight(null))/ (double) compressVo.getOutputHeight() + 0.1;
						// 根据缩放比率大的进行缩放控制
						double rate = rate1 > rate2 ? rate1 : rate2;
						newWidth = (int) (((double) img.getWidth(null)) / rate);
						newHeight = (int) (((double) img.getHeight(null)) / rate);
					} else {
						newWidth = compressVo.getOutputWidth(); // 输出的图片宽度
						newHeight = compressVo.getOutputHeight(); // 输出的图片高度
					}
					BufferedImage tag = new BufferedImage((int) newWidth,(int) newHeight, BufferedImage.TYPE_INT_RGB);

					/*
					 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
					 */
					tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH), 0, 0, null);
					 out = new FileOutputStream(compressVo.getOutputFileName());
					// JPEGImageEncoder可适用于其他图片类型的转换
//					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//					encoder.encode(tag);
	
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}finally{
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return " 图片压缩转换完成！";
		}
		/**
		 * 
		 * @Description: 测试类。
		 * @param		参数说明
		 * @return		返回值
		 * @exception   异常描述
		 * @see		          需要参见的其它内容。（可选）
		 * @author      李 欢
		 * @date 2013-4-27 上午9:48:50 
		 * @version V1.0
		 */
		public static void main(String[] args){
			CompressImageVo comVo=new CompressImageVo();
			comVo.setInputFileName("C:\\Users\\lenovo\\Desktop\\UI\\mb.png");
			comVo.setOutputFileName("d:\\test\\test.png");
			comVo.setProportion(true);
			comVo.setOutputWidth(350);
			comVo.setOutputHeight(150);
			CompressPicsUtil cUtil =new CompressPicsUtil();
			String isSuccess=cUtil.compressPic(comVo);
			System.out.println(isSuccess);
		}
	
}
