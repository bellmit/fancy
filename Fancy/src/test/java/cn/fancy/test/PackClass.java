package cn.fancy.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

/**   
 * @Title: PackClass.java 
 * @Package cn.fancy.test 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2017年3月31日 下午3:11:51 
 * @version V1.0   
 */
public class PackClass {

	public static void main(String[] args) {
		List<Short>sl=new ArrayList<Short>();
		Short s1=new Short((short) 1);
		Short s2=new Short((short) 2);
		sl.add(s1);
		sl.add(s2);
			Short []ss=sl.toArray(new Short[sl.size()]);
			short []ssss=ArrayUtils.toPrimitive(ss);
			System.out.println(ssss);;
}
}

