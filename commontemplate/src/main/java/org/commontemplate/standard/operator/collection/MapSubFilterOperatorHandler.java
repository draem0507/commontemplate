package org.commontemplate.standard.operator.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.TypeUtils;

/**
 * Map数据过滤操作符: "=>"<br/>
 * 如: ${map[entry => entry.key == "xxx"]}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class MapSubFilterOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MapSubFilterOperatorHandler() {
		super(Map.class, Filter.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Map map = (Map)leftOperand;
		Filter filter = (Filter)rightOperand;
		int size = map.size();
		int index = 0;
		int count = 0;
		Map sub = new HashMap();
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry)iterator.next();
			Map variables = new HashMap(4);
			variables.put(resolveEntryName(filter.getName()), entry);
			variables.put(indexName, new Integer(index ++));
			variables.put(countName, new Integer(count));
			variables.put(sizeName, new Integer(size));
			if (TypeUtils.isTrue(filter.filter(variables))) {
				sub.put(entry.getKey(), entry.getValue());
				count ++;
			}
		}
		return sub;
	}

	private String entryName;

	private static final String DEFAULT_ENTRY_NAME = "item";

	public final String resolveEntryName(String name) {
		if (name != null && name.length() > 0)
			return name;
		return getEntryName();
	}

	public final String getEntryName() {
		if (entryName == null || entryName.length() == 0)
			return DEFAULT_ENTRY_NAME;
		return entryName;
	}

	public final void setEntryName(String entryName) {
		this.entryName = entryName;
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