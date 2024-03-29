package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.DepartmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Department and its DTO DepartmentDTO.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class, AcademicYearMapper.class})
public interface DepartmentMapper extends EntityMapper<DepartmentDTO, Department> {

    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "academicyear.id", target = "academicyearId")
    DepartmentDTO toDto(Department department);

    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "academicyearId", target = "academicyear")
    Department toEntity(DepartmentDTO departmentDTO);

    default Department fromId(Long id) {
        if (id == null) {
            return null;
        }
        Department department = new Department();
        department.setId(id);
        return department;
    }
}
