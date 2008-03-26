package org.commontemplate.standard.operator.collection;

import java.util.Map;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

/**
 * Map是否有值<br/>
 * 如: $if{?map}<br/>
 * TODO 待考虑是否需要此操作符<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class MapPresenceOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MapPresenceOperatorHandler() {
		super(Map.class, true);
	}

	public Object doEvaluate(Object operand) throws Exception {
		Map map = (Map)operand;
		return Boolean.valueOf(map != null && map.size() > 0);
	}

}