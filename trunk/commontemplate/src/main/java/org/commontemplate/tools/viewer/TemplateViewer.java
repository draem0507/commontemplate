package org.commontemplate.tools.viewer;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.commontemplate.util.TypeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TemplateViewer {

	private TemplateGenerator generator = new TemplateGenerator();

	public void view(String sourcePath) {
		try {
			// 前置条件
			if (sourcePath == null || sourcePath.length() == 0)
				return ;
			if (! new File(sourcePath).exists())
				return ;

			// 去除文件后缀
			String sourceName;
			int i = sourcePath.lastIndexOf('.');
			if (i > -1) {
				sourceName = sourcePath.substring(0, i);
			} else {
				sourceName = sourcePath;
			}

			// 生成并打开html文件
			String targetPath = sourceName + ".html";

			// 读取数据
			Map data = readXml(sourceName + ".xml");
			if (data == null)
				readJson(sourceName + ".json");
			if (data == null)
				readProperties(sourceName + ".properties");
			if (data == null)
				data = new HashMap();

			generator.generate(data, sourcePath, targetPath);
			Runtime.getRuntime().exec("cmd /c start "+ targetPath);
		} catch (Exception e) {
			TemplateViewerUI.showException(e);
		}
	}

	// 读取.xml文件, 组装成Map数据, 文件不存在时返回null
	private Map readXml(String dataPath) throws Exception {
		if (! new File(dataPath).exists())
			return null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new FileInputStream(new File(dataPath)));
		Element root = document.getDocumentElement();
		return (Map)buildXmlNode(root);
	}

	private Object buildXmlNode(Node node) {
		Map map = new HashMap();
		List list = new ArrayList();
		Object value = null;
		if (node.hasChildNodes()) {
			NodeList children = node.getChildNodes();
			if (children != null && children.getLength() > 0) {
				for (int i = 0; i < children.getLength(); i ++) {
					Node child = children.item(i);
					if (child.getNodeType() == Node.ELEMENT_NODE) {
						String name = child.getNodeName();
						Object next = buildXmlNode(child);
						if ("_".equals(name))
							list.add(next);
						else
							map.put(name, next);
					} else if (child.getNodeType() == Node.TEXT_NODE) {
						value = parseValue(child.getNodeValue());
					}
				}
			}
		}
		if (list.size() > 0)
			return list;
		else if (map.size() > 0)
			return map;
		else
			return value;
	}

	// 读取.json文件, 组装成Map数据, 文件不存在时返回null
	private Map readJson(String dataPath) throws Exception {
		if (! new File(dataPath).exists())
			return null;
		return new HashMap(); // TODO 未读取
	}

	// 读取.properties文件, 组装成Map数据, 文件不存在时返回null
	private Map readProperties(String dataPath) throws Exception {
		if (! new File(dataPath).exists())
			return null;
		return new HashMap(); // TODO 未读取
	}

	private Object parseValue(String str) {
		if (str == null)
			return null;
		if ("true".equals(str))
			return Boolean.TRUE;
		if ("false".equals(str))
			return Boolean.FALSE;
		if (str.length() > 1 && str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"')
			return str.substring(1, str.length() - 1);
		if (str.length() > 1 && str.charAt(0) == '\'' && str.charAt(str.length() - 1) == '\'')
			return str.substring(1, str.length() - 1);
		if (TypeUtils.isSignNumber(str))
			return TypeUtils.parseSignNumber(str);
		return str;
	}

}
