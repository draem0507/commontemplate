package org.commontemplate.core;

import java.io.Serializable;

/**
 * 模板接口.
 * <p/>
 * (线程安全)
 *
 * @see org.commontemplate.core.TemplateFactory
 * @see org.commontemplate.core.Factory
 * @author liangfei0201@163.com
 *
 */
public abstract class Template extends Resource implements Block, Serializable {

	public static final String TYPE = "Template";

	public String getType() {
		return TYPE;
	}

}