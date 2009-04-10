package org.commontemplate.standard.operator.map;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 字面Map
 * @author liangfei0201@163.com
 */
public class LiteralMap implements Map, Serializable {

    private static final long serialVersionUID = -4046409124983128683L;
    
    private final Map map;

    public LiteralMap() {
        map = new HashMap();
    }

    public LiteralMap(int initialCapacity, float loadFactor) {
        map = new HashMap(initialCapacity, loadFactor);
    }
    
    public LiteralMap(int initialCapacity) {
        map = new HashMap(initialCapacity);
    }

    public LiteralMap(Map m) {
        map = new HashMap(m);
    }

    /**
     * 获取原始Map
     * @return 原始Map
     */
    public Map getMap() {
        return map;
    }

    /**
     * 
     * @see java.util.Map#clear()
     */
    public void clear() {
        map.clear();
    }

    /**
     * @param key
     * @return
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    /**
     * @param value
     * @return
     * @see java.util.Map#containsValue(java.lang.Object)
     */
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    /**
     * @return
     * @see java.util.Map#entrySet()
     */
    public Set entrySet() {
        return map.entrySet();
    }

    /**
     * @param o
     * @return
     * @see java.util.Map#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        return map.equals(o);
    }

    /**
     * @param key
     * @return
     * @see java.util.Map#get(java.lang.Object)
     */
    public Object get(Object key) {
        return map.get(key);
    }

    /**
     * @return
     * @see java.util.Map#hashCode()
     */
    public int hashCode() {
        return map.hashCode();
    }

    /**
     * @return
     * @see java.util.Map#isEmpty()
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * @return
     * @see java.util.Map#keySet()
     */
    public Set keySet() {
        return map.keySet();
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public Object put(Object key, Object value) {
        return map.put(key, value);
    }

    /**
     * @param t
     * @see java.util.Map#putAll(java.util.Map)
     */
    public void putAll(Map t) {
        map.putAll(t);
    }

    /**
     * @param key
     * @return
     * @see java.util.Map#remove(java.lang.Object)
     */
    public Object remove(Object key) {
        return map.remove(key);
    }

    /**
     * @return
     * @see java.util.Map#size()
     */
    public int size() {
        return map.size();
    }

    /**
     * @return
     * @see java.util.Map#values()
     */
    public Collection values() {
        return map.values();
    }

}
