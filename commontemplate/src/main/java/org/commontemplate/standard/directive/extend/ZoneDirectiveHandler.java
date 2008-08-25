package org.commontemplate.standard.directive.extend;

import java.util.List;

import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.core.Context;

/**
 * 模板区域定义指令. 用于将模板分区, 便于子模板重写.
 *
 * @author liangfei0201@163.com
 *
 */
public class ZoneDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public static final String ZONE_TYPE = "zone";

	protected void doRender(Context context, String directiveName,
			Object param, List innerElements)
			throws Exception {
		Boolean isInner = (Boolean)context.getProperty(ExtendDirectiveHandler.EXTEND_INNER_PROPERTY);
		if (isInner != null && isInner.booleanValue()) {
			String zoneName = (String)param;
			Zone childZone = (Zone)context.getProperty(ZoneDirectiveHandler.ZONE_TYPE, zoneName);
			Zone innerZone = new Zone(innerElements);
			if (childZone == null) {
				context.getParentLocalContext().putProperty(ZoneDirectiveHandler.ZONE_TYPE, zoneName, innerZone);
			} else {
				childZone.setSuperZone(innerZone);
			}
		} else {
			String zoneName = (String)param;
			Zone childZone = (Zone)context.getProperty(ZoneDirectiveHandler.ZONE_TYPE, zoneName);
			Zone innerZone = new Zone(innerElements);
			if (childZone == null) {
				innerZone.render(context);
			} else {
				childZone.setSuperZone(innerZone);
				childZone.render(context);
			}
		}
	}

	protected boolean isExpressionRequired() {
		return true;
	}

	public boolean isExpressionNamed() {
		return true;
	}

}
