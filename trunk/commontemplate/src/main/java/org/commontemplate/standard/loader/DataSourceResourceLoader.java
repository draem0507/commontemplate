package org.commontemplate.standard.loader;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.commontemplate.core.Resource;

/**
 * 数据库模板加载器
 * 
 * @author liangfei0201@163.com
 *
 */
public class DataSourceResourceLoader extends AbstractResourceLoader {
	
	private DataSource dataSource;

    private String templateTable = "common_template";

    private String nameColumn = "name";

    private String bodyColumn = "body";

    private String lastModifiedColumn = "last_modified";

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setTemplateTable(String templateTable) {
		this.templateTable = templateTable;
	}

	public void setNameColumn(String nameColumn) {
		this.nameColumn = nameColumn;
	}

	public void setBodyColumn(String bodyColumn) {
		this.bodyColumn = bodyColumn;
	}

	public void setLastModifiedColumn(String lastModifiedColumn) {
		this.lastModifiedColumn = lastModifiedColumn;
	}

	protected Resource loadResource(String path, String name, String encoding) throws IOException {
		try {
			Resource t = query(path, name, encoding);
			if (t != null)
				return t;
			throw new IOException("template \"" + name + "\" not found!");
		} catch (SQLException e) {
			throw new IOException(e.getMessage());
		}
	}
	
	private Resource query(String path, String name, String encoding) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT " + bodyColumn + "," + lastModifiedColumn + " FROM "+ templateTable + " WHERE " + nameColumn + " = ?");
			ps.setString(1, path);
			rs = ps.executeQuery();
			if (rs.next()) {
				 Timestamp ts = rs.getTimestamp(lastModifiedColumn);
                 long timeStamp = ts != null ? ts.getTime() : 0;
                 Reader reader =  rs.getCharacterStream(bodyColumn);
                 return new DataSourceResource(reader, timeStamp, name, encoding);
			}
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} finally {
				try {
					if (ps != null)
						ps.close();
				} finally {
					if (conn != null)
						conn.close();
				}
			}
		}
	}

}
