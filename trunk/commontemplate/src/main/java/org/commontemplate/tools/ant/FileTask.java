package org.commontemplate.tools.ant;

import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.util.ClasspathUtils;
import org.commontemplate.util.ExceptionUtils;

public abstract class FileTask extends MatchingTask {

    private ClasspathUtils.Delegate classpathDelegate;

    protected ClasspathUtils.Delegate getDelegate() {
        if (classpathDelegate == null) {
            classpathDelegate = ClasspathUtils.getDelegate(this);
        }
        return classpathDelegate;
    }

    public void setClasspath(Path classpath) {
        getDelegate().setClasspath(classpath);
    }

    public Path createClasspath() {
        return getDelegate().createClasspath();
    }

    public void setClasspathref(Reference r) {
        getDelegate().setClasspathref(r);
    }

	protected String srcdir;

	public void setSrcdir(String srcdir) {
		this.srcdir = srcdir;
	}

	protected String destdir;

	public void setDestdir(String destdir) {
		this.destdir = destdir;
	}

	private List filesets = new ArrayList();

	public List getFilesets() {
		return filesets;
	}

	public void addFileset(FileSet fileset) {
	    filesets.add(fileset);
	}

	public void execute() throws BuildException {
		try {
			Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
			doExecute();
		} catch (BuildException e) {
			super.getProject().demuxOutput(ExceptionUtils.getDetailMessage(e), true);
			throw e;
		} catch (Throwable e) {
			super.getProject().demuxOutput(ExceptionUtils.getDetailMessage(e), true);
			throw new BuildException(e.getMessage(), e);
		}
	}

	public abstract void doExecute() throws Exception;

}
