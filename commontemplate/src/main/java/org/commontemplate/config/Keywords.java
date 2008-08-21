package org.commontemplate.config;

import org.commontemplate.util.Assert;

/**
 * 表达式关键字 (不变类，线程安全)
 *
 * 注：关键字不可作为变量名
 *
 * @author liangfei0201@163.com
 *
 */
public final class Keywords implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// 特定值关键字

	private final String nullKeyword;

	private final String trueKeyword;

	private final String falseKeyword;

	// 上下文区域信息关键字

	private final String currentLocalContextKeyword;

	private final String parentLocalContextKeyword;

	private final String contextKeyword;

	/**
	 * 关键字定义
	 *
	 * @param nullKeyword
	 *            null关键字
	 * @param trueKeyword
	 *            true关键字
	 * @param falseKeyword
	 *            false关键字
	 * @param currentLocalContextKeyword
	 *            this关键字
	 * @param parentLocalContextKeyword
	 *            super关键字
	 * @param contextKeyword
	 *            context关键字
	 */
	public Keywords(String nullKeyword, String trueKeyword,
			String falseKeyword, String currentLocalContextKeyword,
			String parentLocalContextKeyword, String contextKeyword) {
		super();
		Assert.assertNotEmpty(nullKeyword, "NullKeyword不能为空!"); // TODO 未国际化
		Assert.assertNotEmpty(trueKeyword, "TrueKeyword不能为空!"); // TODO 未国际化
		Assert.assertNotEmpty(falseKeyword, "FalseKeyword不能为空!"); // TODO 未国际化
		Assert.assertNotEmpty(currentLocalContextKeyword,
				"CurrentLocalContextKeyword不能为空!"); // TODO 未国际化
		Assert.assertNotEmpty(parentLocalContextKeyword,
				"ParentLocalContextKeyword不能为空!"); // TODO 未国际化
		Assert.assertNotEmpty(contextKeyword, "ContextKeyword不能为空!"); // TODO 未国际化
		assertMutex(new String[] { nullKeyword, trueKeyword, falseKeyword,
				parentLocalContextKeyword, contextKeyword });
		this.nullKeyword = nullKeyword;
		this.trueKeyword = trueKeyword;
		this.falseKeyword = falseKeyword;
		this.currentLocalContextKeyword = currentLocalContextKeyword;
		this.parentLocalContextKeyword = parentLocalContextKeyword;
		this.contextKeyword = contextKeyword;
	}

	private final void assertMutex(String[] strs) {
		int n = strs.length;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (strs[i].equals(strs[j]))
					throw new IllegalStateException("表达式关键字重复：" + strs[i]); // TODO 未国际化
			}
		}
	}

	public final String getNullKeyword() {
		return nullKeyword;
	}

	public final String getTrueKeyword() {
		return trueKeyword;
	}

	public final String getFalseKeyword() {
		return falseKeyword;
	}

	public final String getCurrentLocalContextKeyword() {
		return currentLocalContextKeyword;
	}

	public final String getParentLocalContextKeyword() {
		return parentLocalContextKeyword;
	}

	public final String getContextKeyword() {
		return contextKeyword;
	}

	public final boolean isKeyword(String name) {
		if (name == null)
			return false;
		return nullKeyword.equals(name) || trueKeyword.equals(name)
				|| falseKeyword.equals(name)
				|| parentLocalContextKeyword.equals(name)
				|| contextKeyword.equals(name);
	}

	public static final String DEFAULT_NULL_KEYWORD = "null";

	public static final String DEFAULT_TRUE_KEYWORD = "true";

	public static final String DEFAULT_FALSE_KEYWORD = "false";

	public static final String DEFAULT_CURRENT_LOCAL_CONTEXT_KEYWORD = "this";

	public static final String DEFAULT_PARENT_LOCAL_CONTEXT_KEYWORD = "super";

	public static final String DEFAULT_CONTEXT_KEYWORD = "context";

	public static final Keywords DEFAULT = new Keywords(DEFAULT_NULL_KEYWORD,
			DEFAULT_TRUE_KEYWORD, DEFAULT_FALSE_KEYWORD,
			DEFAULT_CURRENT_LOCAL_CONTEXT_KEYWORD,
			DEFAULT_PARENT_LOCAL_CONTEXT_KEYWORD, DEFAULT_CONTEXT_KEYWORD);

}
