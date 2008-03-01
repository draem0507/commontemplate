package org.commontemplate.tools;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.commontemplate.core.TemplateException;

public class HtmlTemplateExceptionHandler implements TemplateExceptionHandler {
	
	private final Writer writer;
	
	public HtmlTemplateExceptionHandler() {
		this(new OutputStreamWriter(System.err));
	}
	
	public HtmlTemplateExceptionHandler(Writer writer) {
		if (writer == null)
			throw new NullPointerException("异常输出Writer为null!");
		this.writer = writer;
	}

	public void handleTemplateException(TemplateException exception)
			throws IOException, TemplateException {
		writer.write("\n发生位置:\n" + exception.getLocation());
		writer.write("\n异常信息:\n" + exception.getMessage());
		exception.printStackTrace();
	}
	
}
