package org.commontemplate.standard;

import java.util.List;

import org.commontemplate.config.Cache;
import org.commontemplate.config.Configuration;
import org.commontemplate.config.ConfigurationException;
import org.commontemplate.config.DirectiveHandlerProvider;
import org.commontemplate.config.Keywords;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.ReloadController;
import org.commontemplate.config.ResourceBundleProvider;
import org.commontemplate.config.ResourceComparator;
import org.commontemplate.config.ResourceFilter;
import org.commontemplate.config.Syntax;
import org.commontemplate.config.TemplateNameFilter;
import org.commontemplate.config.TextFilter;
import org.commontemplate.core.EventListener;
import org.commontemplate.core.Logger;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.ResourceLoader;
import org.commontemplate.util.Assert;

/**
 * 配置设置
 *
 * @author liangfei0201@163.com
 *
 */
public class ConfigurationSettings extends Configuration {

	private static final long serialVersionUID = 1L;

	// ======== Expression ========

	private boolean functionAvailable;

	public boolean isFunctionAvailable() {
		return functionAvailable;
	}

	/**
	 * 设置是否允许调用函数，如：${xxx.yyy(zzz)}
	 *
	 * @param functionAvailable true为允许调用，false为不允许调用
	 */
	public void setFunctionAvailable(boolean functionAvailable) {
		this.functionAvailable = functionAvailable;
	}

	private Keywords keywords = Keywords.DEFAULT;

	public Keywords getKeywords() {
		return keywords;
	}

	public void setKeywordsSetting(String value) {
		Assert.assertTrue(value != null && value.trim().length() > 0, "keywordsString不能为空！");

		String[] values = value.trim().split("\\,");
		String nullKeyword = values.length > 0 ? values[0] : Keywords.DEFAULT_NULL_KEYWORD;
		String trueKeyword = values.length > 1 ? values[1] : Keywords.DEFAULT_FALSE_KEYWORD;
		String falseKeyword = values.length > 2 ? values[2] : Keywords.DEFAULT_TRUE_KEYWORD;
		String thisKeyword = values.length > 3 ? values[3] : Keywords.DEFAULT_CURRENT_LOCAL_CONTEXT_KEYWORD;
		String superKeyword = values.length > 4 ? values[4] : Keywords.DEFAULT_SUPER_LOCAL_CONTEXT_KEYWORD;
		String contextKeyword = values.length > 5 ? values[5] : Keywords.DEFAULT_CONTEXT_KEYWORD;
		setKeywords(new Keywords(nullKeyword, trueKeyword, falseKeyword, thisKeyword, superKeyword, contextKeyword));
	}

	/**
	 * 设置表达式关键字
	 *
	 * @param keywords 表达式关键字
	 */
	public void setKeywords(Keywords keywords) {
		this.keywords = keywords;
	}

	// 操作符优先级 --------------

	private OperatorHandlerProvider operatorHandlerProvider;

	public OperatorHandlerProvider getOperatorHandlerProvider() {
		return operatorHandlerProvider;
	}

	/**
	 * 设置操作符供给器
	 *
	 * @param operatorHandlerProvider 操作符供给器
	 */
	public void setOperatorHandlerProvider(OperatorHandlerProvider operatorHandlerProvider) {
		this.operatorHandlerProvider = operatorHandlerProvider;
	}

	// ======== Directive ========

	// 纯文本指令过滤器 -----------

	private TextFilter textFilter;

	/**
	 * 获取非指令文本块过滤器
	 *
	 * @return 非指令文本块过滤器
	 */
	public TextFilter getTextFilter() {
		return textFilter;
	}

	/**
	 * 设置非指令文本块过滤器
	 *
	 * @param textFilter 非指令文本块过滤器
	 */
	public void setTextFilter(TextFilter textFilter) {
		this.textFilter = textFilter;
	}

	// 指令语法设置 --------------

	private Syntax syntax = Syntax.DEFAULT;

	public Syntax getSyntax() {
		return syntax;
	}

	/**
	 * 设置指令语法，不设置将默认使用DirectiveSyntax.DEFAULT
	 *
	 * @param syntax 指令语法
	 */
	public void setSyntax(Syntax syntax) {
		Assert.assertNotNull(syntax, "不允许设置空语法!");
		this.syntax = syntax;
	}

	public void setSyntaxSetting(String value) {
		Assert.assertTrue(value != null && value.trim().length() > 0, "DirectiveSyntaxString不能为空！");

		value = value.trim();
		char leader = value.length() > 0 ? value.charAt(0) : Syntax.DEFAULT_LEADER;
		char expressionBegin = value.length() > 1 ? value.charAt(1) : Syntax.DEFAULT_EXPRESSION_BEGIN;
		char expressionEnd = value.length() > 2 ? value.charAt(2) : Syntax.DEFAULT_EXPRESSION_END;
		char lineComment = value.length() > 3 ? value.charAt(3) : Syntax.DEFAULT_LINE_COMMENT;
		char blockComment = value.length() > 4 ? value.charAt(4) : Syntax.DEFAULT_BLOCK_COMMENT;
		char noParse = value.length() > 5 ? value.charAt(5) : Syntax.DEFAULT_NO_PARSE;
		String endDirectiveName = value.length() > 6 ? value.substring(6) : Syntax.DEFAULT_END_DIRECTIVE_NAME;
		setSyntax(new Syntax(leader, expressionBegin, expressionEnd, lineComment, blockComment, noParse, endDirectiveName));
	}

	// 指令处理器 -------------

