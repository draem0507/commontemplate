package org.commontemplate.standard;

import java.util.List;
import java.util.Map;

import org.commontemplate.config.Cache;
import org.commontemplate.config.Configuration;
import org.commontemplate.config.ConfigurationException;
import org.commontemplate.config.ContextInitializer;
import org.commontemplate.config.DirectiveHandlerProvider;
import org.commontemplate.config.ExpressionFilter;
import org.commontemplate.config.Keywords;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.ReloadController;
import org.commontemplate.config.SourceComparator;
import org.commontemplate.config.SourceFilter;
import org.commontemplate.config.Syntax;
import org.commontemplate.config.TemplateNameFilter;
import org.commontemplate.config.TextFilter;
import org.commontemplate.core.EventListener;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.SourceLoader;
import org.commontemplate.standard.syntax.KeywordsSettings;
import org.commontemplate.standard.syntax.SyntaxSettings;

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

	/**
	 * 设置表达式关键字
	 *
	 * @param keywords 表达式关键字
	 */
	public void setKeywords(Keywords keywords) {
		// Assert.assertNotNull(syntax, "ConfigurationSettings.keywords.required");
		if (keywords == null)
			return;
		this.keywords = keywords;
	}

	public void setKeywordsSettings(KeywordsSettings settings) {
		// Assert.assertNotNull(settings, "ConfigurationSettings.keywords.string.required");
		if (settings == null)
			return;
		this.keywords = settings.toKeywords();
	}

	public void setKeywordsString(String value) {
		// Assert.assertTrue(value != null && value.trim().length() > 0, "ExpressionConfigurationSettings.keywords.string.required");
		if (value == null)
			return;
		this.keywords = KeywordsSettings.parseKeywords(value);
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
		// Assert.assertNotNull(syntax, "ConfigurationSettings.syntax.required");
		if (syntax == null)
			return;
		this.syntax = syntax;
	}

	public void setSyntaxSettings(SyntaxSettings syntaxSettings) {
		// Assert.assertNotNull(syntaxSettings, "ConfigurationSettings.syntax.string.required");
		if (syntaxSettings == null)
			return;
		this.syntax = syntaxSettings.toSyntax();
	}

	public void setSyntaxString(String value) {
		// Assert.assertTrue(value != null && value.trim().length() > 0, "ConfigurationSettings.syntax.string.required");
		if (value == null)
			return;
		this.syntax = SyntaxSettings.parseSyntax(value);
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

	private boolean debug;

	public boolean isDebug() {
		return debug;
	}

	/**
	 * 设置是否为调试模式，调试模式将输出更多信息，默认为非调试模式。
	 *
	 * @param debug 是否为调试模式
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	// 模板缓存 -------

	private transient Cache templateCache;

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

	private transient Cache templatePersistentCache;

	public Cache getTemplatePersistentCache() {
		return templatePersistentCache;
	}

	public void setTemplatePersistentCache(Cache templatePersistentCache) {
		this.templatePersistentCache = templatePersistentCache;
	}

	// 输出缓存 -------

	private transient Cache outputCache;

	public Cache getOutputCache() {
		return outputCache;
	}

	/**
	 * 设置输出缓存策略
	 *
	 * @param outputCache 输出缓存策略
	 */
	public void setOutputCache(Cache outputCache) {
		this.outputCache = outputCache;
	}

	// 模板加载器 --------

	private transient SourceLoader sourceLoader;

	public SourceLoader getSourceLoader() {
		return sourceLoader;
	}

	/**
	 * 设置模板源加载器
	 *
	 * @param sourceLoader 模板源加载器
	 */
	public void setSourceLoader(SourceLoader sourceLoader) {
		this.sourceLoader = sourceLoader;
	}

	// 模板过滤器 ------------

	private SourceFilter sourceFilter;

	/**
	 * 获取模板源过滤器
	 *
	 * @return 模板源过滤器
	 */
	public SourceFilter getSourceFilter() {
		return sourceFilter;
	}

	/**
	 * 设置模板源过滤器
	 *
	 * @param sourceFilter 模板源过滤器
	 */
	public void setSourceFilter(SourceFilter sourceFilter) {
		this.sourceFilter = sourceFilter;
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

	private SourceComparator sourceComparator;

	/**
	 * 获取模板源比较器
	 *
	 * @return 模板源比较器
	 */
	public SourceComparator getSourceComparator() {
		return sourceComparator;
	}

	/**
	 * 设置模板源比较器
	 *
	 * @param sourceComparator 模板源比较器
	 */
	public void setSourceComparator(SourceComparator sourceComparator) {
		if (sourceComparator == null)
			throw new ConfigurationException("ConfigurationSettings.resource.comparator.required");
		this.sourceComparator = sourceComparator;
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

	private List evaluateInterceptors;

	public List getEvaluateInterceptors() {
		return evaluateInterceptors;
	}

	public void setEvaluateInterceptors(List evaluateInterceptors) {
		this.evaluateInterceptors = evaluateInterceptors;
	}

	private List renderInterceptors;

	public List getRenderInterceptors() {
		return renderInterceptors;
	}

	public void setRenderInterceptors(List renderInterceptors) {
		this.renderInterceptors = renderInterceptors;
	}

	private ContextInitializer contextInitializer;

	public ContextInitializer getContextInitializer() {
		return contextInitializer;
	}

	public void setContextInitializer(ContextInitializer contextInitializer) {
		this.contextInitializer = contextInitializer;
	}

	private ExpressionFilter expressionFilter;

	public ExpressionFilter getExpressionFilter() {
		return expressionFilter;
	}

	public void setExpressionFilter(ExpressionFilter expressionFilter) {
		this.expressionFilter = expressionFilter;
	}

	private Map scopeHandlers;

	public Map getScopeHandlers() {
		return scopeHandlers;
	}

	public void setScopeHandlers(Map scopeHandlers) {
		this.scopeHandlers = scopeHandlers;
	}

}
