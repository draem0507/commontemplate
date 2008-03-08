package org.commontemplate.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 数字加减乘除四则运算，主要处理类型混合运算，如：Integer + Double
 * 
 * @author liangfei0201@163.com
 *
 */
public final class NumberArithmetic {
	
	private NumberArithmetic(){}
	
	/**
	 * 加法运算
	 * 
	 * @param n1 左参数
	 * @param n2 右参数
	 * @return 结果
	 */
	public static Number add(Number n1, Number n2) {
		if (n1 instanceof Double) {
			if (n2 instanceof Double)
				return new Double(n1.doubleValue() + n2.doubleValue());
			if (n2 instanceof Float)
				return new Double(n1.doubleValue() + n2.floatValue());
			if (n2 instanceof Long)
				return new Double(n1.doubleValue() + n2.longValue());
			if (n2 instanceof Integer)
				return new Double(n1.doubleValue() + n2.intValue());
			if (n2 instanceof Short)
				return new Double(n1.doubleValue() + n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.doubleValue() + n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new Double(n1.doubleValue() + n2.longValue());
			if (n2 instanceof Byte)
				return new Double(n1.doubleValue() + n2.byteValue());
		}
	
		if (n1 instanceof Float) {
			if (n2 instanceof Double)
				return new Double(n1.floatValue() + n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.floatValue() + n2.floatValue());
			if (n2 instanceof Long)
				return new Float(n1.floatValue() + n2.longValue());
			if (n2 instanceof Integer)
				return new Float(n1.floatValue() + n2.intValue());
			if (n2 instanceof Short)
				return new Float(n1.floatValue() + n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.floatValue() + n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new Float(n1.floatValue() + n2.longValue());
			if (n2 instanceof Byte)
				return new Float(n1.floatValue() + n2.byteValue());
		}
		
		if (n1 instanceof Long) {
			if (n2 instanceof Double)
				return new Double(n1.longValue() + n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.longValue() + n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.longValue() + n2.longValue());
			if (n2 instanceof Integer)
				return new Long(n1.longValue() + n2.intValue());
			if (n2 instanceof Short)
				return new Long(n1.longValue() + n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.longValue() + n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.longValue() + n2.longValue()));
			if (n2 instanceof Byte)
				return new Long(n1.longValue() + n2.byteValue());
		}
		
		if (n1 instanceof Integer) {
			if (n2 instanceof Double)
				return new Double(n1.intValue() + n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.intValue() + n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.intValue() + n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.intValue() + n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.intValue() + n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.intValue() + n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.intValue() + n2.longValue()));
			if (n2 instanceof Byte)
				return new Integer(n1.intValue() + n2.byteValue());
		}
		
		if (n1 instanceof BigInteger) {
			if (n2 instanceof Double)
				return new Double(n1.longValue() + n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.longValue() + n2.floatValue());
			if (n2 instanceof Long)
				return new BigInteger(String.valueOf(n1.longValue() + n2.longValue()));
			if (n2 instanceof Integer)
				return new BigInteger(String.valueOf(n1.longValue() + n2.intValue()));
			if (n2 instanceof Short)
				return new BigInteger(String.valueOf(n1.longValue() + n2.shortValue()));
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.longValue() + n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.longValue() + n2.longValue()));
			if (n2 instanceof Byte)
				return new BigInteger(String.valueOf(n1.longValue() + n2.byteValue()));
		}

		if (n1 instanceof Short) {
			if (n2 instanceof Double)
				return new Double(n1.shortValue() + n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.shortValue() + n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.shortValue() + n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.shortValue() + n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.shortValue() + n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.shortValue() + n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.shortValue() + n2.longValue()));
			if (n2 instanceof Byte)
				return new Integer(n1.shortValue() + n2.byteValue());
		}
		
		if (n1 instanceof BigDecimal) {
			if (n2 instanceof Double)
				return new BigDecimal(n1.doubleValue() + n2.doubleValue());
			if (n2 instanceof Float)
				return new BigDecimal(n1.doubleValue() + n2.floatValue());
			if (n2 instanceof Long)
				return new BigDecimal(n1.doubleValue() + n2.longValue());
			if (n2 instanceof Integer)
				return new BigDecimal(n1.doubleValue() + n2.intValue());
			if (n2 instanceof Short)
				return new BigDecimal(n1.doubleValue() + n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.doubleValue() + n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigDecimal(n1.doubleValue() + n2.longValue());
			if (n2 instanceof Byte)
				return new BigDecimal(n1.doubleValue() + n2.byteValue());
		}

		//if (n1 instanceof Byte) {
			if (n2 instanceof Double)
				return new Double(n1.shortValue() + n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.shortValue() + n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.shortValue() + n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.shortValue() + n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.shortValue() + n2.shortValue());
			if (n2 instanceof BigInteger) 
				return new BigInteger(String.valueOf(n1.shortValue() + n2.longValue()));
			if (n2 instanceof BigDecimal) 
				return new BigDecimal(n1.shortValue() + n2.doubleValue());
			//if (n2 instanceof Byte)
				return new Integer(n1.byteValue() + n2.byteValue());
		//}
	}
	
	/**
	 * 减法运算
	 * 
	 * @param n1 左参数
	 * @param n2 右参数
	 * @return 结果
	 */
	public static Number subtract(Number n1, Number n2) {
		if (n1 instanceof Double) {
			if (n2 instanceof Double)
				return new Double(n1.doubleValue() - n2.doubleValue());
			if (n2 instanceof Float)
				return new Double(n1.doubleValue() - n2.floatValue());
			if (n2 instanceof Long)
				return new Double(n1.doubleValue() - n2.longValue());
			if (n2 instanceof Integer)
				return new Double(n1.doubleValue() - n2.intValue());
			if (n2 instanceof Short)
				return new Double(n1.doubleValue() - n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.doubleValue() - n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new Double(n1.doubleValue() - n2.longValue());
			if (n2 instanceof Byte)
				return new Double(n1.doubleValue() - n2.byteValue());
		}
	
		if (n1 instanceof Float) {
			if (n2 instanceof Double)
				return new Double(n1.floatValue() - n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.floatValue() - n2.floatValue());
			if (n2 instanceof Long)
				return new Float(n1.floatValue() - n2.longValue());
			if (n2 instanceof Integer)
				return new Float(n1.floatValue() - n2.intValue());
			if (n2 instanceof Short)
				return new Float(n1.floatValue() - n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.floatValue() - n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new Float(n1.floatValue() - n2.longValue());
			if (n2 instanceof Byte)
				return new Float(n1.floatValue() - n2.byteValue());
		}
		
		if (n1 instanceof Long) {
			if (n2 instanceof Double)
				return new Double(n1.longValue() - n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.longValue() - n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.longValue() - n2.longValue());
			if (n2 instanceof Integer)
				return new Long(n1.longValue() - n2.intValue());
			if (n2 instanceof Short)
				return new Long(n1.longValue() - n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.longValue() - n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.longValue() - n2.longValue()));
			if (n2 instanceof Byte)
				return new Long(n1.longValue() - n2.byteValue());
		}
		
		if (n1 instanceof Integer) {
			if (n2 instanceof Double)
				return new Double(n1.intValue() - n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.intValue() - n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.intValue() - n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.intValue() - n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.intValue() - n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.intValue() - n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.intValue() - n2.longValue()));
			if (n2 instanceof Byte)
				return new Integer(n1.intValue() - n2.byteValue());
		}
		
		if (n1 instanceof BigInteger) {
			if (n2 instanceof Double)
				return new Double(n1.longValue() - n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.longValue() - n2.floatValue());
			if (n2 instanceof Long)
				return new BigInteger(String.valueOf(n1.longValue() - n2.longValue()));
			if (n2 instanceof Integer)
				return new BigInteger(String.valueOf(n1.longValue() - n2.intValue()));
			if (n2 instanceof Short)
				return new BigInteger(String.valueOf(n1.longValue() - n2.shortValue()));
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.longValue() - n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.longValue() - n2.longValue()));
			if (n2 instanceof Byte)
				return new BigInteger(String.valueOf(n1.longValue() - n2.byteValue()));
		}

		if (n1 instanceof Short) {
			if (n2 instanceof Double)
				return new Double(n1.shortValue() - n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.shortValue() - n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.shortValue() - n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.shortValue() - n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.shortValue() - n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.shortValue() - n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.shortValue() - n2.longValue()));
			if (n2 instanceof Byte)
				return new Integer(n1.shortValue() - n2.byteValue());
		}
		
		if (n1 instanceof BigDecimal) {
			if (n2 instanceof Double)
				return new BigDecimal(n1.doubleValue() - n2.doubleValue());
			if (n2 instanceof Float)
				return new BigDecimal(n1.doubleValue() - n2.floatValue());
			if (n2 instanceof Long)
				return new BigDecimal(n1.doubleValue() - n2.longValue());
			if (n2 instanceof Integer)
				return new BigDecimal(n1.doubleValue() - n2.intValue());
			if (n2 instanceof Short)
				return new BigDecimal(n1.doubleValue() - n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.doubleValue() - n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigDecimal(n1.doubleValue() - n2.longValue());
			if (n2 instanceof Byte)
				return new BigDecimal(n1.doubleValue() - n2.byteValue());
		}

		//if (n1 instanceof Byte) {
			if (n2 instanceof Double)
				return new Double(n1.shortValue() - n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.shortValue() - n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.shortValue() - n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.shortValue() - n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.shortValue() - n2.shortValue());
			if (n2 instanceof BigInteger) 
				return new BigInteger(String.valueOf(n1.shortValue() - n2.longValue()));
			if (n2 instanceof BigDecimal) 
				return new BigDecimal(n1.shortValue() - n2.doubleValue());
			//if (n2 instanceof Byte)
				return new Integer(n1.byteValue() - n2.byteValue());
		//}
	}
	
	/**
	 * 乘法运算
	 * 
	 * @param n1 左参数
	 * @param n2 右参数
	 * @return 结果
	 */
	public static Number multiply(Number n1, Number n2) {
		if (n1 instanceof Double) {
			if (n2 instanceof Double)
				return new Double(n1.doubleValue() * n2.doubleValue());
			if (n2 instanceof Float)
				return new Double(n1.doubleValue() * n2.floatValue());
			if (n2 instanceof Long)
				return new Double(n1.doubleValue() * n2.longValue());
			if (n2 instanceof Integer)
				return new Double(n1.doubleValue() * n2.intValue());
			if (n2 instanceof Short)
				return new Double(n1.doubleValue() * n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.doubleValue() * n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new Double(n1.doubleValue() * n2.longValue());
			if (n2 instanceof Byte)
				return new Double(n1.doubleValue() * n2.byteValue());
		}
	
		if (n1 instanceof Float) {
			if (n2 instanceof Double)
				return new Double(n1.floatValue() * n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.floatValue() * n2.floatValue());
			if (n2 instanceof Long)
				return new Float(n1.floatValue() * n2.longValue());
			if (n2 instanceof Integer)
				return new Float(n1.floatValue() * n2.intValue());
			if (n2 instanceof Short)
				return new Float(n1.floatValue() * n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.floatValue() * n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new Float(n1.floatValue() * n2.longValue());
			if (n2 instanceof Byte)
				return new Float(n1.floatValue() * n2.byteValue());
		}
		
		if (n1 instanceof Long) {
			if (n2 instanceof Double)
				return new Double(n1.longValue() * n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.longValue() * n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.longValue() * n2.longValue());
			if (n2 instanceof Integer)
				return new Long(n1.longValue() * n2.intValue());
			if (n2 instanceof Short)
				return new Long(n1.longValue() * n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.longValue() * n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.longValue() * n2.longValue()));
			if (n2 instanceof Byte)
				return new Long(n1.longValue() * n2.byteValue());
		}
		
		if (n1 instanceof Integer) {
			if (n2 instanceof Double)
				return new Double(n1.intValue() * n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.intValue() * n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.intValue() * n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.intValue() * n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.intValue() * n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.intValue() * n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.intValue() * n2.longValue()));
			if (n2 instanceof Byte)
				return new Integer(n1.intValue() * n2.byteValue());
		}
		
		if (n1 instanceof BigInteger) {
			if (n2 instanceof Double)
				return new Double(n1.longValue() * n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.longValue() * n2.floatValue());
			if (n2 instanceof Long)
				return new BigInteger(String.valueOf(n1.longValue() * n2.longValue()));
			if (n2 instanceof Integer)
				return new BigInteger(String.valueOf(n1.longValue() * n2.intValue()));
			if (n2 instanceof Short)
				return new BigInteger(String.valueOf(n1.longValue() * n2.shortValue()));
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.longValue() * n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.longValue() * n2.longValue()));
			if (n2 instanceof Byte)
				return new BigInteger(String.valueOf(n1.longValue() * n2.byteValue()));
		}

		if (n1 instanceof Short) {
			if (n2 instanceof Double)
				return new Double(n1.shortValue() * n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.shortValue() * n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.shortValue() * n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.shortValue() * n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.shortValue() * n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.shortValue() * n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.shortValue() * n2.longValue()));
			if (n2 instanceof Byte)
				return new Integer(n1.shortValue() * n2.byteValue());
		}
		
		if (n1 instanceof BigDecimal) {
			if (n2 instanceof Double)
				return new BigDecimal(n1.doubleValue() * n2.doubleValue());
			if (n2 instanceof Float)
				return new BigDecimal(n1.doubleValue() * n2.floatValue());
			if (n2 instanceof Long)
				return new BigDecimal(n1.doubleValue() * n2.longValue());
			if (n2 instanceof Integer)
				return new BigDecimal(n1.doubleValue() * n2.intValue());
			if (n2 instanceof Short)
				return new BigDecimal(n1.doubleValue() * n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.doubleValue() * n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigDecimal(n1.doubleValue() * n2.longValue());
			if (n2 instanceof Byte)
				return new BigDecimal(n1.doubleValue() * n2.byteValue());
		}

		//if (n1 instanceof Byte) {
			if (n2 instanceof Double)
				return new Double(n1.shortValue() * n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.shortValue() * n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.shortValue() * n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.shortValue() * n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.shortValue() * n2.shortValue());
			if (n2 instanceof BigInteger) 
				return new BigInteger(String.valueOf(n1.shortValue() * n2.longValue()));
			if (n2 instanceof BigDecimal) 
				return new BigDecimal(n1.shortValue() * n2.doubleValue());
			//if (n2 instanceof Byte)
				return new Integer(n1.byteValue() * n2.byteValue());
		//}
	}
	
	/**
	 * 除法运算
	 * 
	 * @param n1 左参数
	 * @param n2 右参数
	 * @return 结果
	 */
	public static Number divide(Number n1, Number n2) {
		if (n1 instanceof Double) {
			if (n2 instanceof Double)
				return new Double(n1.doubleValue() / n2.doubleValue());
			if (n2 instanceof Float)
				return new Double(n1.doubleValue() / n2.floatValue());
			if (n2 instanceof Long)
				return new Double(n1.doubleValue() / n2.longValue());
			if (n2 instanceof Integer)
				return new Double(n1.doubleValue() / n2.intValue());
			if (n2 instanceof Short)
				return new Double(n1.doubleValue() / n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.doubleValue() / n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new Double(n1.doubleValue() / n2.longValue());
			if (n2 instanceof Byte)
				return new Double(n1.doubleValue() / n2.byteValue());
		}
	
		if (n1 instanceof Float) {
			if (n2 instanceof Double)
				return new Double(n1.floatValue() / n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.floatValue() / n2.floatValue());
			if (n2 instanceof Long)
				return new Float(n1.floatValue() / n2.longValue());
			if (n2 instanceof Integer)
				return new Float(n1.floatValue() / n2.intValue());
			if (n2 instanceof Short)
				return new Float(n1.floatValue() / n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.floatValue() / n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new Float(n1.floatValue() / n2.longValue());
			if (n2 instanceof Byte)
				return new Float(n1.floatValue() / n2.byteValue());
		}
		
		if (n1 instanceof Long) {
			if (n2 instanceof Double)
				return new Double(n1.longValue() / n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.longValue() / n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.longValue() / n2.longValue());
			if (n2 instanceof Integer)
				return new Long(n1.longValue() / n2.intValue());
			if (n2 instanceof Short)
				return new Long(n1.longValue() / n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.longValue() / n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.longValue() / n2.longValue()));
			if (n2 instanceof Byte)
				return new Long(n1.longValue() / n2.byteValue());
		}
		
		if (n1 instanceof Integer) {
			if (n2 instanceof Double)
				return new Double(n1.intValue() / n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.intValue() / n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.intValue() / n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.intValue() / n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.intValue() / n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.intValue() / n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.intValue() / n2.longValue()));
			if (n2 instanceof Byte)
				return new Integer(n1.intValue() / n2.byteValue());
		}
		
		if (n1 instanceof BigInteger) {
			if (n2 instanceof Double)
				return new Double(n1.longValue() / n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.longValue() / n2.floatValue());
			if (n2 instanceof Long)
				return new BigInteger(String.valueOf(n1.longValue() / n2.longValue()));
			if (n2 instanceof Integer)
				return new BigInteger(String.valueOf(n1.longValue() / n2.intValue()));
			if (n2 instanceof Short)
				return new BigInteger(String.valueOf(n1.longValue() / n2.shortValue()));
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.longValue() / n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.longValue() / n2.longValue()));
			if (n2 instanceof Byte)
				return new BigInteger(String.valueOf(n1.longValue() / n2.byteValue()));
		}

		if (n1 instanceof Short) {
			if (n2 instanceof Double)
				return new Double(n1.shortValue() / n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.shortValue() / n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.shortValue() / n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.shortValue() / n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.shortValue() / n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.shortValue() / n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.shortValue() / n2.longValue()));
			if (n2 instanceof Byte)
				return new Integer(n1.shortValue() / n2.byteValue());
		}
		
		if (n1 instanceof BigDecimal) {
			if (n2 instanceof Double)
				return new BigDecimal(n1.doubleValue() / n2.doubleValue());
			if (n2 instanceof Float)
				return new BigDecimal(n1.doubleValue() / n2.floatValue());
			if (n2 instanceof Long)
				return new BigDecimal(n1.doubleValue() / n2.longValue());
			if (n2 instanceof Integer)
				return new BigDecimal(n1.doubleValue() / n2.intValue());
			if (n2 instanceof Short)
				return new BigDecimal(n1.doubleValue() / n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.doubleValue() / n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigDecimal(n1.doubleValue() / n2.longValue());
			if (n2 instanceof Byte)
				return new BigDecimal(n1.doubleValue() / n2.byteValue());
		}

		//if (n1 instanceof Byte) {
			if (n2 instanceof Double)
				return new Double(n1.shortValue() / n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.shortValue() / n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.shortValue() / n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.shortValue() / n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.shortValue() / n2.shortValue());
			if (n2 instanceof BigInteger) 
				return new BigInteger(String.valueOf(n1.shortValue() / n2.longValue()));
			if (n2 instanceof BigDecimal) 
				return new BigDecimal(n1.shortValue() / n2.doubleValue());
			//if (n2 instanceof Byte)
				return new Integer(n1.byteValue() / n2.byteValue());
		//}
	}
	
	/**
	 * 求模运算
	 * 
	 * @param n1 左参数
	 * @param n2 右参数
	 * @return 结果
	 */
	public static Number modulus(Number n1, Number n2) {
		if (n1 instanceof Double) {
			if (n2 instanceof Double)
				return new Double(n1.doubleValue() % n2.doubleValue());
			if (n2 instanceof Float)
				return new Double(n1.doubleValue() % n2.floatValue());
			if (n2 instanceof Long)
				return new Double(n1.doubleValue() % n2.longValue());
			if (n2 instanceof Integer)
				return new Double(n1.doubleValue() % n2.intValue());
			if (n2 instanceof Short)
				return new Double(n1.doubleValue() % n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.doubleValue() % n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new Double(n1.doubleValue() % n2.longValue());
			if (n2 instanceof Byte)
				return new Double(n1.doubleValue() % n2.byteValue());
		}
	
		if (n1 instanceof Float) {
			if (n2 instanceof Double)
				return new Double(n1.floatValue() % n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.floatValue() % n2.floatValue());
			if (n2 instanceof Long)
				return new Float(n1.floatValue() % n2.longValue());
			if (n2 instanceof Integer)
				return new Float(n1.floatValue() % n2.intValue());
			if (n2 instanceof Short)
				return new Float(n1.floatValue() % n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.floatValue() % n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new Float(n1.floatValue() % n2.longValue());
			if (n2 instanceof Byte)
				return new Float(n1.floatValue() % n2.byteValue());
		}
		
		if (n1 instanceof Long) {
			if (n2 instanceof Double)
				return new Double(n1.longValue() % n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.longValue() % n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.longValue() % n2.longValue());
			if (n2 instanceof Integer)
				return new Long(n1.longValue() % n2.intValue());
			if (n2 instanceof Short)
				return new Long(n1.longValue() % n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.longValue() % n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.longValue() % n2.longValue()));
			if (n2 instanceof Byte)
				return new Long(n1.longValue() % n2.byteValue());
		}
		
		if (n1 instanceof Integer) {
			if (n2 instanceof Double)
				return new Double(n1.intValue() % n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.intValue() % n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.intValue() % n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.intValue() % n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.intValue() % n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.intValue() % n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.intValue() % n2.longValue()));
			if (n2 instanceof Byte)
				return new Integer(n1.intValue() % n2.byteValue());
		}
		
		if (n1 instanceof BigInteger) {
			if (n2 instanceof Double)
				return new Double(n1.longValue() % n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.longValue() % n2.floatValue());
			if (n2 instanceof Long)
				return new BigInteger(String.valueOf(n1.longValue() % n2.longValue()));
			if (n2 instanceof Integer)
				return new BigInteger(String.valueOf(n1.longValue() % n2.intValue()));
			if (n2 instanceof Short)
				return new BigInteger(String.valueOf(n1.longValue() % n2.shortValue()));
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.longValue() % n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.longValue() % n2.longValue()));
			if (n2 instanceof Byte)
				return new BigInteger(String.valueOf(n1.longValue() % n2.byteValue()));
		}

		if (n1 instanceof Short) {
			if (n2 instanceof Double)
				return new Double(n1.shortValue() % n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.shortValue() % n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.shortValue() % n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.shortValue() % n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.shortValue() % n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.shortValue() % n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigInteger(String.valueOf(n1.shortValue() % n2.longValue()));
			if (n2 instanceof Byte)
				return new Integer(n1.shortValue() % n2.byteValue());
		}
		
		if (n1 instanceof BigDecimal) {
			if (n2 instanceof Double)
				return new BigDecimal(n1.doubleValue() % n2.doubleValue());
			if (n2 instanceof Float)
				return new BigDecimal(n1.doubleValue() % n2.floatValue());
			if (n2 instanceof Long)
				return new BigDecimal(n1.doubleValue() % n2.longValue());
			if (n2 instanceof Integer)
				return new BigDecimal(n1.doubleValue() % n2.intValue());
			if (n2 instanceof Short)
				return new BigDecimal(n1.doubleValue() % n2.shortValue());
			if (n2 instanceof BigDecimal)
				return new BigDecimal(n1.doubleValue() % n2.doubleValue());
			if (n2 instanceof BigInteger)
				return new BigDecimal(n1.doubleValue() % n2.longValue());
			if (n2 instanceof Byte)
				return new BigDecimal(n1.doubleValue() % n2.byteValue());
		}

		//if (n1 instanceof Byte) {
			if (n2 instanceof Double)
				return new Double(n1.shortValue() % n2.doubleValue());
			if (n2 instanceof Float)
				return new Float(n1.shortValue() % n2.floatValue());
			if (n2 instanceof Long)
				return new Long(n1.shortValue() % n2.longValue());
			if (n2 instanceof Integer)
				return new Integer(n1.shortValue() % n2.intValue());
			if (n2 instanceof Short)
				return new Integer(n1.shortValue() % n2.shortValue());
			if (n2 instanceof Short)
				return new Integer(n1.shortValue() % n2.shortValue());
			if (n2 instanceof BigInteger) 
				return new BigInteger(String.valueOf(n1.shortValue() % n2.longValue()));
			//if (n2 instanceof Byte)
				return new Integer(n1.byteValue() % n2.byteValue());
		//}
	}
	
	/**
	 * 负数运算
	 * 
	 * @param n1 参数
	 * @return 结果
	 */
	public static Number negative(Number n1) {
		if (n1 instanceof Double)
			return new Double(- n1.doubleValue());
		if (n1 instanceof Float)
			return new Float(- n1.floatValue());
		if (n1 instanceof Long)
			return new Long(- n1.longValue());
		if (n1 instanceof Integer)
			return new Integer(- n1.intValue());
		if (n1 instanceof Short)
			return new Short((short)(- n1.shortValue()));
		if (n1 instanceof BigDecimal)
			return new BigDecimal(-n1.doubleValue());
		if (n1 instanceof BigInteger) 
			return new BigInteger(String.valueOf(-n1.longValue()));
		//if (n1 instanceof Byte)
			return new Byte((byte)(- n1.byteValue()));
	}
	
}
