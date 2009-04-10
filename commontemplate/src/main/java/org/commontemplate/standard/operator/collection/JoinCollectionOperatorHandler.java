/**
 * Project: commontemplate
 * 
 * File Created at 2009-4-10
 * $Id$
 * 
 * Copyright 2009 Alibaba.com Croporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package org.commontemplate.standard.operator.collection;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 添加元素到集合
 * @author william.liangf
 */
public class JoinCollectionOperatorHandler extends BinaryOperatorHandlerSupport {

    private static final long serialVersionUID = 1L;

    public JoinCollectionOperatorHandler() {
        super(LiteralList.class, Object.class, false, true);
    }

    public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
        LiteralList literalList = (LiteralList)leftOperand;
        literalList.add(rightOperand);
        return literalList;
    }

}
