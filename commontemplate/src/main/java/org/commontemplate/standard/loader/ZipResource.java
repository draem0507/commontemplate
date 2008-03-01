package org.commontemplate.standard.loader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.commontemplate.util.Assert;

/**
 * ZIP模板源
 * 
 * @author liangfei0201@163.com
 *
 */
public class ZipResource extends AbstractResource {

	private static final long serialVersionUID = 1L;
	
	private final File file;
	
	public ZipResource(File file, String name, String encoding) {
		super(name, encoding);
		Assert.assertNotNull(file, "找不到模板文件!");
		this.file = file;
	}

	protected InputStream getInputStream() throws IOException {
		// 注：ZipFile与File的设计是不一样的，File相当于C#的FileInfo，只持有信息，
		// 而ZipFile构造时即打开流，所以每次读取数据时，重新new新的实例，而不作为属性字段持有。
		ZipFile zipFile = new ZipFile(file);
		return zipFile.getInputStream(zipFile.getEntry(getName()));
	}

	public long getLastModified() {
		try {
			ZipFile zipFile = new ZipFile(file);
			return zipFile.getEntry(getName()).getTime();
		} catch (ZipException e) {
			return -1;
		} catch (IOException e) {
			return -1;
		}
	}

}
