package com.xxx.web.spring;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.xxx.model.User;

public class UserListController implements Controller {

	private String successView;

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List users = new ArrayList();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			users.add(new User(12, "梁飞", "liangfei0201@163.com", 10000, df.parse("2007-08-09 22:18:46")));
			users.add(new User(15, "zhangyong", "zhangyong@aaa.com", 25000, df.parse("2007-08-10 13:24:11")));
			users.add(new User(17, "bobo", "bobo@bbb.com", 95010, df.parse("2007-08-11 05:49:35")));
			users.add(new User(28, "lixudong", "lixudong@ccc.com", 25000, df.parse("2007-09-11 17:57:09")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map model = new HashMap();
		model.put("users", users);
		return new ModelAndView(successView, model);
	}

}
