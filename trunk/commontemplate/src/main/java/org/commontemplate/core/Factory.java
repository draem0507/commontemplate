package org.commontemplate.core;

/**
 * 总工厂，合并TemplateFactory和ContextFactory 
 * <p/>
 * (线程安全, 可单例重用)
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface Factory extends TemplateLoader, ContextFactory {

}
