package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.LateFeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LateFee and its DTO LateFeeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LateFeeMapper extends EntityMapper<LateFeeDTO, LateFee> {



    default LateFee fromId(Long id) {
        if (id == null) {
            return null;
        }
        LateFee lateFee = new LateFee();
        lateFee.setId(id);
        return lateFee;
    }
}
