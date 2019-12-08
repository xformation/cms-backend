package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.AdmissionEnquiryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdmissionEnquiry and its DTO AdmissionEnquiryDTO.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class, DepartmentMapper.class, BatchMapper.class, StateMapper.class, CityMapper.class, CountryMapper.class})
public interface AdmissionEnquiryMapper extends EntityMapper<AdmissionEnquiryDTO, AdmissionEnquiry> {

    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "state.id", target = "stateId")
    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "country.id", target = "countryId")
    AdmissionEnquiryDTO toDto(AdmissionEnquiry admissionEnquiry);

    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "batchId", target = "batch")
    @Mapping(source = "stateId", target = "state")
    @Mapping(source = "cityId", target = "city")
    @Mapping(source = "countryId", target = "country")
    AdmissionEnquiry toEntity(AdmissionEnquiryDTO admissionEnquiryDTO);

    default AdmissionEnquiry fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
        admissionEnquiry.setId(id);
        return admissionEnquiry;
    }
}
