package integration;

import java.io.IOException;

import org.commontemplate.core.Factory;
import org.commontemplate.core.ParsingException;
import org.commontemplate.standard.visit.CountVisitor;

public class CountTester {

	public void testCount() {
		// 配置
		Factory factory = TestEngineProvider.getTestEngine();
		CountVisitor countVisitor = new CountVisitor();
		try {
			factory.getTemplate("/integration/out.mtl").accept(countVisitor);
		} catch (ParsingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Block Directive Count:" + countVisitor.getBlockDirectiveCount());
		System.out.println("Line Directive Count:" + countVisitor.getLineDirectiveCount());
	}

	public static void main(String[] args) {
		new CountTester().testCount();
	}

}
