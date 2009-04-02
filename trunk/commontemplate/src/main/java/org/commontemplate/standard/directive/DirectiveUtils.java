package org.commontemplate.standard.directive;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.core.RenderingException;

public final class DirectiveUtils {
	
	private DirectiveUtils() {}
	
	public static void renderAll(List elements, Context context) throws RenderingException {
	    if (elements != null && elements.size() > 0) {
    		for (int i = 0, n = elements.size(); i < n; i ++) {
    			Element directive = (Element)elements.get(i);
    			directive.render(context);
    		}
	    }
	}

}
