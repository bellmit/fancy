package cn.telling.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * ��������������������
 * 
 * @author ITspas
 *
 */
public class Assert {

	/**
	 * �����������Null
	 * 
	 * @param objects
	 * @return
	 */
	public static boolean isNull(Object... objects) {
		if (objects == null)
			return true;
		for (Object object : objects) {
			if (object == null)
				return true;
		}
		return false;
	}

	/**
	 * �����������
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean isContain(String source, String content) {
		if (isNull(source, content))
			return false;
		return source.indexOf(content) > -1;
	}

	/**
	 * ��������������������������
	 * 
	 * @param clazz
	 * @param object
	 * @return
	 */
	public static boolean isInstanceOf(Class<?> clazz, Object object) {
		if (isNull(clazz, object))
			return false;
		return clazz.isInstance(object);
	}

	/**
	 * �����������Key
	 * 
	 * @param source
	 * @param keys
	 * @return
	 */
    @SafeVarargs
    public static <K, M extends Map<K, ?>> boolean isContainKey(M source, K... keys) {
		if (isNull(source, keys))
			return false;
		for (K key : keys) {
			if (!source.containsKey(key))
				return false;
		}
		return true;
	}

	/**
	 * �����������Value
	 * 
	 * @param source
	 * @param values
	 * @return
	 */
    @SafeVarargs
    public static <V, M extends Map<?, V>> boolean isContainValue(M source, V... values) {
		if (isNull(source, values))
			return false;
		for (V value : values) {
			if (!source.containsValue(value))
				return false;
		}
		return true;
	}

	/**
	 * �����������
	 * 
	 * @param source
	 * @param args
	 * @return
	 */
	public static <L extends List<?>> boolean isContain(L source, Object... args) {
		if (isNull(source, args))
			return false;
		for (Object object : args) {
			if (!source.contains(object))
				return false;
		}
		return true;
	}

	/**
	 * �����������
	 * 
	 * @param source
	 * @param args
	 * @return
	 */
	@SafeVarargs
    public static <T> boolean isContain(T[] source, T... args) {
		if (isNull(source, args))
			return false;
		for (Object object : args) {
			if (Arrays.binarySearch(source, object) < 0)
				return false;
		}
		return true;
	}

	/**
	 * ����������������
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object) {
		return !Assert.notEmpty(object);
	}

	/**
	 * ��������������
	 * 
	 * @param object
	 * @return
	 */
	public static boolean notEmpty(Object object) {
		if (Assert.isNull(object))
			return false;
		else if (String.class.isInstance(object))
			return !((String) object).isEmpty();
		else if (object.getClass().isArray())
			return ((Object[]) object).length > 0;
		else if (object instanceof Collection<?>)
			return ((Collection<?>) object).size() > 0;
		else if (object instanceof Map<?, ?>)
			return ((Map<?, ?>) object).size() > 0;
		else if (object instanceof Integer) {
			return !((Integer) object > 0);
		} else
			return true;
	}
}
