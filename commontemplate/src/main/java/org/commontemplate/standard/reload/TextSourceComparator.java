package org.commontemplate.standard.reload;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;

import org.commontemplate.config.SourceComparator;
import org.commontemplate.core.Source;
import org.commontemplate.core.Template;

/**
 * 模板源全文比较器
 *
 * @author liangfei0201@163.com
 *
 */
public class TextSourceComparator implements SourceComparator, Serializable {

	private static final long serialVersionUID = 1L;

	public boolean isModified(Template oldSource, Source newSource) throws IOException {
		Reader oldReader = oldSource.getReader();
		Reader newReader = newSource.getReader();
		for(;;) {
			int oldValue = oldReader.read();
			int newValue = newReader.read();
			if (oldValue == -1 || newValue == -1)
				return oldValue == newValue;
			if (oldValue != newValue)
				return false;
		}
	}

}