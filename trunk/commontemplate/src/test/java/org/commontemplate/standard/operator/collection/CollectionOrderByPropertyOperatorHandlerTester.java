package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;
/**
 * OrderByPropertyOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class CollectionOrderByPropertyOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;

	public void setUp() {

		handler = new CollectionOrderByPropertyOperatorHandler();
	}

	/**
	 * 对2元操作符 orderby 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个List对象，一个排序的属性名称。
	 * @result
	 * 结果<br>
	 * 返回List按照升序排序的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateASC() throws Exception{

		List list = new ArrayList();
		list.add(new UserInfo("Tom", 22));
		list.add(new UserInfo("jack", 20));
		list.add(new UserInfo("Rose", 18));
		list.add(new UserInfo("Aman", 30));

		Collection collection = (Collection) handler.doEvaluate(list, "+userName");

		String[] expectUserName = new String[]{"Aman","Rose", "Tom", "jack"};
		Iterator it = collection.iterator();
		int i = 0;
		while(it.hasNext()) {

			UserInfo userInfo = (UserInfo) it.next();
			assertEquals(expectUserName[i], userInfo.getUserName());
			i++;
		}

		expectUserName = new String[]{"Rose","jack", "Tom", "Aman"};
		collection = (Collection) handler.doEvaluate(list, "+userAge");
		it = collection.iterator();
		i = 0;
		while(it.hasNext()) {

			UserInfo userInfo = (UserInfo) it.next();
			assertEquals(expectUserName[i], userInfo.getUserName());
			i++;
		}
	}

	/**
	 * 对2元操作符 orderby 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个List对象，一个排序的属性名称。
	 * @result
	 * 结果<br>
	 * 返回List按照降序排序的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDESC() throws Exception{

		List list = new ArrayList();
		list.add(new UserInfo("Tom", 22));
		list.add(new UserInfo("jack", 20));
		list.add(new UserInfo("Rose", 18));
		list.add(new UserInfo("Aman", 30));

		Collection collection = (Collection) handler.doEvaluate(list, "-userName");

		String[] expectUserName = new String[]{"jack", "Tom", "Rose", "Aman"};
		Iterator it = collection.iterator();
		int i = 0;
		while(it.hasNext()) {

			UserInfo userInfo = (UserInfo) it.next();
			assertEquals(expectUserName[i], userInfo.getUserName());
			i++;
		}

		expectUserName = new String[]{"Aman", "Tom", "jack", "Rose"};
		collection = (Collection) handler.doEvaluate(list, "-userAge");
		it = collection.iterator();
		i = 0;
		while(it.hasNext()) {

			UserInfo userInfo = (UserInfo) it.next();
			assertEquals(expectUserName[i], userInfo.getUserName());
			i++;
		}
	}
}
