package org.commontemplate.tools.converter;

import java.io.Reader;
import java.io.Writer;

import org.apache.velocity.runtime.RuntimeInstance;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.parser.Parser;
import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.tools.PropertiesConfigurationLoader;

public class VelocityConvert {

	public void convert(String templateName, Reader reader, Writer writer) throws Exception {
		RuntimeServices runtime = new RuntimeInstance();
		Parser parser = runtime.createNewParser();
		SimpleNode node = parser.parse(reader, templateName);
		ConfigurationSettings config = PropertiesConfigurationLoader.loadStandardConfiguration();
		ParserVisitor visitor = new VelocityParserVisitor(new Engine(config));
		node.jjtAccept(visitor, writer);
	}

}
