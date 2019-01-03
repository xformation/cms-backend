package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AcademicDepartmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AcademicDepartment and its DTO AcademicDepartmentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AcademicDepartmentMapper extends EntityMapper<AcademicDepartmentDTO, AcademicDepartment> {



    default AcademicDepartment fromId(Long id) {
        if (id == null) {
            return null;
        }
        AcademicDepartment academicDepartment = new AcademicDepartment();
        academicDepartment.setId(id);
        return academicDepartment;
    }
}
