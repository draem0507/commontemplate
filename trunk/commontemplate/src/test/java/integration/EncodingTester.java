package integration;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.commontemplate.core.Context;
import org.commontemplate.core.Factory;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.RenderingException;

public class EncodingTester {

	public void testEncoding() {
		// 配置
		Factory factory = TestEngineProvider.getTestEngine();

		// 执行模板
		Writer output = null;
		try {
			output = new OutputStreamWriter(System.out);
			Context context = factory.createContext(output);
			context.pushLocalContext("session", ModelProvider.getSessionModel());
			context.pushLocalContext(ModelProvider.getModel());
			factory.getTemplate("/integration/encoding_utf8.ctl").render(context);
			factory.getTemplate("/integration/encoding_utf8.ctl", "UTF-8").render(context);
			factory.getTemplate("/integration/encoding_gbk.ctl", "GBK").render(context);
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
		new EncodingTester().testEncoding();
	}

}
