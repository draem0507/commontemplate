package org.commontemplate.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * Bean属性工具类.<br/>
 * Hack:
 * java.beans包下的相关类对实现外部接口的内部类, 匿名类等支持不友好, 故重新实现.
 *
 * @author liangfei0201@163.com
 *
 */
public class BeanUtils {

	private BeanUtils(){}

	/**
	 * 获取对象的属性值
	 *
	 * @param object 对象实例
	 * @param property 属性名
	 * @return 属性的值
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchFieldException
	 */
	public static Object getProperty(Object object, String property) throws NoSuchPropertyException {
		if (object == null)
			return null;
		try {
			try {
				return getClassGetter(object.getClass(), property).invoke(object, new Object[0]);
			} catch (NoSuchMethodException e) {
				return object.getClass().getField(property).get(object);
			}
		} catch (SecurityException e) {
			throw new NoSuchPropertyException(e);
		} catch (IllegalArgumentException e) {
			throw new NoSuchPropertyException(e);
		} catch (IllegalAccessException e) {
			throw new NoSuchPropertyException(e);
		} catch (InvocationTargetException e) {
			throw new NoSuchPropertyException(e);
		} catch (NoSuchFieldException e) {
			throw new NoSuchPropertyException(e);
		}
	}

	/**
	 * 查找getXXX与isXXX的属性Getter方法
	 *
	 * @param clazz 类元
	 * @param property 属性名
	 * @return 属性Getter方法
	 * @throws NoSuchMethodException Getter不存时抛出
	 */
	private static Method getClassGetter(Class clazz, String property) throws NoSuchMethodException, SecurityException {
		Assert.assertNotNull(clazz, "BeanUtils.class.required");
		Assert.assertNotEmpty(property, "BeanUtils.property.required");
		property = property.trim();
		String upper = property.substring(0, 1).toUpperCase() + property.substring(1);
		try {
			Method getter = getClassMethod(clazz, "get" + upper);
			Assert.assertTrue(getter.getReturnType() != Void.class, "BeanUtils.getter.return.void");
			return getter;
		} catch (NoSuchMethodException e1) {
			try {
				Method getter = getClassMethod(clazz, "is" + upper);
				Assert.assertTrue(getter.getReturnType() != Void.class, "BeanUtils.getter.return.void");
				return getter;
			} catch (NoSuchMethodException e2) {
				Method getter = getClassMethod(clazz, property);
				Assert.assertTrue(getter.getReturnType() != Void.class, "BeanUtils.getter.return.void");
				return getter;
			}
		}
	}

