/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.util;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Ahmed
 */

public class Crypto
{
	public static final String VANSO_KEY = "B374A26A71490437AA024E4FADD5B497FDFF1A8EA6FF12F6FB65AF2720B59CCF";
	
	private static final String ALGORITHM = "AES/CTR/NoPadding";
	private static final int BITS128 = 16;
	private static final int BITS256 = 32;
	
	public static String encrypt128(String key, String message) throws Exception {
		return Base64.encodeBase64String(encrypt(key, message, BITS128));
	}
	
	public static String decrypt128(String key, String message) throws Exception {
		byte[] msg = Base64.decodeBase64(message);
		String decValue = new String(decrypt(key, msg, BITS128),"UTF-8");
		return decValue;
	}
	
	public static String encrypt256(String key, String message) throws Exception {
		return Base64.encodeBase64String(encrypt(key, message, BITS256));
	}
	
	public static String decrypt256(String key, String message) throws Exception {
		byte[] msg = Base64.decodeBase64(message);
		String decValue = new String(decrypt(key, msg, BITS256),"UTF-8");
		return decValue;
	}
	
	private static byte[] encrypt(String keystring, String message, int bits) throws Exception {
		byte[] encValue = null;
		SecureRandom random = new SecureRandom();
		byte[] nonceBytes = new byte[8];
        random.nextBytes(nonceBytes);
		IvParameterSpec nonce = new IvParameterSpec(Arrays.copyOf(nonceBytes, 16));
		
		Key key = generateKey(keystring, bits);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key, nonce);
		byte[] ciphertextWithoutNonce = c.doFinal(message.getBytes("UTF-8"));
        encValue = Arrays.copyOf(nonceBytes, nonceBytes.length + ciphertextWithoutNonce.length);
        for (int i = 0; i < ciphertextWithoutNonce.length; i++) {
        	encValue[i + 8] = ciphertextWithoutNonce[i];
        }
		
		return encValue;
	}
	
	private static byte[] decrypt(String keystring, byte[] message, int bits) throws Exception {
		byte[] decValue = null;
		byte[] nonceBytes = Arrays.copyOf(Arrays.copyOf(message, 8), 16);
		IvParameterSpec nonce = new IvParameterSpec(nonceBytes);
		
		Key key = generateKey(keystring, bits);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key, nonce);
		decValue = c.doFinal(message, 8, message.length - 8);
		
		return decValue;
	}
	
	private static Key generateKey(String keystring, int bits) throws Exception {
		byte[] keyBytes = new byte[bits];
		byte[] key = new byte[bits];
		for (int i=0; i<bits; i++) {
			keyBytes[i] = (byte) keystring.codePointAt(i);
		}
		SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        key = cipher.doFinal(keyBytes);
        for (int i=0; i<bits-16; i++) {
        	key[16+i]= key[i];
        }
        
        return new SecretKeySpec(key, "AES");
	}
        
        
        public static void main(String args[]){
            try {
                System.out.println("Hello world:"+Crypto.decrypt256(VANSO_KEY, "eQDUrpkbc1R0IMw/hGbWPK4="));
            } catch (Exception ex) {
                Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}