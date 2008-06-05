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

public class XmlDataProvider extends DataProviderSupport {

	// 读取.xml文件, 组装成Map数据, 文件不存在时返回null
	protected Map getData(File dataFile) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new FileInputStream(dataFile));
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
						value = TypeUtils.parseValue(child.getNodeValue());
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

}
