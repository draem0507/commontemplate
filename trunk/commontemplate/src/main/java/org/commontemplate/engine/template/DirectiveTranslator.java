package org.commontemplate.engine.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.commontemplate.core.Element;
import org.commontemplate.core.ParsingException;
import org.commontemplate.util.scanner.Token;

/**
 * 指令转译器
 * 
 * @author liangfei0201@163.com
 *
 */
final class DirectiveTranslator {

	private final DirectiveProvider directiveFactory;

	DirectiveTranslator(DirectiveProvider directiveFactory) {
		this.directiveFactory = directiveFactory;
	}

	/**
	 * 将片断列表转译成指令列表
	 * 
	 * @param tokens 片断列表, 类型: List&lt;Token&gt;
	 * @return 模板元素列表, 类型: List&lt;Element&gt;
	 * @throws ParsingException
	 * @throws IOException
	 */
	List translate(final List tokens) throws ParsingException, IOException {
		if (tokens == null || tokens.size() == 0) 
			return new ArrayList(0);
		
		List directives = new ArrayList(tokens.size());
		for (int i = 0, n = tokens.size(); i < n; i ++) {
			Element directive = directiveFactory.getDirective((Token) tokens.get(i), (i >= (n - 1)));
			if (directive != null)
				directives.add(directive);
		}
		return directives;
	}

}
