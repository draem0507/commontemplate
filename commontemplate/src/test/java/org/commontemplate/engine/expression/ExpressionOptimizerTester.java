package org.commontemplate.engine.expression;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import junit.framework.TestCase;

import org.commontemplate.config.Configuration;
import org.commontemplate.core.Expression;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.util.scanner.ScanningException;

/**
 * 表达式树归约器测试
 *
 * @author Yan Rong (yananay@126.com)
 *
 */
public class ExpressionOptimizerTester extends TestCase {

	private ExpressionTokenizer expressionTokenizer;

	private ExpressionTranslator expressionTranslator;
	
	private ExpressionOptimizer expressionOptimizer;
	
	private ExpressionReducer expressionReducer;

	public void setUp() {

		expressionTokenizer = new ExpressionTokenizer();
		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		ExpressionProvider expressionFactory = new ExpressionProvider(config.getOperatorHandlerProvider(), config.getKeywords(), config.isFunctionAvailable());
		expressionTranslator = new ExpressionTranslator(expressionFactory, config.isFunctionAvailable());
		
		expressionOptimizer = new ExpressionOptimizer();
		expressionReducer = new ExpressionReducer();
	}

	/**
	 * 对表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a+1+2
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 a+3
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce() throws IOException, ScanningException{

		String expressionText = "a+1+2";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);

		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertTrue(root instanceof BinaryOperatorImpl);
		assertEquals("+", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("3", rightExpression.getName());
	}

	/**
	 * 对表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a*(1+2*b)
	 * @result
	 *  结果<br>
	 *  表达式应该不会被优化。
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testNoPreOptimizeReduce() throws IOException, ScanningException{

		String expressionText = "a*(1+2*b)";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);

		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("*", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("+", rightExpression.getName());

		binaryOperatorImpl = (BinaryOperatorImpl) rightExpression;

		leftExpression = binaryOperatorImpl.getLeftOperand();
		rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("1", leftExpression.getName());
		assertEquals("*", rightExpression.getName());

		binaryOperatorImpl = (BinaryOperatorImpl) rightExpression;

		leftExpression = binaryOperatorImpl.getLeftOperand();
		rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("2", leftExpression.getName());
		assertEquals("b", rightExpression.getName());
	}

	/**
	 * 对表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a*(1+2+3+b)
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 a*(6+b)
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce2() throws IOException, ScanningException{

		String expressionText = "a*(1+2+3+b)";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("*", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("+", rightExpression.getName());

		binaryOperatorImpl = (BinaryOperatorImpl) rightExpression;

		leftExpression = binaryOperatorImpl.getLeftOperand();
		rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("6", leftExpression.getName());
		assertEquals("b", rightExpression.getName());
	}

	/**
	 * 对表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 (a+b)*(1+2+3)
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 (a+b)*6
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce3() throws IOException, ScanningException{

		String expressionText = "(a+b)*(1+2+3)";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("*", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("+", leftExpression.getName());
		assertEquals("6", rightExpression.getName());

		binaryOperatorImpl = (BinaryOperatorImpl) leftExpression;

		leftExpression = binaryOperatorImpl.getLeftOperand();
		rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("b", rightExpression.getName());

	}

	/**
	 * 对表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a+1+2*3
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 a+7
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce4() throws IOException, ScanningException{

		String expressionText = "a+1+2*3";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("+", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("7", rightExpression.getName());

	}

	/**
	 * 对表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 2*(3+3)
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 12
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce5() throws IOException, ScanningException{

		String expressionText = "2*(3+3)";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("12", root.getName());

	}

	/**
	 * 对表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 (2*3 + 6)
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 12
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce6() throws IOException, ScanningException{

		String expressionText = "(2*3 + 6)";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("12", root.getName());

	}

	/**
	 * 对带有乘方的表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 (2**2**2+ 1)
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 17.0
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce7() throws IOException, ScanningException{

		String expressionText = "(2**2**2+ 1)";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("17.0", root.getName());

	}

	/**
	 * 表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a-1+2
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 a - (-1)
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce8() throws IOException, ScanningException{

		String expressionText = "a-1+2";

		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("-", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("-1", rightExpression.getName());
	}

	/**
	 * 表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a-1*2
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 a - 2
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce9() throws IOException, ScanningException{

		String expressionText = "a-1*2";

		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("-", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("2", rightExpression.getName());
	}

	/**
	 * 表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a*1-2
	 * @result
	 *  结果<br>
	 *  表达式应该不被优化
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce10() throws IOException, ScanningException{

		String expressionText = "a*1-2";

		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("-", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("*", leftExpression.getName());
		assertEquals("2", rightExpression.getName());

		binaryOperatorImpl = (BinaryOperatorImpl) leftExpression;

		leftExpression = binaryOperatorImpl.getLeftOperand();
		rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("1", rightExpression.getName());

	}

	/**
	 * 表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a*2*2
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 a*4
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce11() throws IOException, ScanningException{

		String expressionText = "a*2*2";

		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("*", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("4", rightExpression.getName());

	}

	/**
	 * 表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a*2**3
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 a*8
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce12() throws IOException, ScanningException{

		String expressionText = "a*2**3";

		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("*", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("8.0", rightExpression.getName());

	}

	/**
	 * 表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a/2*3
	 * @result
	 *  结果<br>
	 *  表达式应该不被优化
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce13() throws IOException, ScanningException{

		String expressionText = "a/2*3";

		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("*", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("/", leftExpression.getName());
		assertEquals("3", rightExpression.getName());

		binaryOperatorImpl = (BinaryOperatorImpl) leftExpression;

		leftExpression = binaryOperatorImpl.getLeftOperand();
		rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("2", rightExpression.getName());

	}

	/**
	 * 表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a/(2*3)
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 a/6
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce14() throws IOException, ScanningException{

		String expressionText = "a/(2*3)";

		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("/", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("6", rightExpression.getName());

	}

	/**
	 * 表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a/2/3
	 * @result
	 *  结果<br>
	 *  表达式应该不被优化
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce15() throws IOException, ScanningException{

		String expressionText = "a/2/3";

		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertEquals("/", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("/", leftExpression.getName());
		assertEquals("3", rightExpression.getName());

		binaryOperatorImpl = (BinaryOperatorImpl) leftExpression;

		leftExpression = binaryOperatorImpl.getLeftOperand();
		rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("2", rightExpression.getName());

	}

	/**
	 * 对表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 3*-2
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 -6
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce16() throws IOException, ScanningException{

		String expressionText = "3*-2";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);

		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertTrue(root instanceof ConstantImpl);
		assertEquals("-6", root.getName());
	}

	/**
	 * 对表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a*3*-2
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 a*-6
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce17() throws IOException, ScanningException{

		String expressionText = "a*3*-2";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);

		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertTrue(root instanceof BinaryOperatorImpl);
		assertEquals("*", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("-6", rightExpression.getName());
	}

	/**
	 * 对表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a*-2*3
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 a*-6
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce18() throws IOException, ScanningException{

		String expressionText = "a*-2*3";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);

		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertTrue(root instanceof BinaryOperatorImpl);
		assertEquals("*", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("a", leftExpression.getName());
		assertEquals("-6", rightExpression.getName());
	}
	
	/**
	 * 对表达式进行预优化的测试。
	 * @condition
	 *  条件<br>
	 *  表达式为 a*-2*3
	 * @result
	 *  结果<br>
	 *  表达式应该被优化成 a*-6
	 * @throws ParseException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testPreOptimizeReduce19() throws IOException, ScanningException{

		String expressionText = ".15 - .12";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);

		Expression root = expressionReducer.reduce(expressionOptimizer.optimize(expressions));

		assertTrue(root instanceof ConstantImpl);
		assertEquals("0.03", root.getName());
	}

}
