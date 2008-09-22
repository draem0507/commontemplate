package org.commontemplate.engine;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.commontemplate.config.Cache;
import org.commontemplate.config.ReloadController;
import org.commontemplate.config.ResourceComparator;
import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Comment;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionBuilder;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Resource;
import org.commontemplate.core.ResourceLoader;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateBudiler;
import org.commontemplate.core.TemplateLoader;
import org.commontemplate.core.TemplateParser;
import org.commontemplate.core.Text;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.Variable;
import org.commontemplate.util.Assert;
import org.commontemplate.util.UrlUtils;

/**
 * 模板工厂实现
 * <p/>
 * (内同步，线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
final class TemplateLoaderImpl implements TemplateLoader {

	/**
	 * 构造模板工厂
	 *
	 * @param templateParser 模板指令解释器
	 * @param resourceLoader 模板加载器
	 * @param cache 模板缓存策略
	 * @param reloadController 热加载控制器
	 * @param resourceComparator 模板源比较器
	 *
	 */
	TemplateLoaderImpl(TemplateParser templateParser, ResourceLoader resourceLoader,
			Cache cache, ReloadController reloadController,
			ResourceComparator resourceComparator) {
		Assert.assertNotNull(resourceLoader, "TemplateFactoryImpl.resource.loader.required");
		Assert.assertNotNull(templateParser, "TemplateFactoryImpl.template.parser.required");

		this.resourceLoader = resourceLoader;

		this.templateParser = templateParser;
		this.cache = cache;
		this.reloadController = reloadController;
		this.resourceComparator = resourceComparator;
	}

	// 实现TemplateFactory -------

	public Template getTemplate(String name) throws IOException, ParsingException {
		return cache(filterName(name), null, null);
	}

	public Template getTemplate(String name, String encoding) throws IOException, ParsingException {
		return cache(filterName(name), null, encoding);
	}

	public Template getTemplate(String name, Locale locale) throws IOException,
			ParsingException {
		return cache(filterName(name), locale, null);
	}

	public Template getTemplate(String name, Locale locale, String encoding)
			throws IOException, ParsingException {
		return cache(filterName(name), locale, encoding);
	}

	// 缓存同步 -------

	private final ReloadController reloadController;

	private final ResourceComparator resourceComparator;

	private final Cache cache;

	private Template cache(String name, Locale locale, String encoding)
			throws IOException, ParsingException {
		if (cache == null) { // 如果没有缓存策略，直接读取并编译模板
			Resource resource = load(name, locale, encoding);
			return parseTemplate(resource);
		}

		TemplateCacheKey key = new TemplateCacheKey(name, locale, encoding);
		TemplateCacheEntry entry = null;
		if (cache.isConcurrent()) { // 如果缓存本身已支持并发
			entry = (TemplateCacheEntry)cache.get(key);
			if (entry == null) { // TODO 此检测未在同步锁内，可能多个线程同时重复创建条目，但不影响正常运行，考虑读取次数远大于创建次数，可容忍。
				entry = new TemplateCacheEntry();
				cache.put(key, entry);
			}
		} else {
			if (cache.isOptimism()) { // 该状态表示缓存策略的get()函数未修改任何状态(即无副作用)
				try {
					// 尝试乐观并发，无锁读取
					entry = (TemplateCacheEntry)cache.get(key);
				} catch (Throwable t) { // 有异常则放弃读取
					// ignore
				}
			}
			if (entry == null) {
				// 缓存总锁，只锁定缓存中各条目获取过程，使缓存锁定过程最小化
				synchronized(cache) {
					entry = (TemplateCacheEntry)cache.get(key); // 必需在锁内获取并检查，确保条目唯一性
					if (entry == null) {
						entry = new TemplateCacheEntry(); // 创建简单的条目容器，条目中的内容，在下面条目锁内进行处理，以保证其它条目可以正常存取
						cache.put(key, entry); // 在锁内将条目放入缓存
					}
				}
			}
		}
		Assert.assertNotNull(entry); // 后验条件

		// 单条目锁，锁定资源获取过程，保证在资源获取时各条目之间互不影响。
		// 前面的缓存总锁，已保证条目在缓存中是唯一的，故此条目锁在整个缓存中有效。
		Template template = entry.getTemplate(); // 条目中模板获取过程无状态修改，尝试乐观并发，无锁读取
		if (template == null) {
			synchronized (entry) {
				template = entry.getTemplate();
				if (template == null) { // 不存在，解析加载
					Resource resource = load(name, locale, encoding);
					template = parseTemplate(resource); // 此函数调用时间较长，为缓存目标
					entry.setTemplate(template);
				} else { // 已存在，检查热加载
					if (reloadController != null
							&& reloadController.shouldReload(template.getName())) { // 是否需要检查热加载
						Resource resource = load(name, locale, encoding);
						if (resourceComparator != null
								&& resourceComparator.isModified(template, resource)) { // 比较是否已更改
							template = parseTemplate(resource);
							entry.setTemplate(template);
						}
					}
				}
			}
		} else { // 已存在，检查热加载
			if (reloadController != null
					&& reloadController.shouldReload(template.getName())) { // 是否需要检查热加载
				Resource resource = load(name, locale, encoding);
				if (resourceComparator != null
						&& resourceComparator.isModified(template, resource)) { // 比较是否已更改
					template = parseTemplate(resource);
					synchronized (entry) {
						entry.setTemplate(template); // TODO 可能重复热加载，但都在条目锁内，不影响正常运行，可容忍。
					}
				}
			}
		}
		return template;
	}

	// 加载 --------

	private final ResourceLoader resourceLoader;

	private Resource load(String name, Locale locale, String encoding) throws IOException {
		if (locale == null) {
			if(encoding == null)
				return getResource(name);
			return getResource(name, encoding);
		}
		if(encoding == null)
			return getResource(name, locale);
		return getResource(name, locale, encoding);
	}

	// 代理TemplateLoader -------

	public Resource getResource(String name) throws IOException {
		Resource resource = resourceLoader.getResource(filterName(name));
		if (resource == null)
			throw new IOException("Not found resource: " + name);
		return resource;
	}

	public Resource getResource(String name, String encoding)
			throws IOException {
		Resource resource = resourceLoader.getResource(filterName(name), encoding);
		if (resource == null)
			throw new IOException("Not found resource: " + name);
		return resource;
	}

	public Resource getResource(String name, Locale locale) throws IOException {
		Resource resource = resourceLoader.getResource(filterName(name), locale);
		if (resource == null)
			throw new IOException("Not found resource: " + name);
		return resource;
	}

	public Resource getResource(String name, Locale locale, String encoding)
			throws IOException {
		Resource resource = resourceLoader.getResource(filterName(name), locale, encoding);
		if (resource == null)
			throw new IOException("Not found resource: " + name);
		return resource;
	}

	private String filterName(String name) throws IOException {
		Assert.assertNotEmpty(name, "TemplateFactoryImpl.template.name.required");
		return UrlUtils.cleanUrl(name);
	}

	// 代理TemplateParser ----

	private final TemplateParser templateParser;

	public Expression parseExpression(String expression) throws ParsingException {
		return templateParser.parseExpression(expression);
	}

	public Template parseTemplate(String template) throws ParsingException {
		return templateParser.parseTemplate(template);
	}

	public Template parseTemplate(Resource resource)
			throws ParsingException, IOException {
		return templateParser.parseTemplate(resource);
	}

	public BlockDirective createBlockDirective(String name,
			Expression expression, List elements) {
		return templateParser.createBlockDirective(name, expression, elements);
	}

	public Comment createComment(String comment) {
		return templateParser.createComment(comment);
	}

	public Directive createDirective(String name, Expression expression) {
		return templateParser.createDirective(name, expression);
	}

	public Text createText(String text) {
		return templateParser.createText(text);
	}

	public ExpressionBuilder createExpressionBuilder() {
		return templateParser.createExpressionBuilder();
	}

	public TemplateBudiler createTemplateBudiler() {
		return templateParser.createTemplateBudiler();
	}

	public BinaryOperator createBinaryOperator(String operatorName,
			Expression leftOprand, Expression rightOprand) {
		return templateParser.createBinaryOperator(operatorName, leftOprand,
				rightOprand);
	}

	public Constant createConstant(Object constantValue) {
		return templateParser.createConstant(constantValue);
	}

	public UnaryOperator createUnaryOperator(String operatorName,
			Expression oprand) {
		return templateParser.createUnaryOperator(operatorName, oprand);
	}

	public Variable createVariable(String variableName) {
		return templateParser.createVariable(variableName);
	}

	public Template createTemplate(String name, List elements) {
		return templateParser.createTemplate(name, elements);
	}

}
