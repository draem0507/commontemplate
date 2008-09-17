package concurrent;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.engine.Engine;

import doc.Menu;

public class ConcurrentThread extends Thread {

	private Engine engine;

	private String templateName;

	private long sleep;

	public ConcurrentThread(Engine engine, String templateName) {
		this(engine, templateName, 0);
	}

	public ConcurrentThread(Engine engine, String templateName, long sleep) {
		super();
		this.engine = engine;
		this.templateName = templateName;
		this.sleep = sleep;
	}

	public void run() {
		if (sleep > 0) {
			try {
				sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			StringWriter out = new StringWriter();
			try {
				Template template = engine.getTemplate(templateName);
				Locale locale = Locale.CHINA;
				Context context = engine.createContext(out, locale);
				context.pushLocalContext(getModel());
				context.put("locale", locale);
				context.put("release", Boolean.valueOf(true));
				context.put("pageName", templateName);
				context.put("pageUrl", templateName + ".html");
				template.render(context);
				context.clear();
				out.flush();
				System.out.println(templateName);
				if (out.getBuffer().length() < 20)
					System.err.println("ERROR:" + templateName);
			} finally {
				out.getBuffer().setLength(0);
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map getModel() {
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
										new Menu("menu.dependency",
												"dependency.html"))
								.addChild(
										new Menu("menu.data",
												"data.html"))
								.addChild(
										new Menu("menu.faq",
												"faq.html"))
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

}
