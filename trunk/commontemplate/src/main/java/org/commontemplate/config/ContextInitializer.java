package org.commontemplate.config;

import org.commontemplate.core.Context;

/**
 * 上下文初始化器.
 *
 * @see org.commontemplate.standard.ConfigurationSettings#setContextInitializer(ContextInitializer)
 * @author liangfei0201@163.com
 *
 */
public interface ContextInitializer {

	/**
	 * 初始化上下文
	 *
	 * @param context 上下文
	 */
	void initialize(Context context);

}
