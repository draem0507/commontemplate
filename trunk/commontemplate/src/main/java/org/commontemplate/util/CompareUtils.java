package org.commontemplate.util;

/**
 * 比较工具
 * 
 * @author liangfei0201@163.com
 *
 */
public class CompareUtils {
	
	private CompareUtils() {}
	
	private static final NumberComprator numberComprator = new NumberComprator();
	
	// 混合比较Byte, Short, Integer, Long, Float, Double等
	public static int compareNumber(Number n1, Number n2) {
		return numberComprator.compare(n1, n2);
	}
	
	private static final CharacterComprator characterComprator = new CharacterComprator();
	
	// 混合比较Character, String, StringBuffer等
	public static int compareCharacter(Object o1, Object o2) {
		return characterComprator.compare(o1, o2);
	}

}
