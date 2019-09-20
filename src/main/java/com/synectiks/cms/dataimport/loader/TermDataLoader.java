package com.synectiks.cms.dataimport.loader;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.Term;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.exceptions.AdditionalTermFoundException;
import com.synectiks.cms.exceptions.MandatoryFieldMissingException;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import org.apache.poi.ss.formula.functions.T;
import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import java.util.Optional;

public class TermDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public TermDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, MandatoryFieldMissingException, AdditionalTermFoundException {
        StringBuilder sb = new StringBuilder();
        Term obj = CommonUtil.createCopyProperties(cls.newInstance(), Term.class);

        obj.setTermStatus(Status.ACTIVE);
        if(!this.allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {

            String termDesc = row.getCellAsString(0).orElse(null);
            if (CommonUtil.isNullOrEmpty(termDesc)) {
                sb.append("term_desc, ");
                logger.warn("Mandatory field missing. Field name - term_desc");
            } else {
                obj.setTermsDesc(termDesc);
            }

            String startDate = row.getCellAsString(1).orElse(null);
            if (CommonUtil.isNullOrEmpty(startDate)) {
                sb.append("start_date, ");
                logger.warn("Mandatory field missing. Field name - start_date");
            } else {
                obj.startDate(DateFormatUtil.convertStringToLocalDate(startDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }

            String endDate = row.getCellAsString(2).orElse(null);
            if (CommonUtil.isNullOrEmpty(endDate)) {
                sb.append("end_date, ");
                logger.warn("Mandatory field missing. Field name - end_date");
            } else {
                obj.endDate(DateFormatUtil.convertStringToLocalDate(endDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }

            String termStatus = row.getCellAsString(3).orElse(null);
            if (CommonUtil.isNullOrEmpty(termStatus)) {
                sb.append("status, ");
                logger.warn("Mandatory field missing. Field name - term_status");
            } else {
                if (Status.ACTIVE.toString().equalsIgnoreCase(termStatus)) {
                    obj.setTermStatus(Status.ACTIVE);
                } else {
                    obj.setTermStatus(Status.DEACTIVE);
                }
            }
        }else {
            sb.append("Application already have an active term status, ");
            logger.warn("Application already have an active term status");
            if (sb.length() > 0) {
                String msg = "Application already have an active term status";
                throw new AdditionalTermFoundException(msg);
            }
        }

        String academicYear = row.getCellAsString(4).orElse(null);
        if(CommonUtil.isNullOrEmpty(academicYear)) {
            sb.append("academic_year_id, ");
            logger.warn("Mandatory field missing. Field name - academic_year_id");
        }else {
            AcademicYear academicYear1 = new AcademicYear();
            academicYear1.setYear(academicYear);
            Optional<AcademicYear> ay = this.allRepositories.findRepository("academic_year").findOne(Example.of(academicYear1));
            if(ay.isPresent()) {
                obj.setAcademicyear(ay.get());
            }else {
                sb.append("academic_year_id, ");
                logger.warn("AcademicYear not found. Given academicYear name : "+academicYear);
            }
        }

        if(sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg+sb.substring(0, sb.lastIndexOf(",")));
        }

        return  (T) obj;
    }
}
