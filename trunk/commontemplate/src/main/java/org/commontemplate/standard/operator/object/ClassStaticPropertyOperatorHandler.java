package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.BeanUtils;
import org.commontemplate.util.ClassUtils;

public class ClassStaticPropertyOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ClassStaticPropertyOperatorHandler() {
		super(String.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		String name = (String)operand;
		try {
			return ClassUtils.forName(name);
		} catch (ClassNotFoundException e) {
			int i = name.lastIndexOf('.');
			if (i >= 0 && i < name.length()) {
				String className = name.substring(0, i);
				String property = name.substring(i + 1);
				Class clazz = ClassUtils.forName(className);
				return BeanUtils.getStaticProperty(clazz, property);
			}
			throw e;
		}
	}

}