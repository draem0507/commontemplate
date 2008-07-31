package org.commontemplate.engine.expression;

import java.io.IOException;
import java.util.List;

import org.commontemplate.engine.expression.ExpressionTokenizer;
import org.commontemplate.util.scanner.ScanningException;
import org.commontemplate.util.scanner.Token;

import junit.framework.TestCase;

public class ExpressionTokenizerTester extends TestCase {

	private ExpressionTokenizer expressionTokenizer;

	public void setUp() {
		expressionTokenizer = new ExpressionTokenizer();
	}

	private static void assertTokens(List tokens, String[] messages) {
		assertEquals(messages.length, tokens.size());
		for (int i = 0, n = messages.length; i < n; i ++) {
			assertEquals(messages[i], ((Token)tokens.get(i)).getMessage().trim());
		}
	}

	public void testNullExpression() throws IOException, ScanningException {
		List tokens = expressionTokenizer.split(null);
		assertEquals(null, tokens);
	}

	public void testEmptyExpression() throws IOException, ScanningException {
		List tokens = expressionTokenizer.split("");
		assertEquals(null, tokens);
	}

	public void testSimpleExpression() throws IOException, ScanningException {
		List tokens = expressionTokenizer.split("1+2");
		String[] messages = {"1", "+", "2"};
		assertTokens(tokens, messages);
	}

	public void testConjointOperatorExpression() throws IOException, ScanningException {
		List tokens = expressionTokenizer.split("3*-2");
		String[] messages = {"3", "*-", "2"};
		assertTokens(tokens, messages);
	}

	public void testPropertyExpression() throws IOException, ScanningException {
		List tokens = expressionTokenizer.split("aa.bb");
		String[] messages = {"aa", ".", "bb"};
		assertTokens(tokens, messages);
	}

	public void testFloatNumberExpression() throws IOException, ScanningException {
		List tokens = expressionTokenizer.split("aa+3.4f+bb");
		String[] messages = {"aa", "+", "3.4f", "+", "bb"};
		assertTokens(tokens, messages);
	}

	public void testBracketExpression() throws IOException, ScanningException {
		List tokens = expressionTokenizer.split("3*(4+3)");
		String[] messages = {"3", "*", "(", "4", "+", "3", ")"};
		assertTokens(tokens, messages);
	}

	public void testNotEscapeStringExpression() throws IOException, ScanningException {
		List tokens = expressionTokenizer.split("@c:\native\test.txt");
		String[] messages = {"@", "c", ":", "ative", "est", ".", "txt"};
		assertTokens(tokens, messages);
	}

	public void testFunctionalExpression() throws IOException, ScanningException {
		List tokens = expressionTokenizer.split("getName()");
		String[] messages = {"getName","(", ")"};
		assertTokens(tokens, messages);
	}

	public void testIllegalExpression() {

	}

	public void tearDown() {
		expressionTokenizer = null;
	}

}
