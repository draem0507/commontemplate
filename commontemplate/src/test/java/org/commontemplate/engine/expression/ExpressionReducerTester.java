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
		ExpressionFactory expressionFactory = new ExpressionFactory(config.getOperatorHandlerProvider(), config.getKeywords(), config.isFunctionAvailable());
		expressionTranslator = new ExpressionTranslator(expressionFactory, config.isFunctionAvailable());
		expressionReducer = new ExpressionReducer();
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
		
		Expression root = expressionReducer.reduce(expressions);
		
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
		
		Expression root = expressionReducer.reduce(expressions);
		
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
		Expression root = expressionReducer.reduce(expressions);
		
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
		Expression root = expressionReducer.reduce(expressions);
		
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
		Expression root = expressionReducer.reduce(expressions);
		
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
		Expression root = expressionReducer.reduce(expressions);
		
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
		Expression root = expressionReducer.reduce(expressions);
		
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
		Expression root = expressionReducer.reduce(expressions);
		
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
		Expression root = expressionReducer.reduce(expressions);
		
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
		Expression root = expressionReducer.reduce(expressions);
		
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
		Expression root = expressionReducer.reduce(expressions);
		
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
		Expression root = expressionReducer.reduce(expressions);
		
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
		Expression root = expressionReducer.reduce(expressions);
		
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
		Expression root = expressionReducer.reduce(expressions);
		
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
		Expression root = expressionReducer.reduce(expressions);
		
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
		Expression root = expressionReducer.reduce(expressions);
		
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
		
		Expression root = expressionReducer.reduce(expressions);
		
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
		
		Expression root = expressionReducer.reduce(expressions);
		
		assertTrue(root instanceof BinaryOperatorImpl);
		assertEquals("*", root.getName());
		
		BinaryOperatorImpl binaryOperatorImpl = (BinaryOperatorImpl) root;
		
		Expression leftExpression = binaryOperatorImpl.getLeftOperand();
		Expression rightExpression = binaryOperatorImpl.getRightOperand();
		
		assertEquals("a", leftExpression.getName());
		assertEquals("-6", rightExpression.getName());
	}
	
}
