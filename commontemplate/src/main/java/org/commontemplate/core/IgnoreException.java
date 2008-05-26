package org.commontemplate.core;

import org.commontemplate.util.I18nRuntimeException;

/**
 * 此异常的子类在模板解释过程中将被忽略
 *
 * @author liangfei0201@163.com
 *
 */
public class IgnoreException extends I18nRuntimeException {

	private static final long serialVersionUID = 1L;

	public IgnoreException() {
		super();
	}

}
