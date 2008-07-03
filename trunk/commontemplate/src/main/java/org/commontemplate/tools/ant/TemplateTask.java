package org.commontemplate.tools.ant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.tools.bean.FileResourceLoader;

/**
 * 模板生成ANT任务.
 * <p/>
 * 使用如:<br/>
 * <pre>
 * &lt;taskdef resource="org/commontemplate/tools/ant/commontemplate-tasks.properties" classpath="commontemplate.jar"/&gt;
 * &lt;commontemplate destdir="${bin}" config="F:/commontemplate.properties"&gt;
 *     &lt;fileset dir="${src}"&gt;
 *         &lt;include name="** / *.java" /&gt;
 *     &lt;/fileset&gt;
 * &lt;/commontemplate&gt;
 * </pre>
 *
 * @author liangfei0201@163.com
 *
 */
public class TemplateTask extends Task {

	// 源文件集
	private List filesets = new ArrayList();

	public void addFileset(FileSet fileset) {
	    filesets.add(fileset);
	}

	// commontemplate.properties配置文件
	private String config;

	public void setConfig(String config) {
		this.config = config;
	}

	// 源文件夹
	private String srcdir;

	public void setSrcdir(String srcdir) {
		this.srcdir = srcdir;
	}

	// 目标文件夹
	private String destdir;

	public void setDestdir(String destdir) {
		this.destdir = destdir;
	}

	public void execute() throws BuildException {
		ConfigurationSettings settings = PropertiesConfigurationLoader.loadConfiguration(config, new FileResourceLoader());
		Engine engine = new Engine(settings);
		for (Iterator iterator = filesets.iterator(); iterator.hasNext();) {
	        FileSet fs = (FileSet) iterator.next();
	        DirectoryScanner ds = fs.getDirectoryScanner(getProject());
	        String[] includedFiles = ds.getIncludedFiles();
	        String[] excludedFiles = ds.getExcludedFiles();
	        // TODO 未完成
	    }
	}

}
