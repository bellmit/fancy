package cn.telling.common.uitl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.telling.common.Pub;
import cn.telling.common.vo.IBaseVO;


/***
 * 通用xml和vo的转化类
 * 
 * @author Andy
 * 
 */
public class VOXMLMapping {
	
	private static Logger logger = Logger.getLogger(VOXMLMapping.class);
	
	/***
	 * <p>xml转换成vo<p>
	 * @param xml
	 * @param clazz
	 * @param converter<oldfield,newfiled>
	 * @return
	 * @throws Exception
	 */
	public static IBaseVO fromXML(String xml,Class<?> clazz,Map<String,String> converter) throws Exception{
		IBaseVO vo = (IBaseVO)clazz.newInstance();
		Map<String,String> fieldMap=Pub.getVOFieldType(vo);
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		elementIterator(clazz, converter, vo, fieldMap, root);
		return vo;
//		for ( Iterator<?> i = root.elementIterator(); i.hasNext(); ) {
//	        Element element = (Element) i.next();
//	        String fiendName=Pub.firstLetterToUpper(element.getName()); //首字符转换成大写
//	        if(null!=converter&&converter.size()>0&&converter.containsKey(fiendName)){//xml中的字段转化成vo中的字段
//        		fiendName=converter.get(fiendName);
//        	}
//	        if(fieldMap.containsKey(fiendName)){//判断xml中是否在这个字段
//	        	String fiendType=fieldMap.get(fiendName);
//	        	if(fiendType.equalsIgnoreCase("BigDecimal")){
//					Method m =clazz .getMethod("set"+fiendName, new Class[]{BigDecimal.class});
//					m.invoke(vo, new Object[]{new BigDecimal(element.getText())});
//				}else if("INT".equalsIgnoreCase(fiendType)){
//					Method m =clazz .getMethod("set"+fiendName, new Class[]{BigDecimal.class});
//					m.invoke(vo, new Object[]{new Integer(element.getText())});
//				}else{
//					Method m = clazz.getMethod("set"+fiendName, new Class[]{String.class});
//					m.invoke(vo, new Object[]{element.getText()});
//				}
//	        }
//		 }
//		return vo;
	}
	
	
	/***
	 * element的遍历
	 * @param clazz
	 * @param converter
	 * @param vo
	 * @param fieldMap
	 * @param root
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalArgumentException 
	 * @throws InstantiationException 
	 */
	private static void elementIterator(Class<?> clazz,Map<String, String> converter, IBaseVO vo,Map<String, String> fieldMap, Element root)throws NoSuchMethodException, IllegalAccessException,InvocationTargetException, NoSuchFieldException, SecurityException, ClassNotFoundException, InstantiationException, IllegalArgumentException {
		for (Iterator<?> i = root.elementIterator(); i.hasNext(); ) {
	        Element element = (Element) i.next();
	        logger.debug("节点名称："+element.getName());
	        String fiendName=Pub.firstLetterToUpper(element.getName()); //首字符转换成大写
	        List<?> elements= element.elements();
	        if(null!=converter&&converter.size()>0&&converter.containsKey(fiendName)){//xml中的字段转化成vo中的字段
        		fiendName=converter.get(fiendName);
        	}
	        if(elements.size()>0)callBackXml(clazz, element, fiendName, elements,vo);
	        setValue(clazz, element, fiendName, vo, fieldMap);
		 }
	}
	
