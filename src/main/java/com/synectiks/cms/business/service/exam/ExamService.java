package com.synectiks.cms.business.service.exam;
import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.AcademicExamSetting;
import com.synectiks.cms.domain.CmsAcademicExamSettingVo;
import com.synectiks.cms.domain.CmsVehicleVo;
import com.synectiks.cms.domain.Vehicle;
import com.synectiks.cms.domain.enumeration.SemesterEnum;
import com.synectiks.cms.filter.exam.ExamListFilterInput;
import com.synectiks.cms.repository.AcademicExamSettingRepository;
import com.synectiks.cms.repository.StudentExamReportRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
public class ExamService {
    @Autowired
    AcademicExamSettingRepository academicExamSettingRepository;
    @Autowired
    StudentExamReportRepository studentExamReportRepository;
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public List<CmsAcademicExamSettingVo> searchAcademicExamSetting(Long departmentId, Long batchId, Long sectionId, Long subjectId, Long branchId, SemesterEnum semester, String examName) {
        AcademicExamSetting acexm = new AcademicExamSetting();
        if (examName != null) {
            acexm.setExamName(examName);
        }
        if (departmentId != null) {
            acexm.setDepartmentId(departmentId);
        }
        if (batchId != null) {
            acexm.setBatchId(batchId);
        }
        if (sectionId != null) {
            acexm.setSectionId(sectionId);
        }
        if (branchId != null) {
            acexm.setBranchId(branchId);
        }
        if (subjectId != null) {
            acexm.setSubjectId(subjectId);
        }
        if(semester != null) {
            acexm.setSemester(semester);
        }
        Example<AcademicExamSetting> example = Example.of(acexm);
        List<AcademicExamSetting> list = this.academicExamSettingRepository.findAll(example);
        List<CmsAcademicExamSettingVo> ls = new ArrayList<>();
        for(AcademicExamSetting academicExamSetting: list) {
            CmsAcademicExamSettingVo vo = CommonUtil.createCopyProperties(academicExamSetting, CmsAcademicExamSettingVo.class);
            vo.setStrexamDate(DateFormatUtil.changeLocalDateFormat(academicExamSetting.getExamDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setExamDate(null);
            ls.add(vo);
        }
        return ls;
    }
    public List<CmsAcademicExamSettingVo> searchAcademicExamSetting(ExamListFilterInput filter) {
        AcademicExamSetting academicExamSetting = new AcademicExamSetting();
        if (!CommonUtil.isNullOrEmpty(filter.getDepartmentId())) {
//            Department department = this.commonService.getDepartmentById(Long.valueOf(filter.getDepartmentId()));
//            if (department != null) {
            academicExamSetting.setDepartmentId(Long.parseLong(filter.getDepartmentId()));
//            }
        }
        if (!CommonUtil.isNullOrEmpty(filter.getBatchId())) {
//            Batch batch = this.commonService.getBatchById(Long.valueOf(filter.getBatchId()));
//            if (batch != null) {
            academicExamSetting.setBatchId(Long.parseLong(filter.getBatchId()));
//            }
        }
        if (!CommonUtil.isNullOrEmpty(filter.getSectionId())) {
//            Section section = this.commonService.getSectionById(Long.valueOf(filter.getSectionId()));
//            if (section != null) {
            academicExamSetting.setSectionId(Long.parseLong(filter.getSectionId()));
//            }
        }
        if (!CommonUtil.isNullOrEmpty(filter.getBranchId())) {
//            Branch branch = this.commonService.getBranchById(Long.valueOf(filter.getBranchId()));
//            if (branch != null) {
            academicExamSetting.setBranchId(Long.parseLong(filter.getBranchId()));
//            }
        }
        if (!CommonUtil.isNullOrEmpty(filter.getSubjectId())) {
            academicExamSetting.setSubjectId(Long.parseLong(filter.getSubjectId()));
        }
//        if (!CommonUtil.isNullOrEmpty(filter.getSemester())) {
//            if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER1.toString())) {
//                academicExamSetting.setSemester(SemesterEnum.SEMESTER1);
//            } else if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER2.toString())) {
//                academicExamSetting.setSemester(SemesterEnum.SEMESTER2);
//            } else if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER3.toString())) {
//                academicExamSetting.setSemester(SemesterEnum.SEMESTER3);
//            } else if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER4.toString())) {
//                academicExamSetting.setSemester(SemesterEnum.SEMESTER4);
//            } else if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER5.toString())) {
//                academicExamSetting.setSemester(SemesterEnum.SEMESTER5);
//            } else if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER6.toString())) {
//                academicExamSetting.setSemester(SemesterEnum.SEMESTER6);
//            } else if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER7.toString())) {
//                academicExamSetting.setSemester(SemesterEnum.SEMESTER7);
//            } else if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER8.toString())) {
//                academicExamSetting.setSemester(SemesterEnum.SEMESTER8);
//            }
//        }
        Example<AcademicExamSetting> example = Example.of(academicExamSetting);
        List<AcademicExamSetting> list = this.academicExamSettingRepository.findAll(example);
        List<CmsAcademicExamSettingVo> ls = new ArrayList<>();
        for(AcademicExamSetting ae: list) {
            CmsAcademicExamSettingVo vo = CommonUtil.createCopyProperties(ae, CmsAcademicExamSettingVo.class);
            vo.setStrexamDate(DateFormatUtil.changeLocalDateFormat(ae.getExamDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setExamDate(null);
            ls.add(vo);
        }
        return ls;
    }



    public List<CmsAcademicExamSettingVo> getAcademicList(){
        List<AcademicExamSetting> list = this.academicExamSettingRepository.findAll();
        List<CmsAcademicExamSettingVo> ls = changeAcademicSettingToCmsAcademicSettingList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }
    private List<CmsAcademicExamSettingVo> changeAcademicSettingToCmsAcademicSettingList(List<AcademicExamSetting> list){
        List<CmsAcademicExamSettingVo> ls = new ArrayList<>();
        for(AcademicExamSetting ac: list) {
            CmsAcademicExamSettingVo vo = CommonUtil.createCopyProperties(ac, CmsAcademicExamSettingVo.class);
            convertDatesAndProvideDependencies(ac, vo);
            ls.add(vo);
        }
        return ls;
    }
    private void convertDatesAndProvideDependencies(AcademicExamSetting ac, CmsAcademicExamSettingVo vo) {
        if(ac.getExamDate() != null) {
            vo.setStrexamDate(DateFormatUtil.changeLocalDateFormat(ac.getExamDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setExamDate(null);
        }
    }

}
