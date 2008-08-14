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
	 * 获取块内部元素
	 *
	 * @return 块内部元素, 类型: List&lt;Element&gt;
	 */
	public abstract List getElements();

}
