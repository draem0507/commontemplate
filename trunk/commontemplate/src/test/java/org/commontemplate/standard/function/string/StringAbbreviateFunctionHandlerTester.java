package org.commontemplate.standard.function.string;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.standard.function.FunctionHandlerSupport;
import org.commontemplate.standard.function.FunctionHandlerTester;
import org.commontemplate.standard.operator.UnhandleException;

/**
 * StringAbbreviateFunctionHandler 的测试。
 * @author YanRong
 *
 */
public class StringAbbreviateFunctionHandlerTester extends FunctionHandlerTester {
	
	protected FunctionHandlerSupport newFunctionHandlerSupport() {
		
		return new StringAbbreviateFunctionHandler();
	}
	
	/**
	 * 对于输入参数小于４的测试。<br>
	 * @condition
	 * 条件<br>
	 * 字符串为 "abcdefgh"<br>
	 * 数字为　2
	 * @result
	 * 结果<br>
	 * 返回"a..."。
	 * @throws Exception
	 */
	public void testAbbreviateOneParameter() throws Exception{
		
		String msg = "abcdefgh";
		
		List args = new ArrayList();
		args.add(new Integer(2));
		assertEvaluation(msg, args, "a...");
	}
	
	/**
	 * 对于输入参数小于４的测试。<br>
	 * @condition
	 * 条件<br>
	 * 字符串为 "你好你好你好你好"<br>
	 * 数字为　2
	 * @result
	 * 结果<br>
	 * 返回"你..."。
	 * @throws Exception
	 */
	public void testAbbreviateOneParameterChinese() throws Exception{
		
		String msg = "你好你好你好你好";
		
		List args = new ArrayList();
		args.add(new Integer(2));
		assertEvaluation(msg, args, "你...");
	}
	
	/**
	 * 对于输入参数大于４的测试。<br>
	 * @condition
	 * 条件<br>
	 * 字符串为 "abcdefgh"<br>
	 * 数字为　7
	 * @result
	 * 结果<br>
	 * 返回"abcd..."。
	 * @throws Exception
	 */
	public void testAbbreviateOneParameter2() throws Exception{
		
		String msg = "abcdefgh";
		
		List args = new ArrayList();
		args.add(new Integer(7));
		assertEvaluation(msg, args, "abcd...");
	}
	
	/**
	 * 对于输入参数大于４的测试。<br>
	 * @condition
	 * 条件<br>
	 * 字符串为 "你好你好你好你好"<br>
	 * 数字为　7
	 * @result
	 * 结果<br>
	 * 返回"你好你好..."。
	 * @throws Exception
	 */
	public void testAbbreviateOneParameterChinese2() throws Exception{
		
		String msg = "你好你好你好你好";
		
		List args = new ArrayList();
		args.add(new Integer(7));
		assertEvaluation(msg, args, "你好你好...");
	}
	
	/**
	 * 对于输入2个参数的测试。<br>
	 * @condition
	 * 条件<br>
	 * 字符串为 "abcdefghijklmno"<br>
	 * 数字为4和10
	 * @result
	 * 结果<br>
	 * 返回"abcdefg..."。
	 * @throws Exception
	 */
	public void testAbbreviateTwoParameter() throws Exception {
		
		String msg = "abcdefghijklmno";
		
		List args = new ArrayList();
		args.add(new Integer(4));
		args.add(new Integer(10));
		
		assertEvaluation(msg, args, "abcdefg...");
	}
	
	/**
	 * 对于输入2个参数的测试。<br>
	 * @condition
	 * 条件<br>
	 * 字符串为 "abcdefghijklmno"<br>
	 * 数字为5和10
	 * @result
	 * 结果<br>
	 * 返回"...fghi..."。
	 * @throws Exception
	 */
	public void testAbbreviateTwoParameter2() throws Exception {
		
		String msg = "abcdefghijklmno";
		
		List args = new ArrayList();
		args.add(new Integer(5));
		args.add(new Integer(10));
		
		assertEvaluation(msg, args, "...fghi...");
	}
	
