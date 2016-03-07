package cn.telling.utils;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**   
 * @Title: LogUtils.java 
 * @Package cn.telling.utils 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2015-12-16 上午11:55:42 
 * @version V1.0   
 */
public class LogUtils {

		/**
		 * 核心工具
		 * @param level
		 * @param messager
		 */
		private static void _log(String level, Object messager) {
			Class<?> logClass = null;
			try {
				logClass = Class.forName("org.apache.log4j.Logger");
			} catch (ClassNotFoundException e) {
				return;
			}
			Method method = ReflationUtils.getMethod(logClass, "getLogger",String.class);
			Object instance = ReflationUtils.invoke(null, method,new Throwable().getStackTrace()[2].getClassName());
			method = ReflationUtils.getMethod(logClass, level, Object.class);
			ReflationUtils.invoke(instance, method, messager);
		}
		
	

		/**
		 * Debug日记
		 * @param messager
		 */
		public static void debug(Object messager) {
			_log("debug", messager);
		}

		/**
		 * info日记
		 * @param messager
		 */
		public static void info(Object messager) {
			_log("info", messager);
		}

		/**
		 * warn日记
		 * @param messager
		 */
		public static void warn(Object messager) {
			_log("warn", messager);
		}

		/**
		 * error日记
		 * @param messager
		 */
		public static void error(Object messager) {
			_log("error", messager);
		}

		/**
		 * fatal日记
		 * @param messager
		 */
		public static void fatal(Object messager) {
			_log("fatal", messager);
		}
		
		/**封装slf4j
		 * 
		 *使用{}占位符  */
	    public static <T> Logger Log(Class<T> cls) {
	        return LoggerFactory.getLogger(cls);
	    }
	    
	}
