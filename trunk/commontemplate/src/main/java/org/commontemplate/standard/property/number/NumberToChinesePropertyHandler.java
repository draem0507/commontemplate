package org.commontemplate.standard.property.number;

import java.math.BigDecimal;

import org.commontemplate.standard.property.PropertyHandlerSupport;

/**
 * 数字转中文
 *
 * @author YanRong
 *
 */
public class NumberToChinesePropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	private static final String NEGATIVE = "负";

	private static final String[] NUMBER = {"零", "一","二","三","四","五", "六", "七", "八", "九"};

	private static final String[] UNIT = {"","","","十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};

	public Object getProperty(Object bean) throws Exception {
		BigDecimal bigdMoneyNumber = new BigDecimal(((Number) bean).doubleValue());

		if (bigdMoneyNumber.intValue() == 0)
			return String.valueOf(NUMBER[0]);

		String strChineseDigital = "";

		// 零数位标记
		boolean bZero = true;
		// 中文数字单位下标
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
			
			// 非零数位的处理
			if (doubMoneyNumber % 10 > 0) {
				strChineseDigital = NUMBER[(int) doubMoneyNumber % 10]
						+ UNIT[ChineseUnitIndex] 
						+ strChineseDigital;
				bZero = false;
			}
			// 零数位的处理
			else {
				
				// 万、亿数位的处理
				if (ChineseUnitIndex == 6 || ChineseUnitIndex == 10) {
					// 段中有数字
					//if (doubMoneyNumber % 1000 > 0)
						strChineseDigital = UNIT[ChineseUnitIndex]
								+ strChineseDigital;
				}

				// 前一数位非零的处理
				if (!bZero)
					strChineseDigital = NUMBER[0] + strChineseDigital;

				bZero = true;
			}

			doubMoneyNumber = Math.floor(doubMoneyNumber / 10);
			ChineseUnitIndex++;
		}

		// 负数的处理
		if (bNegative)
			strChineseDigital = NEGATIVE + strChineseDigital;

		return strChineseDigital;
	}

}
