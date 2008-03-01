package org.commontemplate.standard.reload;

import java.io.IOException;
import java.io.Reader;

import org.commontemplate.config.ResourceComparator;
import org.commontemplate.core.Resource;

/**
 * 模板源全文比较器
 * 
 * @author liangfei0201@163.com
 *
 */
public class ResourceTextComparator implements ResourceComparator {

	public boolean isModified(Resource oldSource, Resource newSource) throws IOException {
		Reader oldReader = oldSource.getReader();
		Reader newReader = newSource.getReader();
		for(;;) {
			int oldValue = oldReader.read();
			int newValue = newReader.read();
			if (oldValue == -1 || newValue == -1)
				return oldValue == newValue;
			if (oldValue != newValue) 
				return false;
		}
	}

}