package org.commontemplate.config;

import org.commontemplate.core.Context;

/**
 * 区域处理器
 *
 * @author liangfei0201@163.com
 *
 */
public interface ScopeHandler {

	/**
	 * 获取区域变量
	 *
	 * @param context 上下文信息
	 * @param level 区域级别
	 * @return 变量值
	 */
	Object getScopeVariable(Context context, int level);

}
