package org.commontemplate.tools.web.webwork;

import java.util.Locale;

import org.commontemplate.tools.web.EngineHolder;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.dispatcher.WebWorkResultSupport;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.LocaleProvider;

/**
 * 与WebWork集成的供给方案.
 * <p/>
 * 在xwork.xml中配置：
 * <pre>
 * &lt;result-types&gt;
 *     &lt;result-type default="true" name="commontemplate" class="org.commontemplate.tools.web.webwork.TemplateResult" /&gt;
 * &lt;/result-types&gt;
 * </pre>
 *
 * 另外，需要在web.xml中配置启动模板引擎，参见<code>org.commontemplate.tools.web.EngineInitializeListener</code>
 * @see org.commontemplate.tools.web.EngineInitializeListener
 * @author liangfei0201@163.com
 *
 */
public class TemplateResult extends WebWorkResultSupport {

	private static final long serialVersionUID = 1L;

	protected void doExecute(String location, ActionInvocation invocation) throws Exception {
		EngineHolder.renderTemplate(
				getTemplatePath(location, invocation),
				getTemplateEncoding(location, invocation),
				ServletActionContext.getRequest(),
				ServletActionContext.getResponse(),
				getLocale(invocation),
				invocation.getAction());
	}

	protected String getTemplatePath(String location, ActionInvocation invocation) throws Exception {
		return location;
	}

	protected String getTemplateEncoding(String location, ActionInvocation invocation) throws Exception {
		return null;
	}

	protected Locale getLocale(ActionInvocation invocation) throws Exception {
        if (invocation.getAction() instanceof LocaleProvider) {
            return ((LocaleProvider) invocation.getAction()).getLocale();
        } else {
            return ServletActionContext.getRequest().getLocale();
        }
    }

}
