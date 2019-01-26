package com.synectiks.cms.AcademicSubject;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AcademicSubjectGetImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<AcademicSubjectVo> getAcademicSubject(AcademicSubjectInput academicSubjectInput) {
        String selectQry = "select su.id, su.subject_code, su.subject_type ,su.subject_desc,su.status " +
            "from subject su, department de, batch ba " +
            "where de.id = su.department_id and su.batch_id = ba.id ";


        Query query = this.entityManager.createNativeQuery(selectQry);

        if(academicSubjectInput != null) {
//            int i=0;
            query.setParameter(1, Integer.parseInt(academicSubjectInput.getDepartmentId()));
            query.setParameter(2, Integer.parseInt(academicSubjectInput.getBatchId()));

        }


        List<AcademicSubjectVo> resultList = executeSelectQuery(query);
        return resultList;
    }

    private List<AcademicSubjectVo> executeSelectQuery(Query query) {
        List<AcademicSubjectVo> resultList = new ArrayList<AcademicSubjectVo>();

        List<Object[]> ls = query.getResultList();
        for (Object[] result : ls){
            AcademicSubjectVo as = new AcademicSubjectVo();
            as.setSubjectCode((String)result[0]);
            as.setSubjectType((String)result[1]);
            as.setSubjectDesc((String)result[2]);
            as.setStatus((String)result[3]);
            resultList.add(as);
        }
        return resultList;
    }
}
