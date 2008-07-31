package org.commontemplate.tools.web;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.commontemplate.config.Configuration;
import org.commontemplate.core.Context;
import org.commontemplate.core.Factory;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Template;
import org.commontemplate.engine.Engine;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.tools.bean.BeanFactory;
import org.commontemplate.tools.bean.ComboResourceLoader;
import org.commontemplate.tools.bean.PropertiesBeanFactory;
import org.commontemplate.util.Assert;
import org.commontemplate.util.I18nMessages;

/**
 * 引擎持有者
 *
 * @author liangfei0201@163.com
 *
 */
public final class EngineHolder {

	private EngineHolder() {}

	/**
	 * 搜索配置web.xml中的初始化参数名
	 */
	public static final String CONFIG_PARAM_NAME = "commontemplate-config";

	/**
	 * 默认搜索配置Webapp路径
	 */
	public static final String DEFAULT_CONFIG_WEB_PATH = "/WEB-INF/commontemplate.properties";

	/**
	 * 默认搜索配置Classpath路径
	 */
	public static final String DEFAULT_CONFIG_CLASS_PATH = "commontemplate.properties";

	/**
	 * 标准web配置路径
	 */
	public static final String STANDARD_CONFIG_PATH = "org/commontemplate/tools/web/commontemplate.properties";

	/**
	 * 判断引擎是否已初始化
	 * (此方法未同步,请保证在单线程下调用或自行同步.)
	 *
	 * @return 是否已初始化
	 */
	public static final boolean isInitialized() {
		return engine != null;
	}

	/**
	 * 初始化引擎. 并自动搜索配置文件
	 * (此方法未同步,请保证在单线程下调用或自行同步.)
	 *
	 * @param servletContext 当前运行所在Servlet容器上下文
	 */
	public static final void init(ServletContext servletContext) {
		Assert.assertNotNull(servletContext, "EngineHolder.servlet.context.required");
		// 初始化配置路径
		String propertiesPath = servletContext.getInitParameter(CONFIG_PARAM_NAME); // 查找web.xml中的配置
		if (propertiesPath != null)
			propertiesPath = propertiesPath.trim();
		if (propertiesPath == null || propertiesPath.length() == 0) { // 查找/WEB-INF/
			if (new File(servletContext.getRealPath("/") + DEFAULT_CONFIG_WEB_PATH).exists())
				propertiesPath = DEFAULT_CONFIG_WEB_PATH;
		}
		if (propertiesPath == null || propertiesPath.length() == 0) { // 查找ClassPath
			URL url = Thread.currentThread().getContextClassLoader().getResource(DEFAULT_CONFIG_CLASS_PATH);
			if (url != null && new File(url.getFile()).exists())
				propertiesPath = DEFAULT_CONFIG_CLASS_PATH;
		}
		if (propertiesPath == null || propertiesPath.length() == 0) { // 使用标准配置
			propertiesPath = STANDARD_CONFIG_PATH;
		}
		servletContext.log("[commontemplate] searched config: " + propertiesPath);
		init(servletContext, propertiesPath);
	}

	/**
	 * 初始化引擎.
	 * (此方法未同步,请保证在单线程下调用或自行同步.)
	 *
	 * @param servletContext 当前运行所在Servlet容器上下文
	 * @param propertiesPath 配置路径
	 */
	public static final void init(ServletContext servletContext, String propertiesPath) {
		Assert.assertNotNull(servletContext, "EngineHolder.servlet.context.required");
		Assert.assertNotNull(propertiesPath, "EngineHolder.properties.path.required");
		doInit(servletContext, new PropertiesBeanFactory(propertiesPath.trim(), new ComboResourceLoader(servletContext), getVariables(servletContext)));
	}

	/**
	 * 初始化引擎.
	 * (此方法未同步,请保证在单线程下调用或自行同步.)
	 *
	 * @param servletContext 当前运行所在Servlet容器上下文
	 * @param properties 配置信息
	 */
	public static final void init(ServletContext servletContext, Properties properties) {
		Assert.assertNotNull(servletContext, "EngineHolder.servlet.context.required");
		Assert.assertNotNull(properties, "EngineHolder.properties.required");
		doInit(servletContext, new PropertiesBeanFactory(properties, new ComboResourceLoader(servletContext), getVariables(servletContext)));
	}

