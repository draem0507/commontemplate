package org.commontemplate.standard.directive.extend;

import java.util.List;

import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.core.Context;

/**
 * 模板区域重写指令, 覆盖父模板的相应区域, 用于<code>ExtendDirectiveHandler</code>内部
 *
 * @see org.commontemplate.standard.directive.extend.ExtendDirectiveHandler
 * @author liangfei0201@163.com
 *
 */
public class OverZoneDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	protected void doRender(Context context, String directiveName,
			Object param, List innerElements)
			throws Exception {
		String zoneName = (String)param;
		Zone childZone = (Zone)context.getProperty(ZoneDirectiveHandler.ZONE_TYPE, zoneName);
		Zone innerZone = new Zone(innerElements);
		if (childZone == null) {
			context.getParentLocalContext().putProperty(ZoneDirectiveHandler.ZONE_TYPE, zoneName, innerZone);
		} else {
			childZone.setSuperZone(innerZone);
		}
	}

	protected boolean isExpressionRequired() {
		return true;
	}

}
