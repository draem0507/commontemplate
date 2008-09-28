package integration;

import java.io.InputStreamReader;
import java.io.Reader;

import org.commontemplate.standard.coat.AttributeSyntaxCoatFilter;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.util.IOUtils;

public class Test {

	public static void main(String[] args) {
		try {
			Reader reader = new InputStreamReader(Test.class.getClassLoader().getResourceAsStream("integration/out.ctl"), "UTF-8");
			AttributeSyntaxCoatFilter attributeSyntaxCoatFilter = new AttributeSyntaxCoatFilter();
			attributeSyntaxCoatFilter.setDirectiveHandlerProvider(PropertiesConfigurationLoader
					.loadStandardConfiguration().getDirectiveHandlerProvider());
			Reader filted = attributeSyntaxCoatFilter.filter(reader);
			System.out.println(IOUtils.readToString(filted));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
