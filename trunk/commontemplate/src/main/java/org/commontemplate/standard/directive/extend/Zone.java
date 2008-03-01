package org.commontemplate.standard.directive.extend;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.RenderingException;
import org.commontemplate.standard.directive.DirectiveUtils;

/**
 * 模板区域信息
 * 
 * @author liangfei0201@163.com
 *
 */
public class Zone implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private final List directiveList;

	public Zone(List directiveList) {
		super();
		this.directiveList = directiveList;
	}

	private Zone superZone = null;
	
	public Zone getSuperZone() {
		return superZone;
	}

	public void setSuperZone(Zone superZone) {
		if (this.superZone == null)
			this.superZone = superZone;
		else
			this.superZone.setSuperZone(superZone); // 递归向上传递SuperZone
	}

	public static final String SUPER_ZONE = "superzone";

	public void render(Context context) throws RenderingException {
		context.putObject(SUPER_ZONE, superZone);
		DirectiveUtils.renderAll(directiveList, context);
		context.removeObject(SUPER_ZONE);
	}

}
