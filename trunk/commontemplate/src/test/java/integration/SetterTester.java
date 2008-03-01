package integration;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;

import org.commontemplate.core.Context;
import org.commontemplate.core.Factory;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Template;

public class SetterTester {

	public void testTemplate() {
		// 配置
		Factory factory = TestEngineProvider.getTestEngine();
		
		// 执行模板
		Writer output = null;
		try {
			output = new OutputStreamWriter(System.out);
			Context context = factory.createContext(output, Locale.getDefault());
			context.pushLocalContext("session", ModelProvider.getSessionModel());
			context.pushLocalContext(ModelProvider.getModel());
			Template template = factory.getTemplate("/integration/out.mtl");
			template.render(context);
			context.clear();
			output.flush();
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
		new SetterTester().testTemplate();
	}

}
