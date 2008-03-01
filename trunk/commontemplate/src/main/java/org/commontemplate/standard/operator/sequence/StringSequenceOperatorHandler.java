package org.commontemplate.standard.operator.sequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.UnhandleException;

/**
 * 自定义序列
 * @author liangfei0201@163.com
 *
 */
public class StringSequenceOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	private List sequences;

	public StringSequenceOperatorHandler() {
		super(String.class, String.class);
	}
	
	public void setSequences(Set seq) {
		this.sequences = new ArrayList(seq.size());
		for (Iterator iterator = seq.iterator(); iterator.hasNext();) {
			String s = (String)iterator.next();
			String[] ss = s.split("\\,");
			this.sequences.add(new StringSequence(ss, true, true));
		}
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		String begin = (String)leftOperand;
		String end = (String)rightOperand;
		for (int i = 0, n = sequences.size(); i < n; i ++) {
			StringSequence sequence = (StringSequence)sequences.get(i);
			if (sequence.containSequence(begin, end)) {
				return sequence.getSequence(begin, end);
			}
		}
		throw new UnhandleException("没有找到序列:" + begin + " -- " + end);
	}

}