package integration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelProvider {
	
	public static Map getSessionModel() {
		Map model = new HashMap();
		model.put("allow", Boolean.valueOf(true));
		return model;
	}
	
	public static Map getGlobalModel() {
		Map model = new HashMap();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			model.put("admin", new User(12, "liangfei", "liangfei0201@163.com", 10000, df.parse("2007-12-25")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	public static Map getModel() {
		Map model = new HashMap();
		
		model.put("allow", Boolean.valueOf(false));
		
		model.put("title", "test");
		
		model.put("bank", new Bank());
		
		List users = new ArrayList();
		model.put("users", users);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			users.add(new User(12, "liangfei", "liangfei0201@163.com", 10000, df.parse("2007-08-09")));
			users.add(new User(15, "zhangyong", "zhangyong@aaa.com", 25000, df.parse("2007-08-10")));
			users.add(new User(17, "bobo", "bobo@bbb.com", 95010, df.parse("2007-08-11")));
			users.add(new User(17, "lixudong", null, 25000, df.parse("2007-09-11")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Map mails = new HashMap();
		model.put("mails", mails);
		
		mails.put("liangfei", "liangfei0201@163.com");
		mails.put("zhangyong", "zhangyong@aaa.com");
		mails.put("bobo", "bobo@bbb.com");
		mails.put("lixudong", null);
		
		return model;
	}
}
