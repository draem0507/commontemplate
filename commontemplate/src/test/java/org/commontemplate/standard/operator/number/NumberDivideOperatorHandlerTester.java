package org.commontemplate.standard.operator.number;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;
/**
 * NumberDivideOperatorHandler 的测试。
 *
 */
public class NumberDivideOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	public void setUp() {

		handler = new NumberDivideOperatorHandler();
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleDivideDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.8), new Double(1.2));
		assertTrue(result instanceof Double);
		assertEquals(1.5 ,((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleDivideFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(2.16), new Float(1.8));
		assertTrue(result instanceof Double);
		assertEquals(1.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleDivideLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(3.5), new Long(2));
		assertTrue(result instanceof Double);
		assertTrue(1.75 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleDivideInt() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(3.5), new Integer(2));
		assertTrue(result instanceof Double);
		assertTrue(1.75 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleDivideShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), Short.valueOf("1"));
		assertTrue(result instanceof Double);
		assertEquals(1.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleDivideBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(2.16), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertEquals(1.2, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleDivideBigInt() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), BigInteger.valueOf(1));
		assertTrue(result instanceof Double);
		assertEquals(1.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleDivideByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), Byte.valueOf("1"));
		assertTrue(result instanceof Double);
		assertEquals(1.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatDivideDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Double(1.2));
		assertTrue(result instanceof Double);
		assertEquals(1.5, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatDivideFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Float(1.2));
		assertTrue(result instanceof Float);
		assertEquals(1.5, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatDivideLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Long(1));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatDivideInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Integer(1));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatDivideShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), Short.valueOf("1"));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatDivideBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new BigDecimal(1.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(1.5, ((BigDecimal) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatDivideBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), BigInteger.valueOf(1));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatDivideByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), Byte.valueOf("1"));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongDivideDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(2), new Double(0.2));
		assertTrue(result instanceof Double);
		assertTrue(10 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongDivideFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(2), new Float(0.2));
		assertTrue(result instanceof Float);
		assertEquals((float)10, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongDivideLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), new Long(1000));
		assertTrue(result instanceof Long);
		assertTrue(1 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongDivideInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), new Integer(1000));
		assertTrue(result instanceof Long);
		assertTrue(1 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongDivideShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), Short.valueOf("10"));
		assertTrue(result instanceof Long);
		assertTrue(100 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongDivideBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(2), new BigDecimal(0.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(10, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongDivideBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(1 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongDivideByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), Byte.valueOf("10"));
		assertTrue(result instanceof Long);
		assertTrue(100 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerDivideDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(2), new Double(0.2));
		assertTrue(result instanceof Double);
		assertTrue(10 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerDivideFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(2), new Float(0.2));
		assertTrue(result instanceof Float);
		assertEquals((float)10, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerDivideLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), new Long(1000));
		assertTrue(result instanceof Long);
		assertTrue(1 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerDivideInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), new Integer(1000));
		assertTrue(result instanceof Integer);
		assertTrue(1 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerDivideShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(100 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerDivideBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(2), new BigDecimal(0.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(10, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerDivideBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(1 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerDivideByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), Byte.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(100 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerDivideDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(2), new Double(0.2));
		assertTrue(result instanceof Double);
		assertTrue(10 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerDivideFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(2), new Float(0.2));
		assertTrue(result instanceof Float);
		assertEquals((float)10, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerDivideLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), new Long(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(1 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerDivideInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), new Integer(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(1 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerDivideShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), Short.valueOf("10"));
		assertTrue(result instanceof BigInteger);
		assertTrue(100 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerDivideBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(2), new BigDecimal(0.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(10, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerDivideBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(1 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerDivideByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), Byte.valueOf("10"));
		assertTrue(result instanceof BigInteger);
		assertTrue(100 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortDivideDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("2"), new Double(0.2));
		assertTrue(result instanceof Double);
		assertTrue(10 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortDivideFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("2"), new Float(0.2));
		assertTrue(result instanceof Float);
		assertEquals((float)10, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortDivideLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), new Long(10));
		assertTrue(result instanceof Long);
		assertTrue(1 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortDivideInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), new Integer(10));
		assertTrue(result instanceof Integer);
		assertTrue(1 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortDivideShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(1 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortDivideBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("2"), new BigDecimal(0.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(10, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortDivideBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), BigInteger.valueOf(10));
		assertTrue(result instanceof BigInteger);
		assertTrue(1 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortDivideByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), Byte.valueOf("5"));
		assertTrue(result instanceof Integer);
		assertTrue(2 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalDivideDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Double(1.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(1.5, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalDivideFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Float(1.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(1.5, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalDivideLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Long(1));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalDivideInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Integer(1));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalDivideShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), Short.valueOf("1"));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalDivideBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalDivideBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), BigInteger.valueOf(1));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalDivideByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), Byte.valueOf("1"));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteDivideDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("2"), new Double(0.2));
		assertTrue(result instanceof Double);
		assertTrue(10 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteDivideFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("2"), new Float(0.2));
		assertTrue(result instanceof Float);
		assertEquals((float)10, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteDivideLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), new Long(5));
		assertTrue(result instanceof Long);
		assertTrue(2 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteDivideInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), new Integer(5));
		assertTrue(result instanceof Integer);
		assertTrue(2 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteDivideShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(1 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteDivideBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("2"), new BigDecimal(0.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(10, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteDivideBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), BigInteger.valueOf(1));
		assertTrue(result instanceof BigInteger);
		assertTrue(10 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 / 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的除法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteDivideByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), Byte.valueOf("20"));
		assertTrue(result instanceof Integer);
		assertTrue(0 == ((Integer) result).intValue());
		
	}

}
