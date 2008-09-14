package org.commontemplate.tools;

import junit.framework.TestCase;
/**
 * TemplateRenderer的测试
 *
 * @author Yan Rong
 *
 */
public class TemplateRendererTester extends TestCase {

	public void testTemplateRenderer() {
		User user = new User();
		user.setName("YanRong");
		TemplateRenderer templateRender = new TemplateRenderer("$for{times}${user.name}$end");
		templateRender.put("times", 5);
		templateRender.put("user", user);
		assertEquals("YanRongYanRongYanRongYanRongYanRong", templateRender.evaluate());
	}

	private static class User {

		String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}

