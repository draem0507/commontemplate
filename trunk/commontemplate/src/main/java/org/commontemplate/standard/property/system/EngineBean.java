package org.commontemplate.standard.property.system;

import java.io.Serializable;
import java.util.Date;

import org.commontemplate.Version;

public class EngineBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String getVersion() {
		return Version.getVersion();
	}

	public Date getReleased() {
		return Version.getReleased();
	}

	public String getVendor() {
		return Version.getVendor();
	}
}