	/**
	 * 对于输入没有参数的测试。<br>
	 * @condition
	 * 条件<br>
	 * 字符串为 "abcdefghijklmno"<br>
	 * @result
	 * 结果<br>
	 * 返回""。
	 * @throws Exception
	 */
	public void testAbbreviateNoParameter() throws Exception {
		
		String msg = "abcdefghijklmno";
		
		List args = new ArrayList();
		assertEvaluation(msg, args, "");
		assertEvaluation(msg, null, "");
	}
	
	/**
	 * 对于输入参数个数错误的测试。<br>
	 * @condition
	 * 条件<br>
	 * 字符串为 "abcdefghijklmno"<br>
	 * 输入参数大于2个
	 * @result
	 * 结果<br>
	 * 抛出 UnhandleException。
	 * @throws Exception
	 */
	public void testAbbreviateParameterCountWrong() throws Exception {
		
		String msg = "abcdefghijklmno";
		
		List args = new ArrayList();
		args.add(new Integer(5));
		args.add(new Integer(10));
		args.add(new Integer(5));
		
		try {
			
			assertEvaluation(msg, args, "...fghi...");
			throw new Exception();
		} catch (UnhandleException e) {
			
		}		
	}
	
	/**
	 * 对于输入参数类型错误的测试。<br>
	 * @condition
	 * 条件<br>
	 * 字符串为 "abcdefghijklmno"<br>
	 * 输入参数为字符串
	 * @result
	 * 结果<br>
	 * 抛出 IllegalStateException。
	 * @throws Exception
	 */
	public void testAbbreviateParameterIsNotNumber() throws Exception {
		
		String msg = "abcdefghijklmno";
		
		List args = new ArrayList();
		args.add("a");
		try {
			assertEvaluation(msg, args, "...fghi...");
			throw new Exception();
		} catch (IllegalStateException e){
			
		}
	}
	
	/**
	 * 对于输入参数类型错误的测试。<br>
	 * @condition
	 * 条件<br>
	 * 字符串为 "abcdefghijklmno"<br>
	 * 输入参数为字符串
	 * @result
	 * 结果<br>
	 * 抛出 IllegalStateException。
	 * @throws Exception
	 */
	public void testAbbreviateTwoParameterIsNotNumber() throws Exception {
		
		String msg = "abcdefghijklmno";
		
		List args = new ArrayList();
		args.add("1");
		args.add("a");
		try {
			assertEvaluation(msg, args, "...fghi...");
			throw new Exception();
		} catch (IllegalStateException e){
			
		}
	}
	
	/**
	 * 对于输入参数类型错误的测试。<br>
	 * @condition
	 * 条件<br>
	 * 字符串为 "abcdefghijklmno"<br>
	 * 输入参数为字符串
	 * @result
	 * 结果<br>
	 * 抛出 IllegalStateException。
	 * @throws Exception
	 */
	public void testAbbreviateTwoParameterIsNotNumber2() throws Exception {
		
		String msg = "abcdefghijklmno";
		
		List args = new ArrayList();
		args.add("a");
		args.add("1");
		try {
			assertEvaluation(msg, args, "...fghi...");
			throw new Exception();
		} catch (IllegalStateException e){
			
		}
	}
	
	/**
	 * 对于输入参数类型错误的测试。<br>
	 * @condition
	 * 条件<br>
	 * 字符串为 "abcdefghijklmno"<br>
	 * 输入参数为字符串
	 * @result
	 * 结果<br>
	 * 抛出 IllegalStateException。
	 * @throws Exception
	 */
	public void testAbbreviateTwoParameterIsNotNumber3() throws Exception {
		
		String msg = "abcdefghijklmno";
		
		List args = new ArrayList();
		args.add("a");
		args.add("a");
		try {
			assertEvaluation(msg, args, "...fghi...");
			throw new Exception();
		} catch (IllegalStateException e){
			
		}
	}
	
}
