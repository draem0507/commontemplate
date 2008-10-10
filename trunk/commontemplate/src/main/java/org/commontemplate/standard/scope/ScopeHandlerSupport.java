package org.commontemplate.standard.scope;

import java.io.Serializable;

import org.commontemplate.config.ScopeHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;

public abstract class ScopeHandlerSupport implements ScopeHandler, Serializable {

	private static final long serialVersionUID = 1L;
	
	// 工具函数
	protected final Object getLocalVariable(Context context, LocalContext local, String name) {
		context.pushLocalContext();
		try {
			context.put(name, null); // 加一个空值, 防止循环调用
			return local.getVariable(name);
		} finally {
			context.popLocalContext();
		}
	}

}
