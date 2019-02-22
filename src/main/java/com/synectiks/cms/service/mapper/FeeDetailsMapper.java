package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.FeeDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FeeDetails and its DTO FeeDetailsDTO.
 */
@Mapper(componentModel = "spring", uses = {FeeCategoryMapper.class, BatchMapper.class, FacilityMapper.class, TransportRouteMapper.class, CollegeMapper.class, DepartmentMapper.class, BranchMapper.class, AcademicYearMapper.class})
public interface FeeDetailsMapper extends EntityMapper<FeeDetailsDTO, FeeDetails> {

    @Mapping(source = "feeCategory.id", target = "feeCategoryId")
    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "facility.id", target = "facilityId")
    @Mapping(source = "transportRoute.id", target = "transportRouteId")
    @Mapping(source = "college.id", target = "collegeId")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "academicYear.id", target = "academicYearId")
    FeeDetailsDTO toDto(FeeDetails feeDetails);

    @Mapping(source = "feeCategoryId", target = "feeCategory")
    @Mapping(source = "batchId", target = "batch")
    @Mapping(source = "facilityId", target = "facility")
    @Mapping(source = "transportRouteId", target = "transportRoute")
    @Mapping(source = "collegeId", target = "college")
    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "academicYearId", target = "academicYear")
    FeeDetails toEntity(FeeDetailsDTO feeDetailsDTO);

    default FeeDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        FeeDetails feeDetails = new FeeDetails();
        feeDetails.setId(id);
        return feeDetails;
    }
}
