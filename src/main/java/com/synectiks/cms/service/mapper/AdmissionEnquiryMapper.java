package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AdmissionEnquiryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdmissionEnquiry and its DTO AdmissionEnquiryDTO.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class, AdmissionApplicationMapper.class})
public interface AdmissionEnquiryMapper extends EntityMapper<AdmissionEnquiryDTO, AdmissionEnquiry> {

    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "admissionApplication.id", target = "admissionApplicationId")
    AdmissionEnquiryDTO toDto(AdmissionEnquiry admissionEnquiry);

    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "admissionApplicationId", target = "admissionApplication")
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
