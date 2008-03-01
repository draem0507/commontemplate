package org.commontemplate.standard.reload;

import org.commontemplate.config.ReloadController;

/**
 * 不热加载
 * 
 * @author liangfei0201@163.com
 *
 */
public class NoneReloadController implements ReloadController {

	public boolean shouldReload(String templateName) {
		return false;
	}

}
