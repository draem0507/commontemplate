package org.commontemplate.standard.visitor;

import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.core.StopVisitException;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateVisitor;
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
public class BlockDirectiveVisitor extends TemplateVisitor {

	private final String type;

	private final String name;

	private final Context context;

	private BlockDirective blockDirective;

	public BlockDirectiveVisitor(String type, String name, Context context) {
		Assert.assertNotNull(type, "ElementsVisitor.type.required");
		Assert.assertNotNull(name, "ElementsVisitor.name.required");
		this.type = type;
		this.name = name;
		this.context = context;
	}

	public void visitDirective(Directive directive) {
		// 忽略TemplateVisitor中的指令表达式访问缺省实现
	}

	public void visitBlockDirective(BlockDirective blockDirective) {
		if (type.equals(blockDirective.getName())) {
			Expression expression = blockDirective.getExpression();
			if (expression != null && name.equals(expression.evaluate(context))) {
				this.blockDirective = blockDirective;
				throw new StopVisitException();
			}
		}
	}

	public BlockDirective getBlockDirective() {
		return blockDirective;
	}

	/**
	 * 查找模板指令
	 *
	 * @param template 模板
	 * @param type 块指令类型名
	 * @param name 块名
	 * @return 块指令;
	 */
	public static BlockDirective findBlockDirective(Template template, String type, String name, Context context) {
		BlockDirectiveVisitor visitor = new BlockDirectiveVisitor(type, name, context);
		template.accept(visitor);
		return visitor.getBlockDirective();
	}

	/**
	 * 查找模板指令内部元素
	 *
	 * @param template 模板
	 * @param type 块指令类型名
	 * @param name 块名
	 * @return 块指令的内部元素集;
	 */
	public static List findBlockDirectiveElements(Template template, String type, String name, Context context) {
		BlockDirective blockDirective = findBlockDirective(template, type, name, context);
		if (blockDirective == null)
			return null;
		return blockDirective.getElements();
	}

}