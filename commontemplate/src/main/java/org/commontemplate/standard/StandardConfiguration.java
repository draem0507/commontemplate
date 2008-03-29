package org.commontemplate.standard;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.config.ConfigurationException;
import org.commontemplate.config.DirectiveHandlerProvider;
import org.commontemplate.config.Syntax;
import org.commontemplate.config.Keywords;
import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.ReloadController;
import org.commontemplate.config.ResourceBundleProvider;
import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.config.Cache;
import org.commontemplate.config.TemplateNameFilter;
import org.commontemplate.config.ResourceFilter;
import org.commontemplate.config.ResourceComparator;
import org.commontemplate.config.TextFilter;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.core.EventListener;
import org.commontemplate.core.Logger;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.ResourceLoader;
import org.commontemplate.standard.cache.FifoCache;
import org.commontemplate.standard.cache.StrongCache;
import org.commontemplate.standard.cache.LruCache;
import org.commontemplate.standard.cache.MruCache;
import org.commontemplate.standard.cache.NoneCache;
import org.commontemplate.standard.cache.SoftCache;
import org.commontemplate.standard.cache.WeakCache;
import org.commontemplate.standard.directive.StandardDirectiveHandlerProvider;
import org.commontemplate.standard.filter.TemplateNameFilterChain;
import org.commontemplate.standard.filter.ResourceFilterChain;
import org.commontemplate.standard.filter.TextFilterChain;
import org.commontemplate.standard.format.DateFormatter;
import org.commontemplate.standard.format.NullFormatter;
import org.commontemplate.standard.format.NumberFormatter;
import org.commontemplate.standard.format.OutputFormatterChain;
import org.commontemplate.standard.i18n.PropertiesResourceBundleProvider;
import org.commontemplate.standard.listener.EventListenerChain;
import org.commontemplate.standard.operator.StandardOperatorHandlerProvider;
import org.commontemplate.standard.reload.IntervalReloadController;
import org.commontemplate.util.Assert;
import org.commontemplate.util.TypeUtils;

public class StandardConfiguration extends ConfigurationSettings {

	private static final long serialVersionUID = 1L;

	public void setModificationCheckInterval(long modificationCheckInterval) {
		IntervalReloadController reloadController = new IntervalReloadController();
		reloadController.setModificationCheckInterval(modificationCheckInterval);
		setReloadController(reloadController);
	}

	public void setMessageBasename(String baseName) {
		PropertiesResourceBundleProvider resourceBundleProvider = new PropertiesResourceBundleProvider();
		resourceBundleProvider.setBasename(baseName);
		setResourceBundleProvider(resourceBundleProvider);
	}

	/**
	 * 使用标准的缓存支持.
	 * 如果没有你需要的缓存支持, 可自行实现<code>org.commontemplate.config.TemplateCache</code>接口, 并使用setTemplateCache(TemplateCache cache);注册.
	 *
	 * @param cacheStrategy 缓存类型, 支持: NONE, STRONG, SOFT, WEAK, FIFO, LRU, MRU
	 * @param cacheMaxSize 缓存容量
	 */
	public void setStandardCache(String cacheStrategy, int cacheMaxSize) {
		if (cacheStrategy == null || "NONE".equalsIgnoreCase(cacheStrategy)) {
			setTemplateCache(new NoneCache());
		} else if ("STRONG".equalsIgnoreCase(cacheStrategy)) {
			setTemplateCache(new StrongCache());
		} else if ("SOFT".equalsIgnoreCase(cacheStrategy)) {
			setTemplateCache(new SoftCache());
		} else if ("WEAK".equalsIgnoreCase(cacheStrategy)) {
			setTemplateCache(new WeakCache());
		} else if ("FIFO".equalsIgnoreCase(cacheStrategy)) {
			FifoCache cache = new FifoCache();
			cache.setMaxSize(cacheMaxSize);
			setTemplateCache(cache);
		} else if ("LRU".equalsIgnoreCase(cacheStrategy)) {
			LruCache cache = new LruCache();
			cache.setMaxSize(cacheMaxSize);
			setTemplateCache(cache);
		} else if ("MRU".equalsIgnoreCase(cacheStrategy)) {
			MruCache cache = new MruCache();
			cache.setMaxSize(cacheMaxSize);
			setTemplateCache(cache);
		}
	}

	// 基本格式化器 ----------

