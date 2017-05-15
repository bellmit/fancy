package cn.fancy.socket;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 *接口请求的数据封包校验工具类 
 */
public class PackageUtil {

	public static List<String> GetPackageResult(String content)
			throws UnsupportedEncodingException {
		byte[] bytes=content.getBytes();
		List<String> result = new ArrayList<String>();
		int startIndex = 0;
		int tmpContentLength;
		while (startIndex < bytes.length - 1) {
//			tmpContentLength = bytes[startIndex] * 0xFF * 0xFF
//					+ bytes[startIndex + 1] * 0xFF
//					+ bytes[startIndex + 2];
//			byte[] tempBytes = new byte[tmpContentLength];
////			Byte[] oriByteArr = bytes.toArray(new Byte[bytes.size()]);
////			byte[] byteArr = ArrayUtils.toPrimitive(tempBytes);
//			System.arraycopy(bytes, startIndex + 3, tempBytes, 0, bytes.length);
//			startIndex += (tmpContentLength + 17);
//			content = new String(tempBytes, "UTF-8");
//			result.add(content);
			 

	         tmpContentLength = bytes[startIndex] * 0xFF * 0xFF + bytes[startIndex + 1] * 0xFF + bytes[startIndex + 2];
	         byte[] tempBytes = new byte[tmpContentLength];
	         
	         //bytes.CopyTo(startIndex + 3, tempBytes, 0, tempBytes.length);
	         System.arraycopy(bytes, startIndex + 3,tempBytes, 0, tempBytes.length);
	         startIndex += (tmpContentLength + 17);
	         result.add(new String(tempBytes));
		}
		return result;
	}
	
	/**
	 *校验字节
	 */
	  public static boolean CheckEnd(String data) {
		  byte[]buffer=data.getBytes();
         int len = buffer.length;
         if (len >= 17)
         {
             if (buffer[len - 1] == 0x30 && buffer[len - 2] == 0x30
                 && buffer[len - 3] == 0x2e && buffer[len - 4] == 0x30
                 && buffer[len - 5] == 0x31 && buffer[len - 6] == 0x76
                 && buffer[len - 7] == 0x64 && buffer[len - 8] == 0x6E
                 && buffer[len - 9] == 0x65 && buffer[len - 10] == 0x73
                 && buffer[len - 11] == 0x79 && buffer[len - 12] == 0x73
                 && buffer[len - 13] == 0x70 && buffer[len - 14] == 0x6F)
             {
                 return true;
             }
         }
         return false;
     }

	/**
	 * 封包
	 * @throws UnsupportedEncodingException 
	 */
	public static String AppendEnd(String data){
		byte[] buffer;
		try {
			buffer = data.getBytes("utf-8");

			int len = buffer.length + 17;
			byte[] newBuffer = new byte[len];
			newBuffer[0] = (byte) (buffer.length / 0xFF / 0xFF);
			newBuffer[1] = (byte) (buffer.length / 0xFF);
			newBuffer[2] = (byte) (buffer.length % 0xFF);
			System.arraycopy(buffer, 0, newBuffer, 3, buffer.length);
			newBuffer[len - 1] = 0x30;
			newBuffer[len - 2] = 0x30;
			newBuffer[len - 3] = 0x2e;
			newBuffer[len - 4] = 0x30;
			newBuffer[len - 5] = 0x31;
			newBuffer[len - 6] = 0x76;
			newBuffer[len - 7] = 0x64;
			newBuffer[len - 8] = 0x6E;
			newBuffer[len - 9] = 0x65;
			newBuffer[len - 10] = 0x73;
			newBuffer[len - 11] = 0x79;
			newBuffer[len - 12] = 0x73;
			newBuffer[len - 13] = 0x70;
			newBuffer[len - 14] = 0x6F;
			return new String(newBuffer, 0, newBuffer.length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static void main(String[] args) {
		JSONObject data = new JSONObject();
		data.put("RequestType", "Task");
		data.put("Content", "NotifyTask");
		data.put("Token", "");
		JSONObject d = new JSONObject();
		d.put("taskId", 1);
		d.put("status", 1);
		data.put("Data", d);
		System.out.println(data.toJSONString());
		List<String> a=SocketClient.getResult(PackageUtil.AppendEnd(data.toString()));
		System.out.println("客户端接收到:"+a);
	}
}
