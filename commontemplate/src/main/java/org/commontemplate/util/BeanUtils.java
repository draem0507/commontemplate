package org.commontemplate.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
				return getGetter(object.getClass(), property).invoke(object, new Object[0]);
			} catch (NoSuchMethodException e) {
				Field field = object.getClass().getField(property);
				field.setAccessible(true);
				return field.get(object);
			}
		} catch (SecurityException e) {
			throw new NoSuchPropertyException(e);
		} catch (IllegalArgumentException e) {
			throw new NoSuchPropertyException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
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
	private static Method getGetter(Class clazz, String property) throws NoSuchMethodException, SecurityException {
		Assert.assertNotNull(clazz, "BeanUtils.class.required");
		Assert.assertNotEmpty(property, "BeanUtils.property.required");
		property = property.trim();
		String upper = property.substring(0, 1).toUpperCase() + property.substring(1);
		try {
			return getGetterMethod(clazz, "get" + upper);
		} catch (NoSuchMethodException e1) {
			try {
				return getGetterMethod(clazz, "is" + upper);
			} catch (NoSuchMethodException e2) {
				return getGetterMethod(clazz, property);
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
	private static Method getGetterMethod(Class clazz, String methodName) throws NoSuchMethodException, SecurityException {
		Assert.assertNotNull(clazz, "BeanUtils.class.required");
		Assert.assertNotNull(methodName, "BeanUtils.property.required");
		Method getter = clazz.getMethod(methodName, new Class[0]);
		getter = getAccessibleMethod(getter, clazz);
		Assert.assertTrue(getter.getReturnType() != Void.class, "BeanUtils.getter.return.void"); // 后验条件
		return getter;
	}

	// 获取可访问方法
	private static Method getAccessibleMethod(Method method, Class clazz) throws NoSuchMethodException, SecurityException {
		if (method == null)
			return null;
		if (! method.isAccessible()) {
			try {
				method.setAccessible(true);
			} catch (SecurityException e) { // 当不允许关闭访问控制时, 采用向上查找公开方法
				String methodName = method.getName();
				try {
					method = searchPublicMethod(clazz, methodName);
				} catch (NoSuchMethodException e1) {
					method = searchPublicMethod(clazz.getInterfaces(), methodName);
				}
			}
		}
		return method;
	}

	// 查找公开的方法 (辅助方法，当method.setAccessible(true)失败时被调用)
	private static Method searchPublicMethod(Class[] classes, String methodName) throws NoSuchMethodException, SecurityException {
		if (classes != null && classes.length > 0) {
			for (int i = 0, n = classes.length; i < n; i ++) {
				Class cls = classes[i];
				try {
					return searchPublicMethod(cls, methodName);
				} catch (NoSuchMethodException e) {
					// ignore, continue
				}
			}
		}
		throw new NoSuchMethodException(); // 未找到方法
	}

	// 查找公开的方法 (辅助方法，当method.setAccessible(true)失败时被调用)
	private static Method searchPublicMethod(Class cls, String methodName) throws NoSuchMethodException, SecurityException {
		if (ClassUtils.isPublicClass(cls)) { // 首先保证类是公开的
			Method method = cls.getMethod(methodName, new Class[0]);
			if (ClassUtils.isPublicMethod(method)
					&& (method.getParameterTypes() == null
							|| method.getParameterTypes().length == 0)) // 再保证方法是公开的
				return method;
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
			for (Iterator iterator = getPublicPropertyMethods(object.getClass()).entrySet().iterator(); iterator.hasNext();) {
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
				return getPublicPropertyMethods(clazz).keySet();
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

	private static Map getPublicPropertyMethods(Class clazz) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map map = searchPublicPropertyMethods(clazz);
		Class[] classes = clazz.getInterfaces();
		if (classes != null && classes.length > 0) {
			for (int i = 0, n = classes.length; i < n; i ++) {
				Class cls = classes[i];
				map.putAll(searchPublicPropertyMethods(cls));
			}
		}
		return map;
	}

	private static Map searchPublicPropertyMethods(Class cls) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map map = new HashMap();
		if (cls != null && ClassUtils.isPublicClass(cls)) { // 首先保证类是公开的
			Method[] methods = cls.getMethods();
			for (int i = 0, n = methods.length; i < n; i ++) {
				Method method = methods[i];
				if (ClassUtils.isPublicMethod(method) // 再保证函数是公开的
						&& method.getDeclaringClass() != Object.class
						&& (method.getParameterTypes() == null
								|| method.getParameterTypes().length == 0)) {
					String property = method.getName();
					if (property.startsWith("get")) {
						property = property.substring(3);
						String lower = property.substring(0, 1).toLowerCase() + property.substring(1);
						map.put(lower, getAccessibleMethod(method, cls));
					} else if (property.startsWith("is")) {
						property = property.substring(2);
						String lower = property.substring(0, 1).toLowerCase() + property.substring(1);
						map.put(lower, getAccessibleMethod(method, cls));
					}
				}
			}
		}
		return map;
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
				return getGetter(clazz, property).invoke(null, new Object[0]);
			} catch (NoSuchMethodException e) {
				Field field = clazz.getField(property);
				field.setAccessible(true);
				return field.get(null);
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
	 * 设置对象的属性值
	 *
	 * @param object 对象实例
	 * @param property 属性名
	 * @param value 待设置属性的值
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void setProperty(Object object, String property, Object value) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (object == null)
			return;
		Method setter = getSetter(object.getClass(), property, value);
		setter = getAccessibleMethod(setter, object.getClass());
		setter.invoke(object, new Object[]{value});
	}

	/**
	 * 设置对象的多个属性值
	 *
	 * @param object 对象实例
	 * @param properties 属性集合
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void setProperties(Object object, Map properties) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (object == null)
			return;
		for (Iterator iterator = properties.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry)iterator.next();
			String property = (String)entry.getKey();
			Object value = entry.getValue();
			Method setter = getSetter(object.getClass(), property, value);
			setter.invoke(object, new Object[]{value});
		}
	}

	/**
	 * 查找setXXX的属性Setter方法
	 *
	 * @param clazz 类元
	 * @param property 属性名
	 * @return 属性Setter方法
	 * @throws NoSuchMethodException Setter不存时抛出
	 */
	private static Method getSetter(Class clazz, String property, Object value) throws NoSuchMethodException, SecurityException {
		if (clazz == null)
			throw new java.lang.NullPointerException("类元不能为空！");
		if (property == null)
			throw new java.lang.NullPointerException("属性名不能为空！");
		property = property.trim();
		String upper = property.substring(0, 1).toUpperCase() + property.substring(1);
		String setter = "set" + upper;
		Method method = ClassUtils.getMethod(clazz, setter, new Object[]{value});
		return getAccessibleMethod(method, clazz);
	}

}
