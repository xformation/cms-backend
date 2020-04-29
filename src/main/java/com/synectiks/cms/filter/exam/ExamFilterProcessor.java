package com.synectiks.cms.filter.exam;

import com.synectiks.cms.business.service.exam.ExamService;
import com.synectiks.cms.domain.AcademicExamSetting;
import com.synectiks.cms.domain.CmsAcademicExamSettingVo;
import com.synectiks.cms.domain.enumeration.SemesterEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamFilterProcessor {


    @Autowired
    private ExamService examService;

    public List<CmsAcademicExamSettingVo> searchAcademicExamSetting(Long departmentId, Long batchId, Long sectionId, Long subjectId, Long branchId, SemesterEnum semester, String examName){
        return examService.searchAcademicExamSetting(departmentId, batchId, sectionId, subjectId, branchId, semester, examName);

    }

    public List<CmsAcademicExamSettingVo> searchAcademicExamSetting(ExamListFilterInput filter){
        return examService.searchAcademicExamSetting(filter);
    }
//    public List<StudentExamReport> searchSubjectandStudents(ExamReportFilterInput filter){
//        return examService.searchSubjectandStudents(filter);
//    }

}

