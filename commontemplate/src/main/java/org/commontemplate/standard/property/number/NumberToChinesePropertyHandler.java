package org.commontemplate.standard.property.number;

import org.commontemplate.standard.property.PropertyHandlerSupport;

/**
 * 数字转中文
 *
 * @author liangfei0201@163.com
 *
 */
public class NumberToChinesePropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	private static final char NEGATIVE = '负';

	private static final char[] NUMBER = {'零', '一','二','三','四','五', '六', '七', '八', '九'};

	private static final String[] UNIT = {"十", "百", "千", "万", "十万", "百万", "千万", "亿"};

	private String getUnit(int i) {
		if (i < UNIT.length)
			return UNIT[i];
		else
			return UNIT[i - UNIT.length] + UNIT[i];
	}

	public Object getProperty(Object bean) throws Exception { // TODO 未实现
		int num = ((Number)bean).intValue();
		StringBuffer chinese = new StringBuffer();
		if (num < 0) {
			chinese.append(NEGATIVE);
			num = -num;
		}
		if (num < 10) {
			chinese.append(NUMBER[num]);
		} else {
			// 转换成中文
			String arabiaNumber = String.valueOf(num);
			StringBuffer chineseNumber = new StringBuffer();
			for (int i = 0, n = arabiaNumber.length(); i < n; i ++) {
				chineseNumber.append(NUMBER[arabiaNumber.charAt(i) - 48]);
			}
			// 加入单位
			boolean beforeZero = false;
			for (int i = 0, n = chineseNumber.length(); i < n; i ++) {
				char ch = chineseNumber.charAt(i);
				boolean zero = (NUMBER[0] == ch);
				int l = n - i - 2;
				if (l >= 0) {
					if (zero && beforeZero)
						continue;
					chinese.append(ch);
					if (! zero)
						chinese.append(getUnit(l));
				} else {
					if (! zero)
						chinese.append(ch);
				}
				beforeZero = zero;
			}
		}
		return chinese.toString();
	}

}
