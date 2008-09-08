package org.commontemplate.standard.operator.collection;

/**
 * 仅仅为了测试orderby 操作符使用。<br>
 * 因为orderby 操作符需要得到对象的属性，所以必须是public class 才可以。
 * @author YanRong
 *
 */
public class UserInfo {

	// ClassStaticPropertyOperatorHandler 测试用
	public static final String TEST_CONST = "test const";

	//ClassStaticPropertyOperatorHandler 测试用
	private static final String country = "China";

	public static String getCountry() {
		return country;
	}

	private String userName;

	private int userAge;

	public UserInfo() {

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

	// hashCode and equals generate by eclipse

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userAge;
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserInfo other = (UserInfo) obj;
		if (userAge != other.userAge)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}


}
