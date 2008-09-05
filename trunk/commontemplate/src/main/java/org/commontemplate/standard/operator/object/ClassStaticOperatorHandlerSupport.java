package org.commontemplate.standard.operator.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.BeanUtils;
import org.commontemplate.util.ClassUtils;

public abstract class ClassStaticOperatorHandlerSupport extends UnaryOperatorHandlerSupport{

	private Map propertyHandlers;
	private Map methodHandlers;
	
	public ClassStaticOperatorHandlerSupport(Class operandClass) {
		super(operandClass);
	}
	
	public void setPropertyHandlers(Map propertyHandlers) {
		this.propertyHandlers = propertyHandlers;
	}

	public void setMethodHandlers(Map methodHandlers) {
		this.methodHandlers = methodHandlers;
	}

	public Object doEvaluate(Object operand) throws Exception {
		String operandName = getOperandName(operand);
		Object argument = getOperandArgument(operand);
		//需要得到的变量，类属性，表达式组
		String className = operandName;
		Class clazz=safeForName(className);
		List properties=new ArrayList();
		
		int index=-1;
		int lastIndex = operandName.length();
		while(clazz==null && (index = className.lastIndexOf('.'))!=-1){
			className = className.substring(0,index);
			properties.add(operandName.substring(index+1,lastIndex));
			clazz = safeForName(className);
			lastIndex = index;
		}
		Object result = null;
		//TODO: propertyHandlers support,methodHanlers support?
		if(properties.size()==1){
			if(operand instanceof String)
				result = BeanUtils.getStaticProperty(clazz, (String)properties.get(0));
			else
				result = ObjectHandlerUtils.invokeStaticMethod(clazz, (String)properties.get(0),methodHandlers, argument);
		}else if(properties.size() > 1){
			result = ObjectHandlerUtils.invokeStaticProperty(clazz, (String)properties.get(properties.size() -1 ),propertyHandlers);
			for(int i=properties.size()-2;i>0;i--){
				result = ObjectHandlerUtils.invokeProperty(result, (String)properties.get(i), propertyHandlers);
			}
			if(operand instanceof String)
				result = ObjectHandlerUtils.invokeProperty(result, (String)properties.get(0), propertyHandlers);
			else
				result = ObjectHandlerUtils.invokeMethod(result, (String)properties.get(0), methodHandlers, argument);
		}else{
			// &org.xxx.Some 返回 clazz
			if(operand instanceof String)
				return clazz;
			// &org.xxx.Some(a,b) 返回实例
			return ObjectHandlerUtils.invokeConstructor(clazz, argument);
		}
		return result;
	}
	
	protected abstract String getOperandName(Object operand);
	
	protected Object getOperandArgument(Object operand){
		return null;
	}
	
	private Class safeForName(String className){
		try {
			return ClassUtils.forName(className);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
}
