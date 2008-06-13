package doc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.core.Factory;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Template;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.tools.PropertiesConfigurationLoader;

public class DocumentGenerator {

	private String sourceDir;

	private String targetDir;

	private Factory factory;

	public DocumentGenerator(String sourceDir, String targetDir) {
		this.sourceDir = sourceDir;
		this.targetDir = targetDir;
		ConfigurationSettings config = PropertiesConfigurationLoader
				.loadConfiguration("doc/commontemplate.properties");
		this.factory = new Engine(config);
	}

	public Map getModel() {
		Map model = new HashMap();

		Menu leftMenu = new Menu("menu.left")
				.addChild(
						new Menu("menu.documents")
								.addChild(
										new Menu("menu.overview", "index.html"))
								.addChild(
										new Menu("menu.template",
												"template.html"))
								.addChild(
										new Menu("menu.expression",
												"expression.html"))
								.addChild(
										new Menu("menu.configuration",
												"config.html"))
								.addChild(
										new Menu("menu.integration",
												"integration.html"))
								.addChild(
										new Menu("menu.extension",
												"extension.html"))
								.addChild(
										new Menu("menu.api",
												"api.html"))
								.addChild(
										new Menu("menu.debug",
												"debug.html"))
								.addChild(
										new Menu("menu.viewer",
												"viewer.html"))
								.addChild(
										new Menu("menu.editor",
												"editor.html"))
								.addChild(
										new Menu("menu.faq",
												"faq.html"))
						)
				.addChild(
						new Menu("menu.development")
								.addChild(
										new Menu("menu.architecture",
												"architecture.html"))
								.addChild(
										new Menu("menu.criterion",
												"criterion.html"))
								.addChild(
										new Menu("menu.planning",
												"planning.html"))
								.addChild(
										new Menu("menu.requirement",
												"requirement.html"))
						)
				.addChild(
						new Menu("menu.resources")
								.addChild(
										new Menu("menu.downloads", "downloads.html"))
								.addChild(
										new Menu("menu.license",
												"../resources/license.txt"))
								.addChild(
										new Menu("menu.changes.log",
												"news.html"))
								.addChild(
										new Menu("menu.dependency",
												"dependency.html"))
								.addChild(
										new Menu("menu.uml", "uml.html"))
								.addChild(
										new Menu("menu.javadoc", "../javadoc"))
								.addChild(
										new Menu("menu.coverage.report",
												"../coverage")))
				.addChild(
						new Menu("menu.community")
								.addChild(
										new Menu("menu.developer.team",
												"about.html"))
								.addChild(new Menu("menu.news", "news.html"))
								.addChild(
										new Menu("menu.forums",
												"http://forum.commontemplate.org"))
								.addChild(
										new Menu("menu.wiki",
												"http://code.google.com/p/commontemplate/w/list"))
								.addChild(
										new Menu("menu.mail.list",
												"http://sourceforge.net/mail/?group_id=193256"))
								.addChild(
										new Menu("menu.bugs.list",
												"http://code.google.com/p/commontemplate/issues/list"))
								.addChild(
										new Menu("menu.bugs.report",
												"http://code.google.com/p/commontemplate/issues/entry")));
		model.put("leftMenus", leftMenu.getChildren());

		Menu topMenu = new Menu("menu.top").addChild(
				new Menu("menu.downloads", "downloads.html")).addChild(
				new Menu("menu.news", "news.html")).addChild(
				new Menu("menu.forums", "http://forum.commontemplate.org")).addChild(
				new Menu("menu.about.us", "about.html"));
		model.put("topMenus", topMenu.getChildren());

		return model;
	}

	public void generateAll(String[] templates) {
		for (int i = 0, n = templates.length; i < n; i ++) {
			generate(templates[i], Locale.CHINA);
			generate(templates[i], Locale.US);
		}
	}

	public void generate(String t, Locale l) {
		// 执行模板
		Writer output = null;
		try {
			File file = new File(targetDir + l.getLanguage() + "/" + t + ".html");
			File dir = file.getParentFile();
			if (! dir.exists())
				dir.mkdirs();
			System.out.println(file.getAbsolutePath());
			output = new FileWriter(file);
			Context context = factory
					.createContext(output, l);
			context.pushLocalContext(getModel());
			context.put("locale", l);
			context.put("pageName", t);
			context.put("pageUrl", t + ".html");
			Template template;
			try {
				template = factory.getTemplate(sourceDir + t + ".ctl");
			} catch (IOException e) {
				template = factory.getTemplate(sourceDir + l.getLanguage() + "/" + t + ".ctl");
			}
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
		DocumentGenerator generator = new DocumentGenerator("doc/template/", "doc/");
		generator.generateAll(new String[]{"index", "template", "expression",
				"config", "integration", "extension", "api",
				"debug", "viewer", "editor", "faq", "architecture", "criterion", "planning", "requirement", "downloads", "dependency",
				"uml", "about", "news", "join", "demo_extends"});
	}

}
