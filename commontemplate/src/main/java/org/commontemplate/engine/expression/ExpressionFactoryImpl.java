package org.commontemplate.engine.expression;

import java.util.List;

import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionFactory;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.Variable;
import org.commontemplate.util.Assert;

final class ExpressionFactoryImpl implements ExpressionFactory {

	private final OperatorHandlerProvider operatorHandlerProvider;

	private final List evaluateInterceptors;

	ExpressionFactoryImpl(OperatorHandlerProvider operatorHandlerProvider, List evaluateInterceptors) {
		this.operatorHandlerProvider = operatorHandlerProvider;
		this.evaluateInterceptors = evaluateInterceptors;
	}

	public BinaryOperator createBinaryOperator(String operatorName,
			Expression leftOperand, Expression rightOperand) {
		BinaryOperatorImpl binaryOperator = new BinaryOperatorImpl(operatorName, null,
					operatorHandlerProvider.getBinaryOperatorPriority(operatorName),
					operatorHandlerProvider.getBinaryOperatorHandler(operatorName), evaluateInterceptors);
		Assert.assertNotNull(binaryOperator, "ExpressionFactoryImpl.no.such.binary.operator", new Object[]{operatorName});
		binaryOperator.setOperands(leftOperand, rightOperand);
		return binaryOperator;
	}

	public UnaryOperator createUnaryOperator(String operatorName,
			Expression operand) {
		UnaryOperatorImpl unaryOperator = new UnaryOperatorImpl(operatorName, null,
					operatorHandlerProvider.getUnaryOperatorPriority(operatorName),
					operatorHandlerProvider.getUnaryOperatorHandler(operatorName), evaluateInterceptors);
		Assert.assertNotNull(unaryOperator, "ExpressionFactoryImpl.no.such.unary.operator", new Object[]{operatorName});
		unaryOperator.setOperand(operand);
		return unaryOperator;
	}

	public Constant createConstant(Object constantValue) {
		return new ConstantImpl(constantValue, null, evaluateInterceptors);
	}

	public Variable createVariable(String variableName) {
		return new VariableImpl(variableName, null, evaluateInterceptors);
	}

}
