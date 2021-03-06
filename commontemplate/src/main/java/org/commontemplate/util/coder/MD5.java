package org.commontemplate.util.coder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	private MD5() {}

	public static String encode(byte[] src) {
		if (src == null)
			return null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return HEX.encode(md.digest(src));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static String encode(String src) {
		if (src == null)
			return null;
		return encode(src.getBytes());
	}
}
