package org.commontemplate.standard.filter;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;

import org.commontemplate.config.SourceFilter;
import org.commontemplate.util.IOUtils;

/**
 * Reader转为String过滤
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class StringSourceFilter implements SourceFilter, Serializable {

	private static final long serialVersionUID = 1L;
	
	public Reader filter(Reader reader) throws IOException {
		return new StringReader(filter(IOUtils.readToString(reader)));
	}

	protected abstract String filter(String text);

}
