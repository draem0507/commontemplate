package org.commontemplate.standard.operator.object;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.function.FunctionHandler;
import org.commontemplate.standard.function.FunctionMatcher;
import org.commontemplate.standard.operator.UnhandleException;
import org.commontemplate.standard.property.PropertyHandler;
import org.commontemplate.standard.property.PropertyMatcher;
import org.commontemplate.util.ArgumentUtils;
import org.commontemplate.util.BeanUtils;
import org.commontemplate.util.ClassUtils;

public class ObjectHandlerUtils {

	public static Object invokeProperty(Object left,String right,Map propertyHandlers) throws Exception{
		if (left == null) { // 允许调用null.toString
			if ("toString".equals(right))
				return "null";
			return null;
		}

		if (propertyHandlers != null) {
			for (Iterator iterator = propertyHandlers.entrySet().iterator(); iterator.hasNext();) {
				Entry entry = (Entry)iterator.next();
				if (((PropertyMatcher)entry.getKey()).isMatch(left.getClass(), right)) {
					return ((PropertyHandler)entry.getValue()).doProperty(left);
				}
			}
		}

		try {
			return BeanUtils.getProperty(left, right);
		} catch (Exception e) {
			throw new UnhandleException("ObjectPropertyOperatorHandler.property.no.such", new Object[]{left.getClass().getName(), right});
		}
	}
	
	public static Object invokeMethod(Object left,String right,Map functionHandlers,Object argument) throws Exception{

		if (functionHandlers != null) {
			for (Iterator iterator = functionHandlers.entrySet().iterator(); iterator.hasNext();) {
				Entry entry = (Entry)iterator.next();
				if (((FunctionMatcher)entry.getKey()).isMatch(left.getClass(), right)) {
					return ((FunctionHandler)entry.getValue()).doFunction(left, argument);
				}
			}
		}

		try {
			return ClassUtils.invokeMethod(left, right, ArgumentUtils.getArgumentArray(argument));
		} catch (NoSuchMethodException e) {
			throw new UnhandleException("ObjectFunctionOperatorHandler.function.no.such", new Object[]{left.getClass().getName(), right});
		}
	}
	
	public static Object invokeStaticMethod(Class left,String right,Map functionHandlers,Object argument) throws NoSuchMethodException{
		//TODO: use functionHandlers? 
		return ClassUtils.invokeStaticMethod(left, right, ArgumentUtils.getArgumentArray(argument));
	}
	
	public static Object invokeStaticProperty(Class left,String right,Map propertyHandlers){
		//TODO: use propertyHandlers?
		return BeanUtils.getStaticProperty(left, right);
	}
	
	public static Object invokeConstructor(Class clazz,Object argument) throws InstantiationException, IllegalAccessException, SecurityException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException{
		if (argument == null) {
			return clazz.newInstance();
		}  
		try{
			return ClassUtils.invokeConstructor(clazz, new Object[]{argument});
		}catch(Exception e){
			if (argument instanceof Map) {
				Object obj = clazz.newInstance();
				BeanUtils.setProperties(obj, (Map)argument);
				return obj;
			} else if (argument instanceof Entry) {
				Entry entry = (Entry)argument;
				Object obj = clazz.newInstance();
				BeanUtils.setProperty(obj, String.valueOf(entry.getKey()), entry.getValue());
				return obj;
			} else {
				return ClassUtils.invokeConstructor(clazz, ArgumentUtils.getArgumentArray(argument));
			}
		}
	}
	
}
 