package org.commontemplate.standard.visit;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Expression;
import org.commontemplate.core.Node;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.Assert;

public class BlockDirectivesVisitor implements Visitor {

	private final String type;

	private final List blockDirectives = new ArrayList();

	public BlockDirectivesVisitor(String type) {
		Assert.assertNotNull(type, "ElementsVisitor.type.required");
		this.type = type;
	}

	public int visit(Node node) {
		if (node instanceof Expression)
			return SKIP;
		if (node instanceof BlockDirective) {
			BlockDirective blockDirective = (BlockDirective)node;
			if (type.equals(blockDirective.getName())) {
				blockDirectives.add(blockDirective);
			}
		}
		return NEXT;
	}

	public List getBlockDirectives() {
		return blockDirectives;
	}

	/**
	 * 查找模板元素
	 *
	 * @param node 模板
	 * @param type 块指令类型名
	 * @param name 块名
	 * @return 块指令的内部元素集;
	 */
	public static List findBlockDirectives(Node node, String type) {
		BlockDirectivesVisitor visitor = new BlockDirectivesVisitor(type);
		node.accept(visitor);
		return visitor.getBlockDirectives();
	}

}