	public void setDefaultNumberFormat(String format) {
		addDefaultOutputFormatter(Number.class, new NumberFormatter(format));
	}

	public void setDefaultDateFormat(String format) {
		addDefaultOutputFormatter(Date.class, new DateFormatter(format));
	}

	public void setDefaultNullValue(String nullValue) {
		addDefaultOutputFormatter(null, new NullFormatter(nullValue));
	}

	// ==========================

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

	// 缓存策略 -------

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

	/**
	 * 设置国际化信息文件基名称
	 *
	 * @see java.util.ResourceBundle
	 * @param baseName 国际化信息文件基名称
	 */
	public void setMessageBaseName(String baseName) {
		PropertiesResourceBundleProvider provider = new PropertiesResourceBundleProvider();
		provider.setBasename(baseName);
		setResourceBundleProvider(provider);
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

	private List resourceFilters = new ArrayList();

	/**
	 * 获取已添加的模板源过滤器链
	 *
	 * @return 模板源过滤器链
	 */
	public List getResourceFilters() {
		return resourceFilters;
	}

	/**
	 * 设置模板源过滤器链
	 * 注：为IoC(setter方式)保留
	 *
	 * @param resourceFilters 模板源过滤器链
	 */
	public void setResourceFilters(List resourceFilters) {
		this.resourceFilters.addAll(resourceFilters);
	}

	/**
	 * 添加模板源过滤器到链中
	 *
	 * @param resourceFilter 待添加的模板源过滤器
	 */
	public void addResourceFilter(ResourceFilter resourceFilter) {
		resourceFilters.add(resourceFilter);
	}

	/**
	 * 从链中删除相应模板源过滤器
	 *
	 * @param resourceFilter 待删除的模板源过滤器
	 */
	public void removeResourceFilter(ResourceFilter resourceFilter) {
		resourceFilters.remove(resourceFilter);
	}

	/**
	 * 供给组装后的模板源过滤器给引擎
	 */
	public ResourceFilter getResourceFilter() {
		ResourceFilterChain chain = new ResourceFilterChain();
		chain.setResourceFilters(getResourceFilters());
		return chain;
	}

	// 路径过滤器 -------------

	private List templateNameFilters = new ArrayList();

	/**
	 * 获取模板名称过滤器链
	 *
	 * @return 模板名称过滤器链
	 */
	public List getTemplateNameFilters() {
		return templateNameFilters;
	}

	/**
	 * 设置模板名称过滤器链.
	 * 注：为IoC(setter方式)保留
	 *
	 * @param templateNameFilters 模板名称过滤器链
	 */
	public void setTemplateNameFilters(List templateNameFilters) {
		for (Iterator iterator = templateNameFilters.iterator(); iterator.hasNext();) {
			addTemplateNameFilter((TemplateNameFilter) iterator.next());
		}
	}

	/**
	 * 添加模板名称过滤器到链中
	 *
	 * @param templateNameFilter
	 *            路径过滤器
	 */
	public void addTemplateNameFilter(TemplateNameFilter templateNameFilter) {
		templateNameFilters.add(templateNameFilter);
	}

	/**
	 * 从链中移除路径过滤器
	 *
	 * @param templateNameFilter
	 *            路径过滤器
	 */
	public void removeTemplateNameFilter(TemplateNameFilter templateNameFilter) {
		templateNameFilters.remove(templateNameFilter);
	}

	/**
	 * 供给组装后的模板名称过滤器给引擎
	 */
	public TemplateNameFilter getTemplateNameFilter() {
		TemplateNameFilterChain chain = new TemplateNameFilterChain();
		chain.setTemplateNameFilters(getTemplateNameFilters());
		return chain;
	}

	// 默认格式化器 --------------

	private Map typeFormatters = new HashMap();

	private OutputFormatter nullFormatter;

	/**
	 * 添加指定类型数据的格式化器
	 *
	 * @param type 格式化器所处理的类型，可以为null
	 * @param typeFormatter 格式化器
	 */
	public void addDefaultOutputFormatter(Class type, OutputFormatter typeFormatter) {
		if (type == null) // 将null单独保存而不放到Map中，避免在Map中重复查找null
			nullFormatter = typeFormatter;
		else
			typeFormatters.put(type, typeFormatter);
	}

	/**
	 * 移除指定类型数据的格式化器
	 *
	 * @param type 要移除格式化器处理的类型，可以为null
	 */
	public void removeDefaultOutputFormatter(Class type) {
		if (type == null)
			nullFormatter = null;
		else
			typeFormatters.remove(type);
	}

	private OutputFormatter generalFormatter;

	/**
	 * 设置通用格式化器，当没有相应的类型格式化器时，将使用通用格式化器
	 *
	 * @param generalFormatter
	 */
	public void setDefaultOutputFormatter(OutputFormatter generalFormatter) {
		this.generalFormatter = generalFormatter;
	}

	/**
	 * 供给组装后的默认格式化器给引擎
	 */
	public OutputFormatter getDefaultOutputFormatter() {
		OutputFormatterChain chain = new OutputFormatterChain();
		chain.setGeneralFormatter(generalFormatter);
		chain.setNullFormatter(nullFormatter);
		chain.setTypeFormatters(typeFormatters);
		return chain;
	}

	// 热加载 ------------

	private ReloadController reloadController;

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

	public ResourceComparator getResourceComparator() {
		return resourceComparator;
	}

	/**
	 * 设置模板源比较器
	 *
	 * @param resourceComparator 模板源比较器
	 */
	public void setResourceComparator(
			ResourceComparator resourceComparator) {
		if (resourceComparator == null)
			throw new ConfigurationException("不能设置空的resourceComparator！");
		this.resourceComparator = resourceComparator;
	}

	// 纯文本指令过滤器 -----------

	private List textFilters = new ArrayList();

	/**
	 * 获取非指令文本块过滤器链
	 *
	 * @return 非指令文本块过滤器链
	 */
	public List getTextFilters() {
		return textFilters;
	}

	/**
	 * 设置非指令文本块过滤器链.
	 * 注：为IoC(setter方式)保留
	 *
	 * @param textFilters 非指令文本块过滤器链
	 */
	public void setTextFilters(List textFilters) {
		this.textFilters = textFilters;
	}

	/**
	 * 添加文本块过滤器到链中
	 *
	 * @param textFilter 待添加文本块过滤器链
	 */
	public void addTextFilter(TextFilter textFilter) {
		textFilters.add(textFilter);
	}

	/**
	 * 从链中删除相应文本块过滤器链
	 *
	 * @param textFilter 待删除文本块过滤器链
	 */
	public void removeTextFilter(TextFilter textFilter) {
		textFilters.remove(textFilter);
	}

	// 实现接口
	public TextFilter getTextFilter() {
		TextFilterChain chain = new TextFilterChain();
		chain.setTextFilters(getTextFilters());
		return chain;
	}

	// 语法设置 --------------

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
		this.keywords = keywords;
	}

