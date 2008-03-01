package org.commontemplate.tools;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.commontemplate.config.Configuration;
import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.engine.Engine;

/**
 * 模板求值工具
 * 
 * <p/>
 * 使用示例:
 * <pre>
 * String result = new TemplateRenderer("$for(times) ${user.name} $end").put("times", 5).put("user", new User()).evaluate();
 * 或者:
 * Writer out = ...;
 * new TemplateRenderer("$for(times) ${user.name} $end").put("times", 5).put("user", new User()).render(out);
 * </pre>
 * 
 * <p/>
 * 配置初始化:
 * <pre>
 * TemplateRenderer templateRenderer = new TemplateRenderer("template", PropertiesConfigurationLoader.loadConfiguration("xxx.properties"));
 * </pre>
 * 
 * @see org.commontemplate.tools.PropertiesConfigurationLoader#loadConfiguration
 * @author liangfei0201@163.com
 *
 */
public class TemplateRenderer {
	
	private Template template;
	
	private final Engine engine;

	public TemplateRenderer(String templateText) {
		this(templateText, PropertiesConfigurationLoader.loadStandardConfiguration());
	}

	public TemplateRenderer(String templateText, Configuration config) {
		engine = new Engine(config);
		this.template = engine.parseTemplate(templateText);
	}
	
	public String evaluate() {
		StringWriter out = new StringWriter();
		render(out);
		String result = out.getBuffer().toString();
		out.getBuffer().setLength(0);
		return result;
	}

	/**
	 * 解析并运行模板
	 * 
	 * @return 解析结果
	 */
	public void render(Writer out) {
		Context context = engine.createContext(out);
		template.render(context);
	}
	
	// 变量设置管理接口 ---------

	private final Map variables = new HashMap();

	public Object get(String key) {
		return variables.get(key);
	}
	
	public void remove(String key) {
		variables.remove(key);
	}
	
	public void clear() {
		variables.clear();
	}
	
	// ==== 注：以下函数为了调用者方便，采用链式调用风格，违背“契约式设计原则”，按原则应为void返回类型 ====

	public TemplateRenderer putAll(Map values) {
		variables.putAll(values);
		return this;
	}
	
	public TemplateRenderer put(String key, Object value) {
		variables.put(key, value);
		return this;
	}
	
	public TemplateRenderer put(String key, char value) {
		variables.put(key, new Character(value));
		return this;
	}
	
	public TemplateRenderer put(String key, boolean value) {
		variables.put(key, Boolean.valueOf(value));
		return this;
	}
	
	public TemplateRenderer put(String key, byte value) {
		variables.put(key, new Byte(value));
		return this;
	}
	
	public TemplateRenderer put(String key, short value) {
		variables.put(key, new Short(value));
		return this;
	}
	
	public TemplateRenderer put(String key, int value) {
		variables.put(key, new Integer(value));
		return this;
	}
	
	public TemplateRenderer put(String key, long value) {
		variables.put(key, new Long(value));
		return this;
	}
	
	public TemplateRenderer put(String key, float value) {
		variables.put(key, new Float(value));
		return this;
	}
	
	public TemplateRenderer put(String key, double value) {
		variables.put(key, new Double(value));
		return this;
	}
}
