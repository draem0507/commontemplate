package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;
/**
 * OrderByPropertiesOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class CollectionOrderByPropertiesOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;

	public void setUp() {

		handler = new CollectionOrderByPropertiesOperatorHandler();
	}

	/**
	 * 对2元操作符 orderby 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个List对象，多个排序的属性List对象。
	 * @result
	 * 结果<br>
	 * 返回List按照升序排序的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateASC() throws Exception{

		List list = new ArrayList();
		list.add(new UserInfo("Tom", 22));
		list.add(new UserInfo("Rose", 20));
		list.add(new UserInfo("Rose", 18));
		list.add(new UserInfo("Aman", 30));

		List propertyList = new ArrayList();
		propertyList.add("+userName");
		propertyList.add("+userAge");

		Collection collection = (Collection) handler.doEvaluate(list, propertyList);

		UserInfo[] expectUserInfo =
			new UserInfo[]{new UserInfo("Aman", 30),
							new UserInfo("Rose", 18),
							new UserInfo("Rose", 20),
							new UserInfo("Tom", 22)};
		Iterator it = collection.iterator();
		int i = 0;
		while(it.hasNext()) {

			UserInfo userInfo = (UserInfo) it.next();
			assertEquals(expectUserInfo[i].getUserName(), userInfo.getUserName());
			assertEquals(expectUserInfo[i].getUserAge(), userInfo.getUserAge());
			i++;
		}

		propertyList = new ArrayList();
		propertyList.add("+userName");
		propertyList.add("-userAge");

		expectUserInfo =
			new UserInfo[]{new UserInfo("Aman", 30),
							new UserInfo("Rose", 20),
							new UserInfo("Rose", 18),
							new UserInfo("Tom", 22)};

		collection = (Collection) handler.doEvaluate(list, propertyList);
		it = collection.iterator();
		i = 0;
		while(it.hasNext()) {

			UserInfo userInfo = (UserInfo) it.next();
			assertEquals(expectUserInfo[i].getUserName(), userInfo.getUserName());
			assertEquals(expectUserInfo[i].getUserAge(), userInfo.getUserAge());
			i++;
		}
	}

	/**
	 * 对2元操作符 orderby 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个List对象，多个排序的属性List对象。
	 * @result
	 * 结果<br>
	 * 返回List按照降序排序的结果。
	 * @throws Exception
	 */
	public void testDoEvaluateDESC() throws Exception{

		List list = new ArrayList();
		list.add(new UserInfo("Tom", 22));
		list.add(new UserInfo("Rose", 20));
		list.add(new UserInfo("Rose", 18));
		list.add(new UserInfo("Aman", 30));

		List propertyList = new ArrayList();
		propertyList.add("-userName");
		propertyList.add("-userAge");

		Collection collection = (Collection) handler.doEvaluate(list, propertyList);

		UserInfo[] expectUserInfo =
			new UserInfo[]{new UserInfo("Tom", 22),
							new UserInfo("Rose", 20),
							new UserInfo("Rose", 18),
							new UserInfo("Aman", 30)};
		Iterator it = collection.iterator();
		int i = 0;
		while(it.hasNext()) {

			UserInfo userInfo = (UserInfo) it.next();
			assertEquals(expectUserInfo[i].getUserName(), userInfo.getUserName());
			assertEquals(expectUserInfo[i].getUserAge(), userInfo.getUserAge());
			i++;
		}

		propertyList = new ArrayList();
		propertyList.add("-userName");
		propertyList.add("+userAge");

		expectUserInfo =
			new UserInfo[]{new UserInfo("Tom", 22),
							new UserInfo("Rose", 18),
							new UserInfo("Rose", 20),
							new UserInfo("Aman", 30)};

		collection = (Collection) handler.doEvaluate(list, propertyList);
		it = collection.iterator();
		i = 0;
		while(it.hasNext()) {

			UserInfo userInfo = (UserInfo) it.next();
			assertEquals(expectUserInfo[i].getUserName(), userInfo.getUserName());
			assertEquals(expectUserInfo[i].getUserAge(), userInfo.getUserAge());
			i++;
		}
	}
}