	// 指令处理器 -------------

	private Map directiveHandlers = new HashMap();

	/**
	 * 获取指令处理器集
	 *
	 * @return 指令处理器集
	 */
	public Map getDirectiveHandlers() {
		return directiveHandlers;
	}

	/**
	 * 设置指令处理器集.
	 * 注：为IoC(setter方式)保留
	 *
	 * @param directiveHandlers 指令处理器集
	 */
	public void setDirectiveHandlers(Map directiveHandlers) {
		// this.directiveHandlers.putAll(directiveHandlers);
		for (Iterator iterator = directiveHandlers.entrySet().iterator(); iterator
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			if (entry.getValue() instanceof BlockDirectiveHandler) {
				addDirectiveHandler((String) entry.getKey(),
						(BlockDirectiveHandler) entry.getValue());
			} else {
				addDirectiveHandler((String) entry.getKey(),
						(LineDirectiveHandler) entry.getValue());
			}
		}
	}

	/**
	 * 添加块指令处理器
	 *
	 * @param name 所处理的指令名
	 * @param handler 指令处理器
	 */
	public void addDirectiveHandler(String name, BlockDirectiveHandler handler) {
		if (handler == null)
			throw new ConfigurationException("指令处理类不能为空!");
		if (name == null)
			name = "";
		if (!"".equals(name) && !TypeUtils.isNamed(name))
			throw new ConfigurationException("不合法的指令名称:" + name);

		directiveHandlers.put(name.trim(), handler);
	}

