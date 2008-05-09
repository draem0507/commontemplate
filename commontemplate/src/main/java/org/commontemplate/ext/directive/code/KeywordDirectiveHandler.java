package org.commontemplate.ext.directive.code;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.core.Context;
import org.commontemplate.util.Assert;

/**
 * 关键字高亮显示指令(HTML).
 * 
 * @author liangfei0201@163.com
 *
 */
public class KeywordDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private String color;

	public final void setColor(String color) {
		this.color = color;
	}

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		List keywords = null;
		if (param instanceof String) {
			keywords = new ArrayList(1);
			keywords.add((String)param);
		} else if (param instanceof List) {
			keywords = (List)param;
		} else {
			Assert.fail("参数错误, 必需为字符串列表, 但实际为: " + param);
		}
		context.setOutputFilter(new KeywordFilter(keywords, color));
		DirectiveUtils.renderAll(innerElements, context);
		context.removeOutputFilter();
	}

}
