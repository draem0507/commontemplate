package org.commontemplate.standard.loader;

import junit.framework.TestCase;

import org.commontemplate.core.Template;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.tools.PropertiesConfigurationLoader;

public class StrinResourceLoaderTester extends TestCase {

	private StringResourceLoader loader;

	private Engine engine = new Engine(PropertiesConfigurationLoader.loadStandardConfiguration());

	protected void setUp() throws Exception {
		super.setUp();
		loader = new StringResourceLoader();
		ConfigurationSettings config = PropertiesConfigurationLoader.loadStandardConfiguration();
		config.setSourceLoader(loader);
		engine = new Engine(config);
	}

	public void testLoad() throws Exception {
		loader.addTemplate("xxx.ctl", "$if{users[0].name == 'james'}xxx$end");
		Template template = engine.getTemplate("xxx.ctl");
		super.assertNotNull(template);
		super.assertEquals("$if{users[0].name == 'james'}xxx$end", template.getSource());
	}

}
