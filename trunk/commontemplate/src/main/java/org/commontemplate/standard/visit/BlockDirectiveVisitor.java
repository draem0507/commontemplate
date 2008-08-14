package org.commontemplate.standard.visit;

import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Expression;
import org.commontemplate.core.Node;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.Assert;

/**
 * 元素集访问者.
 * <pre>
 * ElementsVisitor.findElements(template, "zone", "body");
 * </pre>
 *
 * @author liangfei0201@163.com
 *
 */
public class BlockDirectiveVisitor implements Visitor {

	private final String type;

	private final String name;

	private BlockDirective blockDirective;

	public BlockDirectiveVisitor(String type, String name) {
		Assert.assertNotNull(type, "ElementsVisitor.type.required");
		Assert.assertNotNull(name, "ElementsVisitor.name.required");
		this.type = type;
		this.name = name;
	}

	public int visit(Node node) {
		if (node instanceof Expression)
			return SKIP;
		if (node instanceof BlockDirective) {
			BlockDirective blockDirective = (BlockDirective)node;
			if (type.equals(blockDirective.getName())) {
				Expression expression = blockDirective.getExpression();
				if (expression != null && name.equals(expression.evaluate(null))) {
					this.blockDirective = blockDirective;
					return STOP;
				}
			}
		}
		return NEXT;
	}

	public BlockDirective getBlockDirective() {
		return blockDirective;
	}

	/**
	 * 查找模板指令
	 *
	 * @param node 模板
	 * @param type 块指令类型名
	 * @param name 块名
	 * @return 块指令;
	 */
	public static BlockDirective findBlockDirective(Node node, String type, String name) {
		BlockDirectiveVisitor visitor = new BlockDirectiveVisitor(type, name);
		node.accept(visitor);
		return visitor.getBlockDirective();
	}

	/**
	 * 查找模板指令内部元素
	 *
	 * @param node 模板
	 * @param type 块指令类型名
	 * @param name 块名
	 * @return 块指令的内部元素集;
	 */
	public static List findInnerElements(Node node, String type, String name) {
		BlockDirective blockDirective = findBlockDirective(node, type, name);
		if (blockDirective == null)
			return null;
		return blockDirective.getElements();
	}

}