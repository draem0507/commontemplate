package org.commontemplate.standard.collection;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ResultSetCollectionConverter implements CollectionConverter, Serializable {

	private static final long serialVersionUID = 1L;

	public Collection convert(Object data) {
		List list = new LinkedList();
		ResultSet resultSet = (ResultSet)data;
		try {
			ResultSetMetaData metaData = resultSet.getMetaData();
			while (resultSet.next()) {
				Map map = new HashMap();
				for (int i = 1, n = metaData.getColumnCount(); i <= n; i ++) {
					String key = metaData.getColumnName(i);
					Object value = resultSet.getObject(i);
					map.put(key, value);
				}
				list.add(map);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
