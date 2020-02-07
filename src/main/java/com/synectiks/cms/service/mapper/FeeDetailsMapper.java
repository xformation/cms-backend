package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.FeeDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FeeDetails and its DTO FeeDetailsDTO.
 */
@Mapper(componentModel = "spring", uses = {FeeCategoryMapper.class, FacilityMapper.class, TransportRouteMapper.class})
public interface FeeDetailsMapper extends EntityMapper<FeeDetailsDTO, FeeDetails> {

    @Mapping(source = "feeCategory.id", target = "feeCategoryId")
    @Mapping(source = "facility.id", target = "facilityId")
    @Mapping(source = "transportRoute.id", target = "transportRouteId")
    FeeDetailsDTO toDto(FeeDetails feeDetails);

    @Mapping(source = "feeCategoryId", target = "feeCategory")
    @Mapping(source = "facilityId", target = "facility")
    @Mapping(source = "transportRouteId", target = "transportRoute")
    FeeDetails toEntity(FeeDetailsDTO feeDetailsDTO);

    default FeeDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        FeeDetails feeDetails = new FeeDetails();
        feeDetails.setId(id);
        return feeDetails;
    }
}
