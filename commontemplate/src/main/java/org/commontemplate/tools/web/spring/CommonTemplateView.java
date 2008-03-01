package org.commontemplate.tools.web.spring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.commontemplate.tools.web.EngineHolder;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.AbstractTemplateView;

/**
 * 与SpringMVC集成视图.
 * 
 * @author liangfei0201@163.com
 *
 */
public class CommonTemplateView extends AbstractTemplateView {

	private String encoding;

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	protected void renderMergedTemplateModel(Map model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EngineHolder.renderTemplate(getUrl(), getEncoding(), request, response, RequestContextUtils.getLocale(request), model);
	}

}
