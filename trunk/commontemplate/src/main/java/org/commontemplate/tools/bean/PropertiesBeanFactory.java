package org.commontemplate.tools.bean;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.commontemplate.util.Assert;
import org.commontemplate.util.ClassUtils;
import org.commontemplate.util.TypeUtils;

/**
 * Properties配置Bean工厂，通过Properties配置构造并初始化Bean
 * <p/>
 * 使用properties文件作为配置，所需遵循java.util.Properties的所有规则，如：# ! = :等符号需转义等<br/>
 * <br/>
 * 基本类型：(与Java相似)<br/>
 * null, true, false为关键字<br/>
 * 以数字开头的为Number，识别后缀L,F,D,S<br/>
 * 以单引号括起的为Character<br/>
 * 以双引号括起的为String，如果非特殊串，双引号可省<br/>
 * <br/>
 * 引用：<br/>
 * 以$开头表示引用<br/>
 * <br/>
 * 类对象：<br/>
 * 以.class结尾表示相应Class类元<br/>
 * 以()结尾表示创建Instance，并以Instance的key加点号作为前缀，查找并注入其属性<br/>
 * 以.static结尾表示通过静态字段获取Instance<br/>
 * 以().static结尾表示通过静态工厂方法获取Instance，并以Instance的key加点号作为前缀，查找并注入其属性<br/>
 * <br/>
 * 集合：<br/>
 * 以<>结尾表示Set，并以<>前的名称查找Set的项<br/>
 * 以[]结尾表示List，并以[]前的名称查找List的项<br/>
 * 以{}结尾表示Map，并以{}前的名称查找Map的项<br/>
 * <br/>
 * 模板继承：<br/>
 * \@extends=parent1.properties,parent2.properties<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class PropertiesBeanFactory implements BeanFactory {

	// TODO 待处理: 当属性未定义或"xxx="时不调用setter, 当"xxx=null"时调用setter注入null

	// TODO 待加入: Set,List,Map外部文件引用, Set,List按行读取数据, Map读取*.properties的键值对.

	private final Properties properties;

	private final Map variables;

	/**
	 * 在ClassPath中查找配置
	 * @param properties 配置文件路径
	 */
	public PropertiesBeanFactory(String properties) {
		this(properties, new ClassLoaderResourceLoader());
	}

	/**
	 * 用指定的加载器加载配置
	 * @param propertiesPath 配置文件路径
	 * @param resourceLoader 配置文件加载器
	 */
	public PropertiesBeanFactory(String propertiesPath, ResourceLoader resourceLoader) {
		this(propertiesPath, resourceLoader, (Map)null);
	}

	/**
	 * 在ClassPath中查找配置
	 * @param propertiesPath 配置文件路径
	 * @param variables 变量集，类型：Map<String, Object>
	 */
	public PropertiesBeanFactory(String propertiesPath, Map variables) {
		this(propertiesPath, new ClassLoaderResourceLoader(), variables);
	}

	/**
	 * 用指定的加载器加载配置
	 * @param propertiesPath 配置文件路径
	 * @param resourceLoader 配置文件加载器
	 * @param variables 变量集，类型：Map<String, Object>
	 */
	public PropertiesBeanFactory(String propertiesPath, ResourceLoader resourceLoader, Map variables) {
		Assert.assertNotNull(propertiesPath, "PropertiesBeanFactory.config.path.required");
		Assert.assertNotNull(resourceLoader, "PropertiesBeanFactory.config.loader.required");
		this.properties = extendProperties(loadProperties(propertiesPath, resourceLoader), resourceLoader);
		this.variables = variables;
	}

	/**
	 * 用指定的加载器加载配置
	 * @param properties 配置信息
	 * @param resourceLoader 配置文件加载器
	 * @param variables 变量集，类型：Map<String, Object>
	 */
	public PropertiesBeanFactory(Properties properties, ResourceLoader resourceLoader, Map variables) {
		Assert.assertNotNull(properties, "PropertiesBeanFactory.config.properties.required");
		Assert.assertNotNull(resourceLoader, "PropertiesBeanFactory.config.loader.required");
		this.properties = extendProperties(properties, resourceLoader);
		this.variables = variables;
	}

	/**
	 * 在ClassPath中查找配置
	 * @param properties 配置文件路径
	 */
	public PropertiesBeanFactory(String properties, String defaultExtends) {
		this(properties, defaultExtends, new ClassLoaderResourceLoader());
	}

	/**
	 * 用指定的加载器加载配置
	 * @param propertiesPath 配置文件路径
	 * @param resourceLoader 配置文件加载器
	 */
	public PropertiesBeanFactory(String propertiesPath, String defaultExtends, ResourceLoader resourceLoader) {
		this(propertiesPath, defaultExtends, resourceLoader, (Map)null);
	}

	/**
	 * 在ClassPath中查找配置
	 * @param propertiesPath 配置文件路径
	 * @param variables 变量集，类型：Map<String, Object>
	 */
	public PropertiesBeanFactory(String propertiesPath, String defaultExtends, Map variables) {
		this(propertiesPath, defaultExtends, new ClassLoaderResourceLoader(), variables);
	}

	/**
	 * 用指定的加载器加载配置
	 * @param propertiesPath 配置文件路径
	 * @param resourceLoader 配置文件加载器
	 * @param variables 变量集，类型：Map<String, Object>
	 */
	public PropertiesBeanFactory(String propertiesPath, String defaultExtends, ResourceLoader resourceLoader, Map variables) {
		Assert.assertNotNull(propertiesPath, "PropertiesBeanFactory.config.path.required");
		Assert.assertNotNull(resourceLoader, "PropertiesBeanFactory.config.loader.required");
		this.properties = extendProperties(loadProperties(propertiesPath, resourceLoader), resourceLoader, defaultExtends);
		this.variables = variables;
	}

	/**
	 * 用指定的加载器加载配置
	 * @param properties 配置信息
	 * @param resourceLoader 配置文件加载器
	 * @param variables 变量集，类型：Map<String, Object>
	 */
	public PropertiesBeanFactory(Properties properties, String defaultExtends, ResourceLoader resourceLoader, Map variables) {
		Assert.assertNotNull(properties, "PropertiesBeanFactory.config.properties.required");
		Assert.assertNotNull(resourceLoader, "PropertiesBeanFactory.config.loader.required");
		this.properties = extendProperties(properties, resourceLoader, defaultExtends);
		this.variables = variables;
	}


	private static final String EXTENDS_KEY = "@extends";

	// 模板继承读取 (属于构造函数块，所以static)
	private static Properties extendProperties(Properties properties, ResourceLoader resourceLoader, String defaultExtends) {
		if (defaultExtends != null) {
			String extendsValue = properties.getProperty(EXTENDS_KEY);
			if (extendsValue == null)
				properties.setProperty(EXTENDS_KEY, defaultExtends);
		}
		return extendProperties(properties, resourceLoader);
	}

	// 模板继承读取 (属于构造函数块，所以static)
	private static Properties extendProperties(Properties properties, ResourceLoader resourceLoader) {
		String extendsValue = properties.getProperty(EXTENDS_KEY);
		if (extendsValue != null && extendsValue.trim().length() > 0) {
			String[] ps = extendsValue.split("\\,");
			for (int i = 0, n = ps.length; i < n; i ++) {
				if (ps[i] != null && ps[i].trim().length() > 0) {
					Properties superProperties = extendProperties(loadProperties(ps[i].trim(), resourceLoader), resourceLoader); // 递归查找上级配置
					superProperties.putAll(properties);
					properties = superProperties;
				}
			}
		}
		return properties;
	}

	private static Properties loadProperties(String path, ResourceLoader resourceLoader) {
		try {
			InputStream inputStream = resourceLoader.getResourceAsStream(path);
			if (inputStream == null)
				throw new BeanException("PropertiesBeanFactory.config.not.found", new Object[]{path});
			Properties props = new Properties();
			props.load(inputStream);
			return props;
		} catch (IOException e) {
			throw new BeanException(e);
		}
	}

	public Object getBean(String name) throws BeanException {
		return getProperty(name);
	}

	private void initProperty(String parent, Object bean) {
		String prefix = "";
		if (parent != null)
			prefix = parent + ".";
		Method[] methods = bean.getClass().getMethods();
		for (int i = 0, n = methods.length; i < n; i ++) {
			Method method = methods[i];
			String name = method.getName();
			if (name.length() > 3 && name.startsWith("set") && method.getParameterTypes().length == 1) {
				String property = castProperty(name);
				if (hasProperty(prefix + property)) {
					Object arg = getProperty(prefix + property);
					try {
						method.invoke(bean, new Object[]{arg});
					} catch (IllegalArgumentException e) {
						throw new BeanException("PropertiesBeanFactory.config.item.error", new Object[]{prefix + property}, e);
					} catch (IllegalAccessException e) {
						throw new BeanException("PropertiesBeanFactory.config.item.error", new Object[]{prefix + property}, e);
					} catch (InvocationTargetException e) {
						throw new BeanException("PropertiesBeanFactory.config.item.error", new Object[]{prefix + property}, e);
					}
				}
			}
		}
	}

	private boolean hasProperty(String property) {
		String value = properties.getProperty(property);
		return value != null && value.trim().length() > 0;
	}

	private String castProperty(String name) {
		String property = name.substring(3);
		return property.substring(0, 1).toLowerCase() + property.substring(1);
	}

	private Object getProperty(String property) {
		return parseProperty(property, properties.getProperty(property));
	}

	private Object parseProperty(String property, String value) {
		if (value == null || value.trim().length() == 0)
			return null;
		value = value.trim();

		if ("true".equals(value))
			return Boolean.TRUE;
		if ("false".equals(value))
			return Boolean.FALSE;
		if ("null".equals(value))
			return null;
		if (TypeUtils.isSignNumber(value))
			return TypeUtils.parseSignNumber(value);
		if (value.length() == 3 && value.charAt(0) == '\'' && value.charAt(value.length() - 1) == '\'')
			return new Character(value.charAt(1));
		if (value.length() >= 2 && value.charAt(0) == '\"' && value.charAt(value.length() - 1) == '\"')
			return value.substring(1, value.length() - 1);

		if (value.length() > 1 && value.charAt(0) == '$')
			return getVariable(value.substring(1));
		if (value.length() > 6 && value.endsWith(".class"))
			return getClassMeta(value.substring(0, value.length() - 6));
		if (value.length() > 9 && value.endsWith("().static"))
			return getObjectByStaticMethod(value.substring(0, value.length() - 9), property);
		if (value.length() > 7 && value.endsWith(".static"))
			return getObjectByStaticField(value.substring(0, value.length() - 9), property);
		if (value.length() > 2 && value.endsWith("()"))
			return getObject(value.substring(0, value.length() - 2), property);
		if (value.length() > 2 && value.endsWith("<>"))
			return getSet(value.substring(0, value.length() - 2));
		if (value.length() > 2 && value.endsWith("[]"))
			return getList(value.substring(0, value.length() - 2));
		if (value.length() > 2 && value.endsWith("{}"))
			return getMap(value.substring(0, value.length() - 2));

		return value;
	}

	private Object getVariable(String name) {
		String value = properties.getProperty(name);
		if (value != null && value.trim().length() > 0)
			return parseProperty(name, value);
		else if (variables != null)
			return variables.get(name);
		return null;
	}

	private Object getClassMeta(String className) {
		try {
			return ClassUtils.forName(className);
		} catch (ClassNotFoundException e) {
			throw new BeanException(e);
		}
	}

	private Object getObjectByStaticField(String classAndFieldName, String property) {
		try {
			int i = classAndFieldName.lastIndexOf('.');
			if (i <= 0 || i > classAndFieldName.length() - 1)
				throw new BeanException("PropertiesBeanFactory.invalid.static.name", new Object[]{classAndFieldName});

			String className = classAndFieldName.substring(0, i);
			String filedName = classAndFieldName.substring(i + 1);

			Object bean = ClassUtils.forName(className).getField(filedName).get(null);
			initProperty(property, bean);
			return bean;
		} catch (IllegalAccessException e) {
			throw new BeanException(e);
		} catch (ClassNotFoundException e) {
			throw new BeanException(e);
		} catch (IllegalArgumentException e) {
			throw new BeanException(e);
		} catch (SecurityException e) {
			throw new BeanException(e);
		} catch (NoSuchFieldException e) {
			throw new BeanException(e);
		}
	}

	private Object getObjectByStaticMethod(String classAndMethodName, String property) {
		try {
			int i = classAndMethodName.lastIndexOf('.');
			if (i <= 0 || i > classAndMethodName.length() - 1)
				throw new BeanException("PropertiesBeanFactory.invalid.static.name", new Object[]{classAndMethodName});

			String className = classAndMethodName.substring(0, i);
			String methodName = classAndMethodName.substring(i + 1);

			Object bean = ClassUtils.forName(className).getMethod(methodName, new Class[0]).invoke(null, new Object[0]);
			initProperty(property, bean);
			return bean;
		} catch (IllegalAccessException e) {
			throw new BeanException(e);
		} catch (ClassNotFoundException e) {
			throw new BeanException(e);
		} catch (IllegalArgumentException e) {
			throw new BeanException(e);
		} catch (SecurityException e) {
			throw new BeanException(e);
		} catch (InvocationTargetException e) {
			throw new BeanException(e);
		} catch (NoSuchMethodException e) {
			throw new BeanException(e);
		}
	}

	private Object getObject(String className, String property) {
		try {
			Object bean = ClassUtils.forName(className).newInstance();
			initProperty(property, bean);
			return bean;
		} catch (InstantiationException e) {
			throw new BeanException(e);
		} catch (IllegalAccessException e) {
			throw new BeanException(e);
		} catch (ClassNotFoundException e) {
			throw new BeanException(e);
		}
	}

	private Set getSet(String setName) {
		if (! TypeUtils.isNamed(setName))
			throw new BeanException("PropertiesBeanFactory.invalid.set.name", new Object[]{setName});

		Set set = new HashSet();
		String prefix = setName + "<";
		for (Iterator iterator = properties.entrySet().iterator(); iterator.hasNext();) {
    		Entry entry = (Entry)iterator.next();
    		String key = (String)entry.getKey();
    		String value = (String)entry.getValue();
    		if (key.startsWith(prefix) && key.endsWith(">"))
    			set.add(parseProperty(key, value));
    	}
		return set;
	}

	private List getList(String listName) {
		if (! TypeUtils.isNamed(listName))
			throw new BeanException("PropertiesBeanFactory.invalid.list.name", new Object[]{listName});

		String prefix = listName + "[";
		int len = prefix.length();
		SortedMap map = new TreeMap();
		for (Iterator iterator = properties.entrySet().iterator(); iterator.hasNext();) {
    		Entry entry = (Entry)iterator.next();
    		String key = (String)entry.getKey();
    		String value = (String)entry.getValue();
    		if (key.startsWith(prefix) && key.endsWith("]"))
    			map.put(new Integer(key.substring(len, key.length() - 1)), parseProperty(key, value));
    	}
		List list = new ArrayList();
		list.addAll(map.values());
		return list;
	}

	private Map getMap(String mapName) {
		if (! TypeUtils.isNamed(mapName))
			throw new BeanException("PropertiesBeanFactory.invalid.map.name", new Object[]{mapName});

		String prefix = mapName + "{";
		int len = prefix.length();
		Map map = new HashMap();
		for (Iterator iterator = properties.entrySet().iterator(); iterator.hasNext();) {
    		Entry entry = (Entry)iterator.next();
    		String key = (String)entry.getKey();
    		String value = (String)entry.getValue();
    		if (key.startsWith(prefix) && key.endsWith("}"))
    			map.put(key.substring(len, key.length() - 1), parseProperty(key, value));
    	}
		return map;
	}

}
