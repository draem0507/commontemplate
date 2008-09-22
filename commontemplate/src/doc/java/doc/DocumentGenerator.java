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

	private Factory factory;

	public DocumentGenerator() {
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
										new Menu("menu.coat",
												"coat.html"))
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
										new Menu("menu.dependency",
												"dependency.html"))
								.addChild(
										new Menu("menu.data",
												"data.html"))
								.addChild(
										new Menu("menu.faq",
												"faq.html"))
								.addChild(
										new Menu("menu.versus",
												"versus.html"))
						)
				.addChild(
						new Menu("menu.tools")
								.addChild(
										new Menu("menu.debugger",
												"debugger.html"))
								.addChild(
										new Menu("menu.viewer",
												"viewer.html"))
								.addChild(
										new Menu("menu.editor",
												"editor.html"))
								.addChild(
										new Menu("menu.converter",
												"converter.html"))
								.addChild(
										new Menu("menu.generator",
												"generator.html"))
								.addChild(
										new Menu("menu.ant",
												"ant.html"))
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
												"changes.html"))
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
								.addChild(
										new Menu("menu.forums",
												"http://forum.commontemplate.org/index.php/index.php"))
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
												"http://forum.commontemplate.org/post.php?action=newthread&fid=2&extra=page%3D1")));
		model.put("leftMenus", leftMenu.getChildren());

		Menu topMenu = new Menu("menu.top").addChild(
				new Menu("menu.downloads", "downloads.html")).addChild(
				new Menu("menu.changes", "changes.html")).addChild(
				new Menu("menu.forums", "http://forum.commontemplate.org/index.php")).addChild(
				new Menu("menu.about.us", "about.html"));
		model.put("topMenus", topMenu.getChildren());

		return model;
	}

	public void generateAll(String[] templates) {
		for (int i = 0, n = templates.length; i < n; i ++) {
			generate("doc/template/", "doc/", templates[i], Locale.CHINA, true);
			generate("doc/template/", "doc/", templates[i], Locale.US, true);
			generate("doc/template/", "doc/build/", templates[i], Locale.CHINA, false);
			generate("doc/template/", "doc/build/", templates[i], Locale.US, false);
		}
	}

	public void generate(String sourceDir, String targetDir, String t, Locale l, boolean release) {
		// 执行模板
		Writer output = null;
		try {
			File file = new File(targetDir + l.getLanguage() + "/" + t + ".html");
			File dir = file.getParentFile();
			if (! dir.exists())
				dir.mkdirs();
			System.out.println(file.getAbsolutePath());
			output = new FileWriter(file);
			Context context = factory.createContext(output);
			context.setLocale(l);
			context.pushLocalContext(getModel());
			context.put("locale", l);
			context.put("release", Boolean.valueOf(release));
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
		DocumentGenerator generator = new DocumentGenerator();
		generator.generateAll(new String[]{"index", "template" , "expression", "coat",
				"config", "integration", "extension", "api", "dependency", "data",
				"debugger", "viewer", "editor", "converter", "generator", "ant", "faq", "versus", "architecture", "criterion", "planning", "requirement", "downloads",
				"uml", "about", "changes", "join", "demo_extends"});
	}

}
