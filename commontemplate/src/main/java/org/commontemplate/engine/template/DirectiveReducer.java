package org.commontemplate.engine.template;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Element;
import org.commontemplate.util.Assert;
import org.commontemplate.util.LinkedStack;
import org.commontemplate.util.Stack;

/**
 * 指令树归约器
 * 
 * @author liangfei0201@163.com
 *
 */
final class DirectiveReducer {
	
	DirectiveReducer() {
		
	}

	/**
	 * 将指令列表归约成树
	 * 
	 * @param directives 指令列表
	 * @return 指令树的根
	 */
	RootDirective reduce(List directives) {
		Stack directiveStack = new LinkedStack();
		RootDirective rootDirective = new RootDirective();
		directiveStack.push(new BlockDirectiveEntry(rootDirective));
		for (int i = 0, n = directives.size(); i < n; i ++) {
			Element directive = (Element)directives.get(i);
			// 弹栈
			if (directive == EndDirective.END_DIRECTIVE || directive instanceof MiddleDirective)
				((BlockDirectiveEntry) directiveStack.pop()).popDirective();
			// 设置树
			if (directive != EndDirective.END_DIRECTIVE) // 排除EndDirective
				((BlockDirectiveEntry) directiveStack.peek()).appendInnerDirective(directive);
			// 压栈
			if (directive instanceof BlockDirective)
				directiveStack.push(new BlockDirectiveEntry((BlockDirective) directive));
		}
		Element root = ((BlockDirectiveEntry) directiveStack.pop()).popDirective();
		Assert.assertTrue(directiveStack.isEmpty(), "有指令未结束!"); // 后验条件
		return (RootDirective)root;
	}

	// 指令归约辅助封装类
	private static final class BlockDirectiveEntry {

		private BlockDirective blockDirective;

		private List elements = new ArrayList();

		BlockDirectiveEntry(BlockDirective blockDirective) {
			this.blockDirective = blockDirective;
		}

		void appendInnerDirective(Element innerDirective) {
			this.elements.add(innerDirective);
		}

		Element popDirective() {
			((BlockDirectiveSupport)blockDirective).setElements(elements);
			return blockDirective;
		}

	}

}