	private DirectiveHandlerProvider directiveHandlerProvider;

	public DirectiveHandlerProvider getDirectiveHandlerProvider() {
		return directiveHandlerProvider;
	}

	/**
	 * 设置指令供给器
	 *
	 * @param directiveHandlerProvider 指令供给器
	 */
	public void setDirectiveHandlerProvider(DirectiveHandlerProvider directiveHandlerProvider) {
		this.directiveHandlerProvider = directiveHandlerProvider;
	}

	// ======== Template ========

	private boolean debugMode;

	public boolean isDebugMode() {
		return debugMode;
	}

	/**
	 * 设置是否为调试模式，调试模式将输出更多信息，默认为非调试模式。
	 *
	 * @param debugMode 是否为调试模式
	 */
	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	private Logger logger;

	public Logger getLogger() {
		return logger;
	}

	/**
	 * 设置日志记录端
	 *
	 * @param logger 日志记录端
	 */
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	// 模板缓存 -------

	private Cache templateCache;

	public Cache getTemplateCache() {
		return templateCache;
	}

	/**
	 * 设置模板缓存策略
	 *
	 * @param templateCache 模板缓存策略
	 */
	public void setTemplateCache(Cache templateCache) {
		this.templateCache = templateCache;
	}

	// 输出缓存 -------

	private Cache outCache;

	public Cache getOutCache() {
		return outCache;
	}

	/**
	 * 设置输出缓存策略
	 *
	 * @param outCache 输出缓存策略
	 */
	public void setOutCache(Cache outCache) {
		this.outCache = outCache;
	}

	// 国际化信息束工厂 -----

	private ResourceBundleProvider resourceBundleProvider;

	public ResourceBundleProvider getResourceBundleProvider() {
		return resourceBundleProvider;
	}

	/**
	 * 设置国际化信息包供给器
	 *
	 * @param resourceBundleProvider 国际化信息包供给器
	 */
	public void setResourceBundleProvider(
			ResourceBundleProvider resourceBundleProvider) {
		this.resourceBundleProvider = resourceBundleProvider;
	}

	// 模板加载器 --------

	private ResourceLoader resourceLoader;

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	/**
	 * 设置模板源加载器
	 *
	 * @param templateLoader 模板源加载器
	 */
	public void setResourceLoader(ResourceLoader templateLoader) {
		this.resourceLoader = templateLoader;
	}

	// 模板过滤器 ------------

	private ResourceFilter resourceFilter;

	/**
	 * 获取模板源过滤器
	 *
	 * @return 模板源过滤器
	 */
	public ResourceFilter getResourceFilter() {
		return resourceFilter;
	}

	/**
	 * 设置模板源过滤器
	 *
	 * @param resourceFilter 模板源过滤器
	 */
	public void setResourceFilter(ResourceFilter resourceFilter) {
		this.resourceFilter = resourceFilter;
	}

	// 路径过滤器 -------------

	private TemplateNameFilter templateNameFilter;

	/**
	 * 获取模板名称过滤器
	 *
	 * @return 模板名称过滤器
	 */
	public TemplateNameFilter getTemplateNameFilter() {
		return templateNameFilter;
	}

	/**
	 * 设置模板名称过滤器
	 *
	 * @param templateNameFilter 模板名称过滤器
	 */
	public void setTemplateNameFilter(TemplateNameFilter templateNameFilter) {
		this.templateNameFilter = templateNameFilter;
	}

	// 默认格式化器 --------------

	private OutputFormatter defaultOutputFormatter;

	/**
	 * 获取默认格式化器
	 *
	 * @return 默认格式化器
	 */
	public OutputFormatter getDefaultOutputFormatter() {
		return defaultOutputFormatter;
	}

	/**
	 * 设置默认格式化器
	 *
	 * @param defaultOutputFormatter 默认格式化器
	 */
	public void setDefaultOutputFormatter(OutputFormatter defaultOutputFormatter) {
		this.defaultOutputFormatter = defaultOutputFormatter;
	}

	// 热加载 ------------

	private ReloadController reloadController;

	/**
	 * 获取热加载决策器
	 *
	 * @return 热加载决策器
	 */
	public ReloadController getReloadController() {
		return reloadController;
	}

	/**
	 * 设置热加载决策器
	 *
	 * @param reloadController 热加载决策器
	 */
	public void setReloadController(ReloadController reloadController) {
		this.reloadController = reloadController;
	}

	private ResourceComparator resourceComparator;

	/**
	 * 获取模板源比较器
	 *
	 * @return 模板源比较器
	 */
	public ResourceComparator getResourceComparator() {
		return resourceComparator;
	}

	/**
	 * 设置模板源比较器
	 *
	 * @param resourceComparator 模板源比较器
	 */
	public void setResourceComparator(ResourceComparator resourceComparator) {
		if (resourceComparator == null)
			throw new ConfigurationException("不能设置空的resourceComparator！");
		this.resourceComparator = resourceComparator;
	}

	// 事件监听器管理

	private EventListener eventListener;

	public EventListener getEventListener() {
		return eventListener;
	}

	/**
	 * 设置事件监听器
	 *
	 * @param eventListener 事件监听器
	 */
	public void setEventListener(EventListener eventListener) {
		this.eventListener = eventListener;
	}

	private List elementInterceptors;

	public List getElementInterceptors() {
		return elementInterceptors;
	}

	public void setElementInterceptors(List elementInterceptors) {
		this.elementInterceptors = elementInterceptors;
	}

}
