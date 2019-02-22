package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.LateFeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LateFee and its DTO LateFeeDTO.
 */
@Mapper(componentModel = "spring", uses = {CollegeMapper.class, BranchMapper.class})
public interface LateFeeMapper extends EntityMapper<LateFeeDTO, LateFee> {

    @Mapping(source = "college.id", target = "collegeId")
    @Mapping(source = "branch.id", target = "branchId")
    LateFeeDTO toDto(LateFee lateFee);

    @Mapping(source = "collegeId", target = "college")
    @Mapping(source = "branchId", target = "branch")
    LateFee toEntity(LateFeeDTO lateFeeDTO);

    default LateFee fromId(Long id) {
        if (id == null) {
            return null;
        }
        LateFee lateFee = new LateFee();
        lateFee.setId(id);
        return lateFee;
    }
}
