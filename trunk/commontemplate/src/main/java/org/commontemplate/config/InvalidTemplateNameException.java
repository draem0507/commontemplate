package org.commontemplate.config;

import org.commontemplate.util.I18nRuntimeException;

/**
 * 错误的模板名称异常
 *
 * @see org.commontemplate.config.TemplateNameFilter
 * @author liangfei0201@163.com
 *
 */
public class InvalidTemplateNameException extends I18nRuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTemplateNameException(String invalidTemplateName) {
		super();
		this.invalidTemplateName = invalidTemplateName;
	}

	public InvalidTemplateNameException(String invalidTemplateName, String message,
			Throwable cause) {
		super(message, cause);
		this.invalidTemplateName = invalidTemplateName;
	}

	public InvalidTemplateNameException(String invalidTemplateName, String message, Object[] args,
			Throwable cause) {
		super(message, args, cause);
		this.invalidTemplateName = invalidTemplateName;
	}

	public InvalidTemplateNameException(String invalidTemplateName,
			String message) {
		super(message);
		this.invalidTemplateName = invalidTemplateName;
	}

	public InvalidTemplateNameException(String invalidTemplateName,
			String message, Object[] args) {
		super(message, args);
		this.invalidTemplateName = invalidTemplateName;
	}

	public InvalidTemplateNameException(String invalidTemplateName,
			Throwable cause) {
		super(cause);
		this.invalidTemplateName = invalidTemplateName;
	}

	private String invalidTemplateName;

	public String getInvalidTemplateName() {
		return invalidTemplateName;
	}

}
