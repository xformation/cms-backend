package com.synectiks.cms.dataimport.loader;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.entities.AcademicYear;
import com.synectiks.cms.entities.enumeration.Status;
import com.synectiks.cms.exceptions.AdditionalActiveAcademicYearFoundException;
import com.synectiks.cms.exceptions.MandatoryFieldMissingException;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;


public class AcademicYearLoader extends DataLoader {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private boolean isAcademicYearExist = false;
    private AllRepositories allRepositories;
    private String sheetName;

    public AcademicYearLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, AdditionalActiveAcademicYearFoundException, MandatoryFieldMissingException {
        // find out the active academic year. if it exitss then move this current record to exception

        StringBuilder sb = new StringBuilder();
        AcademicYear obj = CommonUtil.createCopyProperties(cls.newInstance(), AcademicYear.class);


        obj.setStatus(Status.ACTIVE);

        if(!this.allRepositories.findRepository(this.sheetName).exists(Example.of(obj))){

            String year = row.getCellAsString(0).orElse(null);
            if(CommonUtil.isNullOrEmpty(year)){
                sb.append("jhi_year, ");
                logger.warn ("Mandatory field missing. Field name - jhi_year");
            }else{
                obj.setYear(year);
            }
            String startDate = row.getCellAsString(1).orElse(null);
            if(CommonUtil.isNullOrEmpty(startDate)){
                sb.append("start_date, ");
                logger.warn ("Mandatory field missing. Field name - start_date");
            }else{
                obj.startDate(DateFormatUtil.convertStringToLocalDate(startDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
            String endDate = row.getCellAsString(2).orElse(null);
            if(CommonUtil.isNullOrEmpty(endDate)){
                sb.append("end_date, ");
                logger.warn ("Mandatory field missing. Field name - end_date");
            }else{
                obj.endDate(DateFormatUtil.convertStringToLocalDate(endDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
            String status = row.getCellAsString(3).orElse(null);
            if(CommonUtil.isNullOrEmpty(status)){
                sb.append("status, ");
                logger.warn ("Mandatory field missing. Field name - status");
            }else{
                if(Status.ACTIVE.toString().equalsIgnoreCase(status)){
                    obj.setStatus(Status.ACTIVE);
                }else {
                    obj.setStatus(Status.DEACTIVE);
                }
            }
        }else{
            sb.append("Application already have an active academic year, ");
            logger.warn ("Application already have an active academic year");
            if(sb.length() > 0) {
                String msg = "Application already have an active academic year";
                throw new AdditionalActiveAcademicYearFoundException(msg);
            }
        }

        if(sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg+sb.substring(0, sb.lastIndexOf(",")));
        }

        return (T)obj;
    }
}