	/**
	 * 添加行指令处理器.
	 * 注：重载函数名称addDirectiveHandler以表明块指令与行指令注册在同一集合中，名称应唯一。
	 *
	 * @param name 所处理的指令名
	 * @param handler 指令处理器
	 */
	public void addDirectiveHandler(String name, LineDirectiveHandler handler) {
		if (handler == null)
			throw new ConfigurationException("指令处理类不能为空!");
		if (name == null)
			name = "";
		if (!"".equals(name) && !TypeUtils.isNamed(name))
			throw new ConfigurationException("不合法的指令名称:" + name);

		directiveHandlers.put(name.trim(), handler);
	}

	// 实现接口
	public DirectiveHandlerProvider getDirectiveHandlerProvider() {
		StandardDirectiveHandlerProvider provider = new StandardDirectiveHandlerProvider();
		provider.setDirectiveHandlers(getDirectiveHandlers());
		return provider;
	}

	// 二元操作符优先级 --------------

	private Map binaryOperatorPrioritys = new HashMap();

	public Map getBinaryOperatorPrioritys() {
		return binaryOperatorPrioritys;
	}

	public void setBinaryOperatorPrioritys(Map binaryOperatorPrioritys) {
		this.binaryOperatorPrioritys.putAll(binaryOperatorPrioritys);
	}

	/**
	 * 添加二元操作符优先级，不设置默认为0
	 *
	 * @param operatorName 二元操作符名称
	 * @param priority 优先级
	 */
	public void addBinaryOperatorPriority(String operatorName, int priority) {
		binaryOperatorPrioritys.put(operatorName, new Integer(priority));
	}

	/**
	 * 移除二元操作符优先级，默认为0
	 *
	 * @param operatorName 二元操作符名称
	 */
	public void removeBinaryOperatorPriority(String operatorName) {
		binaryOperatorPrioritys.remove(operatorName);
	}

	// 一元操作符优先级 --------------

	private Map unaryOperatorPrioritys = new HashMap();

	public Map getUnaryOperatorPrioritys() {
		return unaryOperatorPrioritys;
	}

	public void setUnaryOperatorPrioritys(Map unaryOperatorPrioritys) {
		this.unaryOperatorPrioritys.putAll(unaryOperatorPrioritys);
	}

	/**
	 * 添加一元操作符优先级，不设置默认为0
	 *
	 * @param operatorName 一元操作符名称
	 * @param priority 优先级
	 */
	public void addUnaryOperatorPriority(String operatorName, int priority) {
		unaryOperatorPrioritys.put(operatorName, new Integer(priority));
	}

	/**
	 * 移除一元操作符优先级，默认为0
	 *
	 * @param operatorName 一元操作符名称
	 */
	public void removeUnaryOperatorPriority(String operatorName) {
		unaryOperatorPrioritys.remove(operatorName);
	}

	private int functionPriority = 0;

	public final void setFunctionPriority(int functionPriority) {
		this.functionPriority = functionPriority;
	}

	public int getFunctionPriority() {
		return functionPriority;
	}

	// 二元操作符处理器 -----------

	private Map binaryOperatorHandlers = new HashMap();

	public Map getBinaryOperatorHandlers() {
		return binaryOperatorHandlers;
	}

	public void setBinaryOperatorHandlers(Map binaryOperatorHandlers) {
		this.binaryOperatorHandlers.putAll(binaryOperatorHandlers);
	}

	/**
	 * 添加二元操作符处理器
	 *
	 * @param name
	 *            二元操作符名称
	 * @param binaryOperatorHandler
	 *            二元操作符处理器
	 */
	public void addBinaryOperatorHandler(String name, BinaryOperatorHandler binaryOperatorHandler) {
		binaryOperatorHandlers.put(name, binaryOperatorHandler);
	}

	/**
	 * 移除二元操作符处理器
	 *
	 * @param name
	 *            二元操作符名称
	 */
	public void removeBinaryOperatorHandler(String name) {
		binaryOperatorHandlers.remove(name);
	}


	// 二元操作符链 -------------

	/*private Map lastBinaryOperators = new HashMap();

	public void addBinaryOperatorHandlerChain(String name, BinaryOperatorHandlerSupport nextHandler) {
		if (! getBinaryOperatorHandlers().containsKey(name)) {
			addBinaryOperatorHandler(name, nextHandler);
		}
		BinaryOperatorHandlerSupport lastHandler = (BinaryOperatorHandlerSupport) lastBinaryOperators
				.get(name);
		if (lastHandler != null) {
			lastHandler.setNextHandler(nextHandler);
		}
		lastBinaryOperators.put(name, nextHandler);
	}*/

