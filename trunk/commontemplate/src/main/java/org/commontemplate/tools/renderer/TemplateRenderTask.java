package org.commontemplate.tools.renderer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.standard.data.DataProvider;
import org.commontemplate.standard.loader.FileResourceLoader;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.tools.ant.FileTask;
import org.commontemplate.tools.bean.BeanFactory;
import org.commontemplate.tools.bean.ClassLoaderResourceLoader;
import org.commontemplate.tools.bean.FileClassResourceLoader;
import org.commontemplate.tools.bean.PropertiesBeanFactory;
import org.commontemplate.util.IOUtils;

/**
 * 模板渲染ANT任务.
 * <p/>
 * 定义如:<br/>
 * <pre>
 * &lt;taskdef name="ctlrender" classname="org.commontemplate.tools.renderer.TemplateRenderTask" classpath="commontemplate.jar"/&gt;
 * </pre>
 * 调用如:<br/>
 * <pre>
 *  &lt;target name="xxx"&gt;
 *    &lt;ctlrender srcdir="F:/ctl/" destdir="F:/html/" /&gt;
 *  &lt;/target&gt;
 * </pre>
 * 属性列表:<br/>
<table border="1">
	<tr bgcolor="#CCF1D5">
		<td>属性名</td>
		<td>类型</td>
		<td>描述</td>
		<td>是否必需</td>
	</tr>
	<tr>
		<td>srcdir</td>
		<td>File</td>
		<td>模板所在目录</td>
		<td><b>必需</b></td>
	</tr>
	<tr>
		<td>inputencoding</td>
		<td>String</td>
		<td>读取模板的输入编码</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>destdir</td>
		<td>File</td>
		<td>生成结果目标目录</td>
		<td><b>必需</b></td>
	</tr>
	<tr>
		<td>outputencoding</td>
		<td>String</td>
		<td>生成结果的输出编码</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>dynamicname</td>
		<td>Boolean</td>
		<td>是否为动态模板名称，缺省为false，如：F:/ctl/${entity.name}Dao.java</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>datafile</td>
		<td>File</td>
		<td>共享数据文件</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>datadir</td>
		<td>File</td>
		<td>数据文件目录，目录中的每一个数据文件都将重新执行所有模板文件，通常需使用动态模板文件名，即：dynamicname="true"，否则生成的结果会出现覆盖。</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>dataencoding</td>
		<td>String</td>
		<td>读取数据文件的输入编码</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>datatype</td>
		<td>String</td>
		<td>数据类型, 如:xml, json, properties, yaml等，不设置将以数据文件扩展名识别</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>configfile</td>
		<td>File</td>
		<td>commontemplate.properties配置文件, 默认采用标准配置</td>
		<td>可选</td>
	</tr>
		<tr>
		<td>classpath</td>
		<td>Path</td>
		<td>类加载位置设置，直接设置</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>classpathref</td>
		<td>Reference</td>
		<td>类加载位置设置，引用类型</td>
		<td>可选</td>
	</tr>
</table>
 *
 * @author liangfei0201@163.com
 *
 */
public class TemplateRenderTask extends FileTask {

	// 输入编码
	protected String inputencoding;

	public void setInputencoding(String inputencoding) {
		this.inputencoding = inputencoding;
	}

	// 输出编码
	protected String outputencoding;

	public void setOutputencoding(String outputencoding) {
		this.outputencoding = outputencoding;
	}

	// 数据文件编码
	protected String dataencoding;

	public void setDataencoding(String dataencoding) {
		this.dataencoding = dataencoding;
	}

	// 是否为动态模板名称
	protected boolean dynamicname;

	public void setDynamicname(boolean dynamicname) {
		this.dynamicname = dynamicname;
	}

	// 配置文件路径
	protected String configfile;

	public void setConfigfile(String configfile) {
		this.configfile = configfile;
	}

	// 数据文件目录
	protected String datadir;

	public void setDatadir(String datadir) {
		this.datadir = datadir;
	}

	// 全局数据文件
	protected String datafile;

	public void setDatafile(String datafile) {
		this.datafile = datafile;
	}

	// 数据文件类型
	protected String datatype;

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	protected File datadirFile;

	protected File srcdirFile;

	protected File destdirFile;

	protected Engine engine;

	protected BeanFactory beanFactory;

	protected Map dataProviders;

