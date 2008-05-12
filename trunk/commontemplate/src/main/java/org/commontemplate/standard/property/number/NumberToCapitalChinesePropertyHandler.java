package org.commontemplate.standard.property.number;

import org.commontemplate.standard.property.PropertyHandlerSupport;

/**
 * 数字转大写中文
 *
 * @author YanRong
 *
 */
public class NumberToCapitalChinesePropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	private static final char NEGATIVE = '负';

	private static final char[] NUMBER = {'零', '壹','贰','叁','肆','伍', '陆', '柒', '捌', '玖'};

	private static final char[] UNIT = {'拾', '百', '千', '万', '亿'};

	public Object getProperty(Object bean) throws Exception {
		// TODO 未实现
		return null;
	}

}
