package org.commontemplate.standard.operator.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 字面Map
 * @author liangfei0201@163.com
 */
public class LiteralMap extends HashMap {

    private static final long serialVersionUID = -4046409124983128683L;

    public LiteralMap() {
        super();
    }

    public LiteralMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }
    
    public LiteralMap(int initialCapacity) {
        super(initialCapacity);
    }

    public LiteralMap(Map map) {
        super(map);
    }

}
