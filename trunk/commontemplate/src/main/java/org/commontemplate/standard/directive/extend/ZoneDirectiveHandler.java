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
	
	public void doRender(Context context, String directiveName,
			Object param, List innerElements)
			throws Exception {
		String zoneName = (String)param;
		Zone childZone = (Zone)context.lookupProperty(ZoneDirectiveHandler.ZONE_TYPE, zoneName);
		Zone innerZone = new Zone(innerElements);
		if (childZone == null) {
			innerZone.render(context);
		} else {
			childZone.setSuperZone(innerZone);
			childZone.render(context);
		}
	}

}
