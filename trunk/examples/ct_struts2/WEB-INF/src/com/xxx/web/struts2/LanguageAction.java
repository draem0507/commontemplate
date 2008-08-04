package com.xxx.web.struts2;

import java.util.Locale;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LanguageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String language;
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	private String country;

	public void setCountry(String country) {
		this.country = country;
	}

	public String change() throws Exception {
		if (language != null && language.length() > 0) {
			if (country != null && country.length() > 0)
				ActionContext.getContext().getSession().put("WW_TRANS_I18N_LOCALE", new Locale(language, country));
			else
				ActionContext.getContext().getSession().put("WW_TRANS_I18N_LOCALE", new Locale(language));
		}
		return "success";
	}

}
