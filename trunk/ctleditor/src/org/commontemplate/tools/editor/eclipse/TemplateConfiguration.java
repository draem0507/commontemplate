package org.commontemplate.tools.editor.eclipse;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class TemplateConfiguration extends SourceViewerConfiguration {
	private TemplateDoubleClickStrategy doubleClickStrategy;
	private TemplateTagScanner tagScanner;
	private TemplateScanner scanner;
	private ColorManager colorManager;

	public TemplateConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			TemplatePartitionScanner.XML_COMMENT,
			TemplatePartitionScanner.XML_TAG,
			TemplatePartitionScanner.CT_DIRECTIVE};
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new TemplateDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected TemplateScanner getXMLScanner() {
		if (scanner == null) {
			scanner = new TemplateScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(ITemplateColorConstants.DEFAULT))));
		}
		return scanner;
	}
	protected TemplateTagScanner getXMLTagScanner() {
		if (tagScanner == null) {
			tagScanner = new TemplateTagScanner(colorManager);
			tagScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(ITemplateColorConstants.TAG))));
		}
		return tagScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr =
			new DefaultDamagerRepairer(getXMLTagScanner());
		reconciler.setDamager(dr, TemplatePartitionScanner.XML_TAG);
		reconciler.setRepairer(dr, TemplatePartitionScanner.XML_TAG);
		
		dr = new DefaultDamagerRepairer(getXMLTagScanner());
		reconciler.setDamager(dr, TemplatePartitionScanner.CT_DIRECTIVE);
		reconciler.setRepairer(dr, TemplatePartitionScanner.CT_DIRECTIVE);

		dr = new DefaultDamagerRepairer(getXMLScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr =
			new NonRuleBasedDamagerRepairer(
				new TextAttribute(
					colorManager.getColor(ITemplateColorConstants.XML_COMMENT)));
		reconciler.setDamager(ndr, TemplatePartitionScanner.XML_COMMENT);
		reconciler.setRepairer(ndr, TemplatePartitionScanner.XML_COMMENT);

		return reconciler;
	}
	
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		// 生成一个ContentAssistant
		ContentAssistant assistant = new ContentAssistant();
		assistant.setContentAssistProcessor(new TemplateContentAssistProcessor(), IDocument.DEFAULT_CONTENT_TYPE);
		// 设置帮组内容弹出响应时间
		assistant.setAutoActivationDelay(200);
		assistant.enableAutoActivation(true);
		return assistant;
	}

}