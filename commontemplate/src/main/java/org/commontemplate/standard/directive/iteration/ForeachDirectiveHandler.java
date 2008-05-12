package org.commontemplate.standard.directive.iteration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.core.Context;
import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.RenderingException;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.util.Assert;

/**
 * 集合循环迭代指令.
 *
 * @author liangfei0201@163.com
 *
 */
public class ForeachDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private String statusName = "for";

	public void setStatusName(String statusName) {
		Assert.assertNotEmpty(statusName, "状态名称不能为空！");
		this.statusName = statusName;
	}

	// 条件状态位，用于传递整个For链是否已经为迭代过
	public static final String FOR_STATUS = "for.status";

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		boolean isSuccess = doForeach(context, param, innerElements);
		context.getSuperLocalContext().setStatus(FOR_STATUS, Boolean.valueOf(isSuccess));
	}

	private boolean doForeach(Context context, Object param, List elements) throws RenderingException, EvaluationException, IOException {
		if (param == null) {
			return false;
		} else if (param instanceof Entry) {
			return normalForeach(context, (Entry)param, elements);
		} else if (param instanceof Map) {
			return parallelForeach(context, (Map)param, elements);
		} else if (param instanceof Integer) {
			return simpleForeach(context, ((Integer)param).intValue(), elements);
		} else {
			throw new RuntimeException("for指令参数错误!");
		}
	}

	private boolean simpleForeach(Context context, int count, List elements) {
		if (count <= 0) // count小于0, 不进行迭代.
			return false;
		ForeachStatus status = new ForeachStatus(count);
		context.putVariable(statusName, status);
		for (int i = 0; i < count; i ++) {
			context.setVariable(statusName, status);
			try {
				DirectiveUtils.renderAll(elements, context);
				status.increment();
			} catch (ContinueException ex) {
				status.increment();
				// continue;
			} catch (BreakException ex) {
				break;
			}
		}
		return true;
	}

	private boolean normalForeach(Context context, Entry entry, List elements) {
		String itemName = String.valueOf(entry.getKey());
		Collection collection = getCollection(entry.getValue());
		if (collection == null || collection.size() == 0)
			return false;
		ForeachStatus status = new ForeachStatus(collection.size());
		context.putNullVariable(itemName);
		context.putVariable(statusName, status);
		for (Iterator items = collection.iterator(); items.hasNext();) {
			Object item = items.next();
			context.setVariable(itemName, item);
			context.setVariable(statusName, status);
			try {
				DirectiveUtils.renderAll(elements, context);
				status.increment();
			} catch (ContinueException ex) {
				status.increment();
				// continue;
			} catch (BreakException ex) {
				break;
			}
		}
		return true;
	}

	private boolean parallelForeach(Context context, Map map, List elements) {
		int max = 0;
		Map iters = new HashMap(map.size());
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry)iterator.next();
			Collection coll = getCollection(entry.getValue());
			String itemName = String.valueOf(entry.getKey());
			context.putNullVariable(itemName);
			iters.put(itemName, coll.iterator());
			if (coll.size() > max) {
				max = coll.size();
			}
		}
		if (max == 0)
			return false;

		ForeachStatus status = new ForeachStatus(max);
		context.putVariable(statusName, status);
		for (int i = 0; i < max; i ++) {
			context.setVariable(statusName, status);

			for (Iterator iterator = iters.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				String itemName = (String)entry.getKey();
				Iterator coll = (Iterator)entry.getValue();
				if (coll.hasNext()) {
					context.setVariable(itemName, coll.next());
				} else {
					context.setVariable(itemName, null);
				}
			}

			try {
				DirectiveUtils.renderAll(elements, context);
				status.increment();
			} catch (ContinueException ex) {
				status.increment();
				// continue;
			} catch (BreakException ex) {
				break;
			}
		}
		return true;

	}

	private Collection getCollection(Object data) {
		if (data == null)
			return null;
		if (data instanceof Collection)
			return (Collection) data;
		else if (data instanceof Object[])
			return Arrays.asList((Object[]) data);
		else if (data instanceof Map)
			return ((Map) data).entrySet();
		else if (data instanceof Iterator) {
			List list = new LinkedList();
			for (Iterator i = (Iterator)data; i.hasNext();)
				list.add(i.next());
			return list;
		} else if (data instanceof Enumeration) {
			List list = new LinkedList();
			for (Enumeration e = (Enumeration)data; e.hasMoreElements();)
				list.add(e.nextElement());
			return list;
		} else {
			Collection collection = new HashSet(1);
			collection.add(data);
			return collection;
		}
	}

}
