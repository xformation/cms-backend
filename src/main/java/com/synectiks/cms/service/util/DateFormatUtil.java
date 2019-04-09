package com.synectiks.cms.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateFormatUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateFormatUtil.class);
	
	public static final String changeDateFormat(final String newDateFormat, final String orgDateFormat, final String dateValue) throws ParseException, Exception {
		if(!CommonUtil.isNullOrEmpty(dateValue) || !CommonUtil.isNullOrEmpty(newDateFormat )  || !CommonUtil.isNullOrEmpty(orgDateFormat)){
			logger.warn(String.format("Returning null due to null in the given fields. Date : %s, new date format : %s, original date format : %s", dateValue, newDateFormat, orgDateFormat));
			return null;
		}
		try{
			java.util.Date orgUtilDate = new SimpleDateFormat(orgDateFormat).parse(dateValue);
			String formattedDate = new SimpleDateFormat(newDateFormat).format(orgUtilDate);
			logger.info(String.format("Changing dateFormat - new date format : %s, old date : %s, new date : %s",newDateFormat,dateValue,formattedDate));
			return formattedDate;
		}catch (ParseException e){
			logger.error("ParseException in date formate conversion : " , e);
			throw e;
		}catch (Exception e){
			logger.error("Exception in date formate conversion : " , e);
			throw e;
		}
	}
	
	public static final String changeDateFormat(String targetDateFormat, Date date) throws Exception {
		String newDt = null;
		try{
			newDt = new SimpleDateFormat(targetDateFormat).format(date.getTime());
			logger.info(String.format("Date format conversion :old date : %s, new date : %s",date,newDt));
		}catch (Exception e){
			logger.error("Exception in date formate conversion : " , e);
			throw e;
		}
        
        return newDt;
    }
}
