package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.InsuranceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Insurance and its DTO InsuranceDTO.
 */
@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface InsuranceMapper extends EntityMapper<InsuranceDTO, Insurance> {

    @Mapping(source = "vehicle.id", target = "vehicleId")
    InsuranceDTO toDto(Insurance insurance);

    @Mapping(source = "vehicleId", target = "vehicle")
    Insurance toEntity(InsuranceDTO insuranceDTO);

    default Insurance fromId(Long id) {
        if (id == null) {
            return null;
        }
        Insurance insurance = new Insurance();
        insurance.setId(id);
        return insurance;
    }
}
