package org.commontemplate.ext.coat;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.commontemplate.config.Syntax;
import org.commontemplate.util.scanner.ScanningException;

public class TagTranslatorTester {
	
	private TagTranslator tagTranslator;
	
	private TagTokenizer tagTokenizer;
	
	public void setUp() {
		tagTranslator = new TagTranslator(Syntax.DEFAULT);
		tagTokenizer = new TagTokenizer();
	}
	
	public void testNullExpression() throws IOException, ScanningException {
		List tokens = tagTokenizer.split(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("test/test2.html")));
		String result = tagTranslator.translate(tokens);
		System.out.println(result);
	}
	
	public void tearDown() {
		tagTokenizer = null;
	}

}