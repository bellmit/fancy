/*
 * @(#) RemoteServiceRouter.java 2015年4月3日
 *
 * Copyright (c) 2014, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.rcb.router;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;


public class RemoteServiceRouter implements ServiceRouter {

	private String ip;
	private int port;

	@Override
	public URL getServiceURL(String servicename) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("http://").append(getIp()).append(":").append(getPort()).append("/sr/si/")
					.append(servicename);
			String server = doGet(sb.toString(), "", "utf-8", false);
			if (server == null) return null;
			String[] s = server.split(":");
			if (server.length() < 2) return null;
			return new URL("http", s[0], Integer.parseInt(s[1]), "");
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public static String doGet(String url, String queryString, String charset, boolean pretty) {
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);//url:http://192.168.102.215:80/sr/si/TellingCrmService
		try {
			method.setQueryString(URIUtil.encodeQuery(queryString));
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						method.getResponseBodyAsStream(), charset));
				String line;
				while ((line = reader.readLine()) != null) {
					if (pretty)
						response.append(line).append(System.getProperty("line.separator"));
					else
						response.append(line);
				}
				reader.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			method.releaseConnection();
		}
		return response.toString();
	}

}
