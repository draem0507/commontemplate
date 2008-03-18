package org.commontemplate.standard.operator.number;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.commontemplate.config.BinaryOperatorHandler;

import junit.framework.TestCase;
/**
 * NumberModulusOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class NumberModulusOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	public void setUp() {

		handler = new NumberModulusOperatorHandler();
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleModulusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.8), new Double(1.2));
		assertTrue(result instanceof Double);
		assertEquals(0.6 ,((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleModulusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(2.5), new Float(1.8));
		assertTrue(result instanceof Double);
		assertEquals(0.7, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleModulusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(3), new Long(2));
		assertTrue(result instanceof Double);
		assertEquals(1, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleModulusInt() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(3.5), new Integer(2));
		assertTrue(result instanceof Double);
		assertEquals(1.5, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleModulusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), Short.valueOf("5"));
		assertTrue(result instanceof Double);
		assertEquals(1.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleModulusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(2.5), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.7, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleModulusBigInt() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), BigInteger.valueOf(5));
		assertTrue(result instanceof Double);
		assertEquals(1.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleModulusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), Byte.valueOf("5"));
		assertTrue(result instanceof Double);
		assertEquals(1.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatModulusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Double(1.2));
		assertTrue(result instanceof Double);
		assertEquals(0.6, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatModulusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Float(1.2));
		assertTrue(result instanceof Float);
		assertEquals(0.6, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatModulusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Long(2));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatModulusInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Integer(2));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatModulusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), Short.valueOf("2"));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatModulusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new BigDecimal(1.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.6, ((BigDecimal) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatModulusBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), BigInteger.valueOf(2));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatModulusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), Byte.valueOf("2"));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongModulusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(2), new Double(0.3));
		assertTrue(result instanceof Double);
		assertEquals(0.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongModulusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(2), new Float(0.3));
		assertTrue(result instanceof Float);
		assertEquals((float)0.2, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongModulusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), new Long(1000));
		assertTrue(result instanceof Long);
		assertTrue(0 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongModulusInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), new Integer(1000));
		assertTrue(result instanceof Long);
		assertTrue(0 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongModulusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), Short.valueOf("10"));
		assertTrue(result instanceof Long);
		assertTrue(0 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongModulusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(2), new BigDecimal(0.3));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.2, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongModulusBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongModulusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), Byte.valueOf("10"));
		assertTrue(result instanceof Long);
		assertTrue(0 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerModulusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(2), new Double(0.3));
		assertTrue(result instanceof Double);
		assertEquals(0.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerModulusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(2), new Float(0.3));
		assertTrue(result instanceof Float);
		assertEquals((float)0.2, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerModulusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), new Long(1000));
		assertTrue(result instanceof Long);
		assertTrue(0 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerModulusInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), new Integer(1000));
		assertTrue(result instanceof Integer);
		assertTrue(0 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerModulusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(0 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerModulusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(2), new BigDecimal(0.3));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.2, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerModulusBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerModulusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), Byte.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(0 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerModulusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(2), new Double(0.3));
		assertTrue(result instanceof Double);
		assertEquals(0.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerModulusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(2), new Float(0.3));
		assertTrue(result instanceof Float);
		assertEquals((float)0.2, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerModulusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), new Long(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerModulusInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), new Integer(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerModulusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), Short.valueOf("10"));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerModulusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(2), new BigDecimal(0.3));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.2, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerModulusBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerModulusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), Byte.valueOf("10"));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortModulusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("2"), new Double(0.3));
		assertTrue(result instanceof Double);
		assertEquals(0.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortModulusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("2"), new Float(0.3));
		assertTrue(result instanceof Float);
		assertEquals((float)0.2, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortModulusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), new Long(10));
		assertTrue(result instanceof Long);
		assertTrue(0 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortModulusInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), new Integer(10));
		assertTrue(result instanceof Integer);
		assertTrue(0 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortModulusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(0 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortModulusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("2"), new BigDecimal(0.3));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.2, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortModulusBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), BigInteger.valueOf(10));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortModulusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), Byte.valueOf("5"));
		assertTrue(result instanceof Integer);
		assertTrue(0 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalModulusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Double(1.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.6, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalModulusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Float(1.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.6, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalModulusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Long(1));
		assertTrue(result instanceof BigDecimal);
		assertTrue(0.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalModulusInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Integer(1));
		assertTrue(result instanceof BigDecimal);
		assertTrue(0.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalModulusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), Short.valueOf("1"));
		assertTrue(result instanceof BigDecimal);
		assertTrue(0.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalModulusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertTrue(0 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalModulusBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), BigInteger.valueOf(1));
		assertTrue(result instanceof BigDecimal);
		assertTrue(0.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalModulusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), Byte.valueOf("1"));
		assertTrue(result instanceof BigDecimal);
		assertTrue(0.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteModulusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("2"), new Double(0.3));
		assertTrue(result instanceof Double);
		assertEquals(0.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteModulusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("2"), new Float(0.3));
		assertTrue(result instanceof Float);
		assertEquals((float)0.2, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteModulusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), new Long(5));
		assertTrue(result instanceof Long);
		assertTrue(0 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteModulusInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), new Integer(5));
		assertTrue(result instanceof Integer);
		assertTrue(0 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteModulusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(0 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteModulusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("2"), new BigDecimal(0.3));
		assertTrue(result instanceof BigDecimal);
		assertEquals(0.2, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteModulusBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), BigInteger.valueOf(1));
		assertTrue(result instanceof BigInteger);
		assertTrue(0 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的取模运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteModulusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), Byte.valueOf("20"));
		assertTrue(result instanceof Integer);
		assertTrue(10 == ((Integer) result).intValue());
		
	}
}
