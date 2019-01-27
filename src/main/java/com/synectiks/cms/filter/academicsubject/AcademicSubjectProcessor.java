package com.synectiks.cms.filter.academicsubject;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.domain.QueryResult;

@Repository
public class AcademicSubjectProcessor {

	private final static Logger logger = LoggerFactory.getLogger(Class.class);
	
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation=Propagation.REQUIRED)
    public QueryResult addAcademicSubjects(AcademicSubjectMutationPayload academicSubjectMutationPayload) throws JSONException, ParseException {
        String[] subjectData = academicSubjectMutationPayload.getSubjectData();

        String sql = "INSERT INTO subject( id, subject_code, subject_type, subject_desc, status, department_id, batch_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Query query1 = this.entityManager.createNativeQuery(sql);
        
        String sql2 = "INSERT INTO TEACH (id,jhi_desc,subject_id,teacher_id) VALUES ((select nextval('hibernate_sequence')),?,?,?)";
        Query query2 = this.entityManager.createNativeQuery(sql2);
        
        QueryResult res = new QueryResult();
        res.setStatusCode(0);
    	res.setStatusDesc("Records inserted successfully.");
    	
        JSONObject jsonObj = null;
        try {
        	for (String sub : subjectData) {
                sub = sub.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
                logger.debug(String.format("Array contents : %s",sub));
                int id = getId();
                jsonObj = new JSONObject(sub);
                query1.setParameter(1, id);
                query1.setParameter(2, jsonObj.getString("subjectCode"));
                query1.setParameter(3, jsonObj.getString("subjectType"));
                query1.setParameter(4, jsonObj.getString("subjectDesc"));
                query1.setParameter(5, jsonObj.getString("status"));
                query1.setParameter(6, Integer.parseInt(jsonObj.getString("departmentId")));
                query1.setParameter(7, Integer.parseInt(jsonObj.getString("batchId")));
                query1.executeUpdate();
                
                query2.setParameter(1, jsonObj.getString("subjectDesc"));
                query2.setParameter(2, id);
                query2.setParameter(3, Integer.parseInt(jsonObj.getString("teacherId")));
                query2.executeUpdate();
            }
        }catch(Exception e) {
        	logger.error("Exception. There is some error in inserting subject and teach records. ",e);
    		res.setStatusCode(1);
        	res.setStatusDesc("There is some error in inserting subject and teach records.");
        }
        
       return res;
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public QueryResult updateAcademicSubjects(AcademicSubjectMutationPayload academicSubjectMutationPayload) throws JSONException, ParseException {
        
    	String[] subjectData = academicSubjectMutationPayload.getSubjectData();
        
        String sql = "update subject set  subject_code= ?, subject_type=?, subject_desc=?, status=?, department_id = ?, batch_id = ? where id = ?";
        Query query1 = this.entityManager.createNativeQuery(sql);
        
        String sql2 = "update TEACH set teacher_id = ? where id = ?";
        Query query2 = this.entityManager.createNativeQuery(sql2);

        JSONObject jsonObj = null;
        QueryResult res = new QueryResult();
        res.setStatusCode(0);
    	res.setStatusDesc("Records updated successfully.");
    	try {
    		for (String sub : subjectData) {
                sub = sub.replaceAll("\\{", "\\{\"").replaceAll("=", "\":\"").replaceAll(",", "\",\"").replaceAll(" ", "").replaceAll("\\}", "\"\\}");
                System.out.println("Array contents : " + sub);
                jsonObj = new JSONObject(sub);
                query1.setParameter(1, jsonObj.getString("subjectCode"));
                query1.setParameter(2, jsonObj.getString("subjectType"));
                query1.setParameter(3, jsonObj.getString("subjectDesc"));
                query1.setParameter(4, jsonObj.getString("status"));
                query1.setParameter(5, Integer.parseInt(jsonObj.getString("departmentId")));
                query1.setParameter(6, Integer.parseInt(jsonObj.getString("batchId")));
                query1.setParameter(7, Integer.parseInt(jsonObj.getString("subjectId")));
                query1.executeUpdate();
                
                query2.setParameter(1, Integer.parseInt(jsonObj.getString("teacherId")));
                query2.setParameter(2, Integer.parseInt(jsonObj.getString("teachId")));
                query2.executeUpdate();
            }
    	}catch(Exception e) {
        	logger.error("Exception. There is some error in updating subject and teach records. ",e);
    		res.setStatusCode(1);
        	res.setStatusDesc("There is some error in updating subject and teach records.");
        }
        
     return res;

    }
    
    private int getId() {
    	String sql= "select nextval('hibernate_sequence')";
    	Query query = this.entityManager.createNativeQuery(sql);
    	Integer obj = ((BigInteger)query.getSingleResult()).intValue();
    	return obj.intValue();
    }
    
    public List<AcademicSubjectVo> getAcademicSubjects(AcademicSubjectQueryPayload academicSubjectQueryPayload) {
        String selectQry =  "select sb.id, sb.subject_code, sb.subject_type, sb.subject_desc, sb.status, sb.department_id, sb.batch_id, tc.teacher_id from subject sb, department dt, teach tc " + 
			        		"where sb.department_id = dt.id and sb.id = tc.subject_id " + 
			        		"and sb.department_id = ? and dt.id = ? and dt.branch_id = ? and sb.batch_id = ?";
        Query query = this.entityManager.createNativeQuery(selectQry);

        query.setParameter(1, academicSubjectQueryPayload.getDepartmentId());
        query.setParameter(2, academicSubjectQueryPayload.getDepartmentId());
        query.setParameter(3, academicSubjectQueryPayload.getBranchId());
        query.setParameter(4, academicSubjectQueryPayload.getBatchId());

        List<AcademicSubjectVo> resultList = executeSelectQuery(query);
        return resultList;
    }

    private List<AcademicSubjectVo> executeSelectQuery(Query query) {
        List<AcademicSubjectVo> resultList = new ArrayList<AcademicSubjectVo>();
        List<Object[]> ls = query.getResultList();
        for (Object[] result : ls){
            AcademicSubjectVo as = new AcademicSubjectVo();
            as.setId(((BigInteger)result[0]).intValue());
            as.setSubjectCode((String)result[1]);
            as.setSubjectType((String)result[2]);
            as.setSubjectDesc((String)result[3]);
            as.setStatus((String)result[4]);
            as.setDepartmentId(((BigInteger)result[5]).intValue());
            as.setBatchId(((BigInteger)result[6]).intValue());
            as.setTeacherId(((BigInteger)result[7]).intValue());
            resultList.add(as);
        }
        return resultList;
    }
}
