package iteso.libs;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Encript {
	public static String encript(String s) throws UnsupportedEncodingException{
		return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
	}

	public static String decript(String s) throws UnsupportedEncodingException{        
		byte[] decode = Base64.getDecoder().decode(s.getBytes());
		return new String(decode, "utf-8");
	}

}