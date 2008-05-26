package org.commontemplate.standard.property.number;

import org.commontemplate.standard.property.PropertyHandlerSupport;
import org.commontemplate.util.I18nExceptionFactory;

public class NumberToSizeHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object getProperty(Object bean) throws Exception {
		float value = ((Number)bean).floatValue();
		if (value < 0)
			throw I18nExceptionFactory.createIllegalArgumentException("NumberToSizeHandler.size.error", new Object[]{new Float(value)});
		if (value < 1024)
			return ((Number)bean).intValue() + "Bytes";
		if (value < 10024 * 1024)
			return format(value / 1024) + "KB";
		return format(value / (1024 * 1024)) + "MB";
	}

	private String format(float value) {
		return new java.text.DecimalFormat("###,##0.##").format(value);
	}

}