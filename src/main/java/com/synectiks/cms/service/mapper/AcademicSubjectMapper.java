package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AcademicSubjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AcademicSubject and its DTO AcademicSubjectDTO.
 */
@Mapper(componentModel = "spring", uses = {AcademicDepartmentMapper.class})
public interface AcademicSubjectMapper extends EntityMapper<AcademicSubjectDTO, AcademicSubject> {

    @Mapping(source = "academicDepartment.id", target = "academicDepartmentId")
    AcademicSubjectDTO toDto(AcademicSubject academicSubject);

    @Mapping(source = "academicDepartmentId", target = "academicDepartment")
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
