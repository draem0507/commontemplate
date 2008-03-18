package org.commontemplate.standard.operator.number;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;
/**
 * NumberAddOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class NumberAddOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	public void setUp() {

		handler = new NumberAddOperatorHandler();
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoublePlusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), new Double(1.8));
		assertTrue(result instanceof Double);
		assertTrue(3.0 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoublePlusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), new Float(1.8));
		assertTrue(result instanceof Double);
		assertEquals(3.0, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoublePlusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), new Long(1000));
		assertTrue(result instanceof Double);
		assertTrue(1001.2 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoublePlusInt() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), new Integer(1000));
		assertTrue(result instanceof Double);
		assertTrue(1001.2 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoublePlusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), Short.valueOf("10"));
		assertTrue(result instanceof Double);
		assertTrue(11.2 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoublePlusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertTrue(3 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoublePlusBigInt() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), BigInteger.valueOf(1000));
		assertTrue(result instanceof Double);
		assertTrue(1001.2 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoublePlusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), Byte.valueOf("10"));
		assertTrue(result instanceof Double);
		assertTrue(11.2 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatPlusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Double(1.2));
		assertTrue(result instanceof Double);
		assertEquals(3.0, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatPlusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Float(1.2));
		assertTrue(result instanceof Float);
		assertTrue(3 == ((Float) result).floatValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatPlusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Long(1000));
		assertTrue(result instanceof Float);
		assertTrue((float)1001.8 == ((Float) result).floatValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatPlusInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Integer(1000));
		assertTrue(result instanceof Float);
		assertTrue((float)1001.8 == ((Float) result).floatValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatPlusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), Short.valueOf("10"));
		assertTrue(result instanceof Float);
		assertTrue((float)11.8 == ((Float) result).floatValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatPlusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new BigDecimal(1.2));
		assertTrue(result instanceof BigDecimal);
		assertTrue(3 == ((BigDecimal) result).floatValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatPlusBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), BigInteger.valueOf(1000));
		assertTrue(result instanceof Float);
		assertTrue((float)1001.8 == ((Float) result).floatValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatPlusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), Byte.valueOf("10"));
		assertTrue(result instanceof Float);
		assertTrue((float)11.8 == ((Float) result).floatValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongPlusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(1001.2 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongPlusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), new Float(1.2));
		assertTrue(result instanceof Float);
		assertTrue((float)1001.2 == ((Float) result).floatValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongPlusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), new Long(1000));
		assertTrue(result instanceof Long);
		assertTrue(2000 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongPlusInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), new Integer(1000));
		assertTrue(result instanceof Long);
		assertTrue(2000 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongPlusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), Short.valueOf("10"));
		assertTrue(result instanceof Long);
		assertTrue(1010 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongPlusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1001.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongPlusBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(2000 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongPlusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), Byte.valueOf("10"));
		assertTrue(result instanceof Long);
		assertTrue(1010 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerPlusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(1001.2 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerPlusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), new Float(1.2));
		assertTrue(result instanceof Float);
		assertTrue((float)1001.2 == ((Float) result).floatValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerPlusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), new Long(1000));
		assertTrue(result instanceof Long);
		assertTrue(2000 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerPlusInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), new Integer(1000));
		assertTrue(result instanceof Integer);
		assertTrue(2000 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerPlusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(1010 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerPlusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1001.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerPlusBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(2000 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerPlusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), Byte.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(1010 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerPlusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(1001.2 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerPlusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), new Float(1.2));
		assertTrue(result instanceof Float);
		assertTrue((float)1001.2 == ((Float) result).floatValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerPlusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), new Long(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(2000 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerPlusInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), new Integer(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(2000 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerPlusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), Short.valueOf("10"));
		assertTrue(result instanceof BigInteger);
		assertTrue(1010 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerPlusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1001.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerPlusBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(2000 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerPlusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), Byte.valueOf("10"));
		assertTrue(result instanceof BigInteger);
		assertTrue(1010 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortPlusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(11.2 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortPlusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), new Float(1.2));
		assertTrue(result instanceof Float);
		assertTrue((float)11.2 == ((Float) result).floatValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortPlusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), new Long(1000));
		assertTrue(result instanceof Long);
		assertTrue(1010 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortPlusInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), new Integer(1000));
		assertTrue(result instanceof Integer);
		assertTrue(1010 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortPlusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(20 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortPlusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertTrue(11.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortPlusBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(1010 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortPlusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), Byte.valueOf("20"));
		assertTrue(result instanceof Integer);
		assertTrue(30 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalPlusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Double(1.2));
		assertTrue(result instanceof BigDecimal);
		assertTrue(3 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalPlusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Float(1.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(3, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalPlusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Long(1000));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1001.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalPlusInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Integer(1000));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1001.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalPlusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), Short.valueOf("10"));
		assertTrue(result instanceof BigDecimal);
		assertTrue(11.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalPlusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertTrue(3.6 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalPlusBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1001.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalPlusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), Byte.valueOf("10"));
		assertTrue(result instanceof BigDecimal);
		assertTrue(11.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBytePlusDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(11.2 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBytePlusFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), new Float(1.2));
		assertTrue(result instanceof Float);
		assertTrue((float)11.2 == ((Float) result).floatValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBytePlusLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), new Long(1000));
		assertTrue(result instanceof Long);
		assertTrue(1010 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBytePlusInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), new Integer(1000));
		assertTrue(result instanceof Integer);
		assertTrue(1010 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBytePlusShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(20 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBytePlusBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertTrue(11.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBytePlusBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(1010 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的加法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBytePlusByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), Byte.valueOf("20"));
		assertTrue(result instanceof Integer);
		assertTrue(30 == ((Integer) result).intValue());
		
	}
}
