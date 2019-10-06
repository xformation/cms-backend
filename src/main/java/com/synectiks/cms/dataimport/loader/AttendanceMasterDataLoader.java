package com.synectiks.cms.dataimport.loader;

import java.util.Optional;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.domain.AttendanceMaster;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.domain.enumeration.BatchEnum;
import com.synectiks.cms.domain.enumeration.SectionEnum;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.SubTypeEnum;
import com.synectiks.cms.exceptions.DataNotFoundException;
import com.synectiks.cms.exceptions.DuplicationRecordFoundException;
import com.synectiks.cms.exceptions.MandatoryFieldMissingException;
import com.synectiks.cms.service.util.CommonUtil;

public class AttendanceMasterDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public AttendanceMasterDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, DuplicationRecordFoundException, MandatoryFieldMissingException, DataNotFoundException {
        StringBuilder sb = new StringBuilder();

        AttendanceMaster obj = CommonUtil.createCopyProperties(cls.newInstance(), AttendanceMaster.class);
        Batch batch = new Batch();
        Subject subject = new Subject();
        
        String subjectCode = row.getCellAsString(0).orElse(null);
        if(CommonUtil.isNullOrEmpty(subjectCode)) {
        	sb.append("subject_code, ");
            logger.warn("Mandatory field missing. Field name - subject_code");
        }else {
        	subject.setSubjectCode(subjectCode);
        }
        
        String subjectType = row.getCellAsString(1).orElse(null);
        if(CommonUtil.isNullOrEmpty(subjectType)) {
        	sb.append("subject_type, ");
            logger.warn("Mandatory field missing. Field name - subject_type");
        }else {
        	if(SubTypeEnum.COMMON.toString().equalsIgnoreCase(subjectType)) {
        		subject.setSubjectType(SubTypeEnum.COMMON);
        	}else if(SubTypeEnum.ELECTIVE.toString().equalsIgnoreCase(subjectType)) {
        		subject.setSubjectType(SubTypeEnum.ELECTIVE);
        	}else {
        		sb.append("subject_type, ");
                logger.warn("Subject type not listed in the system. Field name - subject_type");
        	}
        }
        
        String subjectDesc = row.getCellAsString(2).orElse(null);
        if(CommonUtil.isNullOrEmpty(subjectDesc)) {
        	sb.append("subject_desc, ");
            logger.warn("Mandatory field missing. Field name - subject_desc");
        }else {
        	subject.setSubjectDesc(subjectDesc);
        }
        
        String status = row.getCellAsString(3).orElse(null);
        if(CommonUtil.isNullOrEmpty(status)) {
        	sb.append("status, ");
            logger.warn("Mandatory field missing. Field name - status");
        }else {
        	if(Status.ACTIVE.toString().equalsIgnoreCase(status)) {
        		subject.setStatus(Status.ACTIVE);
        	}else if(Status.DEACTIVE.toString().equalsIgnoreCase(status)) {
        		subject.setStatus(Status.DEACTIVE);
        	}else if(Status.DRAFT.toString().equalsIgnoreCase(status)) {
        		subject.setStatus(Status.DRAFT);
        	}else {
        		sb.append("status, ");
                logger.warn("Given status not listed. Field name - status");
        	}
        }
        
        
        String branchName = row.getCellAsString(5).orElse(null);
        String branchAddress = row.getCellAsString(6).orElse(null);
        Optional<Branch> b = null;
        if(!CommonUtil.isNullOrEmpty(branchName) && !CommonUtil.isNullOrEmpty(branchAddress)) {
//        	sb.append("branch_id, ");
//            logger.warn("branch name or branch address not provided, Cannot find the branch");
//        }else {
            Branch branch = new Branch();
            branch.setBranchName(branchName);
            branch.address1(branchAddress);
            b = this.allRepositories.findRepository("branch").findOne(Example.of(branch));
        }
        
        String departmentName = row.getCellAsString(4).orElse(null);
        if(!CommonUtil.isNullOrEmpty(departmentName)) {
//            sb.append("department_id, ");
//            logger.warn("Mandatory field missing. Field name - department_id");
//        }else {
        	if(b != null && b.isPresent()) {
            	Department department = new Department();
                department.setName(departmentName);
                department.setBranch(b.get());
                Optional<Department> dp = this.allRepositories.findRepository("department").findOne(Example.of(department));
                if(dp.isPresent()) {
                    subject.setDepartment(dp.get());
                    batch.setDepartment(dp.get());
                }
//                else {
//                    sb.append("department_id, ");
//                    logger.warn("Department not found. Given department name : " + departmentName);
//                }
            }
//        	else {
//            	sb.append("department_id, ");
//                logger.warn("Either branch name or branch address not provided, Cannot identify that given department belongs to which branch");
//            }
        }
        
