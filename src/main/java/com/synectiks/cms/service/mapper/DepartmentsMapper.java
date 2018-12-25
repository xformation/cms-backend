package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.DepartmentsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Departments and its DTO DepartmentsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DepartmentsMapper extends EntityMapper<DepartmentsDTO, Departments> {



    default Departments fromId(Long id) {
        if (id == null) {
            return null;
        }
        Departments departments = new Departments();
        departments.setId(id);
        return departments;
    }
}
