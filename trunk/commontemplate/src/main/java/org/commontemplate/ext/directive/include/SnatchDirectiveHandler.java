package org.commontemplate.ext.directive.include;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.core.Context;

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
			urlString = parseUrl(context, (String)param);
			encode = null;
		} else if (param instanceof List) {
			List params = (List)param;
			if (params.size() == 2
					&& params.get(0) instanceof String
					&& params.get(1) instanceof String) {
				urlString = parseUrl(context, (String)params.get(0));
				encode = (String)params.get(1);
			} else {
				throw new IllegalArgumentException("snatch指令参数错误,必需为字符串!如:$snatch{'xxx.jsp'}或$snatch{'xxx.jsp','utf-8'}");
			}
		} else {
			throw new IllegalArgumentException("snatch指令参数错误,必需为字符串!如:$snatch{'xxx.jsp'}或$snatch{'xxx.jsp','utf-8'}");
		}
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		try {
			conn.setRequestMethod("GET");
			conn.setDoOutput(false);
			conn.setDoInput(true);
			BufferedReader br = null;
			try {
				if (encode == null)
					br = new BufferedReader(new InputStreamReader(conn.getInputStream(), conn.getContentEncoding()));
				else
					br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encode));
				for (String line = br.readLine(); line != null; line = br.readLine()) {
					context.output(line);
				}
			} finally {
				if (br != null)
					br.close();
			}
		} finally {
			if (conn != null)
				conn.disconnect();
		}
	}

	private String parseUrl(Context context, String url) {
		if (url.indexOf("://") > 0)
			return url;
		return "http://localhost/" + url; // FIXME 当前URL的设定
	}
}
