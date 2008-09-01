 package org.commontemplate.tools.converter;

import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;

/**
 * 模板转换命令行入口
 *
 * @author liangfei0201@163.com
 *
 */
public class Main {

	public static void main(String[] args) {
		try {
			VelocityConvert velocityConvert = new VelocityConvert();
			Writer writer = new OutputStreamWriter(System.out);
			velocityConvert.convert("aa.vm", new StringReader("aaa#set($name=\"xxx\")bbb"), writer);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
