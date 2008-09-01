package org.commontemplate.standard.visit;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateVisitor;
import org.commontemplate.util.Assert;

public class BlockDirectivesVisitor extends TemplateVisitor {

	private final String type;

	private final List blockDirectives = new ArrayList();

	public BlockDirectivesVisitor(String type) {
		Assert.assertNotNull(type, "ElementsVisitor.type.required");
		this.type = type;
	}

	public void visitBlockDirective(BlockDirective blockDirective) {
		if (type.equals(blockDirective.getName())) {
			blockDirectives.add(blockDirective);
		}
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
	public static List findBlockDirectives(Template template, String type) {
		BlockDirectivesVisitor visitor = new BlockDirectivesVisitor(type);
		template.accept(visitor);
		return visitor.getBlockDirectives();
	}

}