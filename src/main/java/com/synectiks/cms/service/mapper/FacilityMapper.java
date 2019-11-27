package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.FacilityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Facility and its DTO FacilityDTO.
 */
@Mapper(componentModel = "spring", uses = {AcademicYearMapper.class, BranchMapper.class})
public interface FacilityMapper extends EntityMapper<FacilityDTO, Facility> {

    @Mapping(source = "academicYear.id", target = "academicYearId")
    @Mapping(source = "branch.id", target = "branchId")
    FacilityDTO toDto(Facility facility);

    @Mapping(source = "academicYearId", target = "academicYear")
    @Mapping(source = "branchId", target = "branch")
    Facility toEntity(FacilityDTO facilityDTO);

    default Facility fromId(Long id) {
        if (id == null) {
            return null;
        }
        Facility facility = new Facility();
        facility.setId(id);
        return facility;
    }
}
