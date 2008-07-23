package org.commontemplate.engine;

import java.io.IOException;
import java.util.List;

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
import org.commontemplate.core.TemplateFactory;
import org.commontemplate.core.TemplateParser;
import org.commontemplate.core.Text;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.Variable;
import org.commontemplate.util.Assert;
import org.commontemplate.util.ResourceEntry;
import org.commontemplate.util.UrlUtils;

/**
 * 模板工厂实现
 * <p/>
 * (内同步，线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
final class TemplateFactoryImpl implements TemplateFactory {

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
	TemplateFactoryImpl(TemplateParser templateParser, ResourceLoader resourceLoader,
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
		return cache(filterName(name), null);
	}

	public Template getTemplate(String name, String encoding) throws IOException, ParsingException {
		return cache(filterName(name), encoding);
	}

	// 缓存同步 -------

	private final ReloadController reloadController;

	private final ResourceComparator resourceComparator;

	private final Cache cache;

	private Template cache(String name, String encoding)
			throws IOException, ParsingException {
		if (cache == null)
			return parseTemplate(load(name, encoding));

		ResourceEntry entry = (ResourceEntry)cache.get(name);
		if (entry == null) {
			// 缓存总锁，锁定缓存中各条目获取过程
			synchronized(cache) {
				entry = (ResourceEntry)cache.get(name);
				if(entry == null) { // 双重检查，因entry是线程栈内定义的，所以双重检查是有效的
					entry = new ResourceEntry();
					cache.put(name, entry);
				}
				//assert(entry != null); // 后验条件
			}
		}

		// 单条目锁，锁定资源获取过程
		synchronized (entry) {
			Template template = (Template)entry.get();
			if (template == null) { // 不存在，解析加载
				Resource resource = load(name, encoding);
				template = parseTemplate(resource);
				entry.set(template);
			} else { // 已存在，检查热加载
				if (reloadController != null && reloadController.shouldReload(name)) { // 是否需要检查热加载
					Resource resource = load(name, encoding);
					if (resourceComparator != null && resourceComparator.isModified(template, resource)) { // 比较是否已更改
						template = parseTemplate(resource);
						entry.set(template);
					}
				}
			}
			//assert(template != null); // 后验条件
			return template;
		}
	}

	// 加载 --------

	private final ResourceLoader resourceLoader;

	private Resource load(String name, String encoding) throws IOException {
		if(encoding == null)
			return loadResource(name);
		return loadResource(name, encoding);
	}

	// 代理TemplateLoader -------

	public Resource loadResource(String name) throws IOException {
		return resourceLoader.loadResource(filterName(name));
	}

	public Resource loadResource(String name, String encoding)
			throws IOException {
		return resourceLoader.loadResource(filterName(name), encoding);
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

	public ExpressionBuilder getExpressionBuilder() {
		return templateParser.getExpressionBuilder();
	}

	public TemplateBudiler getTemplateBudiler(String templateName) {
		return templateParser.getTemplateBudiler(templateName);
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
