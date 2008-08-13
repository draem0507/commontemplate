package org.commontemplate.util;

import java.io.Serializable;
import java.util.Map;

public class MapEntry implements Map.Entry, Serializable {

	private static final long serialVersionUID = 1L;

	private Object key;
	
	private Object value;
	
	public MapEntry(Object key, Object value) {
		this.key = key;
		this.value = value;
	}

	public Object getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

	public Object setValue(Object value) {
		Object oldValue = this.value;
		this.value = value;
		return oldValue;
	}
	
	public String toString() {
		return key + ":" + value;
	}
	
	public boolean equals(Object o) {
		if (o == null) 
			return false;
		if (this == o) 
			return true;
		if (! (o instanceof MapEntry))
			return false;
		MapEntry e = (MapEntry)o;
		return ((key == null && e.key == null) || (key != null && e.key != null && key.equals(e.key))) 
				&& ((value == null && e.value == null) || (value != null && e.value != null && value.equals(e.key)));
	}
	
	public int hashCode() {
		return 37 * (key.hashCode() + (value!=null?value.hashCode():0));
	}
	
}