package org.commontemplate.standard.visit;

import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.BreakVisitException;
import org.commontemplate.core.Expression;
import org.commontemplate.core.Visitable;
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
public class ElementsVisitor implements Visitor {

	private final String type;

	private final String name;

	private List elements;

	public ElementsVisitor(String type, String name) {
		Assert.assertNotNull(type, "ElementsVisitor.type.required");
		Assert.assertNotNull(name, "ElementsVisitor.name.required");
		this.type = type;
		this.name = name;
	}

	public void visit(Visitable node) throws BreakVisitException {
		if (node instanceof BlockDirective) {
			BlockDirective blockDirective = (BlockDirective)node;
			if (type.equals(blockDirective.getName())) {
				Expression expression = blockDirective.getExpression();
				if (expression != null && name.equals(expression.evaluate(null))) {
					elements = blockDirective.getElements();
					throw new BreakVisitException();
				}
			}
		}
	}

	public List getElements() {
		return elements;
	}

	/**
	 * 查找模板元素
	 *
	 * @param node 模板
	 * @param type 块指令类型名
	 * @param name 块名
	 * @return 块指令的内部元素集;
	 */
	public static List findElements(Visitable node, String type, String name) {
		ElementsVisitor visitor = new ElementsVisitor(type, name);
		try {
			node.accept(visitor);
		} catch (BreakVisitException e) {
			// ignore
		}
		return visitor.getElements();
	}

}