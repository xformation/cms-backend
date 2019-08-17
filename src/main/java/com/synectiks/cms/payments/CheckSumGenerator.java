package com.synectiks.cms.payments;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synectiks.cms.constant.CmsConstants;


public final class CheckSumGenerator {

	private static final Logger logger = LoggerFactory.getLogger(CheckSumGenerator.class);

	
//	public static void main(String a[]) throws NoSuchAlgorithmException, InvalidKeyException {
////		String checkSum = getHash("ac");
//		String m = getCheckSum("HMACUAT|SYN220191414051400043|NA|6500|NA|NA|NA|INR|NA|R|hmacuat|NA|NA|F|NA|NA|NA|NA|NA|NA|NA|http://18.209.4.2:3000/payment-response");
//		System.out.println(m);
//	}
	
	public static synchronized String getCheckSum(String input) throws NoSuchAlgorithmException, InvalidKeyException {
		Mac sha256_HMAC = Mac.getInstance(CmsConstants.HASH_ALGO);
		SecretKeySpec secret_key = new SecretKeySpec(CmsConstants.HASH_KEY.getBytes(), CmsConstants.HASH_ALGO);
		sha256_HMAC.init(secret_key);
		byte[] result = sha256_HMAC.doFinal(input.getBytes());
		String hash = DatatypeConverter.printHexBinary(result);
	    return hash.toUpperCase(); 
	}
	
	public static synchronized boolean validateResponse(String input, String orgCheckSum) throws NoSuchAlgorithmException, InvalidKeyException {
		Mac sha256_HMAC = Mac.getInstance(CmsConstants.HASH_ALGO);
		SecretKeySpec secret_key = new SecretKeySpec(CmsConstants.HASH_KEY.getBytes(), CmsConstants.HASH_ALGO);
		sha256_HMAC.init(secret_key);
		byte[] result = sha256_HMAC.doFinal(input.getBytes());
		String hash = DatatypeConverter.printHexBinary(result);
	    String newCheckSum = hash.toUpperCase();
	    logger.debug(String.format("Original checksum :  %s", orgCheckSum));
	    logger.debug(String.format("Generated checksum : %s", newCheckSum));
	    if(orgCheckSum.equals(newCheckSum)) {
	    	return true;
	    }
	    return false;
	}
	
//	public static byte[] getSHA(String input) throws InvalidKeyException, NoSuchAlgorithmException {  
//        MessageDigest md = MessageDigest.getInstance(HASH_ALGO);  
//        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
//    } 
	
	/**
     * Use javax.xml.bind.DatatypeConverter class in JDK to convert byte array
     * to a hexadecimal string. Note that this generates hexadecimal in lower case.
     * @param hash
     * @return 
     */
//    private static String  bytesToHex(byte[] hash) {
//        return DatatypeConverter.printHexBinary(hash).toUpperCase();
//    }
}
