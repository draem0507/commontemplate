package org.commontemplate.engine.expression;

import java.io.IOException;
import java.util.List;

import org.commontemplate.config.Configuration;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Variable;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.util.scanner.ScanningException;

import junit.framework.TestCase;

/**
 * 表达式树归约器测试
 *
 * @author Yan Rong (yananay@126.com)
 *
 */
public class ExpressionReducerTester extends TestCase {

	private ExpressionTokenizer expressionTokenizer;

	private ExpressionTranslator expressionTranslator;

	private ExpressionReducer expressionReducer;

	public void setUp() {

		expressionTokenizer = new ExpressionTokenizer();
		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		ExpressionProvider expressionProvider = new ExpressionProvider(config.getOperatorHandlerProvider(), config.getEvaluateInterceptors(), config.getKeywords(), config.isFunctionAvailable());
		expressionTranslator = new ExpressionTranslator(new ExpressionFactoryImpl(config.getOperatorHandlerProvider(),
				config.getEvaluateInterceptors()), expressionProvider, config.isFunctionAvailable());
		expressionReducer = new ExpressionReducer(config.getEvaluateInterceptors());
	}

	/**
	 * 测试常量的表达式解析。
	 * @condition
	 *  条件<br>
	 *  表达式为 1+2
	 * @result
	 *  结果
	 *  应该为Constant的对象，value为 3
	 * @throws ParsingException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testReduceWhenConstantExpression() throws ParsingException, IOException, ScanningException{

		String expressionText = "1+2";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);

		assertEquals(3, expressions.size());

		Expression root = expressionReducer.reduce(expressions);

		assertTrue(root instanceof Constant);

		Constant constant = (Constant) root;

		assertEquals(constant.getValue(), new Integer(3));

	}

	/**
	 * 测试表达式中带有变量的情况。
	 * @condition
	 *  条件<br>
	 *  表达式为 1+2+a
	 * @result
	 *  结果<br>
	 *  应该是一个二元操作符对象。<br>
	 *  其中一个是常量Constant，value为 3。<br>
	 *  另一个为 Variable 对象，变量名字为 a。
	 * @throws ParsingException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testReduceWhenVariableExpression() throws ParsingException, IOException, ScanningException{

		String expressionText = "1+2+a";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);

		assertEquals(5, expressions.size());

		Expression root = expressionReducer.reduce(expressions);

		assertTrue(root instanceof BinaryOperatorImpl);

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertTrue(leftExpression instanceof Constant);
		assertTrue(rightExpression instanceof Variable);

		assertEquals(((Constant)leftExpression).getValue(), new Integer(3));
		assertEquals("a", ((Variable)rightExpression).getName());

	}

	/**
	 * 测试带有括号时，表达式的解析。
	 * @condition
	 *  条件<br>
	 *  表达式为 1*(2+a)。
	 * @result
	 *  结果<br>
	 *  类似以下的结构：
	 *  		二元操作符对象(*号)
	 *  			|
	 *  			|
	 *  	____________________
	 *  	|					|
	 *  Constant (value=1)  二元操作符对象(+号)
	 *  						|
	 *  			_______________________
	 *  			|						|
	 *  	Constant (value=2)       Variable（name=a)
	 *
	 * @throws ParsingException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testReduceWhenTwoBinaryExpression() throws ParsingException, IOException, ScanningException{

		String expressionText = "1*(2+a)";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);

		assertEquals(7, expressions.size());

		Expression root = expressionReducer.reduce(expressions);

		assertTrue(root instanceof BinaryOperatorImpl);
		assertEquals("*", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertTrue(leftExpression instanceof Constant);
		assertEquals(((Constant)leftExpression).getValue(), new Integer(1));

		assertTrue(rightExpression instanceof BinaryOperatorImpl);
		assertEquals("+", rightExpression.getName());
		binaryOperatorImpl = (BinaryOperatorImpl) rightExpression;
		leftExpression = binaryOperatorImpl.getLeftOperand();
		rightExpression = binaryOperatorImpl.getRightOperand();
		assertTrue(leftExpression instanceof Constant);
		assertEquals(((Constant)leftExpression).getValue(), new Integer(2));
		assertTrue(rightExpression instanceof Variable);
		assertEquals("a", ((Variable)rightExpression).getName());

	}

	/**
	 * 测试表达式中带有对象变量的情况。
	 * @condition
	 *  条件<br>
	 *  表达式为 3 * user.coins + 2
	 * @result
	 *  结果<br>
	 *  类似以下的结构：
	 *                          +
	 *                         /  \
	 *                        *    2
	 *                      /   \
	 *                     3     .
	 *                          /  \
	 *                        user  coins
	 *
	 * @throws ParsingException
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testReduceWhenObjectExpression() throws ParsingException, IOException, ScanningException{

		String expressionText = "3 * user.coins + 2";
		List tokens = expressionTokenizer.split(expressionText);
		List expressions = expressionTranslator.translate(tokens);
		assertEquals(7, expressions.size());

		Expression root = expressionReducer.reduce(expressions);

		assertEquals("+", root.getName());

		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;

		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("*", leftExpression.getName());
		assertEquals("2", rightExpression.getName());

		binaryOperatorImpl = (BinaryOperatorImpl) leftExpression;

		leftExpression = binaryOperatorImpl.getLeftOperand();
		rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("3", leftExpression.getName());
		assertEquals(".", rightExpression.getName());

		binaryOperatorImpl = (BinaryOperatorImpl) rightExpression;

		leftExpression = binaryOperatorImpl.getLeftOperand();
		rightExpression = binaryOperatorImpl.getRightOperand();

		assertEquals("user", leftExpression.getName());
		assertEquals("coins", rightExpression.getName());

	}

}
