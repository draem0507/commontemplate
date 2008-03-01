package org.commontemplate.standard.coat;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.commontemplate.config.Syntax;
import org.commontemplate.config.ResourceFilter;
import org.commontemplate.util.scanner.ScanningException;

/**
 * HTML标签属性语法外套过滤器
 * 
 * @author liangfei0201@163.com
 *
 */
public class AttributeSyntaxCoatFilter implements ResourceFilter {
	
	private TagTokenizer tagTokenizer;
	
	private TagTranslator tagTranslator;
	
	public AttributeSyntaxCoatFilter() {
		this.tagTranslator = new TagTranslator(Syntax.DEFAULT);
		this.tagTokenizer = new TagTokenizer();
	}
	
	public Reader filter(Reader reader) throws IOException {
		try {
			String tmplate = tagTranslator.translate(tagTokenizer.split(reader));
			return new StringReader(tmplate);
		} catch (ScanningException e) {
			throw new RuntimeException(e);
		}
	}

}
