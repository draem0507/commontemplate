package org.commontemplate.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


/**
 * 类元处理工具类
 *
 * @author liangfei0201@163.com
 *
 */
public class ClassUtils {

	private ClassUtils(){}

	/**
	 * 是否为公开类
	 *
	 * @param clazz 类
	 * @return 是否为公开类
	 */
	public static boolean isPublicClass(Class clazz) {
		return clazz != null && (clazz.getModifiers() & Modifier.PUBLIC) == 1;
	}

	/**
	 * 是否为公开方法
	 *
	 * @param method 方法
	 * @return 是否为公开方法
	 */
	public static boolean isPublicMethod(Method method) {
		return method != null && (method.getModifiers() & Modifier.PUBLIC) == 1;
	}

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
		Class.forName(className);
		return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
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
		return forName(className);
	}

	/**
	 * 获取构造子, 或相似构造子
	 *
	 * @param clazz 类
	 * @param args 参数
	 * @return 构造子
	 * @throws NoSuchMethodException 未找到相应构造子时抛出
	 */
	public static Constructor getConstructor(Class clazz, Object[] args) throws NoSuchMethodException {
		if (args == null)
			args = new Object[0];
		Class[] types = new Class[args.length];
		for (int i = 0, n = args.length; i < n; i ++) {
			types[i] = (args[i] == null ? Object.class : args[i].getClass());
		}
		try {
			return clazz.getConstructor(types);
		} catch (NoSuchMethodException e) {
			return getLikeConstructor(clazz, types);
		}
	}

	// 获取相似方法
	private static Constructor getLikeConstructor(Class clazz, Class[] types) throws NoSuchMethodException {
		// TODO 考虑进行String转char, int转long的处理
		Constructor[] constructors = clazz.getConstructors();
		for (int i = 0, n = constructors.length; i < n; i ++) {
			Constructor constructor = constructors[i];
			Class[] paramTypes = constructor.getParameterTypes();
			if (paramTypes.length == types.length) {
				if (isLikeClasses(paramTypes, types))
					return constructor;
			}
		}
		throw I18nExceptionFactory.createNoSuchMethodException("ClassUtils.constructor.no.such", new Object[]{clazz.getName(), getSignature(clazz.getName(), types)});
	}

	/**
	 * 获取方法, 或相似方法
	 *
	 * @param clazz 类
	 * @param name 方法名
	 * @param args 参数
	 * @return 方法
	 * @throws NoSuchMethodException 未找到相应方法时抛出
	 */
	public static Method getMethod(Class clazz, String name, Object[] args) throws NoSuchMethodException {
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
		// TODO 考虑进行String转char, int转long的处理
		Method[] methods = clazz.getMethods();
		for (int i = 0, n = methods.length; i < n; i ++) {
			Method method = methods[i];
			Class[] paramTypes = method.getParameterTypes();
			if (method.getName().equals(name)
					&& paramTypes.length == types.length) {
				if (isLikeClasses(paramTypes, types))
					return method;
			}
		}
		throw I18nExceptionFactory.createNoSuchMethodException("ClassUtils.method.no.such", new Object[]{clazz.getName(), getSignature(name, types)});
	}

	// 判断两个参数列表类型是否相似
	private static boolean isLikeClasses(Class[] cs1, Class[] cs2) {
		for (int j = 0, m = cs1.length; j < m; j ++) {
			Class c1 = cs1[j]; // 前面已作判断，c1, c2都不为空
			Class c2 = cs2[j];
			if (! isLikeClass(c1, c2))
				return false;
		}
		return true;
	}

	// 判断两个参数列表类型是否相似
	private static boolean isLikeClass(Class c1, Class c2) {
		if (c1 == c2)
			return true;
		if (c1 == Object.class)
			return true;
		if (c2 == null)
			return true;
		if (c1 == null)
			return false;
		if (c1.isAssignableFrom(c2))
			return true;
		if (c1.isPrimitive()
				&& isLikePrimitiveClass(c1, c2))
			return true;
		if (c2.isPrimitive()
				&& isLikePrimitiveClass(c2, c1))
			return true;
		return false;
	}

	// 判断基本类型是否相似
	private static boolean isLikePrimitiveClass(Class c1, Class c2) {
		return (c1 == Boolean.TYPE && c2 == Boolean.class)
				|| (c1 == Byte.TYPE && c2 == Byte.class)
				|| (c1 == Character.TYPE && c2 == Character.class)
				|| (c1 == Short.TYPE && c2 == Short.class)
				|| (c1 == Integer.TYPE && c2 == Integer.class)
				|| (c1 == Long.TYPE && c2 == Long.class)
				|| (c1 == Float.TYPE && c2 == Float.class)
				|| (c1 == Double.TYPE && c2 == Double.class);
	}

	// 获取函数签名
	private static String getSignature(String name, Class[] types) {
		StringBuffer signature = new StringBuffer();
		signature.append(name);
		signature.append("(");
		if (types.length > 0) {
			for (int i = 0, n = types.length; i < n; i ++) {
				if (i != 0)
					signature.append(", ");
				signature.append(types[i].getName());
			}
		}
		signature.append(")");
		return signature.toString();
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
	public static Object invokeConstructor(Class clazz, Object[] args) throws NoSuchMethodException {
		if (clazz == null)
			return null;
		try {
			Constructor constructor = getConstructor(clazz, args);
			return constructor.newInstance(args);
		} catch (IllegalArgumentException e) {
			throw new NoSuchMethodException(e.getMessage());
		} catch (InstantiationException e) {
			throw new NoSuchMethodException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new NoSuchMethodException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new NoSuchMethodException(e.getMessage());
		}
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

}
