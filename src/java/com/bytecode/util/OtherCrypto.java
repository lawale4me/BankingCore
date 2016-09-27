/**
 *
 */
package com.bytecode.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author kumar
 *
 */
public class OtherCrypto
{

	private static String key = "*fcmb_key*";

	//public static final String key = "fcmb_key";

	public static String FCMBDecrypt(String stringToDecrypt)
	{
		String ret = "";
	    try
	    {
	      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	      byte[] keyBytes = new byte[16];
	      byte[] b = key.getBytes("UTF-8");
	      int len = b.length;
	      if (len > keyBytes.length) len = keyBytes.length;
	      System.arraycopy(b, 0, keyBytes, 0, len);
	      SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
	      IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
	      cipher.init(2, keySpec, ivSpec);

	      BASE64Decoder decoder = new BASE64Decoder();
	      byte[] results = cipher.doFinal(decoder.decodeBuffer(stringToDecrypt));
	      ret = new String(results, "UTF-8");
	    }
	    catch (Exception ex)
	    {
	      ret = "Error";
	    }

	    return ret;
	}

	public static String FCMBEncrypt(String stringToEncrypt)
	{
	    String ret = "";

	    try
	    {
	      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	      byte[] keyBytes = new byte[16];
	      byte[] b = key.getBytes("UTF-8");
	      int len = b.length;
	      if (len > keyBytes.length) len = keyBytes.length;
	      System.arraycopy(b, 0, keyBytes, 0, len);
	      SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
	      IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
	      cipher.init(1, keySpec, ivSpec);

	      byte[] results = cipher.doFinal(stringToEncrypt.getBytes("UTF-8"));
	      BASE64Encoder encoder = new BASE64Encoder();
	      ret = encoder.encode(results);
	    }
	    catch (Exception ex)
	    {
	      ret = "Error";
	    }

	    return ret;
	 }

}