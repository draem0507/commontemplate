package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.filter.Filter;
import org.commontemplate.util.TypeUtils;

/**
 * 列表条件选择操作符<br/>
 * 如: ${users[u => u.name != "guest" && count < 5]}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class CollectionSubFilterOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public CollectionSubFilterOperatorHandler() {
		super(Collection.class, Filter.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Collection collection = (Collection)leftOperand;
		Filter filter = (Filter)rightOperand;
		int size = collection.size();
		int index = 0;
		int count = 0;
		List sub = new ArrayList();
		for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
			Object item = iterator.next();
			Map variables = new HashMap(4);
			variables.put(resolveItemName(filter.getName()), item);
			variables.put(indexName, new Integer(index ++));
			variables.put(countName, new Integer(count));
			variables.put(sizeName, new Integer(size));
			if (TypeUtils.isTrue(filter.filter(variables))) {
				sub.add(item);
				count ++;
			}
		}
		return sub;
	}

	private String itemName;

	private static final String DEFAULT_ITEM_NAME = "item";

	public final String resolveItemName(String name) {
		if (name != null && name.length() > 0)
			return name;
		return getItemName();
	}

	public final String getItemName() {
		if (itemName == null || itemName.length() == 0)
			return DEFAULT_ITEM_NAME;
		return itemName;
	}

	public final void setItemName(String itemName) {
		this.itemName = itemName;
	}

	private String indexName;

	private static final String DEFAULT_INDEX_NAME = "index";

	public final String getIndexName() {
		if (indexName == null || indexName.length() == 0)
			return DEFAULT_INDEX_NAME;
		return indexName;
	}

	public final void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	private String sizeName;

	private static final String DEFAULT_SIZE_NAME = "size";

	public final String getSizeName() {
		if (sizeName == null || sizeName.length() == 0)
			return DEFAULT_SIZE_NAME;
		return sizeName;
	}

	public final void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	private String countName;

	private static final String DEFAULT_COUNT_NAME = "count";

	public final String getCountName() {
		if (countName == null || countName.length() == 0)
			return DEFAULT_COUNT_NAME;
		return countName;
	}

	public final void setCountName(String countName) {
		this.countName = countName;
	}

}