	private static final void doInit(ServletContext servletContext, BeanFactory beanFactory) {
		servletContext.log(I18nMessages.getMessage("EngineHolder.initializing"));
		long start = System.currentTimeMillis();
		EngineHolder.servletContext = servletContext;
		EngineHolder.beanFactory = beanFactory;
		EngineHolder.config = PropertiesConfigurationLoader.loadConfiguration(EngineHolder.beanFactory);
		EngineHolder.engine = new Engine(EngineHolder.config);
		servletContext.log(I18nMessages.getMessage("EngineHolder.initialized", new Object[]{new Long((System.currentTimeMillis() - start))}));
	}

	private static final Map getVariables(ServletContext servletContext) {
		Map variables = new HashMap();
		variables.put("servletContext", servletContext);
		variables.put("servletContext.rootPath", servletContext.getRealPath("/"));
		variables.put("servletContext.serverInfo", servletContext.getServerInfo());
		variables.put("servletContext.version", servletContext.getMajorVersion() + "." + servletContext.getMinorVersion());
		return variables;
	}

	/**
	 * 销毁持有的引擎.
	 * (此方法未同步,请保证在单线程下调用或自行同步.)
	 */
	public static final void destroy() {
		EngineHolder.servletContext = null;
		EngineHolder.config = null;
		EngineHolder.engine = null;
	}

	private static ServletContext servletContext;

	/**
	 * 获取初始化引擎的ServletContext
	 *
	 * @return ServletContext
	 * @throws NullPointerException
	 */
	public static final ServletContext getServletContext() throws NullPointerException {
		checkInitialized();
		return servletContext;
	}

	private static BeanFactory beanFactory;

	/**
	 * 获取初始化引擎的配置
	 *
	 * @return 配置
	 * @throws NullPointerException
	 */
	public static final BeanFactory getBeanFactory() throws NullPointerException {
		checkInitialized();
		return beanFactory;
	}

	private static Configuration config;

	/**
	 * 获取初始化引擎的配置
	 *
	 * @return 配置
	 * @throws NullPointerException
	 */
	public static final Configuration getConfiguration() throws NullPointerException {
		checkInitialized();
		return config;
	}

	private static Factory engine;

	/**
	 * 获取引擎
	 *
	 * @return 引擎
	 * @throws NullPointerException 引擎未初始化时抛出
	 */
	public static final Factory getEngine() throws NullPointerException {
		checkInitialized();
		return engine;
	}

	// 检查是否已初始化完成
	private static final void checkInitialized() throws NullPointerException {
		Assert.assertNotNull(engine, "EngineHolder.not.initialized", new Object[]{EngineInitializeListener.class.getName()});
	}

	/**
	 * 创建上下文
	 *
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return 上下文
	 * @throws IOException 调用response的Writer出错时抛出
	 */
	public static final WebContext createContext(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return createContext(request, response, null);
	}

	/**
	 * 创建上下文，并指定地区信息
	 *
	 * @param request 请求信息
	 * @param response 响应信息
	 * @param locale 地区信息
	 * @return 上下文
	 * @throws IOException 调用response的Writer出错时抛出
	 */
	public static final WebContext createContext(HttpServletRequest request, HttpServletResponse response, Locale locale) throws IOException {
		return createContext(request, response, locale, null);
	}

	private static final String APPLICATION_SCOPE_NAME = "application";

	private static final String COOKIE_SCOPE_NAME = "cookie";

	private static final String SESSION_SCOPE_NAME = "session";

	private static final String HEADER_SCOPE_NAME = "header";

	private static final String PARAMETER_SCOPE_NAME = "parameter";

	private static final String REQUEST_SCOPE_NAME = "request";

	private static final String MODEL_SCOPE_NAME = "model";

