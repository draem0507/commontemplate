package org.commontemplate.engine.template;

import java.io.Serializable;

import org.commontemplate.core.Template;

/**
 * 模板缓存条目
 *
 * @author liangfei0201@163.com
 *
 */
class TemplateCacheEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	private Template template;

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

}