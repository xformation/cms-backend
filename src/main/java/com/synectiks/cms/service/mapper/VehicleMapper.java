package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.VehicleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Vehicle and its DTO VehicleDTO.
 */
@Mapper(componentModel = "spring", uses = {TransportRouteMapper.class, ContractMapper.class})
public interface VehicleMapper extends EntityMapper<VehicleDTO, Vehicle> {

    @Mapping(source = "transportRoute.id", target = "transportRouteId")
    @Mapping(source = "contract.id", target = "contractId")
    VehicleDTO toDto(Vehicle vehicle);

    @Mapping(source = "transportRouteId", target = "transportRoute")
    @Mapping(source = "contractId", target = "contract")
    @Mapping(target = "insurance", ignore = true)
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
