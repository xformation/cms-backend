package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AcademicExamSettingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AcademicExamSetting and its DTO AcademicExamSettingDTO.
 */
@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, AcademicYearMapper.class, SectionMapper.class})
public interface AcademicExamSettingMapper extends EntityMapper<AcademicExamSettingDTO, AcademicExamSetting> {

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "academicyear.id", target = "academicyearId")
    @Mapping(source = "section.id", target = "sectionId")
    AcademicExamSettingDTO toDto(AcademicExamSetting academicExamSetting);

    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "academicyearId", target = "academicyear")
    @Mapping(source = "sectionId", target = "section")
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