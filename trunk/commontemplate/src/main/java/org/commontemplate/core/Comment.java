package org.commontemplate.core;

/**
 * 注释块
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class Comment extends Content {

	private static final long serialVersionUID = 1L;
	
	// 语义的默认实现
	public void render(Context context) throws RenderingException {
		// do nothing
	}

}
