package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.LegalEntityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LegalEntity and its DTO LegalEntityDTO.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class, CollegeMapper.class, LocationMapper.class})
public interface LegalEntityMapper extends EntityMapper<LegalEntityDTO, LegalEntity> {

    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "college.id", target = "collegeId")
    @Mapping(source = "location.id", target = "locationId")
    LegalEntityDTO toDto(LegalEntity legalEntity);

    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "collegeId", target = "college")
    @Mapping(source = "locationId", target = "location")
    LegalEntity toEntity(LegalEntityDTO legalEntityDTO);

    default LegalEntity fromId(Long id) {
        if (id == null) {
            return null;
        }
        LegalEntity legalEntity = new LegalEntity();
        legalEntity.setId(id);
        return legalEntity;
    }
}
