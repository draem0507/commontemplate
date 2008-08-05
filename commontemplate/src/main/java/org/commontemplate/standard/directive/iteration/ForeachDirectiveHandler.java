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

import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Context;
import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.RenderingException;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.operator.string.NamePair;
import org.commontemplate.util.Assert;
import org.commontemplate.util.BeanUtils;

/**
 * 集合循环迭代指令.
 *
 * @author liangfei0201@163.com
 *
 */
public class ForeachDirectiveHandler extends BlockDirectiveHandler {

	private static final long serialVersionUID = 1L;

	private String statusName = "for";

	public void setStatusName(String statusName) {
		Assert.assertNotEmpty(statusName, "ForeachDirectiveHandler.status.name.required");
		this.statusName = statusName;
	}

	// 条件状态位，用于传递整个For链是否已经为迭代过
	public static final String FOR_STATUS = "for.status";

	public void doRender(Context context, BlockDirective directive) throws Exception {
		Assert.assertNotNull(directive.getExpression(), "ForeachDirectiveHandler.expression.is.null");
		doRender(context, directive.getName(),
				directive.getExpression() == null
					? null : directive.getExpression().evaluate(context),
				directive.getElements());
	}

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		boolean isSuccess = doForeach(context, param, innerElements);
		context.getParentLocalContext().setStatus(FOR_STATUS, Boolean.valueOf(isSuccess));
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
			Assert.fail("ForeachDirectiveHandler.parameter.error");
			return false;
		}
	}

	// 简单数字迭代
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

	// 常规迭代
	private boolean normalForeach(Context context, Entry entry, List elements) {
		Object key = entry.getKey();
		String itemName;
		String childrenName;
		if (key instanceof NamePair) {
			NamePair pair = (NamePair)key;
			itemName = pair.getLeftName();
			childrenName = pair.getRightName();
		} else {
			itemName = String.valueOf(key);
			childrenName = null;
		}
		Collection collection = getCollection(entry.getValue());
		if (collection == null || collection.size() == 0)
			return false;
		exeForeach(context, elements, itemName, childrenName, collection, 0);
		return true;
	}

	private void exeForeach(Context context, List elements, String itemName, String childrenName, Collection collection, int level) {
		if (collection == null || collection.size() == 0)
			return;
		ForeachStatus status = new ForeachStatus(collection.size(), level);
		context.putNullVariable(itemName);
		context.putVariable(statusName, status);
		for (Iterator items = collection.iterator(); items.hasNext();) {
			Object item = items.next();
			context.setVariable(itemName, item);
			context.setVariable(statusName, status);
			try {
				DirectiveUtils.renderAll(elements, context);
				if (childrenName != null) {
					Collection children = getChildren(item, childrenName);
					if (children != null) {
						context.pushLocalContext();
						try {
							exeForeach(context, elements, itemName, childrenName, children, level + 1);
						} finally {
							context.popLocalContext();
						}
					}
				}
				status.increment();
			} catch (ContinueException ex) {
				status.increment();
				// continue;
			} catch (BreakException ex) {
				break;
			}
		}
	}

	protected Collection getChildren(Object item, String childrenName) {
		return getCollection(BeanUtils.getProperty(item, childrenName));
	}

	// 并行迭代
	private boolean parallelForeach(Context context, Map map, List elements) {
		int max = 0;
		Map iters = new HashMap(map.size());
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry)iterator.next();
			Collection collection = getCollection(entry.getValue());
			if (collection != null && collection.size() > 0) {
				String itemName = String.valueOf(entry.getKey());
				context.putNullVariable(itemName);
				iters.put(itemName, collection.iterator());
				if (collection.size() > max) {
					max = collection.size();
				}
			}
		}
		if (max == 0)
			return false;
		ForeachStatus status = new ForeachStatus(max);
		context.putVariable(statusName, status);
		for (int i = 0; i < max; i ++) { // TODO 并行迭代未实现递归迭代
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

	/**
	 * 子类可覆写子函数供给迭代数据
	 *
	 * @param data 原始数据
	 * @return 迭代集合
	 */
	protected Collection getCollection(Object data) {
		if (data == null)
			return null;
		if (data instanceof Collection)
			return (Collection) data;
		else if (data instanceof Object[]) // TODO 未处理基本类型
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
