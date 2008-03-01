package org.commontemplate.standard.operator.object;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.UnhandleException;
import org.commontemplate.standard.property.PropertyHandler;
import org.commontemplate.standard.property.PropertyMatcher;
import org.commontemplate.util.ClassUtils;

public class ObjectPropertyOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	private Map propertyHandlers;
	
	public ObjectPropertyOperatorHandler() {
		super(Object.class, String.class, true);
	}

	public void setPropertyHandlers(Map handlers) {
		this.propertyHandlers = new HashMap(handlers.size());
		for (Iterator iterator = handlers.entrySet().iterator(); iterator.hasNext();) {
			Entry entry = (Entry)iterator.next();
			propertyHandlers.put(new PropertyMatcher((String)entry.getKey()), entry.getValue());
		}
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (rightOperand == null)
			return null;
		
		String property = (String)rightOperand;
		if (leftOperand == null) { // 允许调用null.toString
			if ("toString".equals(property))
				return "null";
			return null;
		}
			
		if (propertyHandlers != null) {
			for (Iterator iterator = propertyHandlers.entrySet().iterator(); iterator.hasNext();) {
				Entry entry = (Entry)iterator.next();
				if (((PropertyMatcher)entry.getKey()).isMatch(leftOperand.getClass(), property)) {
					return ((PropertyHandler)entry.getValue()).handleProperty(leftOperand);
				}
			}
		}
		
		try {
			return ClassUtils.getObjectProperty(leftOperand, property);
		} catch (Exception e) {
			throw new UnhandleException("在 " + leftOperand.getClass().getName() + " 中，没有找到属性 " + property + " 的相关取值函数！", e);
		}
	}

}