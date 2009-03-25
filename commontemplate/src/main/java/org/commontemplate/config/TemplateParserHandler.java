package org.commontemplate.config;

import java.io.IOException;

import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Source;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateBudiler;
import org.commontemplate.core.TemplateFactory;

public interface TemplateParserHandler {

	Template doParseTemplate(Source source, Syntax syntax,
			TemplateBudiler budiler, TemplateFactory factory)
			throws ParsingException, IOException;

}
