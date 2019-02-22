package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.FacilityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Facility and its DTO FacilityDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FacilityMapper extends EntityMapper<FacilityDTO, Facility> {



    default Facility fromId(Long id) {
        if (id == null) {
            return null;
        }
        Facility facility = new Facility();
        facility.setId(id);
        return facility;
    }
}
