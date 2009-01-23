package org.commontemplate.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * BeanUtils测试类
 *
 * @author yanrong
 *
 */
public class ArgumentUtilsTester extends TestCase {
		
	public void testArgumentIsNull() {
		
		List list = ArgumentUtils.getArgumentList(null);
		Object[] obj = ArgumentUtils.getArgumentArray(null);
		
		assertEquals(0, list.size());
		assertEquals(0, obj.length);
	}
	
	public void testArgumentIsList() {
		
		List argList = null;
		List list = ArgumentUtils.getArgumentList(argList);
		Object[] obj = ArgumentUtils.getArgumentArray(argList);
		assertEquals(0, list.size());
		assertEquals(0, obj.length);
		
		argList = new ArrayList();
		argList.add("a");
		assertEquals(argList, ArgumentUtils.getArgumentList(argList));
		obj = ArgumentUtils.getArgumentArray(argList);
		assertEquals(1, obj.length);
		assertEquals("a", obj[0]);
	}
	
	public void testArgumentIsObject() {
		
		String s = "test";
		List list = ArgumentUtils.getArgumentList(s);
		Object[] obj = ArgumentUtils.getArgumentArray(s);
		
		assertEquals(1, list.size());
		assertEquals("test", list.get(0));
		
		assertEquals(1, obj.length);
		assertEquals("test", obj[0]);
	}
}
