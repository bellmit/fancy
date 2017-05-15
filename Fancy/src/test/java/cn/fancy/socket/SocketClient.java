package cn.fancy.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SocketClient {

	private static final Log log = LogFactory.getLog(SocketClient.class);

	private static Properties config = null;

	public static List<String> getResult(String string) {
		List<String> byteLs=new ArrayList<String>();
		OutputStreamWriter out = null;
		Socket socket = null;
		try {
			log.info("准备链接服务器");
			String url = readValue("url", "serviceConfig.properties");
			String port = readValue("port", "serviceConfig.properties");
			socket = new Socket(url, Integer.parseInt(port));
			socket.setSoTimeout(9500);
			out = new OutputStreamWriter(socket.getOutputStream());
			log.info("服务器链接成功,开始发送数据");
			out.write(string);
			out.flush();
			Thread.sleep(5000);//TODO 会有服务端来不及返回数据导致下面的数据获取不到报错问题
			log.info("数据发送刚完成,开始接收数据");
			InputStreamReader isr =new InputStreamReader(socket.getInputStream()); 
			BufferedReader in = new BufferedReader(isr);
			StringBuffer content= new StringBuffer();
			BufferedReader reader = new BufferedReader(in);
			int ch;
			while ((ch = reader.read()) != -1) {
			    content.append((char) ch);
			}
			if(PackageUtil.CheckEnd(content.toString())){
				byteLs=PackageUtil.GetPackageResult(content.toString());
			}
			log.info("客户端返回"+content.toString());
			out.close();
			socket.close();
			return byteLs;
		} catch (Exception e) {
			log.error("关闭流失败");
			e.printStackTrace();
			return byteLs;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					log.error("关闭流失败");
					e.printStackTrace();
				}
			}
		}
	}

	public static String readValue(String key, String filename) {
		String value = null;
		InputStream in = null;
		try {
			in = SocketClient.class.getClassLoader().getResourceAsStream(
					filename);
			config = new Properties();
			config.load(in);
			value = config.getProperty(key);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ConfigInfoError" + e.toString());

			if (in != null)
				try {
					in.close();
				} catch (IOException e1) {
					log.error("关闭流失败");
					e1.printStackTrace();
				}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.error("关闭流失败");
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	public static void main(String[] args) {
System.out.println(0xFF);
byte []c=new byte[]{-128,127};
System.out.println(c);
	}
}
