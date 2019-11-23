package com.synectiks.cms.filter.academicsubject;

import java.text.ParseException;
import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synectiks.cms.business.service.AcademicSubjectService;
import com.synectiks.commons.entities.cms.QueryResult;
import com.synectiks.commons.entities.cms.Subject;

@Component
public class AcademicSubjectProcessor {

	private final static Logger logger = LoggerFactory.getLogger(Class.class);
	
	@Autowired
	private AcademicSubjectService academicSubjectService;
	
    public QueryResult addAcademicSubjects(AcademicSubjectMutationPayload academicSubjectMutationPayload) throws JSONException, ParseException {
    	logger.info("Adding subject and teach records.");
    	QueryResult res = this.academicSubjectService.insertSubjectAndTeachRecord(academicSubjectMutationPayload);
    	return res;
    }
    
    public QueryResult updateAcademicSubjects(AcademicSubjectMutationPayload academicSubjectMutationPayload) throws JSONException, ParseException {
    	logger.info("Updating subject and teach records.");
    	QueryResult res = this.academicSubjectService.updateSubjectAndTeachRecord(academicSubjectMutationPayload);
        return res;
    }
    
    public List<Subject> getAcademicSubjects(AcademicSubjectQueryPayload academicSubjectQueryPayload) {
    	logger.info("Getting subject records.");
    	List<Subject> resultList = this.academicSubjectService.getAcademicSubjects(academicSubjectQueryPayload);
        return resultList;
    }

  
}
