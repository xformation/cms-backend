package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.LateFeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LateFee and its DTO LateFeeDTO.
 */
@Mapper(componentModel = "spring", uses = {CollegeMapper.class, BranchMapper.class, AcademicYearMapper.class, TermMapper.class})
public interface LateFeeMapper extends EntityMapper<LateFeeDTO, LateFee> {

    @Mapping(source = "college.id", target = "collegeId")
    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "academicYear.id", target = "academicYearId")
    @Mapping(source = "term.id", target = "termId")
    LateFeeDTO toDto(LateFee lateFee);

    @Mapping(source = "collegeId", target = "college")
    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "academicYearId", target = "academicYear")
    @Mapping(source = "termId", target = "term")
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
