package com.synectiks.cms.payments;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;

public final class CheckSumGenerator {

	private static final String HASH_KEY = "uIZ2iayX70hc";
	private static final String HASH_ALGO = "HmacSHA256";
	
	public static void main(String a[]) throws NoSuchAlgorithmException, InvalidKeyException {
//		String checkSum = getHash("ac");
		String m = getCheckSum("HMACUAT|SYN220191414051400043|NA|6500|NA|NA|NA|INR|NA|R|hmacuat|NA|NA|F|NA|NA|NA|NA|NA|NA|NA|http://18.209.4.2:3000/payment-response");
		System.out.println(m);
	}
	
	public static synchronized String getCheckSum(String input) throws NoSuchAlgorithmException, InvalidKeyException {
		Mac sha256_HMAC = Mac.getInstance(HASH_ALGO);
		SecretKeySpec secret_key = new SecretKeySpec(HASH_KEY.getBytes(), HASH_ALGO);
		sha256_HMAC.init(secret_key);
		byte[] result = sha256_HMAC.doFinal(input.getBytes());
		String hash = DatatypeConverter.printHexBinary(result);
	    return hash.toUpperCase(); 
	}
	
	
	public static byte[] getSHA(String input) throws InvalidKeyException, NoSuchAlgorithmException {  
        MessageDigest md = MessageDigest.getInstance(HASH_ALGO);  
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
	
	/**
     * Use javax.xml.bind.DatatypeConverter class in JDK to convert byte array
     * to a hexadecimal string. Note that this generates hexadecimal in lower case.
     * @param hash
     * @return 
     */
    private static String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash).toUpperCase();
    }
}
