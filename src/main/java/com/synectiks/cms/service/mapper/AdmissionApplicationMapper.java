package com.synectiks.cms.service.mapper;

import com.synectiks.commons.entities.cms.*;
import com.synectiks.cms.service.dto.AdmissionApplicationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdmissionApplication and its DTO AdmissionApplicationDTO.
 */
@Mapper(componentModel = "spring", uses = {AdmissionEnquiryMapper.class, AcademicHistoryMapper.class, DocumentsMapper.class, BranchMapper.class, BatchMapper.class, StateMapper.class, CityMapper.class, CountryMapper.class, DepartmentMapper.class, AcademicYearMapper.class})
public interface AdmissionApplicationMapper extends EntityMapper<AdmissionApplicationDTO, AdmissionApplication> {

    @Mapping(source = "admissionEnquiry.id", target = "admissionEnquiryId")
    @Mapping(source = "academicHistory.id", target = "academicHistoryId")
    @Mapping(source = "documents.id", target = "documentsId")
    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "state.id", target = "stateId")
    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "academicyear.id", target = "academicyearId")
    AdmissionApplicationDTO toDto(AdmissionApplication admissionApplication);

    @Mapping(source = "admissionEnquiryId", target = "admissionEnquiry")
    @Mapping(source = "academicHistoryId", target = "academicHistory")
    @Mapping(source = "documentsId", target = "documents")
    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "batchId", target = "batch")
    @Mapping(source = "stateId", target = "state")
    @Mapping(source = "cityId", target = "city")
    @Mapping(source = "countryId", target = "country")
    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "academicyearId", target = "academicyear")
    AdmissionApplication toEntity(AdmissionApplicationDTO admissionApplicationDTO);

    default AdmissionApplication fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdmissionApplication admissionApplication = new AdmissionApplication();
        admissionApplication.setId(id);
        return admissionApplication;
    }
}