	/**
	 * 获取类的方法 (保证返回方法的公开性)
	 *
	 * @param clazz 类
	 * @param methodName 方法名
	 * @return 公开的方法
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private static Method getClassMethod(Class clazz, String methodName) throws NoSuchMethodException, SecurityException {
		Assert.assertNotNull(clazz, "BeanUtils.class.required");
		Assert.assertNotNull(methodName, "BeanUtils.property.required");
		try {
			return searchPublicMethod(clazz.getInterfaces(), methodName);
		} catch (NoSuchMethodException e1) {
			try {
				return searchPublicMethod(clazz.getClasses(), methodName);
			} catch (NoSuchMethodException e2) {
				return clazz.getMethod(methodName, new Class[0]);
			}
		}
	}

	// 查找公开的方法 (辅助方法)
	private static Method searchPublicMethod(Class[] classes, String methodName) throws NoSuchMethodException, SecurityException {
		if (classes != null && classes.length > 0) {
			for (int i = 0, n = classes.length; i < n; i ++) {
				Class cls = classes[i];
				if ((cls.getModifiers() & Modifier.PUBLIC) == 1) { // 首先保证类是公开的
					try {
						Method method = cls.getMethod(methodName, new Class[0]);
						if ((method.getModifiers() & Modifier.PUBLIC) == 1
								&& (method.getParameterTypes() == null
										|| method.getParameterTypes().length == 0)) // 再保证方法是公开的
							return method;
					} catch (NoSuchMethodException e) {
						// ignore, continue
					}
				}
			}
		}
		throw new NoSuchMethodException(); // 未找到方法
	}

	/**
	 * 获取对象的所有属性值
	 *
	 * @param object 对象实例
	 * @return 属性集合
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Map getProperties(Object object) {
		if (object == null)
			return null;
		Map map = new HashMap();
		try {
			for (Iterator iterator = getPropertyMethods(object.getClass()).entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				map.put(entry.getKey(), ((Method)entry.getValue()).invoke(object, new Object[0]));
			}
		} catch (SecurityException e) {
			// ignore
		} catch (IllegalArgumentException e) {
			// ignore
		} catch (NoSuchMethodException e) {
			// ignore
		} catch (IllegalAccessException e) {
			// ignore
		} catch (InvocationTargetException e) {
			// ignore
		}
		return map;
	}

	public static Set getPropertyNames(Class clazz) {
		try {
			if (clazz != null)
				return getPropertyMethods(clazz).keySet();
		} catch (SecurityException e) {
			// ignore
		} catch (IllegalArgumentException e) {
			// ignore
		} catch (NoSuchMethodException e) {
			// ignore
		} catch (IllegalAccessException e) {
			// ignore
		} catch (InvocationTargetException e) {
			// ignore
		}
		return null;
	}

	private static Map getPropertyMethods(Class clazz) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map map = new HashMap();
		if (clazz != null) {
			if ((clazz.getModifiers() & Modifier.PUBLIC) == 1) { // 首先保证类是公开的
				Method[] methods = clazz.getMethods();
				for (int i = 0, n = methods.length; i < n; i ++) {
					Method method = methods[i];
					if ((method.getModifiers() & Modifier.PUBLIC) == 1 // 再保证函数是公开的
							&& method.getDeclaringClass() != Object.class
							&& (method.getParameterTypes() == null
									|| method.getParameterTypes().length == 0)) {
						String property = method.getName();
						if (property.startsWith("get")) {
							property = property.substring(3);
							String lower = property.substring(0, 1).toLowerCase() + property.substring(1);
							map.put(lower, method);
						} else if (property.startsWith("is")) {
							property = property.substring(2);
							String lower = property.substring(0, 1).toLowerCase() + property.substring(1);
							map.put(lower, method);
						}
					}
				}
			} else { // 如果当前类不是公开的, 查找其父类与接口
				map.putAll(searchPropertyMethods(clazz.getInterfaces()));
				map.putAll(searchPropertyMethods(clazz.getClasses()));
			}
		}
		return map;
	}

	private static Map searchPropertyMethods(Class[] classes) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map all = new HashMap();
		if (classes != null && classes.length > 0) {
			for (int i = 0, n = classes.length; i < n; i ++) {
				Class clazz = classes[i];
				all.putAll(getPropertyMethods(clazz));
			}
		}
		return all;
	}

	/**
	 * 获取静态属性的值
	 *
	 * @param clazz 类
	 * @param property 属性名
	 * @return 值
	 * @throws NoSuchPropertyException 当属性不存在时抛出
	 */
	public static Object getStaticProperty(Class clazz, String property) throws NoSuchPropertyException {
		if (clazz == null)
			return null;
		try {
			try {
				return getClassGetter(clazz, property).invoke(null, new Object[0]);
			} catch (NoSuchMethodException e) {
				return clazz.getField(property).get(null);
			}
		} catch (SecurityException e) {
			throw new NoSuchPropertyException(e);
		} catch (IllegalArgumentException e) {
			throw new NoSuchPropertyException(e);
		} catch (IllegalAccessException e) {
			throw new NoSuchPropertyException(e);
		} catch (InvocationTargetException e) {
			throw new NoSuchPropertyException(e);
		} catch (NoSuchFieldException e) {
			throw new NoSuchPropertyException(e);
		}
	}

}
