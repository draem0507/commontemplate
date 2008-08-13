package org.commontemplate.standard.directive.data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.commontemplate.util.DomUtils;
import org.commontemplate.util.TypeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * XML格式数据供给器, 如: <br/>
 * <pre>
 * &lt;object&gt;
 *     &lt;object name="mail"&gt;
 *         &lt;object name="from"&gt;xxx@xxx.com&lt;/object&gt;
 *         &lt;object name="to"&gt;yyy@yyy.com&lt;/object&gt;
 *     &lt;/object&gt;
 *     &lt;array name="users"&gt;
 *         &lt;object&gt;
 *             &lt;object name="id"&gt;1&lt;/object&gt;
 *             &lt;object name="name"&gt;james&lt;/object&gt;
 *         &lt;/object&gt;
 *         &lt;object&gt;
 *             &lt;object name="id"&gt;2&lt;/object&gt;
 *             &lt;object name="name"&gt;kent&lt;/object&gt;
 *         &lt;/object&gt;
 *     &lt;/array&gt;
 * &lt;/object&gt;
 * </pre>
 *
 * @author liangfei0201@163.com
 *
 */
public class XmlDataProvider extends InputStreamDataProvider {

	public static final String OBJECT_TAG_NAME = "object";

	public static final String ARRAY_TAG_NAME = "array";

	public static final String NAME_ATTRIBUTE_NAME = "name";

	// 读取.xml文件, 组装成Map数据, 文件不存在时返回null
	public Map getData(InputStream dataInputStream) throws Exception {
		Document document = DomUtils.getDocument(dataInputStream);
		Element root = document.getDocumentElement();
		if (! OBJECT_TAG_NAME.equals(root.getTagName()))
			throw new IllegalStateException("XML数据格式错误, 根标签必需为<object>!"); // TODO 未国际化
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
			throw new IllegalStateException("XML数据格式错误, 只支持<object>和<array>两个标签!"); // TODO 未国际化
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
							throw new IllegalStateException("XML数据格式错误, <object>标签的内部元素name属性不能为空!"); // TODO 未国际化
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
