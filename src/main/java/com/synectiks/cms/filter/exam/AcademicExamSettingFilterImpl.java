package com.synectiks.cms.filter.exam;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.synectiks.commons.entities.cms.*;
import com.synectiks.cms.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.commons.entities.cms.enumeration.AttendanceStatusEnum;
import com.synectiks.cms.service.util.DateFormatUtil;



@Repository
public class AcademicExamSettingFilterImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AcademicYearRepository academicYearRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    BatchRepository batchRepository;

    @Autowired
    AcademicExamSettingRepository academicExamSettingRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public QueryResult updateExam(List<AcademicExamSettingUpdateFilter> list) {
        logger.info("Start updating Exam  data ");
        QueryResult result = new QueryResult();
        result.setStatusCode(0);
        result.setStatusDesc(CmsConstants.UPDATE_SUCCESS_MESSAGE);

        AcademicExamSetting stObj = new AcademicExamSetting();
        Subject subject = null;
        try {
            for(AcademicExamSettingUpdateFilter sa: list) {
                String values = sa.getSubjectIds();//.split("##delimline##");


                String data[] = values.split("#~#");
                subject = this.subjectRepository.findById(Long.valueOf(data[0])).get();

                stObj.setSubject(subject);

                Example<AcademicExamSetting> example = Example.of(stObj);
                Optional<AcademicExamSetting> osa = this.academicExamSettingRepository.findOne((example));
                if(osa.isPresent()) {
                    stObj.setId(osa.get().getId());
                    stObj.setExamName(osa.get().getExamName());
                    stObj.setSubject(osa.get().getSubject());
                    stObj.setStartTime(osa.get().getStartTime());
                    stObj.setEndTime(osa.get().getEndTime());
                    stObj.setTotal(osa.get().getTotal());
                    stObj.setPassing(osa.get().getPassing());
                }
//
            }
        }catch(Exception e) {
            logger.error("Method updateExamStatus. "+CmsConstants.UPDATE_FAILURE_MESSAGE, e);
            result.setStatusCode(1);
            result.setStatusDesc(CmsConstants.UPDATE_FAILURE_MESSAGE);
        }

        return result;
    }

    public List<DailyExamVo> getExamDataForAdmin(ExamListFilterInput filter) throws Exception {

        Branch branch = new Branch();
        branch.setId(Long.valueOf(filter.getBranchId()));
        Department department = new Department();
        department.setId(Long.valueOf(filter.getDepartmentId()));
        Batch batch = new Batch();
        batch.setId(Long.valueOf(filter.getBatchId()));
        Section section = new Section();
        section.setId(Long.valueOf(filter.getSectionId()));

        List<DailyExamVo> voList = new ArrayList<>();
        List<Subject> subjectList = getSubjectListByNativeQuery(department, batch);

        return voList;
    }
    private List<Subject> getSubjectListByNativeQuery(Department department, Batch batch){
        String sql = "select id, subject_code, subject_desc from subject where department_id = ? and batch_id = ?  ";
        Query query = this.entityManager.createNativeQuery(sql);

        query.setParameter(1, department.getId());
        query.setParameter(2, batch.getId());

        List<Object[]> ls = query.getResultList();

        List<Subject> subjecttList = new ArrayList<>();
        for (Object[] result : ls){
            Subject subject = new Subject();
            subject.setId(((BigInteger)result[0]).longValue() );
            subject.setSubjectCode((String)result[1]);
            subject.setSubjectDesc((String)result[2]);
            subjecttList.add(subject);
        }

        return subjecttList;
    }


}
