package org.commontemplate.standard.operator.number;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;
/**
 * NumberSubtractOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class NumberSubtractOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	public void setUp() {

		handler = new NumberSubtractOperatorHandler();
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleSubDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.8), new Double(1.2));
		assertTrue(result instanceof Double);
		assertEquals(0.6 ,((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleSubFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.9), new Float(1.8));
		assertTrue(result instanceof Double);
		assertEquals(0.1, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleSubLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(3.5), new Long(2));
		assertTrue(result instanceof Double);
		assertTrue(1.5 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleSubInt() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(3.5), new Integer(2));
		assertTrue(result instanceof Double);
		assertTrue(1.5 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleSubShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), Short.valueOf("1"));
		assertTrue(result instanceof Double);
		assertEquals(0.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleSubBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.9), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.1, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleSubBigInt() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), BigInteger.valueOf(1));
		assertTrue(result instanceof Double);
		assertEquals(0.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleSubByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), Byte.valueOf("1"));
		assertTrue(result instanceof Double);
		assertEquals(0.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatSubDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Double(1.2));
		assertTrue(result instanceof Double);
		assertEquals(0.6, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatSubFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Float(1.2));
		assertTrue(result instanceof Float);
		assertEquals(0.6, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatSubLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Long(1));
		assertTrue(result instanceof Float);
		assertEquals((float)0.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatSubInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Integer(1));
		assertTrue(result instanceof Float);
		assertEquals((float)0.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatSubShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), Short.valueOf("1"));
		assertTrue(result instanceof Float);
		assertEquals((float)0.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatSubBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new BigDecimal(1.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.6, ((BigDecimal) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatSubBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), BigInteger.valueOf(1));
		assertTrue(result instanceof Float);
		assertEquals((float)0.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatSubByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), Byte.valueOf("1"));
		assertTrue(result instanceof Float);
		assertEquals((float)0.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongSubDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(2), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(0.8 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongSubFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(2), new Float(1.2));
		assertTrue(result instanceof Float);
		assertEquals((float)0.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongSubLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), new Long(1000));
		assertTrue(result instanceof Long);
		assertTrue(0 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongSubInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), new Integer(1000));
		assertTrue(result instanceof Long);
		assertTrue(0 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongSubShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), Short.valueOf("10"));
		assertTrue(result instanceof Long);
		assertTrue(990 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongSubBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(2), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.2, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongSubBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongSubByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), Byte.valueOf("10"));
		assertTrue(result instanceof Long);
		assertTrue(990 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerSubDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(2), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(0.8 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerSubFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(2), new Float(1.2));
		assertTrue(result instanceof Float);
		assertEquals((float)0.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerSubLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), new Long(1000));
		assertTrue(result instanceof Long);
		assertTrue(0 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerSubInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), new Integer(1000));
		assertTrue(result instanceof Integer);
		assertTrue(0 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerSubShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(990 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerSubBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(2), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.2, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerSubBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerSubByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), Byte.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(990 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerSubDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(2), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(0.8 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerSubFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(2), new Float(1.2));
		assertTrue(result instanceof Float);
		assertEquals((float)0.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerSubLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), new Long(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerSubInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), new Integer(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerSubShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), Short.valueOf("10"));
		assertTrue(result instanceof BigInteger);
		assertTrue(990 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerSubBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(2), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.2, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerSubBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerSubByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), Byte.valueOf("10"));
		assertTrue(result instanceof BigInteger);
		assertTrue(990 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortSubDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("2"), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(0.8 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortSubFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("2"), new Float(1.2));
		assertTrue(result instanceof Float);
		assertEquals((float)0.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortSubLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), new Long(10));
		assertTrue(result instanceof Long);
		assertTrue(0 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortSubInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), new Integer(10));
		assertTrue(result instanceof Integer);
		assertTrue(0 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortSubShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(0 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortSubBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("2"), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.2, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortSubBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), BigInteger.valueOf(10));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortSubByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), Byte.valueOf("5"));
		assertTrue(result instanceof Integer);
		assertTrue(5 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalSubDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Double(1.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.6, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalSubFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Float(1.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.6, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalSubLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Long(1));
		assertTrue(result instanceof BigDecimal);
		assertTrue(0.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalSubInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Integer(1));
		assertTrue(result instanceof BigDecimal);
		assertTrue(0.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalSubShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), Short.valueOf("1"));
		assertTrue(result instanceof BigDecimal);
		assertTrue(0.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalSubBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertTrue(0 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalSubBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), BigInteger.valueOf(1));
		assertTrue(result instanceof BigDecimal);
		assertTrue(0.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalSubByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), Byte.valueOf("1"));
		assertTrue(result instanceof BigDecimal);
		assertTrue(0.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteSubDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("2"), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(0.8 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteSubFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("2"), new Float(1.2));
		assertTrue(result instanceof Float);
		assertEquals((float)0.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteSubLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), new Long(5));
		assertTrue(result instanceof Long);
		assertTrue(5 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteSubInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), new Integer(5));
		assertTrue(result instanceof Integer);
		assertTrue(5 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteSubShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(0 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteSubBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("2"), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.2, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteSubBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), BigInteger.valueOf(1));
		assertTrue(result instanceof BigInteger);
		assertTrue(9 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 - 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的减法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteSubByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), Byte.valueOf("20"));
		assertTrue(result instanceof Integer);
		assertTrue(-10 == ((Integer) result).intValue());
		
	}
}
