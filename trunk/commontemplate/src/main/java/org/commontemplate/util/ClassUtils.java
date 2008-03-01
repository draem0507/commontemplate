package org.commontemplate.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类工具
 * 
 * @author liangfei0201@163.com
 *
 */
public class ClassUtils {
	
	private ClassUtils(){}
	
	/**
	 * 通过类名反射得到类元.
	 * 使用的类名应该和<code>java.lang.Class#getName()</code>一致
	 * 
	 * @see java.lang.Class#getName()
	 * @param className 类名
	 * @return 类元
	 * @throws ClassNotFoundException 类不存在时抛出
	 */
	public static Class forName(String className) throws ClassNotFoundException {
		Assert.assertNotNull(className, "className不能为空！");
		return Class.forName(className);
	}
	
	/**
	 * 通过标准类名反射得到类元.
	 * <p/>
	 * 与forName主要区别在于数组名使用："java.lang.Object[]"，在forName时使用："[Ljava.lang.Object;"
	 *  
	 * @param className 标准类名
	 * @return 类元
	 * @throws ClassNotFoundException 类不存在时抛出
	 */
	public static Class forCanonicalName(String className) throws ClassNotFoundException {
		Assert.assertNotNull(className, "className不能为空！");
		if (className.endsWith("[]"))
			className = "[L" + className.substring(0, className.length() - 2) + ";";
		return Class.forName(className);
	}

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
	public static Object getObjectProperty(Object object, String property) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
		if (object == null)
			return null;
		try {
			return getClassGetter(object.getClass(), property).invoke(object, new Object[0]);
		} catch (NoSuchMethodException e) {
			return object.getClass().getField(property).get(object);
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
	public static Method getClassGetter(Class clazz, String property) throws NoSuchMethodException, SecurityException {
		Assert.assertNotNull(clazz, "class不能为空！");
		Assert.assertNotEmpty(property, "property不能为空！");
		property = property.trim();
		String upper = property.substring(0, 1).toUpperCase() + property.substring(1);
		try {
			Method getter = getClassMethod(clazz, "get" + upper);
			Assert.assertTrue(getter.getReturnType() != Void.class, "getter返回值类型不能为void！");
			return getter;
		} catch (NoSuchMethodException e1) {
			try {
				Method getter = getClassMethod(clazz, "is" + upper);
				Assert.assertTrue(getter.getReturnType() != Void.class, "getter返回值类型不能为void！");
				return getter;
			} catch (NoSuchMethodException e2) {
				Method getter = getClassMethod(clazz, property);
				Assert.assertTrue(getter.getReturnType() != Void.class, "getter返回值类型不能为void！");
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
	public static Method getClassMethod(Class clazz, String methodName) throws NoSuchMethodException, SecurityException {
		Assert.assertNotNull(clazz, "class不能为空！");
		Assert.assertNotNull(methodName, "methodName不能为空！");
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
						if ((method.getModifiers() & Modifier.PUBLIC) == 1) // 再保证方法是公开的
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
	 * 获取对象的多个属性值
	 * 
	 * @param object 对象实例
	 * @param properties 属性列表
	 * @return 属性集合
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchFieldException 
	 */
	public static Map getObjectProperties(Object object, String[] properties) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
		if (object == null || properties == null)
			return null;
		Map map = new HashMap(properties.length);
		for (int i = 0, n = properties.length; i < n; i ++) {
			String prop = properties[i];
			if (prop != null && prop.length() > 0)
				map.put(prop, getObjectProperty(object, prop));
		}
		return map;
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
	public static Map getObjectAllProperties(Object object) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (object == null)
			return null;
		Map map = new HashMap();
		Class clazz = object.getClass();
		Method[] methods = clazz.getMethods();
		for (int i = 0, n = methods.length; i < n; i ++) {
			Method method = methods[i];
			String property = method.getName();
			if (property.startsWith("get")) {
				property = property.substring(3);
				String lower = property.substring(0, 1).toLowerCase() + property.substring(1);
				map.put(lower, method.invoke(object, new Object[0]));
			} else if (property.startsWith("is")) {
				property = property.substring(2);
				String lower = property.substring(0, 1).toLowerCase() + property.substring(1);
				map.put(lower, method.invoke(object, new Object[0]));
			}
		}
		return map;
	}
	
	public static List getObjectAllPropertyNames(Object object) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (object == null)
			return null;
		List list = new ArrayList();
		Class clazz = object.getClass();
		Method[] methods = clazz.getMethods();
		for (int i = 0, n = methods.length; i < n; i ++) {
			Method method = methods[i];
			if (method.getName().startsWith("get")) {
				list.add(method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4));
			} else if (method.getName().startsWith("is")) {
				list.add(method.getName().substring(2, 3).toLowerCase() + method.getName().substring(3));
			}
		}
		return list;
	}
	
	public static Object getStaticProperty(Class clazz, String property) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
		if (clazz == null)
			return null;
		try {
			return getClassGetter(clazz, property).invoke(null, new Object[0]);
		} catch (NoSuchMethodException e) {
			return clazz.getField(property).get(null);
		}
	}
	
	// FIXME 未处理int与Integer的区别, 以及null参数的处理. 
	public static Object getStaticFunction(Class clazz, String name, Object[] args) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
		if (clazz == null)
			return null;
		Class[] classes = new Class[args.length];
		for (int i = 0, n = args.length; i < n; i ++) {
			classes[i] = args[i].getClass();
		}
		return clazz.getMethod(name, classes).invoke(null, args);
	}


}
