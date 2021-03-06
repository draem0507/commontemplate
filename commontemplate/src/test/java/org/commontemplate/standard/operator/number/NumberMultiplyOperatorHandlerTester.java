package org.commontemplate.standard.operator.number;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;
/**
 * NumberMultiplyOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class NumberMultiplyOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	public void setUp() {

		handler = new NumberMultiplyOperatorHandler();
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleMultiplyDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.8), new Double(1.2));
		assertTrue(result instanceof Double);
		assertEquals(2.16 ,((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleMultiplyFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.9), new Float(1.8));
		assertTrue(result instanceof Double);
		assertEquals(3.42, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleMultiplyLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(3.5), new Long(2));
		assertTrue(result instanceof Double);
		assertTrue(7 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleMultiplyInt() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(3.5), new Integer(2));
		assertTrue(result instanceof Double);
		assertTrue(7 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleMultiplyShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), Short.valueOf("1"));
		assertTrue(result instanceof Double);
		assertEquals(1.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleMultiplyBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.9), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertEquals(3.42, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleMultiplyBigInt() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), BigInteger.valueOf(1));
		assertTrue(result instanceof Double);
		assertEquals(1.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDoubleMultiplyByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), Byte.valueOf("1"));
		assertTrue(result instanceof Double);
		assertEquals(1.2, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatMultiplyDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Double(1.2));
		assertTrue(result instanceof Double);
		assertEquals(2.16, ((Double) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatMultiplyFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Float(1.2));
		assertTrue(result instanceof Float);
		assertEquals(2.16, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatMultiplyLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Long(1));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatMultiplyInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Integer(1));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatMultiplyShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), Short.valueOf("1"));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatMultiplyBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new BigDecimal(1.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(2.16, ((BigDecimal) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatMultiplyBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), BigInteger.valueOf(1));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateFloatMultiplyByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), Byte.valueOf("1"));
		assertTrue(result instanceof Float);
		assertEquals((float)1.8, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongMultiplyDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(2), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(2.4 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongMultiplyFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(2), new Float(1.2));
		assertTrue(result instanceof Float);
		assertEquals((float)2.4, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongMultiplyLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), new Long(1000));
		assertTrue(result instanceof Long);
		assertTrue(1000000 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongMultiplyInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), new Integer(1000));
		assertTrue(result instanceof Long);
		assertTrue(1000000 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongMultiplyShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), Short.valueOf("10"));
		assertTrue(result instanceof Long);
		assertTrue(10000 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongMultiplyBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(2), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertEquals(3.6, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongMultiplyBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(1000000 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateLongMultiplyByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Long(1000), Byte.valueOf("10"));
		assertTrue(result instanceof Long);
		assertTrue(10000 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerMultiplyDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(2), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(2.4 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerMultiplyFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(2), new Float(1.2));
		assertTrue(result instanceof Float);
		assertEquals((float)2.4, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerMultiplyLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), new Long(1000));
		assertTrue(result instanceof Long);
		assertTrue(1000000 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerMultiplyInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), new Integer(1000));
		assertTrue(result instanceof Integer);
		assertTrue(1000000 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerMultiplyShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(10000 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerMultiplyBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(2), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertEquals(3.6, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerMultiplyBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(1000000 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateIntegerMultiplyByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new Integer(1000), Byte.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(10000 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerMultiplyDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(2), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(2.4 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerMultiplyFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(2), new Float(1.2));
		assertTrue(result instanceof Float);
		assertEquals((float)2.4, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerMultiplyLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), new Long(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(1000000 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerMultiplyInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), new Integer(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(1000000 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerMultiplyShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), Short.valueOf("10"));
		assertTrue(result instanceof BigInteger);
		assertTrue(10000 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerMultiplyBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(2), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertEquals(3.6, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerMultiplyBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), BigInteger.valueOf(1000));
		assertTrue(result instanceof BigInteger);
		assertTrue(1000000 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigIntegerMultiplyByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(BigInteger.valueOf(1000), Byte.valueOf("10"));
		assertTrue(result instanceof BigInteger);
		assertTrue(10000 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortMultiplyDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("2"), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(2.4 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortMultiplyFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("2"), new Float(1.2));
		assertTrue(result instanceof Float);
		assertEquals((float)2.4, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortMultiplyLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), new Long(10));
		assertTrue(result instanceof Long);
		assertTrue(100 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortMultiplyInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), new Integer(10));
		assertTrue(result instanceof Integer);
		assertTrue(100 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortMultiplyShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(100 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortMultiplyBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("2"), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertEquals(3.6, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortMultiplyBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), BigInteger.valueOf(10));
		assertTrue(result instanceof BigInteger);
		assertTrue(100 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateShortMultiplyByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Short.valueOf("10"), Byte.valueOf("5"));
		assertTrue(result instanceof Integer);
		assertTrue(50 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalMultiplyDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Double(1.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(2.16, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalMultiplyFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Float(1.2));
		assertTrue(result instanceof BigDecimal);
		assertEquals(2.16, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalMultiplyLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Long(1));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalMultiplyInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Integer(1));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalMultiplyShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), Short.valueOf("1"));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalMultiplyBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertTrue(3.24 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalMultiplyBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), BigInteger.valueOf(1));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateBigDecimalMultiplyByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), Byte.valueOf("1"));
		assertTrue(result instanceof BigDecimal);
		assertTrue(1.8 == ((BigDecimal) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteMultiplyDouble() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("2"), new Double(1.2));
		assertTrue(result instanceof Double);
		assertTrue(2.4 == ((Double) result).doubleValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteMultiplyFloat() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("2"), new Float(1.2));
		assertTrue(result instanceof Float);
		assertEquals((float)2.4, ((Float) result).floatValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteMultiplyLong() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), new Long(5));
		assertTrue(result instanceof Long);
		assertTrue(50 == ((Long) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteMultiplyInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), new Integer(5));
		assertTrue(result instanceof Integer);
		assertTrue(50 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteMultiplyShort() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), Short.valueOf("10"));
		assertTrue(result instanceof Integer);
		assertTrue(100 == ((Integer) result).intValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteMultiplyBigDecimal() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("2"), new BigDecimal(1.8));
		assertTrue(result instanceof BigDecimal);
		assertEquals(3.6, ((BigDecimal) result).doubleValue(), 0.000001);
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteMultiplyBigInteger() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), BigInteger.valueOf(1));
		assertTrue(result instanceof BigInteger);
		assertTrue(10 == ((BigInteger) result).longValue());
		
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的乘法运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateByteMultiplyByte() throws Exception{
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), Byte.valueOf("20"));
		assertTrue(result instanceof Integer);
		assertTrue(200 == ((Integer) result).intValue());
		
	}
}
