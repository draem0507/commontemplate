package org.commontemplate.standard.operator.object;

import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.BeanUtils;
import org.commontemplate.util.ClassUtils;
import org.commontemplate.util.Function;

public class DotInstanceFunctionOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public DotInstanceFunctionOperatorHandler() {
		super(NewPackage.class, Function.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		NewPackage pkg = (NewPackage)leftOperand;
		Function func = (Function)rightOperand;
		String name = func.getName();
		Class cls = Class.forName(pkg.getNewPackage().getName() + "." + name);
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
