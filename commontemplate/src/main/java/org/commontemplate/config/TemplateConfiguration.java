package org.commontemplate.config;

import java.util.List;

import org.commontemplate.core.SourceLoader;

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
	 * 获取板渲染拦截器
	 *
	 * @see org.commontemplate.config.RenderInterceptor
	 * @return 模板渲染拦截器, 类型: List&lt;RenderInterceptor&gt;
	 */
	public abstract List getRenderInterceptors();

	/**
	 * 获取模板过滤器
	 *
	 * @return 模板过滤器
	 */
	public abstract SourceFilter getSourceFilter();

	/**
	 * 获取模板加载器
	 *
	 * @return 模板加载器
	 */
	public abstract SourceLoader getSourceLoader();

	/**
	 * 获取模板缓存器
	 *
	 * @return 缓存器
	 */
	public abstract Cache getTemplateCache();

	/**
	 * 获取模板持久化缓存器
	 *
	 * @return 缓存器
	 */
	public abstract Cache getTemplatePersistentCache();

	/**
	 * 获取热加载检查器
	 *
	 * @return 热加载检查器
	 */
	public abstract ReloadController getReloadController();

	/**
	 * 获取模板源比较器
	 *
	 * @return 模板源比较器
	 */
	public abstract SourceComparator getSourceComparator();

	/**
	 * 获取路径过滤器
	 *
	 * @return 路径过滤器
	 */
	public abstract TemplateNameFilter getTemplateNameFilter();

}
