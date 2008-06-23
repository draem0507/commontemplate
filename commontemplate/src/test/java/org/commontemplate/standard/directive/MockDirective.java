package org.commontemplate.standard.directive;

import java.io.IOException;
import java.io.Writer;

import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.Location;
/**
 * Mock一个Element的实现，用来测试指令。
 * @author YanRong
 *
 */
public class MockDirective extends Element {

	private static final long serialVersionUID = 1L;

	private String name;

	public MockDirective(String name) {
		this.name = name;
	}

	public void render(Context context) throws RenderingException {

		Writer out = context.getOut();
		try {
			out.write(name);
		} catch (IOException e) {

		}
	}

	/**
	 * 获取模板元素的名称
	 *
	 * @return 模板元素名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取模板元素在模板中的位置
	 *
	 * @return 元素在模板中的位置
	 */
	public Location getLocation() {
		return null;
	}

	/**
	 * 获取模板元素的标准组成
	 *
	 * @return 模板元素的标准组成
	 */
	public String getSource() {
		return null;
	}

	public String getSignature() {
		return getSource();
	}

	/**
	 * 返回模板元素的标准组成, 同getCanonicalForm()
	 *
	 * @return 模板元素的标准组成
	 */
	public String toString() {
		return getSource();
	}

	/**
	 * 接收访问者, 并带领访问者遍历整个树 (中序遍历)
	 *
	 * @param visitor 访问者
	 */
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public void setName(String name) {
		this.name = name;
	}

	public Template getTemplate() {
		return null;
	}

	public String getType() {
		return "MockDirective";
	}

}
