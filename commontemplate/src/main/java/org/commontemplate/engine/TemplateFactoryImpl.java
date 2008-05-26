package org.commontemplate.engine;

import java.io.IOException;

import org.commontemplate.config.ReloadController;
import org.commontemplate.config.Cache;
import org.commontemplate.config.ResourceComparator;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateFactory;
import org.commontemplate.core.TemplateParser;
import org.commontemplate.core.Resource;
import org.commontemplate.core.ResourceLoader;
import org.commontemplate.util.Assert;
import org.commontemplate.util.ResourceEntry;

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

		ResourceEntry entry;

		// 缓存总锁，锁定缓存中各条目获取过程
		synchronized(cache) {
			entry = (ResourceEntry)cache.get(name);
			if(entry == null) {
				entry = new ResourceEntry();
				cache.put(name, entry);
			}
			//assert(entry != null); // 后验条件
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

	private String filterName(String name) {
		if (name == null)
			throw new java.lang.NullPointerException("TemplateFactoryImpl.template.name.required");
		name = name.trim();
		if (name.length() == 0)
			throw new java.lang.NullPointerException("TemplateFactoryImpl.template.name.required");
		char leader = name.charAt(0);
		if (leader != '/' && leader != '\\')
			name = "/" + name;
		return name;
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

}