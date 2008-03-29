package org.commontemplate.ext.directive.taglib;

import integration.TestEngineProvider;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.commontemplate.engine.Engine;
import org.commontemplate.ext.directive.taglib.TagBlockDirectiveAdapter;

public class TagBlockDirectiveAdapterTester extends TestCase {

	private TagBlockDirectiveAdapter tagBlockDirectiveAdapter;

	public void setUp() {
		tagBlockDirectiveAdapter = new TagBlockDirectiveAdapter();
		tagBlockDirectiveAdapter.setTagClassName(TestTag.class.getName());
	}

	public void tearDown() {
		tagBlockDirectiveAdapter = null;
	}

	public void testRightTag() throws Exception {
		Map param = new HashMap();
		List innerDirectives = new ArrayList();
		Engine engine = TestEngineProvider.getTestEngine();
		StringWriter out = new StringWriter();
		tagBlockDirectiveAdapter.doRender(engine.createContext(out), "xxx", param, innerDirectives);
		out.flush();
		super.assertEquals("okok!", out.getBuffer().toString());
	}

}
