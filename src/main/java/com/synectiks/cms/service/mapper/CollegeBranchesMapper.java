package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.CollegeBranchesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CollegeBranches and its DTO CollegeBranchesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CollegeBranchesMapper extends EntityMapper<CollegeBranchesDTO, CollegeBranches> {



    default CollegeBranches fromId(Long id) {
        if (id == null) {
            return null;
        }
        CollegeBranches collegeBranches = new CollegeBranches();
        collegeBranches.setId(id);
        return collegeBranches;
    }
}
