package org.commontemplate.standard.operator.array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.filter.Filter;
import org.commontemplate.util.TypeUtils;

public class ArraySubFilterOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ArraySubFilterOperatorHandler() {
		super(new Class[]{boolean[].class, char[].class, byte[].class,
				short[].class, int[].class, long[].class,
				float[].class, double[].class, Object[].class}, new Class[]{Filter.class});
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Filter filter = (Filter)rightOperand;
		int size = Array.getLength(leftOperand);
		int count = 0;
		List sub = new ArrayList();
		for (int i = 0; i < size; i ++) {
			Object item = Array.get(leftOperand, i);
			Map variables = new HashMap(4);
			variables.put(resolveItemName(filter.getName()), item);
			variables.put(indexName, new Integer(i));
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