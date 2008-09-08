package org.commontemplate.standard.operator.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
import org.commontemplate.standard.operator.collection.UserInfo;
import org.commontemplate.util.Function;
import org.commontemplate.util.MapEntry;

public class NewInstanceConstructorOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		List dotHandlers = new ArrayList();
		dotHandlers.add(new ObjectFunctionOperatorHandler());
		dotHandlers.add(new ObjectPropertyOperatorHandler());
		dotHandlers.add(new StaticFunctionOperatorHandler());
		dotHandlers.add(new StaticPropertyOperatorHandler());
		BinaryOperatorHandlerChain dotHandlersChain = new BinaryOperatorHandlerChain();
		dotHandlersChain.setBinaryOperatorHandlers(dotHandlers);
		NewInstanceConstructorOperatorHandler handler = new NewInstanceConstructorOperatorHandler();
		handler.setDotBinaryOperatorHandler(dotHandlersChain);
		return handler;
	}

	// 无参构造函数
	public void testEmptyConstructor() throws Exception{
		assertEvaluation(new Function(UserInfo.class.getName(), null), new UserInfo());
	}

	// 构造函数传参
	public void testConstructor() throws Exception{
		ArrayList args = new ArrayList();
		args.add("Jason Green");
		args.add(new Integer(31));
		assertEvaluation(new Function(UserInfo.class.getName(),args), new UserInfo("Jason Green", 31));
	}

	// 单一键值对注入
	public void testEntrySetter() throws Exception{
		Entry entry = new MapEntry("userName", "Kent");
		assertEvaluation(new Function(UserInfo.class.getName(), entry), new UserInfo("Kent", 0));
	}

	// Map多键值对注入
	public void testMapSetter() throws Exception{
		Map properties = new HashMap();
		properties.put("userName", "James");
		properties.put("userAge", new Integer(23));
		assertEvaluation(new Function(UserInfo.class.getName(), properties), new UserInfo("James", 23));
	}

}
