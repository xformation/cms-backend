package com.synectiks.cms.dataimport.loader;

import java.util.Optional;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.enumeration.BatchEnum;
import com.synectiks.cms.domain.enumeration.SectionEnum;
import com.synectiks.cms.exceptions.DuplicateRecordFoundException;
import com.synectiks.cms.exceptions.MandatoryFieldMissingException;
import com.synectiks.cms.service.util.CommonUtil;

public class SectionDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public SectionDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, DuplicateRecordFoundException, MandatoryFieldMissingException {
        StringBuilder sb = new StringBuilder();

        Section obj = CommonUtil.createCopyProperties(cls.newInstance(), Section.class);
        
        String sectionName = row.getCellAsString(0).orElse(null);
        if(!CommonUtil.isNullOrEmpty(sectionName)) {
        	SectionEnum se = findEnum(sectionName);
            if(se == null) {
            	sb.append("section, ");
                logger.warn("Mandatory field missing. Field name - section");
            }else {
            	obj.setSection(se);
            }
        }else {
        	sb.append("section, ");
            logger.warn("Mandatory field missing. Field name - section");
        }
        
        String batchName = row.getCellAsString(1).orElse(null);
        BatchEnum be = null;
        if(!CommonUtil.isNullOrEmpty(batchName)) {
        	be = findBatch(batchName);
        }else {
        	sb.append("batch, ");
            logger.warn("Mandatory field missing. Field name - batch");
        }
        
        String departmentName = row.getCellAsString(2).orElse(null);
    	String branchName = row.getCellAsString(3).orElse(null);
        String branchAddress = row.getCellAsString(4).orElse(null);
        
        if(be == null || CommonUtil.isNullOrEmpty(departmentName) || CommonUtil.isNullOrEmpty(branchName) || CommonUtil.isNullOrEmpty(branchAddress)) {
    		sb.append("batch, ");
            logger.warn("Mandatory field missing. Field name - batch");
        }else {
	        Branch branch = new Branch();
	        branch.setBranchName(branchName);
	        branch.address1(branchAddress);
	        Optional<Branch> b = this.allRepositories.findRepository("branch").findOne(Example.of(branch));
	        
	        if(b.isPresent()) {
	        	Department department = new Department();
	            department.setName(departmentName);
	            department.setBranch(b.get());
	            Optional<Department> dp = this.allRepositories.findRepository("department").findOne(Example.of(department));
	            if(dp.isPresent()) {
	            	Batch batch = new Batch();
	            	batch.setBatch(be);
	            	batch.setDepartment(dp.get());
	            	Optional<Batch> obt = this.allRepositories.findRepository("batch").findOne(Example.of(batch));
	                if(obt.isPresent()) {
	                	obj.setBatch(obt.get());
	                }
	            }else {
	                sb.append("department_id, ");
	                logger.warn("Department not found. Given department name : " + departmentName);
	            }
	        }else {
	        	sb.append("branch_id, ");
	            logger.warn("Either branch name or branch address not provided, Cannot identify that given department belongs to which branch");
	        }
            
        }
        
        if (sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg + sb.substring(0, sb.lastIndexOf(",")));
        }
        
        if (this.allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {
        	String msg = "Section and batch combination already exists";
        	sb.append(msg+",");
            logger.warn(msg);
            throw new DuplicateRecordFoundException(msg);
        }

        return (T)obj;
    }
    
    private SectionEnum findEnum(String sectionName) {
    	if(SectionEnum.A.toString().equalsIgnoreCase(sectionName)) {
    		return SectionEnum.A;
    	}else if(SectionEnum.B.toString().equalsIgnoreCase(sectionName)) {
    		return SectionEnum.B;
    	}else if(SectionEnum.C.toString().equalsIgnoreCase(sectionName)) {
    		return SectionEnum.C;
    	}else if(SectionEnum.D.toString().equalsIgnoreCase(sectionName)) {
    		return SectionEnum.D;
    	}
    	return null;
    }
    private BatchEnum findBatch(String batchName) {
    	if(BatchEnum.FIRSTYEAR.toString().equalsIgnoreCase(batchName)) {
    		return BatchEnum.FIRSTYEAR;
    	}else if(BatchEnum.SECONDYEAR.toString().equalsIgnoreCase(batchName)) {
    		return BatchEnum.SECONDYEAR;
    	}else if(BatchEnum.THIRDYEAR.toString().equalsIgnoreCase(batchName)) {
    		return BatchEnum.THIRDYEAR;
    	}else if(BatchEnum.FOURTHYEAR.toString().equalsIgnoreCase(batchName)) {
    		return BatchEnum.FOURTHYEAR;
    	}
    	return null;
    }
}
