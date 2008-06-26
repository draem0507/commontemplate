package org.commontemplate.tools.web.struts2;

import java.util.Locale;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import org.commontemplate.tools.web.EngineHolder;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.LocaleProvider;

/**
 * 与Struts2集成的供给方案.
 * <p/>
 * 在struts2.xml中配置：
 * <pre>
 * &lt;result-types&gt;
 *     &lt;result-type default="true" name="commontemplate" class="org.commontemplate.tools.web.struts2.TemplateResult" /&gt;
 * &lt;/result-types&gt;
 * </pre>
 *
 * @author liangfei0201@163.com
 *
 */
public class TemplateResult extends StrutsResultSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * 标准struts2配置路径
	 */
	public static final String STANDARD_CONFIG_PATH = "org/commontemplate/tools/web/struts2/commontemplate.properties";

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
