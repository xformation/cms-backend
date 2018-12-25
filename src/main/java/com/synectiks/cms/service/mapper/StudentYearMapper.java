package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.StudentYearDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StudentYear and its DTO StudentYearDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StudentYearMapper extends EntityMapper<StudentYearDTO, StudentYear> {



    default StudentYear fromId(Long id) {
        if (id == null) {
            return null;
        }
        StudentYear studentYear = new StudentYear();
        studentYear.setId(id);
        return studentYear;
    }
}
