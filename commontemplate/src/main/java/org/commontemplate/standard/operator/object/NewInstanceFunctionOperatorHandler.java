package org.commontemplate.standard.operator.object;

import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.BeanUtils;
import org.commontemplate.util.ClassUtils;
import org.commontemplate.util.Function;

public class NewInstanceFunctionOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NewInstanceFunctionOperatorHandler() {
		super(Function.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		Function func = (Function)operand;
		String name = func.getName();
		Class cls = Class.forName(name);
		Object args = func.getArgument();
		if (args == null) {
			return cls.newInstance();
		} else if (args instanceof Map) {
			Object obj = cls.newInstance();
			BeanUtils.setProperties(obj, (Map)args);
			return obj;
		} else if (args instanceof Entry) {
			Entry entry = (Entry)args;
			Object obj = cls.newInstance();
			BeanUtils.setProperty(obj, String.valueOf(entry.getKey()), entry.getValue());
			return obj;
		} else {
			return ClassUtils.invokeConstructor(cls, func.getArguments().toArray());
		}
	}

}