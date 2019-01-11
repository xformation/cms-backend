package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.CollegeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity College and its DTO CollegeDTO.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class})
public interface CollegeMapper extends EntityMapper<CollegeDTO, College> {

    @Mapping(source = "branch.id", target = "branchId")
    CollegeDTO toDto(College college);

    @Mapping(source = "branchId", target = "branch")
    College toEntity(CollegeDTO collegeDTO);

    default College fromId(Long id) {
        if (id == null) {
            return null;
        }
        College college = new College();
        college.setId(id);
        return college;
    }
}
