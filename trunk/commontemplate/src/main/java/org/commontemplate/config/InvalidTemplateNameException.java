package org.commontemplate.config;

/**
 * 错误的模板名称异常
 * 
 * @see org.commontemplate.config.TemplateNameFilter
 * @author liangfei0201@163.com
 * 
 */
public class InvalidTemplateNameException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTemplateNameException(String invalidTemplateName) {
		super();
		this.invalidTemplateName = invalidTemplateName;
	}

	public InvalidTemplateNameException(String message, Throwable cause,
			String invalidTemplateName) {
		super(message, cause);
		this.invalidTemplateName = invalidTemplateName;
	}

	public InvalidTemplateNameException(String message,
			String invalidTemplateName) {
		super(message);
		this.invalidTemplateName = invalidTemplateName;
	}

	public InvalidTemplateNameException(Throwable cause,
			String invalidTemplateName) {
		super(cause);
		this.invalidTemplateName = invalidTemplateName;
	}

	private String invalidTemplateName;

	public String getInvalidTemplateName() {
		return invalidTemplateName;
	}

}
