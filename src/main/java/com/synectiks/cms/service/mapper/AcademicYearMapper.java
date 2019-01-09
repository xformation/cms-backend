package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AcademicYearDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AcademicYear and its DTO AcademicYearDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AcademicYearMapper extends EntityMapper<AcademicYearDTO, AcademicYear> {



    default AcademicYear fromId(Long id) {
        if (id == null) {
            return null;
        }
        AcademicYear academicYear = new AcademicYear();
        academicYear.setId(id);
        return academicYear;
    }
}
