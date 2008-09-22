package concurrent;

import junit.framework.TestCase;

import org.commontemplate.engine.Engine;
import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.tools.PropertiesConfigurationLoader;

public class ConcurrentTester extends TestCase {

	private Engine engine;

	public ConcurrentTester() {
		ConfigurationSettings config = PropertiesConfigurationLoader
				.loadConfiguration("doc/commontemplate.properties");
		this.engine = new Engine(config);
	}

	public void testConcurrent() throws Exception {
		System.out.println("----begin----");
		for (int i = 0; i < 100; i ++) {
			new ConcurrentThread(engine, "concurrent/1.ctl", 500 - i * 10).start();
			new ConcurrentThread(engine, "concurrent/2.ctl", 500 - i * 10).start();
			new ConcurrentThread(engine, "concurrent/3.ctl", 500 - i * 10).start();
			new ConcurrentThread(engine, "concurrent/4.ctl", 500 - i * 10).start();
		}
	}

	public static void main(String[] args) {
		try {
			new ConcurrentTester().testConcurrent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
