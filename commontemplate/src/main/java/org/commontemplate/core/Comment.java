package org.commontemplate.core;

/**
 * 注释块
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class Comment extends Content {

	public static final String TYPE = "Comment";

	public String getType() {
		return TYPE;
	}

	// 语义的默认实现
	public void render(Context context) throws RenderingException {
		// do nothing
	}

}
