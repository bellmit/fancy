package cn.telling.common.uitl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
public class XmlConfigHelp {

	//private final static String CONFIGFILEPATH = "/web/WEB-INF/xmlConfig/dataSourceConfig/dsConfig.xml";
	private final static String CONFIGFILEPATH = "E:\\workstation\\project\\TellingB2B\\web\\WEB-INF\\xmlConfig\\dataSourceConfig\\dsConfig.xml";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//String xmlPath=getServletContext().getResourceAsStream( "/WEB-INF/xmlConfig/dataSourceConfig/dsConfig.xml" );
			ParseConfigDoc(CONFIGFILEPATH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 辅助方法，由于访问配置文件生成Document对象
	 * 
	 * @return org.w3c.dom.Document
	 * @param fileName
	 *            java.lang.String
	 */
	private static Document ParseConfigDoc(String fileName) throws Exception {
		Document doc = null;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));

			InputSource is = new InputSource(reader);

			DOMParser parser = new DOMParser();
			parser.parse(is);
			doc = parser.getDocument();
			System.out.print(doc.getNodeName());

		} catch (SAXException saxe) {
			saxe.printStackTrace();
			throw new Exception("配置文件解析异常， IOException!", saxe);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(
					"配置文件解析异常，ServiceConfigEntity parseConfigDoc(String) Exception!",
					e);
		}
		return doc;
	}

}
