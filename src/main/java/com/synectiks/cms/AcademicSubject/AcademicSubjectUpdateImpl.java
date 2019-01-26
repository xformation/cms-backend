package com.synectiks.cms.AcademicSubject;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AcademicSubjectUpdateImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<AcademicSubjectVo> updateAcademicSubjectData(UpdateAcademicSubjectInputPayload updateAcademicSubjectInputPayload) throws JSONException, ParseException {
        String[] subjectData = updateAcademicSubjectInputPayload.getSubjectData();

        String sql = "update subject set  subject_code= ?, subject_type=?,subject_desc=?,status=? where department_id = ? and batch_id = ? ";
        Query query1 = this.entityManager.createNativeQuery(sql);

        JSONObject jsonObj = null;
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
            query1.executeUpdate();

        }
     return (List<AcademicSubjectVo>) updateAcademicSubjectInputPayload;

    }
}
