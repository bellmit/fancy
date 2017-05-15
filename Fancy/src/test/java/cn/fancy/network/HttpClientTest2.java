package cn.fancy.network;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
/**   
 * @Title: HttpClientTest2.java 
 * @Package cn.fancy.network 
 * @Description: 异步使用HttpClient请求，支持https
 * @author 操圣
 * @date 2017年3月2日 下午4:38:56 
 * @version V1.0   
 */
public class HttpClientTest2 {
	 public static void main(String[] argv) {
	        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
	        httpclient.start();

	        final CountDownLatch latch = new CountDownLatch(1);
	        final HttpGet request = new HttpGet("http://www.baidu.com");

	        System.out.println(" caller thread id is : " + Thread.currentThread().getId());

	        httpclient.execute(request, new FutureCallback<HttpResponse>() {

	            public void completed(final HttpResponse response) {
	                latch.countDown();
	                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
	                System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
	                try {
	                    String content = EntityUtils.toString(response.getEntity(), "UTF-8");
	                    System.out.println(" response content is : " + content);
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }

	            public void failed(final Exception ex) {
	                latch.countDown();
	                System.out.println(request.getRequestLine() + "->" + ex);
	                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
	            }

	            public void cancelled() {
	                latch.countDown();
	                System.out.println(request.getRequestLine() + " cancelled");
	                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
	            }

	        });
	        System.out.println("我是--------------------------");
	        try {
	            latch.await();
	            System.out.println("我为什么等待");
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        try {
	            httpclient.close();
	            System.out.println("我为什么关了");
	        } catch (IOException ignore) {

	        }
	        
	    }
}

