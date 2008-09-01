package org.commontemplate.standard.property.system;

import java.util.Date;

import org.commontemplate.Version;

public class EngineBean {
	
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
