package org.commontemplate.standard.directive.extend;

import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.core.Context;

/**
 * 回调父模板区域重写指令. 用于<code>OverZoneDirectiveHandler</code>内部
 *
 * @see org.commontemplate.standard.directive.extend.OverZoneDirectiveHandler
 * @author liangfei0201@163.com
 *
 */
public class SuperZoneDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		Zone superZone = (Zone)context.getProperty(Zone.SUPER_ZONE);
		if (superZone != null)
			superZone.render(context);
	}

	protected boolean isExpressionRequired() {
		return true;
	}

}
