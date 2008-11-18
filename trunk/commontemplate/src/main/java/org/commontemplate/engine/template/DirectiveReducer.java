package org.commontemplate.engine.template;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Element;
import org.commontemplate.core.ParsingException;
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
	RootBlockDirectiveImpl reduce(List directives) {
		Stack directiveStack = new LinkedStack();
		RootBlockDirectiveImpl rootDirective = new RootBlockDirectiveImpl();
		directiveStack.push(new BlockDirectiveEntry(rootDirective));
		for (int i = 0, n = directives.size(); i < n; i ++) {
			Element directive = (Element)directives.get(i);
			if (directive == null)
				continue;
			// 弹栈
			if (directive instanceof EndDirective
					|| directive instanceof MiddleBlockDirectiveImpl) {
				if (directiveStack.isEmpty())
					throw new ParsingException(directive.getLocation(), "DirectiveReducer.block.directive.excrescent.end");
				BlockDirective blockDirective = ((BlockDirectiveEntry) directiveStack.pop()).popDirective();
				if (directive instanceof EndDirective) {
					String blockDirectiveName = ((EndDirective)directive).getBlockDirectiveName();
					if (blockDirectiveName != null && ! blockDirectiveName.equals(blockDirective.getName()))
						throw new ParsingException(directive.getLocation(), "DirectiveReducer.block.directive.invaild.end", new Object[]{blockDirectiveName, blockDirective.getName()});
				}
			}
			// 设置树
			if (! (directive instanceof EndDirective)) { // 排除EndDirective
				if (directiveStack.isEmpty())
					throw new ParsingException(directive.getLocation(), "DirectiveReducer.block.directive.excrescent.end");
				((BlockDirectiveEntry) directiveStack.peek()).appendInnerDirective(directive);
			}
			// 压栈
			if (directive instanceof BlockDirective)
				directiveStack.push(new BlockDirectiveEntry((BlockDirective) directive));
		}
		Element root = ((BlockDirectiveEntry) directiveStack.pop()).popDirective();
		if (! directiveStack.isEmpty()) { // 后验条件
			throw new ParsingException(root.getLocation(), "DirectiveReducer.block.directive.without.end", new Object[]{root.getName()});
		}
		return (RootBlockDirectiveImpl)root;
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

		BlockDirective popDirective() {
			((BlockDirectiveSupport)blockDirective).setElements(elements);
			return blockDirective;
		}

	}

}
