package com.synectiks.cms.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import com.synectiks.cms.constant.CmsConstants;

public class CommonUtil {
	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	private static final String OS = System.getProperty("os.name").toLowerCase();
	
	public static <T> T createCopyProperties(Object src, Class<T> cls) {
		T instance = null;
		if (!isNull(src)) {
			try {
				instance = cls.newInstance();
				BeanUtils.copyProperties(src, instance);
			} catch (InstantiationException | IllegalAccessException e) {
				logger.error("Failed to instantiate class: " + cls.getName(), e);
			} catch (BeansException be) {
				logger.error("Failed to fill bean: " + cls.getName(), be);
			}
		}
		return instance;
	}
	
	public static boolean isNull(Object object) {
		return null == object;
	}

	/**
	 * Method check if object is null or empty
	 * @param object
	 * @return
	 */
	public static boolean isNullOrEmpty(String object) {
		if (!isNull(object) && !object.trim().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static final String getOsType() {
		logger.debug("Operating System : "+OS);
		if(isWindows()) {
			return CmsConstants.OS_WINDOWS;
		}else if(isMac()) {
			return CmsConstants.OS_MAC;
		}else if(isUnix()) {
			return CmsConstants.OS_UNIX;
		}else if(isSolaris()) {
			return CmsConstants.OS_SOLARIS;
		}
		return OS;
	}
	
	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}

	public static boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}
	
	public static void main(String a[]) {
		getOsType();
	}
	
}
