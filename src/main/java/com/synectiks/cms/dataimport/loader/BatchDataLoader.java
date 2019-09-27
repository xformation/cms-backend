package com.synectiks.cms.dataimport.loader;

import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.enumeration.BatchEnum;
import com.synectiks.cms.exceptions.AdditionalBatchFoundException;
import com.synectiks.cms.exceptions.MandatoryFieldMissingException;
import com.synectiks.cms.service.util.CommonUtil;
import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import java.util.Optional;

public class BatchDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public BatchDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, AdditionalBatchFoundException , MandatoryFieldMissingException {
        StringBuilder sb = new StringBuilder();

        Batch obj = CommonUtil.createCopyProperties(cls.newInstance(), Batch.class);

        obj.setBatch(BatchEnum.FIRSTYEAR);
        if (!this.allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {

            String batch = row.getCellAsString(0).orElse(null);
            if (CommonUtil.isNullOrEmpty(batch)) {
                sb.append("batch, ");
                logger.warn("Mandatory field missing. Field name - batch");
            } else {
                if (BatchEnum.FIRSTYEAR.toString().equalsIgnoreCase(batch)) {
                    obj.setBatch(BatchEnum.FIRSTYEAR);
                } else {
                    if (BatchEnum.SECONDYEAR.toString().equalsIgnoreCase(batch)) {
                        obj.setBatch(BatchEnum.SECONDYEAR);
                    } else {
                        if (BatchEnum.THIRDYEAR.toString().equalsIgnoreCase(batch)) {
                            obj.setBatch(BatchEnum.THIRDYEAR);
                        } else {
                            if (BatchEnum.FOURTHYEAR.toString().equalsIgnoreCase(batch)) {
                                obj.setBatch((BatchEnum.FOURTHYEAR));
                            }
                        }
                    }
                }
            }
        } else {
            sb.append("Application already have an active batch, ");
            logger.warn("Application already have an active batch");
            if (sb.length() > 0) {
                String msg = "Application already have an active batch";
                throw new AdditionalBatchFoundException(msg);
            }
        }

        String name = row.getCellAsString(1).orElse(null);
        if(CommonUtil.isNullOrEmpty(name)) {
            sb.append("department_id, ");
            logger.warn("Mandatory field missing. Field name - department_id");
        }else {
            Department department = new Department();
            department.setName(name);
            Optional<Department> dp = this.allRepositories.findRepository("department").findOne(Example.of(department));
            if(dp.isPresent()) {
                obj.setDepartment(dp.get());
            }else {
                sb.append("department_id, ");
                logger.warn("Department not found. Given department name : " + name);
            }
        }

        if (sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg + sb.substring(0, sb.lastIndexOf(",")));
        }

        return (T)obj;
    }
}
