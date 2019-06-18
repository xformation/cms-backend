package com.synectiks.cms.business.service.exam;

import java.util.List;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.enumeration.SemesterEnum;
import com.synectiks.cms.filter.exam.ExamListFilterInput;
import com.synectiks.cms.repository.AcademicExamSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.StudentTypeEnum;
import com.synectiks.cms.filter.student.StudentListFilterInput;
import com.synectiks.cms.graphql.types.Student.StudentType;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.service.util.CommonUtil;

@Component
public class ExamService {

    @Autowired
    AcademicExamSettingRepository academicExamSettingRepository;

    @Autowired
    CommonService commonService;

    @Autowired
    private ExamService examService;

    public List<AcademicExamSetting> searchSubject(ExamListFilterInput filter) {
        AcademicExamSetting academicExamSetting = new AcademicExamSetting();
        if (!CommonUtil.isNullOrEmpty(filter.getBranchId())) {
            Branch branch = this.commonService.getBranchById(Long.valueOf(filter.getBranchId()));
            if (branch != null) {
                academicExamSetting.setBranch(branch);
            }
        }

        if (!CommonUtil.isNullOrEmpty(filter.getDepartmentId())) {
            Department department = this.commonService.getDepartmentById(Long.valueOf(filter.getDepartmentId()));
            if (department != null) {
                academicExamSetting.setDepartment(department);
            }
        }
        if (!CommonUtil.isNullOrEmpty(filter.getBatchId())) {
            Batch batch = this.commonService.getBatchById(Long.valueOf(filter.getBatchId()));
            if (batch != null) {
                academicExamSetting.setBatch(batch);
            }
        }
        if (!CommonUtil.isNullOrEmpty(filter.getSectionId())) {
            Section section = this.commonService.getSectionById(Long.valueOf(filter.getSectionId()));
            if (section != null) {
                academicExamSetting.setSection(section);
            }
        }

        if (!CommonUtil.isNullOrEmpty(filter.getSemester())) {
            if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER1.toString())) {
                academicExamSetting.setSemester(SemesterEnum.SEMESTER1);
            } else if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER2.toString())) {
                academicExamSetting.setSemester(SemesterEnum.SEMESTER2);
            } else if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER3.toString())) {
                academicExamSetting.setSemester(SemesterEnum.SEMESTER3);
            } else if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER4.toString())) {
                academicExamSetting.setSemester(SemesterEnum.SEMESTER4);
            } else if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER5.toString())) {
                academicExamSetting.setSemester(SemesterEnum.SEMESTER5);
            } else if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER6.toString())) {
                academicExamSetting.setSemester(SemesterEnum.SEMESTER6);
            } else if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER7.toString())) {
                academicExamSetting.setSemester(SemesterEnum.SEMESTER7);
            } else if (filter.getSemester().equalsIgnoreCase(SemesterEnum.SEMESTER8.toString())) {
                academicExamSetting.setSemester(SemesterEnum.SEMESTER8);
            }
        }

            Example<AcademicExamSetting> example = Example.of(academicExamSetting);
            List<AcademicExamSetting> list = this.academicExamSettingRepository.findAll(example);
            return list;
        }


    }
