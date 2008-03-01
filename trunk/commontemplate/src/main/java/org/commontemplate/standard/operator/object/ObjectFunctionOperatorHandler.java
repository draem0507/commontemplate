package org.commontemplate.standard.operator.object;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.function.FunctionHandler;
import org.commontemplate.standard.function.FunctionMatcher;
import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.Function;

public class ObjectFunctionOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ObjectFunctionOperatorHandler() {
		super(Object.class, Function.class);
	}
	
	private Map functionHandlers;
	
	/**
	 * 设置全局属性
	 * 
	 * @param handlers Map<String, FunctionHandler>
	 */
	public void setFunctionHandlers(Map handlers) {
		this.functionHandlers = new HashMap(handlers.size());
		for (Iterator iterator = handlers.entrySet().iterator(); iterator.hasNext();) {
			Entry entry = (Entry)iterator.next();
			functionHandlers.put(new FunctionMatcher((String)entry.getKey()), entry.getValue());
		}
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Function function = (Function)rightOperand;
		String functionName = function.getName();
		Object[] args = function.getArguments().toArray();
		Class[] types = new Class[args.length];
		for (int i = 0, n = args.length; i < n; i ++) {
			types[i] = (args[i] == null ? Object.class : args[i].getClass());
		}

		if (functionHandlers != null) {
			for (Iterator iterator = functionHandlers.entrySet().iterator(); iterator.hasNext();) {
				Entry entry = (Entry)iterator.next();
				if (((FunctionMatcher)entry.getKey()).isMatch(leftOperand.getClass(), function.getName())) {
					return ((FunctionHandler)entry.getValue()).handleFunction(leftOperand, function.getArguments());
				}
			}
		}
		
		try {
			Method method = getMethod(leftOperand, functionName, types);
			return method.invoke(leftOperand, args);
		} catch (Exception e) {
			throw new Exception("访问 " + leftOperand.getClass().getName() + " 的 " + getSignature(functionName, types) + " 方法失败！", e);
		}
	}

	// 获取方法
	private Method getMethod(Object model, String functionName, Class[] types) throws Exception {
		try {
			Method method = model.getClass().getMethod(functionName, types);
			return method;
		} catch (NoSuchMethodException e) {
			return getLikeMethod(model, functionName, types);
		}
	}
	
	// 获取相似方法
	private Method getLikeMethod(Object model, String functionName, Class[] types) throws Exception {
		Method[] methods = model.getClass().getMethods();
		for (int i = 0, n = methods.length; i < n; i ++) {
			if (methods[i].getName().equals(functionName)
					&& methods[i].getParameterTypes().length == types.length) {
				Class[] paramTypes = methods[i].getParameterTypes();
				if (typeLikes(paramTypes, types))
					return methods[i];
			}
		}
		throw new Exception("在 " + model.getClass().getName() + " 中, 没有找到函数: " + getSignature(functionName, types));
	}
	
	// 判断两个参数列表类型是否相似
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
	
	private String getSignature(String functionName, Class[] types) {
		StringBuffer signature = new StringBuffer();
		signature.append(functionName);
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