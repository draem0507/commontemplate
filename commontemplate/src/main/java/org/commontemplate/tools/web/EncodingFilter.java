package org.commontemplate.tools.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 字符编码过滤器.
 * <p/>
 * 使用方式，配置web.xml:
 * <pre>
 * &lt;filter&gt;
 *     &lt;filter-name&gt;encoding&lt;/filter-name&gt;
 *     &lt;filter-class&gt;org.commontemplate.tools.web.EncodingFilter&lt;/filter-class&gt;
 *     &lt;init-param&gt;
 *         &lt;param-name&gt;encoding&lt;/param-name&gt;
 *         &lt;param-value&gt;UTF-8&lt;/param-value&gt;
 *     &lt;/init-param&gt;
 * &lt;/filter&gt;
 * </pre>
 * 
 * @author liangfei0201@163.com
 *
 */
public class EncodingFilter implements Filter {
	
	private String requestEncoding = null;
	
	private String responseEncoding = null;
	
	private String contextType = "text/html";
	
	public void init(FilterConfig config) throws ServletException {
		requestEncoding = config.getInitParameter("requestEncoding");
		responseEncoding = config.getInitParameter("responseEncoding");
		String encoding = config.getInitParameter("encoding"); // 同时设置requestEncoding和responseEncoding
		if (encoding != null) {
			if (requestEncoding == null)
				requestEncoding = encoding;
			if (responseEncoding == null)
				responseEncoding = encoding;
		}
		String type = config.getInitParameter("contextType");
		if (type != null) 
			type = type.trim();
		if (type == null || type.length() == 0)
			type = "text/html";
		if (responseEncoding != null)
			contextType = type + ";charset=" + responseEncoding;
		else 
			contextType = type;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (requestEncoding != null)
			request.setCharacterEncoding(requestEncoding);
		if (responseEncoding != null) 
			response.setCharacterEncoding(responseEncoding);
		response.setContentType(contextType);
		chain.doFilter(request, response);
	}

	public void destroy() {
		requestEncoding = null;
		responseEncoding = null;
	}

}
