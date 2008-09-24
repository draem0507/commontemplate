package org.commontemplate.standard.property.system;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

public class SystemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	public long getNanoTime() {
		return System.currentTimeMillis();
		// return System.nanoTime(); // JDK1.5
	}

	public Properties getProperties() {
		return System.getProperties();
	}

	public Map getEnv() {
		return System.getProperties();
		// return System.getenv(); // JDK1.5
	}

}
