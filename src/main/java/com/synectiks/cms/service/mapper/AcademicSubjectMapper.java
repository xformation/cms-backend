package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AcademicSubjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AcademicSubject and its DTO AcademicSubjectDTO.
 */
@Mapper(componentModel = "spring", uses = {DepartmentsMapper.class})
public interface AcademicSubjectMapper extends EntityMapper<AcademicSubjectDTO, AcademicSubject> {

    @Mapping(source = "department.id", target = "departmentId")
    AcademicSubjectDTO toDto(AcademicSubject academicSubject);

    @Mapping(source = "departmentId", target = "department")
    AcademicSubject toEntity(AcademicSubjectDTO academicSubjectDTO);

    default AcademicSubject fromId(Long id) {
        if (id == null) {
            return null;
        }
        AcademicSubject academicSubject = new AcademicSubject();
        academicSubject.setId(id);
        return academicSubject;
    }
}
