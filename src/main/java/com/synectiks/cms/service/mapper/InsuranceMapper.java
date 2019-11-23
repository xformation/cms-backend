package com.synectiks.cms.service.mapper;

import com.synectiks.commons.entities.cms.*;
import com.synectiks.cms.service.dto.InsuranceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Insurance and its DTO InsuranceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InsuranceMapper extends EntityMapper<InsuranceDTO, Insurance> {


    @Mapping(target = "vehicle", ignore = true)
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
