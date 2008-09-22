package org.commontemplate.engine;

import java.io.Serializable;
import java.util.Locale;

class TemplateCacheKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String name;

	private final Locale locale;

	private final String encoding;

	TemplateCacheKey(String name, Locale locale, String encoding) {
		super();
		this.name = name;
		this.locale = locale;
		this.encoding = encoding;
	}

	public String getName() {
		return name;
	}

	public Locale getLocale() {
		return locale;
	}

	public String getEncoding() {
		return encoding;
	}

	public String toString() {
		return name + "." + locale + "." + encoding;
	}

	// hashCode() and equals() generate by eclipse

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((encoding == null) ? 0 : encoding.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TemplateCacheKey other = (TemplateCacheKey) obj;
		if (encoding == null) {
			if (other.encoding != null)
				return false;
		} else if (!encoding.equals(other.encoding))
			return false;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
