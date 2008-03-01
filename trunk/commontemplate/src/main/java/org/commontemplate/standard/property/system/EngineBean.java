package org.commontemplate.standard.property.system;

import java.util.Date;

import org.commontemplate.Version;

public class EngineBean {
	
	public String getVersionNumber() {
		return Version.getVersionNumber();
	}
	
	public Date getReleaseDate() {
		return Version.getReleaseDate();
	}
	
	public String getVendor() {
		return Version.getVendor();
	}
}
