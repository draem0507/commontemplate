package org.commontemplate.config;

/**
 * 热加载时机控制器
 *
 * @see org.commontemplate.config.SourceComparator
 * @author liangfei0201@163.com
 *
 */
public interface ReloadController {

	/**
	 * 是否需要检查模板源 <p/> 引擎在每次从缓存中返回模板之前都会回调该接口函数，<br/> 如果返回true，引擎将重新读取模板源，<br/>
	 * 并调用<code>org.commontemplate.config.ResourceComparator#isModified</code>进行比较，<br/>
	 * 若模板源已更新，则重新解析模板源并刷新缓存。<br/>
	 *
	 * @param key
	 *            模板索引
	 * @return 返回true，表示应该检查文件有没有更新，返回false，
	 */
	public boolean shouldReload(Object key);

}