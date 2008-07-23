package org.commontemplate.standard.filter;

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
public class TemplateNameRelativer implements TemplateNameFilter {

	public String filter(String templateName, String currentTemplateName) throws InvalidTemplateNameException {
		try {
			if (templateName == null)
				throw new InvalidTemplateNameException(null, "TemplateNameRelativer.template.name.required");
			templateName = templateName.replace('\\', '/');
			currentTemplateName = currentTemplateName.replace('\\', '/');
			/*char leader = name.charAt(0);
			if (leader != '/' && leader != '\\')
				name = "/" + name;*/
			if (templateName.trim().charAt(0) == UrlUtils.PATH_SEPARATOR_CHAR) // 根目录开头，不添加当前路径
				return UrlUtils.cleanUrl(templateName);
			return UrlUtils.cleanUrl(UrlUtils.getDirectoryName(currentTemplateName) + templateName);
		} catch (MalformedURLException e) {
			throw new InvalidTemplateNameException(e.getMessage());
		}
	}

}