	// 一元操作符处理器 -----------

	private Map unaryOperatorHandlers = new HashMap();

	public Map getUnaryOperatorHandlers() {
		return unaryOperatorHandlers;
	}

	public void setUnaryOperatorHandlers(Map unaryOperatorHandlers) {
		this.unaryOperatorHandlers.putAll(unaryOperatorHandlers);
	}

	/**
	 * 添加一元操作符处理器
	 *
	 * @param name
	 *            一元操作符名称
	 * @param unaryOperatorHandler
	 *            一元操作符处理器
	 */
	public void addUnaryOperatorHandler(String name, UnaryOperatorHandler unaryOperatorHandler) {
		unaryOperatorHandlers.put(name, unaryOperatorHandler);
	}

	/**
	 * 移除一元操作符处理器
	 *
	 * @param name
	 *            一元操作符名称
	 */
	public void removeUnaryOperatorHandler(String name) {
		unaryOperatorHandlers.remove(name);
	}

	// 一元操作符链 ------------

	/*private Map lastUnaryOperators = new HashMap();

	public void addUnaryOperatorHandlerChain(String name, UnaryOperatorHandlerSupport nextHandler) {
		if (! getUnaryOperatorHandlers().containsKey(name)) {
			addUnaryOperatorHandler(name, nextHandler);
		}
		UnaryOperatorHandlerSupport lastHandler = (UnaryOperatorHandlerSupport) lastUnaryOperators
				.get(name);
		if (lastHandler != null) {
			lastHandler.setNextHandler(nextHandler);
		}
		lastUnaryOperators.put(name, nextHandler);
	}*/

	// 实现接口
	public OperatorHandlerProvider getOperatorHandlerProvider() {
		StandardOperatorHandlerProvider provider = new StandardOperatorHandlerProvider();
		provider.setBinaryOperatorHandlers(getBinaryOperatorHandlers());
		provider.setBinaryOperatorPrioritys(getBinaryOperatorPrioritys());
		provider.setUnaryOperatorHandlers(getUnaryOperatorHandlers());
		provider.setUnaryOperatorPrioritys(getUnaryOperatorPrioritys());
		provider.setFunctionPriority(getFunctionPriority());
		return provider;
	}

	// 事件监听器管理

	private List eventListeners = new ArrayList();

	public List getEventListeners() {
		return eventListeners;
	}

	public void setEventListeners(List eventListeners) {
		this.eventListeners = eventListeners;
	}

	/**
	 * 添加事件监听器
	 *
	 * @param eventListener 待添加的事件监听器
	 */
	public void addEventListener(EventListener eventListener) {
		Assert.assertNotNull(eventListener, "不能添加空的EventListener");
		eventListeners.add(eventListener);
	}

	/**
	 * 移除事件监听器
	 *
	 * @param eventListener 待移除的事件监听器
	 */
	public void removeEventListener(EventListener eventListener) {
		eventListeners.remove(eventListener);
	}

	private List asynchronousEventListeners = new ArrayList();

	public List getAsynchronousEventListeners() {
		return asynchronousEventListeners;
	}

	public void setAsynchronousEventListeners(List asynchronousEventListeners) {
		this.asynchronousEventListeners = asynchronousEventListeners;
	}

	/**
	 * 添加异步执行事件监听器
	 *
	 * @param eventListener 待添加的异步执行事件监听器
	 */
	public void addAsynchronousEventListener(EventListener eventListener) {
		Assert.assertNotNull(eventListener, "不能添加空的异步EventListener");
		asynchronousEventListeners.add(eventListener);
	}

	/**
	 * 移除异步执行事件监听器
	 *
	 * @param eventListener 待移除的异步执行事件监听器
	 */
	public void removeAsynchronousEventListener(EventListener eventListener) {
		asynchronousEventListeners.remove(eventListener);
	}

	// 实现接口
	public EventListener getEventListener() {
		EventListenerChain chain = new EventListenerChain();
		chain.setEventListeners(getEventListeners());
		chain.setAsynchronousEventListeners(getAsynchronousEventListeners());
		return chain;
	}

	// 子类可以重写此函数进行相应配置有效性检查，如果不需要检查则忽略
	public void validate() {
		// Empty Implement
	}

}
