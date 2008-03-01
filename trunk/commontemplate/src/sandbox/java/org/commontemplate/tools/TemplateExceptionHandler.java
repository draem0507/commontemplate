package org.commontemplate.tools;

import java.io.IOException;

import org.commontemplate.core.TemplateException;

/**
 * 异常信息输出格式化器
 * @author liangfei0201@163.com
 *
 */
public interface TemplateExceptionHandler {
	
	/**
	 * 格式化输出异常信息
	 * @param exception 异常信息
	 */
	public void handleTemplateException(TemplateException exception) throws IOException, TemplateException;

} 
