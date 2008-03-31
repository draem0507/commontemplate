package org.commontemplate.standard.operator.object;

import org.commontemplate.config.BinaryOperatorHandler;

import junit.framework.TestCase;
/**
 * PackageDotOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class PackageDotOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	protected void setUp() throws Exception {
		handler = new PackageDotOperatorHandler();
	}
	
	/**
	 * 对2元操作符 . 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Package对象和类的名字。
	 * @result
	 * 结果<br>
	 * 如果该类存在，则返回该类。否则返回新的Package对象。
	 * @throws Exception
	 */
	public void testDoEvaluateClassExist() throws Exception {
		
		Package pkg = Package.getPackage("org.commontemplate.standard.operator.object");
		
		Class clazz = (Class) handler.doEvaluate(pkg, "PackageDotOperatorHandlerTester"); 
		
		assertEquals(this.getClass(), clazz);
	}
	
	/**
	 * 对2元操作符 . 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Package对象和类的名字。
	 * @result
	 * 结果<br>
	 * 如果该类存在，则返回该类。否则返回新的Package对象。
	 * @throws Exception
	 */
	public void testDoEvaluateClassNotExist() throws Exception {
		
		Package pkg = Package.getPackage("org.commontemplate.standard.operator.object");
		
		pkg = (Package) handler.doEvaluate(pkg, "abc"); 
		assertNull(pkg);
	}
	
	/**
	 * 对2元操作符 . 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Package对象和类的名字。
	 * @result
	 * 结果<br>
	 * 如果该类存在，则返回该类。否则返回新的Package对象。
	 * @throws Exception
	 */
	public void testDoEvaluateClassNotExist2() throws Exception {
		
		Package pkg = Package.getPackage("org.commontemplate.standard.operator");
		
		pkg = (Package) handler.doEvaluate(pkg, "object"); 
		assertNotNull(pkg);
		assertEquals("org.commontemplate.standard.operator.object", pkg.getName());
	}
	
}