	/**
	 * 创建上下文，并供给模型数据
	 *
	 * @param request 请求信息
	 * @param response 响应信息
	 * @param locale 地区信息
	 * @param model 模型数据
	 * @return 上下文
	 * @throws IOException 调用response的Writer出错时抛出
	 */
	public static final WebContext createContext(HttpServletRequest request, HttpServletResponse response, Locale locale, Object model) throws IOException {
		Assert.assertNotNull(request, "EngineHolder.request.required");
		Assert.assertNotNull(response, "EngineHolder.response.required");
		if (locale == null)
			locale = request.getLocale();
		Context context = getEngine().createContext(response.getWriter(), locale);
		context.pushLocalContext(APPLICATION_SCOPE_NAME, new ApplicationMap(request));
		context.pushLocalContext(COOKIE_SCOPE_NAME, new CookieMap(request));
		context.pushLocalContext(SESSION_SCOPE_NAME, new SessionMap(request));
		context.pushLocalContext(HEADER_SCOPE_NAME, new HeaderMap(request));
		context.pushLocalContext(PARAMETER_SCOPE_NAME, new ParameterMap(request));
		context.pushLocalContext(REQUEST_SCOPE_NAME, new RequestMap(request));
		if (model == null)
			context.pushLocalContext(MODEL_SCOPE_NAME);
		else if (model instanceof Map)
			context.pushLocalContext(MODEL_SCOPE_NAME, (Map)model);
		else
			context.pushLocalContext(MODEL_SCOPE_NAME, new ModelMap(model));
		return new WebContext(context, request, response);
	}

	/**
	 * 呈现模板
	 *
	 * @param templateName 模板名称
	 * @param context 上下文
	 * @throws IOException 模板不存在，或读取模板出错时抛出
	 * @throws ParsingException 模板格式错误时抛出
	 * @throws RenderingException 模板运行出错时抛出
	 */
	public static final void renderTemplate(String templateName, Context context) throws IOException, ParsingException, RenderingException {
		renderTemplate(templateName, null, context);
	}

	/**
	 * 呈现模板
	 *
	 * @param templateName 模板名称
	 * @param templateEncoding 模板编码
	 * @param context 上下文
	 * @throws IOException 模板不存在，或读取模板出错时抛出
	 * @throws ParsingException 模板格式错误时抛出
	 * @throws RenderingException 模板运行出错时抛出
	 */
	public static final void renderTemplate(String templateName, String templateEncoding, Context context) throws IOException, ParsingException, RenderingException {
		renderTemplate(getEngine().getTemplate(templateName, templateEncoding), context);
	}

	/**
	 * 呈现模板, 并设置response编码
	 *
	 * @param templateName 模板名称
	 * @param templateEncoding 模板编码, 可以为null, 表示用默认编码加载
	 * @param request 请求信息
	 * @param response 响应信息
	 * @param locale 地区信息, 可以为null, 将使用request.getLocale()
	 * @param model 模型数据, 可以为null
	 * @throws IOException 模板不存在，或读取模板出错时抛出
	 * @throws ParsingException 模板格式错误时抛出
	 * @throws RenderingException 模板运行出错时抛出
	 */
	public static final void renderTemplate(String templateName, String templateEncoding, HttpServletRequest request, HttpServletResponse response, Locale locale, Object model) throws IOException, ParsingException, RenderingException {
		Assert.assertNotNull(templateName, "EngineHolder.template.name.required");
		Assert.assertNotNull(request, "EngineHolder.request.required");
		Assert.assertNotNull(response, "EngineHolder.response.required");
		Template template = getEngine().getTemplate(templateName, templateEncoding);
		// 设置响应编码信息
		String encoding = template.getEncoding();
		String type = (String)getBeanFactory().getBean("response.contentType");
		if (type == null) {
			type = "text/html";
		}
		if (encoding != null) {
			type += "; charset=" + encoding;
			response.setCharacterEncoding(encoding);
		}
		response.setContentType(type);
		renderTemplate(template, createContext(request, response, locale, model));
	}

	/**
	 * 呈现模板.
	 *
	 * @param template 模板
	 * @param context 上下文
	 * @throws IOException 模板不存在，或读取模板出错时抛出
	 * @throws ParsingException 模板格式错误时抛出
	 * @throws RenderingException 模板运行出错时抛出
	 */
	private static final void renderTemplate(Template template, Context context) throws IOException, ParsingException, RenderingException {
		Assert.assertNotNull(template, "EngineHolder.template.required");
		Assert.assertNotNull(context, "EngineHolder.context.required");
		try {
			template.render(context);
		} finally {
			context.clear();
		}
	}

}
