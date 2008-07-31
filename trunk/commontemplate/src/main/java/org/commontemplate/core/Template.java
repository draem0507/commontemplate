package org.commontemplate.core;

import java.io.Serializable;
import java.util.List;

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
public abstract class Template extends Resource implements Node, Serializable {

	public static final String TYPE = "Template";

	public String getType() {
		return TYPE;
	}

	/**
	 * 获取模板/块组成元素
	 *
	 * @return 模板/块组成元素, 类型: List&lt;Element&gt;
	 */
	public abstract List getElements();

}