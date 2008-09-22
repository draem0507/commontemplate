package integration;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.io.StringWriter;

import junit.framework.TestCase;

import org.commontemplate.core.Context;
import org.commontemplate.core.Factory;
import org.commontemplate.core.Template;

public class TemplateTester extends TestCase {

	private Factory factory;

	public TemplateTester() {
		factory = TestEngineProvider.getTestEngine();
		factory.getGlobalContext().putAll(ModelProvider.getGlobalModel());
	}

	public void testAllTemplate() throws IOException {
		File templateDir = new File(this.getClass().getClassLoader().getResource("integration/template/").getFile());
		super.assertTrue(templateDir.isDirectory());
		File[] templateFiles = templateDir.listFiles();
		for (int i = 0, n = templateFiles.length; i < n; i ++) {
			File templateFile = templateFiles[i];
			System.out.println(templateFile.getName());
			super.assertEquals(FileContentReader.getResultContent("integration/result/" + templateFile.getName()), getTemplateContent("integration/template/" + templateFile.getName()));
		}
	}

	private String getTemplateContent(String templateFile) throws IOException {
		StringWriter output = null;
		try {
			output = new StringWriter();
			Template template = factory.getTemplate(templateFile);
			Context context = factory.createContext(output);
			context.setLocale(Locale.CHINA);
			context.pushLocalContext("session", ModelProvider.getSessionModel());
			context.pushLocalContext(ModelProvider.getModel());
			template.render(context);
			context.clear();
			output.flush();
		} finally {
			if (output != null) {
				output.close();
			}
		}
		return output.getBuffer().toString();
	}

	public static void main(String[] args) {
		try {
			new TemplateTester().testAllTemplate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
