package org.commontemplate.engine;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.OutputController;
import org.commontemplate.core.OutputConverter;
import org.commontemplate.core.OutputFilter;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.UnformattedException;
import org.commontemplate.util.StringCastUtils;

/**
 * 输出控制器实现
 *
 * @author liangfei0201@163.com
 *
 */
final class OutputControllerImpl implements OutputController, Serializable {

	private static final long serialVersionUID = 1L;

	private final LocalContext superLocalContext;

	private final Context context;

	private transient final Writer out;

	OutputControllerImpl(LocalContext superLocalContext, Context context, Writer out) {
		this.superLocalContext = superLocalContext;
		this.context = context;
		this.out = out;
	}

	private OutputFilter outputFilter;

	public void setOutputFilter(OutputFilter outputFilter) {
		this.outputFilter = outputFilter;
	}

	public OutputFilter getOutputFilter() {
		return outputFilter;
	}

	public void removeOutputFilter() {
		this.outputFilter = null;
	}

	private OutputFormatter generalFormatter;

	public void setGeneralOutputFormatter(OutputFormatter generalOutputFormatter) {
		this.generalFormatter = generalOutputFormatter;
	}

	public OutputFormatter getGeneralOutputFormatter() {
		return generalFormatter;
	}

	public void removeGeneralOutputFormatter() {
		this.generalFormatter = null;
	}

	private final Map typeFormatters = new HashMap();

	// 将null单独保存而不放到Map中，避免在Map中重复查找null
	private OutputFormatter nullFormatter;

	public void setOutputFormatter(Class type, OutputFormatter outputFormatter) {
		if (type == null)
			nullFormatter = outputFormatter;
		else
			typeFormatters.put(type, outputFormatter);
	}

	public OutputFormatter getOutputFormatter(Class type) {
		if (type == null)
			return nullFormatter;
		else
			return (OutputFormatter)typeFormatters.get(type);
	}

	public void removeOutputFormatter(Class type) {
		if (type == null)
			nullFormatter = null;
		else
			typeFormatters.remove(type);
	}

	public void clearOutputFormatters() {
		generalFormatter = null;
		nullFormatter = null;
		typeFormatters.clear();
	}

	public void output(Object model) throws IOException {
		if (outputConverter != null)
			model = outputConverter.convert(model);
		if (model instanceof String)
			outputText((String)model);
		else
			outputText(format(model));
	}

	private void outputText(String text) throws IOException {
		if (outputFilter != null)
			text = outputFilter.filter(text);
		if (superLocalContext != null)
			superLocalContext.output(text);
		else if (text != null)
			out.write(text);
	}

	public String format(Object model) {
		try {
			if (model == null) {
				if (nullFormatter != null)
					return nullFormatter.format(null, context.getLocale(), context.getTimeZone());
			} else {
				if (typeFormatters != null && typeFormatters.size() > 0) {
					for (Iterator iterator = typeFormatters.entrySet().iterator(); iterator.hasNext();) {
						Map.Entry entry = (Map.Entry)iterator.next();
						Class type = (Class)entry.getKey();
						if (type != null && type.isAssignableFrom(model.getClass())) {
							OutputFormatter typeFormatter = (OutputFormatter)entry.getValue();
							if (typeFormatter != null)
								return typeFormatter.format(model, context.getLocale(), context.getTimeZone());
						}
					}
				}
			}
			if (generalFormatter != null) {
				return generalFormatter.format(model, context.getLocale(), context.getTimeZone());
			}
		} catch (UnformattedException e) {
			// Ignore, 让其继续调用下面的代码
		}
		// 调用父区域格式化
		if (superLocalContext != null)
			return superLocalContext.format(model);
		// 默认处理方式
		return DEFAULT_FORMATTER.format(model, context.getLocale(), context.getTimeZone());
	}

	private static final OutputFormatter DEFAULT_FORMATTER = new DefaultOutputFormatter();

	private static final class DefaultOutputFormatter implements OutputFormatter, Serializable {

		private static final long serialVersionUID = 1L;

		public String format(Object model, Locale locale, TimeZone timeZone)
				throws UnformattedException {
			return model == null ? null : StringCastUtils.objectToString(model);
		}

	};

	private OutputConverter outputConverter;

	public OutputConverter getOutputConverter() {
		return outputConverter;
	}

	public void removeOutputConverter() {
		outputConverter = null;
	}

	public void setOutputConverter(OutputConverter outputConverter) {
		this.outputConverter = outputConverter;
	}

}
