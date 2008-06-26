package org.commontemplate.tools.web.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.commontemplate.tools.web.EngineHolder;

/**
 * 模板引擎Servlet
 *
 * <p/>
 * 使用示例：(web.xml配置)
 * <pre>
 * &lt;servlet&gt;
 *     &lt;servlet-name&gt;commontemplate&lt;/servlet-name&gt;
 *     &lt;servlet-class&gt;org.commontemplate.tools.web.servlet.TemplateServlet&lt;/servlet-class&gt;
 *     &lt;load-on-startup&gt;1&lt;/load-on-startup&gt;
 * &lt;/servlet&gt;
 * &lt;servlet-mapping&gt;
 *     &lt;servlet-name&gt;commontemplate&lt;/servlet-name&gt;
 *     &lt;url-pattern&gt;*.ctl&lt;/url-pattern&gt;
 * &lt;/servlet-mapping&gt;
 * </pre>
 * 自动在/WEB-INF/目录及ClassPath中查找配置：commontemplate.properties
 *
 * 另外，需要在web.xml中配置启动模板引擎，参见<code>org.commontemplate.tools.web.EngineInitializeListener</code>
 * @see org.commontemplate.tools.web.EngineInitializeListener
 * @author liangfei0201@163.com
 *
 */
public class TemplateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EngineHolder.renderTemplate(
				getTemplatePath(request),
				getTemplateEncoding(request),
				request, response, getLocale(request), null);
		response.flushBuffer();
	}

	public static final String INCLUDE_PATH_INFO = "javax.servlet.include.path_info";

	public static final String INCLUDE_SERVLET_PATH = "javax.servlet.include.servlet_path";

	/**
	 * 子类可通过覆写该函数重设模板路径的获取规则
	 */
	protected String getTemplatePath(HttpServletRequest request) throws ServletException, IOException {
		String path = null;
        path = (String) request.getAttribute(INCLUDE_PATH_INFO);
        if (path == null || path.length() == 0)
            path = request.getPathInfo();
        if (path == null || path.length() == 0)
        	path = (String) request.getAttribute(INCLUDE_SERVLET_PATH);
        if (path == null || path.length() == 0)
            path = request.getServletPath();
        return path;
	}

	/**
	 * 子类可通过覆写该函数重设模板加载编码
	 */
	protected String getTemplateEncoding(HttpServletRequest request) throws ServletException, IOException {
		return null;
	}

	/**
	 * 子类可通过覆写该函数重设国际化地区
	 */
	protected Locale getLocale(HttpServletRequest request) throws ServletException, IOException {
		return request.getLocale();
	}

}
