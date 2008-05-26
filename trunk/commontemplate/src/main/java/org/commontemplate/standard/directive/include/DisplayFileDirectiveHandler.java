package org.commontemplate.standard.directive.include;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.util.Assert;
import org.commontemplate.core.Context;

/**
 * 显示文件内容指令, 不作为模板解析.
 *
 * @author liangfei0201@163.com
 *
 */
public class DisplayFileDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param instanceof String) {
			readFile(context, (String)param);
		} else if (param instanceof List) {
			List list = (List)param;
			if (list.size() == 1 && list.get(0) instanceof String) {
				readFile(context, (String)list.get(0));
			} else if (list.size() == 2 && list.get(0) instanceof String && list.get(1) instanceof String) {
				readFile(context, (String)list.get(0), (String)list.get(1));
			} else {
				Assert.fail("DisplayFileDirectiveHandler.parameter.error", new Object[]{param});
			}
		} else {
			Assert.fail("DisplayFileDirectiveHandler.parameter.error", new Object[]{param});
		}
	}

	private void readFile(Context context, String name) throws IOException {
		read(context, context.loadResource(name).getReader());
	}

	private void readFile(Context context, String name, String encoding) throws IOException {
		read(context, context.loadResource(name, encoding).getReader());
	}

	private void read(Context context, Reader reader) throws IOException {
		try {
			char[] buf = new char[1024];
			int len;
			while (-1 != (len = reader.read(buf))) {
				context.output(new String(buf, 0, len));
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
}