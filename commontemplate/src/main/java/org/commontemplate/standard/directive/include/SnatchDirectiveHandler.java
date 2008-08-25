package org.commontemplate.standard.directive.include;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.tools.web.WebContext;
import org.commontemplate.util.I18nExceptionFactory;
import org.commontemplate.util.I18nMessages;
import org.commontemplate.util.UrlUtils;

/**
 * 抓取网络页面
 *
 * @author liangfei0201@163.com
 *
 */
public class SnatchDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	protected void doRender(Context context, String directiveName, Object param)
			throws Exception {
		String urlString;
		String encode;
		if (param instanceof String) {
			urlString = parseUrl(context, (String) param);
			encode = null;
		} else if (param instanceof List) {
			List params = (List) param;
			if (params.size() == 2 && params.get(0) instanceof String
					&& params.get(1) instanceof String) {
				urlString = parseUrl(context, (String) params.get(0));
				encode = (String) params.get(1);
			} else {
				throw I18nExceptionFactory.createIllegalArgumentException("SnatchDirectiveHandler.parameter.error");
			}
		} else {
			throw I18nExceptionFactory.createIllegalArgumentException("SnatchDirectiveHandler.parameter.error");
		}
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		try {
			conn.setRequestMethod("GET");
			conn.setDoOutput(false);
			conn.setDoInput(true);
			Reader reader = null;
			try {
				if (encode == null)
					encode = conn.getContentEncoding();
				if (encode == null)
					reader = new InputStreamReader(conn.getInputStream());
				else
					reader = new InputStreamReader(conn.getInputStream(),
							encode);
				int len = -1;
				char[] buf = new char[8196];
				while ((len = reader.read(buf)) > 0) {
					context.getOut().write(buf, 0, len);
				}
			} finally {
				if (reader != null)
					reader.close();
			}
		} catch (IOException e) {
			context.output(I18nMessages.getMessage("SnatchDirectiveHandler.snatch.error", new Object[]{e.getMessage()}));
		} finally {
			if (conn != null)
				conn.disconnect();
		}
	}

	private String parseUrl(Context context, String url)
			throws MalformedURLException {
		if (url != null && url.length() > 0
				&& url.indexOf(UrlUtils.PROTOCOL_SEPARATOR) == -1) {
			if (context instanceof WebContext) {
				HttpServletRequest request = ((WebContext) context)
						.getRequest();
				if (request != null) {
					String root;
					if (url.charAt(0) == UrlUtils.PATH_SEPARATOR_CHAR) {
						root = getRoot(request.getRequestURL().toString(),
								request.getContextPath());
					} else {
						root = UrlUtils.getDirectoryName(request.getRequestURL()
								.toString());
					}
					return UrlUtils.cleanUrl(root + url);
				}
			}
		}
		return url;
	}

	private String getRoot(String url, String context) {
		int idx = UrlUtils.getDomainIndex(url);
		if (idx > 0)
			return url.substring(0, idx
					+ (context == null ? 0 : context.length()));
		return "";
	}

	protected boolean isExpressionRequired() {
		return true;
	}

}
