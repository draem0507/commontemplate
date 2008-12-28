package org.commontemplate.engine.template;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.commontemplate.config.Cache;
import org.commontemplate.config.ReloadController;
import org.commontemplate.config.SourceComparator;
import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Comment;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionBuilder;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Source;
import org.commontemplate.core.SourceLoader;
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
	 * @param sourceLoader 模板加载器
	 * @param cache 模板缓存策略
	 * @param persistentCahce 模板持久化缓存策略
	 * @param reloadController 热加载控制器
	 * @param sourceComparator 模板源比较器
	 *
	 */
	TemplateLoaderImpl(TemplateParser templateParser, SourceLoader sourceLoader,
			Cache cache, Cache persistentCahce, ReloadController reloadController,
			SourceComparator sourceComparator) {
		Assert.assertNotNull(sourceLoader, "TemplateFactoryImpl.resource.loader.required");
		Assert.assertNotNull(templateParser, "TemplateFactoryImpl.template.parser.required");

		this.sourceLoader = sourceLoader;

		this.templateParser = templateParser;
		this.cache = cache;
		this.persistentCahce = persistentCahce;
		this.reloadController = reloadController;
		this.sourceComparator = sourceComparator;
	}

	// 缓存同步 -------

	private final ReloadController reloadController;

	private final SourceComparator sourceComparator;

	private final Cache cache;

	private final Cache persistentCahce;

	private Template cacheTemplate(String name, Locale locale, String encoding)
			throws IOException, ParsingException {
		if (cache == null) { // 如果没有缓存策略，直接读取并编译模板
			Source source = loadSource(name, locale, encoding);
			return persistentTemplate(source);
		}

		// 缓存参数
		TemplateCacheKey key = new TemplateCacheKey(name, locale, encoding); // 缓存索引键
		TemplateCacheEntry entry; // 缓存条目
		Template template; // 缓存模板

		// 缓存总锁，只锁定缓存中各条目获取过程，使缓存锁定过程最小化
		synchronized(cache) {
			entry = (TemplateCacheEntry)cache.get(key);
			if (entry == null) {
				entry = new TemplateCacheEntry(); // 创建最简单的条目容器，条目中的内容，在下面条目锁内进行处理，以保证其它条目可以正常存取
				cache.put(key, entry);
			}
		}

		// 单条目锁，锁定资源获取过程，保证在资源获取时各条目之间互不影响。
		// 前面的缓存总锁，已保证条目在缓存中是唯一的，故此条目锁在整个缓存中有效。
		synchronized (entry) {
			template = entry.getTemplate();
			if (template == null) { // 不存在，解析加载
				Source source = loadSource(name, locale, encoding);
				template = persistentTemplate(source); // 此函数调用时间较长，为主要缓存目标
				entry.setTemplate(template);
			} else { // 已存在，检查热加载
				if (reloadController != null
						&& reloadController.shouldReload(template.getName())) { // 是否需要检查热加载
					Source source = loadSource(name, locale, encoding);
					if (sourceComparator != null
							&& sourceComparator.isModified(template, source)) { // 比较是否已更改
						template = parseTemplate(source); // 重新解析模板
						if (persistentCahce != null) {
							synchronized(persistentCahce) {
								persistentCahce.put(source.getName(), template); // 加入到持久化缓存中
							}
						}
						entry.setTemplate(template);
					}
				}
			}
		}

		return template;
	}

	// 持久化模板
	private Template persistentTemplate(Source source) throws ParsingException, IOException {
		// assert source != null
		Template template;
		if (persistentCahce != null) {
			synchronized(persistentCahce) {
				template = (Template)persistentCahce.get(source.getName());
				if (template == null // 模板为空
						|| (sourceComparator != null // 或者模板源已改变
								&& sourceComparator.isModified(template, source))) {
					template = parseTemplate(source);
					persistentCahce.put(source.getName(), template);
				}
			}
		} else {
			template = parseTemplate(source);
		}
		return template;
	}

	// 加载资源 --------

	private final SourceLoader sourceLoader;

	// 注：返回的Source中的getName()并不一定等于传入的name参数。
	private Source loadSource(String name, Locale locale, String encoding) throws IOException {
		if (locale == null) {
			if(encoding == null)
				return getSource(name);
			return getSource(name, encoding);
		}
		if(encoding == null)
			return getSource(name, locale);
		return getSource(name, locale, encoding);
	}

	private String cleanName(String name) throws IOException {
		Assert.assertNotEmpty(name, "TemplateFactoryImpl.template.name.required");
		return UrlUtils.cleanUrl(name);
	}

	// 实现TemplateFactory -------

	public Template getTemplate(String name) throws IOException, ParsingException {
		return cacheTemplate(cleanName(name), null, null);
	}

	public Template getTemplate(String name, String encoding) throws IOException, ParsingException {
		return cacheTemplate(cleanName(name), null, encoding);
	}

	public Template getTemplate(String name, Locale locale) throws IOException,
			ParsingException {
		return cacheTemplate(cleanName(name), locale, null);
	}

	public Template getTemplate(String name, Locale locale, String encoding)
			throws IOException, ParsingException {
		return cacheTemplate(cleanName(name), locale, encoding);
	}

	// 代理TemplateLoader -------

	public Source getSource(String name) throws IOException {
		Source source = sourceLoader.getSource(cleanName(name));
		if (source == null)
			throw new IOException("Not found template source: " + name);
		return source;
	}

	public Source getSource(String name, String encoding)
			throws IOException {
		Source source = sourceLoader.getSource(cleanName(name), encoding);
		if (source == null)
			throw new IOException("Not found template source: " + name);
		return source;
	}

	public Source getSource(String name, Locale locale) throws IOException {
		Source source = sourceLoader.getSource(cleanName(name), locale);
		if (source == null)
			throw new IOException("Not found template source: " + name);
		return source;
	}

	public Source getSource(String name, Locale locale, String encoding)
			throws IOException {
		Source source = sourceLoader.getSource(cleanName(name), locale, encoding);
		if (source == null)
			throw new IOException("Not found template source: " + name);
		return source;
	}

	// 代理TemplateParser ----

	private final TemplateParser templateParser;

	public Expression parseExpression(String expression) throws ParsingException {
		return templateParser.parseExpression(expression);
	}

	public Template parseTemplate(String template) throws ParsingException {
		return templateParser.parseTemplate(template);
	}
	
	public Template parseTemplate(String name, String template)
			throws ParsingException {
		return templateParser.parseTemplate(name, template);
	}

	public Template parseTemplate(Source source)
			throws ParsingException, IOException {
		return templateParser.parseTemplate(source);
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
