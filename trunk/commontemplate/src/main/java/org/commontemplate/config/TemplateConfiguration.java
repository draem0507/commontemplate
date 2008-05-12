package org.commontemplate.config;

import java.util.List;

/**
 * 与指令相关的配置
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class TemplateConfiguration extends ExpressionConfiguration {

	/**
	 * 获取指令语法
	 *
	 * @return 指令语法
	 */
	public Syntax getSyntax() {
		return Syntax.DEFAULT;
	}

	/**
	 * 获取指令处理器决策器
	 *
	 * @return 指令处理器决策器
	 */
	public abstract DirectiveHandlerProvider getDirectiveHandlerProvider();

	/**
	 * 获取文本指令过滤器
	 *
	 * @return 文本指令过滤器
	 */
	public abstract TextFilter getTextFilter();

	/**
	 * 获取模板过滤器
	 *
	 * @return 模板过滤器
	 */
	public abstract ResourceFilter getResourceFilter();

	public abstract List getRenderInterceptors();

}
