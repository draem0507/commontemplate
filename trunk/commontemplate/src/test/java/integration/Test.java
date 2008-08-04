package integration;

import java.io.OutputStreamWriter;
import java.io.Writer;

import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.standard.loader.StringResourceLoader;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.util.StringConvertUtils;

public class Test {

	public static void main(String[] args) {
		try {
			ConfigurationSettings config = PropertiesConfigurationLoader.loadStandardConfiguration();
			StringResourceLoader resourceLoader = new StringResourceLoader();
			resourceLoader.addTemplate("xxx", "$for{times} ${user.name} $end");
			config.setResourceLoader(resourceLoader); // 如果是字符串模板, 则重定义资源加载器
			Engine engine = new Engine(config); // 将引擎单实例重用, 放在属性中持有, 避免重复装配引擎.
			Template template = engine.getTemplate("xxx"); // 如果不需要热加载, 模板也是线程安全的.
			Writer out = new OutputStreamWriter(System.out);
			Context context = null;
			try {
				context = engine.createContext(out);
				context.put("times", 5);
				context.put("user", new User());
				template.render(context);
			} finally {
				if (context != null)
					context.clear();
				if (out != null)
					out.close();
			}
			String src = "C:\\nxx\txx\\\"xx\\hxx\\uxx\\\"xx";
			String convert = StringConvertUtils.convertLiteral(src);
			String revert = StringConvertUtils.revertLiteral(src);
			System.out.println(convert);
			System.out.println(revert);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
