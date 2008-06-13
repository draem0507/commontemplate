package org.commontemplate.tools.viewer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.tools.PropertiesConfigurationLoader;

public class TemplateGenerator {

	private final Engine engine;

	public TemplateGenerator() {
		ConfigurationSettings config = PropertiesConfigurationLoader.loadConfiguration(TemplateGenerator.class.getPackage().getName().replace('.', '/') + "/commontemplate.properties");
		this.engine = new Engine(config);
	}

	public void generate(Map data, File sourceFile, File targetFile) throws IOException {
		Writer writer = null;
		try {
			writer = new FileWriter(targetFile);
			Context context = null;
			try {
				context = engine.createContext(writer);
				context.pushLocalContext(data);
				Template template = engine.getTemplate(sourceFile.getCanonicalPath());
				template.render(context);
				writer.flush();
				context.clear();
			} finally {
				if (context != null)
					context.clear();
			}
		} finally {
			if (writer != null)
				writer.close();
		}
	}

}
