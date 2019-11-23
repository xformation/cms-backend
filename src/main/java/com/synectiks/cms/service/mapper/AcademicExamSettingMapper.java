package com.synectiks.cms.service.mapper;

import com.synectiks.commons.entities.cms.*;
import com.synectiks.cms.service.dto.AcademicExamSettingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AcademicExamSetting and its DTO AcademicExamSettingDTO.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class, SubjectMapper.class, DepartmentMapper.class, TypeOfGradingMapper.class, AcademicYearMapper.class, SectionMapper.class, BatchMapper.class})
public interface AcademicExamSettingMapper extends EntityMapper<AcademicExamSettingDTO, AcademicExamSetting> {

    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "typeOfGrading.id", target = "typeOfGradingId")
    @Mapping(source = "academicyear.id", target = "academicyearId")
    @Mapping(source = "section.id", target = "sectionId")
    @Mapping(source = "batch.id", target = "batchId")
    AcademicExamSettingDTO toDto(AcademicExamSetting academicExamSetting);

    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "subjectId", target = "subject")
    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "typeOfGradingId", target = "typeOfGrading")
    @Mapping(source = "academicyearId", target = "academicyear")
    @Mapping(source = "sectionId", target = "section")
    @Mapping(source = "batchId", target = "batch")
    AcademicExamSetting toEntity(AcademicExamSettingDTO academicExamSettingDTO);

    default AcademicExamSetting fromId(Long id) {
        if (id == null) {
            return null;
        }
        AcademicExamSetting academicExamSetting = new AcademicExamSetting();
        academicExamSetting.setId(id);
        return academicExamSetting;
    }
}
