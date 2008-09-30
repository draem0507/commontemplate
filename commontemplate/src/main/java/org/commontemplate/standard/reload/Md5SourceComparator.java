package org.commontemplate.standard.reload;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.commontemplate.config.SourceComparator;
import org.commontemplate.core.Source;
import org.commontemplate.core.Template;
import org.commontemplate.util.IOUtils;

/**
 * 模板源MD5比较器
 * <p/>
 * 注：这种比较方式性能较差
 *
 * @author liangfei0201@163.com
 *
 */
public class Md5SourceComparator implements SourceComparator, Serializable {

	private static final long serialVersionUID = 1L;

	public boolean isModified(Template oldSource, Source newSource) throws IOException {
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