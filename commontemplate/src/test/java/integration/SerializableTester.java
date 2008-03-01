package integration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.util.Locale;

import junit.framework.TestCase;

import org.commontemplate.core.Context;
import org.commontemplate.core.Factory;
import org.commontemplate.core.Template;

public class SerializableTester extends TestCase {

	public void testSerializable() throws Exception {
		// 配置
		Factory factory = TestEngineProvider.getTestEngine();
		
		// 执行模板
		StringWriter output = new StringWriter();
		Context context = factory.createContext(output, Locale.getDefault());
		context.pushLocalContext("session", ModelProvider.getSessionModel());
		context.pushLocalContext(ModelProvider.getModel());
		Template t1 = factory.getTemplate("/integration/serial.ctl");
		super.assertEquals("/integration/serial.ctl", t1.getName());
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("cache/test.template")));
		oos.writeObject(t1);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("cache/test.template")));
		Template t2 = (Template)ois.readObject();
		super.assertEquals("/integration/serial.ctl", t2.getName());
		t2.render(context);
		context.clear();
		output.flush();
		super.assertEquals("liangfeiok", output.getBuffer().toString());
	}
}
