package integration;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

import org.commontemplate.core.Context;
import org.commontemplate.core.Factory;
import org.commontemplate.core.Template;

public class TimeTest {

	public void testTime() throws IOException {
		long beginInit = System.currentTimeMillis();
		Factory factory = TestEngineProvider.getTestEngine();
		long endInit = System.currentTimeMillis();

		long beginParse = System.currentTimeMillis();
		Template template = factory.getTemplate("integration/demo.ctl");
		long endParse = System.currentTimeMillis();

		int runs = 20; // 运行次数

		long[] beginRuns = new long[runs];
		long[] endRuns = new long[runs];
		for (int i = 0; i < runs; i ++) {
			StringWriter output = new StringWriter();

			beginRuns[i] = System.currentTimeMillis();
			Context context = factory.createContext(output);
			context.setLocale(Locale.CHINA);
			context.pushLocalContext("session", ModelProvider.getSessionModel());
			context.pushLocalContext(ModelProvider.getModel());
			template.render(context);
			context.clear();
			output.flush();
			output.close();
			endRuns[i] = System.currentTimeMillis();

			//System.out.println(output.getBuffer());
		}

		System.out.println("init time:\t" + (endInit - beginInit) + "ms");
		System.out.println("parse time:\t" + (endParse - beginParse) + "ms");
		for (int i = 0; i < runs; i ++) {
			System.out.println("run" + (i + 1) + " time:\t" + (endRuns[i] - beginRuns[i]) + "ms");
		}
	}

	public static void main(String[] args) {
		try {
			new TimeTest().testTime();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
