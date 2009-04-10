package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 字面集合
 * @author liangfei0201@163.com
 */
public class LiteralList extends ArrayList {

    private static final long serialVersionUID = -2639608548954549100L;

    public LiteralList() {
        super();
    }

    public LiteralList(int initialCapacity) {
        super(initialCapacity);
    }

    public LiteralList(Collection collection) {
        super(collection);
    }

}
