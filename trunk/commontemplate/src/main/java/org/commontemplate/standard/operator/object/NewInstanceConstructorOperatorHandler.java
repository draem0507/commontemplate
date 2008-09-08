package org.commontemplate.standard.operator.object;

import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.util.ArgumentUtils;
import org.commontemplate.util.BeanUtils;
import org.commontemplate.util.ClassUtils;
import org.commontemplate.util.Function;

/**
 * 类构造函数处理器.
 * 如:
 * new com.xxx.User()
 * 或者:
 * new com.xxx.User(1, "james")
 * 或者:
 * new com.xxx.User(id: 1, name: "james")
 * 或者:
 * new com.xxx.User.xxx(aaa, bbb)
 *
 * @author Guileen
 * @author liangfei0201@163.com
 *
 */
public class NewInstanceConstructorOperatorHandler extends NewInstanceOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NewInstanceConstructorOperatorHandler() {
		super(Function.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		Function function = (Function)operand;
		String name = function.getName();
		Object args = function.getArgument();
		try {
			Class cls = ClassUtils.forName(name);
			if (args == null) {
				return cls.newInstance();
			} else if (args instanceof Map) {
				Object obj = cls.newInstance();
				BeanUtils.setProperties(obj, (Map)args);
				return obj;
			} else if (args instanceof Entry) {
				Object obj = cls.newInstance();
				Entry entry = (Entry)args;
				BeanUtils.setProperty(obj, String.valueOf(entry.getKey()), entry.getValue());
				return obj;
			} else {
				return ClassUtils.invokeConstructor(cls, ArgumentUtils.getArgumentArray(args));
			}
		} catch (ClassNotFoundException e) {
			return super.doEvaluateFunction((Function)operand);
		}
	}

}