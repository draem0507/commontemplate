package org.commontemplate.standard.operator.number;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * NumberAddOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class NumberAddOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
		Number result = (Number) handler.doEvaluate(new Double(1.2), new Float(1.8));
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
	public void testDoEvaluateDoublePlusLong() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
		Number result = (Number) handler.doEvaluate(new Float(1.8), new Double(1.2));
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
	public void testDoEvaluateFloatPlusFloat() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
		Number result = (Number) handler.doEvaluate(new BigDecimal(1.8), new Float(1.2));
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
	public void testDoEvaluateBigDecimalPlusLong() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
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
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
		Number result = (Number) handler.doEvaluate(Byte.valueOf("10"), Byte.valueOf("20"));
		assertTrue(result instanceof Integer);
		assertTrue(30 == ((Integer) result).intValue());
		
	}
}
