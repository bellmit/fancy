package cn.fancy.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.alibaba.fastjson.JSONObject;

public class SocketServer {
    
    public static void main(String[] args) throws InterruptedException {    
    	ServerSocket serverSocket = null;
		Socket socket = null;
		DataInputStream in;
		try {
			// 构造ServerSocket实例，指定端口监听客户端的连接请求
			serverSocket = new ServerSocket(8089);
			// 建立跟客户端的连接
			socket = serverSocket.accept();
			JSONObject data = new JSONObject();
			data.put("RequestType", "Task");
			data.put("Content", "Content");
			data.put("Token", "");
			JSONObject d = new JSONObject();
			d.put("taskId", 1);
			d.put("status", 1);
			data.put("Data", d);
			// 向客户端发送消息;
			OutputStreamWriter os = new OutputStreamWriter(socket.getOutputStream());
			os.write(PackageUtil.AppendEnd(data.toString()));
			os.flush();
			// 接受客户端的响应
			in = new DataInputStream(socket.getInputStream());
			byte[] bytes =new byte[in.available()];
			in.read(bytes);
			//JSONObject da=(JSONObject) PackageUtil.toObject(PackageUtil.GetPackageResult(bytes).get(0));
			System.out.println("服务端收到:"+PackageUtil.GetPackageResult(new String(bytes,"utf-8")));
			os.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 操作结束，关闭socket
			try {
				serverSocket.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }    
    
   
}
