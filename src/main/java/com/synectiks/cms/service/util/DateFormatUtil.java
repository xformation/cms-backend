package com.synectiks.cms.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public static void main(String a[]) throws Exception {
		String dt = changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, "dd/MM/yyyy", "29/04/2019");
		Date d = getUtilDate(CmsConstants.DATE_FORMAT_dd_MM_yyyy,dt);
		System.out.println(d);
	}
}
