package org.commontemplate.standard.coat;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.config.ConfigurationException;
import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.config.DirectiveHandlerProvider;
import org.commontemplate.config.Syntax;

import au.id.jericho.lib.html.Attribute;
import au.id.jericho.lib.html.Attributes;
import au.id.jericho.lib.html.Element;
import au.id.jericho.lib.html.OutputDocument;
import au.id.jericho.lib.html.Segment;
import au.id.jericho.lib.html.Source;

class JerichoFilter {

	private String namespace;

	private Syntax syntax;

	private DirectiveHandlerProvider directiveHandlerProvider;

	public JerichoFilter(String namespace, Syntax syntax,
			DirectiveHandlerProvider directiveHandlerProvider) {
		this.namespace = namespace;
		this.syntax = syntax;
		this.directiveHandlerProvider = directiveHandlerProvider;
	}

	// 是否为块指令判断
	private boolean isBlockDirective(String name) {
		if (directiveHandlerProvider == null)
			throw new ConfigurationException("HTML标签属性语法外套处理类的directiveHandlerProvider属性不能为空! 请配置类似如: resourceFilters[200].directiveHandlerProvider=$directiveHandlerProvider"); // TODO 未国际化
		DirectiveHandler directiveHandler = directiveHandlerProvider.getDirectiveHandler(name);
		return (directiveHandler instanceof BlockDirectiveHandler);
	}

	public String filter(Reader reader) throws IOException {
		Source source = new Source(reader);
		OutputDocument document = new OutputDocument(source);
		replaceChildren(source, document);
		return document.toString();
	}

	// 替换子元素中的指令属性
	private void replaceChildren(Segment segment, OutputDocument document) {
		// 迭代子元素，逐个查找
		List elements = segment.getChildElements();
		if (elements != null) {
			for (Iterator elementIterator = elements.iterator(); elementIterator.hasNext();) {
				Element element = (Element)elementIterator.next();
				if (element != null) {
					// ---- 标签属性处理 ----
					List blockDirectiveNames = new ArrayList();
					List blockDirectiveValues = new ArrayList();
					List blockDirectiveAttributes = new ArrayList();
					String lineDirectiveName = null;
					String lineDirectiveValue = null;
					// 迭代标签属性，查找指令属性
					Attributes attributes = element.getAttributes();
					if (attributes != null) {
						for (Iterator attributeIterator = attributes.iterator(); attributeIterator.hasNext();) {
							Attribute attribute = (Attribute)attributeIterator.next();
							if (attribute != null) {
								String name = attribute.getName();
								if (name != null && name.startsWith(namespace + ":")) { // 识别名称空间
									String directiveName = name.substring(namespace.length() + 1);
									if (directiveName.matches("^[a-z|A-Z|0-9|_|\\.]+$")) { // 符合命名
										String value = attribute.getValue();
										if (isBlockDirective(directiveName)) {
											blockDirectiveNames.add(directiveName);
											blockDirectiveValues.add(value);
											blockDirectiveAttributes.add(attribute);
										} else {
											if (lineDirectiveName != null)
												throw new RuntimeException("一个标签上只能有一个<b>行指令</b>属性! 出现两个行指令属性: " + lineDirectiveName + "和" + directiveName + ", 请检查标签:" + element.getStartTag().toString());
											lineDirectiveName = directiveName;
											lineDirectiveValue = value;
										}
									}
								}
							}
						}
					}
					// ---- 块指令处理 ----
					if (blockDirectiveNames.size() > 0) {
						StringBuffer buf = new StringBuffer();
						for (int i = 0; i < blockDirectiveNames.size(); i ++) { // 按顺序添加块指令
							String blockDirectiveName = (String)blockDirectiveNames.get(i);
							String blockDirectiveValue = (String)blockDirectiveValues.get(i);
							buf.append(syntax.getDirectiveLeader());
							buf.append(blockDirectiveName);
							buf.append(syntax.getExpressionBegin());
							buf.append(blockDirectiveValue);
							buf.append(syntax.getExpressionEnd());
						}
						document.insert(element.getBegin(), buf.toString()); // 插入块指令
					}
					// ---- 行指令处理 ----
					if (lineDirectiveName != null) { // 如果是行指令, 替换整个标签内容.
						StringBuffer buf = new StringBuffer();
						buf.append(syntax.getDirectiveLeader());
						buf.append(lineDirectiveName);
						buf.append(syntax.getExpressionBegin());
						buf.append(lineDirectiveValue);
						buf.append(syntax.getExpressionEnd());
						document.replace(element.getBegin(), element.getEnd(), buf.toString()); // 替换为行指令
					} else { // 否则表示全为块指令
						for (int i = 0; i < blockDirectiveAttributes.size(); i ++) {
							Attribute attribute = (Attribute)blockDirectiveAttributes.get(i);
							document.remove(attribute); // 移除属性
						}
						replaceChildren(element, document); // 递归处理子标签
					}
					// ---- 结束指令处理 ----
					if (blockDirectiveNames.size() > 0) {
						StringBuffer buf = new StringBuffer();
						for (int i = blockDirectiveNames.size() - 1; i >= 0; i --) { // 倒序添加结束指令
							String blockDirectiveName = (String)blockDirectiveNames.get(i);
							buf.append(syntax.getDirectiveLeader());
							buf.append(syntax.getEndDirectiveName());
							buf.append(syntax.getExpressionBegin());
							buf.append(blockDirectiveName);
							buf.append(syntax.getExpressionEnd());
						}
						document.insert(element.getEnd(), buf.toString()); // 插入结束指令
					}
					// 清理临时容器
					blockDirectiveNames.clear();
					blockDirectiveValues.clear();
					blockDirectiveAttributes.clear();
				}
			}
		}
	}

}
