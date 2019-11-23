package com.synectiks.cms.service.mapper;

import com.synectiks.commons.entities.cms.*;
import com.synectiks.cms.service.dto.FeeCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FeeCategory and its DTO FeeCategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class})
public interface FeeCategoryMapper extends EntityMapper<FeeCategoryDTO, FeeCategory> {

    @Mapping(source = "branch.id", target = "branchId")
    FeeCategoryDTO toDto(FeeCategory feeCategory);

    @Mapping(source = "branchId", target = "branch")
    FeeCategory toEntity(FeeCategoryDTO feeCategoryDTO);

    default FeeCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        FeeCategory feeCategory = new FeeCategory();
        feeCategory.setId(id);
        return feeCategory;
    }
}
