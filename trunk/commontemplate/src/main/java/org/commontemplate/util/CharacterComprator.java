package org.commontemplate.util;

/**
 * 字符比较器
 * <p/>
 * 混合比较Character, CharSequence, String, StringBuffer等
 * 
 * @author liangfei0201@163.com
 *
 */
public class CharacterComprator implements java.util.Comparator, java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	public CharacterComprator() {
		
	}
	
	public int compare(Object o1, Object o2) {
		if (o1 instanceof Character && o2 instanceof Character) 
			return ((Character)o1).compareTo((Character)o2);
		if (o1 instanceof String && o2 instanceof String) 
			return ((String)o1).compareTo((String)o2);
		if (o1 instanceof StringBuffer && o2 instanceof StringBuffer) 
			return (((StringBuffer)o1).toString()).compareTo(((StringBuffer)o2).toString());
		if (o1 instanceof CharSequence && o2 instanceof CharSequence)
			compareSequence((CharSequence)o1, (CharSequence)o2);
		if (o1 instanceof Character && o2 instanceof CharSequence)
			return compareCharacterSequence((Character)o1, (CharSequence)o2);
		if (o1 instanceof CharSequence && o2 instanceof Character)
			return - compareCharacterSequence((Character)o2, (CharSequence)o1);
		return 0;
	}
	
	private int compareSequence(CharSequence c1, CharSequence c2) {
		if (c1.length() != c2.length())
			return c1.length() - c2.length();
		for (int i = 0, n = c1.length(); i < n; i ++)
			if (c1.charAt(i) != c2.charAt(i))
				return c1.charAt(i) - c2.charAt(i);
		return 0;
	}
	
	private int compareCharacterSequence(Character c, CharSequence cs) {
		if (cs.length() == 1)
			return c.charValue() - cs.charAt(0);
		return cs.length() - 1;
	}

}
