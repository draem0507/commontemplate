package org.commontemplate.standard.directive.filter.code;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;

import org.commontemplate.core.OutputFilter;

import de.java2html.converter.JavaSource2HTMLConverter;
import de.java2html.javasource.JavaSource;
import de.java2html.javasource.JavaSourceParser;
import de.java2html.options.JavaSourceConversionOptions;

public class JavaCodeFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	public String filter(String text) {
		try {
			StringReader reader = new StringReader(text);
			StringWriter writer = new StringWriter(); 
			
			JavaSource source = new JavaSourceParser().parse(reader);
			JavaSource2HTMLConverter converter = new JavaSource2HTMLConverter();
			converter.convert(source, JavaSourceConversionOptions.getDefault(), writer);
			
			return writer.toString();
		} catch (IOException e) {
			return text;
		}
	}

}
