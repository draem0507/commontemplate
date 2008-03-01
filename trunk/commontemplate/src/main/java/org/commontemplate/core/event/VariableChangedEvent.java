package org.commontemplate.core.event;

/**
 * 变量存储器中的变量值改变事件
 * 
 * @see org.commontemplate.core.VariableStorage
 * @author liangfei0201@163.com
 *
 */
public class VariableChangedEvent extends ValueChangedEvent {

	private static final long serialVersionUID = 1L;

	public VariableChangedEvent(Object source, String variableName,
			Object oldVariableValue, Object newVariableValue) {
		super(source, variableName, oldVariableValue, newVariableValue);
	}

}
