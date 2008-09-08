package org.commontemplate.standard.operator.object;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
import org.commontemplate.standard.operator.collection.UserInfo;

public class NewInstanceOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		List dotHandlers = new ArrayList();
		dotHandlers.add(new ObjectFunctionOperatorHandler());
		dotHandlers.add(new ObjectPropertyOperatorHandler());
		dotHandlers.add(new StaticFunctionOperatorHandler());
		dotHandlers.add(new StaticPropertyOperatorHandler());
		BinaryOperatorHandlerChain dotHandlersChain = new BinaryOperatorHandlerChain();
		dotHandlersChain.setBinaryOperatorHandlers(dotHandlers);
		NewInstanceOperatorHandler handler = new NewInstanceOperatorHandler();
		handler.setDotBinaryOperatorHandler(dotHandlersChain);
		return handler;
	}

	public void testNewInstance() throws Exception{
		assertEvaluation(UserInfo.class.getName(), new UserInfo());
	}

	public void testNewInstanceProperty() throws Exception{
		assertEvaluation(UserInfo.class.getName() + ".userName", new UserInfo().getUserName());
		assertEvaluation(UserInfo.class.getName() + ".userAge", new Integer(new UserInfo().getUserAge()));
	}
}
