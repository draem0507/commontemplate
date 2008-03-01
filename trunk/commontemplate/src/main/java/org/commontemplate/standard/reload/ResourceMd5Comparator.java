package org.commontemplate.standard.reload;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.commontemplate.config.ResourceComparator;
import org.commontemplate.core.Resource;
import org.commontemplate.util.IOUtils;

/**
 * 模板源MD5比较器
 * <p/>
 * 注：这种比较方式性能较差
 * 
 * @author liangfei0201@163.com
 *
 */
public class ResourceMd5Comparator implements ResourceComparator {

	public boolean isModified(Resource oldSource, Resource newSource) throws IOException {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] oldBytes = IOUtils.readToBytes(oldSource.getReader());
			byte[] newBytes = IOUtils.readToBytes(newSource.getReader());
			if (oldBytes.length != newBytes.length)
				return false;
			return MessageDigest.isEqual(messageDigest.digest(oldBytes), messageDigest.digest(newBytes));
		} catch (NoSuchAlgorithmException e) {
			return false;
		}
	}

}