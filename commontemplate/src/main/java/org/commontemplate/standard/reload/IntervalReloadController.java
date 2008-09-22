package org.commontemplate.standard.reload;

import java.io.Serializable;
import java.util.Map;

import org.commontemplate.config.ReloadController;
import org.commontemplate.util.Assert;
import org.commontemplate.util.CollectionUtils;

/**
 * 按时间间隔热加载
 *
 * @author liangfei0201@163.com
 *
 */
public class IntervalReloadController implements ReloadController, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 不检查模板是否修改
	 */
	public static final long NO_CHECK_MODIFICATION = 0;

	private long modificationCheckInterval;

	/**
	 * @param modificationCheckInterval 模板热加载延迟, 值为Configuration.NO_CHECK_MODIFICATION时不热加载, 不能小于0
	 */
	public void setModificationCheckInterval(long modificationCheckInterval) {
		Assert.assertTrue(modificationCheckInterval >= 0, "IntervalReloadController.modification.check.interval.less.than.zero");
		this.modificationCheckInterval = modificationCheckInterval;
	}

	private Map checkedTimes = CollectionUtils.getConcurrentMap();

	public boolean shouldReload(Object key) {
		if (modificationCheckInterval == NO_CHECK_MODIFICATION)
			return false;
		Long time = (Long)checkedTimes.get(key);
		long before = (time == null ? 0 : time.longValue());
		long now = System.currentTimeMillis();
		checkedTimes.put(key, new Long(now));
		return now - before - modificationCheckInterval > 0;
	}

}
