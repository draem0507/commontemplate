package org.commontemplate.standard.function.string;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.function.FunctionHandler;
import org.commontemplate.util.ArgumentUtils;
import org.commontemplate.util.BeanUtils;
import org.commontemplate.util.ClassUtils;

public class StringNewInstanceFunctionHandler implements FunctionHandler, Serializable {

	private static final long serialVersionUID = 1L;

	public Object doFunction(Object bean, Object args) throws Exception {
		String name = (String)bean;
		Class cls = ClassUtils.forName(name);
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
			return ClassUtils.invokeConstructor(cls, ArgumentUtils.getArgumentArray(args));
		}
	}

}
