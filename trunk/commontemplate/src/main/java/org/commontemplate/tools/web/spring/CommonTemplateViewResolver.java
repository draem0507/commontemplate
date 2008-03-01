package org.commontemplate.tools.web.spring;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

/**
 * 与SpringMVC集成类.
 * <p/>
 * 使用配置如下: (spring-beans.xml)
 * <pre>
 * &lt;bean id="viewResolver" class="org.commontemplate.tools.web.spring.CommonTemplateViewResolver"&gt;
 *     &lt;property name="suffix" value=".ctl"/&gt;
 *     &lt;property name="contentType" value="text/html; charset=UTF-8"/&gt;
 * &lt;/bean&gt;
 * </pre>
 * 
 * @see org.commontemplate.tools.web.spring.CommonTemplateView
 * @author liangfei0201@163.com
 *
 */
public class CommonTemplateViewResolver extends AbstractTemplateViewResolver {

	public CommonTemplateViewResolver() {
		super();
		// 简化默认配置
		super.setViewClass(requiredViewClass());
		super.setSuffix(".ctl");
		super.setContentType("text/html; charset=UTF-8");
	}

	protected Class requiredViewClass() {
		return CommonTemplateView.class;
	}

}
