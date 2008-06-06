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

/**
 * XML测试数据供给器
 *
 * @author liangfei0201@163.com
 *
 */
public class XmlDataProvider extends DataProviderSupport {

	public static final String OBJECT_TAG_NAME = "object";

	public static final String ARRAY_TAG_NAME = "array";

	public static final String NAME_ATTRIBUTE_NAME = "name";

	// 读取.xml文件, 组装成Map数据, 文件不存在时返回null
	protected Map getData(File dataFile) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new FileInputStream(dataFile));
		Element root = document.getDocumentElement();
		if (! OBJECT_TAG_NAME.equals(root.getTagName()))
			throw new IllegalStateException("XML数据格式错误, 根标签必需为<object>!");
		Object data = buildElement(root);
		if (data instanceof Map)
			return (Map) data;
		return null;
	}

	// 构建元素
	private Object buildElement(Element element) {
		if (OBJECT_TAG_NAME.equals(element.getTagName())) {
			return buildObject(element);
		} else if (ARRAY_TAG_NAME.equals(element.getTagName())) {
			return buildArray(element);
		} else {
			throw new IllegalStateException("XML数据格式错误, 只支持<object>和<array>两个标签!");
		}
	}

	// 构建对象元素
	private Object buildObject(Element element) {
		Map map = new HashMap();
		Object value = null;
		if (element.hasChildNodes()) {
			NodeList children = element.getChildNodes();
			if (children != null && children.getLength() > 0) {
				for (int i = 0; i < children.getLength(); i++) {
					Node child = children.item(i);
					if (child instanceof Element) {
						Element childElement = (Element)child;
						String name = childElement.getAttribute(NAME_ATTRIBUTE_NAME);
						if (name == null || name.length() == 0)
							throw new IllegalStateException("XML数据格式错误, <object>标签的内部元素name属性不能为空!");
						map.put(name, buildElement(childElement));
					} else if (child.getNodeType() == Node.TEXT_NODE) {
						value = TypeUtils.parseValue(child.getNodeValue());
					}
				}
			}
		}
		if (map.size() > 0)
			return map;
		else
			return value;
	}

	// 构建数组元数
	private List buildArray(Element element) {
		List list = new ArrayList();
		if (element.hasChildNodes()) {
			NodeList children = element.getChildNodes();
			if (children != null && children.getLength() > 0) {
				for (int i = 0; i < children.getLength(); i++) {
					Node child = children.item(i);
					if (child instanceof Element) {
						list.add(buildElement((Element)child));
					}
				}
			}
		}
		return list;
	}

}
