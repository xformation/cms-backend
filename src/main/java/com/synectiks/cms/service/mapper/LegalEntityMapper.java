package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.LegalEntityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LegalEntity and its DTO LegalEntityDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LegalEntityMapper extends EntityMapper<LegalEntityDTO, LegalEntity> {



    default LegalEntity fromId(Long id) {
        if (id == null) {
            return null;
        }
        LegalEntity legalEntity = new LegalEntity();
        legalEntity.setId(id);
        return legalEntity;
    }
}
