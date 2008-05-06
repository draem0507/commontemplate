package org.commontemplate.ext.directive.include;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.tools.web.WebContext;
import org.commontemplate.util.UrlCleaner;

/**
 * 抓取网络页面
 *
 * @author liangfei0201@163.com
 *
 */
public class SnatchDirectiveHandler implements LineDirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param)
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
				throw new IllegalArgumentException(
						"snatch指令参数错误,必需为字符串!如:$snatch{'xxx.jsp'}或$snatch{'xxx.jsp','utf-8'}");
			}
		} else {
			throw new IllegalArgumentException(
					"snatch指令参数错误,必需为字符串!如:$snatch{'xxx.jsp'}或$snatch{'xxx.jsp','utf-8'}");
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
			context.output("[Snatch Error: " + e.getMessage() + "]");
		} finally {
			if (conn != null)
				conn.disconnect();
		}
	}

	private String parseUrl(Context context, String url)
			throws MalformedURLException {
		if (url != null && url.length() > 0
				&& url.indexOf(UrlCleaner.PROTOCOL_SEPARATOR) == -1) {
			if (context instanceof WebContext) {
				HttpServletRequest request = ((WebContext) context)
						.getRequest();
				if (request != null) {
					String root;
					if (url.charAt(0) == UrlCleaner.PATH_SEPARATOR_CHAR) {
						root = getRoot(request.getRequestURL().toString(),
								request.getContextPath());
					} else {
						root = UrlCleaner.getDirectory(request.getRequestURL()
								.toString());
					}
					return UrlCleaner.clean(root + url);
				}
			}
		}
		return url;
	}

	private String getRoot(String url, String context) {
		int idx = UrlCleaner.getDomainIndex(url);
		if (idx > 0)
			return url.substring(0, idx
					+ (context == null ? 0 : context.length()));
		return "";
	}
}
