package integration;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.commontemplate.core.Context;
import org.commontemplate.core.Factory;
import org.commontemplate.core.GlobalContext;
import org.commontemplate.core.Template;

public class DemoTester {

	public void testOut() throws IOException {
		// 配置
		Factory factory = TestEngineProvider.getTestEngine();

		GlobalContext globalContext = factory.getGlobalContext();
		globalContext.putVariable("title", "demo test");
		globalContext.putAll(ModelProvider.getGlobalModel());

		// 执行模板
		Writer output = null;
		try {
			output = new OutputStreamWriter(System.out);
			Context context = factory.createContext(output);
			context.pushLocalContext("session", ModelProvider.getSessionModel());
			context.pushLocalContext(ModelProvider.getModel());

			Template template = factory.getTemplate("/integration/demo.ctl");
			template.render(context);

			context.clear();
			output.flush();
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}

	public static void main(String[] args) {
		try {
			new DemoTester().testOut();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
