<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
<b>1. 日志使用说明</b><br/>
<b>(1) 用于引擎内部实现</b><br/>
如输出引擎运行过程的状态和错误信息等，可以通过日志框架开启或关闭引擎内部输出的日志信息。<br/>
引擎内部均采用org.commontemplate.util.log.LoggerFactory.getLogger()方式调用日志接口。<br/>
LoggerFactory通过在ClassPath搜索常用日志框架的类文件是否存在，进行自动适应。<br/>
默认的查找序为：CommonsLogging, Log4J, Avalon, SLF4J, JdkLogging，否则使用SimpleLogger。<br/>
也可以通过调用LoggerFactory.setLoggerProvider()进行设置。<br/>
<b>(2) 用于模板中的\$log指令</b><br/>
通过配置commontemplate.properties中的loggerProvider项实现切换。<br/>
以下是内置的可切换项，也可以扩展实现。<a href="extension.html#logger">日志扩展...</a><br/>
<br/>
<b>2. 内部实现</b><br/>
<b>(1)NoneLogger</b><br/>
空的实现，当不需要日志输出时使用。br/>
配置commontemplate.properties:<br/>
$code{properties}
loggerProvider=org.commontemplate.util.log.NoneLoggerProvider()
$end<br/>
<b>(2)SimpleLogger</b><br/>
简单的控制台输出实现。<br/>
配置commontemplate.properties:<br/>
$code{properties}
loggerProvider=org.commontemplate.util.log.SimpleLoggerProvider()
$end<br/>
<br/>
<b>2. 外部集成</b><br/>
<b>(1)与CommonsLogging集成</b><br/>
项目地址：<a href="http://jakarta.apache.org/commons/logging" target="_blank">http://jakarta.apache.org/commons/logging</a><br/>
配置commontemplate.properties:<br/>
$code{properties}
loggerProvider=org.commontemplate.util.log.CommonsLoggerProvider()
$end<br/>
配置commons-logging.properties:<br/>
$code{properties}
logger=org.apache.commons.logging.impl.Log4JLogger
$end<br/>
<b>(2)与Log4J集成</b><br/>
项目地址：<a href="http://log4j.apache.org" target="_blank">http://log4j.apache.org</a><br/>
配置commontemplate.properties:<br/>
$code{properties}
loggerProvider=org.commontemplate.util.log.Log4jLoggerProvider()
$end<br/>
配置log4j.properties:<br/>
$code{properties}
log4j.rootLogger=DEBUG,stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%-5p [%d] %C - %m\n
log4j.logger.CommonTemplate=DEBUG
$end<br/>
<b>(3)与Avalon集成</b><br/>
项目地址：<a href="http://excalibur.apache.org" target="_blank">http://excalibur.apache.org</a><br/>
配置commontemplate.properties:<br/>
$code{properties}
loggerProvider=org.commontemplate.util.log.AvalonLoggerProvider()
$end<br/>
<b>(4)与SLF4J集成</b><br/>
项目地址：<a href="http://www.slf4j.org" target="_blank">http://www.slf4j.org</a><br/>
配置commontemplate.properties:<br/>
$code{properties}
loggerProvider=org.commontemplate.util.log.Slf4jLoggerProvider()
$end<br/>
<b>(5)与JdkLogging集成</b><br/>
项目地址：<a href="http://java.sun.com/javase/4/docs/api/java/util/logging/package-summary.html" target="_blank">http://java.sun.com/javase/4/docs/api/java/util/logging/package-summary.html</a><br/>
配置commontemplate.properties:<br/>
$code{properties}
loggerProvider=org.commontemplate.util.log.JdkLoggerProvider()
$end<br/>
	<!--$end-->
<!--$end-->