package concurrent;

import integration.ModelProvider;

import java.io.StringWriter;
import java.util.Locale;

import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.engine.Engine;

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
				Context context = engine.createContext(out);
				context.setLocale(Locale.CHINA);
				context.pushLocalContext("session", ModelProvider.getSessionModel());
				context.pushLocalContext(ModelProvider.getModel());
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

}
