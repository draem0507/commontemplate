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
import org.commontemplate.standard.loader.FileResource;
import org.commontemplate.tools.PropertiesConfigurationLoader;

public class TemplateGenerator {

	private final Engine engine;

	public TemplateGenerator() {
		ConfigurationSettings config = PropertiesConfigurationLoader.loadStandardConfiguration();
		this.engine = new Engine(config);
	}

	public void generate(Map data, String sourcePath, String targetPath) throws IOException {
		Writer writer = null;
		try {
			writer = new FileWriter(new File(targetPath));
			Context context = null;
			try {
				context = engine.createContext(writer);
				context.pushLocalContext(data);
				Template template = engine.parseTemplate(new FileResource(new File(sourcePath), sourcePath, "UTF-8"));
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
