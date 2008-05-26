package org.commontemplate.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 类元处理工具类
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
		Assert.assertNotNull(className, "ClassUtils.class.name.required");
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
		Assert.assertNotNull(className, "ClassUtils.class.name.required");
		if (className.endsWith("[]"))
			className = "[L" + className.substring(0, className.length() - 2) + ";";
		return Class.forName(className);
	}

	/**
	 * 执行类方法
	 *
	 * @param obj 类实例
	 * @param name 方法名
	 * @param args 方法参数
	 * @return 方法返回值
	 * @throws NoSuchMethodException 方法不存在时抛出
	 */
	public static Object invokeMethod(Object obj, String name, Object[] args) throws NoSuchMethodException {
		if (obj == null || name == null)
			return null;
		try {
			Method method = getMethod(obj.getClass(), name, args);
			return method.invoke(obj, args);
		} catch (SecurityException e) {
			throw new NoSuchMethodException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new NoSuchMethodException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new NoSuchMethodException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new NoSuchMethodException(e.getMessage());
		}
	}

	/**
	 * 执行静态方法
	 *
	 * @param clazz 类元
	 * @param name 方法名
	 * @param args 方法参数
	 * @return 方法返回值
	 * @throws NoSuchMethodException 方法不存在时抛出
	 */
	public static Object invokeStaticMethod(Class clazz, String name, Object[] args) throws NoSuchMethodException {
		if (clazz == null || name == null)
			return null;
		try {
			Method method = getMethod(clazz, name, args);
			return method.invoke(null, args);
		} catch (SecurityException e) {
			throw new NoSuchMethodException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new NoSuchMethodException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new NoSuchMethodException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new NoSuchMethodException(e.getMessage());
		}
	}

	// 获取方法
	private static Method getMethod(Class clazz, String name, Object[] args) throws NoSuchMethodException {
		if (args == null)
			args = new Object[0];
		Class[] types = new Class[args.length];
		for (int i = 0, n = args.length; i < n; i ++) {
			types[i] = (args[i] == null ? Object.class : args[i].getClass());
		}
		try {
			return clazz.getMethod(name, types);
		} catch (NoSuchMethodException e) {
			return getLikeMethod(clazz, name, types);
		}
	}

	// 获取相似方法
	private static Method getLikeMethod(Class clazz, String name, Class[] types) throws NoSuchMethodException {
		Method[] methods = clazz.getMethods();
		for (int i = 0, n = methods.length; i < n; i ++) {
			if (methods[i].getName().equals(name)
					&& methods[i].getParameterTypes().length == types.length) {
				Class[] paramTypes = methods[i].getParameterTypes();
				if (typeLikes(paramTypes, types))
					return methods[i];
			}
		}
		throw I18nExceptionFactory.createNoSuchMethodException("ClassUtils.method.no.such", new Object[]{clazz.getName(), getSignature(name, types)});
	}

	// 判断两个参数列表类型是否相似
	private static boolean typeLikes(Class[] cs1, Class[] cs2) {
		for (int j = 0, m = cs1.length; j < m; j ++) {
			Class c1 = cs1[j];
			Class c2 = cs2[j];
			if (! (c1 == c2 || (c1.isPrimitive()
					&& ((c1 == Boolean.TYPE && c2 == Boolean.class)
							|| (c1 == Byte.TYPE && c2 == Byte.class)
							|| (c1 == Character.TYPE && c2 == Character.class)
							|| (c1 == Short.TYPE && c2 == Short.class)
							|| (c1 == Integer.TYPE && c2 == Integer.class)
							|| (c1 == Long.TYPE && c2 == Long.class)
							|| (c1 == Float.TYPE && c2 == Float.class)
							|| (c1 == Double.TYPE && c2 == Double.class))))) {
				return false;
			}
		}
		return true;
	}

	// 获取函数签名
	private static String getSignature(String name, Class[] types) {
		StringBuffer signature = new StringBuffer();
		signature.append(name);
		signature.append("(");
		if (types.length > 0) {
			for (int i = 0, n = types.length; i < n; i ++) {
				signature.append(types[i].getName());
			}
		}
		signature.append(")");
		return signature.toString();
	}

}