	public void doExecute() throws Exception {
		// 文件夹检查
		datadirFile = getProject().resolveFile(datadir);
		if (! datadirFile.exists())
			throw new BuildException(datadir + " 文件夹不存在!");
		if (! datadirFile.isDirectory())
			throw new BuildException(datadir + " 不是目录!");
		srcdirFile = getProject().resolveFile(srcdir);
		if (! srcdirFile.exists())
			throw new BuildException(srcdir + " 文件夹不存在!");
		if (! srcdirFile.isDirectory())
			throw new BuildException(srcdir + " 不是目录!");
		destdirFile = getProject().resolveFile(destdir);
		if (! destdirFile.exists())
			destdirFile.mkdirs();
		if (! destdirFile.isDirectory())
			throw new BuildException(destdir + " 不是目录!");

		// 引擎装配
		if (configfile == null)
			beanFactory = new PropertiesBeanFactory(PropertiesConfigurationLoader.STANDARD_CONFIG_PATH, new ClassLoaderResourceLoader(PropertiesConfigurationLoader.class.getClassLoader()));
		else
			beanFactory = new PropertiesBeanFactory(configfile, PropertiesConfigurationLoader.STANDARD_CONFIG_PATH, new FileClassResourceLoader());
		dataProviders = (Map)beanFactory.getBean("dataProviders");
		ConfigurationSettings settings = PropertiesConfigurationLoader.loadConfiguration(beanFactory);
		FileResourceLoader fileResourceLoader = new FileResourceLoader();
		fileResourceLoader.setDefaultEncoding(inputencoding);
		fileResourceLoader.setRootDirectory(srcdirFile.getAbsolutePath());
		settings.setResourceLoader(fileResourceLoader);
		engine = new Engine(settings);

		// 全局数据加载
		if (datafile != null) {
			Map map = getData(getProject().resolveFile(datafile));
			engine.getGlobalContext().putAll(map);
		}

		// 迭代模板文件
		String[] templateFiles = getTemplateFiles();
		if (templateFiles != null && templateFiles.length > 0) {
			for (int i = 0, n = templateFiles.length; i < n; i ++) {
				if (datadir != null) {
					// 迭代数据文件
			        String[] dataFiles = getDataFiles();
			        if (dataFiles != null && dataFiles.length > 0) {
						for (int j = 0, m = dataFiles.length; j < m; j ++) {
							renderTemplate(templateFiles[i], dataFiles[j]);
						}
					}
				} else {
					renderTemplate(templateFiles[i], null);
				}
			}
		}
	}

	private String[] getDataFiles() {
		DirectoryScanner ds = new DirectoryScanner();
        ds.setBasedir(datadirFile);
        ds.addDefaultExcludes();
        ds.scan();
        return ds.getIncludedFiles();
	}

	private String[] getTemplateFiles() {
		DirectoryScanner ds = new DirectoryScanner();
        ds.setBasedir(srcdirFile);
        ds.addDefaultExcludes();
        ds.scan();
        return ds.getIncludedFiles();
	}

	private Map getData(File dataFile) throws Exception {
		if (dataProviders == null)
			throw new BuildException("没有找到任何数据处理器!");
		String datafilename = dataFile.getAbsolutePath();
		String type;
		if (datatype != null) {
			type = datatype;
		} else {
			int i = datafilename.lastIndexOf('.');
			if (i == -1)
				throw new BuildException("没有datatype属性，并且" + datafilename + "没有后缀可识别");
			type = datafilename.substring(i + 1);
		}
		DataProvider dataProvider = (DataProvider)dataProviders.get(type);
		if (dataProvider == null)
			throw new BuildException("不能处理的数据类型：" + type);
		File file = getProject().resolveFile(datafilename);
		if (dataencoding != null) {
			return dataProvider.getData(IOUtils.readToString(new InputStreamReader(new FileInputStream(file), dataencoding)));
		} else {
			return dataProvider.getData(file);
		}
	}

	public void renderTemplate(String tempalteName, String dataName)
			throws Exception {
		String destName;
		if (dynamicname) {
			StringWriter out = new StringWriter();
			Context context = engine.createContext(out);
			if (dataName != null) {
				Map map = getData(getProject().resolveFile(getDirPath(datadirFile) + dataName));
				context.putAll(map);
			}
			engine.parseTemplate(tempalteName).render(context);
			context.clear();
			destName = out.getBuffer().toString();
		} else {
			destName = tempalteName;
		}
		File destFile = new File(getDirPath(destdirFile) + destName);
		File parentFile = destFile.getParentFile();
		if (! parentFile.exists())
			parentFile.mkdirs();
		Writer out;
		if (outputencoding != null)
			out = new OutputStreamWriter(new FileOutputStream(destFile), outputencoding);
		else
			out = new OutputStreamWriter(new FileOutputStream(destFile));
		try {
			Template template = engine.getTemplate(tempalteName);
			Context context = engine.createContext(out);
			if (dataName != null) {
				Map map = getData(getProject().resolveFile(getDirPath(datadirFile) + dataName));
				context.putAll(map);
			}
			template.render(context);
			context.clear();
			out.flush();
		} finally {
			out.close();
		}
	}

	private String getDirPath(File file) {
		String path = file.getAbsolutePath();
		if (! path.endsWith("/") && ! path.endsWith("\\")) {
			path = path + File.separator;
		}
		return path;
	}

}
