package com.xxx.web.webwork;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork.ActionSupport;
import com.xxx.model.User;

public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private List users;
	
	public List getUsers() {
		return users;
	}
	
	public String list() throws Exception {
		// 数据本应从UserService中获取，这里只是测试，所以直接在Action中产生
		users = new ArrayList();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			users.add(new User(12, "梁飞", "liangfei0201@163.com", 10000, df.parse("2007-08-09 22:18:46")));
			users.add(new User(15, "zhangyong", "zhangyong@aaa.com", 25000, df.parse("2007-08-10 13:24:11")));
			users.add(new User(17, "bobo", "bobo@bbb.com", 95010, df.parse("2007-08-11 05:49:35")));
			users.add(new User(28, "lixudong", "lixudong@ccc.com", 25000, df.parse("2007-09-11 17:57:09")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	private User user = new User();

	public final User getUser() {
		return user;
	}

	public final void setUser(User user) {
		this.user = user;
	}
	
	public String view() throws Exception {
		if (user != null) {
			int id = user.getId();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (id == 12)
				user = new User(12, "梁飞", "liangfei0201@163.com", 10000, df.parse("2007-08-09 22:18:46"));
			else if (id == 15)
				user = new User(15, "zhangyong", "zhangyong@aaa.com", 25000, df.parse("2007-08-10 13:24:11"));
			else if (id == 17)
				user = new User(17, "bobo", "bobo@bbb.com", 95010, df.parse("2007-08-11 05:49:35"));
			else if (id == 28)
				user = new User(28, "lixudong", "lixudong@ccc.com", 25000, df.parse("2007-09-11 17:57:09"));
			else
				user = null;
		}
		return "success";
	}

}
