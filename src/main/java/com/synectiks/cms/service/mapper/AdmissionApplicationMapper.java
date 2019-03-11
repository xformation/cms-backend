package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AdmissionApplicationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdmissionApplication and its DTO AdmissionApplicationDTO.
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface AdmissionApplicationMapper extends EntityMapper<AdmissionApplicationDTO, AdmissionApplication> {

    @Mapping(source = "student.id", target = "studentId")
    AdmissionApplicationDTO toDto(AdmissionApplication admissionApplication);

    @Mapping(source = "studentId", target = "student")
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
