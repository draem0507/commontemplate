package org.commontemplate.standard.property.number;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class NumberToCapitalChinesePropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	private static final char[] CHINESE_NUMBER = {'零', '壹','贰','叁','肆','伍', '陆', '柒', '捌', '玖'};

	private static final String[] CHINESE_UNIT = {"拾", "百", "千", "万", "十万", "百万", "千万", "亿"};

	public Object getProperty(Object bean) throws Exception { // TODO 未实现
		int value = ((Number)bean).intValue();
		StringBuffer chinese = new StringBuffer();
		chinese.append(CHINESE_UNIT);
		return chinese.toString();
	}

}
