package org.commontemplate.standard.reload;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.config.ReloadController;
import org.commontemplate.util.Assert;

/**
 * 按时间间隔热加载
 * 
 * @author liangfei0201@163.com
 *
 */
public class IntervalReloadController implements ReloadController {

	/**
	 * 不检查模板是否修改
	 */
	public static final long NO_CHECK_MODIFICATION = 0;

	private long modificationCheckInterval;
	
	/**
	 * @param modificationCheckInterval 模板热加载延迟, 值为Configuration.NO_CHECK_MODIFICATION时不热加载, 不能小于0
	 */
	public void setModificationCheckInterval(long modificationCheckInterval) {
		Assert.assertTrue(modificationCheckInterval >= 0, "checkInterval不能小于0, 值为0表示不检查.");
		this.modificationCheckInterval = modificationCheckInterval;
	}
	
	private Map checkedTimes = new HashMap();
	
	public boolean shouldReload(String templateName) {
		if (modificationCheckInterval == NO_CHECK_MODIFICATION) 
			return false;
		Long time = (Long)checkedTimes.get(templateName);
		long before = (time == null ? 0 : time.longValue());
		long now = System.currentTimeMillis();
		checkedTimes.put(templateName, new Long(now));
		return now - before - modificationCheckInterval > 0;
	}
	
}