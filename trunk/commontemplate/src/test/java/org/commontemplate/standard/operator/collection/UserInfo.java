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
}
