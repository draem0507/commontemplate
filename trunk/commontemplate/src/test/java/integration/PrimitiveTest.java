package integration;

import java.lang.reflect.Method;
import java.util.List;

public class PrimitiveTest {
	
	public void test() {
		PrimitiveObject obj = new PrimitiveObject();
		Object o = testPrimitive("test2", obj, new Integer(1));
		System.out.println("result: " + o);
	}

	public Object testPrimitive(String name, Object leftModel, Object rightModel) {
		Object[] args;
		if (rightModel == null) {
			args = new Object[0];
		} else if (rightModel instanceof List) {
			args = ((List) rightModel).toArray();
		} else {
			args = new Object[] { rightModel };
		}
		
		Class[] types = new Class[args.length];
		for (int i = 0, n = args.length; i < n; i ++) {
			types[i] = args[i].getClass();
		}
		try{
			Method method = getMethod(leftModel, types, name);
			return method.invoke(leftModel, args);
		} catch (Exception e) {
			throw new RuntimeException("访问 " + leftModel.getClass().getName() + " 的 " + name + " 方法失败！", e);
		}
	}
	
	private Method getMethod(Object model, Class[] types, String name) {
		try {
			Method method = model.getClass().getMethod(name, types);
			return method;
		} catch (NoSuchMethodException e) {
			return getLikeMethod(model.getClass().getMethods(), types, name);
		}
	}
	
	private Method getLikeMethod(Method[] methods, Class[] types, String name) {
		for (int i = 0, n = methods.length; i < n; i ++) {
			if (methods[i].getName().equals(name)
					&& methods[i].getParameterTypes().length == types.length) {
				Class[] paramTypes = methods[i].getParameterTypes();
				if (typeLikes(paramTypes, types))
					return methods[i];
			}
		}
		throw new RuntimeException("");
	}
	
	private boolean typeLikes(Class[] cs1, Class[] cs2) {
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

	public static void main(String[] args) {
		new PrimitiveTest().test();
	}

}
