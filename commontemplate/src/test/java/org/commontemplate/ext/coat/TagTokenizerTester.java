package org.commontemplate.ext.coat;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.commontemplate.util.scanner.ScanningException;
import org.commontemplate.util.scanner.Token;

public class TagTokenizerTester {
	
	TagTokenizer tagTokenizer;
	
	public void setUp() {
		tagTokenizer = new TagTokenizer();
	}
	
	public void testNullExpression() throws IOException, ScanningException {
		List tokens = tagTokenizer.split(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("test/test.html")));
		for (int i = 0, n = tokens.size(); i < n; i ++) {
			System.out.println(i + ".--------------------------------------------------------");
			System.out.println(((Token)tokens.get(i)).getMessage());
		}
	}
	
	public void tearDown() {
		tagTokenizer = null;
	}

}
