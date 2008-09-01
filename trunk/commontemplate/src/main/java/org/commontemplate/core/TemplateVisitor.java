package org.commontemplate.core;

/**
 * 模板结构访问者，用于遍历整个模板指令树
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class TemplateVisitor {

	public void visitTemplate(Template template) {}

	public void visitText(Text text) {}

	public void visitComment(Comment comment) {}

	public void visitDirective(Directive directive) {}

	public void visitBlockDirective(BlockDirective blockDirective) {}

	public void visitEndBlockDirective() {}

}