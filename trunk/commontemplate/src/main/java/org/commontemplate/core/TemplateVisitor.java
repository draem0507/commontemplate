package org.commontemplate.core;

/**
 * 模板结构访问者，用于遍历整个模板指令树.<br>
 * 使用如:
 * <pre>
 * TemplateVisitor visitor = new MyTemplateVisitor();
 * template.accept(visitor);
 * </pre>
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class TemplateVisitor extends ExpressionVisitor {

	/**
	 * 当访问到模板时被回调
	 *
	 * @see org.commontemplate.core.Template#accept(TemplateVisitor)
	 * @param template 访问到的模板
	 * @throws StopVisitException 当希望停止访问时抛出
	 */
	public void visitTemplate(Template template) throws StopVisitException {}

	/**
	 * 模板访问结束时被回调
	 *
	 * @see org.commontemplate.core.Template#accept(TemplateVisitor)
	 * @param template 结束的模板
	 * @throws StopVisitException 当希望停止访问时抛出
	 */
	public void endTemplate(Template template) throws StopVisitException {}

	/**
	 * 当访问到文本块或不解析块时被回调
	 *
	 * @see org.commontemplate.core.Text#accept(TemplateVisitor)
	 * @param text 访问到的文本块或不解析块
	 * @throws StopVisitException 当希望停止访问时抛出
	 */
	public void visitText(Text text) throws StopVisitException {}

	/**
	 * 当访问到行注释或块注释时被回调
	 *
	 * @see org.commontemplate.core.Comment#accept(TemplateVisitor)
	 * @param comment 访问到的行注释或块注释
	 * @throws StopVisitException 当希望停止访问时抛出
	 */
	public void visitComment(Comment comment) throws StopVisitException {}

	/**
	 * 当访问到行指令时被回调.<br>
	 * 注：缺省实现为继续访问指令表达式。<br>
	 * 如果不需要访问指令表达式，请覆写此函数并留空。<br>
	 * 也可以在访问指令表达式前后作相关处理：<br>
	 * <pre>
	 * public void visitDirective(Directive directive) {
	 *     // 在表达式访问之前处理...
	 *     super.visitDirective(directive);
	 *     // 在表达式访问之后处理...
	 * }
	 * </pre>
	 * @see org.commontemplate.core.Directive#accept(TemplateVisitor)
	 * @param directive 访问到的行指令
	 * @throws StopVisitException 当希望停止访问时抛出
	 */
	public void visitDirective(Directive directive) throws StopVisitException {
		if (directive.getExpression() != null)
			directive.getExpression().accept(this, false);
	}

	/**
	 * 当访问到块指令时被回调.<br>
	 * 注：缺省实现为继续访问指令表达式。<br>
	 * 如果不需要访问指令表达式，请覆写此函数并留空。<br>
	 * 也可以在访问指令表达式前后作相关处理：<br>
	 * <pre>
	 * public void visitBlockDirective(BlockDirective blockDirective) {
	 *     // 在表达式访问之前处理...
	 *     super.visitBlockDirective(blockDirective);
	 *     // 在表达式访问之后处理...
	 * }
	 * </pre>
	 * @see org.commontemplate.core.BlockDirective#accept(TemplateVisitor)
	 * @param blockDirective 访问到的块指令
	 * @throws StopVisitException 当希望停止访问时抛出
	 */
	public void visitBlockDirective(BlockDirective blockDirective) throws StopVisitException {
		if (blockDirective.getExpression() != null)
			blockDirective.getExpression().accept(this, false);
	}

	/**
	 * 块指令访问结束时被回调
	 *
	 * @see org.commontemplate.core.BlockDirective#accept(TemplateVisitor)
	 * @param blockDirective 结束的块指令
	 * @throws StopVisitException 当希望停止访问时抛出
	 */
	public void endBlockDirective(BlockDirective blockDirective) throws StopVisitException {}

}