        Optional<Batch> obatch = null;
        String batchName = row.getCellAsString(7).orElse(null);
        if(!CommonUtil.isNullOrEmpty(batchName)) {
//        	sb.append("batch_id, ");
//            logger.warn("Mandatory field missing. Field name - batch_id");
//        }else {
        	if(BatchEnum.FIRSTYEAR.toString().equalsIgnoreCase(batchName)) {
        		batch.setBatch(BatchEnum.FIRSTYEAR);
        	}else if(BatchEnum.SECONDYEAR.toString().equalsIgnoreCase(batchName)) {
        		batch.setBatch(BatchEnum.SECONDYEAR);
        	}else if(BatchEnum.THIRDYEAR.toString().equalsIgnoreCase(batchName)) {
        		batch.setBatch(BatchEnum.THIRDYEAR);
        	}else if(BatchEnum.FOURTHYEAR.toString().equalsIgnoreCase(batchName)) {
        		batch.setBatch(BatchEnum.FOURTHYEAR);
        	}else {
//        		sb.append("batch_id, ");
                logger.warn("Given batch/year not listed. batch/year - "+batchName);
        	}
        	if(batch.getDepartment() != null && batch.getBatch() != null) {
//        		sb.append("batch_id, ");
//                logger.warn("Mandatory field missing. Field name - batch_id");
//        	}else {
        		 obatch = this.allRepositories.findRepository("batch").findOne(Example.of(batch));
        		 if(obatch != null && obatch.isPresent()) {
        			 subject.setBatch(obatch.get());
        			 obj.setBatch(obatch.get());
        		 }
        	}
        }
        
        if (sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg + sb.substring(0, sb.lastIndexOf(",")));
        }
        
        if(!this.allRepositories.findRepository("subject").exists(Example.of(subject))) {
        	logger.info("Saving subject record.");
        	subject = (Subject)this.allRepositories.findRepository("subject").save(subject);
        	logger.info("Subject saved successfully. Subject : "+subject);
        }else {
        	logger.warn("Duplicate subject found. Discarding the subject.");
        }
        
        Optional<Teacher> oth = null;
        String teacherEmailAddress = row.getCellAsString(8).orElse(null);
        if(!CommonUtil.isNullOrEmpty(teacherEmailAddress)) {
//        	sb.append("teacher_email_address, ");
//            logger.warn("Mandatory field missing. Field name - teacher_email_address. Needed to assign teacher to respective subject");
//        }else {
        	Teacher th = new Teacher();
        	th.setTeacherEmailAddress(teacherEmailAddress);
        	oth = this.allRepositories.findRepository("teacher").findOne(Example.of(th));
        }
        
        String sectionName = row.getCellAsString(9).orElse(null);
        if(oth != null && oth.isPresent()) {
	        Teach teach = new Teach();
			teach.setSubject(subject);
    		teach.setTeacher(oth.get());
    		if(CommonUtil.isNullOrEmpty(sectionName)) {
	        	if(!this.allRepositories.findRepository("teach").exists(Example.of(teach)) && obj.getBatch() != null) {
	        		teach.setDesc(oth.get().getTeacherName() + " is teaching "+subject.getSubjectDesc());
	    			teach = (Teach)this.allRepositories.findRepository("teach").save(teach);
	    			obj.setTeach(teach);
	    		}
        	}else {
        		if(obj.getBatch() != null) {
                	Section section = new Section();
                	if(SectionEnum.A.toString().equalsIgnoreCase(sectionName)) {
                		section.setSection(SectionEnum.A);
                	}else if(SectionEnum.B.toString().equalsIgnoreCase(sectionName)) {
                		section.setSection(SectionEnum.B);
                	}if(SectionEnum.C.toString().equalsIgnoreCase(sectionName)) {
                		section.setSection(SectionEnum.C);
                	}if(SectionEnum.D.toString().equalsIgnoreCase(sectionName)) {
                		section.setSection(SectionEnum.D);
                	}
                    section.setBatch(obatch.get());
                    Optional<Section> osc = this.allRepositories.findRepository("section").findOne(Example.of(section));
                    if(osc.isPresent()) {
                    	obj.setSection(osc.get());
                    	Optional<Teach> oteach = this.allRepositories.findRepository("teach").findOne(Example.of(teach));
                    	obj.setTeach(oteach.isPresent() ? oteach.get() : null);
                    	if(!this.allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {
                    		teach.setDesc(oth.get().getTeacherName() + " is teaching "+subject.getSubjectDesc() +" in section "+sectionName);
        	    			teach = (Teach)this.allRepositories.findRepository("teach").save(teach);
        	    			obj.setTeach(teach);
                    	}else {
                    		logger.warn("Duplicate record in attendance master. Discarding the record.");
                    		String msg = "Duplicate attendance master found";
                        	sb.append(msg+",");
                            logger.warn(msg);
                            throw new DuplicationRecordFoundException(msg);
                    	}
                    }else {
                    	sb.append("section_id, ");
                    	String msg = "Field name - ";
                        logger.warn("Section given in file is not listed in database. Section : "+sectionName+". Field name - section_id");
                        throw new DataNotFoundException(msg + sb.substring(0, sb.lastIndexOf(",")));
                    }
                }
        	}
    	}
        
        if(obj.getTeach() == null) {
        	sb.append("teach_id, ");
            logger.warn("Mandatory field missing. Field name - teach_id");
        }
        if(obj.getBatch() == null) {
        	sb.append("batch_id, ");
            logger.warn("Mandatory field missing. Field name - batch_id");
        }
        
        if (sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg + sb.substring(0, sb.lastIndexOf(",")));
        }
        
        if(this.allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {
        	String msg = "Duplicate attendance master found";
        	sb.append(msg+",");
            logger.warn(msg);
            if (sb.length() > 0) {
                throw new DuplicationRecordFoundException(msg);
            }
        }
        
        String desc = "Teacher "+oth.get().getTeacherName()+" is the attendance master of subject "+subject.getSubjectDesc();
        if(obj.getSection() != null) {
        	desc = desc + " and section "+obj.getSection().getSection().toString();
        }
        obj.setDesc(desc);
        return (T)obj;
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
