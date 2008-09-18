package org.commontemplate.standard.directive.include;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.Resource;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.util.Assert;

/**
 * 显示文件内容指令, 不作为模板解析.
 *
 * @author liangfei0201@163.com
 *
 */
public class DisplayFileDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	// 是否本地化查找模板, 如: xxx_zh_CN.ctl
	private boolean localizedLookup;

	public boolean isLocalizedLookup() {
		return localizedLookup;
	}

	public void setLocalizedLookup(boolean localizedLookup) {
		this.localizedLookup = localizedLookup;
	}

	private int bufferSize = 4096;

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		if (bufferSize > 0)
			this.bufferSize = bufferSize;
	}

	public void doRender(Context context, String directiveName, Object param)
			throws Exception {
		if (param instanceof String) {
			readFile(context, (String) param);
		} else if (param instanceof List) {
			List list = (List) param;
			if (list.size() == 1 && list.get(0) instanceof String) {
				readFile(context, (String) list.get(0));
			} else if (list.size() == 2 && list.get(0) instanceof String
					&& list.get(1) instanceof String) {
				readFile(context, (String) list.get(0), (String) list.get(1));
			} else {
				Assert.fail("DisplayFileDirectiveHandler.parameter.error",
						new Object[] { param });
			}
		} else {
			Assert.fail("DisplayFileDirectiveHandler.parameter.error",
					new Object[] { param });
		}
	}

	private void readFile(Context context, String name) throws IOException {
		Resource resource;
		if (localizedLookup)
			resource = context.getResource(name, context.getLocale());
		else
			resource = context.getResource(name);

		read(context, resource.getReader());
	}

	private void readFile(Context context, String name, String encoding)
			throws IOException {
		Resource resource;
		if (localizedLookup)
			resource = context
					.getResource(name, context.getLocale(), encoding);
		else
			resource = context.getResource(name, encoding);

		read(context, resource.getReader());
	}

	private void read(Context context, Reader reader) throws IOException {
		try {
			int len;
			char[] buf = new char[bufferSize];
			while (-1 != (len = reader.read(buf)))
				context.output(new String(buf, 0, len));
		} finally {
			reader.close();
		}
	}

	public boolean isExpressionRequired() {
		return true;
	}

}