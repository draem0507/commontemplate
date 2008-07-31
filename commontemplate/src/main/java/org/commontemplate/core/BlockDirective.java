package org.commontemplate.core;

import java.util.List;

/**
 * 块指令 <p/> (线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class BlockDirective extends Directive {

	public static final String TYPE = "BlockDirective";

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
