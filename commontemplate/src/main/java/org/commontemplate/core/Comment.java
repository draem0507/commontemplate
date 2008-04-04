package org.commontemplate.core;

/**
 * 注释块
 * 
 * @author liangfei0201@163.com
 *
 */
public abstract class Comment extends Content {

	// final: 不允许处理任何任务, 保证语义正确
	public final void render(Context context) throws RenderingException {
		// do nothing
	}

}
