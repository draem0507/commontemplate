package org.commontemplate.core;

/**
 * 块指令 <p/> (线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class BlockDirective extends Directive implements Block {

	public static final String TYPE = "BlockDirective";

	public String getType() {
		return TYPE;
	}

}
