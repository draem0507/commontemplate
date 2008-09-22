package org.commontemplate.standard.reload;

import java.io.Serializable;

import org.commontemplate.config.ReloadController;

/**
 * 不热加载
 *
 * @author liangfei0201@163.com
 *
 */
public class NoneReloadController implements ReloadController, Serializable {

	private static final long serialVersionUID = 1L;

	public boolean shouldReload(Object key) {
		return false;
	}

}
