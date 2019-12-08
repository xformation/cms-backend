package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.VehicleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Vehicle and its DTO VehicleDTO.
 */
@Mapper(componentModel = "spring", uses = {InsuranceMapper.class, EmployeeMapper.class, TransportRouteMapper.class, ContractMapper.class, CollegeMapper.class, BranchMapper.class})
public interface VehicleMapper extends EntityMapper<VehicleDTO, Vehicle> {

    @Mapping(source = "insurance.id", target = "insuranceId")
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "transportRoute.id", target = "transportRouteId")
    @Mapping(source = "contract.id", target = "contractId")
    @Mapping(source = "college.id", target = "collegeId")
    @Mapping(source = "branch.id", target = "branchId")
    VehicleDTO toDto(Vehicle vehicle);

    @Mapping(source = "insuranceId", target = "insurance")
    @Mapping(source = "employeeId", target = "employee")
    @Mapping(source = "transportRouteId", target = "transportRoute")
    @Mapping(source = "contractId", target = "contract")
    @Mapping(source = "collegeId", target = "college")
    @Mapping(source = "branchId", target = "branch")
    Vehicle toEntity(VehicleDTO vehicleDTO);

    default Vehicle fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        return vehicle;
    }
}
