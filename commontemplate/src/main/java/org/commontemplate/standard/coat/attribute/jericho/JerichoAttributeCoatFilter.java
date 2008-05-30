/**
 *
 */
package org.commontemplate.standard.coat.attribute.jericho;

import java.io.*;

import org.commontemplate.standard.coat.attribute.*;

import au.id.jericho.lib.html.Source;

/**
 * Jericho属性外套实现
 *
 * @author GuiLeen
 * @since 2008-4-5 上午01:55:21
 */
public class JerichoAttributeCoatFilter extends AbstractAttributeFilter {

	protected Document getDocument(final Reader reader) throws IOException {
		final Source source = new Source(reader);
		return new JerichoDocument(source);
	}

}
