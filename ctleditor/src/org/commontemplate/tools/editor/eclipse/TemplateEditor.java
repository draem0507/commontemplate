package org.commontemplate.tools.editor.eclipse;

import org.eclipse.ui.editors.text.TextEditor;

public class TemplateEditor extends TextEditor {

	private ColorManager colorManager;

	public TemplateEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new TemplateConfiguration(colorManager));
		setDocumentProvider(new TemplateDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
