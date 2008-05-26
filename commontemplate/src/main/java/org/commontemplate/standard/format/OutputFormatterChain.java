package org.commontemplate.standard.format;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.Map.Entry;

import org.commontemplate.config.ConfigurationException;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.UnformattedException;
import org.commontemplate.util.ClassUtils;

public class OutputFormatterChain implements OutputFormatter, Serializable {

	private static final long serialVersionUID = 1L;

	private Map typeFormatters;

	private OutputFormatter nullFormatter;

	private OutputFormatter generalFormatter;

	public void setTypeFormatters(Map typeFormatters) {
		this.typeFormatters = new HashMap(typeFormatters.size());
		for (Iterator iterator = typeFormatters.entrySet().iterator(); iterator.hasNext();) {
			Entry entry = (Entry)iterator.next();
			try {
				this.typeFormatters.put(ClassUtils.forCanonicalName((String)entry.getKey()), entry.getValue());
			} catch (ClassNotFoundException e) {
				throw new ConfigurationException("OutputFormatterChain.invaild.class.name", new Object[]{entry.getKey()}, e);
			}
		}
	}

	public void setNullFormatter(OutputFormatter nullFormatter) {
		this.nullFormatter = nullFormatter;
	}

	public void setGeneralFormatter(OutputFormatter generalFormatter) {
		this.generalFormatter = generalFormatter;
	}

	public String format(Object model, Locale locale, TimeZone timeZone)
			throws UnformattedException {
		if (model == null) {
			if (nullFormatter != null)
				return nullFormatter.format(null, locale, timeZone);
		} else {
			if (typeFormatters != null && typeFormatters.size() > 0) {
				for (Iterator iterator = typeFormatters.entrySet().iterator(); iterator.hasNext();) {
					Map.Entry entry = (Map.Entry)iterator.next();
					Class type = (Class)entry.getKey();
					if (type != null && type.isAssignableFrom(model.getClass())) {
						OutputFormatter typeFormatter = (OutputFormatter)entry.getValue();
						if (typeFormatter != null)
							return typeFormatter.format(model, locale, timeZone);
					}
				}
			}
		}
		if (generalFormatter != null)
			return generalFormatter.format(model, locale, timeZone);
		throw new UnformattedException();
	}

}