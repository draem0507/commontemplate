package org.commontemplate.core;

import java.io.IOException;

/**
 * 静态文本元素
 * 
 * @author liangfei0201@163.com
 *
 */
public abstract class Text extends Content {

	// final: 只允许输出固定文本, 保证语义正确
	public final void render(Context context) throws RenderingException {
		try {
			context.output(getValue());
		} catch (IOException e) {
			throw new RenderingException(this, e);
		}
	}
	
}
