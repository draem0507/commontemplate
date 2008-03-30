package org.commontemplate.standard.operator.context;

import java.util.Map;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.standard.property.StaticPropertyHandler;

/**
 * 系统属性取值一元操作符: "."<br/>
 * 如: ${.now} $if{order.date + 3.day > .now} ${.random} ${.uuid} ${.system["os.name"]}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class StaticPropertyOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StaticPropertyOperatorHandler() {
		super(String.class);
	}

	private Map propertyHandlers;

	/**
	 * 设置全局属性
	 *
	 * @param propertyHandlers Map<String, StaticPropertyHandler>
	 */
	public void setStaticPropertyHandlers(Map propertyHandlers) {
		this.propertyHandlers = propertyHandlers;
	}

	public Object doEvaluate(Object operand) throws Exception {
		String property = (String)operand;
		if (propertyHandlers != null) {
			StaticPropertyHandler handler = (StaticPropertyHandler)propertyHandlers.get(property);
			if (handler != null)
				return handler.getProperty();
		}
		throw new RuntimeException("未找到静态属性:" + property);
	}

}