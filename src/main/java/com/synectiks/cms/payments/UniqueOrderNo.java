package com.synectiks.cms.payments;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public final class UniqueOrderNo {
	private final static Logger logger = LoggerFactory.getLogger(UniqueOrderNo.class);
	
	private static final String ALGO = "SHA-256";
	private static final String ENCODING = "UTF-8";
	private static final String BASE = "SYN";
	
	private static AtomicLong COUNTER = new AtomicLong();
			
	private static final long MICRO_IN_MILL = 1000;
    private static final long NANO_IN_MICRO = 1000;

    private static long baseNanoTime;
    private static long baseTimeInMicro;
    private static long lastGuid;

    private static long creationTimeMillis;
    private static long lastTimeMillis;
    
    private static final String DATE_FORMAT = "yyyymmddhhmmsssss";
    
    private UniqueOrderNo () {}
    
    static
    {
        baseNanoTime = System.nanoTime();
        baseTimeInMicro = System.currentTimeMillis() * MICRO_IN_MILL;
        lastGuid = baseTimeInMicro;
        COUNTER.set(0);
    }

    
	public static void main(String a[]) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String id = getUniqueOrderNo();
		System.out.println(id);
	}
	
	public static synchronized String getUniqueOrderNo() throws NoSuchAlgorithmException {
		String id = BASE+COUNTER.getAndIncrement();
		String dt = new SimpleDateFormat(DATE_FORMAT).format(new Date(System.currentTimeMillis()));
		id = id + dt;
		return id.toUpperCase();
	}
	
	private static synchronized String next() {
		UUID u = UUID.randomUUID();
		return toIDString(u.getMostSignificantBits());// + toIDString(u.getLeastSignificantBits());
	}
	
	private static synchronized String toIDString(long i) {
	      char[] buf = new char[32];
	      int z = 64; // 1 << 6;
	      int cp = 32;
	      long b = z - 1;
	      do {
	          buf[--cp] = DIGITS66[(int)(i & b)];
	          i >>>= 6;
	      } while (i != 0);
	      return new String(buf, cp, (32-cp));
	}
	
	// array de 64+2 digitos 
	private final static char[] DIGITS66 = {
	    '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
		'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		'1','7','3','7'
	};
	
	private static synchronized String getSecureRandomNumber() throws NoSuchAlgorithmException {
		SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
		
		//generate a random number
		String randomNum = new Integer(prng.nextInt()).toString();
		
		//get its digest
//		MessageDigest sha = MessageDigest.getInstance("SHA-1");
//		byte[] result =  sha.digest(randomNum.getBytes());
		
		logger.debug("Random number: " + randomNum);
//		System.out.println("Message digest: " + new String(result));
		return randomNum;
	}
}
