package com.synectiks.cms.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synectiks.cms.constant.CmsConstants;

public final class DateFormatUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateFormatUtil.class);
	
	public static final String changeDateFormat(final String newDateFormat, final String orgDateFormat, final String dateValue) throws ParseException, Exception {
		if(CommonUtil.isNullOrEmpty(dateValue) || CommonUtil.isNullOrEmpty(newDateFormat )  || CommonUtil.isNullOrEmpty(orgDateFormat)){
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
	
	public static final Date getUtilDate(String dateFormat, String date) throws Exception {
		Date newDt = null;
		SimpleDateFormat sdf = new SimpleDateFormat();
		try{
			sdf.applyPattern(dateFormat);
			newDt = sdf.parse(date);
			logger.info(String.format("Date conversion from string to util date :old date : %s, new date : %s",date,newDt));
		}catch (Exception e){
			logger.error("Exception in date conversion from string to util date: " , e);
			throw e;
		}
        return newDt;
	}
	
	public static final String subtractDays(String dtFormat, String dt, int days) throws ParseException {
//        String dtFormat = "yyyy-MM-dd";
        Date date = new SimpleDateFormat(dtFormat).parse(dt);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        String newDt = new SimpleDateFormat(dtFormat).format(cal.getTime());
        return newDt;
    }
	
	public static final String subtractDays(String dtFormat, Date date, int days) throws ParseException {
//      String dtFormat = "yyyy-MM-dd";
//      Date date = new SimpleDateFormat(dtFormat).parse(dt);
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(date);
      cal.add(Calendar.DATE, -days);
      String newDt = new SimpleDateFormat(dtFormat).format(cal.getTime());
      return newDt;
   }
	
	public final static Date converUtilDateFromLocaDate(LocalDate localDate) {
		if(localDate == null) return null;
	    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	public final static java.sql.Date converSqlDateFromLocaDate(LocalDate localDate) {
		if(localDate == null) return null;
	    long dt =  Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()).getTime();
	    return new java.sql.Date(dt);
	}
	public final static Date convertUtilDateFromLocalDateTime(LocalDateTime localDateTime) {
		if(localDateTime == null) return null;
	    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	public final static LocalDate convertLocalDateFromUtilDate(Date date) {
		if(date == null) return null;
	   return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public final static String changeLocalDateFormat(LocalDate dt, String targetFormat) {
		if(dt == null || targetFormat == null) return null;
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern(targetFormat);
        String text = dt.format(formatters);
        logger.debug("Formated local date : "+text);
        return text;
	}
	
	public final static LocalDate convertStringToLocalDate(String dt, String targetFormat) {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern(targetFormat);
		LocalDate localDate = LocalDate.parse(dt, formatters);
		 logger.debug("Local date from string date: "+localDate);
		return localDate;
	}
	
	public final static LocalDate getLocalDateFromString(String strDate) {
    	String stDt[] = strDate.split("/");
        if(stDt[0].length() == 1) {
        	stDt[0] = "0"+stDt[0];
        }
        if(stDt[1].length() == 1) {
        	stDt[1] = "0"+stDt[1];
        }
        return DateFormatUtil.convertStringToLocalDate(stDt[2]+"-"+stDt[0]+"-"+stDt[1], CmsConstants.DATE_FORMAT_yyyy_MM_dd);
    }
	
	public static int calculateAge(LocalDate dateOfBirth) {
	  LocalDate now = LocalDate.now(); 
	  Period diff = Period.between(dateOfBirth, now); //difference between the dates is calculated
	  logger.debug("Age : " + diff.getYears());
	  return diff.getYears();
	}
	
	public static void main(String a[]) throws Exception {
//		String dt = changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, "dd/MM/yyyy", "29/04/2019");
//		Date d = getUtilDate(CmsConstants.DATE_FORMAT_dd_MM_yyyy,dt);
//		System.out.println(d);
		LocalDate date = convertStringToLocalDate("08"+"/"+"09"+"/"+"2019","MM/dd/yyyy"); //LocalDate.now();
		System.out.println("date to be formated : "+date);
//		String dt = changeLocalDateFormat(date, CmsConstants.DATE_FORMAT_MM_dd_yyyy);
//		DateTimeFormatter formatters = DateTimeFormatter.ofPattern(CmsConstants.DATE_FORMAT_MM_dd_yyyy);
//		System.out.println("local date after format change : "+formatters.format(date));
	}
}
