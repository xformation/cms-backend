package com.synectiks.cms.business.service;

import java.text.ParseException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.QueryResult;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.SubTypeEnum;
import com.synectiks.cms.filter.academicsubject.AcademicSubjectMutationPayload;
import com.synectiks.cms.filter.academicsubject.AcademicSubjectQueryPayload;
import com.synectiks.cms.repository.SubjectRepository;
import com.synectiks.cms.repository.TeachRepository;

@Component
public class AcademicSubjectService {

private final static Logger logger = LoggerFactory.getLogger(Class.class);
	
    @Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private TeachRepository teachRepository;

	@Autowired
	CommonService commonService;
	
    @Transactional(propagation=Propagation.REQUIRED)
    public QueryResult insertSubjectAndTeachRecord(AcademicSubjectMutationPayload academicSubjectMutationPayload) throws JSONException, ParseException {
        String[] subjectData = academicSubjectMutationPayload.getSubjectData();
        QueryResult res = new QueryResult();
        res.setStatusCode(0);
    	res.setStatusDesc("Records inserted successfully.");
    	
        JSONObject jsonObj = null;
        try {
        	for (String sub : subjectData) {
                sub = sub.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
                logger.debug(String.format("Array contents : %s",sub));
                jsonObj = new JSONObject(sub);
                
                Subject subject = new Subject();
                subject.setSubjectCode(jsonObj.getString("subjectCode"));
                subject.setSubjectDesc(jsonObj.getString("subjectDesc"));
                // in case of null (default) subject type will be common
                if(SubTypeEnum.ELECTIVE.toString().equalsIgnoreCase(jsonObj.getString("subjectType"))) {
                	subject.setSubjectType(SubTypeEnum.ELECTIVE);
                }else {
                	subject.setSubjectType(SubTypeEnum.COMMON);
                }
                // in case of null (default) subject status will be active
                if(Status.DEACTIVE.toString().equalsIgnoreCase(jsonObj.getString("status"))) {
                	subject.setStatus(Status.DEACTIVE);
                }else {
                	subject.setStatus(Status.ACTIVE);
                }
                Department dept = commonService.getDepartmentById(Long.parseLong(jsonObj.getString("departmentId")));
                Batch bth = commonService.getBatchById(Long.parseLong(jsonObj.getString("batchId")));
                subject.setDepartment(dept);
                subject.setBatch(bth);
                subject = this.subjectRepository.save(subject);
                
                Teach teach = new Teach();
                teach.setDesc(jsonObj.getString("subjectDesc"));
                teach.setSubject(subject);
                Teacher teacher = commonService.getTeacherById(Long.parseLong(jsonObj.getString("teacherId")));
                teach.setTeacher(teacher);
                this.teachRepository.save(teach);

            }
        }catch(Exception e) {
        	logger.error("Exception. There is some error in inserting subject and teach records. ",e);
    		res.setStatusCode(1);
        	res.setStatusDesc("Error in inserting subject and teach records.");
        }
        
       return res;
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public QueryResult updateSubjectAndTeachRecord(AcademicSubjectMutationPayload academicSubjectMutationPayload) throws JSONException, ParseException {
        
    	String[] subjectData = academicSubjectMutationPayload.getSubjectData();
        JSONObject jsonObj = null;
        QueryResult res = new QueryResult();
        res.setStatusCode(0);
    	res.setStatusDesc("Records updated successfully.");
    	try {
    		for (String sub : subjectData) {
                sub = sub.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
                jsonObj = new JSONObject(sub);
                
                Subject subject = new Subject();
                subject.setId(Long.parseLong(jsonObj.getString("subjectId")));
                subject.setSubjectCode(jsonObj.getString("subjectCode"));
                subject.setSubjectDesc(jsonObj.getString("subjectDesc"));
                // While update, subject type must be provided by end user
                if(SubTypeEnum.ELECTIVE.toString().equalsIgnoreCase(jsonObj.getString("subjectType"))) {
                	subject.setSubjectType(SubTypeEnum.ELECTIVE);
                }else if(SubTypeEnum.COMMON.toString().equalsIgnoreCase(jsonObj.getString("subjectType"))){
                	subject.setSubjectType(SubTypeEnum.COMMON);
                }
                // While update, status must be provided by end user
                if(Status.DEACTIVE.toString().equalsIgnoreCase(jsonObj.getString("status"))) {
                	subject.setStatus(Status.DEACTIVE);
                }else if(Status.ACTIVE.toString().equalsIgnoreCase(jsonObj.getString("status"))){
                	subject.setStatus(Status.ACTIVE);
                }
                
                Department dept = commonService.getDepartmentById(Long.parseLong(jsonObj.getString("departmentId")));
                Batch bth = commonService.getBatchById(Long.parseLong(jsonObj.getString("batchId")));
                subject.setDepartment(dept);
                subject.setBatch(bth);
                subject = this.subjectRepository.save(subject);
                
                Teach teach = new Teach();
                teach.setId(Long.parseLong(jsonObj.getString("teachId")));
                Teacher teacher = commonService.getTeacherById(Long.parseLong(jsonObj.getString("teacherId")));
                teach.setTeacher(teacher);
                this.teachRepository.save(teach);
            }
    	}catch(Exception e) {
        	logger.error("Exception. There is some error in updating subject and teach records. ",e);
    		res.setStatusCode(1);
        	res.setStatusDesc("Error in updating subject and teach records.");
        }
        
     return res;

    }
    
    public List<Subject> getAcademicSubjects(AcademicSubjectQueryPayload academicSubjectQueryPayload) {
        List<Subject> subjectList = getSubjectList(Long.valueOf(academicSubjectQueryPayload.getDepartmentId()), 
        		Long.valueOf(academicSubjectQueryPayload.getBatchId()));
        return subjectList;
    }

    private List<Subject> getSubjectList(Long departmentId, Long batchId)  {
		Department department = this.commonService.getDepartmentById(departmentId);
		Batch batch = this.commonService.getBatchById(batchId);
		Subject sub = new Subject();
		sub.setDepartment(department);
		sub.setBatch(batch);
		Example<Subject> example = Example.of(sub);
		List<Subject> list = this.subjectRepository.findAll(example);
		return list;
	}
    

    
}
