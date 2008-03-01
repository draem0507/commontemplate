package org.commontemplate.tools.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 引擎初始化监听器.
 * <p/>
 * 使用方式是在web.xml中加入：
 * <pre>
 * &lt;listener&gt;
 *     &lt;listener-class&gt;org.commontemplate.tools.web.EngineInitializeListener&lt;/listener-class&gt;
 * &lt;/listener&gt;
 * </pre>
 *
 * @author liangfei0201@163.com
 *
 */
public class EngineInitializeListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		try {
			EngineHolder.init(event.getServletContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		try {
			EngineHolder.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
