package org.commontemplate.standard.property.number;

import java.math.BigDecimal;

import org.commontemplate.standard.property.PropertyHandlerSupport;

/**
 * 数字转中文货币
 *
 * @author YanRong
 *
 */
public class NumberToChineseCurrencyPropertyHandler extends
		PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	private static final String INTEGRAL = "整";

	private static final String NEGATIVE = "负";

	// 中文数字字符数组
	private static final String[] NUMBER = new String[] { "零", "壹", "贰", "叁", "肆",
			"伍", "陆", "柒", "捌", "玖" };

	// 中文金额单位数组
	private static final String[] UNIT = new String[] { "分", "角", "圆", "拾", "佰",
			"仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟" };

	public Object getProperty(Object bean) throws Exception {

		BigDecimal bigdMoneyNumber = new BigDecimal(((Number) bean).doubleValue());

		if (bigdMoneyNumber.intValue() == 0)
			return NUMBER[0] + UNIT[2] + INTEGRAL;

		String strChineseCurrency = "";

		// 零数位标记
		boolean bZero = true;
		// 中文金额单位下标
		int ChineseUnitIndex = 0;

		// 处理小数部分，四舍五入
		double doubMoneyNumber = Math
				.round(bigdMoneyNumber.doubleValue() * 100);

		// 是否负数
		boolean bNegative = doubMoneyNumber < 0;

		// 取绝对值
		doubMoneyNumber = Math.abs(doubMoneyNumber);

		// 循环处理转换操作
		while (doubMoneyNumber > 0) {
			// 整的处理(无小数位)
			if (ChineseUnitIndex == 2 && strChineseCurrency.length() == 0)
				strChineseCurrency = strChineseCurrency + INTEGRAL;

			// 非零数位的处理
			if (doubMoneyNumber % 10 > 0) {
				strChineseCurrency = NUMBER[(int) doubMoneyNumber % 10]
						+ UNIT[ChineseUnitIndex] + strChineseCurrency;
				bZero = false;
			}
			// 零数位的处理
			else {
				// 元的处理(个位)
				if (ChineseUnitIndex == 2) {
					// 段中有数字
					if (doubMoneyNumber > 0) {
						strChineseCurrency = UNIT[ChineseUnitIndex]
								+ strChineseCurrency;
						bZero = true;
					}
				}
				// 万、亿数位的处理
				else if (ChineseUnitIndex == 6 || ChineseUnitIndex == 10) {
					// 段中有数字
					//if (doubMoneyNumber % 1000 > 0)
						strChineseCurrency = UNIT[ChineseUnitIndex]
								+ strChineseCurrency;
				}

				// 前一数位非零的处理
				if (!bZero)
					strChineseCurrency = NUMBER[0] + strChineseCurrency;

				bZero = true;
			}

			doubMoneyNumber = Math.floor(doubMoneyNumber / 10);
			ChineseUnitIndex++;
		}

		// 负数的处理
		if (bNegative)
			strChineseCurrency = NEGATIVE + strChineseCurrency;

		return strChineseCurrency;
	}

}
