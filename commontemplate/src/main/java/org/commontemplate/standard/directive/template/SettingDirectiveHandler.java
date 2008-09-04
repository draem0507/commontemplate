package org.commontemplate.standard.directive.template;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import java.util.Map.Entry;

import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.standard.format.BooleanFormatter;
import org.commontemplate.standard.format.DateFormatter;
import org.commontemplate.standard.format.NullFormatter;
import org.commontemplate.standard.format.NumberFormatter;
import org.commontemplate.util.Assert;
import org.commontemplate.util.LocaleUtils;

public class SettingDirectiveHandler extends DirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, Directive directive) throws Exception {
		Expression expression = directive.getExpression();
		Assert.assertNotNull(expression, "$setting指令的参数不能为空!"); // TODO 未国际化
		Object param = expression.evaluate(context);
		if (param instanceof Entry) {
			setSetting(context, (Entry)param);
		} else if (param instanceof Map) {
			for (Iterator iterator = ((Map)param).entrySet().iterator(); iterator.hasNext();) {
				Entry entry = (Entry)iterator.next();
				setSetting(context, entry);
			}
		} else {
			Assert.fail("$setting指令的参数\"" + expression.getSource()
					+ "\"不正确，只能为属性键值对！如："
					+ "$settings{debug:true,locale:\"zh_CN\",timeZone:\"GMT+8\","
					+ "dateFormat:\"yyyy-MM-DD HH:mm:ss\",numberFormat:\"###,##0.00\"}"); // TODO 未国际化
		}
	}

	private void setSetting(Context context, Entry entry) {
		if ("debug".equals(entry.getKey())) {
			context.setDebug(((Boolean)entry.getValue()).booleanValue());
		} else if ("locale".equals(entry.getKey())) {
			context.setLocale(LocaleUtils.getLocale(String.valueOf(entry.getValue())));
		} else if ("timeZone".equals(entry.getKey())) {
			context.setTimeZone(TimeZone.getTimeZone(String.valueOf(entry.getValue())));
		} else if ("dateFormat".equals(entry.getKey())) {
			context.getRootLocalContext().setOutputFormatter(Date.class, new DateFormatter(String.valueOf(entry.getValue())));
		} else if ("numberFormat".equals(entry.getKey())) {
			context.getRootLocalContext().setOutputFormatter(Number.class, new NumberFormatter(String.valueOf(entry.getValue())));
		} else if ("booleanValue".equals(entry.getKey())) {
			context.getRootLocalContext().setOutputFormatter(null, new BooleanFormatter(String.valueOf(entry.getValue())));
		} else if ("nullValue".equals(entry.getKey())) {
			context.getRootLocalContext().setOutputFormatter(null, new NullFormatter(String.valueOf(entry.getValue())));
		} else {
			Assert.fail("不支持setting配置参数：" + entry.getKey()); // TODO 未国际化
		}
	}

	public boolean isExpressionRequired() {
		return true;
	}

}