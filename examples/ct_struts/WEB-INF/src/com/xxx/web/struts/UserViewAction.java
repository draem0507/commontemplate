package com.xxx.web.struts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.xxx.model.User;
import com.xxx.web.struts.form.UserForm;

public class UserViewAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UserForm userForm = (UserForm)form;
		int id = userForm.getId();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		User user = null;
		try {
			if (id == 12)
				user = new User(12, "梁飞", "liangfei0201@163.com", 10000, df.parse("2007-08-09 22:18:46"));
			else if (id == 15)
				user = new User(15, "zhangyong", "zhangyong@aaa.com", 25000, df.parse("2007-08-10 13:24:11"));
			else if (id == 17)
				user = new User(17, "bobo", "bobo@bbb.com", 95010, df.parse("2007-08-11 05:49:35"));
			else if (id == 28)
				user = new User(28, "lixudong", "lixudong@ccc.com", 25000, df.parse("2007-09-11 17:57:09"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		request.setAttribute("user", user);
		return mapping.findForward("success");
	}
}
