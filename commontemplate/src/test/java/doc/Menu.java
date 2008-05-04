package doc;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private final String key;

	private final String title;

	private final String url;

	private final List children = new ArrayList();

	public Menu(String key) {
		super();
		this.key = key;
		this.title = null;
		this.url = null;
	}

	public Menu(String key, String url) {
		super();
		this.key = key;
		this.title = null;
		this.url = url;
	}

	public Menu(String key, String title, String url) {
		super();
		this.key = key;
		this.title = title;
		this.url = url;
	}

	public Menu addChild(Menu child) {
		children.add(child);
		return this;
	}

	public String getKey() {
		return key;
	}

	public String getUrl() {
		return url;
	}

	public boolean isRemoteUrl() {
		return (url != null) && (
				url.startsWith("http://")
				|| url.startsWith("https://")
				|| ! url.endsWith(".html"));
	}

	public List getChildren() {
		return children;
	}

	public String getTitle() {
		return title;
	}

}
