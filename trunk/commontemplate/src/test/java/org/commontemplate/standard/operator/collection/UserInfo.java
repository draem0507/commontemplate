package org.commontemplate.standard.operator.collection;
/**
 * 仅仅为了测试orderby 操作符使用。<br>
 * 因为orderby 操作符需要得到对象的属性，所以必须是public class 才可以。
 * @author YanRong
 *
 */
public class UserInfo {

	private String userName;
	private int userAge;

	// ClassStaticPropertyOperatorHandler 测试用
	public static final String TEST_CONST = "test const";
	//ClassStaticPropertyOperatorHandler 测试用
	private static final String country = "China";

	public static String getCountry() {
		return country;
	}

	public UserInfo(String userName, int userAge) {
		this.userName = userName;
		this.userAge = userAge;
	}

	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 测试 ObjectFunctionOperatorHandler 使用
	 * @return
	 */
	public String getMessage() {

		return "hello man";
	}

	/**
	 * 测试 ObjectFunctionOperatorHandler 使用
	 * @return
	 */
	public String getMessage(Integer i) {

		return "hello man " + i;
	}
	
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(obj instanceof UserInfo){
			return userName.equals(((UserInfo)obj).getUserName()) && userAge==((UserInfo)obj).getUserAge();
		}
		return super.equals(obj);
	}
}
