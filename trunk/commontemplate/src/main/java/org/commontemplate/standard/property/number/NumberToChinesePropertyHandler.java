package org.commontemplate.standard.property.number;

import org.commontemplate.standard.property.PropertyHandlerSupport;

/**
 * 数字转中文
 *
 * @author YanRong
 *
 */
public class NumberToChinesePropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	private static final char NEGATIVE = '负';

	private static final char[] NUMBER = {'零', '一','二','三','四','五', '六', '七', '八', '九'};

	private static final char[] UNIT = {'十', '百', '千', '万', '亿'};

	public Object getProperty(Object bean) throws Exception {
		// TODO 未实现
		return null;
	}

}
