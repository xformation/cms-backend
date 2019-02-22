package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.FeeCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FeeCategory and its DTO FeeCategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FeeCategoryMapper extends EntityMapper<FeeCategoryDTO, FeeCategory> {



    default FeeCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        FeeCategory feeCategory = new FeeCategory();
        feeCategory.setId(id);
        return feeCategory;
    }
}
