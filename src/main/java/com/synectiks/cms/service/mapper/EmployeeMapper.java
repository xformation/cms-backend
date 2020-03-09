package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.EmployeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Employee and its DTO EmployeeDTO.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class, TransportRouteMapper.class})
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {

    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "transportRoute.id", target = "transportRouteId")
    EmployeeDTO toDto(Employee employee);

    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "transportRouteId", target = "transportRoute")
    Employee toEntity(EmployeeDTO employeeDTO);

    default Employee fromId(Long id) {
        if (id == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(id);
        return employee;
    }
}
