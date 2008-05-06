/**
 *
 */
package org.commontemplate.ext.coat.attribute.jericho;

import java.io.*;

import org.commontemplate.ext.coat.attribute.*;

import au.id.jericho.lib.html.Source;

/**
 * @author GL
 * @since 2008-4-5 上午01:55:21
 */
public class JerichoAttributeCoatFilter extends AbstractAttributeFilter {

	protected Document getDocument(final Reader reader) throws IOException {
		final Source source = new Source(reader);
		return new JerichoDocument(source);
	}

}