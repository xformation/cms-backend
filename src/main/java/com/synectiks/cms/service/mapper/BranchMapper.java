package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.BranchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Branch and its DTO BranchDTO.
 */
@Mapper(componentModel = "spring", uses = {CollegeMapper.class})
public interface BranchMapper extends EntityMapper<BranchDTO, Branch> {

    @Mapping(source = "college.id", target = "collegeId")
    BranchDTO toDto(Branch branch);

    @Mapping(source = "collegeId", target = "college")
    Branch toEntity(BranchDTO branchDTO);

    default Branch fromId(Long id) {
        if (id == null) {
            return null;
        }
        Branch branch = new Branch();
        branch.setId(id);
        return branch;
    }
}
