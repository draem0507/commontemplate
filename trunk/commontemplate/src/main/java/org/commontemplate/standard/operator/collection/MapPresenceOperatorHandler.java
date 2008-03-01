package org.commontemplate.standard.operator.collection;

import java.util.Map;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

public class MapPresenceOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MapPresenceOperatorHandler() {
		super(Map.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		Map map = (Map)operand;
		return Boolean.valueOf(map != null && map.size() > 0);
	}

}