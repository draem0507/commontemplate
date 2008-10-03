package concurrent;

import integration.ModelProvider;
import integration.TestEngineProvider;

import org.commontemplate.engine.Engine;

public class ConcurrentTester {

	private Engine engine;

	public ConcurrentTester() {
		this.engine = TestEngineProvider.getTestEngine();
		engine.getGlobalContext().putAll(ModelProvider.getGlobalModel());
	}

	public void testConcurrent() throws Exception {
		System.out.println("----begin----");
		for (int i = 0; i < 1000; i ++) {
			new ConcurrentThread(engine, "concurrent/1.ctl", 1000 - i * 10).start();
			new ConcurrentThread(engine, "concurrent/2.ctl", 1000 - i * 10).start();
			new ConcurrentThread(engine, "concurrent/3.ctl", 1000 - i * 10).start();
			new ConcurrentThread(engine, "concurrent/4.ctl", 1000 - i * 10).start();
		}
	}

	public static void main(String[] args) {
		try {
			final long begin = System.currentTimeMillis();
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run () {
					long time = System.currentTimeMillis() - begin;
					System.out.println("time: " + time + " ms");
				}
			});
			new ConcurrentTester().testConcurrent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
