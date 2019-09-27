package com.synectiks.cms.dataimport.loader;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.Holiday;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.exceptions.AdditionalHolidayFoundException;
import com.synectiks.cms.exceptions.MandatoryFieldMissingException;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import java.util.Optional;

public class HolidayDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public HolidayDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, MandatoryFieldMissingException, AdditionalHolidayFoundException {
        StringBuilder sb = new StringBuilder();
        Holiday obj = CommonUtil.createCopyProperties(cls.newInstance(), Holiday.class);

        obj.setHolidayStatus(Status.ACTIVE);
        if (!this.allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {

            String holidayDesc = row.getCellAsString(0).orElse(null);
            if (CommonUtil.isNullOrEmpty(holidayDesc)) {
                sb.append("holiday_desc, ");
                logger.warn("Mandatory field missing. Field name - holiday_desc");
            } else {
                obj.setHolidayDesc(holidayDesc);
            }


            String holidayDate = row.getCellAsString(1).orElse(null);
            if (CommonUtil.isNullOrEmpty(holidayDate)) {
                sb.append("holiday_date, ");
                logger.warn("Mandatory field missing. Field name - holiday_date");
            } else {
                obj.holidayDate(DateFormatUtil.convertStringToLocalDate(holidayDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }


            String termStatus = row.getCellAsString(3).orElse(null);
            if (CommonUtil.isNullOrEmpty(termStatus)) {
                sb.append("status, ");
                logger.warn("Mandatory field missing. Field name - term_status");
            } else {
                if (Status.ACTIVE.toString().equalsIgnoreCase(termStatus)) {
                    obj.setHolidayStatus(Status.ACTIVE);
                } else {
                    obj.setHolidayStatus(Status.DEACTIVE);
                }
            }
        }else {
            sb.append("Application already have an active holiday status, ");
            logger.warn("Application already have an active holiday status");
            if (sb.length() > 0) {
                String msg = "Application already have an active holiday status";
                throw new AdditionalHolidayFoundException(msg);
            }
        }

        String academicYear = row.getCellAsString(4).orElse(null);
        if(CommonUtil.isNullOrEmpty(academicYear)) {
            sb.append("jhi_year, ");
            logger.warn("Mandatory field missing. Field name - jhi_year");
        }else {
            AcademicYear academicYear1 = new AcademicYear();
            academicYear1.setYear(academicYear);
            Optional<AcademicYear> ay = this.allRepositories.findRepository("academic_year").findOne(Example.of(academicYear1));
            if(ay.isPresent()) {
                obj.setAcademicyear(ay.get());
            }else {
                sb.append("jhi_year_id, ");
                logger.warn("AcademicYear not found. Given academicYear name : "+academicYear);
            }
        }

        if(sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg+sb.substring(0, sb.lastIndexOf(",")));
        }

        return (T) obj;
    }
}
