package org.commontemplate.standard.operator.object;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.UnhandleException;
import org.commontemplate.standard.property.PropertyMatcher;
import org.commontemplate.standard.property.StaticPropertyHandler;
import org.commontemplate.util.BeanUtils;
import org.commontemplate.util.NoSuchPropertyException;

public class StaticPropertyOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StaticPropertyOperatorHandler() {
		super(Class.class, String.class);
	}

	private Map propertyHandlers;

	public void setStaticPropertyHandlers(Map handlers) {
		this.propertyHandlers = new HashMap(handlers.size());
		for (Iterator iterator = handlers.entrySet().iterator(); iterator.hasNext();) {
			Entry entry = (Entry)iterator.next();
			propertyHandlers.put(new PropertyMatcher((String)entry.getKey()), entry.getValue());
		}
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		Class clazz = (Class)leftOperand;
		String property = (String)rightOperand;
		if (propertyHandlers != null) {
			for (Iterator iterator = propertyHandlers.entrySet().iterator(); iterator.hasNext();) {
				Entry entry = (Entry)iterator.next();
				if (((PropertyMatcher)entry.getKey()).isMatch(leftOperand.getClass(), property)) {
					return ((StaticPropertyHandler)entry.getValue()).doProperty(clazz);
				}
			}
		}
		try {
			return BeanUtils.getStaticProperty(clazz, property);
		} catch (NoSuchPropertyException e) {
			throw new UnhandleException("ObjectPropertyOperatorHandler.property.no.such", new Object[]{leftOperand.getClass().getName(), property});
		}
	}


}
