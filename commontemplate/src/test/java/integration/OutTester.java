package integration;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

import org.commontemplate.core.Context;
import org.commontemplate.core.Factory;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.RenderingException;

public class OutTester {

	public void testOut() {
		// 配置
		Factory factory = TestEngineProvider.getTestEngine();
		factory.getGlobalContext().putAll(ModelProvider.getGlobalModel());
		// 执行模板
		StringWriter output = null;
		try {
			//output = new OutputStreamWriter(System.out);
			output = new StringWriter();
			Context context = factory.createContext(output, Locale.getDefault());
			context.pushLocalContext("session", ModelProvider.getSessionModel());
			context.pushLocalContext(ModelProvider.getModel());
			factory.getTemplate("/integration/out.ctl").render(context);
			context.clear();
			output.flush();
			System.out.print(output.getBuffer().toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			System.out.println("模板异常发生在:" + e.getLocation());
			e.printStackTrace();
		} catch (RenderingException e) {
			System.out.println("模板异常发生在:" + e.getLocation());
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new OutTester().testOut();
	}

}
