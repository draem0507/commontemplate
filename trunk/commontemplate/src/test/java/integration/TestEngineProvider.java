package integration;

import org.commontemplate.engine.Engine;
import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.tools.PropertiesConfigurationLoader;

public class TestEngineProvider {
	
	public static Engine getTestEngine() {
		ConfigurationSettings config = PropertiesConfigurationLoader.loadConfiguration("integration/commontemplate-test.properties");
		return new Engine(config);
	}

}
