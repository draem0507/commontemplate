package org.commontemplate.tools.editor.eclipse;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

/**
 * Ä£°åÄÚÈÝÖúÊÖ
 * 
 * @author liangfei0201@163.com
 *
 */
public class TemplateContentAssistProcessor implements
		IContentAssistProcessor {

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		IDocument document = viewer.getDocument();
		StringBuilder buf = new StringBuilder();
		try {
			int start = document.getLineOffset(document.getLineOfOffset(offset));
			int end = offset;
			for (int i = start; i < end - 1; i ++) {
				char ch = document.getChar(i);
				if (ch == '\t')
					buf.append('\t');
				else
					buf.append(' ');
			}
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		String indent = buf.toString();
		Map<String, String> blockDirectives = new TreeMap<String, String>(new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				if (o1 == null && o2 == null)
					return 0;
				if (o1 == null || ! (o1 instanceof Comparable))
					return -1;
				if (o2 == null || ! (o2 instanceof Comparable))
					return 1;
				Comparable<Object> c1 = (Comparable<Object>)o1;
				Comparable<Object> c2 = (Comparable<Object>)o2;
				return c1.compareTo(c2);
			}
		});
		blockDirectives.put("if", "{boolean}");
		blockDirectives.put("for", "{var : list}");
		ICompletionProposal[] proposals = new ICompletionProposal[blockDirectives.size()];
		int i = 0;
		for (Map.Entry<String, String> entry : blockDirectives.entrySet()) {
			String key = entry.getKey();
			String value = key + entry.getValue();
			proposals[i] = new CompletionProposal(value + "\n" + indent + "\t\n" + indent + "$end\n", offset, 0, new String(
					value + "\n" + indent + "\t").length(), null, "$" + key, null, null);
			i ++;
		}
		return proposals;
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		return null;
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		return new char[]{'$'};
	}

	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	public String getErrorMessage() {
		return null;
	}

	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

}