package org.commontemplate.standard.filter;

import java.io.Serializable;
import java.net.MalformedURLException;

import org.commontemplate.config.InvalidTemplateNameException;
import org.commontemplate.config.TemplateNameFilter;
import org.commontemplate.util.UrlUtils;

/**
 * 路径关联器. 注册此类, 才可以在模板中使用相对路径.
 *
 * @author liangfei0201@163.com
 *
 */
public class TemplateNameRelativer implements TemplateNameFilter, Serializable {

	private static final long serialVersionUID = 1L;

	public String filter(String templateName, String currentTemplateName) throws InvalidTemplateNameException {
		try {
			if (templateName == null)
				throw new InvalidTemplateNameException(null, "TemplateNameRelativer.template.name.required");
			return UrlUtils.relativeUrl(templateName, currentTemplateName);
		} catch (MalformedURLException e) {
			throw new InvalidTemplateNameException(e.getMessage());
		}
	}

}
