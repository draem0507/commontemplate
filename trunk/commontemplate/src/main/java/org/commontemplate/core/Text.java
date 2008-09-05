package org.commontemplate.core;

import java.io.IOException;

/**
 * 静态文本元素
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class Text extends Content {

	// 语义的默认实现
	public void render(Context context) throws RenderingException {
		try {
			context.output(getValue());
		} catch (IOException e) {
			throw new RenderingException(this, context, e);
		}
	}

}