	/***
	 * 递归xml文件
	 * @param clazz
	 * @param element
	 * @param fiendName
	 * @param elements
	 * @throws NoSuchFieldException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	private static void callBackXml(Class<?> clazz, Element element,String fiendName, List<?> elements,IBaseVO basevo) throws NoSuchFieldException,ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		if(elements.size()>0){//大于0标示有下级节点
		 List<IBaseVO>  list =new ArrayList<IBaseVO>();
			 for(Iterator<?> i = element.elementIterator(); i.hasNext(); ){//循环list级次
				 Element eles = (Element) i.next();
				 if(eles.elements().size()>0){
					 logger.debug(eles.getName());
					 ParameterizedType t=(ParameterizedType)clazz.getField(fiendName.toLowerCase()).getGenericType();
					  String className=t.getActualTypeArguments()[0].toString();
					  Class<?> claz=Class.forName(className.substring("class ".length(), className.length()));
					  IBaseVO vo = (IBaseVO)claz.newInstance();
					  for(Iterator<?> j = eles.elementIterator(); j.hasNext();){//循环每一个元素vo
						  Map<String,String> fieldMap= Pub.getVOFieldType(vo);
						  Element ele = (Element) j.next();
						  String fname=ele.getName();
						  logger.debug(fname+" value "+ele.getText());
						  setValue(claz, ele, Pub.firstLetterToUpper(fname), vo, fieldMap);//给vo赋值
						  if(ele.elements().size()>0){
							  callBackXml(claz, ele, Pub.firstLetterToUpper(fname), ele.elements(),vo);//如果有下级就递归
						  }
					  }
					  list.add(vo);
				 }

			 }
			 Method m =clazz .getMethod("set"+fiendName, new Class[]{List.class});
			 m.invoke(basevo, new Object[]{list});
		}
	}
	
	/***
	 * 给属性添加值
	 * @param clazz
	 * @param element
	 * @param fiendName
	 * @param vo
	 * @param fieldMap
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void setValue(Class<?> clazz, Element element,String fiendName, IBaseVO vo, Map<String, String> fieldMap)throws NoSuchMethodException, IllegalAccessException,InvocationTargetException {
		if(fieldMap.containsKey(fiendName)){//判断xml中是否在这个字段
		    	String fiendType=fieldMap.get(fiendName);
		    	if("INT".equalsIgnoreCase(fiendType)){
		    		Method m =clazz .getMethod("set"+fiendName, new Class[]{int.class});
					m.invoke(vo, new Object[]{Integer.valueOf(element.getText())});
				}else if(fiendType.equalsIgnoreCase("BigDecimal")){
					Method m =clazz .getMethod("set"+fiendName, new Class[]{BigDecimal.class});
					m.invoke(vo, new Object[]{new BigDecimal(element.getText())});
				}
		    	else if("List".equalsIgnoreCase(fiendType)){
				}else{
					Method m = clazz.getMethod("set"+fiendName, new Class[]{String.class});
					m.invoke(vo, new Object[]{element.getText()});
				}
		    }
	}
	
	
	/***
	 * 
	 * @param vo 需要转化的vo
	 * @param map ：用于别名的转化
	 * @param action:业务类型 U:修改,A:新增
	 * @return 返回xml
	 * @throws Exception
	 */
	public static String toXML(IBaseVO vo,Map<String,String> map,String action)throws Exception {
		 Document document = DocumentHelper.createDocument();
		 Element root = document.addElement(vo.getRootTag());
		 if(null!=action){
			 root.addAttribute("action", action);
		 }
		 callBackVO(vo, map, root);
		return document.asXML();
	}


	/***
	 * 回调用于多层迭代
	 * @param vo 实体类
	 * @param map  ：用于别名的转化
	 * @param root ： 根节点
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void callBackVO(IBaseVO vo, Map<String, String> map,Element root) throws NoSuchMethodException, IllegalAccessException,InvocationTargetException {
		Map<String,String> fieldMap=Pub.getVOFieldType(vo);
		 for(String key:fieldMap.keySet()){
			 String value=fieldMap.get(key);
			 if(value.equalsIgnoreCase("List")){
				 logger.debug("字段类型："+value);
				 Method m = vo.getClass().getMethod("get"+key, new Class[0]);
				 Object obj = m.invoke(vo, new Object[0]);
				 @SuppressWarnings("unchecked")
				 List<IBaseVO>  taskList = (ArrayList<IBaseVO>)obj;
				 if(taskList!=null&&taskList.size()>0){
					 Element element=root.addElement(key.toLowerCase());
					 for(IBaseVO base:taskList){
						 String tag=base.getRootTag();
						 if(tag==null){
							 callBackVO(base, map, element);
						 }else{
							 callBackVO(base, map, element.addElement(tag));
						 }
					 }
				 }
			 }else{
				 logger.debug("字段类型："+value);
				 addChildElement(vo, map, root, key);
			 }
		 }
	}


	/***
	 * 添加子节点
	 * @param vo java实体类
	 * @param map 需要转换的字段
	 * @param root 根节点
	 * @param key 字段编码
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void addChildElement(IBaseVO vo, Map<String, String> map,Element root, String key) throws NoSuchMethodException,IllegalAccessException, InvocationTargetException {
		Method m = vo.getClass().getMethod("get"+key, new Class[0]);
		 Object obj = m.invoke(vo, new Object[0]);
		 if(obj!=null){
			 if(null!=map&&map.size()>0){
				 root.addElement(map.get(key.toLowerCase())==null?key:map.get(key.toLowerCase())).addText(obj.toString());
			 }
			 root.addElement(key.toLowerCase()).addText(obj.toString());
		 }
		 
//		 else{
//			 root.addElement(key.toLowerCase()).addText("");
//		 }
	}
	
	
}
