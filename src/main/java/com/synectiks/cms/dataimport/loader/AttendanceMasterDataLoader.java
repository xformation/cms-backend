package com.synectiks.cms.dataimport.loader;

import java.util.Optional;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.entities.AttendanceMaster;
import com.synectiks.cms.entities.Batch;
import com.synectiks.cms.entities.Branch;
import com.synectiks.cms.entities.Department;
import com.synectiks.cms.entities.Section;
import com.synectiks.cms.entities.Subject;
import com.synectiks.cms.entities.Teach;
import com.synectiks.cms.entities.Teacher;
import com.synectiks.cms.entities.enumeration.BatchEnum;
import com.synectiks.cms.entities.enumeration.SectionEnum;
import com.synectiks.cms.entities.enumeration.Status;
import com.synectiks.cms.entities.enumeration.SubTypeEnum;
import com.synectiks.cms.exceptions.DataNotFoundException;
import com.synectiks.cms.exceptions.DuplicateRecordFoundException;
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
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, DuplicateRecordFoundException, MandatoryFieldMissingException, DataNotFoundException {
        StringBuilder sb = new StringBuilder();

        AttendanceMaster obj = CommonUtil.createCopyProperties(cls.newInstance(), AttendanceMaster.class);
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
        if(CommonUtil.isNullOrEmpty(branchName) || CommonUtil.isNullOrEmpty(branchAddress)) {
        	sb.append("branch_id, ");
            logger.warn("Mandatory field missing. Branch name or branch address not provided, Cannot find the branch");
        }else {
            Branch branch = new Branch();
            branch.setBranchName(branchName);
            branch.address1(branchAddress);
            b = this.allRepositories.findRepository("branch").findOne(Example.of(branch));
            if(!b.isPresent()) {
            	sb.append("branch_id, ");
                logger.warn("Mandatory field missing. Branch not found");
            }
        }
        
        if (sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg + sb.substring(0, sb.lastIndexOf(",")));
        }
        
        Optional<Department> dp = null;
        String departmentName = row.getCellAsString(4).orElse(null);
        if(CommonUtil.isNullOrEmpty(departmentName)) {
        	sb.append("department, ");
            logger.warn("Mandatory field missing. Field name - departmentName");
        }else {
        	Department department = new Department();
            department.setName(departmentName);
            department.setBranch(b.get());
            dp = this.allRepositories.findRepository("department").findOne(Example.of(department));
            if(!dp.isPresent()) {
            	sb.append("department, ");
                logger.warn("Mandatory field missing. Field name - departmentName");
            }
        }
        if (sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg + sb.substring(0, sb.lastIndexOf(",")));
        }
        
        Batch batch = new Batch();
        Optional<Batch> obatch = null;
        String batchName = row.getCellAsString(7).orElse(null);
        if(CommonUtil.isNullOrEmpty(batchName)) {
        	sb.append("batch_id, ");
            logger.warn("Mandatory field missing. Field name - batch_id");
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg + sb.substring(0, sb.lastIndexOf(",")));
        }else {
        	if(BatchEnum.FIRSTYEAR.toString().equalsIgnoreCase(batchName)) {
        		batch.setBatch(BatchEnum.FIRSTYEAR);
        	}else if(BatchEnum.SECONDYEAR.toString().equalsIgnoreCase(batchName)) {
        		batch.setBatch(BatchEnum.SECONDYEAR);
        	}else if(BatchEnum.THIRDYEAR.toString().equalsIgnoreCase(batchName)) {
        		batch.setBatch(BatchEnum.THIRDYEAR);
        	}else if(BatchEnum.FOURTHYEAR.toString().equalsIgnoreCase(batchName)) {
        		batch.setBatch(BatchEnum.FOURTHYEAR);
        	}else {
        		sb.append("batch_id, ");
                logger.warn("Given batch/year not listed. batch/year - "+batchName);
                String msg = "Field name - ";
                throw new DataNotFoundException(msg + sb.substring(0, sb.lastIndexOf(",")));
        	}
        	
        	batch.setDepartment(dp.get());
        	obatch = this.allRepositories.findRepository("batch").findOne(Example.of(batch));
        	if(!obatch.isPresent()) {
        		sb.append("batch, ");
                logger.warn("Given batch is missing. Field name - batch");
                String msg = "Field name - ";
                throw new DataNotFoundException(msg + sb.substring(0, sb.lastIndexOf(",")));
        	}
        }
        
        
        subject.setDepartment(dp.get());
        subject.setBatch(obatch.get());
        Optional<Subject> osub = null;
        if(!this.allRepositories.findRepository("subject").exists(Example.of(subject))) {
        	logger.info("Saving subject record.");
        	subject = (Subject)this.allRepositories.findRepository("subject").save(subject);
        	logger.info("Subject saved successfully. Subject : "+subject);
        }else {
        	logger.warn("Duplicate subject found. Discarding the subject.");
        	osub = this.allRepositories.findRepository("subject").findOne(Example.of(subject));
        	subject = osub.get();
        }
        
        Optional<Teacher> oth = null;
        String teacherEmailAddress = row.getCellAsString(8).orElse(null);
        if(CommonUtil.isNullOrEmpty(teacherEmailAddress)) {
        	sb.append("teacher_email_address, ");
            logger.warn("Mandatory field missing. Field name - teacher_email_address. Needed to assign teacher to respective subject");
        }else {
        	Teacher th = new Teacher();
        	th.setTeacherEmailAddress(teacherEmailAddress);
        	th.setDepartment(dp.get());
        	oth = this.allRepositories.findRepository("teacher").findOne(Example.of(th));
        	if(!oth.isPresent()) {
        		sb.append("teacher_email_address, ");
                logger.warn("Mandatory field missing. Field name - teacher_email_address. Needed to assign teacher to respective subject");
        	}
        }
        if (sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg + sb.substring(0, sb.lastIndexOf(",")));
        }
        
        Teach teach = new Teach();
		teach.setSubject(subject);
		teach.setTeacher(oth.get());
		if(!this.allRepositories.findRepository("teach").exists(Example.of(teach))) {
			teach.setDesc(subject.getSubjectCode());
			teach = (Teach)this.allRepositories.findRepository("teach").save(teach);
		}else {
			logger.debug("Teach alrady exists. Returning the reference of existing teach");
			teach = (Teach)this.allRepositories.findRepository("teach").findOne(Example.of(teach)).get();
		}
		
		Optional<Section> osc = null;
		Section section = new Section();
        String sectionName = row.getCellAsString(9).orElse(null);
        if(!CommonUtil.isNullOrEmpty(sectionName)) {
        	if(SectionEnum.A.toString().equalsIgnoreCase(sectionName)) {
        		section.setSection(SectionEnum.A);
        	}else if(SectionEnum.B.toString().equalsIgnoreCase(sectionName)) {
        		section.setSection(SectionEnum.B);
        	}else if(SectionEnum.C.toString().equalsIgnoreCase(sectionName)) {
        		section.setSection(SectionEnum.C);
        	}else if(SectionEnum.D.toString().equalsIgnoreCase(sectionName)) {
        		section.setSection(SectionEnum.D);
        	}else {
        		logger.warn("Given section not listed. Section - "+sectionName);
        		sb.append("section, ");
                String msg = "Field name - ";
                throw new DataNotFoundException(msg + sb.substring(0, sb.lastIndexOf(",")));
        	}
        	if(section.getSection() != null) {
            	section.setBatch(obatch.get());
            	osc = this.allRepositories.findRepository("section").findOne(Example.of(section));
            	if(osc.isPresent()) {
            		obj.setSection(osc.get());
            	}else {
            		sb.append("section, ");
                    logger.warn("Given section is missing. Field name - section. "+sectionName);
                    String msg = "Field name - ";
                    throw new DataNotFoundException(msg + sb.substring(0, sb.lastIndexOf(",")));
            	}
            }
    	}
        
        obj.setBatch(obatch.get());
        obj.setTeach(teach);
        
        if(this.allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {
        	String msg = "Duplicate attendance master found";
        	sb.append(msg+",");
            logger.warn(msg);
            if (sb.length() > 0) {
                throw new DuplicateRecordFoundException(msg);
            }
        }
        
////        String desc = "Teacher "+oth.get().getTeacherName()+" is the attendance master of subject "+subject.getSubjectDesc();
//        if(obj.getSection() != null) {
//        	desc = desc + " and section "+obj.getSection().getSection().toString();
//        }
//        obj.setDesc(desc);
        return (T)obj;
    }
    
   
    
    
}
