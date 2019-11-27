package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.StudentExamReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StudentExamReport and its DTO StudentExamReportDTO.
 */
@Mapper(componentModel = "spring", uses = {AcademicExamSettingMapper.class, StudentMapper.class, SectionMapper.class, SubjectMapper.class, DepartmentMapper.class, TypeOfGradingMapper.class, BatchMapper.class, AcademicYearMapper.class})
public interface StudentExamReportMapper extends EntityMapper<StudentExamReportDTO, StudentExamReport> {

    @Mapping(source = "academicExamSetting.id", target = "academicExamSettingId")
    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "section.id", target = "sectionId")
    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "typeOfGrading.id", target = "typeOfGradingId")
    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "academicyear.id", target = "academicyearId")
    StudentExamReportDTO toDto(StudentExamReport studentExamReport);

    @Mapping(source = "academicExamSettingId", target = "academicExamSetting")
    @Mapping(source = "studentId", target = "student")
    @Mapping(source = "sectionId", target = "section")
    @Mapping(source = "subjectId", target = "subject")
    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "typeOfGradingId", target = "typeOfGrading")
    @Mapping(source = "batchId", target = "batch")
    @Mapping(source = "academicyearId", target = "academicyear")
    StudentExamReport toEntity(StudentExamReportDTO studentExamReportDTO);

    default StudentExamReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        StudentExamReport studentExamReport = new StudentExamReport();
        studentExamReport.setId(id);
        return studentExamReport;
    }
}
